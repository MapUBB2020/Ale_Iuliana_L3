package lab3.repository;

import lab3.model.Student;
import lab3.model.Teacher;

import java.util.List;

public class TeacherRepository implements ICrudRepository<Teacher>{
    List<Teacher> teachers;

    @Override
    public Teacher findOne(Long id) {
        for (Teacher teacher: teachers) {
            if (teacher.getID() == id)
                return teacher;
        }
        return null;
    }

    @Override
    public Iterable<Teacher> findAll() {
        return teachers;
    }

    @Override
    public Teacher save(Teacher entity) {
        for (Teacher teacher: teachers) {
            if (teacher.getID() == entity.getID())
                return null;
        }
        return entity;
    }

    @Override
    public Teacher delete(Long id) {
        for (Teacher teacher: teachers) {
            if (teacher.getID() == id)
                return teacher;
        }
        return null;
    }

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
