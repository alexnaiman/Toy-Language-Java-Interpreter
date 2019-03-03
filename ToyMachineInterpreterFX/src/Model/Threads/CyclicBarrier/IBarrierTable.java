package Model.Threads.CyclicBarrier;

import java.util.Collection;

public interface IBarrierTable<K, V> {
    void add(K key, V value);
    void delete(K key);
    V get(K key);
    boolean contains(K key);
    Iterable<K> getAllKeys();
    Collection<V> getAllValues();
}
