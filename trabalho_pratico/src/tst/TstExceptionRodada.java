package tst;

import java.util.ArrayList;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Rodada;
import app.Time;
import exceptions.*;

@Category(Excecao.class)
public class TstExceptionRodada {


    @Test(expected = ArrayTimeVazioException.class)
    public void testeLancaExcecaoParaTimesNulos() throws ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {
        ArrayList<Time> timesNulos = null;
        
        Rodada.gerarRodadasSorteio(38, timesNulos);
    }


    @Test(expected = NumeroTimesImparException.class)
    public void testeLancaExcecaoParaNumeroImparDeTimes() throws NomeVazioException, ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {
        ArrayList<Time> timesImpares = new ArrayList<>();
        timesImpares.add(new Time("Flamengo"));
        timesImpares.add(new Time("Cruzeiro"));
        timesImpares.add(new Time("Palmeiras"));

        Rodada.gerarRodadasSorteio(4, timesImpares);
    }
    

    @Test(expected = NumeroInvalidoParaRodadasException.class)
    public void testeLancaExcecaoParaQuantidadeDeRodadasInvalida() throws NomeVazioException, ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {
        ArrayList<Time> timesPares = new ArrayList<>();
        timesPares.add(new Time("Flamengo"));
        timesPares.add(new Time("Cruzeiro"));
        timesPares.add(new Time("Palmeiras"));
        timesPares.add(new Time("Santos"));

        int numeroDeRodadasIncorreto = 5; 

        Rodada.gerarRodadasSorteio(numeroDeRodadasIncorreto, timesPares);
    }
}