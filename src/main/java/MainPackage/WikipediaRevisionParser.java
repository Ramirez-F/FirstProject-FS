package MainPackage;

import com.jayway.jsonpath.JsonPath;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class WikipediaRevisionParser extends Node {

    public String parseEdits(InputStream inputStream) throws IOException {
        Map<String, Object> json = JsonPath.read(inputStream, "$");

        Map<String, Object> pages = JsonPath.read(json, "$.query.pages");

        String pageKey = (String) pages.keySet().toArray()[0];

        List<Object> revisions = JsonPath.read(pages.get(pageKey), "$.revisions");

        if (revisions == null || revisions.isEmpty()) {
            System.out.println("No changes found for this article.");
            return pageKey;
        }

        //Prints Out Gathered Data
        for (Object revision : revisions) {
            Map<String, Object> revisionMap = (Map<String, Object>) revision;
            String title = (String) revisionMap.get("title");
            String comment = (String) revisionMap.get("comment");
            String timestamp = (String) revisionMap.get("timestamp");
            String user = (String) revisionMap.get("user");
            System.out.printf("%-25s %-20s %-50s\n",timestamp, user, comment);
            System.out.println(" ");
        }
        return pageKey;
    }
}


