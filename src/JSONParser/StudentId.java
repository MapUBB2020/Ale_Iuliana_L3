package JSONParser;

import lab3.model.Course;
import lab3.model.Person;

import java.util.ArrayList;
import java.util.List;

public class StudentId extends Person {
    private Long studentId;
    private int totalCredits = 0;
    private List<Long> enrolledCourses = new ArrayList<>();

    public StudentId(){
        super();
    }
    public StudentId(Long studentId, String firstName, String lastName) {
        super(firstName,lastName);
        this.studentId = studentId;
        //this.enrolledCourses = enrolledCourses;
    }

    public Long getId() {
        return studentId;
    }

    public void setId(Long studentId) {
        this.studentId = studentId;
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }

    public List<Long> getEnrolledCourses() {
        return enrolledCourses;
    }
    public void setEnrolledCourses(List<Long> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

}
