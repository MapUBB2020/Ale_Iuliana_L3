package UI;

import Exceptions.IncorrectFileNameException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lab3.model.Course;
import lab3.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

public class EnrollmentController extends Observable {
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
                    boolean isRegistered = Controller.registrationSystem.register(course.getId(), studentLogged);
                    if (isRegistered) {
                        try {
                            Controller.registrationSystem.writeToJson();
                            setChanged();
                            notifyObservers();
                        } catch (IOException | IncorrectFileNameException e) {
                            e.printStackTrace();
                        }
                    } else {
                        enrollmentPage.getChildren().add(new Label("Could not enroll!"));
                    }
                    enrollButton.setDisable(true);
                }
            });
            enrollmentPage.getChildren().add(enrollButton);
        }
    }
}
