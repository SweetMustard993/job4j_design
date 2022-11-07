package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> duplicates = new HashMap<>();

    public void printResult() {
        duplicates.forEach((fileProperty, list) -> {
            if (list.size() >= 2) {
                System.out.printf("%s - %s %s", fileProperty.getName(), fileProperty.getSize(), System.lineSeparator());
                list.forEach(System.out::println);
            }
        });
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        duplicates.putIfAbsent(fileProperty, new ArrayList<Path>());
        duplicates.get(fileProperty).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }
}