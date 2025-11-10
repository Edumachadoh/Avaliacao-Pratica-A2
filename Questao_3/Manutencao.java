public class Manutencao implements State{

     private UsinaNuclear usinaNuclear;

    public Manutencao(UsinaNuclear usinaNuclear) {
        this.usinaNuclear = usinaNuclear;
        System.out.println("Usina em estado de manutenção");
    }

    @Override
    public void manutencao() {
        System.err.println("Usina ja está em modo de manutencao");
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
           System.out.println("Não é possível verificar temperatura em estado de manutencao");
    }
    
}
