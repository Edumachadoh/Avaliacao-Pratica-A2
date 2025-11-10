

public class Emergencia implements State{
    private UsinaNuclear usinaNuclear;

    public Emergencia(UsinaNuclear usinaNuclear) {
        this.usinaNuclear = usinaNuclear;
        System.out.println("Usina em estado de emergencia");
    }

    @Override
    public void manutencao() {
         System.out.println("Manutenção solicitada");
        usinaNuclear.setState(new Manutencao(usinaNuclear));
    }

    @Override
    public void emergencia() {
        System.err.println("Usina já está em estado de emergencia");
    }

    @Override
    public void desligar() {
        System.err.println("Não é possível desligar a usina em estado de emergencia");
    }

    @Override
     public void verificarTemperatura() {
           System.out.println("Não é possível verificar temperatura em estado de emergencia");
     }
    
}
