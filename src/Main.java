import Controller.Controller;
import Model.Expression.*;
import Model.FileStatement.*;
import Model.GeneralStatement.*;
import Model.HeapStatement.NewStatement;
import Model.HeapStatement.WriteHeapStatement;
import Model.ProgramState;
import Model.Utils.*;
import Repository.IRepository;
import Repository.Repository;
import View.TextMenu;
import View.RunCommand;
import View.ExitCommand;


public class Main {

    public static void main(String[] args) {
        IStack<IStatement> executionStack = new CustomStack<>();
        IDictionary<String, Integer> symTable = new CustomDictionary<>();
        IList<Integer> out = new CustomList<>();
        IFileTable<Integer, FileData> fileTable = new FileTable<>();
        IStatement ifStmnt = new IfStatement(new VariableExpression("var_c"), new CompoundStatement(new ReadFile(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0)));
        IStatement opStmnt = new OpenRFile("test.in", "var_f");
        IStatement readStmnt = new ReadFile(new VariableExpression("var_f"), "var_c");
        IStatement closeFile = new CloseRFile(new VariableExpression("var_f"));
        IStatement ex1 = new CompoundStatement(opStmnt, new CompoundStatement(readStmnt, new CompoundStatement(new PrintStatement(new VariableExpression("var_c")), new CompoundStatement(ifStmnt, closeFile))));
        // executionStack.push(ex1);
        ProgramState prg1 = new ProgramState(ex1, executionStack, symTable, out, fileTable, new CustomHeap<>(new IntHeapKeyGenerator()));
        IRepository repo1 = new Repository("");
        repo1.add(prg1);
        Controller ctr1 = new Controller(repo1);
        IStatement ex2 = new CompoundStatement(new OpenRFile("test.in", "var_f"),
                new CompoundStatement(new ReadFile(new ArithmeticExpression('+', new VariableExpression("var_f"),
                        new ConstantExpression(2)), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")), new CompoundStatement(new IfStatement(new VariableExpression("var_c"), new CompoundStatement(new ReadFile(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new VariableExpression("var_c"))), new CloseRFile(new VariableExpression("var_f"))))));
        IStack<IStatement> executionStack2 = new CustomStack<>();
        IDictionary<String, Integer> symTable2 = new CustomDictionary<>();
        IList<Integer> out2 = new CustomList<>();
        IFileTable<Integer, FileData> fileTable2 = new FileTable<>();
        // executionStack2.push(ex2);
        ProgramState prg2 = new ProgramState(ex2, executionStack2, symTable2, out2, fileTable2, new CustomHeap<>(new IntHeapKeyGenerator()));
        IRepository repo3 = new Repository("");
        repo3.add(prg2);
        Controller ctr3 = new Controller(repo3);

        IStack<IStatement> ExeStack5 = new CustomStack<>();
        IDictionary<String, Integer> SymTable5 = new CustomDictionary<>();
        IList<Integer> Output5 = new CustomList<>();
        IFileTable<Integer, FileData> fileTable5 = new FileTable<>();
        IHeap<Integer, Integer> heap5 = new CustomHeap<>(new IntHeapKeyGenerator());
        IStatement ex5 = new CompoundStatement(
                new AssignmentStatement("v", new ConstantExpression(10)),
                new CompoundStatement(
                        new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(
                                new NewStatement("a", new ConstantExpression(22)),
                                new CompoundStatement(
                                        new PrintStatement(
                                                new ArithmeticExpression('+', new ConstantExpression(100), new ReadHeapExpression("v"))),
                                        new PrintStatement(
                                                new ArithmeticExpression('+', new ConstantExpression(100), new ReadHeapExpression("a"))
                                        )
                                )
                        )
                )
        );
        ProgramState prg5 = new ProgramState(ex5, ExeStack5, SymTable5, Output5, fileTable5, heap5);
        Repository repo5 = new Repository("");
        repo5.add(prg5);
        Controller ctrl5 = new Controller(repo5);

        IStack<IStatement> ExeStack6 = new CustomStack<>();
        IDictionary<String, Integer> SymTable6 = new CustomDictionary<>();
        IList<Integer> Output6 = new CustomList<>();
        IFileTable<Integer, FileData> fileTable6 = new FileTable<>();
        IHeap<Integer, Integer> heap6 = new CustomHeap<>(new IntHeapKeyGenerator());
        IStatement ex6 = new CompoundStatement(
                new AssignmentStatement("v", new ConstantExpression(10)),
                new CompoundStatement(
                        new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(
                                new NewStatement("a", new ConstantExpression(22)),
                                new CompoundStatement(
                                        new WriteHeapStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new ReadHeapExpression("a")))
                                )
                        )
                )
        );
        ProgramState prg6 = new ProgramState(ex6, ExeStack6, SymTable6, Output6, fileTable6, heap6);
        Repository repo6 = new Repository("");
        repo6.add(prg6);
        Controller ctrl6 = new Controller(repo6);


        IStack<IStatement> ExeStack7 = new CustomStack<>();
        IDictionary<String, Integer> SymTable7 = new CustomDictionary<>();
        IList<Integer> Output7 = new CustomList<>();
        IFileTable<Integer, FileData> fileTable7 = new FileTable<>();
        IHeap<Integer, Integer> heap7 = new CustomHeap<>(new IntHeapKeyGenerator());
        IStatement ex7 = new CompoundStatement(
                new AssignmentStatement("v", new ConstantExpression(10)),
                new CompoundStatement(
                        new NewStatement("v", new ConstantExpression(20)),
                        new CompoundStatement(
                                new NewStatement("a", new ConstantExpression(22)),
                                new CompoundStatement(
                                        new WriteHeapStatement("a", new ConstantExpression(30)),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("a")),
                                                new CompoundStatement(
                                                        new PrintStatement(new ReadHeapExpression("a")),
                                                        new AssignmentStatement("a",
                                                                new ConstantExpression(0))
                                                )
                                        )
                                )
                        )
                )
        );
        ProgramState prg7 = new ProgramState(ex7, ExeStack7, SymTable7, Output7, fileTable7, heap7);
        Repository repo7 = new Repository("");
        repo7.add(prg7);
        Controller ctrl7 = new Controller(repo7);

        IStack<IStatement> ExeStack8 = new CustomStack<>();
        IDictionary<String, Integer> SymTable8 = new CustomDictionary<>();
        IList<Integer> Output8 = new CustomList<>();
        IFileTable<Integer, FileData> fileTable8 = new FileTable<>();
        IHeap<Integer, Integer> heap8 = new CustomHeap<>(new IntHeapKeyGenerator());

        IStatement ex8 = new CompoundStatement(new AssignmentStatement("a", new ConstantExpression(3)), new WhileStatement(new BooleanExpression(new VariableExpression("a"), new ConstantExpression(0), ">"), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new AssignmentStatement("a", new ArithmeticExpression('-', new VariableExpression("a"), new ConstantExpression(1))))));
        ProgramState prg8 = new ProgramState(ex8, ExeStack8, SymTable8, Output8, fileTable8, heap8);
        Repository repo8 = new Repository("");
        repo8.add(prg8);
        Controller ctrl8 = new Controller(repo8);

        IStack<IStatement> ExeStack9 = new CustomStack<>();
        IDictionary<String, Integer> SymTable9 = new CustomDictionary<>();
        IList<Integer> Output9 = new CustomList<>();
        IFileTable<Integer, FileData> fileTable9 = new FileTable<>();
        IHeap<Integer, Integer> heap9 = new CustomHeap<>(new IntHeapKeyGenerator());

        IStatement ex9 = new CompoundStatement(new AssignmentStatement("a", new ConstantExpression(-2)), new WhileStatement(new BooleanExpression(new VariableExpression("a"), new ConstantExpression(0), "<"), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new AssignmentStatement("a", new ArithmeticExpression('+', new VariableExpression("a"), new ConstantExpression(1))))));
        ProgramState prg9 = new ProgramState(ex9, ExeStack9, SymTable9, Output9, fileTable9, heap9);
        Repository repo9 = new Repository("");
        repo9.add(prg9);
        Controller ctrl9 = new Controller(repo9);


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunCommand("1", ex1.toString(), ctr1));
        menu.addCommand(new RunCommand("2", ex2.toString(), ctr3));
        menu.addCommand(new RunCommand("3", ex5.toString(), ctrl5));
        menu.addCommand(new RunCommand("4", ex6.toString(), ctrl6));
        menu.addCommand(new RunCommand("5", ex7.toString(), ctrl7));
        menu.addCommand(new RunCommand("6", ex8.toString(), ctrl8));
        menu.addCommand(new RunCommand("7", ex9.toString(), ctrl9));
        menu.show();

    }
}
