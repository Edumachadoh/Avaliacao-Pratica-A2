import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ValidacaoContexto {
    private DocumentoFiscal documento;
    private int contagemFalhas = 0;
    private boolean pararChain = false;
    private List<String> log = new ArrayList<>();
    
    // REQUISITO: Rollback - Usamos uma Pilha (Stack)
    private Stack<Runnable> tarefasRollback = new Stack<>();

    public ValidacaoContexto(DocumentoFiscal doc) { this.documento = doc; }

    public DocumentoFiscal getDocumento() { return documento; }
    public boolean deveParar() { return pararChain; }
    public boolean temRollbacks() { return !tarefasRollback.isEmpty(); }

    // Método para registrar uma falha
    public void registrarFalha(String mensagem) {
        log.add("[FALHA] " + mensagem);
        this.contagemFalhas++;
        
        // REQUISITO: Circuit Breaker
        if (this.contagemFalhas >= 3) {
            this.pararChain = true;
            log.add("[STOP] CIRCUIT BREAKER ATIVADO (3 falhas). Interrompendo cadeia.");
        }
    }

    // Método para registrar sucesso
    public void registrarSucesso(String mensagem) {
        log.add("[OK] " + mensagem);
    }

    // Método para parar a cadeia por uma falha crítica
    public void pararPorFalhaCritica(String mensagem) {
        log.add("[STOP] " + mensagem);
        this.pararChain = true;
    }
    
    // REQUISITO: Rollback (Adicionar)
    public void adicionarRollback(Runnable tarefa) {
        this.tarefasRollback.push(tarefa);
    }
    
    // REQUISITO: Rollback (Executar)
    public void executarRollbacks() {
        log.add("--- INICIANDO ROLLBACKS ---");
        while (!tarefasRollback.isEmpty()) {
            tarefasRollback.pop().run(); // Executa a tarefa do topo da pilha
        }
        log.add("--- ROLLBACKS CONCLUÍDOS ---");
    }

    public void printLog() {
        System.out.println("--- LOG DE VALIDAÇÃO DA NF-e ---");
        for (String s : log) {
            System.out.println(s);
        }
    }
}
