package Repository;

import Model.ProgramState;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Exception.FileException;

public class Repository implements IRepository {

    private List<ProgramState> states = new ArrayList<>();
    private int current = 0;
    private String logFilePath;

    public Repository(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    @Override
    public void add(ProgramState state) {
        states.add(state);
    }

    @Override
    public void logProgramStateExec(ProgramState state) {
        LocalDateTime now = LocalDateTime.now();
        String format1 = now.format(DateTimeFormatter.ofPattern("MMM-d-hh-mm-ss"));
        String filePath = "./resources/logs/log_" + format1 + ".txt";
        String finalPath;
        if (logFilePath.equals("")) finalPath = filePath;
        else finalPath = "./resources/logs/" + logFilePath;
        try (PrintWriter printer = new PrintWriter(new BufferedWriter(new FileWriter(finalPath, true)))) {
            printer.println(String.valueOf(state) + "\n");
            printer.println("################");
        } catch (FileNotFoundException e) {
            throw new FileException("File not found in PrintWriter");
        } catch (IOException e) {
            throw new FileException("IO exception at PrintWriter");
        }
    }

    @Override
    public List<ProgramState> getProgramList() {
        return states;
    }

    @Override
    public void setProgramList(List<ProgramState> list) {
        this.states = list;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (ProgramState p : states) {
            builder.append(p).append("\n");
        }
        return builder.toString();
    }

}
