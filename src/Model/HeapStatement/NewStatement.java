package Model.HeapStatement;

import Model.Expression.IExpression;
import Model.GeneralStatement.IStatement;
import Model.ProgramState;
import Model.Utils.IHeap;

public class NewStatement implements IStatement {
    private String varName;
    private IExpression expression;

    public NewStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        Integer value = expression.eval(state.getSymTable(), state.getHeap());
        IHeap<Integer, Integer> heap = state.getHeap();
        int id = heap.add(value);
        state.getSymTable().setValue(varName, id);
        return state;
    }

    @Override
    public String toString() {
        return "New(" + this.varName + "," + this.expression.toString() + ")";
    }
}
