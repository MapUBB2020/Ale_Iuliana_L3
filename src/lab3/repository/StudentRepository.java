package lab3.repository;

import lab3.model.Course;
import lab3.model.Student;

import java.util.List;

public class StudentRepository implements ICrudRepository<Student>{

    private List<Student> students;
    /**
     *  @return the student with the specified id or null - if there is no student with the given id
     *  */
    @Override
    public Student findOne(Long id) {
        for (Student stud: students) {
            if (stud.getStudentId() == id)
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
        return students;
    }

    /**
     *
     * @param entity entity must be not null
     * @return null- if the given student is saved otherwise returns the student (id already exists)
     */
    @Override
    public Student save(Student entity) {
        for (Student stud: students) {
            if (stud.getStudentId() == entity.getStudentId() && stud.getTotalCredits() == entity.getTotalCredits())
                return null;
        }
        return entity;
    }

    /**
     *
     * @param id id must be not null
     * @return the removed student or null if there is no student with the given id
     */
    @Override
    public Student delete(Long id) {
        for (Student stud: students) {
            if (stud.getStudentId() == id)
                return stud;
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
            if (stud.getStudentId() == entity.getStudentId()){
                stud.setFirstName(entity.getFirstName());
                stud.setLastName(entity.getLastName());
                stud.setTotalCredits(entity.getTotalCredits());
                stud.setEnrolledCourses(entity.getEnrolledCourses());
                return null;
            }
        }
        return entity;
    }
}
