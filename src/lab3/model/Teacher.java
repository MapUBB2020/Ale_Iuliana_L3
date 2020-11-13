package lab3.model;

import java.util.List;

public class Teacher implements Person{
    private List<Course> courses;

    public Teacher(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
