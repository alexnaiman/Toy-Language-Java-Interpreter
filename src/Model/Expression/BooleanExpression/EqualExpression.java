package Model.Expression.BooleanExpression;

import Model.Expression.IExpression;
import Model.Utils.IDictionary;
import Model.Utils.IHeap;

public class EqualExpression implements IExpression {
    private IExpression left, right;

    public EqualExpression(IExpression left, IExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(IDictionary<String, Integer> dict, IHeap<Integer, Integer> heap) {
        if (left.eval(dict, heap) == right.eval(dict, heap))
            return 1;
        return 0;
    }

    @Override
    public String toString() {
        return "(" + left + "==" + right + ")";
    }
}
