package app;

public class Ranking {
	private int colocacao;
	private String[] criteriosDesempate;
	
	public Ranking(int colocacao, String[] criteriosDesempate) {
		super();
		this.colocacao = colocacao;
		this.criteriosDesempate = criteriosDesempate;
	}
	public int getColocacao() {
		return colocacao;
	}
	public void setColocacao(int colocacao) {
		this.colocacao = colocacao;
	}
	public String[] getCriteriosDesempate() {
		return criteriosDesempate;
	}
	public void setCriteriosDesempate(String[] criteriosDesempate) {
		this.criteriosDesempate = criteriosDesempate;
	}
	
	

}
