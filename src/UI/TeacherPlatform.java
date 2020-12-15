package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

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
    public VBox mainPane;

    public static Label staticCoursesInfo;
    public static Teacher teacherLogged;

    @FXML
    public void initialize() {
        staticCoursesInfo = coursesInfo;
        IDTeacher.setText("Your university ID: " + TeacherController.teacherIdText);
        teacherName.setText(TeacherController.lastNameText + " " + TeacherController.firstNameText);
        showCoursesInfo();
    }

    @Override
    public void update(Observable o, Object arg) {
        showCoursesInfo();
    }

    public void showCoursesInfo() {
        String coursesInfo = "";
        for(Course course :teacherLogged.getCourses()) {
            coursesInfo += "Course: " + "\t" + course.getName() + "\n";
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
        staticCoursesInfo.setText(coursesInfo);
    }
}
