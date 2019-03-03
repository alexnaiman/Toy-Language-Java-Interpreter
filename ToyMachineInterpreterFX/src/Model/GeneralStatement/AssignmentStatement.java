package Model.GeneralStatement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IDictionary;

public class AssignmentStatement implements IStatement {

    private String id;
    private IExpression expression;

    public AssignmentStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Integer> symTable = state.getSymTable();
        int value = expression.eval(symTable, state.getHeap());
        symTable.setValue(id, value);
        return null;
    }

    @Override
    public String toString() {
        return id + "=" + expression + ";";
    }
}
