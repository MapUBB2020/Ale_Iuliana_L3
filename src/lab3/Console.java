package lab3;

import Exceptions.WrongInput;
import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.controller.TeacherController;
import lab3.model.Course;
import lab3.model.Person;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.view.CourseView;
import lab3.view.StudentView;
import lab3.view.TeacherView;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    public void getStudentData(Long IdStudent){
        Scanner scanner = new Scanner(System.in);
        //Student student = new Student();

        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        studentController.setStudentId(IdStudent);
        studentController.setStudentFirstName(firstName);
        studentController.setStudentLastName(lastName);
        //List<Course> coursesStudent = new ArrayList<Course>();
        //studentController.setStudentEnrolledCourses(coursesStudent);

        //return student;
    }

    /**
     * The menu for the application
     */
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
            System.out.println("6.Filter courses by teacher.");
            System.out.println("7.Sort courses by number of credits.");
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Choose option:");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 0:
                        run = false;
                        break;
                    case 1: {
                        Student student = new Student();
                        StudentView studentView = new StudentView();
                        studentController = new StudentController(student, studentView);
                        System.out.println("Info about Student");

                        try {
                            System.out.println("Id: ");
                            Long IdStudent = scanner.nextLong();

                            List<Student> allStudents = (List<Student>) regsys.getStudentRepository().findAll();
                            if (allStudents.size() == 0) {
                                getStudentData(IdStudent);
                            } else {
                                for (Student stud : allStudents) {
                                    if (stud.getId().equals(IdStudent)) {
                                        student = stud; //stud(eC: bd, sda)
                                    } else {
                                        getStudentData(IdStudent);
                                    }
                                }
                            }
                        } catch (InputMismatchException e) {
                            throw new WrongInput("The student id should be of type long");
                        }

                        System.out.println("Info about Course");
                        try {
                            System.out.println("Id: ");
                            Long IdCourse = scanner.nextLong();
                            regsys.register(IdCourse, student);
                        }catch (InputMismatchException e){
                            throw new WrongInput("The course id should be of type long");
                        }

                        break;
                    }
                    case 2: {

                        List<Course> list = regsys.retrieveCoursesWithFreePlaces();
                        for (Course course : list) {
                            int nrCredits = course.getMaxEnrollment() - course.getStudentsEnrolled().size();
                            System.out.println(course.getName() + " : " + nrCredits);
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Course details");
                        System.out.println("Id: ");
                        Long courseId = scanner.nextLong();
                        List<Student> students = regsys.retrieveStudentsEnrolledForACourse(courseId);
                        for (Student student : students) {
                            System.out.println(student.getFirstName());
                            System.out.println(student.getLastName());
                        }
                        break;
                    }
                    case 4: {
                        List<Course> allCourses = regsys.getAllCourses();
                        for (Course course : allCourses) {
                            CourseView courseView = new CourseView();
                            courseController = new CourseController(course, courseView);
                            courseView.printCourseDetails(courseController.getCourseName(),
                                    courseController.getCourseTeacher(),
                                    courseController.getCourseMaxEnrollment(),
                                    courseController.getCourseStudentsEnrolled(),
                                    courseController.getCourseCredits()
                            );
                        }
                        //System.out.println(regsys.getAllCourses());
                        break;
                    }
                    case 5: {
                        System.out.println("Teacher Id: ");
                        Long teacherId = scanner.nextLong();
                        System.out.println("Course Id: ");
                        Long courseIdNew = scanner.nextLong();
                        regsys.deleteCourse(teacherId, courseIdNew);
                        break;
                    }
                    case 6: {
                        System.out.println("First Name of teacher: ");
                        String firstName = scanner.nextLine();
                        System.out.println("Last Name of teacher:");
                        String lastName = scanner.nextLine();
                        Person teacher = new Person(firstName, lastName);
                        List<Course> sortedCourses = regsys.filterByTeacher(teacher);
                        for (Course course : sortedCourses) {
                            CourseView courseView = new CourseView();
                            courseController = new CourseController(course, courseView);
                            courseView.printCourseDetails(courseController.getCourseName(),
                                    courseController.getCourseTeacher(),
                                    courseController.getCourseMaxEnrollment(),
                                    courseController.getCourseStudentsEnrolled(),
                                    courseController.getCourseCredits()
                            );
                        }
                        break;
                    }
                    case 7: {
                        List<Course> filteredCourses = regsys.sortByCredits();
                        for (Course course : filteredCourses) {
                            CourseView courseView = new CourseView();
                            courseController = new CourseController(course, courseView);
                            courseView.printCourseDetails(courseController.getCourseName(),
                                    courseController.getCourseTeacher(),
                                    courseController.getCourseMaxEnrollment(),
                                    courseController.getCourseStudentsEnrolled(),
                                    courseController.getCourseCredits()
                            );
                        }
                        break;
                    }
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
