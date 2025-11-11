public class ValidadorSeFazHandler extends AbstractValidadorHandler{
    @Override
    protected void executar(ValidacaoContexto context) {
        // (Só executa se 1 e 2 passaram)
        String numNota = context.getDocumento().getNumeroNota();
        
        if (numNota.equals("NFe_REJEITADA_SEFAZ")) {
            context.registrarFalha("5. SEFAZ (Consulta Online) Rejeitada.");

            // RESTRITO: Se a validação 5 falhar, 4 deve fazer rollback.
            context.pararPorFalhaCritica("Falha na autorização SEFAZ.");
            
        } else {
            context.registrarSucesso("5. SEFAZ (Consulta Online) Autorizada.");
        }
    }
}
