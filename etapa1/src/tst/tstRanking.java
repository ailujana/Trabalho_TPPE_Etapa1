package tst;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Ranking;
import app.Time;
import exceptions.NomeVazioException;
import exceptions.TipoInvalidoException;

@Category(Funcional.class)
public class tstRanking {
    
    private Ranking ranking;
    private List<Time> times;
    
    @Before
    public void setUp() throws NomeVazioException {
        times = new ArrayList<>();
        times.add(new Time("Flamengo"));
        times.add(new Time("Palmeiras"));
        times.add(new Time("Atletico-MG"));
        times.add(new Time("Botafogo"));
        ranking = new Ranking(times);
    }
    
    @Test
    public void testaOrdenacaoPorPontos() throws TipoInvalidoException {
        // Flamengo: 6 pontos
        times.get(0).registrarVitoria(2, 0);
        times.get(0).registrarVitoria(1, 0);
        
        // Palmeiras: 3 pontos
        times.get(1).registrarVitoria(3, 1);
        
        // Atletico-MG: 1 ponto
        times.get(2).registrarEmpate(1);
        
        // Botafogo: 0 pontos
        times.get(3).registrarDerrota(0, 2);
        
        ranking.ordenarClassificacao();
        List<Time> classificacao = ranking.getClassificacao();
        
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
        assertEquals("Atletico-MG", classificacao.get(2).getNome());
        assertEquals("Botafogo", classificacao.get(3).getNome());
    }
    
    @Test
    public void testaDesempatePorVitorias() throws TipoInvalidoException {
        // Ambos com 6 pontos, mas Flamengo com mais vitorias
        // Flamengo: 2 vitorias (6 pontos)
        times.get(0).registrarVitoria(2, 0);
        times.get(0).registrarVitoria(1, 0);
        
        // Palmeiras: 1 vitoria + 3 empates (6 pontos)
        times.get(1).registrarVitoria(3, 0);
        times.get(1).registrarEmpate(1);
        times.get(1).registrarEmpate(1);
        times.get(1).registrarEmpate(1);
        
        ranking.ordenarClassificacao();
        List<Time> classificacao = ranking.getClassificacao();
        
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
        assertEquals(2, classificacao.get(0).getnVitorias());
        assertEquals(1, classificacao.get(1).getnVitorias());
    }
    
    @Test
    public void testaDesempatePorSaldoDeGols() throws TipoInvalidoException {
        // Ambos com 6 pontos e 2 vitorias
        // Flamengo: saldo +5 (7 marcados, 2 sofridos)
        times.get(0).registrarVitoria(5, 0);
        times.get(0).registrarVitoria(2, 0);
        
        // Palmeiras: saldo +3 (5 marcados, 2 sofridos)
        times.get(1).registrarVitoria(3, 1);
        times.get(1).registrarVitoria(2, 1);
        
        ranking.ordenarClassificacao();
        List<Time> classificacao = ranking.getClassificacao();
        
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
        assertEquals(7, classificacao.get(0).getSaldoGols());
        assertEquals(3, classificacao.get(1).getSaldoGols());
    }
    
    @Test
    public void testaDesempatePorGolsMarcados() throws TipoInvalidoException {
        // Ambos com 6 pontos, 2 vitorias e mesmo saldo (+4)
        // Flamengo: 8 gols marcados, 4 sofridos
        times.get(0).registrarVitoria(5, 2);
        times.get(0).registrarVitoria(3, 2);
        
        // Palmeiras: 6 gols marcados, 2 sofridos
        times.get(1).registrarVitoria(4, 1);
        times.get(1).registrarVitoria(2, 1);
        
        ranking.ordenarClassificacao();
        List<Time> classificacao = ranking.getClassificacao();
        
        assertEquals("Flamengo", classificacao.get(0).getNome());
        assertEquals("Palmeiras", classificacao.get(1).getNome());
        assertEquals(4, classificacao.get(0).getSaldoGols());
        assertEquals(4, classificacao.get(1).getSaldoGols());
        assertEquals(8, classificacao.get(0).getGolsMarcados());
        assertEquals(6, classificacao.get(1).getGolsMarcados());
    }
    
    @Test
    public void testaColocacaoEspecifica() throws TipoInvalidoException {
        times.get(0).registrarVitoria(3, 0); // Flamengo: 3 pontos, 3 gols
        times.get(1).registrarVitoria(2, 0); // Palmeiras: 3 pontos, 2 gols
        
        assertEquals(1, ranking.getColocacao(times.get(0)));
        assertEquals(2, ranking.getColocacao(times.get(1)));
    }
    
    @Test
    public void testaClassificadosLibertadores() throws NomeVazioException, TipoInvalidoException {
        // Adiciona mais times para testar
        List<Time> todosOsTimes = new ArrayList<>(times);
        for (int i = 4; i <= 10; i++) {
            Time t = new Time("Time" + i);
            todosOsTimes.add(t);
            ranking.adicionarTime(t);
        }
        
        // Define pontuacoes diferentes (ordem decrescente)
        for (int i = 0; i < todosOsTimes.size(); i++) {
            for (int v = 0; v < (todosOsTimes.size() - i); v++) {
                todosOsTimes.get(i).registrarVitoria(1, 0);
            }
        }
        
        ranking.ordenarClassificacao();
        List<Time> libertadores = ranking.getClassificadosLibertadores();
        assertEquals(6, libertadores.size());
        
        // Verifica que sao os 6 primeiros colocados
        List<Time> classificacao = ranking.getClassificacao();
        for (int i = 0; i < 6; i++) {
            assertEquals(classificacao.get(i).getNome(), libertadores.get(i).getNome());
        }
    }
    
    @Test
    public void testaClassificadosSulAmericana() throws NomeVazioException, TipoInvalidoException {
        // Adiciona mais times para testar
        List<Time> todosOsTimes = new ArrayList<>(times);
        for (int i = 4; i <= 12; i++) {
            Time t = new Time("Time" + i);
            todosOsTimes.add(t);
            ranking.adicionarTime(t);
        }
        
        // Define pontuacoes diferentes
        for (int i = 0; i < todosOsTimes.size(); i++) {
            for (int v = 0; v < (todosOsTimes.size() - i); v++) {
                todosOsTimes.get(i).registrarVitoria(1, 0);
            }
        }
        
        ranking.ordenarClassificacao();
        List<Time> sulAmericana = ranking.getClassificadosSulAmericana();
        assertEquals(6, sulAmericana.size());
        
        // Verifica que sao do 7o ao 12o colocados
        List<Time> classificacao = ranking.getClassificacao();
        for (int i = 0; i < 6; i++) {
            assertEquals(classificacao.get(i + 6).getNome(), sulAmericana.get(i).getNome());
        }
    }
    
    @Test
    public void testaRebaixados() throws NomeVazioException, TipoInvalidoException {
        // Adiciona mais times para testar (total de 20 times)
        List<Time> todosOsTimes = new ArrayList<>(times);
        for (int i = 4; i < 20; i++) {
            Time t = new Time("Time" + i);
            todosOsTimes.add(t);
            ranking.adicionarTime(t);
        }
        
        // Define pontuacoes diferentes
        for (int i = 0; i < todosOsTimes.size(); i++) {
            for (int v = 0; v < (todosOsTimes.size() - i); v++) {
                todosOsTimes.get(i).registrarVitoria(1, 0);
            }
        }
        
        ranking.ordenarClassificacao();
        List<Time> rebaixados = ranking.getRebaixados();
        assertEquals(4, rebaixados.size());
        
        // Verifica que sao os 4 ultimos colocados
        List<Time> classificacao = ranking.getClassificacao();
        int tamanho = classificacao.size();
        for (int i = 0; i < 4; i++) {
            assertEquals(classificacao.get(tamanho - 4 + i).getNome(), rebaixados.get(i).getNome());
        }
    }
    
    @Test
    public void testaTimeNaoEncontrado() {
        Time timeNaoAdicionado = null;
        try {
            timeNaoAdicionado = new Time("Santos");
        } catch (NomeVazioException e) {
            fail("Nao deveria lancar excecao");
        }
        
        assertEquals(-1, ranking.getColocacao(timeNaoAdicionado));
    }
}