package JSONParser;

import lab3.model.Person;
import lab3.model.Student;

import java.util.List;

/**
 * We created another class for course that has an id instead of the object teacher
 * The studentsEnrolled list will be created in the CourseRepository when we set the relations
 */
public class CourseId {
    private Long id;
    private String name;
    private Long teacherId;
    private int maxEnrollment;
    private int credits;

    public CourseId() {
    }

    public CourseId(Long id, String name, Long teacherId, int maxEnrollment, List<Long> studentsEnrolled, int credits) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.maxEnrollment = maxEnrollment;
        this.credits = credits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacher() {
        return teacherId;
    }

    public void setTeacher(Long teacherId) {
        this.teacherId = teacherId;
    }

    public int getMaxEnrollment() {
        return maxEnrollment;
    }

    public void setMaxEnrollment(int maxEnrollment) {
        this.maxEnrollment = maxEnrollment;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
