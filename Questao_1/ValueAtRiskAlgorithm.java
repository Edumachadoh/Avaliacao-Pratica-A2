import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValueAtRiskAlgorithm implements Algorithm{

    @Override
    public void calculate(List<Double> historicData) {
        
        // fazer uma copia da lista original
        List<Double> retornosOrdenados = new ArrayList<>(historicData);

        // ordenar a lista de dados
        Collections.sort(retornosOrdenados);

        // Pelo algoritmo de ValueAtRisk, determina-se 95% de confiança no calculo, logo deve-se olhar aos 5% piores dados (5% = 0.05)
        
        // Encontrar a posição que representa o corte de 5%
        // Ex: Se tiver 1000 dias, 1000 * 0.05 = 50, o olhar é para o corte de posição 50
        int indiceVaR = (int) (retornosOrdenados.size() * 0.05);

        // Pegar o valor dessa posição
        double var = retornosOrdenados.get(indiceVaR);

        // Multiplicar por -1 para tornar a perda em realmente uma perda e então multiplicar por 100 para mostrar em porcentagem
        double perdaEmPercentual = var * -100;

        // Usamos println para ser mais simples que o 'printf'
        System.out.println("Algoritmo Value at Risk (VaR): ");
        System.out.println("Com 95% de confiança, a perda máxima esperada é: " + perdaEmPercentual + "%");
    }
    
}
