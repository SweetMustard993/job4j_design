package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicBoolean;

public class Analysis {
    public void unavailable(String source, String target) {
        StringJoiner in = new StringJoiner("");
        List<String[]> statusList = new ArrayList<>();
        boolean unavailable = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            reader.lines()
                    .map(line -> line.split(" "))
                    .forEach(statusList::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String[] status : statusList) {
            if (!"200".equals(status[0]) && !unavailable) {
                in.add(status[1]);
                unavailable = true;
            }
            if ("200".equals(status[0]) && unavailable) {
                in.add(status[1]);
                in.add(System.lineSeparator());
                unavailable = false;
            }
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