import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // dados a serem analisados
        List<Double> data = new ArrayList<>(Arrays.asList(
            0.012, -0.005, 0.008, -0.011, 0.002, 0.015, -0.009, 0.001, 0.010, -0.003,
            0.007, -0.010, 0.013, -0.006, 0.004, -0.002, 0.011, -0.014, 0.009, 0.000,
            -0.008, 0.014, 0.003, -0.007, 0.006, -0.012, 0.005, -0.001, 0.016, -0.004,

            // perdas moderadas
            -0.025, 0.018, -0.021, 0.019, -0.030,

            // perdas acentuadas
            -0.055, // 5% piores (posição 1 de 40 é 2.5%)
            -0.080, // Pior dia (posição 0)
            0.022, -0.019, 0.025
        ));


        // 2. Iniciar o contexto
        Context context = new Context(new ValueAtRiskAlgorithm());
        
        // 3. Executar os cálculos
        // Os resultados agora serão sempre os mesmos
        System.out.println("Executando o primeiro algoritmo (VaR):");
        context.calculate(data);

        System.out.println("\n--- Trocando de algoritmo ---");
        context.setAlgorithm(new ExpectedShortfallAlgorithm());
        System.out.println("Executando o segundo algoritmo (ES):");
        context.calculate(data);
        
        System.out.println("\n--- Trocando de algoritmo ---");
        context.setAlgorithm(new StressTestingAlgorith());
        System.out.println("Executando o terceiro algoritmo (Stress):");
        context.calculate(data);
    }
}