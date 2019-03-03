package Model.GeneralStatement;

import Model.FileStatement.FileData;
import Model.FileStatement.IFileTable;
import Model.ProgramState;
import Model.Utils.*;

import java.util.Map;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IStack<IStatement> newExeStack = new CustomStack<>();
        //IList<Integer> out2 = new CustomList<>();
        IHeap<Integer, Integer> heap2 = state.getHeap();
        IFileTable<Integer, FileData> fileTable2 = state.getFileTable();
        IDictionary<String, Integer> symTable2 = state.getSymTable().copy();
        int id = IntForkGenerator.generate();
        newExeStack.push(statement);
        return new ProgramState(null, newExeStack, symTable2, state.getOut(), fileTable2, heap2, id);
    }

    @Override
    public String toString() {
        return "fork(" + statement + ")";
    }
}
