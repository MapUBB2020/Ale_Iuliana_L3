package JSONParser;

import lab3.model.Course;
import lab3.model.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherId extends Person{
    private Long teacherID;
    private List<Long> courses = new ArrayList<>(Arrays.asList());

    public TeacherId() {
        super();
    }

    public TeacherId(List<Long> courses, Long teacherID, String firstName, String lastName) {
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

    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }
}
