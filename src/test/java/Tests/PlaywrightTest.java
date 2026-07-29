package Tests;

import Tests.BaseTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PlaywrightTest {

    private Page createPage(Playwright playwright) {
        Browser browser = playwright.chromium()
                .launch(new com.microsoft.playwright.BrowserType.LaunchOptions().setHeadless(false));
        return browser.newPage();
    }

    @Test public void testPlaywright() {
        try (Playwright playwright = Playwright.create()) {
            Page page = createPage(playwright);

            page.navigate("https://www.marshopping.com");

            String actualTitle = page.title();
            System.out.println("Page title: " + actualTitle);
            Assert.assertEquals(actualTitle, "Bem-vindo ao MAR Shopping", "Page title validation failed");

            Assert.assertTrue(page.url().contains("marshopping.com"), "URL validation failed");

            page.context().browser().close();
        }
    }
}

