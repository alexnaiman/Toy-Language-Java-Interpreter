package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.IHeap;

public class VariableExpression implements IExpression {
    private String id;

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public int eval(IDictionary<String, Integer> dict, IHeap<Integer, Integer> heap) {
        return dict.getValue(id);
    }

    @Override
    public String toString() {
        return id;
    }
}
