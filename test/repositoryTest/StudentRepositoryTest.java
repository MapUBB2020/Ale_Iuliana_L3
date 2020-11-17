package repositoryTest;

import lab3.model.Student;
import lab3.repository.StudentRepository;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentRepositoryTest {
    StudentRepository studentRepo = new StudentRepository();

    @Test
    public void testStudentRepository() {
        Student s1 = new Student((long)1, "Alexandra", "Negru");
        Student s2 = new Student((long)2, "Iuliana", "Popa");
        Student s3 = new Student((long)3, "Aaa", "Bbb");
        Student s4 = new Student((long)4, "Ccc", "Ddd");

        studentRepo.students.add(s1);
        studentRepo.students.add(s2);

        Assert.assertEquals(studentRepo.students.get(0).firstName, s1.firstName);
        Assert.assertEquals(studentRepo.students.get(0).lastName, "Negru");
        Assert.assertEquals(studentRepo.students.get(1).firstName, s2.firstName);
        Assert.assertEquals(studentRepo.students.get(1).lastName, "Popa");
        Assert.assertEquals(studentRepo.students.get(0).getTotalCredits(),0);
        Assert.assertEquals(studentRepo.students.get(1).getTotalCredits(),0);

        assertEquals(studentRepo.findOne((long) 1), s1);
        assertEquals(studentRepo.findOne((long)2).lastName, s2.lastName);
        assertEquals(studentRepo.findOne((long)2).lastName, "Popa");
        assertEquals(studentRepo.findOne((long)2).firstName, "Iuliana");

        List studentsNew = new ArrayList<Student>(Arrays.asList(s1,s2));
        assertEquals(studentRepo.findAll(),studentsNew);

        //returneaza entitatea daca exista deja sau null daca se insereaza acum
        assertEquals(studentRepo.save(s1), s1); // s1 si s2 i-am inserat deja mai sus
        assertEquals(studentRepo.save(s2), s2);
        assertNull(studentRepo.save(s3));
        assertNull(studentRepo.save(s4));

        Student s5 = new Student((long) 5, "QQQ", "rrr");
        assertNull(studentRepo.save(s5));

        assertNull(studentRepo.delete((long)7));
        assertEquals(studentRepo.delete(s3.getId()),s3);
        assertEquals(studentRepo.delete(s4.getId()),s4);

        Student s6 = new Student((long) 5, "QQQ", "rrr");
        studentRepo.students.add(s6);
        assertNull(studentRepo.update(s6));
        Student s7 = new Student((long) 7, "Andrei", "Pop");
        assertEquals(studentRepo.update(s7), s7);

    }


}
