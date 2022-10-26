package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analysis {
    public void unavailable(String source, String target) {
        StringJoiner in = new StringJoiner("");
        AtomicBoolean unavailable = new AtomicBoolean(false);
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines()
                    .map(line -> line.split(" "))
                    .forEach(line -> {
                        if (("400".equals(line[0]) || "500".equals(line[0])) && !unavailable.get()) {
                            in.add(line[1]);
                            in.add(";");
                            unavailable.set(true);
                        }
                        if (("200".equals(line[0])) && unavailable.get()) {
                            in.add(line[1]);
                            in.add(System.lineSeparator());
                            unavailable.set(false);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter((new FileOutputStream(target)))) {
            out.print(in);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/source.log", "./data/target.txt");
    }
}