package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    @Override
    public void add(E value) {
        Node<E> tempLast = last;
        Node<E> newNode = new Node<>(value, null);
        last = newNode;
        if (tempLast == null) {
            first = newNode;
        } else {
            tempLast.next = newNode;
        }
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        index = Objects.checkIndex(index, size);
        Node<E> rsl;
        if (index == size) {
            rsl = last;
        } else {
            rsl = first;
            for (int i = 0; i < index; i++) {
                rsl = rsl.next;
            }
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            final int expectedModCount = modCount;
            int indexIterator = 0;
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException("список был изменен c момента начала итерации");
                }
                return indexIterator < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = node.item;
                node = node.next;
                indexIterator++;
                return rsl;
            }
        };
    }

    class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}