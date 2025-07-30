package ConstructorChaining;

public class Student {
    String name;
    int age;
    String course;

    Student() {
        this("Unknown", 0, "None"); // calling another constructor
    }

    Student(String name) {
        this(name, 0, "None"); // calling another constructor
    }

    Student(String name, int age) {
        this(name, age, "Java"); // calling another constructor
    }

    Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    void display() {
        System.out.println(name + " - " + age + " - " + course);
    }

    public static void main(String[] args) {
        Student s1 = new Student();
        Student s2 = new Student("Hrutwik");
        Student s3 = new Student("Kale", 25);
        Student s4 = new Student("Nikhil", 26, "Spring Boot");

        s1.display();
        s2.display();
        s3.display();
        s4.display();
    }
}
