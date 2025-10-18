package tst;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import app.Partida;
import app.Time;
import exceptions.NomeVazioException;


@Category(Funcional.class)
@RunWith(Parameterized.class)
public class tstPartidaResultado {

  private final int gm, gv;
  private final Partida.Resultado esperado;
  private final boolean empate;
  private final boolean mandanteVenceu;
  private final int saldo;

  public tstPartidaResultado(int gm, int gv,
      Partida.Resultado esperado, boolean empate, boolean mandanteVenceu, int saldo) {
    this.gm = gm; this.gv = gv;
    this.esperado = esperado; this.empate = empate;
    this.mandanteVenceu = mandanteVenceu; this.saldo = saldo;
  }

  @Parameters
  public static Iterable<Object[]> params() {
    return Arrays.asList(new Object[][]{
      {2,1, Partida.Resultado.VITORIA_MANDANTE, false, true,  1},
      {0,0, Partida.Resultado.EMPATE,           true,  false, 0},
      {1,3, Partida.Resultado.VITORIA_VISITANTE,false, false,-2},
    });
  }

  @Test
  public void testaResultadoEAuxiliares() throws NomeVazioException {
    Partida p = Partida.of(1,  new Time("Flamengo"), new Time("Vasco"), gm, gv);
    assertEquals(esperado, p.getResultado());
    assertEquals(empate, p.isEmpate());
    assertEquals(mandanteVenceu, p.mandanteVenceu());
    assertEquals(saldo, p.saldoMandante());
  }
}
