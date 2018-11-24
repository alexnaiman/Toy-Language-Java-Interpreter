package View;

import Controller.Controller;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ConstantExpression;
import Model.Expression.VariableExpression;
import Model.FileStatement.*;
import Model.ProgramState;
import Model.GeneralStatement.*;
import Model.Utils.*;
import Repository.IRepository;
import Repository.Repository;

import java.util.Scanner;

public class View {
    private void printMenu() {
        System.out.println("1. v=2;Print(v)");
        System.out.println("2. a=2+3*5;b=a+1;Print(b)");
        System.out.println("3. a=2-2;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("0. Exit");
        System.out.println("Choose one from above");
    }

    public void run() {
        boolean stop = false;
        while (!stop) {
            System.out.println("Log file:");
            Scanner in = new Scanner(System.in);
            String logFile = in.nextLine();
            printMenu();
            int option = 0;
            if (in.hasNextInt()) {
                option = in.nextInt();
            } else {
                System.out.println("Sorry, couldn't understand you!");
                continue;
            }
            switch (option) {
                case 0:
                    stop = true;
                    break;
                case 1:
                    try {

                        IStack<IStatement> executionStack = new CustomStack<>();
                        IDictionary<String, Integer> symTable = new CustomDictionary<String, Integer>();
                        IList<Integer> out = new CustomList<Integer>();
//                        IStatement ex1 = new CompoundStatement(
//                                new AssignmentStatement("v", new ConstantExpression(2)),
//                                new PrintStatement(new VariableExpression("v")));
                        IStatement ifStmnt = new IfStatement(new VariableExpression("var_c"), new CompoundStatement(new ReadFile(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new ConstantExpression(0)));
                        IStatement opStmnt = new OpenRFile("test.in", "var_f");
                        IStatement readStmnt = new ReadFile(new VariableExpression("var_f"), "var_c");
                        IStatement comp1 = new CompoundStatement(readStmnt, new PrintStatement(new VariableExpression("var_c")));
                        IStatement closeFile = new CloseRFile(new VariableExpression("var_f"));
                        IStatement ex1 = new CompoundStatement(opStmnt, new CompoundStatement(readStmnt, new CompoundStatement(new PrintStatement(new VariableExpression("var_c")), new CompoundStatement(ifStmnt, closeFile))));
                        executionStack.push(ex1);
                        IFileTable<Integer, FileData> fileTable = new FileTable<>();
                        ProgramState crtPrgState = new ProgramState(ex1, executionStack, symTable, out, fileTable, new CustomHeap<>(new IntHeapKeyGenerator()));
                        IRepository repository = new Repository(logFile);
                        repository.add(crtPrgState);
                        Controller controller = new Controller(repository);
                        controller.setLogging(true);
                        controller.allStep();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        IStack<IStatement> executionStack = new CustomStack<>();
                        IDictionary<String, Integer> symTable = new CustomDictionary<String, Integer>();
                        IList<Integer> out = new CustomList<Integer>();

                        IStatement ex2 = new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+', new ConstantExpression(2), new
                                ArithmeticExpression('*', new ConstantExpression(3), new ConstantExpression(5)))),
                                new CompoundStatement(new AssignmentStatement("b", new ArithmeticExpression('+', new VariableExpression("a"), new
                                        ConstantExpression(1))), new PrintStatement(new VariableExpression("b"))));

                        executionStack.push(ex2);
                        ProgramState crtPrgState = new ProgramState(executionStack, symTable, out);
                        IRepository repository = new Repository(logFile);
                        repository.add(crtPrgState);
                        Controller controller = new Controller(repository);
                        controller.setLogging(true);
                        controller.allStep();

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    IStack<IStatement> executionStack = new CustomStack<>();
                    IDictionary<String, Integer> symTable = new CustomDictionary<String, Integer>();
                    IList<Integer> out = new CustomList<Integer>();

                    IStatement ex3 = new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('-', new ConstantExpression(2), new
                            ConstantExpression(2))),
                            new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ConstantExpression(2)), new
                                    AssignmentStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));

                    executionStack.push(ex3);
                    ProgramState crtPrgState = new ProgramState(executionStack, symTable, out);
                    IRepository repository = new Repository(logFile);
                    repository.add(crtPrgState);
                    Controller controller = new Controller(repository);
                    controller.setLogging(true);
                    controller.allStep();
                    break;
            }

        }
    }
}
