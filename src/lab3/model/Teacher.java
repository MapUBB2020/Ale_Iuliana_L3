package lab3.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Teacher extends Person{

    private Long teacherID;
    private List<Course> courses = new ArrayList<>(Arrays.asList());

    public Teacher() {
        super();
    }

    public Teacher(List<Course> courses, Long teacherID, String firstName, String lastName) {
        super(firstName,lastName);
        this.courses = courses;
        this.teacherID = teacherID;
    }

    public long getID() {
        return teacherID;
    }

    public void setID(Long teacherID) {
        this.teacherID = teacherID;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
