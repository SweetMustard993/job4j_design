package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        boolean silenceActivated = false;
        List<String> log = new ArrayList<>();
        List<String> answers = readPhrases();
        int answerNumber = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Для начала общения напишите что-нибудь в чат");
        String input = scanner.nextLine();
        log.add(input);
        while (!OUT.equals(input)) {
            if (STOP.equals(input)) {
                silenceActivated = true;
            }
            if (CONTINUE.equals(input)) {
                silenceActivated = false;
            }
            if (!silenceActivated) {
                answerNumber = (int) (Math.random() * answers.size());
                log.add(answers.get(answerNumber));
                System.out.println(answers.get(answerNumber));
            }
            input = scanner.nextLine();
            log.add(input);
        }
        scanner.close();
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> answers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().forEach(answers::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            log.forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./src/data/log.txt", "./src/data/answers.txt");
        cc.run();
    }
}