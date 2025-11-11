public class ValidadorCertificadoHandler extends AbstractValidadorHandler{
    @Override
    protected void executar(ValidacaoContexto context) {
        if (context.getDocumento().getCertificado().equals("CERT_VALIDO")) {
            context.registrarSucesso("2. Certificado Digital validado.");
        } else {
            context.registrarFalha("2. Certificado Digital expirado/revogado.");
            // RESTRITO: Se 2 falhar, 3 e 5 não rodam, a cadeia para
            context.pararPorFalhaCritica("Falha crítica no Certificado.");
        }
    }
}
