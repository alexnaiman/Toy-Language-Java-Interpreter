package Model.HeapStatement;

import Model.Expression.IExpression;
import Model.GeneralStatement.IStatement;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.IHeap;
import Exception.HeapException;

public class WriteHeapStatement implements IStatement {
    private String name;
    private IExpression expression;

    public WriteHeapStatement(String name, IExpression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap<Integer, Integer> heap = state.getHeap();
        int id = symTable.getValue(name);
        int value = expression.eval(symTable, heap);
        if (!heap.exists(id))
            throw new HeapException("Invalid heap address");
        heap.replace(id, value);
        return null;
    }

    @Override
    public String toString() {
        return "WriteHeap(" + this.name + "," + this.expression.toString() + ")";
    }
}
