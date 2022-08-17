package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three");
        assertThat(list).hasSize(3)
                .containsExactly("first", "second", "three")
                .containsAnyOf("seven", "second", "nine")
                .endsWith("three");
    }

    @Test
    void toSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "nine", "seven");
        assertThat(set).isNotNull()
                .allSatisfy((e) -> {
                    assertThat(e.length()).isGreaterThan(3);
                    assertThat(e.length()).isLessThan(10);
                })
                .allMatch(e -> e.length() >= 4);
    }

    @Test
    void checkGroupFromList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "nine", "seven", "second", "seven", "second");
        assertThat(list).filteredOn(e -> e.startsWith("se"))
                .first().isEqualTo("seven");
    }

    @Test
    void toMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "nine", "seven");
        assertThat(map).hasSize(5)
                .containsValues(1, 2, 3, 4)
                .doesNotContainKey("zero")
                .containsEntry("nine", 3);
    }
}