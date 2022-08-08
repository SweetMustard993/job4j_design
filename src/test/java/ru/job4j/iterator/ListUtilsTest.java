package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenDoubleAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3, 4);
    }

    @Test
    void whenDoubleAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 0, 0);
        assertThat(input).hasSize(4).containsSequence(0, 1, 2, 3);
    }

    @Test
    void whenRemoveIfOne() {
        ListUtils.removeIf(input, (x) -> x == 1);
        assertThat(input).hasSize(1).containsSequence(3);
    }

    @Test
    void whenReplaceIfOne() {
        ListUtils.replaceIf(input, (x) -> x == 1, 3);
        assertThat(input).hasSize(2).containsSequence(3, 3);
    }

    @Test
    void whenRemoveAllMatch() {
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 3, 5, 6));
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(0).containsSequence();
    }

    @Test
    void whenAddBeforeFirst() {
        ListUtils.addBefore(input, 0, 0);
        assertThat(input).hasSize(3).containsSequence(0, 1, 3);
    }
}