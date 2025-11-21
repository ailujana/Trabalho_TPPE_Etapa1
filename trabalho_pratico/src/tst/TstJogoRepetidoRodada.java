package tst;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Partida;
import app.Rodada;
import exceptions.*;

@Category(Excecao.class)
public class TstJogoRepetidoRodada extends TstTimes{

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