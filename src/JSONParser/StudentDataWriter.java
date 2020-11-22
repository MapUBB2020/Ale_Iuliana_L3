package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab3.model.Student;
import lab3.repository.StudentRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StudentDataWriter {
    public void writeData(List<StudentId> students) throws IOException {
        Path path = Paths.get("students.json");
        try (Writer writer = Files.newBufferedWriter(path,  StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(students, writer);
        }
    }
}
