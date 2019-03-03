package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.IHeap;

public class ConstantExpression implements IExpression {
    private int value;

    public ConstantExpression(int value) {
        this.value = value;
    }

    @Override
    public int eval(IDictionary<String, Integer> dict, IHeap<Integer, Integer> heap) {
        return value;
    }

    public String toString() {
        return "" + value;
    }
}
