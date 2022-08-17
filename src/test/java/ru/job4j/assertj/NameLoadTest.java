package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void validateFalseNotContainsSym() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"fail3"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("symbol")
                .hasMessageContaining("=")
                .hasMessageContaining(names[0]);
    }

    @Test
    void validateFalseNotContainsKey() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=fail2"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key")
                .hasMessageContaining(names[0]);
    }

    @Test
    void validateFalseNotContainsValue() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"fail3="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value")
                .hasMessageContaining(names[0]);
    }

    @Test
    void parseFailEmptyNames() {
        NameLoad nameLoad = new NameLoad();
        String[] names = new String[0];
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }
}