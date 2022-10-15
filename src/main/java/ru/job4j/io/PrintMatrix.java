package ru.job4j.io;

import java.io.FileOutputStream;

import static ru.job4j.io.MatrixIO.multiple;

public class PrintMatrix {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("multiple table.txt")) {
            out.write(multiple(11).getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
