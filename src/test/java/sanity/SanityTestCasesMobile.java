package sanity;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Homepage;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.WebConstants;
import util.WebOperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SanityTestCasesMobile extends BaseTest {


    /**
     * This test method ensures that searched results are displayed when searching for the term "Events" on the mobile version of the site.
     */
    @Test
    public void verifyMobileEventsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'Events'");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("Events");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
    }


    /**
     * This test method ensures that searched results are displayed when searching for the term "Tenant" on the mobile version of the site.
     */
    @Test
    public void verifyMobileShopsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'Shops'");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("Shops");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
    }


    /**
     * This test method ensures that searched results are displayed when searching for the term "Tenants" on the mobile version of the site.
     */
    @Test
    public void verifyMobileTenantsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'Tenants'");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("Tenants");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
    }


    /**
     * This test method verifies that searched results are displayed correctly when searched with the text 'News' on mobile.
     */
    @Test
    public void verifyMobileNewsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'News'");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        WebOperations.wait(4000);
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("News");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
    }

    /**
     * This test method verifies that the homepage main banner carousel slides automatically on mobile.
     * It ensures that the banner headings are unique and the carousel slides without duplicates.
     */
    @Test
    public void verifyMobileHomepageBannerCarouselSlideAutomatically() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage main banner carousel slide automatically");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        List<String> bannerHeadings = new ArrayList<>();
        Set<String> uniqueHeadings = new HashSet<>();

        for (int i = 1; i < 3; i++) {
            String text = WebOperations.getWebElements("bannerheading").get(i).getText();
            ExtentTestManager.getTest().log(Status.INFO, "Banner heading: " + text);
            WebOperations.addScreenshotToExtentReport();
            Assert.assertFalse(text.isEmpty(), "Banner Heading is empty or banner is not changing automatically");
            uniqueHeadings.add(text);
            bannerHeadings.add(text);
            WebOperations.wait(WebConstants.WAIT_TIME_5_SEC);

        }
        if(!WebOperations.isElementEmpty("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Assert.assertEquals(bannerHeadings.size(), uniqueHeadings.size(), "There are duplicate banner headings.");
        ExtentTestManager.getTest().log(Status.INFO, "Mobile Homepage banner carousel slid automatically, and banners are unique.");
    }

}