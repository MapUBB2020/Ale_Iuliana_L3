package JSONParser;

import Exceptions.IncorrectFileNameException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lab3.repository.StudentRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lab3.model.Student;

public class StudentDataReader{

    /**
     *
     * @param filename
     * @return true if the filename is correct, false otherwise
     */
    public boolean isCorrectFileName(String filename){
        return filename.equals("students.json");
    }

    /**
     * Method that reads from the file a list of students that has a list of enrolled courses as id's
     * @return the list of students
     * @throws IOException
     * @throws IncorrectFileNameException
     */
    public List<StudentId> initialiseData() throws IOException, IncorrectFileNameException {
        StudentRepository studentRepository = new StudentRepository();

        Path path = Paths.get("students.json");
        List<StudentId> studentsFromJson = new ArrayList<>();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (Reader reader = Files.newBufferedReader(path)) {
            studentsFromJson = gson.fromJson(reader,
                    new TypeToken<List<StudentId>>(){}.getType());
        }catch(FileNotFoundException e) {
            if (!isCorrectFileName(path.toString()))
                throw new IncorrectFileNameException("Incorrect filename: " + path.toString());
        }

        return studentsFromJson;
    }
}
