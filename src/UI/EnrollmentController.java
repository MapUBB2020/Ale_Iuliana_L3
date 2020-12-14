package UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lab3.model.Course;
import lab3.model.Student;

import java.util.List;

public class EnrollmentController {
    public VBox enrollmentPage;
    public static Student studentLogged;

    @FXML
    void initialize() {
        List<Course> courses = Controller.registrationSystem.getCourseRepository().findAll();
        for (Course course: courses) {
            enrollmentPage.getChildren().add(new Label(course.getName()));
            enrollmentPage.getChildren().add(new Label(course.getTeacher().getFirstName()
                    + " " + course.getTeacher().getLastName()));
            enrollmentPage.getChildren().add(new Label(String.valueOf(course.getCredits()) + " credits"));
            Button enrollButton = new Button();
            enrollButton.setText("ENROLL");
            enrollButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Controller.registrationSystem.register(course.getId(), studentLogged);
                }
            });
            enrollmentPage.getChildren().add(enrollButton);
        }
    }
}
