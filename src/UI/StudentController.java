package UI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lab3.model.Student;

import java.io.IOException;
import java.util.List;

public class StudentController {
    @FXML
    Button loginButton;
    @FXML
    TextField firstNameLogin;
    @FXML
    TextField lastNameLogin;
    @FXML
    Label invalidInput;

    @FXML
    void initialize() {
        invalidInput.setVisible(false);
    }

    /**
     * Get the user's input from the text fields and search for the student in repo,
     * if valid, change the scene to show student info
     */
    @FXML
    public void login() {
        String firstName = firstNameLogin.getText();
        String lastName = lastNameLogin.getText();

        List<Student> students = Controller.registrationSystem.getStudentRepository().students;
        students.forEach(student -> {
            if (student.getFirstName().equals(firstName) && student.getLastName().equals(lastName)) {
                EnrollmentController.studentLogged = new Student(firstName,
                        lastName,
                        student.getId(),
                        student.getTotalCredits(),
                        student.getEnrolledCourses());
                Parent studentLogin = null;
                try {
                    studentLogin = FXMLLoader.load(getClass().getResource("studentLogin.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Controller.windowStudent.setTitle("Student");
                assert studentLogin != null;
                Controller.windowStudent.setScene(new Scene(studentLogin));
                Controller.windowStudent.show();
            }
        });
        invalidInput.setVisible(true);
    }
}
