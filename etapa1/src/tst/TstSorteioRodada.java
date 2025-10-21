package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.*;
import exceptions.*;

@Category(Funcional.class)
public class TstSorteioRodada extends TstTimes{
    
    @Test
    public void testeSorteioRodadaGeraCalendarioCompletoEValido() throws NomeVazioException, ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {
        int numeroDeRodadasEsperado = 38;
        Rodada.gerarRodadasSorteio(numeroDeRodadasEsperado, times);

        ArrayList<ArrayList<Partida>> calendario = Rodada.getRodadas();
        
        assertNotNull(calendario);
        assertEquals(numeroDeRodadasEsperado, calendario.size());

        int numeroDeTimes = times.size();
        int partidasPorRodadaEsperado = numeroDeTimes / 2;

        for (ArrayList<Partida> rodadaAtual : calendario) {
            assertEquals(partidasPorRodadaEsperado, rodadaAtual.size());

            Set<Time> timesNaRodada = new HashSet<>(); // hash set para facilitar verificação de duplicação
            for (Partida p : rodadaAtual) {
                timesNaRodada.add(p.getMandante());
                timesNaRodada.add(p.getVisitante());
            }
            assertEquals(numeroDeTimes, timesNaRodada.size());
        }
        
        Partida jogoDeIda = calendario.get(0).get(0); 
        Time mandanteIda = jogoDeIda.getMandante();
        Time visitanteIda = jogoDeIda.getVisitante();

        int rodadaDeVoltaIndex = calendario.size() / 2; 
        ArrayList<Partida> rodadaDeVolta = calendario.get(rodadaDeVoltaIndex);

        boolean encontrouJogoDeVolta = false;
        for (Partida jogoDeVolta : rodadaDeVolta) {
            if (jogoDeVolta.getMandante().equals(visitanteIda) && jogoDeVolta.getVisitante().equals(mandanteIda)) {
                encontrouJogoDeVolta = true;
                break;
            }
        }
        
        assertTrue(encontrouJogoDeVolta);
    }
}