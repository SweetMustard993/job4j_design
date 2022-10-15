package ru.job4j.io;

import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

public class MatrixIO {
    public static String multiple(int size) {
        int[][] table = new int[size][size];
        String tableString = "";
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                table[i][j] = (i + 1) * (j + 1);
                tableString += table[i][j] + " ";

            }
            tableString += System.lineSeparator();
        }
        return tableString;
    }
}
