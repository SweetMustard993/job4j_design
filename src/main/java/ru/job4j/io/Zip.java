package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static final String DIRECTORY = "d";
    public static final String EXCLUDE = "e";
    public static final String OUTPUT = "o";


    public void packFiles(List<Path> sources, File target) {
        List<File> sourcesFiles = sources.stream()
                .map(Path::toFile)
                .collect(Collectors.toList());
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File source : sourcesFiles) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateZipArgs(ArgsName argsName) {
        if (argsName == null) {
            throw new IllegalArgumentException("no arguments passed");
        }
        Path directory = Path.of(argsName.get(DIRECTORY));
        if (!(Files.exists(directory) && Files.isDirectory(directory))) {
            throw new IllegalArgumentException("directory not exists");
        }
        argsName.get(EXCLUDE);
        argsName.get(OUTPUT);
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        Zip.validateZipArgs(argsName);
        Zip zip = new Zip();
        try {
            zip.packFiles(Search.search(Path.of(argsName.get(DIRECTORY)),
                    file -> !file.endsWith(argsName.get(EXCLUDE).substring(1))),
                    Path.of(argsName.get(OUTPUT)).toFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}