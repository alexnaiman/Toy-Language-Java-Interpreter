package Model.Expression;

import Model.Utils.IDictionary;
import Model.Utils.IHeap;
import Exception.HeapException;

public class ReadHeapExpression implements IExpression {
    private String name;

    public ReadHeapExpression(String name) {
        this.name = name;
    }

    @Override
    public int eval(IDictionary<String, Integer> dict, IHeap<Integer, Integer> heap) {
        if (!dict.exists(name))
            throw new HeapException("Variable does not exist");
        int id = dict.getValue(name);
        if (!heap.exists(id))
            throw new HeapException("Variable does not exist in the heap");
        return heap.get(id);
    }

    @Override
    public String toString() {
        return "readHeap(" + this.name + ")";
    }
}
