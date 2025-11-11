public class ValidadorSchemaHandler extends AbstractValidadorHandler{
    @Override
    protected void executar(ValidacaoContexto context) {
        if (context.getDocumento().getXmlContent().equals("<xml>...")) {
            context.registrarSucesso("1. Schema XML (XSD) validado.");
        } else {
            context.registrarFalha("1. Schema XML (XSD) inválido.");
            // RESTRITO: Se 1 falhar, 3 e 5 não rodam, a cadeia para
            context.pararPorFalhaCritica("Falha crítica no Schema XML.");
        }
    }
}
