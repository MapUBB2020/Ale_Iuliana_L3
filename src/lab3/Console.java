package lab3;

import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.controller.TeacherController;

import java.util.Scanner;

public class Console {
    RegistrationSystem regsys;
    CourseController courseController;
    StudentController studentController;
    TeacherController teacherController;

    public Console(RegistrationSystem regsys,
                   CourseController courseController,
                   StudentController studentController,
                   TeacherController teacherController) {
        this.regsys = regsys;
        this.courseController = courseController;
        this.studentController = studentController;
        this.teacherController = teacherController;
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
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
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
