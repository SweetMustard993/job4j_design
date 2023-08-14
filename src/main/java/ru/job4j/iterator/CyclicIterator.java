package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private List<T> data;
    int index;

    public CyclicIterator(List<T> data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (data.isEmpty()) {
            throw new NoSuchElementException();
        }
        return index < data.size();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            index = 0;
        }
        return data.get(index++);
    }
}