package UI;

import Exceptions.IncorrectFileNameException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lab3.RegistrationSystem;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.io.IOException;
import java.text.ParseException;

public class Controller {
    Stage windowStudent = new Stage();
    Stage windowTeacher = new Stage();
    @FXML
    Button studentButton;
    @FXML
    Button teacherButton;

    static public RegistrationSystem registrationSystem;

    @FXML
    void initialize() throws ParseException, IncorrectFileNameException, IOException {
        TeacherRepository teacherRepository = new TeacherRepository();

        teacherRepository.initialise();

        CourseRepository courseRepository = new CourseRepository(teacherRepository.teachers);
        courseRepository.initialise();

        StudentRepository studentRepository = new StudentRepository(courseRepository.courseRepo);
        try {
            studentRepository.initialise();
        } catch (Exception NullPointerException) {
            System.out.println("No students");
        }

        courseRepository.setRelations(studentRepository);
        teacherRepository.setRelations(courseRepository);

        registrationSystem = new RegistrationSystem(courseRepository,
                studentRepository,
                teacherRepository);
    }

    @FXML
    public void openWindowForStudent() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentRegistration.fxml"));
        windowStudent.setTitle("Anwendung Universitat");
        windowStudent.setScene(new Scene(root));
        windowStudent.show();
    }

    @FXML
    public void openWindowForTeacher() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("teacherLogin.fxml"));
        windowTeacher.setTitle("Anwendung Universitat");
        windowTeacher.setScene(new Scene(root));
        windowTeacher.show();
    }

}
