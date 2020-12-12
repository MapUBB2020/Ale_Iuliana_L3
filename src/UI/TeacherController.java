package UI;

import Exceptions.IncorrectFileNameException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import java.io.IOException;
import java.text.ParseException;


public class TeacherController {
    public static Controller controller;
    public static String teacherIdText;
    public static String firstNameText;
    public static String lastNameText;
    public static String coursesInfo;

    @FXML
    public Button loginButton;
    @FXML
    public TextField teacherID;
    @FXML
    public TextField firstName;
    @FXML
    public TextField lastName;
    @FXML
    public Label validation;

    @FXML
    public void initialize() {
        validation.setVisible(false);
    }

    @FXML
    public void changeSceneOfTeacherLogin(ActionEvent actionEvent) throws IOException, IncorrectFileNameException, ParseException {
        teacherIdText = teacherID.getText();
        firstNameText = firstName.getText().toString();
        lastNameText = lastName.getText().toString();

        //in initialise() se fac citirile pt fiecare obiect, dupa care fac legaturile intre clase
        Controller.studentRepository.initialise();
        Controller.courseRepository.initialise();
        Controller.teacherRepository.initialise();
        Controller.teacherRepository.setRelations(Controller.courseRepository);
        Controller.courseRepository.changeCourse();
        Controller.courseRepository.setRelations(Controller.studentRepository);

        coursesInfo = "";
        Teacher foundTeacher = new Teacher();
        boolean found = false;
        for (Teacher teacher : Controller.teacherRepository.teachers)
            if (String.valueOf(teacher.getID()).equals(teacherIdText) && teacher.getFirstName().equals(firstNameText) && teacher.getLastName().equals(lastNameText)) {
                found = true;
                foundTeacher = teacher;
            }
        if (found) {
            for (Course course : foundTeacher.getCourses()) {
                coursesInfo = "Course: " + coursesInfo + "\t" + course.getName() + "\n";
                if (course.getStudentsEnrolled().size() == 0) {
                    coursesInfo += "No students";
                    break;
                }
                coursesInfo += "Enrolled students: \n";
                int i = 1;
                for (Student student : course.getStudentsEnrolled()) {
                    coursesInfo = coursesInfo + "\t" + i + ". " + student.firstName + " " + student.lastName + "\n";
                    i++;
                }
            }
            Parent finishRoot = FXMLLoader.load(getClass().getResource("teacherPlatform.fxml"));
            Scene teacherPlatform = new Scene(finishRoot);
            Main.primaryStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Main.primaryStage.setScene(teacherPlatform);
            Main.primaryStage.show();
        }

        if (!found)
        {
            validation.setVisible(true);
            validation.setText("Either ID, first name or last name is incorrect!");
        }


    }
}
