public class Main {
    public static void main(String[] args) {
        UsinaNuclear usinaChernobyl = new UsinaNuclear(350, 9, 2);
        usinaChernobyl.manutencao();
        usinaChernobyl.desligar();

        System.out.println("----------------------------------------");

        UsinaNuclear usinaNoruega = new UsinaNuclear(450, 12, 5);
        usinaNoruega.emergencia();
        usinaNoruega.desligar();
        
    }
}