package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isBudgetEducation() {
        return budgetEducation;
    }

    public String[] getSubjects() {
        return subjects;
    }

    public SchoolCertificate getCertificate() {
        return certificate;
    }

    public static void main(String[] args) {
        final Student student = new Student("Vadim", 20, false,
                new String[]{"мат. анализ", "аналитическая геометрия", "Химия"},
                new SchoolCertificate("Vadim", 11));
        System.out.println(new JSONObject(student).toString());

        List<String> list = Arrays.asList("мат. анализ", "аналитическая геометрия", "Химия");
        JSONObject jsonSchoolCertificate = new JSONObject("{\"name\":\"Vadim\", \"study years\":\"11\"}");

        JSONArray jsonArray = new JSONArray(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", student.getName());
        jsonObject.put("age", student.getAge());
        jsonObject.put("budget education", student.isBudgetEducation());
        jsonObject.put("subjects", jsonArray);
        jsonObject.put("certificate", jsonSchoolCertificate);
        System.out.println(jsonObject.toString());

    }
}
