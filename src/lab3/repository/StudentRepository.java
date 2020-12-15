package lab3.repository;

import Exceptions.IncorrectFileNameException;
import JSONParser.StudentDataReader;
import JSONParser.StudentId;
import lab3.model.Course;
import lab3.model.Student;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements ICrudRepository<Student>{

    /**
     * Create a list for the students
     * Create an object studentDataReader to read from json
     */
    public List<Student> students = new ArrayList<Student>();
    StudentDataReader studentDataReader = new StudentDataReader();
    public List<Course> courses = new ArrayList<>();

    /**
     * Default empty constructor
     */
    public StudentRepository() {
    }

    /**
     *
     * @param courses
     * Default constructor for student repo with the list of courses
     */
    public StudentRepository(List<Course> courses) {
        this.courses = courses;
    }

    /**
     *
     * @throws IOException
     * @throws ParseException
     * @throws IncorrectFileNameException
     */
    @Override
    public void initialise() throws IOException, ParseException, IncorrectFileNameException {
        students = changeStudent();
    }

    /**
     * Method that reads from the json file the list of students with id's for the enrolled courses
     * and converts the id's in Course objects
     * @return the list of students
     * @throws IOException
     * @throws ParseException
     * @throws IncorrectFileNameException
     */
    public List<Student> changeStudent() throws IOException, ParseException, IncorrectFileNameException {
        List<StudentId> studentsId = studentDataReader.initialiseData();
        List<Student> students = new ArrayList<>();
        for (StudentId studentId: studentsId) {
            Student student = new Student();
            student.setId(studentId.getId());
            student.setFirstName(studentId.getFirstName());
            student.setLastName(studentId.getLastName());
            List<Course> coursesForStudent = new ArrayList<>();
            for (Long courseId: studentId.getEnrolledCourses()) {
                for (Course course: courses) {
                    if (courseId.equals(course.getId())) {
                        coursesForStudent.add(course);
                        break;
                    }
                }
            }
            student.setEnrolledCourses(coursesForStudent);
            student.setTotalCredits(studentId.getTotalCredits());
            students.add(student);
        }
        return students;
    }

    /**
     *
     * @return the list of students with id's
     * Method that converts from  object to id (the opposite of the former method)
     */
    public List<StudentId> changeToStudentId() {
        List<StudentId> studentsId = new ArrayList<>();

        for (Student student: students) {
            StudentId studentId = new StudentId();
            studentId.setId(student.getId());
            studentId.setFirstName(student.getFirstName());
            studentId.setLastName(student.getLastName());
            studentId.setTotalCredits(student.getTotalCredits());
            List<Long> coursesId = new ArrayList<>();
            for (Course course: student.getEnrolledCourses()) {
                coursesId.add(course.getId());
            }
            studentId.setEnrolledCourses(coursesId);
            studentsId.add(studentId);
        }
        return studentsId;
    }


    /**
     *  @return the student with the specified id or null - if there is no student with the given id
     *  */
    @Override
    public Student findOne(Long id) {
        for (Student stud: students) {
            if (stud.getId().equals(id))
                return stud;
        }
        return null;
    }

    /**
     *
     * @return all students
     */
    @Override
    public Iterable<Student> findAll() {
        if (students.size() != 0)
            return students;
        return new ArrayList<>();
    }

    /**
     *
     * @param entity entity must be not null
     * @return null- if the given student is saved otherwise returns the student (id already exists)
     */
    @Override
    public Student save(Student entity) {
        if (findOne(entity.getId()) != null) {
            return entity;
        }
        else {
            students.add(entity);
        }
        return null;
    }

    /**
     *
     * @param id id must be not null
     * @return the removed student or null if there is no student with the given id
     */
    @Override
    public Student delete(Long id) {
        for (Student stud: students) {
            if (stud.getId() == id){
                students.remove(stud);
                return stud;
            }
        }
        return null;
    }

    /**
     *
     * @param entity entity must not be null
     * @return null - if the student is updated, otherwise returns the entity - (e.g id does not exist).
     */
    @Override
    public Student update(Student entity){
        for (Student stud: students) {
            if (stud.getId().equals(entity.getId())){
                stud.setFirstName(entity.getFirstName());
                stud.setLastName(entity.getLastName());
                //stud.setTotalCredits(entity.getTotalCredits());
                //stud.setEnrolledCourses(entity.getEnrolledCourses());
                return null;
            }
        }
        return entity;
    }
}
