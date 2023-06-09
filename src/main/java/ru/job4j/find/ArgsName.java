package ru.job4j.find;

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
                .forEach(entry -> {
                    if (values.containsKey(entry[0])) {
                        throw new IllegalArgumentException("parameter " + entry[0] + "listed twice");
                    }
                    values.put(entry[0], entry[1]);
                });
    }


    public static ArgsName of(String... args) {
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
}