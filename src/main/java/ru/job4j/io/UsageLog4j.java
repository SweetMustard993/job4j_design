package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        String placeOfBirth = "Moscow";
        char groupName = 'a';
        int age = 33;
        boolean isStudent = true;
        int courseGrade = 4;
        String courseName = "information systems and networks";
        boolean hasAcademicDebts = false;
        LOG.debug("User info name : {}, place og birth: {}, age : {}, student: {}, grade: {}, course name: {}, "
                        + "groupe name: {}, hasAcademicDebts: {}", name, placeOfBirth, age, isStudent,
                courseGrade, courseName, groupName, hasAcademicDebts);
    }
}