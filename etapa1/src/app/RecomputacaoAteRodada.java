package app;

import java.util.ArrayList;
import java.util.List;

public class RecomputacaoAteRodada {

		private Ranking ranking;
		private ArrayList<ArrayList<Partida>> fonte; 
		
		public RecomputacaoAteRodada(Ranking ranking, ArrayList<ArrayList<Partida>> fonte) {
			super();
			this.ranking = ranking;
			this.fonte = fonte;
		}
		
		// Método que veio para a classe como objeto método agora! -> refatorado 
		
		public void recomputarAteRodada(int numeroRodada) {
	        if (numeroRodada <= 0) return;
	        
	        if (this.fonte == null || this.fonte.isEmpty()) return;

	        
	        ranking.coletarTimesDasRodadas(); // uso Rodada.getRodadas() internamente


	        ranking.zerarEstatisticasTimes();

	        // percorre as rodadas do calendário em ordem e aplica até numeroRodada (inclusive)
	        for (List<Partida> rodada : this.fonte) {
	            if (rodada == null || rodada.isEmpty()) continue;
	            int idRodada = rodada.get(0).getIdRodada();
	            if (idRodada > numeroRodada) break;

	            // aplica somente partidas já jogadas na rodada
	            for (Partida p : rodada) {
	                ranking.garantirTimesDaPartida(p);
	                ranking.processarPartida(p);
	            }

	            // ordena após concluir a rodada para refletir classificação rodada-a-rodada
	            ranking.ordenarClassificacao();
	        }

	        // ordenação final para consistência
	        ranking.ordenarClassificacao();
	    }
		
		
}
