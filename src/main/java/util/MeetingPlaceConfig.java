package util;

import lombok.Getter;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.WebOperations.mConfigProps;

public class MeetingPlaceConfig {

    private static final JSONObject testdata = new JSONObject(WebOperations.getJsonTestData());
    private static String meetingplace = "";
    private static String url = "";
    private static String language = "";

    /**
     * Sets the meeting place from system properties or configuration properties if not already set.
     */
    public static void setMeetingplace() {
        if (meetingplace == null || meetingplace.isEmpty()) {
            String terminalMeetingPlace = System.getProperty("meetingplace");
            if (terminalMeetingPlace != null && !terminalMeetingPlace.isEmpty()) {
                meetingplace = terminalMeetingPlace;
            } else {
                meetingplace = mConfigProps.getProperty("MeetingPlaceAndLanguage");
            }
        }
    }

    /**
     * Gets the meeting place in lowercase. Ensures the meeting place is set before returning.
     *
     * @return the meeting place in lowercase
     */
    public static String getMeetingplace() {
        setMeetingplace();
        return meetingplace;
    }


    /**
     * Gets the URL for the meeting place. If not already set, it retrieves the URL from system properties or test data based on the environment.
     *
     * @return the URL for the meeting place
     */
    public static String getMeetingPlaceUrl() {
        try {
            String mpNameAndLanguage = System.getProperty("mpNameAndLanguage");
            url = mpNameAndLanguage != null ? testdata.getJSONObject("MeetingPlaces").getJSONObject(mpNameAndLanguage).getString("url") : testdata.getJSONObject("MeetingPlaces").getJSONObject(getMeetingplace()).getString("url");
            return url;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * Gets the language from the URL using a regex pattern.
     *
     * @return the language extracted from the URL, or null if not found
     */
    public static String getMeetingPlaceLanguage() {
        String regex = "/([a-zA-Z]{2,3})/?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()) {
            language = matcher.group(1);
            return language;
        }
        return null;
    }
}