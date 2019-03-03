using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.CustomException;
using Toy_Language_CS_Interpreter.Model.Expression;

namespace Toy_Language_CS_Interpreter.Model.Statement.FileStatement
{
    class CloseFileStatement : IStatement
    {
        private IExpression expression;

        public CloseFileStatement(IExpression expression)
        {
            this.expression = expression;
        }

        public ProgramState Execute(ProgramState state)
        {
            int result = expression.Evaluate(state.SymbolTable);
            if (!state.FileTable.Contains(result))
                throw new FileException("Cannot find file!");
            FileData data = state.FileTable.Get(result);
            StreamReader reader = data.Reader;
            reader.Close();
            state.FileTable.Delete(result);
            return state;
        }
        public override string ToString()
        {
            return "closeFile(" + expression + ")";
        }
    }
}
