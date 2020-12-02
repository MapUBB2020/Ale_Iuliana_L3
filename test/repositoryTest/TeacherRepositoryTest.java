package repositoryTest;

import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.TeacherRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherRepositoryTest {
    TeacherRepository teacherRepository = new TeacherRepository();

    @Test
    public void TestTeacherRepository(){

        Teacher t3 = new Teacher(
                new ArrayList<Course>(Arrays.asList(
                        new Course((long) 3, "WS", new Teacher(new ArrayList<>(), (long) 3, "Hannelore", "Lisei"), 30, new ArrayList<>(),6))),
                (long) 3,
                "Hannelore",
                "Lisei");
        Teacher t4 = new Teacher(
                new ArrayList<Course>(Arrays.asList(
                        new Course((long) 4, "Programare logica", new Teacher(new ArrayList<>(), (long) 3, "Christian", "Sacarea"), 30, new ArrayList<>(),6))),
                (long) 4,
                "Christian",
                "Sacarea");

        teacherRepository.save(t3);
        teacherRepository.save(t4);

        Assert.assertEquals(teacherRepository.teachers.get(0).firstName, "Hannelore");
        Assert.assertEquals(teacherRepository.teachers.get(0).getCourses().get(0).getName(), "WS");
        Assert.assertEquals(teacherRepository.teachers.get(1).firstName, "Christian");
        Assert.assertEquals(teacherRepository.teachers.get(1).getCourses().get(0).getName(), "Programare logica");

        Assert.assertNull(teacherRepository.findOne((long)5));
        Assert.assertEquals(teacherRepository.findOne((long)3).lastName, "Lisei");
        Assert.assertEquals(teacherRepository.findOne((long)4).lastName, "Sacarea");

        List<Teacher> teacherList = new ArrayList<Teacher>(Arrays.asList(t3,t4));

        Assert.assertEquals(teacherRepository.save(t3),t3);
        Assert.assertEquals(teacherRepository.save(t4),t4);
        Teacher t5 = new Teacher(
                new ArrayList<Course>(Arrays.asList(
                        new Course((long) 5, "A", new Teacher(new ArrayList<>(), (long) 3, "Aaa", "Bbb"), 30, new ArrayList<>(),6))),
                (long) 5,
                "Aaa",
                "Bbb");
        Assert.assertNull(teacherRepository.save(t5));

        teacherRepository.delete((long)5);

        Assert.assertNull(teacherRepository.delete(t5.getID()));
        Assert.assertEquals(teacherRepository.delete(t3.getID()),t3);

        Assert.assertNull(teacherRepository.update(t4));
        Assert.assertNotNull(teacherRepository.update(t5));
        Assert.assertEquals(teacherRepository.update(t5).lastName,"Bbb");

    }
}
