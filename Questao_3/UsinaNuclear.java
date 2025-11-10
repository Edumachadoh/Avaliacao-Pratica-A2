public class UsinaNuclear {
    private double temperatura;
    private double pressao;
    private double radiacao;
    private State estado;

    public UsinaNuclear( double temperatura, double pressao, double radiacao) {
        this.pressao = pressao;
        this.radiacao = radiacao;
        this.temperatura = temperatura;
        this.estado = new OperacaoNormal(this);
        verificarTemperatura();
    }

    public void setState(State estado) {
        this.estado = estado;
        this.estado.verificarTemperatura();

    }

    public double getTemperatura() {
        return this.temperatura;
    }

    public double getPressao() {
        return this.pressao;
    }

    public void manutencao() {
        estado.manutencao();
    }
    public void emergencia() {
        estado.emergencia();
    }
    public void desligar() {
        estado.desligar();
    }
    public void verificarTemperatura() {
        estado.verificarTemperatura();
    }


}
