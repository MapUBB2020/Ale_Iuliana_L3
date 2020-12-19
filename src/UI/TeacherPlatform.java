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

    public static Label staticCoursesInfo = new Label();
    public static Teacher teacherLogged = new Teacher();

    /**
     * Show teacher data and the info for the teacher's courses
     */
    @FXML
    public void initialize() {
        staticCoursesInfo = coursesInfo;
        IDTeacher.setText("Your university ID: " + TeacherController.teacherIdText);
        teacherName.setText(TeacherController.lastNameText + " " + TeacherController.firstNameText);
        showCoursesInfo();
    }

    /**
     * When an update is being made in the student platform, the teacher platform updates automatically
     * @param o the teacher platform, which is the observable
     */
    @Override
    public void update(Observable o, Object arg) {
        showCoursesInfo();
    }

    /**
     * Show students enrolled to teacher's courses
     */
    public void showCoursesInfo() {
        StringBuilder coursesInfo = new StringBuilder();
        for (Course course: teacherLogged.getCourses()) {
            coursesInfo.append("Course: " + "\t").append(course.getName()).append("\n");
            if (course.getStudentsEnrolled().size() == 0) {
                coursesInfo.append("No students");
                break;
            }
            coursesInfo.append("Enrolled students: \n");
            int i = 1;
            for (Student student : course.getStudentsEnrolled()) {
                coursesInfo.append("\t").append(i).append(". ").append(student.firstName).append(" ").append(student.lastName).append("\n");
                i++;
            }
        }
        staticCoursesInfo.setText(coursesInfo.toString());
    }
}
