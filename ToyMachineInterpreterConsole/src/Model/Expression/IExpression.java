package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.IHeap;

public interface IExpression {
    int eval(IDictionary<String, Integer> dict, IHeap<Integer, Integer> heap);
}
