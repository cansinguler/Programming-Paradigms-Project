package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentManagerImpl implements StudentManager {
    @Override
    public void addStudent(Student student) {
        // Check if student already exists
        if (isStudentExists(student.getStudentID())) {
            System.out.println("Error: Student ID already exists.");
            return; // Skip adding the student
        }

        String sql = "INSERT INTO students (studentID, name, age, grade) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, student.getStudentID());
            pstmt.setString(2, student.getName());
            pstmt.setInt(3, student.getAge());
            pstmt.setDouble(4, student.getGrade());

            pstmt.executeUpdate();
            System.out.println("Student added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
        }
    }


    // Helper method to check if a student with the same ID exists
    public boolean isStudentExists(String studentID) {
        String sql = "SELECT COUNT(*) FROM students WHERE studentID = ?";
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);
            var rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // If count > 0, student exists
            }
        } catch (SQLException e) {
            System.out.println("Error checking if student exists: " + e.getMessage());
        }
        return false;
    }


    @Override
    public void removeStudent(String studentID) {
        String sql = "DELETE FROM students WHERE studentID = ?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing student: " + e.getMessage());
        }
    }


    @Override
    public void updateStudent(String studentID, String name, int age, double grade) {
        String sql = "UPDATE students SET name = ?, age = ?, grade = ? WHERE studentID = ?";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setDouble(3, grade);
            pstmt.setString(4, studentID);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating student: " + e.getMessage());
        }
    }


    @Override
    public ArrayList<Student> displayAllStudents() {
        ArrayList<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             var rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String studentID = rs.getString("studentID");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double grade = rs.getDouble("grade");

                Student student = new Student(name, age, grade, studentID);
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving students: " + e.getMessage());
        }

        return students;
    }


    @Override
    public double calculateAverageGrade() {
        double totalGrade = 0.0;
        int studentCount = 0;
        String sql = "SELECT grade FROM students";

        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             var rs = pstmt.executeQuery()) {

            while (rs.next()) {
                totalGrade += rs.getDouble("grade");
                studentCount++;
            }

            if (studentCount > 0) {
                return totalGrade / studentCount;
            }
        } catch (SQLException e) {
            System.out.println("Error calculating average grade: " + e.getMessage());
        }

        return 0.0; // Return 0.0 if no students are found
    }
    public Student getStudentByID(String studentID) {
        String sql = "SELECT * FROM students WHERE studentID = ?";  // Query to get student by ID
        try (Connection conn = DatabaseHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, studentID);  // Set the student ID in the query
            var rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                double grade = rs.getDouble("grade");

                // Create and return a Student object
                return new Student(name, age, grade, studentID);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving student: " + e.getMessage());
        }
        return null;  // If no student found, return null
    }


}
