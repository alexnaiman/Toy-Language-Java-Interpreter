package Model.Utils;

import java.util.HashMap;
import java.util.Map;

import Exception.HeapException;

public class CustomHeap<K, V> implements IHeap<K, V> {

    private Map<K, V> heap = new HashMap<>();
    private KeyGenerator<K> generator;

    public CustomHeap(KeyGenerator<K> generator) {
        this.generator = generator;
    }

    @Override
    public K add(V value) {
        K id = generator.generate();
        heap.put(id, value);
        return id;
    }

    @Override
    public void remove(K address) {
        if (!heap.containsKey(address))
            throw new HeapException("Cannot find address");
        heap.remove(address);
    }

    @Override
    public V get(K address) {
        if (!exists(address))
            throw new HeapException("Cannot find address");
        return heap.get(address);
    }

    @Override
    public Iterable<K> getAll() {
        return heap.keySet();
    }

    @Override
    public boolean exists(K address) {
        return heap.containsKey(address);
    }

    @Override
    public void replace(K address, V newValue) {
        if (this.exists(address))
            this.heap.replace(address, newValue);
        else
            throw new HeapException("Invalid heap address");
    }

    @Override
    public void setContent(Map<K, V> newContent) {
        this.heap = newContent;
    }

    @Override
    public Map<K, V> getContent() {
        return heap;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("\nHEAP: ");
        if (this.heap.isEmpty()) str.append("\n");
        for (K key : this.heap.keySet())
            str.append("\n").append(key.toString()).append("->").append(this.get(key).toString());
        return str.toString();
    }
}
