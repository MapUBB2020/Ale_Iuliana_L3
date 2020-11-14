package lab3.model;

import java.util.List;

public class Student implements Person{
    private long studentId;
    private int totalCredits;
    private static List<Course> enrolledCourses;
    private static List<Student> students;


    public Student(long studentId, int totalCredits, List<Course> enrolledCourses, List<Student> students) {
        this.studentId = studentId;
        this.totalCredits = totalCredits;
        this.enrolledCourses = enrolledCourses;
        this.students = students;
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

    public static List<Student> getStudents() {
        return students;
    }
    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString(){
        String string = "";
        string = this.studentId + " " + this.totalCredits;
        for (Course course : enrolledCourses)
            string += course.getName() + ", ";
        return string;
    }
}
