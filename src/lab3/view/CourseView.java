package lab3.view;

import lab3.model.Person;
import lab3.model.Student;

import java.util.List;

public class CourseView {
    public void printCourseDetails(String courseName, Person courseTeacher, int courseMaxEnrollment, List<Student> courseStudentsEnrolled, int courseCredits) {
        System.out.println("Course: ");
        System.out.println("Name: " + courseName);
        System.out.println("Teacher: " + courseTeacher.firstName + " " + courseTeacher.lastName);
        System.out.println("Max number of students: " + courseMaxEnrollment);
        System.out.println("Students enrolled: " + courseStudentsEnrolled);
        System.out.println("Credits: " + courseCredits);
    }
}
