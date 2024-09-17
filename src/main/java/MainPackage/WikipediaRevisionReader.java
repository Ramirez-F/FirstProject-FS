package MainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Scanner;

public class WikipediaRevisionReader {
    public static void main(String[] args) {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("What article do you want");
        String line = scanner.nextLine();
        try {
            String timestamp = revisionReader.getLatestRevision0f(line);
            System.out.println(timestamp);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }


    private String getLatestRevision0f(String articleTitle) throws IOException {
        String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=ison&prop=revisions&titles=%s&rvprop=timestamp&rvlimit=1",
                articleTitle);
        String encodedUrlString = URLEncoder.encode(urlString, Charset.defaultCharset());
        try {
            URL url = new URL(encodedUrlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent",
                    "WikipediaRevisionReader/0.1 (https://youtube.com/paulgestwicki; pvgestwicki@bsu.edu)");
            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            String timestamp = parser.parse(inputStream);
            return timestamp;
        } catch (MalformedURLException malformedURLException) {
            throw new RuntimeException(malformedURLException);
        }
    }
}