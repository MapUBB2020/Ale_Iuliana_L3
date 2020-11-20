package JSONParser;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface Reader<T> {
    public List<T> initialiseData() throws IOException, ParseException;
}
