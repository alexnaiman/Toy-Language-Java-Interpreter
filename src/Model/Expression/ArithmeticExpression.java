package Model.Expression;

import Model.Utils.IDictionary;
import Exception.DivisionByZero;
import Exception.InvalidSign;
import Model.Utils.IHeap;


public class ArithmeticExpression implements IExpression {
    private IExpression left, right;
    private char op;


    public ArithmeticExpression(char op, IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public int eval(IDictionary<String, Integer> dict, IHeap<Integer, Integer> heap) {
        int evaluatedLeft = left.eval(dict, heap);
        int evaluatedRight = right.eval(dict, heap);
        switch (op) {
            case '+':
                return evaluatedLeft + evaluatedRight;
            case '-':
                return evaluatedLeft - evaluatedRight;
            case '*':
                return evaluatedLeft * evaluatedRight;
            case '/':
                if (evaluatedRight == 0) {
                    throw new DivisionByZero("Cannot divide by zero");
                }
                return evaluatedLeft - evaluatedRight;
        }

        throw new InvalidSign("Cannot find operator! Use one of ('+', '-', '*', '/'");
    }

    @Override
    public String toString() {
        return "" + left + op + right;
    }
}
