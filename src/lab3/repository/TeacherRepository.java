package lab3.repository;

import JSONParser.TeacherDataReader;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TeacherRepository implements ICrudRepository<Teacher> {

//
//    public List<Teacher> teachers = new ArrayList<Teacher> (Arrays.asList(
//            new Teacher(
//                    new ArrayList<Course>(Arrays.asList(
//                            new Course((long) 2, "MAP", new Teacher(new ArrayList<Course>(), (long) 2, "Catalin", "Rusu"), 30, new ArrayList<Student>(),6))),
//                    (long) 2,
//                    "Catalin",
//                    "Rusu"),
//            new Teacher(
//                    new ArrayList<Course>(Arrays.asList(
//                            new Course((long) 1, "BD", new Teacher(new ArrayList<Course>(), (long) 1, "Diana", "Troanca"), 30, new ArrayList<Student>(),6))),
//                    (long) 1,
//                    "Diana",
//                    "Troanca")));
//
//
//

    TeacherDataReader teacherDataReader = new TeacherDataReader();
    public List<Teacher> teachers = new ArrayList<Teacher>();

    public void initialise() throws IOException, ParseException {
        teachers = teacherDataReader.initialiseData();
    }


    /**
     *
     * @param id -the id of the teacher to be returned id must not be null
     * @return the teacher object if it exists in the list, null otherwise
     */
    @Override
    public Teacher findOne(Long id) {
        for (Teacher teacher: teachers) {
            if (teacher.getID() == id)
                return teacher;
        }
        return null;
    }

    /**
     *
     * @return the list with all teachers
     */
    @Override
    public Iterable<Teacher> findAll() {
        return teachers;
    }

    /**
     *
     * @param entity teacher must be not null
     * @return null if the entity teacher is saved, otherwise returns the entity (id already exists)
     */
    @Override
    public Teacher save(Teacher entity) {
        if (findOne(entity.getID()) != null) {
            return entity;
        }
        else {
            teachers.add(entity);
        }
        return null;
    }

    /**
     *
     * @param id id must be not null
     * @return the removed teacher if the teacher with the given id exists, null otherwise
     */
    @Override
    public Teacher delete(Long id) {
        if (id != null){
            Teacher teacher = findOne(id);
            if (teacher != null) {
                teachers.remove(teacher);
                return teacher;
            }
        }
        return null;
    }

    /**
     *
     * @param entity teacher must not be null
     * @return null if the teacher is updated, otherwise return the teacher entity
     */
    @Override
    public Teacher update(Teacher entity) {
        for (Teacher teacher: teachers) {
            if (teacher.getID() == entity.getID()){
                teacher.setFirstName(entity.getFirstName());
                teacher.setLastName(entity.getLastName());
                teacher.setCourses(entity.getCourses());
                return null;
            }
        }
        return entity;

    }
}
