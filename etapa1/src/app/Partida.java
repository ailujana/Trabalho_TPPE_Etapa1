package app;

public class Partida {
	/*
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }*/
	private String timeMandante, timeVisitante;
	private int golsMandanteMarcado, golsMandanteSofrido, golsVisitanteMarcado, golsVisitanteSofrido, saldoGolsMandante, saldoGolsVisitante, cartaoVermelhoMandate, cartaoAmareloMandante, cartaoVermelhoVisitante, cartaoAmareloVisitante;
	private boolean mandanteGanhou;
	
	public Partida(String timeMandante, String timeVisitante, int golsMandanteMarcado, int golsMandanteSofrido,
			int golsVisitanteMarcado, int golsVisitanteSofrido, int saldoGolsMandante, int saldoGolsVisitante,
			int cartaoVermelhoMandate, int cartaoAmareloMandante, int cartaoVermelhoVisitante,
			int cartaoAmareloVisitante) {
		super();
		this.timeMandante = timeMandante;
		this.timeVisitante = timeVisitante;
		this.golsMandanteMarcado = golsMandanteMarcado;
		this.golsMandanteSofrido = golsMandanteSofrido;
		this.golsVisitanteMarcado = golsVisitanteMarcado;
		this.golsVisitanteSofrido = golsVisitanteSofrido;
		this.saldoGolsMandante = golsMandanteMarcado - golsMandanteSofrido;
		this.saldoGolsVisitante = golsVisitanteMarcado - golsVisitanteSofrido;
		this.cartaoVermelhoMandate = cartaoVermelhoMandate;
		this.cartaoAmareloMandante = cartaoAmareloMandante;
		this.cartaoVermelhoVisitante = cartaoVermelhoVisitante;
		this.cartaoAmareloVisitante = cartaoAmareloVisitante;
	}
	public String getTimeMandante() {
		return timeMandante;
	}
	public void setTimeMandante(String timeMandante) {
		this.timeMandante = timeMandante;
	}
	public String getTimeVisitante() {
		return timeVisitante;
	}
	public void setTimeVisitante(String timeVisitante) {
		this.timeVisitante = timeVisitante;
	}
	public int getGolsMandanteMarcado() {
		return golsMandanteMarcado;
	}
	public void setGolsMandanteMarcado(int golsMandanteMarcado) {
		this.golsMandanteMarcado = golsMandanteMarcado;
	}
	public int getGolsMandanteSofrido() {
		return golsMandanteSofrido;
	}
	public void setGolsMandanteSofrido(int golsMandanteSofrido) {
		this.golsMandanteSofrido = golsMandanteSofrido;
	}
	public int getGolsVisitanteMarcado() {
		return golsVisitanteMarcado;
	}
	public void setGolsVisitanteMarcado(int golsVisitanteMarcado) {
		this.golsVisitanteMarcado = golsVisitanteMarcado;
	}
	public int getGolsVisitanteSofrido() {
		return golsVisitanteSofrido;
	}
	public void setGolsVisitanteSofrido(int golsVisitanteSofrido) {
		this.golsVisitanteSofrido = golsVisitanteSofrido;
	}
	public int getSaldoGolsMandante() {
		return saldoGolsMandante;
	}
	public void setSaldoGolsMandante(int saldoGolsMandante) {
		this.saldoGolsMandante = saldoGolsMandante;
	}
	public int getSaldoGolsVisitante() {
		return saldoGolsVisitante;
	}
	public void setSaldoGolsVisitante(int saldoGolsVisitante) {
		this.saldoGolsVisitante = saldoGolsVisitante;
	}
	public int getCartaoVermelhoMandate() {
		return cartaoVermelhoMandate;
	}
	public void setCartaoVermelhoMandate(int cartaoVermelhoMandate) {
		this.cartaoVermelhoMandate = cartaoVermelhoMandate;
	}
	public int getCartaoAmareloMandante() {
		return cartaoAmareloMandante;
	}
	public void setCartaoAmareloMandante(int cartaoAmareloMandante) {
		this.cartaoAmareloMandante = cartaoAmareloMandante;
	}
	public int getCartaoVermelhoVisitante() {
		return cartaoVermelhoVisitante;
	}
	public void setCartaoVermelhoVisitante(int cartaoVermelhoVisitante) {
		this.cartaoVermelhoVisitante = cartaoVermelhoVisitante;
	}
	public int getCartaoAmareloVisitante() {
		return cartaoAmareloVisitante;
	}
	public void setCartaoAmareloVisitante(int cartaoAmareloVisitante) {
		this.cartaoAmareloVisitante = cartaoAmareloVisitante;
	}
	public boolean isMandanteGanhou() {
		return mandanteGanhou;
	}
	public void setMandanteGanhou(boolean mandanteGanhou) {
		this.mandanteGanhou = mandanteGanhou;
	}
	
	
	
	
	
	

}
