package Model.FileStatement;

import java.io.BufferedReader;

public class FileData {
    private String fileName;
    private BufferedReader reader;

    public FileData(String fileName, BufferedReader reader) {
        this.fileName = fileName;
        this.reader = reader;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    @Override
    public String toString() {
        return fileName;
    }
}
