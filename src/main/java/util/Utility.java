package util;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Utility {

    private static final JSONObject testdata = new JSONObject(WebOperations.getJsonTestData());

    public static List<List<String>> getUrlsAndLanguagesForMeetingPlace(String meetingPlace)  {
        JSONObject meetingPlacesNode = testdata.getJSONObject("MeetingPlaces");
        JSONObject meetingPlaceNode = meetingPlacesNode.optJSONObject(meetingPlace).getJSONObject("Meetingplace Url");

        // This will hold the result: List of lists, where each list holds [url, language]
        List<List<String>> urls = new ArrayList<>();

        if (meetingPlaceNode != null) {
            Iterator<String> keys = meetingPlaceNode.keys();

            while (keys.hasNext()) {
                String datasetKey = keys.next();
                JSONObject datasetNode = meetingPlaceNode.getJSONObject(datasetKey);
                String url = datasetNode.getString("prod_web_url");
                List<String> entry = new ArrayList<>();
                entry.add(url);
                urls.add(entry);
            }
        }

        return urls;
    }

}
