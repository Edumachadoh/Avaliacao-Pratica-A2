public class ValidadorRegrasFiscaisHandler  extends AbstractValidadorHandler{
    @Override
    protected void executar(ValidacaoContexto context) {
        // (Só executa se 1 e 2 passaram)
        if (context.getDocumento().getValorImpostos() > 100) {
            context.registrarSucesso("3. Regras Fiscais (Impostos) validadas.");
        } else {
            context.registrarFalha("3. Regras Fiscais (Impostos) inválidas.");
            // Não para a cadeia, mas conta para o Circuit Breaker
        }
    }
}
