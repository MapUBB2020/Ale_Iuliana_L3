package lab3.view;

import lab3.model.Course;

import java.util.List;

public class StudentView {
    public void printStudentDetails(String studentFirstName, String studentLastName, int studentTotalCredits, List<Course> studentsCoursesEnrolled){
        System.out.println("Student: ");
        System.out.println("First Name: " + studentFirstName);
        System.out.println("Last Name: " + studentLastName);
        System.out.println("Total credits: " + studentTotalCredits);
        System.out.println("List of courses" + studentsCoursesEnrolled);
    }

}
