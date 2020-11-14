package lab3.view;

import lab3.model.Course;

import java.util.List;

public class TeacherView {
    public void printTeacherDetails(String teacherFirstName, String teacherLastName, List<Course> teacherCoursesEnrolled){
        System.out.println("Teacher: ");
        System.out.println("First Name: " + teacherFirstName);
        System.out.println("Last Name: " + teacherLastName);
        System.out.println("List of courses" + teacherCoursesEnrolled);
    }
}
