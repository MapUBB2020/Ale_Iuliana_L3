package lab3;

import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.util.ArrayList;
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

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public void register(Long id, Student student) {
        Course course = new Course();
        for (Course newCourse: courseRepository.findAll()) {
            if (course.getId().equals(id)) {
                course = newCourse;
            }
        }

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

    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> coursesWithFreePlaces = new ArrayList<Course>();
        for (Course course : courseRepository.findAll())
            if (course.getStudentsEnrolled().size() < course.getMaxEnrollment())
                coursesWithFreePlaces.add(course);
        return coursesWithFreePlaces;
    }
    //cand afisez o sa fac un for si afisez pt fiecare curs numarul de locuri libere

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Student> retrieveStudentsEnrolledForACourse(Long id) {
        Course course = new Course();
        for (Course newCourse: courseRepository.findAll()) {
            if (course.getId().equals(id)) {
                course = newCourse;
            }
        }
        return course.getStudentsEnrolled();
    }

    public void deleteCourse(Long idTeacher, Long idCourse) {
        Teacher teacher = new Teacher();
        for (Teacher newTeacher: teacherRepository.findAll()) {
            if (newTeacher.getID() == idTeacher) {
                teacher = newTeacher;
            }
        }

        Course course = new Course();
        for (Course newCourse: courseRepository.findAll()) {
            if (course.getId().equals(idCourse)) {
                course = newCourse;
            }
        }
        teacher.getCourses().remove(course);
        teacherRepository.update(teacher);
        for (Student stud: course.getStudentsEnrolled()) {
            stud.setTotalCredits(stud.getTotalCredits() - course.getCredits());
            for (Course c: stud.getEnrolledCourses()) {
                if (c.getId().equals(course.getId())) {
                    stud.getEnrolledCourses().remove(course);
                    studentRepository.update(stud);
                }
            }
        }
        courseRepository.delete(course.getId());
    }
}
