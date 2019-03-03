using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Expression;

namespace Toy_Language_CS_Interpreter.Model.Statement.GeneralStatement
{
    class PrintStatement : IStatement
    {
        private IExpression expression;

        public PrintStatement(IExpression expression)
        {
            this.expression = expression;
        }

        public ProgramState Execute(ProgramState state)
        {
            int result = expression.Evaluate(state.SymbolTable);
            state.Output.Add(result);
            return state;
        }

        public override string ToString()
        {
            return "Print(" + expression + ")";
        }
    }
}
