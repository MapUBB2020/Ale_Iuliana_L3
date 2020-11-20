package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lab3.repository.StudentRepository;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lab3.model.Student;

public class StudentDataReader{
    public List<Student> initialiseData() throws IOException {
        StudentRepository studentRepository = new StudentRepository();

        Path path = Paths.get("students.json");
        List<Student> studentsFromJson = new ArrayList<Student>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = Files.newBufferedReader(path)) {
            studentsFromJson = gson.fromJson(reader,
                    new TypeToken<List<Student>>(){}.getType());
            studentsFromJson.forEach(System.out::println);
        }

        return studentsFromJson;
    }
}
