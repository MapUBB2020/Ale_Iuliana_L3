package lab3.controller;

import lab3.model.Course;
import lab3.model.Student;
import lab3.view.StudentView;

import java.util.List;

public class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    public String getStudentFirstName() { return model.getFirstName(); }

    public void setStudentFirstName(String firstName) {
        model.setFirstName(firstName);
    }

    public String getStudentLastName() {
        return model.getLastName();
    }

    public void setStudentLastName(String lastName) {
        model.setLastName(lastName);
    }

    public long getStudentId() {
        return model.getId();
    }

    public void setStudentId(long studentId) {
        model.setId(studentId);
    }

    public int getStudentTotalCredits() {
        return model.getTotalCredits();
    }

    public void setStudentTotalCredits(int totalCredits) {
        model.setTotalCredits(totalCredits);
    }

    public List<Course> getStudentEnrolledCourses() {

        return model.getEnrolledCourses();
    }
    public void setStudentEnrolledCourses(List<Course> enrolledCourses) {
        model.setEnrolledCourses(enrolledCourses);
    }

    public void updateStudentView(){
        view.printStudentDetails(model.getFirstName(), model.getLastName(), model.getTotalCredits(), model.getEnrolledCourses());
    }
}
