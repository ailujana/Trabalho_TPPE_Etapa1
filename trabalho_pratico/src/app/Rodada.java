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

    //Metodo que chama os outros
    public static void gerarRodadasSorteio(int qtd, ArrayList<Time> times)
            throws ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {

        validarParametros(qtd, times);

        rodadas.clear();

        int numero_times = times.size();
        int metade = qtd / 2;

        ArrayList<Time> timesAuxiliar = new ArrayList<>(times);

        gerarRodadasIda(metade, numero_times, timesAuxiliar);
        gerarRodadasVolta(metade);
    }

    
    //Metodos novos

    private static void validarParametros(int qtd, ArrayList<Time> times)
            throws ArrayTimeVazioException, NumeroTimesImparException, NumeroInvalidoParaRodadasException {

        if (times == null) 
            throw new ArrayTimeVazioException();

        int numero_times = times.size();

        if (numero_times % 2 != 0) 
            throw new NumeroTimesImparException();

        int rodadasEsperadas = 2 * (numero_times - 1);

        if (qtd != rodadasEsperadas)
            throw new NumeroInvalidoParaRodadasException();
    }

    private static void gerarRodadasIda(int metade, int numero_times, ArrayList<Time> timesAuxiliar) {

        for (int r = 0; r < metade; r++) {

            ArrayList<Partida> rodada = new ArrayList<>();

            for (int i = 0; i < numero_times / 2; i++) {
                Time t1 = timesAuxiliar.get(i);
                Time t2 = timesAuxiliar.get(numero_times - 1 - i);

                rodada.add(Partida.agendada(r + 1, t1, t2));
            }

            rodadas.add(rodada);
            rotacionarTimes(timesAuxiliar);
        }
    }

    private static void rotacionarTimes(ArrayList<Time> timesAuxiliar) {
        Time ultimo = timesAuxiliar.remove(timesAuxiliar.size() - 1);
        timesAuxiliar.add(1, ultimo);
    }

    private static void gerarRodadasVolta(int metade) {
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
