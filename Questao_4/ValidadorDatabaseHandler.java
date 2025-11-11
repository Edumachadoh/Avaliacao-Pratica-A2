public class ValidadorDatabaseHandler extends AbstractValidadorHandler{
    @Override
    protected void executar(ValidacaoContexto context) {
        String numNota = context.getDocumento().getNumeroNota();
        if (numNota.equals("NFe_DUPLICADA")) {
            context.registrarFalha("4. Database (Duplicidade) detectada.");
            // Não para a cadeia mas conta para o breaker
        } else {
            context.registrarSucesso("4. Database (Duplicidade) OK.");
           
            System.out.println("[AÇÃO-DB] Inserindo nota " + numNota + " (status PENDENTE)");
            
            context.adicionarRollback(() -> {
                System.out.println("[ROLLBACK-DB] Removendo nota " + numNota + " do banco.");
            });
        }
    }
}
