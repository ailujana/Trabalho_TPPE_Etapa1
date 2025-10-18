package app;

import java.util.ArrayList;
import exceptions.*;

public class Rodada {
    private int id;
	private ArrayList<Time> times;
    private static ArrayList<ArrayList<Partida>> rodadas = new ArrayList<ArrayList<Partida>>();


    public Rodada(int id, ArrayList<Time> times) {
        this.id = id;
        this.times = times;
    }

    public static void gerarRodadasSorteio(int qtd, ArrayList<Time> times) throws  ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {
        if (times == null) throw new ArrayTimeVazioException();

        int numero_times = times.size();

        if (numero_times % 2 != 0) throw new NumeroTimesImparException();

        int rodadas_esperadas = 2 * (numero_times - 1); 

        if (qtd != rodadas_esperadas) throw new NumeroInvalidoParaRodadasException();

        rodadas.clear();

        ArrayList<Time> times_auxiliar = new ArrayList<>(times);

        int metade = qtd / 2; 
        // combinação de times de forma circular
        for (int r = 0; r < metade; r++) {
            ArrayList<Partida> rodada = new ArrayList<>();
            for (int i = 0; i < numero_times / 2; i++) {
                Time t1 = times_auxiliar.get(i);
                Time t2 = times_auxiliar.get(numero_times - 1 - i);
                
                rodada.add(Partida.agendada(r + 1, t1, t2));
            }
            rodadas.add(rodada);

            // rotaciona, removendo o último e inserindo na posição 1
            Time ultimo = times_auxiliar.remove(numero_times - 1);
            times_auxiliar.add(1, ultimo);
        }

        // rodadas reversas
        ArrayList<ArrayList<Partida>> ida = new ArrayList<>(rodadas);

        for (int i = 0; i < ida.size(); i++) {
            ArrayList<Partida> original = ida.get(i);
            ArrayList<Partida> inversa = new ArrayList<>();
            int idVolta = i + 1 + metade;
            for (Partida p : original) {
                inversa.add(Partida.agendada(idVolta, p.getVisitante(), p.getMandante()));
            }
            rodadas.add(inversa);
        }
    }


    public static void limparHistorico() {
        rodadas.clear();
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Time> getTimes() {
		return times;
	}

	public void setTimes(ArrayList<Time> times) {
		this.times = times;
	}

	public static ArrayList<ArrayList<Partida>> getRodadas() {
		return rodadas;
	}

	public static void setRodadas(ArrayList<ArrayList<Partida>> rodadas) {
		Rodada.rodadas = rodadas;
	}

}
