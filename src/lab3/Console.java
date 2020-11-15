package lab3;

import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.controller.TeacherController;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.view.CourseView;
import lab3.view.StudentView;
import lab3.view.TeacherView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
    RegistrationSystem regsys;
    CourseController courseController;
    StudentController studentController;
    TeacherController teacherController;

    public Console(RegistrationSystem regsys) {
        this.regsys = regsys;
    }

    public void run() {
        boolean run = true;
        while (run) {
            System.out.println("Please choose one:");
            System.out.println("0.Quit");
            System.out.println("1.Register a student to a course.");
            System.out.println("2.Show courses that have free places and how many.");
            System.out.println("3.Show students who enrolled to a specific course.");
            System.out.println("4.Show all courses.");
            System.out.println("5.Delete a course for a teacher and the students enrolled.");
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Choose option:");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        run = false;
                        break;
                    case 1: {
                        System.out.println("Info about Student");
                        Student student = new Student();
                        System.out.println("Id: ");
                        Long IdStudent = scanner.nextLong();
                        for (Student stud : regsys.getStudentRepository().findAll()) {
                            if (stud.getId().equals(IdStudent)) {
                                student = stud; //stud(eC: bd, sda)
                            } else {
                                System.out.println("First Name: ");
                                String firstName = scanner.nextLine();
                                System.out.println("Last Name:");
                                String lastName = scanner.nextLine();
                                System.out.println("Number of credits: ");
                                int nrCredits = scanner.nextInt();
                                student.setId(IdStudent);
                                student.setFirstName(firstName);
                                student.setLastName(lastName);
                                student.setTotalCredits(nrCredits);
                                List<Course> coursesStudent = new ArrayList<Course>(); //???
                                student = new Student(IdStudent, nrCredits, coursesStudent, firstName, lastName);
                            }
                        }

                        System.out.println("Info about Course");
                        System.out.println("Id: ");
                        Long IdCourse = scanner.nextLong();

                        regsys.register(IdCourse, student);
                        break;
                    }
                    case 2:
                        regsys.retrieveCoursesWithFreePlaces();
                        break;
                    case 3:
                        System.out.println("Course details");
                        System.out.println("Id: ");
                        Long courseId = scanner.nextLong();
                        regsys.retrieveStudentsEnrolledForACourse(courseId);
                        break;
                    case 4:
                        regsys.getAllCourses();
                        break;
                    case 5:
                        System.out.println("Teacher Id: ");
                        Long teacherId = scanner.nextLong();
                        System.out.println("Course Id: ");
                        Long courseIdNew = scanner.nextLong();
                        regsys.deleteCourse(teacherId, courseIdNew);
                        break;
                    default:
                        System.out.println("Option doesn't exist. Choose another.");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

}
