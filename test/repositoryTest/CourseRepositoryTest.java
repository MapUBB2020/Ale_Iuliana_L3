package repositoryTest;

import lab3.model.Course;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CourseRepositoryTest {
    CourseRepository courseRepository = new CourseRepository();

    @Test
    public void testCourseRepository() {
        Course course1 = new Course((long) 3, "WS", new Teacher(new ArrayList<>(), (long) 3, "Hannelore", "Lisei"), 30, new ArrayList<>(),6);
        Course course2 = new Course((long) 4, "Programare logica", new Teacher(new ArrayList<>(), (long) 3, "Christian", "Sacarea"), 30, new ArrayList<>(),6);

        courseRepository.save(course1);
        courseRepository.save(course2);

        Assert.assertEquals(courseRepository.save(course1), course1);
        Assert.assertEquals(courseRepository.save(course2), course2);

        Assert.assertEquals(courseRepository.findOne((long)3).getName(), course1.getName());
        Assert.assertEquals(courseRepository.findOne((long)4), course2);

        Assert.assertEquals(courseRepository.delete((long) 3), course1);
        Assert.assertEquals(courseRepository.delete((long) 4), course2);
        Assert.assertNull(courseRepository.delete((long) 5));

        Assert.assertEquals(courseRepository.update(course1), course1);
    }
}
