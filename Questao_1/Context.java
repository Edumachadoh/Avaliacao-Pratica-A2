import java.util.List;

public class Context {
    private Algorithm algoritmo;

    public Context(Algorithm algoritmo) {
        setAlgorithm(algoritmo);
    }

    public void setAlgorithm(Algorithm algoritmo) {
        this.algoritmo = algoritmo;
    }

    public void calculate(List<Double> historicData) {
        algoritmo.calculate(historicData);
    }
    
}
