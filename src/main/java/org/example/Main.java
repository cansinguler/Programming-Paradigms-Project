package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        DatabaseHelper.createTable(); // Ensure the database is ready

        StudentManagerImpl manager = new StudentManagerImpl();
        String studentID = "S001"; // Alice's ID

        // Remove Alice if she exists in the database
        manager.removeStudent(studentID);

        // Now add Alice again
        Student student1 = new Student("Alice", 20, 85.5, studentID);
        manager.addStudent(student1);

        // Example to display all students
        ArrayList<Student> students = manager.displayAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            for (Student student : students) {
                student.displayInfo();
            }
        }

        // Calculate average grade
        double avgGrade = manager.calculateAverageGrade();
        System.out.println("Average Grade: " + avgGrade);
    }
}
