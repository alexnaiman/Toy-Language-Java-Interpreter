package Model.FileStatement;

import Model.Expression.IExpression;
import Model.ProgramState;
import Model.GeneralStatement.IStatement;
import Model.Utils.IDictionary;
import Exception.FileException;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFile implements IStatement {
    private IExpression varExpression;
    private String varName;

    public ReadFile(IExpression varExpression, String varName) {
        this.varExpression = varExpression;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IFileTable<Integer, FileData> fileTable = state.getFileTable();
        IDictionary<String, Integer> symTable = state.getSymTable();
        int id = varExpression.eval(symTable, state.getHeap());

        if (!fileTable.contains(id)) {
            throw new FileException("The id cannot be found");
        }
        BufferedReader reader = fileTable.get(id).getReader();
        String line = null;

        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new FileException("Cannot read line");
        }

        int value = 0;
        try {
            if (line != null) {
                value = Integer.parseInt(line);
            } else {
                throw new FileException("Cannot read number! Wrong format");
            }
        } catch (NumberFormatException e) {
            throw new FileException("Cannot read number! Wrong format");
        }
        symTable.setValue(varName, value);
        return state;
    }

    @Override
    public String toString() {
        return "readFile(" + varExpression + "," + varName + ")";
    }
}
