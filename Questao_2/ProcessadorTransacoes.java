public interface ProcessadorTransacoes {
    RespostaAutorizacao autorizar(double valor, String cartao, String moeda);
}
