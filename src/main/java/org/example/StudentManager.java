package org.example;

import java.util.ArrayList;

public interface StudentManager {
    void addStudent(Student student); // Adds a new student
    void removeStudent(String studentID); // Removes a student by ID
    void updateStudent(String studentID, String name, int age, double grade); // Updates student info
    ArrayList<Student> displayAllStudents(); // Returns a list of all students
    double calculateAverageGrade(); // Calculates and returns the average grade
}
