package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, StringBuilder> duplicates;
    private Map<FileProperty, StringBuilder> files = new HashMap<>();

    DuplicatesVisitor(Map<FileProperty, StringBuilder> duplicates) {
        this.duplicates = duplicates;
    }

    public Map<FileProperty, StringBuilder> getDuplicates() {
        return duplicates;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(Files.size(file), file.getFileName().toString());
        if (files.containsKey(fileProperty)) {
            duplicates.putIfAbsent(fileProperty, files.get(fileProperty));
        }
        files.putIfAbsent(fileProperty, new StringBuilder(file.toAbsolutePath().toString()));
        duplicates.computeIfPresent(fileProperty, (k, v) -> v.append("\n").append(file.toAbsolutePath()));
        return super.visitFile(file, attrs);
    }
}