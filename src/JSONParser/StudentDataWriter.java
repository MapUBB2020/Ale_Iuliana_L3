package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab3.model.Student;
import lab3.repository.StudentRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StudentDataWriter {
    public void writeData() throws IOException {
        StudentRepository studentRepository = new StudentRepository();

        Path path = Paths.get("students.json");
        try (Writer writer = Files.newBufferedWriter(path)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(studentRepository.students, writer);
        }
        System.out.println(studentRepository.students.get(0));


    }
}
