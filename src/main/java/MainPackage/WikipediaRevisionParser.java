package MainPackage;

import com.jayway.jsonpath.JsonPath;

import java.io.IOException;
import java.io.InputStream;

public class WikipediaRevisionParser {
    public String parse(InputStream testDataStream) throws IOException {
        Object o = JsonPath.read(testDataStream, "$..timestamp");
        return null;
    }
}
