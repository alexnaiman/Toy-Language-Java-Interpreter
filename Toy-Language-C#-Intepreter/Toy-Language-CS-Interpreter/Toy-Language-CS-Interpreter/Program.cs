using System;
//using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model;
using Toy_Language_CS_Interpreter.Model.Containers;
using Toy_Language_CS_Interpreter.Model.Expression;
using Toy_Language_CS_Interpreter.Model.Statement;
using Toy_Language_CS_Interpreter.Model.Statement.FileStatement;
using Toy_Language_CS_Interpreter.Model.Statement.GeneralStatement;
using Toy_Language_CS_Interpreter.Repository;
using Toy_Language_CS_Interpreter.View;

namespace Toy_Language_CS_Interpreter
{
    class Program
    {
        static void Main(string[] args)
        {
            IStack<IStatement> executionStack = new CustomStack<IStatement>();
            IDictionary<string, int> symbolTable = new CustomDictionary<string, int>();
            IList<int> output = new CustomList<int>();
            IFileTable<int, FileData> filetable = new FileTable<int, FileData>();
            IStatement statement = new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(2)), new PrintStatement(new VariableExpression("v")));
            executionStack.Push(statement);
            ProgramState state = new ProgramState(symbolTable, executionStack, output, filetable);
            IRepository repository = new Repository.Repository(state);
            Controller.Controller controller = new Controller.Controller(repository);


            //2
            IStack<IStatement> executionStack2 = new CustomStack<IStatement>();
            IDictionary<string, int> symbolTable2 = new CustomDictionary<string, int>();
            IList<int> output2 = new CustomList<int>();
            IFileTable<int, FileData> filetable2 = new FileTable<int, FileData>();
            IStatement s4 = new CompoundStatement(
                                       new CompoundStatement(
                                               new CompoundStatement(
                                                       new OpenFileStatement("var_f", "test.txt"),
                                                       new ReadFileStatement(new VariableExpression("var_f"), "var_c")
                                               ),
                                               new CompoundStatement(
                                                       new PrintStatement(new VariableExpression("var_c")),
                                                       new IfStatement(
                                                               new VariableExpression("var_c"),
                                                               new PrintStatement(new ConstantExpression(0))
                                                              , new CompoundStatement(
                                                                       new ReadFileStatement(new VariableExpression("var_f"), "var_c"),
                                                                       new PrintStatement(new VariableExpression("var_c")))))),
                                       new CloseFileStatement(new VariableExpression("var_f"))
                               );
                executionStack2.Push(s4);
            ProgramState state2 = new ProgramState(symbolTable2, executionStack2, output2, filetable2);
            IRepository repository2 = new Repository.Repository(state2);
            Controller.Controller controller2 = new Controller.Controller(repository2);
            //3

            IStack<IStatement> executionStack3 = new CustomStack<IStatement>();
            IDictionary<string, int> symbolTable3 = new CustomDictionary<string, int>();
            IList<int> output3 = new CustomList<int>();
            IFileTable<int, FileData> filetable3 = new FileTable<int, FileData>();
            IStatement statement3 = new CompoundStatement(
                               new AssignmentStatement("a",
                                       new ArithmeticExpression('-',
                                               new ConstantExpression(2),
                                               new ConstantExpression(2))),
                               new CompoundStatement(
                                       new IfStatement(
                                               new VariableExpression("a"),
                                               new AssignmentStatement("v",
                                                       new ConstantExpression(3)),
                                               new AssignmentStatement("v",
                                                       new ConstantExpression(5))),
                                       new PrintStatement(
                                               new VariableExpression("v"))));
            executionStack3.Push(statement3);
            ProgramState state3 = new ProgramState(symbolTable3, executionStack3, output3, filetable3);
            IRepository repository3 = new Repository.Repository(state3);
            Controller.Controller controller3 = new Controller.Controller(repository3);



            TextMenu textMenu = new TextMenu();
            textMenu.AddCommand(new ExitCommand("0", "exit"));
            textMenu.AddCommand(new RunCommand("1", statement.ToString(), controller));
            textMenu.AddCommand(new RunCommand("2", s4.ToString(), controller2));
            textMenu.AddCommand(new RunCommand("3", statement3.ToString(), controller3));
            textMenu.Show();
        }
    }
}
