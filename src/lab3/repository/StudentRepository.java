package lab3.repository;

import JSONParser.StudentDataReader;
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

    @Override
    public void initialise() throws IOException, ParseException {
        students = studentDataReader.initialiseData();
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
