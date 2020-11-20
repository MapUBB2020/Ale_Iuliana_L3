package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lab3.model.Teacher;
import lab3.repository.TeacherRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;
import java.io.Reader;

public class TeacherReader<Teacher> implements DataReader<Teacher> {

    @Override
    public List<Teacher> initialiseData() throws IOException, ParseException {
        TeacherRepository teacherRepository = new TeacherRepository();
        List<Teacher> repo = (List<Teacher>) teacherRepository.teachers;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path path = Paths.get("teachers.json");
        try (Reader reader = Files.newBufferedReader(path)) {
            List<Teacher> teachersFromJson = gson.fromJson(reader,
                    new TypeToken<List<Teacher>>(){}.getType());
            teachersFromJson.forEach(System.out::println);
            teacherRepository.teachers = (List<lab3.model.Teacher>) teachersFromJson;
        }
        return repo;
    }
}
