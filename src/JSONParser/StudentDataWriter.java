package JSONParser;

import Exceptions.IncorrectFileNameException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab3.model.Student;
import lab3.repository.StudentRepository;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class StudentDataWriter {
    /**
     *
     * @param filename
     * @return true if the filename is correct, false otherwise
     */
    public boolean isCorrectFileName(String filename){
        return filename.equals("students.json");
    }

    /**
     * Method that writes to the gson file a list of students with id's for the enrolled courses
     * @param students
     * @throws IOException
     * @throws IncorrectFileNameException
     */
    public void writeData(List<StudentId> students) throws IOException, IncorrectFileNameException {
        Path path = Paths.get("students.json");
        try (Writer writer = Files.newBufferedWriter(path,  StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(students, writer);
        } catch(FileNotFoundException e) {
            if (!isCorrectFileName(path.toString()))
                throw new IncorrectFileNameException("Incorrect filename: " + path.toString());
        }

    }
}
