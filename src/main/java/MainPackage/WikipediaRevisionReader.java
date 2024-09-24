package MainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class WikipediaRevisionReader {

    public static void main(String[] args) {

        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        Scanner scanner = new Scanner(System.in);

        System.out.println("What article do you want?");
        String articleTitle = scanner.nextLine();

        if (articleTitle.isEmpty()) {
            System.err.println("Error: No article name provided.");
            System.exit(1);
        }

        try {
            revisionReader.fetchRecentEdits(articleTitle);
        } catch (IOException ioException) {
            System.err.println("Network connection problem: " + ioException.getMessage());
        }
    }

    private void fetchRecentEdits(String articleTitle) throws IOException {
        try {
            String encodedTitle = URLEncoder.encode(articleTitle, StandardCharsets.UTF_8);
            String urlString = String.format("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&list=recentchanges&titles=%s&rvprop=timestamp|comment|user&rvlimit=15",
                    encodedTitle);
            urlString = urlString.replace("|", "%7C");

            System.out.println("Fetching URL: " + urlString);

            URI uri = new URI(urlString);
            URL url = uri.toURL();
            System.out.println(url);
            System.out.println(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent",
                    "WikipediaRevisionReader/0.1 (https://youtube.com/paulgestwicki; pvgestwicki@bsu.edu)");
            InputStream inputStream = connection.getInputStream();
            WikipediaRevisionParser parser = new WikipediaRevisionParser();
            parser.parseEdits(inputStream);
        } catch (URISyntaxException e) {
            System.err.println("Error: Invalid URL syntax.");
            System.err.println("Exception message: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error fetching edits: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

}
