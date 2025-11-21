package tst;

import java.util.ArrayList;

import org.junit.Before;
import app.Time;
import exceptions.NomeVazioException;


public class TstTimes {
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
}

