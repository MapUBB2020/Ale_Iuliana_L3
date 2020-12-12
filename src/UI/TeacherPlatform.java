package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TeacherPlatform {
    @FXML
    public Label IDTeacher;
    @FXML
    public Label teacherName;
    @FXML
    public Label coursesInfo;

    @FXML
    public void initialize() {
        IDTeacher.setText("Your university ID: " + TeacherController.teacherIdText);
        teacherName.setText(TeacherController.lastNameText + " " + TeacherController.firstNameText);
        coursesInfo.setText(String.valueOf(TeacherController.coursesInfo));
      }
}
