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

public class TeacherDataWriter {

    /**
     *
     * @param filename
     * @return true if the filename is correct, false otherwise
     */
    public boolean isCorrectFileName(String filename){
        return filename.equals("teachers.json");
    }

    /**
     * Method that writes to "teachers.json" a list of teachers that has a list of courses id's
     * @param teachers
     * @throws IOException
     * @throws IncorrectFileNameException
     *
     */
    public void writeData(List<TeacherId> teachers) throws IOException, IncorrectFileNameException {
        Path path = Paths.get("teachers.json");
        try (Writer writer = Files.newBufferedWriter(path,  StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(teachers, writer);
        } catch(FileNotFoundException e) {
            if (!isCorrectFileName(path.toString()))
                throw new IncorrectFileNameException("Incorrect filename: " + path.toString());
        }
    }
}
