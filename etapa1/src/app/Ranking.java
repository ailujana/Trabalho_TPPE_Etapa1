package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import exceptions.NumeroInvalidoException;
import exceptions.TipoInvalidoException;

public class Ranking {
    private List<Time> classificacao;
    
    public Ranking() {
        this.classificacao = new ArrayList<>();
    }
    
    public Ranking(List<Time> times) {
        this.classificacao = new ArrayList<>(times);
        ordenarClassificacao();
    }
    
    /**
     * Ordena os times aplicando os critérios de desempate:
     * 1. Maior pontuação
     * 2. Maior número de vitórias
     * 3. Maior saldo de gols
     * 4. Maior número de gols marcados
     */
    public void ordenarClassificacao() {
        Collections.sort(classificacao, new Comparator<Time>() {
            @Override
            public int compare(Time t1, Time t2) {
                // 1º critério: pontos (ordem decrescente)
                if (t1.getPontos() != t2.getPontos()) {
                    return Integer.compare(t2.getPontos(), t1.getPontos());
                }
                
                // 2º critério: número de vitórias (ordem decrescente)
                if (t1.getnVitorias() != t2.getnVitorias()) {
                    return Integer.compare(t2.getnVitorias(), t1.getnVitorias());
                }
                
                // 3º critério: saldo de gols (ordem decrescente)
                if (t1.getSaldoGols() != t2.getSaldoGols()) {
                    return Integer.compare(t2.getSaldoGols(), t1.getSaldoGols());
                }
                
                // 4º critério: gols marcados (ordem decrescente)
                return Integer.compare(t2.getGolsMarcados(), t1.getGolsMarcados());
            }
        });
    }
    
    /**
     * Retorna a colocação de um time específico
     */
    public int getColocacao(Time time) {
        ordenarClassificacao();
        for (int i = 0; i < classificacao.size(); i++) {
            if (classificacao.get(i).getNome().equals(time.getNome())) {
                return i + 1; // colocação começa em 1
            }
        }
        return -1; // time não encontrado
    }
    
    /**
     * Retorna a lista de times ordenada
     */
    public List<Time> getClassificacao() {
        ordenarClassificacao();
        return new ArrayList<>(classificacao);
    }
    
    /**
     * Adiciona um time à classificação
     */
    public void adicionarTime(Time time) {
        if (!classificacao.contains(time)) {
            classificacao.add(time);
        }
    }
    
    /**
     * Atualiza a classificação com base nos resultados de uma lista de partidas (uma rodada).
     * Mantém: pontos, vitórias, gols pró/contra e saldo; aplica desempate por vitórias.
     */
    public void aplicarRodada(List<Partida> partidasDaRodada) {
        if (partidasDaRodada == null) return;
        for (Partida p : partidasDaRodada) {
            processarPartida(p);
        }
        ordenarClassificacao();
    }

    /**
     * Recalcula TODA a classificação do zero até a rodada 'numeroRodada' (inclusiva),
     * usando o calendário completo (lista de rodadas).
     */
    public void recomputarAteRodada(List<? extends List<Partida>> calendario, int numeroRodada) {
        if (calendario == null || numeroRodada <= 0) return;
        zerarEstatisticasTimes();
        for (List<Partida> rodada : calendario) {
            if (rodada.isEmpty()) continue;
            // Considera o id da partida como número da rodada (ver Partida.getIdRodada())
            int id = rodada.get(0).getIdRodada();
            if (id > numeroRodada) continue;
            aplicarRodada(rodada);
        }
        ordenarClassificacao();
    }

    /**
     * Compatibilidade: atualiza com uma lista de partidas (não necessariamente agrupadas por rodada).
     */
    public void atualizarComPartidas(List<Partida> partidas) {
        if (partidas == null) return;
        for (Partida p : partidas) {
            processarPartida(p);
        }
        ordenarClassificacao();
    }

    private void processarPartida(Partida p) {
        if (p == null || !p.isJogado()) return;
        int gm = p.getGolsMandante();
        int gv = p.getGolsVisitante();
        Time mand = p.getMandante();
        Time vist = p.getVisitante();

        try {
            switch (p.getResultado()) {
                case VITORIA_MANDANTE:
                    mand.registrarVitoria(gm, gv);
                    vist.registrarDerrota(gv, gm);
                    break;
                case VITORIA_VISITANTE:
                    vist.registrarVitoria(gv, gm);
                    mand.registrarDerrota(gm, gv);
                    break;
                case EMPATE:
                    // gm == gv garantido por Partida.of para partidas jogadas
                    mand.registrarEmpate(gm);
                    vist.registrarEmpate(gv);
                    break;
            }
        } catch (TipoInvalidoException | NumeroInvalidoException e) {
            // Não deve ocorrer, pois Partida valida gols e o fluxo garante coerência com o resultado
            throw new IllegalStateException("Falha ao aplicar resultado da partida na tabela", e);
        }
    }

    private void zerarEstatisticasTimes() {
        for (Time t : classificacao) {
            try {
                t.setPontos(0);
                t.setnVitorias(0);
                t.setGolsMarcados(0);
                t.setGolsSofridos(0);
            } catch (NumeroInvalidoException e) {
                throw new IllegalStateException("Falha ao resetar estatísticas do time", e);
            }
        }
    }
    
    /**
     * Retorna os times classificados para Libertadores (6 primeiros)
     */
    public List<Time> getClassificadosLibertadores() {
        ordenarClassificacao();
        int fim = Math.min(6, classificacao.size());
        return new ArrayList<>(classificacao.subList(0, fim));
    }
    
    /**
     * Retorna os times classificados para Sul-Americana (7º ao 12º)
     */
    public List<Time> getClassificadosSulAmericana() {
        ordenarClassificacao();
        int inicio = Math.min(6, classificacao.size());
        int fim = Math.min(12, classificacao.size());
        return new ArrayList<>(classificacao.subList(inicio, fim));
    }
    
    /**
     * Retorna os times rebaixados (4 últimos)
     */
    public List<Time> getRebaixados() {
        ordenarClassificacao();
        int tamanho = classificacao.size();
        int inicio = Math.max(0, tamanho - 4);
        return new ArrayList<>(classificacao.subList(inicio, tamanho));
    }
}