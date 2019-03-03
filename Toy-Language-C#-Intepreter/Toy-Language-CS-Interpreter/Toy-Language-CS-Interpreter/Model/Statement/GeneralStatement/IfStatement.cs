using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Expression;

namespace Toy_Language_CS_Interpreter.Model.Statement.GeneralStatement
{
    class IfStatement : IStatement
    {
        private IExpression expression;
        private IStatement thenStatement, elseStatement;

        public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement)
        {
            this.expression = expression;
            this.thenStatement = thenStatement;
            this.elseStatement = elseStatement;
        }

        public ProgramState Execute(ProgramState state)
        {
            int result = expression.Evaluate(state.SymbolTable);
            if (result == 0)
                state.ExecutionStack.Push(elseStatement);
            else
                state.ExecutionStack.Push(thenStatement);
            return state;
        }

        public override string ToString()
        {
            return "if(" + expression + ") then " + thenStatement + " else " + elseStatement;
        }
    }
}
