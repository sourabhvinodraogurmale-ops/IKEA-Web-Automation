package Tests;

import com.microsoft.playwright.*;
import com.microsoft.playwright.Page;

public class PracticePL {

    public static void main(String [] args) {

        Playwright pw = Playwright.create();
        Browser browser = pw.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        BrowserContext bc = browser.newContext();
        Page page = bc.newPage();
        page.navigate("https://www.google.com");

        String pt = page.title();
        System.out.println(pt);
        browser.close();
        pw.close();

    }
}
