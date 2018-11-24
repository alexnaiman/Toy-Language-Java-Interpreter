package Model.FileStatement;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FileTable<K, V> implements IFileTable<K, V> {

    private Map<K,V> map = new HashMap<>();

    @Override
    public void add(K key, V value) {
        map.put(key,value);
    }

    @Override
    public void delete(K key) {
        map.remove(key);
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    @Override
    public Iterable<K> getAllKeys() {
        return map.keySet();
    }

    @Override
    public Collection<V> getAllValues() {
        return map.values();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(Map.Entry<K,V> entry : map.entrySet()){
            builder.append('<').append(entry.getKey()).append(',').append(entry.getValue()).append(">;");
        }
        return builder.toString();
    }
}
