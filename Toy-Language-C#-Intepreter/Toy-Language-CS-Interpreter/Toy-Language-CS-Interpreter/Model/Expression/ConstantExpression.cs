using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Containers;

namespace Toy_Language_CS_Interpreter.Model.Expression
{
    class ConstantExpression:IExpression
    {
        private int value;

        public ConstantExpression(int value)
        {
            this.value = value;
        }

        public int Evaluate(Containers.IDictionary<string, int> symbolTable)
        {
            return value;
        }

        public override string ToString()
        {
            return "" + value;
        }
    }
}
