package lab3;

import Exceptions.WrongInput;
import lab3.controller.CourseController;
import lab3.controller.StudentController;
import lab3.model.Course;
import lab3.model.Person;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.view.CourseView;
import lab3.view.StudentView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Console {
    RegistrationSystem regsys;
    CourseController courseController;
    StudentController studentController;

    public Console(RegistrationSystem regsys) {
        this.regsys = regsys;
    }

    public void getStudentData(Long IdStudent) throws WrongInput {
        Scanner scanner = new Scanner(System.in);

        System.out.println("First Name: ");
        String firstName = scanner.nextLine();
        studentController.setStudentFirstName(firstName);
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        studentController.setStudentLastName(lastName);
        studentController.setStudentId(IdStudent);
    }

    public boolean courseExists(Long courseId){
        List<Course> courses= regsys.getAllCourses();
        for (Course course: courses) {
            if (course.getId().equals(courseId))
                return true;
        }
        return false;
    }

    public boolean teacherExists(Long teacherID){
        List<Teacher> teachers= regsys.getTeacherRepository().teachers;
        for (Teacher teacher: teachers) {
            if (teacher.getID() == (teacherID))
                return true;
        }
        return false;
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
                                    }
                                }
                                getStudentData(IdStudent);
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
                        try {
                            System.out.println("Id: ");
                            Long courseId = scanner.nextLong();
                            if (!courseExists(courseId))
                            {
                                System.out.println("The course with the given id does nt exist");
                                break;
                            }
                            List<Student> students = regsys.retrieveStudentsEnrolledForACourse(courseId);
                            for (Student student : students) {
                                System.out.println(student.getFirstName());
                                System.out.println(student.getLastName());
                            }
                        }
                        catch (InputMismatchException e) {
                            throw new WrongInput("Id should be of type int.");
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
                        try {
                            System.out.println("Teacher Id: ");
                            Long teacherId = scanner.nextLong();
                            if (!teacherExists(teacherId)){
                                System.out.println("The teacher with the given id does not exist");
                                break;
                            }
                            System.out.println("Course Id: ");
                            Long courseIdNew = scanner.nextLong();
                            if (!courseExists(teacherId)){
                                System.out.println("The course with the given id does not exist");
                                break;
                            }
                            regsys.deleteCourse(teacherId, courseIdNew);
                        } catch (InputMismatchException e) {
                            throw new WrongInput("Teacher id and course id should be both of type int.");
                        }
                        break;
                    }
                    case 6: {
                        Scanner scanner1 = new Scanner(System.in);
                        System.out.println("First Name of teacher: ");
                        String firstName = scanner1.nextLine();
                        System.out.println("Last Name of teacher:");
                        String lastName = scanner1.nextLine();
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
