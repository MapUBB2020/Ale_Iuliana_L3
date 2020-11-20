package JSONParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DataReader<T> {
    public List<T> initialiseData() throws IOException, ParseException;
}
