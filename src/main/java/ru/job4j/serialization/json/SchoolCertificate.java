package ru.job4j.serialization.json;

import java.util.Arrays;

public class SchoolCertificate {
    private final String name;
    private final int studyYears;


    public SchoolCertificate(String name, int studyYears) {
        this.name = name;
        this.studyYears = studyYears;
    }

    @Override
    public String toString() {
        return "SchoolCertificate{"
                + "name='" + name + '\''
                + ", studyYears=" + studyYears
                + '}';
    }

    public String getName() {
        return name;
    }

    public int getStudyYears() {
        return studyYears;
    }
}
