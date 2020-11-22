package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lab3.model.Course;
import lab3.repository.CourseRepository;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class CourseDataReader {

    public List<CourseId> initialiseData() throws IOException, ParseException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Path path = Paths.get("courses.json");
        List<CourseId> coursesFromJson;
        try (Reader reader = Files.newBufferedReader(path)) {
            coursesFromJson = gson.fromJson(reader,
                    new TypeToken<List<CourseId>>(){}.getType());
        }
        return coursesFromJson;
    }
}
