package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "schoolCertificate")
public class SchoolCertificate {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int studyYears;

    public SchoolCertificate(String name, int studyYears) {
        this.name = name;
        this.studyYears = studyYears;
    }

    public SchoolCertificate() {

    }

    @Override
    public String toString() {
        return "SchoolCertificate{"
                + "name='" + name + '\''
                + ", studyYears=" + studyYears
                + '}';
    }
}
