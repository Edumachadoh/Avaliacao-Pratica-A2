public class RespostaAutorizacao {
    boolean autorizado;
    String codigoAutorizacao;
    String mensagemErro;

    @Override
    public String toString() {
        return "[Moderno] Autorizado: " + autorizado + ", Codigo: " + codigoAutorizacao + ", Erro: " + mensagemErro;
    
    }
}
