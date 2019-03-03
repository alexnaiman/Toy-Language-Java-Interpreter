package Model.FileStatement;

import Model.ProgramState;
import Model.GeneralStatement.IStatement;
import Model.Utils.IDictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Exception.FileException;

public class OpenRFile implements IStatement {
    private String fileName;
    private String varName;

    public OpenRFile(String fileName, String varName) {
        this.fileName = fileName;
        this.varName = varName;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        IFileTable<Integer, FileData> fileTable = state.getFileTable();
        IDictionary<String, Integer> symTable = state.getSymTable();
        BufferedReader reader;

        for (FileData fileData : fileTable.getAllValues()) {
            if (fileData.getFileName().equals(fileName)) {
                throw new FileException("File is already opened");
            }
        }
        try {
            reader = new BufferedReader(new FileReader(fileName));
        } catch (IOException e) {
            throw new FileException("File not found");
        }
        int id = NumberGenerator.getId();
        fileTable.add(id, new FileData(fileName, reader));
        symTable.setValue(varName, id);
        return null;
    }

    @Override
    public String toString() {
        return "openRFile(" + varName + "," + fileName + ")";
    }
}
