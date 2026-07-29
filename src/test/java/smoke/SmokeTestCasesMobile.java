package smoke;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Homepage;
import pages.MainMenu;
import pages.Tenant;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.Message;
import util.WebConstants;
import util.WebOperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmokeTestCasesMobile extends BaseTest {


    /**
     * This test method verifies that the homepage banner carousel on mobile automatically changes the slide.
     * It checks if the banner heading changes by verifying that the text content is not empty and that it changes over time.
     * It also ensures that no duplicate banner headings are shown.
     */
    @Test()
    public void verifyMobileHomepageBannerCarouselSlideAutomatically() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage main banner carousel slide automatically");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        List<String> bannerHeadings = new ArrayList<>();
        Set<String> uniqueHeadings = new HashSet<>();
        for (int i = 1; i < 3; i++) {
            String text = WebOperations.getWebElements("bannerheading").get(i).getText();
            System.out.println("banner heading: " + text);
            WebOperations.addScreenshotToExtentReport();
            ExtentTestManager.getTest().log(Status.INFO, "Verifying if banner changed Automatically");
            Assert.assertFalse(text.isEmpty(), Message.BANNER_HEADING_EMPTY_OR_NOT_CHANGED);
            uniqueHeadings.add(text);
            bannerHeadings.add(text);
            WebOperations.wait(WebConstants.WAIT_TIME_5_SEC);
        }
        if(!WebOperations.isElementEmpty("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Assert.assertEquals(bannerHeadings.size(), uniqueHeadings.size(), Message.DUPLICATE_BANNER_HEADINGS_FOUND);
    }


    /**
     * This test method verifies that the layout of cards is displayed in a single column for mobile view.
     * It ensures that the layout is responsive and adjusts to mobile screen sizes.
     */
    @Test()
    public void verifySingleColumnLayoutForMobile() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify able to view all the card Layout in Single Column ");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Homepage.clickMainMenu();
        MainMenu.clickMenuShopsButton();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Tenant page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        ExtentTestManager.getTest().log(Status.INFO, "Verifying card layout in single column for mobile view");
        Assert.assertTrue(WebOperations.isElementPresent("tenantscardmobile"), "The layout is not in a single column on mobile.");
    }


    /**
     * This test method ensures that the Meeting Place logo in the mobile menu is clickable and correctly navigates back to the homepage.
     */
    @Test()
    public void verifyMobileMenutoHomepageNavigation() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Mp Menu Logo Clickable");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickMeetingplaceLogoHomepage();
        WebOperations.wait(3000);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying navigating to the homepage");
        Assert.assertFalse(WebOperations.isElementVisible("closemenubutton"), Message.UNABLE_TO_NAVIGATE_TO_HOMEPAGE);
    }


    /**
     * This test method verifies that the subscribe button remains inactive when mandatory fields are invalid or empty on the mobile version.
     * It checks that error messages are displayed for the missing fields.
     */
    @Test(priority = 16)
    public void verifyMobileSubscribeButtonInactiveWhenMandatoryFieldsInvalid() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying subscribe button inactive when mandatory fields are invalid or empty");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        MainMenu.skippingAllTheFieldsEmpty();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying error messages and ensuring subscribe button remains inactive when mandatory fields are invalid or empty");
        Assert.assertTrue(WebOperations.isElementPresent("missingnameerrmsg"), Message.MISSING_NAME_ERROR_MESSAGE_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("missingemailerrmsg"), Message.MISSING_EMAIL_ERROR_MESSAGE_NOT_VISIBLE);
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_ENABLED);
    }

    /**
     * This test method ensures that the subscribe button is disabled after clicking the newsletter sign-up link in the mobile version.
     */
    @Test(priority = 17)
    public void verifyMobileSubscribeButtonDisabled() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify subscribe button is disabled after clicking newsletter sign-up link");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.PASS, "Verifying that the subscribe button is disabled after navigating to the sign-up page.");
        Assert.assertTrue(WebOperations.isElementPresent("disabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_NOT_DISABLED);
    }
}

