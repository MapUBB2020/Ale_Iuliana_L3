package lab3;

import lab3.model.Course;
import lab3.model.Student;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.util.List;

public class RegistrationSystem {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;

    public RegistrationSystem(CourseRepository courseRepository,
                              StudentRepository studentRepository,
                              TeacherRepository teacherRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    public void register(Course course, Student student) {
        if (course.getMaxEnrollment() == course.getStudentsEnrolled().size()) {
            List<Course> enrolledCourses = student.getEnrolledCourses();
            enrolledCourses.add(course);
            student.setEnrolledCourses(enrolledCourses);

            int credits = student.getTotalCredits();
            credits += course.getCredits();
            if (credits < 30) {
                student.setTotalCredits(credits);
                course.getStudentsEnrolled().add(student);

                if (studentRepository.findOne(student.getId()) != null) {
                    studentRepository.update(student);
                } else {
                    studentRepository.save(student);
                }
                courseRepository.update(course);
            } else {
                System.out.println("Maximum credits for student reached! Cannot choose this course.");
            }
        } else {
            System.out.println("Maximum number of students reached! Please choose another course.");
        }
    }
}
