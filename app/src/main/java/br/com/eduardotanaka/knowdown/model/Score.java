package br.com.eduardotanaka.knowdown.model;

public class Score {

    private int score;
    private int acerto;
    private int erro;
    private int coin;

    public Score() {
        setAcerto(0);
        setCoin(0);
        setErro(0);
        setScore(0);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAcerto() {
        return acerto;
    }

    public void setAcerto(int acerto) {
        this.acerto = acerto;
    }

    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }
}
