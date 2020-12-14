package lab3.model;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private Long studentId;
    private int totalCredits = 0;
    private List<Course> enrolledCourses = new ArrayList<Course>();;

    public Student(){
        super();
    }
    public Student(Long studentId, String firstName, String lastName) {
        super(firstName,lastName);
        this.studentId = studentId;
        //this.enrolledCourses = enrolledCourses;
    }

    public Student(String firstName, String lastName, Long studentId, int totalCredits, List<Course> enrolledCourses) {
        super(firstName, lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
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

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }
    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }


}
