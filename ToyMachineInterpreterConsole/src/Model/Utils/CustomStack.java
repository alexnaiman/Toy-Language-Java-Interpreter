package Model.Utils;

import java.util.ArrayDeque;
import java.util.Deque;

public class CustomStack<T> implements IStack<T> {

    private Deque<T> elems = new ArrayDeque<>();

    @Override
    public void push(T elem) {
        elems.push(elem);
    }

    @Override
    public T pop() {
        return elems.pop();
    }

    @Override
    public boolean isEmpty() {
        return elems.isEmpty();
    }

    @Override
    public T top() {
        return elems.peek();
    }

    @Override
    public Iterable<T> getAll() {
        return elems;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (T elem : elems) {
            builder.append(elem).append(" ");
        }
        return builder.toString();
    }

}

