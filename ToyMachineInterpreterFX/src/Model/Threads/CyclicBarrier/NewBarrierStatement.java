package Model.Threads.CyclicBarrier;

import Model.Expression.IExpression;
import Model.GeneralStatement.IStatement;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.IHeap;

import java.beans.Expression;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewBarrierStatement implements IStatement {
    private String variable;
    private IExpression expression;
    private static Lock lock = new ReentrantLock();

    public NewBarrierStatement(String variable, IExpression expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        lock.lock();
        IDictionary<String, Integer> symbolTable = state.getSymTable();
        IHeap<Integer, Integer> heap = state.getHeap();
        IBarrierTable<Integer, BarrierData> barrierTable = state.getBarrierTable();
        Integer value = expression.eval(symbolTable, heap);

        Integer location = BarrierAdressGenerator.generate();
        barrierTable.add(location,new BarrierData(value));
        symbolTable.setValue(variable, location);
        lock.unlock();
        return null;
    }

    @Override
    public String toString() {
        return "newBarrier( " + variable + ", " + expression.toString() + ")";
    }
}
