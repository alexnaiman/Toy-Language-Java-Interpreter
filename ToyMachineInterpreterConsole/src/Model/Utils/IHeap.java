package Model.Utils;

import java.util.Map;

public interface IHeap<K, V> {
    K add(V value);

    void remove(K address);

    V get(K address);

    Iterable<K> getAll();

    void replace(K address, V newValue);

    void setContent(Map<K, V> newContent);

    boolean exists(K key);

    Map<K, V> getContent();
}
