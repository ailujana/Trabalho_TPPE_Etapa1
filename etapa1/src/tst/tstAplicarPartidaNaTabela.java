package tst;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Partida;
import app.Time;
import exceptions.NomeVazioException;

@Category(Funcional.class)
public class tstAplicarPartidaNaTabela {

  private static class FakeTabela {
    String mand, vist; int gm, gv; int chamadas;
    void registrarPartidaResultado(String m, String v, int g1, int g2) {
      this.mand = m; this.vist = v; this.gm = g1; this.gv = g2; chamadas++;
    }
  }

  @Test
  public void aplicaNaTabelaChamaRegistrarComMesmosDados() throws NomeVazioException {
    Partida p = Partida.of(5, new Time("Corinthians"), new Time("Palmeiras"), 3, 2);
    FakeTabela fake = new FakeTabela();

    p.aplicarNaTabela(new Partida.TabelaAdapter() {
      @Override public void registrarPartidaResultado(String mand, String vist, int gm, int gv) {
        fake.registrarPartidaResultado(mand, vist, gm, gv);
      }
    });

    assertEquals("Corinthians", fake.mand);
    assertEquals("Palmeiras",   fake.vist);
    assertEquals(3, fake.gm);
    assertEquals(2, fake.gv);
    assertEquals(1, fake.chamadas);
  }
}
