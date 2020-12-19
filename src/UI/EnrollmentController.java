package UI;

import Exceptions.IncorrectFileNameException;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
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
    private final TeacherPlatform teacherPlatform = new TeacherPlatform();

    @FXML
    Label notEnrolledLabel;
    @FXML
    Label studentName;
    @FXML
    Label creditsNumber;

    /**
     * Show all courses with their specific teacher and credits + button for enrollment
     * When the enrollment button is clicked and the student can actually enroll to that course,
     * it gets written to the JSON file and the teacher platform is updated.
     */
    @FXML
    void initialize() {
        studentName.setText(studentLogged.getFirstName() + " " + studentLogged.getLastName());
        creditsNumber.setText(studentLogged.getTotalCredits() + "credits");
        notEnrolledLabel.setVisible(false);
        List<Course> courses = Controller.registrationSystem.getCourseRepository().findAll();
        for (Course course: courses) {
            Label courseLabel = new Label("Course: " + course.getName());

            //styling for course label
            courseLabel.setTranslateX(20);
            courseLabel.setTranslateY(20);
            courseLabel.setStyle("-fx-font:18 system");
            courseLabel.setStyle("-fx-font-weight:bold ");
            courseLabel.setPadding(new Insets(10,0,10,0));
            //end styling

            enrollmentPage.getChildren().add(courseLabel);

            Label teacherLabel = new Label("Teacher: " + course.getTeacher().getFirstName()
                    + " " + course.getTeacher().getLastName());

            //styling for teacher label
            teacherLabel.setTranslateX(20);
            teacherLabel.setTranslateY(20);
            //end styling

            enrollmentPage.getChildren().add(teacherLabel);

            Label creditsLabel = new Label("Credits: " + String.valueOf(course.getCredits()) + " credits");

            //styling for credits label
            creditsLabel.setTranslateX(20);
            creditsLabel.setTranslateY(20);
            //end styling

            enrollmentPage.getChildren().add(creditsLabel);
            Button enrollButton = new Button();

            //styling for enroll button
            enrollButton.setTranslateX(50);
            enrollButton.setTranslateY(20);
            enrollButton.setText("ENROLL");
            //end styling

            //make the teacher platform the observer
            addObserver(teacherPlatform);
            enrollButton.setOnAction(event -> {
                notEnrolledLabel.setVisible(false);
                boolean isRegistered = Controller.registrationSystem.register(course.getId(), studentLogged);
                if (isRegistered) {
                    try {
                        creditsNumber.setText(studentLogged.getTotalCredits() + "credits");
                        Controller.registrationSystem.writeToJson();
                        setChanged();
                        notifyObservers();
                    } catch (IOException | IncorrectFileNameException e) {
                        e.printStackTrace();
                    }
                } else {
                    notEnrolledLabel.setVisible(true);
                }
                enrollButton.setDisable(true);
            });
            enrollmentPage.getChildren().add(enrollButton);
        }
    }
}
