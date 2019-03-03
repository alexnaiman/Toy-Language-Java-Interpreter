package Model.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Exception.NullEntryException;

public class CustomDictionary<Key, Value> implements IDictionary<Key, Value> {

    private Map<Key, Value> elems = new HashMap<>();

    @Override

    public List<Value> getValues() {
        return new ArrayList<>(elems.values());
    }

    @Override
    public Value getValue(Key k) {
        if (!exists(k))
            throw new NullEntryException("Cannot find item! Key is not defined");
        return elems.get(k);
    }

    @Override
    public void setValue(Key k, Value v) {
        elems.put(k, v);
    }

    @Override
    public boolean exists(Key k) {
        return elems.containsKey(k);
    }

    @Override
    public Iterable<Key> getAll() {
        return elems.keySet();
    }

    @Override
    public void delete(Key k) {
        elems.remove(k);
    }

    @Override
    public IDictionary<Key, Value> copy() {
        IDictionary<Key, Value> copyDict = new CustomDictionary<>();
        for(Key key : this.getAll())
            copyDict.setValue(key, elems.get(key));
        return copyDict;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Key, Value> elem : elems.entrySet()) {
            builder.append(elem).append(" ");
        }
        return builder.toString();
    }

}

