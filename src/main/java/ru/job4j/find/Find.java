package ru.job4j.find;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Find {

    public static final String DIRECTORY = "d";
    public static final String NAME = "n";
    public static final String TYPE = "t";
    public static final String OUT = "o";
    public static final String HINT = """
            Для поиска файлов укажите, через ";", пары ключ=значение для следущих параметров: -d - директория поиска файла
            -n - имя файла, маска, либо регулярное выражение
            -t - тип поиска: mask искать по маске, name по полному совпадение имени, regex по регулярному выражению
            -o - файл для записи результатов""";

    public static void main(String[] args) throws IOException {
        System.out.println(HINT);
        Scanner scanner = new Scanner(System.in);
        String arguments = scanner.nextLine();
        ArgsName argsName = ArgsName.of(arguments.split(";"));
        validate(argsName);
        search(argsName).forEach(System.out::println);
    }

    private static Pattern defineSearchValue(ArgsName argsName) {
        String argValue = argsName.get(TYPE);
        Pattern result = Pattern.compile("");
        if (argValue.equals("mask")) {
            argValue = argsName.get(NAME).replaceAll("\\.", "[.]")
                    .replaceAll("\\?", ".{1}"
                            .replaceAll("\\*", ".*"));
            result = Pattern.compile(argValue);
        }
        if (argValue.equals("name")) {
            String quoteName = Pattern.quote(argsName.get(NAME));
            result = Pattern.compile(quoteName);
        }
        if (argValue.equals("regex")) {
            result = Pattern.compile(argsName.get(NAME));
        }
        return result;
    }

    public static List<Path> search(ArgsName argsName) throws IOException {
        Pattern searchValue = defineSearchValue(argsName);
        Predicate<Path> condition = path -> {
            Matcher matcher = searchValue.matcher(path.getFileName().toString());
            return matcher.matches();
        };
        FindFile searcher = new FindFile(condition);
        Files.walkFileTree(Paths.get(argsName.get(DIRECTORY)), searcher);
        StringBuilder resultToFile = new StringBuilder();
        searcher.getFileList().forEach(str -> resultToFile.append(str).append("\r\n"));
        Files.writeString(Paths.get(argsName.get(OUT)), resultToFile);
        return searcher.getFileList();
    }

    public static void validate(ArgsName argsName) {
        if (argsName == null) {
            throw new IllegalArgumentException("no arguments passed");
        }
        Path file = Path.of(argsName.get(DIRECTORY));
        if (Files.notExists(file)) {
            throw new IllegalArgumentException("the specified directory does not exist");
        }
        if (!Files.isDirectory(file)) {
            throw new IllegalArgumentException("the specified path is not a directory");
        }

        if (!(argsName.get(TYPE).equals("mask") || argsName.get(TYPE).equals("regex") || argsName.get(TYPE).equals("name"))) {
            throw new IllegalArgumentException("parameter type must be one of the values: \"mask\", \"name\" or \"regex\"");
        }
        argsName.get(OUT);
        argsName.get(NAME);
    }
}
