package lab3;

import JSONParser.CourseDataReader;
import JSONParser.StudentDataWriter;
import JSONParser.TeacherDataReader;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.io.IOException;
import java.text.ParseException;

/**
 * Main class where program starts.
 */
public class StartApp {

    /**
     * Start point of the application
     * @param args command line arguments
     */
    public static void main(String[] args) throws IOException, ParseException, NullPointerException {
        StudentRepository studentRepository = new StudentRepository();
        TeacherRepository teacherRepository = new TeacherRepository();
        CourseRepository courseRepository = new CourseRepository();

        try {
            studentRepository.initialise();
        } catch (Exception NullPointerException) {
            System.out.println("No students");
        }
        teacherRepository.initialise();
        courseRepository.initialise();


        RegistrationSystem registrationSystem = new RegistrationSystem(courseRepository,
                studentRepository,
                teacherRepository);

        Console console = new Console(registrationSystem);
        console.run();
        studentRepository.writeToJson();
    }
}
