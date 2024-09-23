package TestPackage;

import MainPackage.WikipediaRevisionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class WikipediaRevisionParserTest {
    @Test
    public void testParse() throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String timestamp = parser.parseEdits(testDataStream);
        Assertions.assertEquals("2024-09-17T22:06:28Z", timestamp);
    }
}
