package ru.job4j.serialization.json;

import java.util.Formatter;

public class Demo {
    public static void main(String[] args) {
        Formatter fmt = new Formatter();
        for (double i = 1.23; i < 1.0e+6; i = i * 100) {
            fmt.format(" %f %e", i, i);
            System.out.println(fmt);
        }
        fmt.close();
    }
}
