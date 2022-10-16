package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            Arrays.stream(text.toString().split(System.lineSeparator()))
                    .map(Integer::valueOf)
                    .collect(Collectors.toMap(num -> num, num -> num % 2 == 0))
                    .forEach((integer, aBoolean) -> {
                        System.out.println(integer + " " + aBoolean);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
