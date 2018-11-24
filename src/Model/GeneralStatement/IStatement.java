package Model.GeneralStatement;

import Model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state);
}
// TODO
// add clone method for IStatement
// Recursive
//  catch in controller exceptions