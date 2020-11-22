package lab3.repository;

import Exceptions.IncorrectFileNameException;
import JSONParser.CourseId;
import JSONParser.TeacherDataReader;
import JSONParser.TeacherId;
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

    public void initialise() throws IOException, ParseException, IncorrectFileNameException {
        teachers = changeTeacher();
    }

    public List<Teacher> changeTeacher() throws IOException, ParseException, IncorrectFileNameException {
        List<TeacherId> teachersId = teacherDataReader.initialiseData();
        List<Teacher> teachers = new ArrayList<>();
        for (TeacherId teacherId: teachersId) {
            Teacher teacher = new Teacher();
            teacher.setID(teacherId.getID());
            teacher.setFirstName(teacherId.getFirstName());
            teacher.setLastName(teacherId.getLastName());
            teachers.add(teacher);
        }
        return teachers;
    }

    public List<TeacherId> changeToTeacherId() {
        List<TeacherId> teachersId = new ArrayList<>();

        for (Teacher teacher: teachers) {
            TeacherId teacherId = new TeacherId();
            teacherId.setID(teacher.getID());
            teacherId.setFirstName(teacher.getFirstName());
            teacherId.setLastName(teacher.getLastName());
            List<Long> coursesId = new ArrayList<>();
            for (Course course: teacher.getCourses()) {
                coursesId.add(course.getId());
            }
            teacherId.setCourses(coursesId);
            teachersId.add(teacherId);
        }
        return teachersId;
    }

    public void setRelations(CourseRepository courseRepository) {
        for (Course course: courseRepository.findAll()) {
            findOne(course.getTeacher().getID()).getCourses().add(course);
        }
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
