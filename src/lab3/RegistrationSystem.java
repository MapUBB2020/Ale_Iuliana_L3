package lab3;

import lab3.model.Course;
import lab3.model.Student;

import java.util.List;

public class RegistrationSystem {
    public void register(Course course, Student student) {
        if (course.getMaxEnrollment() == course.getStudentsEnrolled().size()) {
            List<Course> enrolledCourses = student.getEnrolledCourses();
            enrolledCourses.add(course);
            student.setEnrolledCourses(enrolledCourses);

            int credits = student.getTotalCredits();
            credits += course.getCredits();
            student.setTotalCredits(credits);
        } else {
            System.out.println("Maximum number of students reached! Please choose another course.");
        }
    }
}
