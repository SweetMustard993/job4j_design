package ru.job4j.io;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        validateKey(key);
        return values.get(key);
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(arg -> arg.substring(1))
                .map(arg -> arg.split("=", 2))
                .forEach(entry -> values.put(entry[0], entry[1]));
    }


    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.validateArgs(args);
        names.parse(args);
        return names;
    }

    private void validateArgs(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        Arrays.asList(args).forEach(arg -> {
            if (arg.indexOf("=") == 1) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a key");
            }
            if (arg.indexOf("=") == arg.length() - 1) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain a value");
            }
            if (!arg.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not start with "
                        + "a '-' character");
            }
            if (!arg.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + arg + "' does not contain an equal sign");
            }
        });
    }

    private void validateKey(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));

        System.out.println(String.format("%1$,+016.2f", 5000000.0000));
    }
}