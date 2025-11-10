import java.util.HashMap;

public class PagamentoAdapter implements ProcessadorTransacoes{
    private SistemaBancarioLegado legado;
    private String codigoLojaObrigatorio; // O campo que o legado precisa

    // inicialização do construtor do adapter
    public PagamentoAdapter(SistemaBancarioLegado legado, String codigoLoja) {
        this.legado = legado;
        this.codigoLojaObrigatorio = codigoLoja;
    }

    // implementa de ProcessadorTransacoes
    @Override
    public RespostaAutorizacao autorizar(double valor, String cartao, String moeda) {
        
        // ADAPTAÇÃO "DE IDA" (Moderno -> Legado) 
        HashMap<String, Object> paramsLegado = new HashMap<>();
        paramsLegado.put("valor", valor);
        paramsLegado.put("cartao", cartao);
        
        // REQUISITO DO CAMPO OBRIGATÓRIO:
        paramsLegado.put("codigoLoja", this.codigoLojaObrigatorio); 
        
        // REQUISITO DA MOEDA: Converte a String "BRL" para o código 3
        paramsLegado.put("codigoMoeda", converterMoedaParaCodigo(moeda));
        
        // Chama o legado
        HashMap<String, Object> respostaLegada = legado.processarTransacao(paramsLegado);

        // ADAPTAÇÃO "DE VOLTA" (Legado -> Moderno) 
        RespostaAutorizacao respostaARespostaAutorizacao = new RespostaAutorizacao();
        
        if ((boolean) respostaLegada.get("status")) {
            respostaARespostaAutorizacao.autorizado = true;
            respostaARespostaAutorizacao.codigoAutorizacao = (String) respostaLegada.get("idAutorizacao");
        } else {
            respostaARespostaAutorizacao.autorizado = false;
            respostaARespostaAutorizacao.mensagemErro = (String) respostaLegada.get("erro");
        }
        
        return respostaARespostaAutorizacao;
    }

    /**
     * Método auxiliar privado para fazer a conversão da moeda.
     */
    private int converterMoedaParaCodigo(String moeda) {
        switch (moeda.toUpperCase()) {
            case "USD": return 1;
            case "EUR": return 2;
            case "BRL": return 3;
            default: return 0; // Código inválido
        }
    }
    
}
