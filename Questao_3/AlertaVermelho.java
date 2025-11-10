

public class AlertaVermelho implements State{
    private UsinaNuclear usinaNuclear;

    public AlertaVermelho(UsinaNuclear usinaNuclear) {
        this.usinaNuclear = usinaNuclear;
        System.out.println("Usina em estado de operação de alerta vermelho");
    }


    @Override
    public void manutencao() {
        System.out.println("Manutenção solicitada");
        usinaNuclear.setState(new Manutencao(usinaNuclear));
    }

    @Override
    public void emergencia() {
        System.out.println("Entrando em estado de emergencia");
        usinaNuclear.setState(new Emergencia(usinaNuclear));
    }

    @Override
    public void desligar() {
         System.out.println("Desligando a usina");
        usinaNuclear.setState(new Desligada(usinaNuclear));
    }

    @Override
     public void verificarTemperatura() {
        if (usinaNuclear.getPressao() > 10) {
            System.out.println("Resfriamento falhou devido a pressão muito alta, iniciado o estado de EMERGENCIA");
            usinaNuclear.setState(new Emergencia(usinaNuclear));
        }
     }
    
}
