package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lab3.model.Teacher;
import lab3.repository.TeacherRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.io.Reader;

public class TeacherDataReader {
    public List<TeacherId> initialiseData() throws IOException, ParseException {
        TeacherRepository teacherRepository = new TeacherRepository();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path path = Paths.get("teachers.json");
        List<TeacherId> teachersFromJson = new ArrayList<>();
        try (Reader reader = Files.newBufferedReader(path)) {
            teachersFromJson = gson.fromJson(reader,
                    new TypeToken<List<TeacherId>>(){}.getType());
        }
        return teachersFromJson;
    }
}
