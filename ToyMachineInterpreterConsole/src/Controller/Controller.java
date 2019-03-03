package Controller;

import Model.ProgramState;
import Model.GeneralStatement.IStatement;
import Model.Utils.IStack;
import Repository.IRepository;
import Exception.StatementExecutionError;
import Exception.ControllerException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private boolean isLogging = true;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
        executor = Executors.newFixedThreadPool(5);
    }

    private void oneStepForAll(List<ProgramState> statesList) {
        statesList.forEach(p -> repository.logProgramStateExec(p));
        List<Callable<ProgramState>> callList = statesList.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newList = null;
        try {
            newList = executor.invokeAll(callList)
                    .stream()
                    .map(future ->
                    {
                        try {
                            return future.get();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new ControllerException(e.getMessage());
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new ControllerException(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        statesList.addAll(newList);
        statesList.forEach(prg -> repository.logProgramStateExec(prg));

        repository.setProgramList(statesList);
    }

    public boolean isLogging() {
        return isLogging;
    }

    public void setLogging(boolean logging) {
        isLogging = logging;
    }

    public void allStep() {
        executor = Executors.newFixedThreadPool(2);
        List<ProgramState> prgList = removeCompletedPrograms(repository.getProgramList());
        try {


            while (prgList.size() > 0) {
                prgList.stream().forEach(programState -> programState.getHeap().setContent(conservativeGarbageCollector(programState.getSymTable().getValues(), programState.getHeap().getContent())));
                oneStepForAll(prgList);
                prgList = removeCompletedPrograms(repository.getProgramList());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            prgList.get(0).getFileTable().getAllValues().stream().filter(fileData -> fileData.getReader() != null).forEach(item -> {
                try {
                    item.getReader().close();
                } catch (IOException e) {
                    System.out.println("Something went wrong! Cannot close" + item.getFileName());
                }
            });
        }
        executor.shutdownNow();
        repository.setProgramList(prgList);


    }

    private Map<Integer, Integer> conservativeGarbageCollector(Collection<Integer> symTable, Map<Integer, Integer> heap) {
        return heap.entrySet().stream().filter(e -> symTable.contains(e.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<ProgramState> removeCompletedPrograms(List<ProgramState> list) {
        return list.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    public String toString() {
        return "Controller:\n-" + repository.toString();
    }
}


