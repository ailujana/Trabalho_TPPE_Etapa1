package tst;

import static org.junit.Assert.*;

import org.junit.Test;

import app.Partida;

public class tstPartidaExcecao {

  @Test(expected = IllegalArgumentException.class)
  public void mandanteNuloOuVazio() {
    Partida.of(1, " ", "Vasco", 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void visitanteNuloOuVazio() {
    Partida.of(1, "Flamengo", null, 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void mandanteIgualVisitante() {
    Partida.of(1, "Flamengo", "flamengo", 1, 0); // case-insensitive
  }

  @Test(expected = IllegalArgumentException.class)
  public void golsNegativos() {
    Partida.of(1, "Flamengo", "Vasco", -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void idRodadaInvalidoZero() {
    Partida.of(0, "Flamengo", "Vasco", 1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void idRodadaInvalidoNegativo() {
    Partida.of(-3, "Flamengo", "Vasco", 1, 0);
  }
}
