public class Cliente {
    public static void main(String[] args) {
        // --- 1. Monta a Cadeia de Validadores ---
        AbstractValidadorHandler cadeia = new ValidadorSchemaHandler();
        cadeia.setNext(new ValidadorCertificadoHandler())
              .setNext(new ValidadorRegrasFiscaisHandler())
              .setNext(new ValidadorDatabaseHandler())
              .setNext(new ValidadorSeFazHandler());

        
        // --- Cenário 1: Falha no Rollback (Rejeitado pela SEFAZ) ---
        System.out.println("### CENÁRIO 1: Falha SEFAZ (Testando Rollback) ###");
        DocumentoFiscal doc1 = new DocumentoFiscal(
            "<xml>...", "CERT_VALIDO", 200, "NFe_REJEITADA_SEFAZ"
        );
        ValidacaoContexto ctx1 = new ValidacaoContexto(doc1);

        cadeia.processar(ctx1);

        if (ctx1.deveParar() && ctx1.temRollbacks()) {
            ctx1.executarRollbacks();
        }
        ctx1.printLog();

        
        // --- Cenário 2: Falha Circuit Breaker ---
        System.out.println("\n\n### CENÁRIO 2: Falha Circuit Breaker (3 falhas) ###");
        DocumentoFiscal doc2 = new DocumentoFiscal(
            "<xml>...", "CERT_VALIDO",
            50, 
            "NFe_DUPLICADA" 
        );

        doc2 = new DocumentoFiscal(
            "<xml>...", "CERT_VALIDO", 50, "NFe_REJEITADA_SEFAZ"
        );
        ValidacaoContexto ctx2 = new ValidacaoContexto(doc2);
        
        cadeia.processar(ctx2);
        
        if (ctx2.deveParar() && ctx2.temRollbacks()) {
            ctx2.executarRollbacks();
        }
        ctx2.printLog();
        
        
        // --- Cenário 3: Falha Crítica (Condicional) ---
        System.out.println("\n\n### CENÁRIO 3: Falha Crítica (Schema) ###");
        DocumentoFiscal doc3 = new DocumentoFiscal(
            "XML_INVALIDO", "CERT_VALIDO", 200, "NFe_123"
        );
        ValidacaoContexto ctx3 = new ValidacaoContexto(doc3);
        
        cadeia.processar(ctx3);
        ctx3.printLog();

        // --- Cenário 4: Sucesso Total (Caminho Feliz) ---
        System.out.println("\n\n### CENÁRIO 4: Sucesso Total (Caminho Feliz) ###");
        DocumentoFiscal doc4 = new DocumentoFiscal(
            "<xml>...",       
            "CERT_VALIDO",     
            500.0,             
            "NFe_VALIDA_456"   
        );
        ValidacaoContexto ctx4 = new ValidacaoContexto(doc4);

        cadeia.processar(ctx4);

        if (ctx4.deveParar() && ctx4.temRollbacks()) {
            System.out.println("[CLIENTE] ERRO: Rollback foi ativado desnecessariamente!");
            ctx4.executarRollbacks();
        } else if (!ctx4.deveParar() && ctx4.temRollbacks()) {
            System.out.println("[CLIENTE] Sucesso. Rollbacks ignorados (seriam 'commitados').");
        }

        ctx4.printLog();
    }
}
