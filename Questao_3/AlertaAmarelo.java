

public class AlertaAmarelo implements State{

    private UsinaNuclear usinaNuclear;

    public AlertaAmarelo(UsinaNuclear usinaNuclear) {
        this.usinaNuclear = usinaNuclear;
        System.out.println("Usina em estado de operação de alerta amarelo");
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
        if (usinaNuclear.getTemperatura() > 400) {
            System.out.println("Temperatura acima de 400 graus, iniciado o estado de ALERTA-VERMELHO");
             usinaNuclear.setState(new AlertaVermelho(usinaNuclear));
        }
     }
    
    
}
