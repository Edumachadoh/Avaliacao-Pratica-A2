public class Client {
    public static void main(String[] args) {

        SistemaBancarioLegado sistemaLegado = new SistemaBancarioLegado();

        // O Adapter é configurado com o "campo obrigatório" codigoLoja
        ProcessadorTransacoes processador = new PagamentoAdapter(sistemaLegado, "LOJA_01");

        System.out.println("[Cliente] Enviando pagamento de 1600 BRL...");
        // O cliente usa o método moderno com "BRL"
        RespostaAutorizacao resposta = processador.autorizar(1600.0, "1234-5678", "BRL");
        
        System.out.println(resposta);

        System.out.println("\n---------------------\n");

        System.out.println("[Cliente] Enviando pagamento de 50 USD...");
        
        // O cliente usa o método moderno com "USD"
        RespostaAutorizacao respostaUsd = processador.autorizar(50.0, "9999-8888", "USD");

        System.out.println(respostaUsd);
    }
}
