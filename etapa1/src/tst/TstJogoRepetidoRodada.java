package tst;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Partida;
import app.Rodada;
import app.Time;
import exceptions.*;

@Category(Excecao.class)
public class TstJogoRepetidoRodada {

    private ArrayList<Time> times = new ArrayList<>();

    @Before
    public void setup() throws NomeVazioException {

        times.add(new Time("Athletico-PR"));
        times.add(new Time("Atlético-GO"));
        times.add(new Time("Atlético-MG"));
        times.add(new Time("Bahia"));
        times.add(new Time("Botafogo"));
        times.add(new Time("Corinthians"));
        times.add(new Time("Criciúma"));
        times.add(new Time("Cruzeiro"));
        times.add(new Time("Cuiabá"));
        times.add(new Time("Flamengo"));
        times.add(new Time("Fluminense"));
        times.add(new Time("Fortaleza"));
        times.add(new Time("Grêmio"));
        times.add(new Time("Internacional"));
        times.add(new Time("Juventude"));
        times.add(new Time("Palmeiras"));
        times.add(new Time("Red Bull Bragantino"));
        times.add(new Time("São Paulo"));
        times.add(new Time("Vasco da Gama"));
        times.add(new Time("Vitória"));
    }

    @Test
	@Category(Funcional.class)
    public void testeNaoExistemPartidasRepetidasNoCampeonato() throws ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {
        int numeroDeRodadas = 38;
        Rodada.gerarRodadasSorteio(numeroDeRodadas, times);
        
        ArrayList<ArrayList<Partida>> calendario = Rodada.getRodadas();

        
        Set<String> partidasUnicas = new HashSet<>();  // hash set para ver duplicados
        int totalPartidasContadas = 0;

        for (ArrayList<Partida> rodada : calendario) {
            for (Partida p : rodada) {
                totalPartidasContadas++;
                String chavePartida = p.getMandante().getNome() + " vs " + p.getVisitante().getNome();  // cria a chave no hash set
                partidasUnicas.add(chavePartida);
            }
        }

        int totalPartidasEsperado = numeroDeRodadas * (times.size() / 2);
        assertEquals(totalPartidasEsperado, totalPartidasContadas);
        assertEquals(totalPartidasEsperado, partidasUnicas.size());
    }
}