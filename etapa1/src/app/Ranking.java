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
    

public void ordenarClassificacao() {
    Collections.sort(classificacao, new Comparator<Time>() {
        @Override
        public int compare(Time t1, Time t2) {
            if (t1.getPontos() != t2.getPontos()) {
                return Integer.compare(t2.getPontos(), t1.getPontos());
            }
            
            if (t1.getnVitorias() != t2.getnVitorias()) {
                return Integer.compare(t2.getnVitorias(), t1.getnVitorias());
            }
            
            if (t1.getSaldoGols() != t2.getSaldoGols()) {
                return Integer.compare(t2.getSaldoGols(), t1.getSaldoGols());
            }
            
            if (t1.getGolsMarcados() != t2.getGolsMarcados()) {
                return Integer.compare(t2.getGolsMarcados(), t1.getGolsMarcados());
            }
            
            if (t1.getCartoesVermelhos() != t2.getCartoesVermelhos()) {
                return Integer.compare(t1.getCartoesVermelhos(), t2.getCartoesVermelhos());
            }
            

            if (t1.getCartoesAmarelos() != t2.getCartoesAmarelos()) {
                return Integer.compare(t1.getCartoesAmarelos(), t2.getCartoesAmarelos());
            }

            return t1.getNome().compareTo(t2.getNome());
        }
    });
}


    public int getColocacao(Time time) {
        ordenarClassificacao();
        
        for (int i = 0; i < this.classificacao.size(); i++) {
            if (this.classificacao.get(i).getNome().equals(time.getNome())) {
                return i + 1; // colocação começa em 1
            }
        }
        return -1; // time não encontrado
    }

    // lista de times ordenados
    public List<Time> getClassificacao() {
        ordenarClassificacao();
        return new ArrayList<>(classificacao);
    }


    public void adicionarTime(Time time) {
        if (time == null) return;
        for (Time t : classificacao) {
            if (t.getNome().equalsIgnoreCase(time.getNome())) {
                return;
            }
        }
        classificacao.add(time);
    }
    
    private void garantirTimesDaPartida(Partida p) {
        if (p == null) return;
        adicionarTime(p.getMandante());
        adicionarTime(p.getVisitante());
    }


    public void aplicarRodada(List<Partida> partidasDaRodada) {
        if (partidasDaRodada == null) return;
        for (Partida p : partidasDaRodada) garantirTimesDaPartida(p);
        for (Partida p : partidasDaRodada) processarPartida(p);
        ordenarClassificacao();
    }


    public void recomputarAteRodada(int numeroRodada) {
        if (numeroRodada <= 0) return;
        ArrayList<ArrayList<Partida>> fonte = Rodada.getRodadas(); 
        if (fonte == null || fonte.isEmpty()) return;

        
        coletarTimesDasRodadas(); // uso Rodada.getRodadas() internamente


        zerarEstatisticasTimes();

        // percorre as rodadas do calendário em ordem e aplica até numeroRodada (inclusive)
        for (List<Partida> rodada : fonte) {
            if (rodada == null || rodada.isEmpty()) continue;
            int idRodada = rodada.get(0).getIdRodada();
            if (idRodada > numeroRodada) break;

            // aplica somente partidas já jogadas na rodada
            for (Partida p : rodada) {
                garantirTimesDaPartida(p);
                processarPartida(p);
            }

            // ordena após concluir a rodada para refletir classificação rodada-a-rodada
            ordenarClassificacao();
        }

        // ordenação final para consistência
        ordenarClassificacao();
    }

    
    public void aplicarRodadaPorId(int idRodada) {
        if (idRodada <= 0) return;
        ArrayList<ArrayList<Partida>> rodadas = Rodada.getRodadas(); 
        if (rodadas == null || idRodada >= rodadas.size()) return;
        for (List<Partida> rodada : rodadas) {
            if (rodada == null || rodada.isEmpty()) continue;
            if (rodada.get(0).getIdRodada() == idRodada) {
                aplicarRodada(rodada);
                return;
            }
        }
    }

    private void processarPartida(Partida p) {
        if (p == null || !p.getPlacar().foiJogado()) return;
        int gm = p.getPlacar().getGolsMandante();
        int gv = p.getPlacar().getGolsVisitante();
        Time mand = p.getMandante();
        Time vist = p.getVisitante();

        // garante que os objetos Time estão na lista de classificação
        garantirTimesDaPartida(p);

        try {
            switch (p.placar.getResultado()) {
                case VITORIA_MANDANTE:
                    mand.registrarVitoria(gm, gv); // soma 3 pontos, 1 vitória, gols e saldo
                    vist.registrarDerrota(gv, gm); // soma 0 pontos, derrota, gols e saldo
                    break;
                case VITORIA_VISITANTE:
                    vist.registrarVitoria(gv, gm);
                    mand.registrarDerrota(gm, gv);
                    break;
                case EMPATE:
                    mand.registrarEmpate(gm); // soma 1 ponto, gols
                    vist.registrarEmpate(gv);
                    break;
            }
        } catch (TipoInvalidoException | NumeroInvalidoException e) {
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
    

    private void coletarTimesDasRodadas() {
        ArrayList<ArrayList<Partida>> calendario = Rodada.getRodadas();
        if (calendario == null) return;
        for (ArrayList<Partida> rodada : calendario) {
            if (rodada == null) continue;
            for (Partida p : rodada) {
                if (p == null) continue;
                adicionarTime(p.getMandante());
                adicionarTime(p.getVisitante());
            }
        }
    }
    
    
    public ArrayList<Time> getClassificadosLibertadores() {
        ordenarClassificacao();
        int fim = Math.min(6, classificacao.size());
        return new ArrayList<>(classificacao.subList(0, fim));
    }
    
 
    public ArrayList<Time> getClassificadosSulAmericana() {
        ordenarClassificacao();
        int inicio = Math.min(6, classificacao.size());
        int fim = Math.min(12, classificacao.size());
        return new ArrayList<>(classificacao.subList(inicio, fim));
    }

    public ArrayList<Time> getRebaixados() {
        ordenarClassificacao();
        int tamanho = classificacao.size();
        int inicio = Math.max(0, tamanho - 4);
        return new ArrayList<>(classificacao.subList(inicio, tamanho));
    }
}