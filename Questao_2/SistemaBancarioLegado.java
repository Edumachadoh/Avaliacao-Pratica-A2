import java.util.HashMap;

public class SistemaBancarioLegado {

    public HashMap<String, Object> processarTransacao(HashMap<String, Object> parametros) {
        
        System.out.println("[Legado] Recebido: " + parametros);
        HashMap<String, Object> resposta = new HashMap<>();

        // Campo obrigatório (Ex: "codigoLoja")
        if (parametros.get("codigoLoja") == null) {
            resposta.put("status", false);
            resposta.put("erro", "FALTA_CODIGO_LOJA");
            return resposta;
        }

        // Código de moeda (1, 2, ou 3)
        if (parametros.get("codigoMoeda") == null) {
            resposta.put("status", false);
            resposta.put("erro", "FALTA_MOEDA");
            return resposta;
        }

        // Se passou, simula sucesso
        resposta.put("status", true);
        resposta.put("idAutorizacao", "LEGADO_ABC123");
        
        return resposta;
    }
}
