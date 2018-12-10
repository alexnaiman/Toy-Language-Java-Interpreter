package Model.GeneralStatement;

import Model.ProgramState;
import Model.Utils.IStack;

public class CompoundStatement implements IStatement {

    private IStatement first, second;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> stack = state.getExecutionStack();
        stack.push(second);
        stack.push(first);

        return null;
    }

    @Override
    public String toString() {
        return "[" + first + ";\n\t" + second + "];";
    }
}
