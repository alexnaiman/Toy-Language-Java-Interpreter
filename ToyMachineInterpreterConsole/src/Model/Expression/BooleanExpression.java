package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.IHeap;
import Exception.InvalidSign;

public class BooleanExpression implements IExpression {
    private IExpression left, right;
    private String op;

    public BooleanExpression(IExpression left, IExpression right, String op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public int eval(IDictionary<String, Integer> dict, IHeap<Integer, Integer> heap) {
        int evaluatedLeft = left.eval(dict, heap);
        int evaluatedRight = right.eval(dict, heap);
        switch (op) {
            case "==": {
                if (evaluatedLeft == evaluatedRight)
                    return 1;
                return 0;
            }
            case "!=": {
                if (evaluatedLeft != evaluatedRight)
                    return 1;
                return 0;
            }
            case "<": {
                if (evaluatedLeft < evaluatedRight)
                    return 1;
                return 0;
            }
            case "<=": {
                if (evaluatedLeft <= evaluatedRight)
                    return 1;
                return 0;
            }
            case ">=": {
                if (evaluatedLeft >= evaluatedRight)
                    return 1;
                return 0;
            }
            case ">": {
                if (evaluatedLeft > evaluatedRight)
                    return 1;
                return 0;
            }
        }
        throw new InvalidSign("Cannot find operator! Use one of ('+', '-', '*', '/'");

    }

    @Override
    public String toString() {
        return "(" + left + op + right + ")";
    }
}
