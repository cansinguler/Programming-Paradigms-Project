package org.example;

public class Student {
    private String name;
    private int age;
    private double grade;
    private String studentID;

    // Constructor
    public Student(String name, int age, double grade, String studentID) {
        this.name = name;
        setAge(age);
        setGrade(grade);
        this.studentID = studentID;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) { // Validation
            this.age = age;
        } else {
            throw new IllegalArgumentException("Age must be positive.");
        }
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) { // Validation
            this.grade = grade;
        } else {
            throw new IllegalArgumentException("Grade must be between 0.0 and 100.0.");
        }
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    // Display student info
    public void displayInfo() {
        System.out.println("Student ID: " + studentID);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Grade: " + grade);
    }
}
