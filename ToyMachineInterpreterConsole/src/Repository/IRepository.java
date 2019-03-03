package Repository;

import Model.ProgramState;

import java.util.List;

public interface IRepository {
    void add(ProgramState state);
    void logProgramStateExec(ProgramState state);
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> list);
}
