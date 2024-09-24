package MainPackage;

import java.io.IOException;
import java.util.Scanner;

public class Main {
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
}
