package lab3.model;

import java.util.List;

public class Student extends Person{
    private long studentId;
    private int totalCredits;
    private List<Course> enrolledCourses;

    public Student(long studentId, int totalCredits, List<Course> enrolledCourses, List<Student> students, String firstName, String lastName) {
        super(firstName,lastName);
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
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
