package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab3.repository.StudentRepository;
import lab3.repository.TeacherRepository;

import java.util.List;

public class StudentReader<Student> implements Reader{
    @Override
    public List<Student> initialiseData() {
        StudentRepository studentRepository = new StudentRepository();
        List<Student> repo = (List<Student>) studentRepository.students;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(repo);

        return repo;
    }
}
