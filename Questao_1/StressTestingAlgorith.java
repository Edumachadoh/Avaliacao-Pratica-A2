import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StressTestingAlgorith implements Algorithm{

    @Override
    public void calculate(List<Double> historicData) {
        
        //criar uma nova lista
        List<Double> retornosOrdenados = new ArrayList<>(historicData);
        
        // ordenar do menor ao maior numero
        Collections.sort(retornosOrdenados);

        // criar uma varíavel para armazenar o pior dia, que é a posição 0
        // se inicia com 0.0
        double piorDia = 0.0; 

        // Verifica se a lista não está vazia para evitar um erro e encontra o item na posição 0 e o atribui a variavel piorDia
        if (!retornosOrdenados.isEmpty()) {
            piorDia = retornosOrdenados.get(0);
        }

        // Converter para percentual
        double perdaEmPercentual = piorDia * -100;

        System.out.println("Algoritmo de Stress Testing: ");
        System.out.println("Cenário: 'Repetir o pior dia da história'");
        System.out.println("Resultado simulado (pior perda): " + perdaEmPercentual + "%");
    }
    
}
