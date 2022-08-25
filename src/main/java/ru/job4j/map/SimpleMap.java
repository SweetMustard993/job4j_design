package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            modCount++;
            count++;
            rsl = true;
        }
        if (count >= capacity * LOAD_FACTOR) {
            expand();
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & table.length - 1;
    }

    private void expand() {
        MapEntry<K, V>[] oldTable = table;
        table = new MapEntry[capacity * 2];
        capacity *= 2;
        count = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null) {
                put(oldTable[i].key, oldTable[i].value);
            }
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int index = -1;
        boolean mapEntryValidate = false;
        if (key == null) {
            rsl = table[0].key == null ? table[0].value : null;
        } else {
            index = hash(key.hashCode() & table.length - 1);
            mapEntryValidate = (table[index] != null) && (table[index].key != null);
        }
        if (mapEntryValidate && table[index].key.hashCode() == key.hashCode() && table[index].key.equals(key)) {
            rsl = table[index].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int index = -1;
        boolean indexValidate = false;
        if (key == null) {
            table[0] = table[0].key == null ? null : table[0];
            rsl = table[0] == null;
            modCount++;
        } else {
            index = hash(key.hashCode() & table.length - 1);
            indexValidate = (table[index] != null) && (table[index].key != null);
        }
        if (indexValidate && table[index].key.hashCode() == key.hashCode() && table[index].key.equals(key)) {
            table[index] = null;
            rsl = true;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expectedModCount = modCount;
            int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < table.length) {
                    if (table[index] != null) {
                        break;
                    }
                    index++;
                }
                return index < table.length;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }

}