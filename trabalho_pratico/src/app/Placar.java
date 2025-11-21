package app;

import app.Partida.Resultado;

public final class Placar {

    private final int golsMandante;
    private final int golsVisitante;
    private final Partida.Status status;

    public Placar(int golsMandante, int golsVisitante, Partida.Status status) {
        this.golsMandante = golsMandante;
        this.golsVisitante = golsVisitante;
        this.status = status;
    }

    public int getGolsMandante() {
        return golsMandante;
    }

    public int getGolsVisitante() {
        return golsVisitante;
    }

    public Partida.Status getStatus() {
        return status;
    }

    public boolean foiJogado() {
        return status == Partida.Status.JOGADO;
    }

    public boolean isAgendado() {
	  return getStatus() == Partida.Status.AGENDADO;
    }

    void garantirJogado(String metodo) {
      if (!foiJogado()) {
        throw new IllegalStateException("Partida ainda não foi jogada: operação '" + metodo + "' indisponível");
      }
    }
    
    public Resultado getResultado() {
        garantirJogado("resultado");
        if (getGolsMandante() > getGolsVisitante()) return Resultado.VITORIA_MANDANTE;
        if (getGolsMandante() < getGolsVisitante()) return Resultado.VITORIA_VISITANTE;
        return Resultado.EMPATE;
    }
    
    public boolean isEmpate() {
        garantirJogado("empate");
        return getGolsMandante() == getGolsVisitante();
    }

    public boolean mandanteVenceu() {
        garantirJogado("mandanteVenceu");
        return getGolsMandante() > getGolsVisitante();
    }

    public boolean visitanteVenceu() {
        garantirJogado("visitanteVenceu");
        return getGolsVisitante() > getGolsMandante();
    }

    public int saldoMandante() {
        garantirJogado("saldoMandante");
        return getGolsMandante() - getGolsVisitante();
    }

}
