public abstract class AbstractValidadorHandler {
    protected AbstractValidadorHandler next;

    public AbstractValidadorHandler setNext(AbstractValidadorHandler next) {
        this.next = next;
        return next; 
    }

    // Método "Template" que gerencia a cadeia
    public final void processar(ValidacaoContexto context) {
        // 1. Se o Circuit Breaker ou uma falha crítica já parou a cadeia, não faz nada
        if (context.deveParar()) {
            return;
        }

     
        executar(context);
        
        // 3. Se a cadeia não foi parada, passa para o próximo
        if (next != null && !context.deveParar()) {
            next.processar(context);
        }
    }

    // As subclasses devem implementar esta lógica
    protected abstract void executar(ValidacaoContexto context);
}
