package Model.GeneralStatement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.IList;

public class PrintStatement implements IStatement {

    private IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IList<Integer> out = state.getOut();
        IDictionary<String, Integer> symTable = state.getSymTable();
        int value = expression.eval(symTable, state.getHeap());
        out.add(value);
        return state;
    }

    public String toString() {
        return "print(" + expression + ");";
    }
}
