using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Expression;

namespace Toy_Language_CS_Interpreter.Model.Statement.GeneralStatement
{
    class AssignmentStatement : IStatement
    {
        private string variableName;
        private IExpression expression;

        public AssignmentStatement(string variableName, IExpression expression)
        {
            this.variableName = variableName;
            this.expression = expression;
        }

        public ProgramState Execute(ProgramState state)
        {
            int result = expression.Evaluate(state.SymbolTable);
            state.SymbolTable.Put(variableName, result);
            return state;
        }

        public override string ToString()
        {
            return variableName + "=" + expression + ";";
        }

    }
}
