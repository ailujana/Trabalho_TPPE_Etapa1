package app;

import exceptions.PartidaInvalidaException;

public final class Partida {

  public enum Resultado { VITORIA_MANDANTE, EMPATE, VITORIA_VISITANTE }
  public enum Status { AGENDADO, JOGADO }

  private final int idRodada;
  private final Time mandante;
  private final Time visitante;
  private final int golsMandante;
  private final int golsVisitante;
  private final Status status;

  private Partida(int idRodada, Time mandante, Time visitante,
                  int golsMandante, int golsVisitante, Status status) {

    if (mandante == null )
      throw new PartidaInvalidaException("Mandante inválido");
    if (visitante == null )
      throw new PartidaInvalidaException("Visitante inválido");

    if (mandante.getNome().toLowerCase().equals(visitante.getNome().toLowerCase()))
      throw new PartidaInvalidaException("Mandante não pode ser igual ao visitante");

    if (idRodada <= 0)
      throw new PartidaInvalidaException("idRodada inválido");

    if (status == Status.JOGADO) {
      if (golsMandante < 0 || golsVisitante < 0)
        throw new PartidaInvalidaException("Gols não podem ser negativos");
    }

    this.idRodada = idRodada;
    this.mandante = mandante;
    this.visitante = visitante;
    this.golsMandante = golsMandante;
    this.golsVisitante = golsVisitante;
    this.status = status;
  }

  // Partida já jogada 
  public static Partida of(int idRodada, Time mandante, Time visitante, int golsMandante, int golsVisitante) {
    return new Partida(idRodada, mandante, visitante, golsMandante, golsVisitante, Status.JOGADO);
  }

  // Partida apenas agendada, gols como 0
  public static Partida agendada(int idRodada, Time mandante, Time visitante) {
    return new Partida(idRodada, mandante, visitante, 0, 0, Status.AGENDADO);
  }

  public int getIdRodada() { 
	  return idRodada; 
  }

  public Time getMandante() { 
	  return mandante; 
  }
  
  public Time getVisitante() { 
	  return visitante; 
  }
  
  public int getGolsMandante() {
	  return golsMandante; 
  }
  
  public int getGolsVisitante() { 
	  return golsVisitante; 
  }
  
  public Status getStatus() { 
	  return status; 
  }
  
  public boolean isAgendado() {
	  return status == Status.AGENDADO;
  }
  
  public boolean isJogado() {
	  return status == Status.JOGADO;
  }

  public Resultado getResultado() {
    garantirJogado("resultado");
    if (golsMandante > golsVisitante) return Resultado.VITORIA_MANDANTE;
    if (golsMandante < golsVisitante) return Resultado.VITORIA_VISITANTE;
    return Resultado.EMPATE;
  }

  public boolean isEmpate() {
    garantirJogado("empate");
    return golsMandante == golsVisitante;
  }

  public boolean mandanteVenceu() {
    garantirJogado("mandanteVenceu");
    return golsMandante > golsVisitante;
  }

  public boolean visitanteVenceu() {
    garantirJogado("visitanteVenceu");
    return golsVisitante > golsMandante;
  }

  public int saldoMandante() {
    garantirJogado("saldoMandante");
    return golsMandante - golsVisitante;
  }

  public interface TabelaAdapter {
    void registrarPartidaResultado(String mand, String vist, int golsMandante, int golsVisitante);
  }

  public void aplicarNaTabela(TabelaAdapter tabela) {
    garantirJogado("aplicarNaTabela");
    tabela.registrarPartidaResultado(mandante.getNome(), visitante.getNome(), golsMandante, golsVisitante);
  }

  public String chave() {
    return mandante.getNome() + "->" + visitante.getNome();
  }

  private void garantirJogado(String metodo) {
    if (status != Status.JOGADO) {
      throw new IllegalStateException("Partida ainda não foi jogada: operação '" + metodo + "' indisponível");
    }
  }
}
