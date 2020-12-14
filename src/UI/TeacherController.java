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
import java.util.Observable;
import java.util.Observer;


public class TeacherController implements Observer {
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

        coursesInfo = "";
        Teacher foundTeacher = new Teacher();
        boolean found = false;
        for (Teacher teacher : Controller.registrationSystem.getTeacherRepository().teachers)
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
            Controller.windowTeacher = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Controller.windowTeacher.setScene(teacherPlatform);
            Controller.windowTeacher.show();
        }

        if (!found)
        {
            validation.setVisible(true);
            validation.setText("Either ID, first name or last name is incorrect!");
        }


    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Done");
    }
}
