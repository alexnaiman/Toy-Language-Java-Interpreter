package Model.Utils;

import java.util.ArrayList;
import java.util.List;

public class CustomList<T> implements IList<T> {

    private List<T> elems = new ArrayList<>();

    @Override
    public void add(T elem) {
        elems.add(elem);
    }

    @Override
    public Iterable<T> getAll() {
        return elems;
    }

    @Override
    public void remove(T elem) {
        elems.remove(elem);
    }

    @Override
    public String toString() {
        StringBuilder buff = new StringBuilder();

        for (T elem : elems) {
            buff.append(elem).append(" ");
        }
        return buff.toString();
    }
}
