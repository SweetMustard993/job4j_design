package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;

public class Student {
    private final String name;
    private final int age;
    private final boolean budgetEducation;
    private final String[] subjects;
    private final SchoolCertificate certificate;

    public Student(String name, int age, boolean budgetEducation, String[] subjects, SchoolCertificate certificate) {
        this.name = name;
        this.age = age;
        this.budgetEducation = budgetEducation;
        this.subjects = subjects;
        this.certificate = certificate;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", budgetEducation=" + budgetEducation
                + ", subjects=" + Arrays.toString(subjects)
                + ", certificate=" + certificate
                + '}';
    }

    public static void main(String[] args) {
        final Student student = new Student("Vadim", 20, false,
                new String[]{"мат. анализ", "аналитическая геометрия", "Химия"},
                new SchoolCertificate("Vadim", 11));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(student));

        final String studentJson =
                "{"
                        + "\"name\":\"Vadim\","
                        + "\"age\":20,"
                        + "\"budgetEducation\":false,"
                        + "\"subjects\":[\"мат. анализ\"," + "\"аналитическая геометрия\",\"Химия\"],"
                        + "\"certificate\":"
                        + "{"
                        + "\"name\":\"Vadim\","
                        + "\"studyYears\":11"
                        + "}"
                        + "}";

        final Student studentFromJson = gson.fromJson(studentJson, Student.class);
        System.out.println(studentFromJson);
    }
}
