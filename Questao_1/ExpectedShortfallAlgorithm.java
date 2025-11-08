import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpectedShortfallAlgorithm implements Algorithm {

    @Override
    public void calculate(List<Double> historicData) {

        // criar uma nova lista
        List<Double> retornosOrdenados = new ArrayList<>(historicData);
        
        // ordenar do menor ao maior numero
        Collections.sort(retornosOrdenados);

        // Pelo algoritmo de ValueAtRisk, determina-se 95% de confiança no calculo, logo deve-se olhar aos 5% piores dados (5% = 0.05)
        
        // Encontrar a posição que representa o corte de 5%
        // Ex: Se tiver 1000 dias, 1000 * 0.05 = 50, o olhar é para o corte de posição 50
        int indiceVaR = (int) (retornosOrdenados.size() * 0.05);

        // Inicializar variavel para armazenar a soma das piores perdas
        // Pensando ainda no exemplo acima, seria somar os elementos da posição 0 até a 49.
        double somaDasPioresPerdas = 0;
        
        // loop para somar as piores perdas
        for (int i = 0; i < indiceVaR; i++) {
            somaDasPioresPerdas += retornosOrdenados.get(i);
        }

        // --- Passo 4: Calcular a Média ---
        // Inicializar variavel para armazenar a media das piores perdas
        double mediaDasPioresPerdas = 0.0;

        // Verificar se indiceVar não é 0, e então fazer a média
        if (indiceVaR > 0) {
            mediaDasPioresPerdas = somaDasPioresPerdas / indiceVaR;
        }
        
        // Converter para percentual
        double perdaMediaEmPercentual = mediaDasPioresPerdas * -100;

        System.out.println("Algoritmo Expected Shortfall (ES): ");
        System.out.println("Nos 5% piores casos, a perda média esperada é: " + perdaMediaEmPercentual + "%");
    }
    
}
