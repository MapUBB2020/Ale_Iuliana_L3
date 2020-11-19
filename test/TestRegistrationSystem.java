import lab3.RegistrationSystem;
import lab3.model.Course;
import lab3.model.Student;
import lab3.model.Teacher;
import lab3.repository.CourseRepository;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class TestRegistrationSystem {

    CourseRepository courseRepository = new CourseRepository();
    StudentRepository studentRepository = new StudentRepository();
    TeacherRepository teacherRepository = new TeacherRepository();

    RegistrationSystem regSys = new RegistrationSystem(courseRepository,studentRepository,teacherRepository);

    @Test
    public void RegSysTest() {
        Course c1 = new Course((long) 3, "WS", new Teacher(new ArrayList<>(), (long) 3, "Hannelore", "Lisei"), 30, new ArrayList<>(),6);
        Course c2 = new Course((long) 4, "Programare logica", new Teacher(new ArrayList<>(), (long) 3, "Christian", "Sacarea"), 30, new ArrayList<>(),6);
        List<Course> l1 = new ArrayList<Course>();
        l1.add(c1);
        l1.add(c2);

        Assert.assertNotNull(regSys.retrieveCoursesWithFreePlaces());
        Assert.assertNotEquals(regSys.retrieveCoursesWithFreePlaces(),l1);

        Course c3 = new Course((long) 1, "BD", new Teacher(new ArrayList<Course>(), (long) 1, "Diana", "Troanca"), 30, new ArrayList<Student>(),6);
        Course c4 = new Course((long) 2, "MAP", new Teacher(new ArrayList<Course>(), (long) 2, "Catalin", "Rusu"), 30, new ArrayList<Student>(),6);
        List<Course> l2 = new ArrayList<Course>();
        l2.add(c3);
        l2.add(c4);

        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(0).getName(),l2.get(0).getName());
        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(0).getCredits(),6);
        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(0).getMaxEnrollment(),l2.get(0).getMaxEnrollment());
        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(0).getTeacher().firstName, "Diana");


        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(1).getName(),l2.get(1).getName());
        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(1).getCredits(),l2.get(1).getCredits());
        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(1).getMaxEnrollment(),30);
        Assert.assertEquals(regSys.retrieveCoursesWithFreePlaces().get(1).getTeacher().firstName, "Catalin");

        Assert.assertNotEquals(regSys.getAllCourses().get(0),l1.get(0));
        Assert.assertNotEquals(regSys.getAllCourses().get(1),l1.get(1));
        Assert.assertEquals(regSys.getAllCourses().get(0).getName(),l2.get(0).getName());
        Assert.assertEquals(regSys.getAllCourses().get(1).getCredits(),l2.get(1).getCredits());

        courseRepository.save(c1);
        courseRepository.save(c2);
        Student s1 = new Student((long)1, "Alexandra", "Negru");
        Student s2 = new Student((long)2, "Iuliana", "Popa");
        Student s3 = new Student((long)3, "Aaa", "Bbb");
        regSys.register((long)3, s1);
        regSys.register((long)4, s2);
        regSys.register((long)3, s3);

        Assert.assertEquals(regSys.retrieveStudentsEnrolledForACourse((long)3).get(0).getFirstName(),"Alexandra");
        Assert.assertEquals(regSys.retrieveStudentsEnrolledForACourse((long)3).get(1).getFirstName(),s3.getFirstName());
        Assert.assertEquals(regSys.retrieveStudentsEnrolledForACourse((long)3).get(0).getEnrolledCourses(),s1.getEnrolledCourses());
        Assert.assertEquals(regSys.retrieveStudentsEnrolledForACourse((long)3).get(1).getEnrolledCourses(),s3.getEnrolledCourses());

        Assert.assertNotEquals(regSys.retrieveStudentsEnrolledForACourse((long)3).get(0),s3);
        Assert.assertNotEquals(regSys.retrieveStudentsEnrolledForACourse((long)3).get(1),s1);

        //Test register

        Assert.assertTrue(regSys.register((long) 1, s1));
        Assert.assertTrue(regSys.register((long) 1, s2));
        Assert.assertTrue(regSys.register((long) 1, s3));
        s1.setTotalCredits(30);
        Assert.assertFalse(regSys.register((long) 1, s1));
    }


}
