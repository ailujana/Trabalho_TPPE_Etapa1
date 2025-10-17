package tst;

import org.junit.Before;

import app.Time;
import exceptions.NomeVazioException;

public class TstBasePontuacao {
    protected Time t;

    @Before
    public void setUp() throws NomeVazioException {
        t = new Time("Time A");
    }
}

