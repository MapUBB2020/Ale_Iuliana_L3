package UI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
    public AnchorPane mainPane;

    public static Teacher teacherLogged;

    @FXML
    public void initialize() {
        IDTeacher.setText("Your university ID: " + TeacherController.teacherIdText);
        teacherName.setText(TeacherController.lastNameText + " " + TeacherController.firstNameText);
        coursesInfo.setText(String.valueOf(TeacherController.coursesInfo));
      }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Done");
        String coursesInfoString = "";
        for (Course course : teacherLogged.getCourses()) {
            coursesInfoString = "Course: " + coursesInfo + "\t" + course.getName() + "\n";
            if (course.getStudentsEnrolled().size() == 0) {
                coursesInfoString += "No students";
                break;
            }
            coursesInfoString += "Enrolled students: \n";
            int i = 1;
            for (Student student : course.getStudentsEnrolled()) {
                coursesInfoString = coursesInfoString + "\t" + i + ". " + student.firstName + " " + student.lastName + "\n";
                i++;
            }
            mainPane.getChildren().add(new Label(coursesInfoString));
        }
        //coursesInfo.setText(coursesInfoString);
    }
}

//package UI;
//
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.AnchorPane;
//import lab3.model.Teacher;
//
//import java.util.Observable;
//import java.util.Observer;
//
//public class TeacherPlatform implements Observer {
//    TeacherController teacherController;
//    @FXML
//    public Label IDTeacher;
//    @FXML
//    public Label teacherName;
//    @FXML
//    public Label coursesInfo;
//    @FXML
//    public AnchorPane mainPane;
//
//    public void initialize() {
//        teacherController = new TeacherController();
//        mainPane.getChildren().add(new Label(teacherController.teacherID.getText()));
//        mainPane.getChildren().add(new Label(teacherController.firstName.getText()));
//        mainPane.getChildren().add(new Label(TeacherController.coursesInfo));
//
//    }
//
//    @Override
//    public void update(Observable o, Object arg) {
//        mainPane.getChildren().add()
//    }
//}
