package lab3.controller;

import lab3.model.Course;
import lab3.model.Person;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.view.CourseView;

import java.util.List;

public class CourseController {
    private Course model;
    private CourseView view;

    public CourseController(Course model, CourseView view) {
        this.model = model;
        this.view = view;
    }

    public Long getCourseId() {
        return model.getId();
    }

    public void setCourseId(Long id) {
        model.setId(id);
    }

    public String getCourseName() {
        return model.getName();
    }

    public void setCourseName(String name) {
        model.setName(name);
    }

    public Teacher getCourseTeacher() {
        return model.getTeacher();
    }

    public void setCourseTeacher(Teacher teacher) {
        model.setTeacher(teacher);
    }

    public int getCourseMaxEnrollment() {
        return model.getMaxEnrollment();
    }

    public void setCourseMaxEnrollment(int maxEnrollment) {
        model.setMaxEnrollment(maxEnrollment);
    }

    public List<Student> getCourseStudentsEnrolled() {
        return model.getStudentsEnrolled();
    }

    public void setCourseStudentsEnrolled(List<Student> studentsEnrolled) {
        model.setStudentsEnrolled(studentsEnrolled);
    }

    public int getCourseCredits() {
        return model.getCredits();
    }

    public void setCourseCredits(int credits) {
        model.setCredits(credits);
    }

    public void updateView() {
        view.printCourseDetails(model.getName(), model.getTeacher(), model.getMaxEnrollment(), model.getStudentsEnrolled(), model.getCredits());
    }
}
