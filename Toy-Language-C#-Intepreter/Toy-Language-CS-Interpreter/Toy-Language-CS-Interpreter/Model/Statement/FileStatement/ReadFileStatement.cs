using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.CustomException;
using Toy_Language_CS_Interpreter.Model.Expression;
using Toy_Language_CS_Interpreter.Model.Statement.GeneralStatement;

namespace Toy_Language_CS_Interpreter.Model.Statement.FileStatement
{
    class ReadFileStatement : IStatement
    {
        private IExpression expression;
        private string variableName;

        public ReadFileStatement(IExpression expression, string variableName)
        {
            this.expression = expression;
            this.variableName = variableName;
        }

        public ProgramState Execute(ProgramState state)
        {
            int result = expression.Evaluate(state.SymbolTable);
            if (!state.FileTable.Contains(result))
                throw new FileException("Cannot find file");
            FileData data = state.FileTable.Get(result);
            StreamReader reader = data.Reader;
            string line = reader.ReadLine();
            IExpression value = line == null ? new ConstantExpression(0) : new ConstantExpression(int.Parse(line));
            IStatement statement = new AssignmentStatement(variableName, value);
            statement.Execute(state);
            return state;
        }
        public override string ToString()
        {
            return "readFile(" + expression + ", " + variableName + ")";
        }
    }
}
