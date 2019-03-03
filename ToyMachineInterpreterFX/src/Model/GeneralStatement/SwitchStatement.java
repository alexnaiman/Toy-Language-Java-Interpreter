package Model.GeneralStatement;

import Model.Expression.BooleanExpression;
import Model.Expression.IExpression;
import Model.ProgramState;
import Model.Utils.IDictionary;
import Model.Utils.IHeap;
import Model.Utils.IStack;

public class SwitchStatement implements IStatement {
    private IExpression condition;
    private IExpression case1;
    private IStatement statement1;
    private IExpression case2;
    private IStatement statement2;
    private IStatement defaultStatement;

    public SwitchStatement(IExpression condition, IExpression case1, IStatement statement1, IExpression case2, IStatement statement2, IStatement defaultStatement) {
        this.condition = condition;
        this.case1 = case1;
        this.statement1 = statement1;
        this.case2 = case2;
        this.statement2 = statement2;
        this.defaultStatement = defaultStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> executionStack = state.getExecutionStack();
        IDictionary<String, Integer> symbolTable = state.getSymTable();
        IHeap<Integer,Integer> heap = state.getHeap();

        IStatement newStatement = new IfStatement(new BooleanExpression(condition, case1, "=="),statement1,new IfStatement(new BooleanExpression(condition, case2, "=="), statement2, defaultStatement));

        executionStack.push(newStatement);
        return null;
    }

    @Override
    public String toString() {
        return "switch( " +
                condition.toString() +
                ") \n{case:( " +
                case1.toString() + " ) " +
                statement1.toString() + ")\n(case:( " +
                case2.toString() + " ) " +
                statement2.toString() + ")\n(default: " +
                defaultStatement.toString() + " )}";

    }
}
