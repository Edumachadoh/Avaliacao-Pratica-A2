

public class Desligada implements State {
    private UsinaNuclear usinaNuclear;

    public Desligada(UsinaNuclear usinaNuclear) {
        this.usinaNuclear = usinaNuclear;
        System.out.println("Usina desligada");
    }

    @Override
    public void manutencao() {
        System.out.println("Manutenção solicitada");
        usinaNuclear.setState(new Manutencao(usinaNuclear));
    }

    @Override
    public void emergencia() {
        System.out.println("Não é possível entrar em estado de emergencia");
        usinaNuclear.setState(new Emergencia(usinaNuclear));
    }

    @Override
    public void desligar() {
        System.out.println("A usina já está desligada");
    }

    @Override
     public void verificarTemperatura() {
        System.out.println("Não é possível verificar temperatura, usina desligada");
     }
    
}
