using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Containers;

namespace Toy_Language_CS_Interpreter.Model.Expression
{
    class VariableExpression : IExpression
    {
        private string id;

        public VariableExpression(string id)
        {
            this.id = id;
        }

        public int Evaluate(Containers.IDictionary<string, int> symbolTable)
        {
            return symbolTable.Get(id);
        }

        public override string ToString()
        {
            return id;
        }
    }
}
