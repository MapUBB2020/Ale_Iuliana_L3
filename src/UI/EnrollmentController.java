package UI;

import Exceptions.IncorrectFileNameException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import lab3.model.Course;
import lab3.model.Student;

import java.io.IOException;
import java.util.List;
import java.util.Observable;

public class EnrollmentController extends Observable {
    public VBox enrollmentPage;
    public static Student studentLogged;
    private final TeacherPlatform teacherPlatform = new TeacherPlatform();

    @FXML
    void initialize() {
        List<Course> courses = Controller.registrationSystem.getCourseRepository().findAll();
        for (Course course: courses) {
            Label courseLabel = new Label("Course: " + course.getName());
            courseLabel.setTranslateX(20);
            courseLabel.setTranslateY(20);
            courseLabel.setStyle("-fx-font:18 system");
            courseLabel.setStyle("-fx-font-weight:bold ");
            courseLabel.setPadding(new Insets(10,0,10,0));
            enrollmentPage.getChildren().add(courseLabel);
            Label teacherLabel = new Label("Teacher: " + course.getTeacher().getFirstName()
                    + " " + course.getTeacher().getLastName());
            teacherLabel.setTranslateX(20);
            teacherLabel.setTranslateY(20);
            enrollmentPage.getChildren().add(teacherLabel);
            Label creditsLabel = new Label("Credits: " + String.valueOf(course.getCredits()) + " credits");
            creditsLabel.setTranslateX(20);
            creditsLabel.setTranslateY(20);
            enrollmentPage.getChildren().add(creditsLabel);
            Button enrollButton = new Button();
            enrollButton.setTranslateX(50);
            enrollButton.setTranslateY(20);
            enrollButton.setText("ENROLL");
            addObserver(teacherPlatform);
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
                        Label notEnrolledLabel = new Label("Could not enroll!");
                        notEnrolledLabel.setTextFill(Paint.valueOf("red"));
                        notEnrolledLabel.setStyle("-fx-font-weight: bold");
                        notEnrolledLabel.setPadding(new Insets(60,0,0,400));
                        enrollmentPage.getChildren().add(notEnrolledLabel);
                    }
                    enrollButton.setDisable(true);
                }
            });
            enrollmentPage.getChildren().add(enrollButton);
        }
    }
}
