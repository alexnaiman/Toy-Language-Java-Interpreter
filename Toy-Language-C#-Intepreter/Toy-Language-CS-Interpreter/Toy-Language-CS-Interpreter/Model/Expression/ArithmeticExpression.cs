using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Toy_Language_CS_Interpreter.Model.Containers;
using Toy_Language_CS_Interpreter.Model.CustomException;

namespace Toy_Language_CS_Interpreter.Model.Expression
{
    class ArithmeticExpression : IExpression
    {
        private char op;
        private IExpression left, right;

        public ArithmeticExpression(char op, IExpression left, IExpression right)
        {
            this.op = op;
            this.left = left;
            this.right = right;
        }

        public int Evaluate(Containers.IDictionary<string, int> symbolTable)
        {
            int leftResult = left.Evaluate(symbolTable);
            int rightResult = right.Evaluate(symbolTable);
            switch (op)
            {
                case '+':
                    return leftResult + rightResult;
                case '-':
                    return leftResult - rightResult;
                case '*':
                    return leftResult * rightResult;
                case '/':
                    if (rightResult == 0)
                        throw new DivideByZeroException("Cannot divide by zero");
                    return leftResult / rightResult;
                default:
                    throw new InvalidSignException("Cannot find operator! Use one of ('+', '-', '*', '/'");
            }
        }
        public override string ToString()
        {
            return "" + left + op + right;
        }
    }
}
