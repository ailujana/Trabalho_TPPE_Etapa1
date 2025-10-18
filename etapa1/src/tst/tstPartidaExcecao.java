package tst;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Partida;
import app.Time;
import exceptions.NomeVazioException;

@Category(Excecao.class)
public class tstPartidaExcecao {

  @Test(expected = NomeVazioException.class)
  public void mandanteNuloOuVazio() throws NomeVazioException {
    Partida.of(1, new Time(null), new Time("Vasco"), 1, 0);
  }

  @Test(expected = NomeVazioException.class)
  public void visitanteNuloOuVazio() throws NomeVazioException {
    Partida.of(1, new Time("Flamengo"), new Time(null), 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void mandanteIgualVisitante() throws NomeVazioException {
    Partida.of(1, new Time("Flamengo"), new Time("flamengo"), 1, 0); // case-insensitive
  }

  @Test(expected = IllegalArgumentException.class)
  public void golsNegativos() throws NomeVazioException {
    Partida.of(1, new Time("Flamengo"), new Time("Vasco"), -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void idRodadaInvalidoZero() throws NomeVazioException {
    Partida.of(0, new Time("Flamengo"), new Time("Vasco"), 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void idRodadaInvalidoNegativo() throws NomeVazioException {
    Partida.of(-3, new Time("Flamengo"), new Time("Vasco"), 1, 0);
  }
}
