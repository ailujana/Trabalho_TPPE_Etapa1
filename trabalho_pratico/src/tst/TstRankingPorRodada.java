package tst;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Partida;
import app.Ranking;
import app.Rodada;
import app.Time;
import app.Placar;
import exceptions.NomeVazioException;

@Category(Funcional.class)
public class TstRankingPorRodada {

  @Test
  public void calculaPontuacaoEEstatisticasEmUmaRodada() throws NomeVazioException {
    Rodada.limparHistorico();

    Time fla = new Time("Flamengo");
    Time pal = new Time("Palmeiras");
    Time atl = new Time("Atletico-MG");
    Time bot = new Time("Botafogo");

    Ranking r = new Ranking(Arrays.asList(fla, pal, atl, bot));

    ArrayList<Partida> r1 = new ArrayList<>(Arrays.asList(
      Partida.of(1, fla, pal, 2, 0), // fla vence
      Partida.of(1, atl, bot, 1, 1)  // empate
    ));

    Rodada.getRodadas().clear();
    Rodada.getRodadas().add(r1);

    r.aplicarRodada(r1);

    assertEquals(3, fla.getPontos());
    assertEquals(1, fla.getnVitorias());
    assertEquals(2, fla.getGolsMarcados());
    assertEquals(0, fla.getGolsSofridos());
    assertEquals(2, fla.getSaldoGols());
    assertEquals(1, r.getColocacao(fla));

    assertEquals(0, pal.getPontos());
    assertEquals(0, pal.getnVitorias());
    assertEquals(0, pal.getGolsMarcados());
    assertEquals(2, pal.getGolsSofridos());
    assertEquals(-2, pal.getSaldoGols());

    assertEquals(1, atl.getPontos());
    assertEquals(0, atl.getnVitorias());
    assertEquals(1, atl.getGolsMarcados());
    assertEquals(1, atl.getGolsSofridos());
    assertEquals(0, atl.getSaldoGols());

    assertEquals(1, bot.getPontos());
    assertEquals(0, bot.getnVitorias());
    assertEquals(1, bot.getGolsMarcados());
    assertEquals(1, bot.getGolsSofridos());
    assertEquals(0, bot.getSaldoGols());
  }

  @Test
  public void calculaPontuacaoEEstatisticasAposMultiplasRodadas() throws NomeVazioException {
    Rodada.limparHistorico();

    Time fla = new Time("Flamengo");
    Time pal = new Time("Palmeiras");
    Time atl = new Time("Atletico-MG");
    Time bot = new Time("Botafogo");

    Ranking r = new Ranking(Arrays.asList(fla, pal, atl, bot));

    Rodada.getRodadas().clear();
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(1, fla, pal, 1, 0), // FLA 1x0 PAL
      Partida.of(1, atl, bot, 0, 0)  // ATL 0x0 BOT
    )));
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(2, fla, atl, 0, 1), // FLA 0x1 ATL
      Partida.of(2, pal, bot, 0, 0)  // PAL 0x0 BOT
    )));
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(3, fla, bot, 0, 2), // FLA 0x2 BOT
      Partida.of(3, pal, atl, 1, 1)  // PAL 1x1 ATL
    )));
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(4, fla, atl, 0, 1), // FLA 0x1 ATL
      Partida.of(4, pal, bot, 2, 2)  // PAL 2x2 BOT
    )));

    r.recomputarAteRodada(4);


    assertEquals(8, atl.getPontos());
    assertEquals(2, atl.getnVitorias());
    assertEquals(3, atl.getGolsMarcados());
    assertEquals(1, atl.getGolsSofridos());
    assertEquals(2, atl.getSaldoGols());
    assertEquals(1, r.getColocacao(atl)); // 1º lugar

    assertEquals(6, bot.getPontos());
    assertEquals(1, bot.getnVitorias());
    assertEquals(4, bot.getGolsMarcados());
    assertEquals(2, bot.getGolsSofridos());
    assertEquals(2, bot.getSaldoGols());
    assertEquals(2, r.getColocacao(bot)); // 2º lugar

    assertEquals(3, fla.getPontos());
    assertEquals(1, fla.getnVitorias());
    assertEquals(1, fla.getGolsMarcados());
    assertEquals(4, fla.getGolsSofridos());
    assertEquals(-3, fla.getSaldoGols());
    assertEquals(3, r.getColocacao(fla)); // 3º lugar (desempate: 1 vitória)

    assertEquals(3, pal.getPontos());
    assertEquals(0, pal.getnVitorias());
    assertEquals(3, pal.getGolsMarcados());
    assertEquals(4, pal.getGolsSofridos());
    assertEquals(-1, pal.getSaldoGols());
    assertEquals(4, r.getColocacao(pal)); // 4º lugar (desempate: 0 vitórias)
  }

  @Test
  public void desempataPorVitoriasAposMultiplasRodadas() throws NomeVazioException {
    Rodada.limparHistorico();

    
    Time fla = new Time("Flamengo");
    Time pal = new Time("Palmeiras");
    Time atl = new Time("Atletico-MG");
    Time bot = new Time("Botafogo");

    Ranking r = new Ranking(Arrays.asList(fla, pal, atl, bot));

    Rodada.getRodadas().clear();
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(1, fla, pal, 1, 0),
      Partida.of(1, atl, bot, 0, 0)
    )));
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(2, fla, atl, 0, 1),
      Partida.of(2, pal, bot, 0, 0)
    )));
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(3, fla, bot, 0, 2),
      Partida.of(3, pal, atl, 1, 1)
    )));
    Rodada.getRodadas().add(new ArrayList<>(Arrays.asList(
      Partida.of(4, fla, atl, 0, 1),
      Partida.of(4, pal, bot, 2, 2)
    )));


    r.recomputarAteRodada(4);

    // Fla: 1V,0E,3D => 3 pts;
    // Pal: 0V,3E,1D => 3 pts
    assertEquals(3, fla.getPontos());
    assertEquals(1, fla.getnVitorias());
    assertEquals(3, pal.getPontos());
    assertEquals(0, pal.getnVitorias());

    // desempate por vitórias coloca Fla à frente do Pal
    assertTrue(r.getColocacao(fla) < r.getColocacao(pal));
  }
}