package Model.Threads.CyclicBarrier;

import Model.FileStatement.FileData;
import Model.GeneralStatement.IStatement;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.IStack;

import java.time.chrono.IsoChronology;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AwaitBarrierStatement implements IStatement {
    private String variable;
    private static Lock lock = new ReentrantLock();

    public AwaitBarrierStatement(String variable) {
        this.variable = variable;
    }


    @Override
    public ProgramState execute(ProgramState state) {
        lock.lock();
        IStack<IStatement> exeutionStack = state.getExecutionStack();
        IBarrierTable<Integer, BarrierData> barrierTable = state.getBarrierTable();
        IDictionary<String, Integer> symbolTable = state.getSymTable();
        Integer foundIndex = symbolTable.getValue(variable);

        BarrierData barrierData = barrierTable.get(foundIndex);
        Integer capacity = barrierData.getKey();
        Integer size = barrierData.getList().size();

        if (capacity > size)
        {
            if(barrierData.getList().contains(state.getId()))
                exeutionStack.push(this);
            else{
                barrierData.getList().add(state.getId());
                state.getBarrierTable().add(foundIndex, new BarrierData(capacity,barrierData.getList()));
            }
        }
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "await( " + variable + " )";
    }
}
