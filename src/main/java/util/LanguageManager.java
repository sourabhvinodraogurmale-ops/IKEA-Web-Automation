package util;

import org.json.JSONObject;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LanguageManager {

    private static JSONObject languageData;
    private static String currentLang;

    public static void loadLanguage(String lang) {
        currentLang = lang;

        try {
            InputStream is = LanguageManager.class
                    .getClassLoader()
                    .getResourceAsStream("localization/" + lang + ".json");

            if (is == null) {
                throw new RuntimeException("Language file not found: " + lang);
            }

            String content = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            languageData = new JSONObject(content);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load language file: " + lang, e);
        }
    }

    public static String getValue(String key) {
        return languageData
                .getJSONObject(currentLang)
                .getString(key);
    }
}