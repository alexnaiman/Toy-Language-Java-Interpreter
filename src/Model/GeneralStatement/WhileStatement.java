package Model.GeneralStatement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.IHeap;
import Model.Utils.IStack;

public class WhileStatement implements IStatement {
    private IExpression expression;
    private IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        IDictionary<String, Integer> symTable = state.getSymTable();
        IHeap<Integer, Integer> heap = state.getHeap();

        int value = expression.eval(symTable, heap);

        if (value != 0) {
            stack.push(new WhileStatement(expression, statement));
            stack.push(statement);
        }
        return null;
    }

    @Override
    public String toString() {
        return "While(" + expression + "){" + statement + "}";
    }
}
