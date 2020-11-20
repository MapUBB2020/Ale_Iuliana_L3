package JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lab3.repository.CourseRepository;

import java.util.List;

public class CourseDataReader<Course> implements DataReader {

    @Override
    public List<Course> initialiseData() {
        CourseRepository courseRepository = new CourseRepository();
        List<Course> repo = (List<Course>) courseRepository.courseRepo;

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(repo);

        return repo;
    }
}
