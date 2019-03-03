package Model.FileStatement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.GeneralStatement.IStatement;
import Model.Utils.IDictionary;
import Exception.FileException;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFile implements IStatement {
    private IExpression varExpression;

    public CloseRFile(IExpression varExpression) {
        this.varExpression = varExpression;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IFileTable<Integer, FileData> fileTable = state.getFileTable();
        IDictionary<String, Integer> symTable = state.getSymTable();
        int id = varExpression.eval(symTable, state.getHeap());

        if (!fileTable.contains(id)) {
            throw new FileException("File cannot be found!");
        }
        BufferedReader reader = fileTable.get(id).getReader();
        try {
            reader.close();
        } catch (IOException e) {
            throw new FileException("Cannot close the file!");
        }

        fileTable.delete(id);

        return null;
    }

    @Override
    public String toString() {
        return "closeRFile(" + varExpression + ")";
    }
}
