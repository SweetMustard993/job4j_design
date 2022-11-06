package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class DuplicatesFinder {
    public static void main(String[] args) throws IOException {
        Map<FileProperty, StringBuilder> duplicates = new HashMap<>();
        Files.walkFileTree(Path.of("C:\\projects\\Test"), new DuplicatesVisitor(duplicates));
        duplicates.forEach((fileProperty, stringBuilder) -> System.out.printf("%s - %s %s %s", fileProperty.getName(),
                fileProperty.getSize(), System.lineSeparator(), stringBuilder));

    }
}