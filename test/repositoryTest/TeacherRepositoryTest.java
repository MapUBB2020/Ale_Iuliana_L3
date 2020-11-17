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
        Student s1 = new Student((long)1, "Alexandra", "Negru");
        Student s2 = new Student((long)2, "Iuliana", "Popa");
        Student s3 = new Student((long)3, "Aaa", "Bbb");
        Student s4 = new Student((long)4, "Ccc", "Ddd");
        List<Student> enrolledStudents = new ArrayList<Student>(Arrays.asList(s1,s2,s3,s4));

        Course c3 = new Course((long)3, "WS" , new Teacher(new ArrayList<Course>(), (long) 3, "Hannelore", "Lisei"), 32, enrolledStudents, 6);
        Course c4 = new Course((long)4, "Programare logica" , new Teacher(new ArrayList<Course>(), (long) 4, "Christian", "Sacarea"), 32, enrolledStudents, 5);
        List<Course> courseList = new ArrayList<>(Arrays.asList(c3,c4));

        //Teacher t3 = new Teacher(courseList, (long) 3, "Hannelore", "Lisei");
        //Teacher t4 = new Teacher(courseList, (long) 4, "Christian", "Sacarea");
        //List<Teacher> teacherList = new ArrayList<Teacher>(Arrays.asList(t1,t2));

        Teacher t3 = new Teacher(
                new ArrayList<Course>(Arrays.asList(
                        new Course((long) 3, "WS", new Teacher(new ArrayList<>(), (long) 3, "Hannelore", "Lisei"), 30, new ArrayList<>(),6))),
                (long) 3,
                "Hannelore",
                "Lisei");
        teacherRepository.save(t3);
        //teacherRepository.save(t4);

        Assert.assertEquals(teacherRepository.teachers.get(2).firstName, "Hannelore");

    }
}
