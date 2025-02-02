package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class StudentManagementGUI {
    private JFrame frame;
    private JTextField studentIDField, nameField, ageField, gradeField;
    private JTextArea outputArea;
    private StudentManagerImpl manager;

    public StudentManagementGUI() {
        manager = new StudentManagerImpl(); // Ensure to use your existing manager

        // Create the main frame
        frame = new JFrame("Student Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create panels for input and output
        JPanel inputPanel = createInputPanel();
        JPanel outputPanel = createOutputPanel();

        // Add panels to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(outputPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add space between components

        // Student ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Student ID:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        studentIDField = new JTextField(15);
        panel.add(studentIDField, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        nameField = new JTextField(15);
        panel.add(nameField, gbc);

        // Age
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Age:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        ageField = new JTextField(15);
        panel.add(ageField, gbc);

        // Grade
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Grade:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gradeField = new JTextField(15);
        panel.add(gradeField, gbc);

        // Buttons - First row of buttons
        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(addButton, gbc);

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeStudent();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(removeButton, gbc);

        // Buttons - Second row of buttons
        JButton updateButton = new JButton("Update Student");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(updateButton, gbc);

        JButton displayButton = new JButton("Display All Students");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAllStudents();
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(displayButton, gbc);

        // Buttons - Third row of button
        JButton avgButton = new JButton("Calculate Average");
        avgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverageGrade();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;  // Make the "Calculate Average" button span across both columns
        panel.add(avgButton, gbc);

        return panel;
    }






    private JPanel createOutputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Create the output area
        outputArea = new JTextArea(10, 30);
        outputArea.setEditable(false);

        // Set the font to monospaced
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }


    private void addStudent() {
        try {
            String studentID = studentIDField.getText();
            String name = nameField.getText();
            String ageText = ageField.getText();
            String gradeText = gradeField.getText();

            // Check if any of the fields are empty
            if (studentID.isEmpty() || name.isEmpty() || ageText.isEmpty() || gradeText.isEmpty()) {
                outputArea.append("Error: All fields must be filled in.\n");
                return;
            }

            // Parse age and grade only if they are valid numbers
            int age;
            double grade;

            try {
                age = Integer.parseInt(ageText);
            } catch (NumberFormatException e) {
                outputArea.append("Error: Age must be a valid number.\n");
                return;
            }

            try {
                grade = Double.parseDouble(gradeText);
            } catch (NumberFormatException e) {
                outputArea.append("Error: Grade must be a valid number.\n");
                return;
            }

            Student student = new Student(name, age, grade, studentID);

            // Check if adding the student is successful
            manager.addStudent(student); // This will print the error message in the console if ID exists
            outputArea.append("Student added successfully!\n");
        } catch (Exception e) {
            outputArea.append("Error: " + e.getMessage() + "\n");
        }
    }



    private void removeStudent() {
        try {
            String studentID = studentIDField.getText();

            // Check if student exists using getStudentByID
            Student student = manager.getStudentByID(studentID);

            if (student != null) {
                // If student exists, remove the student
                manager.removeStudent(studentID);
                outputArea.append("Student removed successfully!\n");
            } else {
                // If student does not exist, show an error message
                outputArea.append("Error: Student ID not found.\n");
            }

        } catch (Exception e) {
            outputArea.append("Error: " + e.getMessage() + "\n");
        }
    }


    private void updateStudent() {
        try {
            String studentID = studentIDField.getText();
            String name = nameField.getText();
            String ageText = ageField.getText();
            String gradeText = gradeField.getText();

            // Fetch existing student by ID
            Student existingStudent = manager.getStudentByID(studentID);

            if (existingStudent == null) {
                outputArea.append("Error: Student not found.\n");
                return;
            }

            // Only update the fields if they are not empty
            String newName = name.isEmpty() ? existingStudent.getName() : name;
            int newAge = ageText.isEmpty() ? existingStudent.getAge() : Integer.parseInt(ageText);
            double newGrade = gradeText.isEmpty() ? existingStudent.getGrade() : Double.parseDouble(gradeText);

            // Update the student with new values
            manager.updateStudent(studentID, newName, newAge, newGrade);
            outputArea.append("Student updated successfully!\n");

        } catch (Exception e) {
            outputArea.append("Error: " + e.getMessage() + "\n");
        }
    }


    private void displayAllStudents() {
        try {
            outputArea.setText(""); // Clear existing output
            ArrayList<Student> students = manager.displayAllStudents();

            // Display the column headers with proper formatting
            outputArea.append(String.format("%-15s %-15s %-15s %-15s\n", "Student ID", "Name", "Age", "Grade"));
            outputArea.append("---------------------------------------------------------------\n");

            if (students.isEmpty()) {
                outputArea.append("No students found.\n");
            } else {
                for (Student student : students) {
                    outputArea.append(String.format("%-15s %-15s %-15d %-15.2f\n",
                            student.getStudentID(),
                            student.getName(),
                            student.getAge(),
                            student.getGrade()));
                }
            }

        } catch (Exception e) {
            outputArea.append("Error: " + e.getMessage() + "\n");
        }
    }




    private void calculateAverageGrade() {
        try {
            double avgGrade = manager.calculateAverageGrade();
            outputArea.append("Average Grade: " + avgGrade + "\n");

        } catch (Exception e) {
            outputArea.append("Error: " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        new StudentManagementGUI(); // Launch the GUI
    }
}
