package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {

    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            increaseCapacity();
        }
        container[size++] = value;

    }

    @Override
    public T set(int index, T newValue) {
        index = Objects.checkIndex(index, size);
        T rsl = container[index];
        container[index] = newValue;
        return rsl;
    }

    @Override
    public T remove(int index) {
        index = Objects.checkIndex(index, size);
        modCount++;
        T rsl = (T) container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size--;
        return rsl;
    }

    @Override
    public T get(int index) {
        return container[Objects.checkIndex(index, size)];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            final int expectedModCount = modCount;
            int indexIterator = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("список был изменен c момента начала итерации");
                }
                return indexIterator < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[indexIterator++];
            }

        };
    }

    private void increaseCapacity() {
        Object[] newArray = Arrays.copyOf(container, (container.length + 1) * 2);
        container = (T[]) newArray;
    }
}