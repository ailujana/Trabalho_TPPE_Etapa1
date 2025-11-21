package app;

import exceptions.*;

public class Time {

    private String nome;
    private int pontos;
    private int nVitorias;
    private int golsMarcados;
    private int golsSofridos;
    private int qtdCartoesAmarelos;
    private int qtdCartoesVermelhos;

    public Time(String nome) throws NomeVazioException {
        if (nome == null || nome.isEmpty()) {
            throw new NomeVazioException();
        }
        this.nome = nome;
        this.pontos = 0;
        this.nVitorias = 0;
        this.golsMarcados = 0;
        this.golsSofridos = 0;
    }

    public void registrarVitoria(int golsPro, int golsContra){
        this.pontos += 3;
        this.nVitorias += 1;
        this.golsMarcados += golsPro;
        this.golsSofridos += golsContra;
    }

    public void registrarEmpate(int gols) throws TipoInvalidoException, NumeroInvalidoException{
        if (gols < 0) {
            throw new NumeroInvalidoException();
        }
        this.pontos += 1;
        this.golsMarcados += gols;
        this.golsSofridos += gols;
    }
    
    public void registrarDerrota(int golsPro, int golsContra) throws TipoInvalidoException, NumeroInvalidoException  {
        validarGols(golsPro, golsContra);
        if (golsPro >= golsContra) {
            throw new NumeroInvalidoException();
        }
        this.golsMarcados += golsPro;
        this.golsSofridos += golsContra;
    }

    private void validarGols(int golsPro, int golsContra) throws TipoInvalidoException, NumeroInvalidoException  {
        if (golsPro < 0 || golsContra < 0) {
            throw new NumeroInvalidoException();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws NomeVazioException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeVazioException();
        }
        this.nome = nome.trim();
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) throws NumeroInvalidoException {
        if (pontos < 0) {
            throw new NumeroInvalidoException();
        }
        this.pontos = pontos;
    }

    public int getnVitorias() {
        return nVitorias;
    }

    public void setnVitorias(int nVitorias) throws NumeroInvalidoException {
        if (nVitorias < 0) {
            throw new NumeroInvalidoException();
        }
        this.nVitorias = nVitorias;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public void setGolsMarcados(int golsMarcados) throws NumeroInvalidoException {
        if (golsMarcados < 0) {
            throw new NumeroInvalidoException();
        }
        this.golsMarcados = golsMarcados;
    }

    public int getGolsSofridos() {
        return golsSofridos;
    }

    public void setGolsSofridos(int golsSofridos) throws NumeroInvalidoException {
        if (golsSofridos < 0) {
            throw new NumeroInvalidoException();
        }
        this.golsSofridos = golsSofridos;
    }

    public void registrarCartaoAmarelo() {
        this.qtdCartoesAmarelos++;
    }
    public void registrarCartaoVermelho() {
        this.qtdCartoesVermelhos++;
    }

    public int getCartoesAmarelos() {
        return this.qtdCartoesAmarelos;
    }

    public int getCartoesVermelhos() {
        return this.qtdCartoesVermelhos;
    }

    public int getSaldoGols() {
        return this.golsMarcados - this.golsSofridos;
    }
}
