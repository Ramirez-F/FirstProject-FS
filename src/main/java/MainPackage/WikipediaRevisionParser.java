package MainPackage;

import com.jayway.jsonpath.JsonPath;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class WikipediaRevisionParser {
    public String parseEdits(InputStream inputStream) throws IOException {
        Map<String, Object> json = JsonPath.read(inputStream, "$");

        Map<String, Object> pages = JsonPath.read(json, "$.query.pages");

        String pageKey = (String) pages.keySet().toArray()[0];

        List<Object> revisions = JsonPath.read(pages.get(pageKey), "$.revisions");

        if (revisions == null || revisions.isEmpty()) {
            System.out.println("No changes found for this article.");
            return pageKey;
        }

        for (Object revision : revisions) {
            Map<String, Object> revisionMap = (Map<String, Object>) revision;
            String timestamp = (String) revisionMap.get("timestamp");
            String user = (String) revisionMap.get("user");
            System.out.println(timestamp + "    " + user);
        }
        return pageKey;
    }
}


