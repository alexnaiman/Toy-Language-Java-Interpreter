using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Toy_Language_CS_Interpreter.Model.Statement.GeneralStatement
{
    class CompoundStatement : IStatement
    {
        private IStatement first, second;

        public CompoundStatement(IStatement first, IStatement second)
        {
            this.first = first;
            this.second = second;
        }

        public ProgramState Execute(ProgramState state)
        {
            state.ExecutionStack.Push(second);
            state.ExecutionStack.Push(first);
            return state;
        }

        public override string ToString()
        {
            return "[" + first + ";\n" + second + "]\r\n;";
        }
    }
}

// TODO 
// close open read if print 













