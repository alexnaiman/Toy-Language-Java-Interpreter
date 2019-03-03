package Model.GeneralStatement;

import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state);
}