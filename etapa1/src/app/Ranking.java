package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
     * Atualiza a classificação com base nos resultados das partidas
     */
    public void atualizarComPartidas(List<Partida> partidas) {
        for (Partida p : partidas) {
            if (p.isJogado()) {
                atualizarTimeComPartida(p.getMandante(), p.getGolsMandante(), p.getGolsVisitante());
                atualizarTimeComPartida(p.getVisitante(), p.getGolsVisitante(), p.getGolsMandante());
            }
        }
        ordenarClassificacao();
    }
    
    private void atualizarTimeComPartida(Time time, int golsPro, int golsContra) {
        // Implementação auxiliar para atualizar estatísticas
        // (assumindo que Time já tem os métodos necessários)
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