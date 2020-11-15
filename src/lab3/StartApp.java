package lab3;

import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentRepository();
        TeacherRepository teacherRepository = new TeacherRepository();
        CourseRepository courseRepository = new CourseRepository();

        //repos.initialise_data();

        RegistrationSystem registrationSystem = new RegistrationSystem(courseRepository,
                studentRepository,
                teacherRepository);

        Console console = new Console(registrationSystem);
        console.run();
    }
}
