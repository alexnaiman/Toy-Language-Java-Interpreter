package Controller;

import Model.ProgramState;
import Model.GeneralStatement.IStatement;
import Model.Utils.IStack;
import Repository.IRepository;
import Exception.StatementExecutionError;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private boolean isLogging = true;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    private ProgramState oneStep(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty()) throw new StatementExecutionError("Execution Stack is empty");
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public boolean isLogging() {
        return isLogging;
    }

    public void setLogging(boolean logging) {
        isLogging = logging;
    }

    public void allStep() {
        ProgramState programState = repository.getCurrent(); // repo is the controller field of type MyRepoInterface
        try {
            while (!programState.getExecutionStack().isEmpty()) {
                oneStep(programState).getHeap().setContent(conservativeGarbageCollector(programState.getSymTable().getValues(), programState.getHeap().getContent()));
                if (isLogging) {
                    repository.logProgramStateExec(programState);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            programState.getFileTable().getAllValues().stream().filter(fileData -> fileData.getReader() != null).forEach(item -> {
                try {
                    item.getReader().close();
                } catch (IOException e) {
                    System.out.println("Something went wrong! Cannot close" + item.getFileName());
                }
            });
        }
    }

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTable, Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e -> symTable.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String toString() {
        return "Controller:\n-" + repository.toString();
    }
}


