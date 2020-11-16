package lab3.repository;

import lab3.model.Course;
import lab3.model.Person;
import lab3.model.Student;
import lab3.model.Teacher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CourseRepository implements ICrudRepository<Course>{
    List<Course> courseRepo = new ArrayList<>(Arrays.asList(
            new Course((long) 1, "BD", new Teacher(new ArrayList<Course>(), (long) 1, "Diana", "Troanca"), 30, new ArrayList<Student>(),6),
            new Course((long) 2, "MAP", new Teacher(new ArrayList<Course>(), (long) 2, "Catalin", "Rusu"), 30, new ArrayList<Student>(),6)));

    @Override
    public Course findOne(Long id) {
        for(Course course: courseRepo)
            if (course.getId().equals(id))
              return course;
        return null;
    }

    @Override
    public List<Course> findAll() {
        return courseRepo;
    }

    @Override
    public Course save(Course entity) {
        if (entity != null){
            if(findOne(entity.getId()) != null) {
                return entity;
            } else {
                courseRepo.add(entity);
            }
        }
        return null;
    }

    @Override
    public Course delete(Long id) {
        if (id != null){
            Course entity = findOne(id);
            if (entity != null)
                courseRepo.remove(entity);
        }
        return null;
    }

    @Override
    public Course update(Course entity) {
        if (entity != null) {
            if(findOne(entity.getId()) != null) {
                entity.setName(entity.getName());
                entity.setTeacher(entity.getTeacher());
                entity.setMaxEnrollment(entity.getMaxEnrollment());
                entity.setStudentsEnrolled(entity.getStudentsEnrolled());
                entity.setCredits(entity.getCredits());
            } else {
                return entity;
            }
        }
        return null;
    }
}
