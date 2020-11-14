package lab3.controller;

import lab3.model.Course;
import lab3.model.Teacher;
import lab3.view.TeacherView;

import java.util.List;

public class TeacherController {
    private Teacher model;
    private TeacherView view;

    public String getTeacherFirstName() { return model.getFirstName(); }

    public void setTeacherFirstName(String firstName) {
        model.setFirstName(firstName);
    }

    public String getTeacherLastName() {
        return model.getLastName();
    }

    public void setTeacherLastName(String lastName) {
        model.setLastName(lastName);
    }

    public long getTeacherID() {
        return model.getID();
    }

    public void setTeacherID(long teacherID) {
        model.setID(teacherID);
    }

    public List<Course> getTeacherCourses() {
        return model.getCourses();
    }

    public void setTeacherCourses(List<Course> courses) {
        model.setCourses(courses);
    }

    public void updateTeacherView(){
        view.printTeacherDetails(model.getFirstName(), model.getLastName(), model.getCourses());
    }

}
