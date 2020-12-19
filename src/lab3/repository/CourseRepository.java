package lab3.repository;

import JSONParser.CourseDataReader;
import JSONParser.CourseId;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository implements ICrudRepository<Course>{
    CourseDataReader courseDataReader = new CourseDataReader();
    public List<Course> courseRepo = new ArrayList<>();
    public List<Teacher> teacherRepo = new ArrayList<>();

    public CourseRepository() {
    }

    public CourseRepository(List<Teacher> teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    @Override
    public void initialise() throws IOException, ParseException {
        courseRepo = changeCourse();
    }

    /**
     * Method that reads a list of courses with teacher id instead of teacher object and converts the id to object
     * @return the list of courses with teachers, not with id's
     * @throws IOException
     * @throws ParseException
     */
    public List<Course> changeCourse() throws IOException, ParseException {
        List<CourseId> coursesId = courseDataReader.initialiseData();
        List<Course> courses = new ArrayList<>();
        for (CourseId courseId: coursesId) {
            Course course = new Course();
            course.setId(courseId.getId());
            course.setName(courseId.getName());
            for (Teacher teacher: teacherRepo) {
                if (teacher.getID() == courseId.getTeacher()) {
                    course.setTeacher(teacher);
                }
            }
            course.setMaxEnrollment(courseId.getMaxEnrollment());
            course.setCredits(courseId.getCredits());
            courses.add(course);
        }
        return courses;
    }

    /**
     * Method that sets the studentsEnrolled list by iterating through bothe the students and courses list
     * We add the student which is enrolled at a course to the list of studentsEnrolled from Course
     * @param studentRepository
     */
    public void setRelations(StudentRepository studentRepository) {
        for (Student student: studentRepository.findAll()) {
            for (Course course: student.getEnrolledCourses()) {
                findOne(course.getId()).getStudentsEnrolled().add(student);
            }
        }
    }

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
            if (entity != null) {
                courseRepo.remove(entity);
                return entity;
            }
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
