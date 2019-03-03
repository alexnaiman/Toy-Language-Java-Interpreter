package Model.Utils;

public interface IList<T> {
    void add(T elem);

    Iterable<T> getAll();

    void remove(T elem);
}
