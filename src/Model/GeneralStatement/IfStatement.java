package Model.GeneralStatement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.IStack;

public class IfStatement implements IStatement {
    private IExpression condition;
    private IStatement thenStatement;
    private IStatement elseStatement;

    public IfStatement(IExpression condition, IStatement thenStatement, IStatement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IDictionary<String, Integer> symTable = state.getSymTable();
        IStack<IStatement> stack = state.getExecutionStack();
        int value = condition.eval(symTable, state.getHeap());

        if (value != 0)
            stack.push(thenStatement);
        else
            stack.push(elseStatement);

        return state;
    }

    @Override
    public String toString() {
        return "if(" + condition + ") then(" + thenStatement + ") else(" + elseStatement + ");";
    }
}
