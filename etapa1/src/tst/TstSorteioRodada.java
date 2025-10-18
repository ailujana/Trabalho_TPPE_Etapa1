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
public class TstSorteioRodada {
    public ArrayList<Time> times = new ArrayList<Time>();

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