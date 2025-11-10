public class OperacaoNormal implements State{
    private UsinaNuclear usinaNuclear;

    public OperacaoNormal(UsinaNuclear usinaNuclear) {
        this.usinaNuclear = usinaNuclear;
        System.out.println("Usina em estado de operação normal");
        
    }


    @Override
    public void manutencao() {
        System.out.println("Manutenção solicitada");
        usinaNuclear.setState(new Manutencao(usinaNuclear));
    }

    @Override
    public void emergencia() {
        System.err.println("Não é possível entrar em estado de emergencia");
    }

    @Override
    public void desligar() {
          System.out.println("Desligando a usina");
        usinaNuclear.setState(new Desligada(usinaNuclear));
    }

    @Override
     public void verificarTemperatura() {
        if (usinaNuclear.getTemperatura() > 300) {
             System.out.println("Temperatura acima de 300 graus, iniciado o estado de ALERTA-AMARELO");
            usinaNuclear.setState(new AlertaAmarelo(usinaNuclear));
        }
     }
}
