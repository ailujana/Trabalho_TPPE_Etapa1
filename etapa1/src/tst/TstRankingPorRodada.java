package tst;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import app.Partida;
import app.Ranking;
import app.Time;
import exceptions.NomeVazioException;

@Category(Funcional.class)
public class TstRankingPorRodada {

  @Test
  public void calculaPontuacaoEEstatisticasEmUmaRodada() throws NomeVazioException {
    Time fla = new Time("Flamengo");
    Time pal = new Time("Palmeiras");
    Time atl = new Time("Atletico-MG");
    Time bot = new Time("Botafogo");

    Ranking r = new Ranking(Arrays.asList(fla, pal, atl, bot));

    List<Partida> r1 = Arrays.asList(
      Partida.of(1, fla, pal, 2, 0), // Fla vence
      Partida.of(1, atl, bot, 1, 1)  // empate
    );

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
  public void desempataPorVitoriasAposMultiplasRodadas() throws NomeVazioException {
    // Construímos 4 rodadas para igualar pontos com número de vitórias diferente
    Time fla = new Time("Flamengo");
    Time pal = new Time("Palmeiras");
    Time atl = new Time("Atletico-MG");
    Time bot = new Time("Botafogo");

    Ranking r = new Ranking(Arrays.asList(fla, pal, atl, bot));

    List<List<Partida>> calendario = new ArrayList<>();

    // R1: Fla 1-0 Pal, Atl 0-0 Bot
    calendario.add(Arrays.asList(
      Partida.of(1, fla, pal, 1, 0),
      Partida.of(1, atl, bot, 0, 0)
    ));
    // R2: Fla 0-1 Atl, Pal 0-0 Bot
    calendario.add(Arrays.asList(
      Partida.of(2, fla, atl, 0, 1),
      Partida.of(2, pal, bot, 0, 0)
    ));
    // R3: Fla 0-2 Bot, Pal 1-1 Atl
    calendario.add(Arrays.asList(
      Partida.of(3, fla, bot, 0, 2),
      Partida.of(3, pal, atl, 1, 1)
    ));
    // R4: Fla 0-1 Atl, Pal 2-2 Bot
    calendario.add(Arrays.asList(
      Partida.of(4, fla, atl, 0, 1),
      Partida.of(4, pal, bot, 2, 2)
    ));

    r.recomputarAteRodada(calendario, 4);

    // Fla: 1V,0E,3D => 3 pts; Pal: 0V,3E,1D => 3 pts
    assertEquals(3, fla.getPontos());
    assertEquals(1, fla.getnVitorias());
    assertEquals(3, pal.getPontos());
    assertEquals(0, pal.getnVitorias());

    // Desempate por vitórias coloca Fla à frente do Pal
    assertTrue(r.getColocacao(fla) < r.getColocacao(pal));
  }
}