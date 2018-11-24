package Model.Utils;

import java.util.List;

public interface IDictionary<Key, Value> {

    List<Value> getValues();

    Value getValue(Key k);

    void setValue(Key k, Value v);

    boolean exists(Key k);

    Iterable<Key> getAll();

    void delete(Key k);

    IDictionary<Key, Value> copy();


}
