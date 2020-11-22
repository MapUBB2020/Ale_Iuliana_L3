package lab3;

import JSONParser.StudentDataWriter;
import lab3.model.Course;
import lab3.model.Person;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationSystem {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;
    private TeacherRepository teacherRepository;
    StudentDataWriter studentDataWriter = new StudentDataWriter();

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

    public void writeToJson() throws IOException {
        studentDataWriter.writeData(studentRepository.students);
    }

    /**
     *Registers a given student to a given course.
     * @param id the id of the course to which the student registers
     * @param student the student to be registered
     * @return true if student is registered, false if not
     */
    public boolean register(Long id, Student student) {
        Course course = new Course();
        for (Course newCourse: courseRepository.findAll()) {
            if (newCourse.getId().equals(id)) {
                course = newCourse;
            }
        }

        if (course.getMaxEnrollment() > course.getStudentsEnrolled().size()) {
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
                return true;
            } else {
                System.out.println("Maximum credits for student reached! Cannot choose this course.");
            }
        } else {
            System.out.println("Maximum number of students reached! Please choose another course.");
        }
        return false;
    }

    /**
     *
     * @return a list with the courses with free places (which have the size of the studentsEnrolled list smaller than the maximum capacity
     * of enrollment)
     */
    public List<Course> retrieveCoursesWithFreePlaces() {
        List<Course> coursesWithFreePlaces = new ArrayList<Course>();
        for (Course course : courseRepository.findAll())
            if (course.getStudentsEnrolled().size() < course.getMaxEnrollment())
                coursesWithFreePlaces.add(course);
        return coursesWithFreePlaces;
    }
    //cand afisez o sa fac un for si afisez pt fiecare curs numarul de locuri libere

    /**
     *
     * @return all available courses
     */
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    /**
     *
     * @param id for a course
     * @return the students enrolled for the course with a given id
     * searches through the list of all courses and when the course with the given id is found, returns the list with enrolled students
     */
    public List<Student> retrieveStudentsEnrolledForACourse(Long id) {
        Course course = new Course();
        for (Course newCourse: courseRepository.findAll()) {
            if (newCourse.getId().equals(id)) {
                course = newCourse;
            }
        }
        return course.getStudentsEnrolled();
    }

    /**
     * Deletes a given course for a given teacher
     * @param idTeacher id of the Teacher who wants to delete the course
     * @param idCourse the id of the course to be deleted
     */
    public void deleteCourse(Long idTeacher, Long idCourse) {
        Teacher teacher = new Teacher();
        for (Teacher newTeacher: teacherRepository.findAll()) {
            if (newTeacher.getID() == idTeacher) {
                teacher = newTeacher;
            }
        }
        Course course = new Course();
        for (Course newCourse: courseRepository.findAll()) {
            if (newCourse.getId().equals(idCourse)) {
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

    public List<Course> sortByCredits() {
        List<Course> sortedCourses = courseRepository.findAll();
        sortedCourses.sort((course1, course2) -> course1.getCredits() - course2.getCredits());
        return sortedCourses;
    }

    public List<Course> filterByTeacher(Person teacher) {
        List<Course> courseRepo = courseRepository.findAll();
        return courseRepo.stream()
                .filter(course -> course.getTeacher().firstName.equals(teacher.getFirstName()))
                .filter(course -> course.getTeacher().lastName.equals(teacher.getLastName()))
                .collect(Collectors.toList());
    }
}
