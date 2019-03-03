using System;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Containers;

namespace Toy_Language_CS_Interpreter.Model.Expression
{
    interface IExpression
    {
        int Evaluate(IDictionary<string, int> symbolTable);
    }
}
