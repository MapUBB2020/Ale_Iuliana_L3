package lab3.model;

import java.util.List;

public class Student implements Person{
    private long studentId;
    private int totalCredits;
    //private List<Course> enrolledCourses;

    public Student(long studentId, int totalCredits) {
        this.studentId = studentId;
        this.totalCredits = totalCredits;
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




}
