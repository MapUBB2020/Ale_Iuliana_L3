package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.util.Observable;
import java.util.Observer;

public class TeacherPlatform implements Observer {
    @FXML
    public Label IDTeacher;
    @FXML
    public Label teacherName;
    @FXML
    public Label coursesInfo;
    @FXML
    public AnchorPane mainPane;

    @FXML
    public void initialize() {
        IDTeacher.setText("Your university ID: " + TeacherController.teacherIdText);
        teacherName.setText(TeacherController.lastNameText + " " + TeacherController.firstNameText);
        coursesInfo.setText(String.valueOf(TeacherController.coursesInfo));
      }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Done");
    }
}
