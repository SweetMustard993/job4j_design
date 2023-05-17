package ru.job4j.serialization.xml;


import javax.xml.bind.*;
import javax.xml.bind.annotation.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private int age;

    @XmlAttribute
    private boolean budgetEducation;

    @XmlElementWrapper(name = "subjects")
    @XmlElement(name = "subject")
    private String[] subjects;
    private SchoolCertificate certificate;

    public Student(String name, int age, boolean budgetEducation, String[] subjects, SchoolCertificate certificate) {
        this.name = name;
        this.age = age;
        this.budgetEducation = budgetEducation;
        this.subjects = subjects;
        this.certificate = certificate;
    }

    public Student() {

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

    public static void main(String[] args) throws JAXBException {
        final Student student = new Student("Vadon", 30, false, new String[]{"math", "angem"},
                new SchoolCertificate("Vadon", 4));

        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(student, new File("C:/projects/job4j_design/src/data/student.xml"));
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Student student1 = (Student) unmarshaller.unmarshal(new File("C:/projects/job4j_design/src/data/student.xml"));
        System.out.println(student1.toString());
    }
}
