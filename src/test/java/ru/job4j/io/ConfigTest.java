package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {

    @Test
    void whenPairWithoutComment(@TempDir Path tempDir) throws IOException {
        File path = tempDir.resolve("path.txt").toFile();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println("name=postgres");
        }
        Config config = new Config(path.getAbsolutePath());
        config.load();
        assertThat(config.value("name")).isEqualTo("postgres");
    }

    @Test
    void whenPairWithComment(@TempDir Path tempDir) throws IOException {
        File path = tempDir.resolve("path.txt").toFile();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println("# PostgreSQL");
            out.println("hibernate.connection.username=postgres");
        }
        Config config = new Config(path.getAbsolutePath());
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    void whenPairWithTwoReg(@TempDir Path temDir) throws IOException {
        File path = temDir.resolve("path.txt").toFile();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println("hibernate.connection.username=postgres=3");
        }
        Config config = new Config(path.getAbsolutePath());
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres=3");
    }

    @Test
    void whenPairWithEmptyString(@TempDir Path temDir) throws IOException {
        File path = temDir.resolve("path.txt").toFile();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println("");
            out.println("hibernate.connection.username=postgres");
        }
        Config config = new Config(path.getAbsolutePath());
        config.load();
        assertThat(config.value("hibernate.connection.username")).isEqualTo("postgres");
    }

    @Test
    void whenPairWithWrongArgument(@TempDir Path temDir) throws IllegalArgumentException, IOException {
        File path = temDir.resolve("path.txt").toFile();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println("hibernate.connection.username=");
        }
        Config config = new Config(path.getAbsolutePath());
        Throwable thrown = assertThrows(IllegalArgumentException.class, config::load);
    }

    @Test
    void whenPairWithWrongKeyArgument(@TempDir Path temDir) throws IllegalArgumentException, IOException {
        File path = temDir.resolve("path.txt").toFile();
        try (PrintWriter out = new PrintWriter(path)) {
            out.println("=postgres");
        }
        Config config = new Config(path.getAbsolutePath());
        Throwable thrown = assertThrows(IllegalArgumentException.class, config::load);
    }
}