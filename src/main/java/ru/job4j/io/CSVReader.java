package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class CSVReader {
    public static final String PATH = "path";
    public static final String DELIMITER = "delimiter";
    public static final String OUT = "out";
    public static final String FILTER = "filter";

    public static void handle(ArgsName argsName) throws Exception {
        ArrayList<HashMap<Integer, StringBuilder>> arrayLines = new ArrayList<>();
        List<String> filterValues = getFilterValues(argsName);
        String delimiter = argsName.get(DELIMITER);
        List<Integer> positions = new ArrayList<>();
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(argsName.get(PATH)))).useDelimiter(delimiter + "|\n")) {
            int indexTokenInLine = 0;
            int indexLine = 0;
            List<String> columnsNames = new ArrayList<>();
            if (scanner.hasNextLine()) {
                columnsNames = Arrays.stream(scanner.nextLine().split(delimiter))
                        .collect(Collectors.toList());
                arrayLines.add(new HashMap<>());
            }
            OUTER:
            for (String value : filterValues) {
                for (String token : columnsNames) {
                    if (value.equalsIgnoreCase(token.replace("\"", ""))) {
                        positions.add(indexTokenInLine);
                        arrayLines.get(indexLine).put(indexTokenInLine, new StringBuilder(token));
                        indexTokenInLine = 0;
                        continue OUTER;
                    }
                    indexTokenInLine++;
                }
            }
            indexLine++;
            indexTokenInLine = 0;
            arrayLines.add(new HashMap<>());
            boolean quotesInToken = false;
            while (scanner.hasNext()) {
                if (positions.contains(indexTokenInLine) && scanner.hasNext("\\B\"\".+")) {
                    quotesInToken = true;
                }
                if (positions.contains(indexTokenInLine) && scanner.hasNext(".+\"\"\\B|.+\"\"\r\\B")) {
                    quotesInToken = false;
                }
                if (!quotesInToken && positions.contains(indexTokenInLine) && !scanner.hasNext(".+\r")) {
                    addToken(arrayLines, scanner, indexTokenInLine, indexLine, delimiter);
                    indexTokenInLine++;
                    continue;
                }
                if (positions.contains(indexTokenInLine) && scanner.hasNext(".+\r")) {
                    addToken(arrayLines, scanner, indexTokenInLine, indexLine, delimiter);
                    indexTokenInLine = 0;
                    indexLine++;
                    arrayLines.add(new HashMap<>());
                    continue;
                }
                if (quotesInToken && positions.contains(indexTokenInLine)) {
                    addToken(arrayLines, scanner, indexTokenInLine, indexLine, delimiter);
                    continue;
                }
                if (!positions.contains(indexTokenInLine) && scanner.hasNext(".+\r")) {
                    arrayLines.add(new HashMap<>());
                    scanner.next();
                    indexLine++;
                    indexTokenInLine = 0;
                    continue;
                }
                if (!positions.contains(indexTokenInLine) && !scanner.hasNext(".+\r")) {
                    String ss = scanner.next();
                    indexTokenInLine++;
                }
            }
        }
        writeOut(argsName, positions, arrayLines, delimiter);
    }

    private static void validateCSVReaderArgs(ArgsName argsName) {
        if (argsName == null) {
            throw new IllegalArgumentException("no arguments passed");
        }
        Path file = Path.of(argsName.get(PATH));
        if (Files.notExists(file)) {
            throw new IllegalArgumentException("the specified path does not exist");
        }
        if (Files.isDirectory(file)) {
            throw new IllegalArgumentException("the specified path is a directory");
        }
        if (!argsName.get(PATH).endsWith(".csv")) {
            throw new IllegalArgumentException("the file at the specified path must be in .csv format");
        }
        if (!(argsName.get(OUT).equals("stdout") || Path.of(argsName.get(OUT)).isAbsolute())) {
            throw new IllegalArgumentException("should point to console output or file path");
        }
        argsName.get(OUT);
        argsName.get(DELIMITER);
        argsName.get(FILTER);
    }

    private static List<String> getFilterValues(ArgsName argsName) {
        return Arrays.stream(argsName.get(FILTER)
                .split(","))
                .toList();
    }

    private static void writeOut(ArgsName argsName, List<Integer> positions, ArrayList<HashMap<Integer,
            StringBuilder>> arrayMap, String delimiter) {
        StringBuilder text = new StringBuilder();
        int index = 0;
        for (HashMap<Integer, StringBuilder> lineMap : arrayMap) {
            StringJoiner line = new StringJoiner(delimiter, "", "");
            for (Integer position : positions) {
                if (lineMap.size() > 0) {
                    line.add(lineMap.get(position).toString());
                }
            }
            text.append(line).append(System.lineSeparator());
            index++;
        }
        if (argsName.get(OUT).equals("stdout")) {
            System.out.println(text);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(argsName.get(OUT)))) {
                writer.write(text.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void addToken(ArrayList<HashMap<Integer, StringBuilder>> arrayMap, Scanner scanner,
                                 int indexTokenInLine, int indexLine, String delimiter) {
        String value = scanner.next().replace("\r", "");
        arrayMap.get(indexLine).computeIfPresent(indexTokenInLine,
                (k, v) -> v.append(delimiter).append(value));
        arrayMap.get(indexLine).putIfAbsent(indexTokenInLine, new StringBuilder(value));
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.validateCSVReaderArgs(argsName);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}