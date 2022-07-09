package ru.job4j.iterator;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.NoSuchElementException;

public class BackwardArrayItTest {

    @Test
    public void whenMultiCallHasNextThenTrue() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertSame(it.next(), 3);
    }

    @Test
    public void whenMultiCallHasNextThenNext() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertTrue(it.hasNext());
        assertSame(it.next(), 3);

    }

    @Test
    public void whenReadSequence() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {1, 2, 3}
        );
        assertSame(it.next(), 3);
        assertSame(it.next(), 2);
        assertSame(it.next(), 1);
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        BackwardArrayIt it = new BackwardArrayIt(
                new int[] {}
        );
        it.next();
    }
}