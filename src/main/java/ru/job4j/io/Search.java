package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        search(Paths.get(args[0]), p -> p.toFile().getName().endsWith(".txt")).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("The values of the passed parameters are null");
        }
        Path path = Paths.get(args[0]);
        if (Files.notExists(path)) {
            throw new IllegalArgumentException(String.format("Not exist %s", args[0]));
        }
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("The value of the first argument is not a directory");
        }
        if (args[1].startsWith(".") && args[1].length() > 1) {
            throw new IllegalArgumentException("The value is not an extension");
        }
    }
}