package Model;

import Model.FileStatement.FileData;
import Model.FileStatement.IFileTable;
import Model.GeneralStatement.IStatement;
import Model.Threads.CyclicBarrier.Barrier;
import Model.Threads.CyclicBarrier.BarrierData;
import Model.Threads.CyclicBarrier.IBarrierTable;
import Model.Utils.IDictionary;
import Model.Utils.IHeap;
import Model.Utils.IList;
import Model.Utils.IStack;
import Exception.StatementExecutionError;

public class ProgramState {
    private IStatement originalProgram;
    private IStack<IStatement> executionStack;
    private IDictionary<String, Integer> symTable;
    private IList<Integer> out;
    private IFileTable<Integer, FileData> fileTable;
    private IHeap<Integer, Integer> heap;
    private IBarrierTable<Integer, BarrierData> barrierTable;
    private int id;

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Integer> symTable, IList<Integer> out, IStatement originalProgram, IHeap<Integer, Integer> heap) {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.heap = heap;
    }

    public ProgramState(IStack<IStatement> executionStack, IDictionary<String, Integer> symTable, IList<Integer> out) {
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.out = out;
    }

    public ProgramState(IStatement originalProgram, IStack<IStatement> executionStack, IDictionary<String, Integer> symTable, IList<Integer> out, IFileTable<Integer, FileData> fileTable, IHeap<Integer, Integer> heap) {
        this.originalProgram = originalProgram;
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        executionStack.push(originalProgram);
    }

    public ProgramState(IStatement originalProgram, IStack<IStatement> executionStack, IDictionary<String, Integer> symTable, IList<Integer> out, IFileTable<Integer, FileData> fileTable, IHeap<Integer, Integer> heap, int id) {
        this.originalProgram = originalProgram;
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = id;
    }
    public ProgramState(IStatement originalProgram, IStack<IStatement> executionStack, IDictionary<String, Integer> symTable, IList<Integer> out, IFileTable<Integer, FileData> fileTable, IHeap<Integer, Integer> heap, int id, IBarrierTable<Integer, BarrierData> barrierTable) {
        this.originalProgram = originalProgram;
        this.executionStack = executionStack;
        this.symTable = symTable;
        this.out = out;
        this.fileTable = fileTable;
        this.heap = heap;
        this.id = id;
        this.barrierTable = barrierTable;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("StateId:").append(id).append('\n');
        builder.append("ExecutionStack:");

        for (IStatement statement : executionStack.getAll()) {
            builder.append("\n\t").append(statement.toString());
        }
        builder.append("\nSymTable:");
        for (String key : symTable.getAll())
            builder.append("\n\t").append(key).append("::=").append(symTable.getValue(key));
        builder.append("\n").append(heap);
        builder.append("\nFileTable:");
        builder.append("\t").append(fileTable.toString());
        builder.append("\nBarrierTable:");
        builder.append("\t").append(barrierTable.toString());
        builder.append("\nOut:");
        for (Integer i : out.getAll())
            builder.append("\n\t").append(i);
        return builder.toString();
    }

    public IStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(IStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public IDictionary<String, Integer> getSymTable() {
        return symTable;
    }

    public void setSymTable(IDictionary<String, Integer> symTable) {
        this.symTable = symTable;
    }

    public IList<Integer> getOut() {
        return out;
    }

    public void setOut(IList<Integer> out) {
        this.out = out;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public IFileTable<Integer, FileData> getFileTable() {
        return fileTable;
    }

    public void setFileTable(IFileTable<Integer, FileData> fileTable) {
        this.fileTable = fileTable;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    public IHeap<Integer, Integer> getHeap() {
        return heap;
    }

    public void setHeap(IHeap<Integer, Integer> heap) {
        this.heap = heap;
    }

    public ProgramState oneStep() {
        if (executionStack.isEmpty()) throw new StatementExecutionError("Execution Stack is empty");
        IStatement currentStatement = executionStack.pop();
        return currentStatement.execute(this);
    }

    public boolean isNotCompleted(){
        return !executionStack.isEmpty();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public IBarrierTable<Integer, BarrierData> getBarrierTable() {
        return barrierTable;
    }

    public void setBarrierTable(IBarrierTable<Integer, BarrierData> barrierTable) {
        this.barrierTable = barrierTable;
    }
}
