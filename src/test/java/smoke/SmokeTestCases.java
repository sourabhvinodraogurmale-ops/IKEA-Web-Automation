package smoke;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
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
public class SmokeTestCases extends BaseTest {

    /**
     * This test method verifies that the homepage loads successfully by checking the visibility of several key elements.
     * It performs assertions to ensure that the main menu, banner heading, footer links, and the "accept all cookies" button are all visible on the page.
     * The method logs the progress in the Extent report, indicating that the homepage is being verified.
     *
     * @throws AssertionError if any of the elements are not visible or do not load correctly.
     */
    @Test(priority = 1)
    public void verifyHomePageLoads() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify HomePage Loads");
        Assert.assertTrue(WebOperations.isElementPresent("mainmenu"), Message.MAIN_MENU_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("bannerheading"), Message.BANNER_HEADING_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("footerlinks"), Message.FOOTER_LINKS_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying homepage loads successfully");
    }

    /**
     * This test method verifies the functionality of the 'Accept All Cookies' button on the homepage.
     * It first checks if the 'Accept All Cookies' container is visible, then simulates clicking the button,
     * and finally ensures that the container is removed from the screen after the action.
     */
    @Test(priority = 2)
    public void verifyCookiesAcceptAllOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify cookies Accept All option");
        ExtentTestManager.getTest().log(Status.INFO, "Checking if 'Accept All Cookies' container is visible");
        Assert.assertTrue(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Homepage.clickAcceptCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if 'Accept All Cookies' container is removed from the screen");
        Assert.assertFalse(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_COOKIES_BUTTON_REMOVED);
    }


    /**
     * This test method verifies that the 'View on Map' option is displayed correctly for a tenant in the food and beverage section.
     * It ensures the page navigation works correctly and that the 'View on Map' option is visible when viewing the tenant's detailed page.
     */
    @Test(priority = 4)
    public void verifyViewOnMapOptionDisplayed() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify 'View on Map' option displayed");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to Eat And Drink Page");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"), Message.FOOD_AND_BEVERAGE_HEADING_NOT_VISIBLE);
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        WebOperations.addScreenshotToExtentReport();
        String shopName = Tenant.clickSubTenantAndGetTenantName();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying 'View on Map' option");
        Assert.assertEquals(WebOperations.getWebElement("detailedpageheading").getText(), shopName);
        Assert.assertTrue(WebOperations.isElementPresent("viewonmap"), Message.VIEW_ON_MAP_BLOCK_NOT_VISIBLE);
    }

    /**
     * This test method verifies the shop listing page's functionality.
     * It checks if the shop search functionality works by verifying results for "Ikea", as well as ensuring the 'No shops found' message is shown when no results are returned.
     */
    @Test(priority = 5)
    public void verifyShopListingPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify shop listing page");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the 'Tenant' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"), Message.SHOPS_HEADING_NOT_VISIBLE);
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Tenant.clickAndTypeFindShopInputField("ea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the 'Ikea' card appears in the search results");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"), Message.KEA_CARD_NOT_VISIBLE);
        Tenant.clickAndTypeFindShopInputField("ikeo");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the 'No shops found' message is displayed");
        Assert.assertTrue(WebOperations.isElementPresent("nosearchmatchesmsg"), Message.NO_SHOPS_FOUND_MESSAGE_NOT_VISIBLE);
        Tenant.clickAndTypeFindShopInputField("ikea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the 'Ikea' card reappears after searching 'ikea' again");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"), Message.KEA_CARD_NOT_VISIBLE);
        Tenant.clearSearchFieldAndSelectCategory();
        Assert.assertEquals(WebOperations.getText("visiblenumberonfilter"), "1", Message.FILTER_COUNT_NOT_CORRECT);
        Tenant.clickAllFiltersAndClearAll();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the filter count is no longer visible after clearing all filters");
        Assert.assertFalse(WebOperations.isElementPresent("visiblenumberonfilter"), Message.FILTER_COUNT_VISIBLE_AFTER_CLEARING);
    }

    /**
     * This test method verifies that tenants with offers have a visual indicator on their card.
     * It checks if the filter results are correct and ensures the visual indicator appears for all filtered results.
     */
    @Test(priority = 6)
    public void verifyShopsWithOffersIndicator() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify All Tenant/Tenants that have an Offer will now have a visual indicator");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Tenant.clickAllFilters();
        int filteredNumber = Tenant.clickOffersAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "verifying offers visual indicator is present for all the filtered results");
        Assert.assertFalse(WebOperations.isElementPresent("filters"), Message.FILTER_HEADING_VISIBILITY);
        Assert.assertEquals(WebOperations.getWebElements("subtenants").size(), filteredNumber, "The number of filtered subtenants does not match the expected filtered number");
        Assert.assertEquals(WebOperations.getWebElements("offersvisualindicator").size(), filteredNumber, "The number of visual indicator icons displayed does not match the expected filtered number");
    }

    /**
     * This test method verifies that the logo is displayed at the top left corner of the page inside the shops section.
     * It ensures that the logo is visible and correctly positioned on the page.
     */
    @Test(priority = 7)
    public void verifyLogoDisplayedTopLeft() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Logo displayed on Top Left Corner of the page ");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Tenant page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ExtentTestManager.getTest().log(Status.INFO, "Verifying meeting place logo on top left corner of the page");
        Assert.assertTrue(WebOperations.isElementPresent("meetingplacelogoshops"), "Meeting place logo is not present inside Tenant");
    }

    /**
     * This test method verifies that the meeting place name and today's opening time are displayed at the top right corner of the page.
     * It ensures that both elements are visible when viewing the shops page.
     */
    @Test(priority = 8)
    public void verifyTodaysOpeningTimeAndMeetingPlaceNameDisplayedTopRight() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify 'Today's Opening Time' And 'Meeting Place' displayed on 'Top Right' Corner of the page ");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Tenant page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ExtentTestManager.getTest().log(Status.INFO, "Verifying meeting place name and today's opening time on top left corner of the page");
        Assert.assertTrue(WebOperations.isElementPresent("meetingplacenameandtodaysopeningtimeshops"), "Meeting place Name and Today's Opening Time is not present inside Tenant");
    }


    /**
     * This test method verifies that when the 'Show More' button is clicked on the tenant listing page, additional tenant cards are displayed.
     * It checks that the number of tenant cards increases after clicking the 'Show More' button.
     */
    @Test(priority = 10)
    public void verifyShowMoreButtonDisplaysMoreCards() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify tapping/clicking on 'show more' displays more cards ");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Tenant page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        int currentShopsCardSize = WebOperations.getWebElements("subtenants").size();
        boolean showMoreButton = Tenant.clickShowMoreIfVisible();
        if (showMoreButton) {
            int shopsCardSizeAfterClickingShowMoreButton = WebOperations.getWebElements("subtenants").size();
            ExtentTestManager.getTest().log(Status.INFO, "Verifying Clicking 'show more' displays more cards");
            Assert.assertTrue(shopsCardSizeAfterClickingShowMoreButton > currentShopsCardSize, "More Tenant not displayed after clicking show more button");
        }
    }

    /**
     * This test method ensures that the menu navigation options for the tenant, food and beverage, events, and services pages work correctly.
     * It verifies that clicking on the main menu links leads to the respective pages (Shops, Food & Beverage, Events, and Services).
     */
    @Test(priority = 11)
    public void verifyMenuNavigation() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Menu Navigation options works ");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Tenant page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Eat And Drink page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"));
        Homepage.clickMainMenu();
        MainMenu.clickEventsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Events page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("eventsheading"));
        Homepage.clickMainMenu();
        MainMenu.clickServicesLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Services page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("servicesheading"));
    }

    /**
     * This test method checks if the footer links on the homepage are functioning correctly. It clicks each footer link and verifies
     * that it leads to the correct detailed page.
     */
    @Test(priority = 12)
    public void VerifyFooterLinks() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Footer links/options works");
        Homepage.clickRejectCookies();
        WebOperations.scrollToElement("footerlinks");
        WebOperations.addScreenshotToExtentReport();
        for (int i = 0; i < 2; i++) {
            List<WebElement> footerLinkList = WebOperations.getWebElements("footerlinks");
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            String linkText = footerLinkList.get(i).getText();
            footerLinkList.get(i).click();
            ExtentTestManager.getTest().log(Status.INFO, "Clicking footer link : " + linkText);
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            Assert.assertEquals(WebOperations.getText("detailedpageheading"), linkText);
            ExtentTestManager.getTest().log(Status.INFO, "Verifying navigating to the expected page");
            WebOperations.addScreenshotToExtentReport();
            driver.navigate().back();
            WebOperations.addScreenshotToExtentReport();
        }
    }

    /**
     * This test method ensures that the Meeting Place logo in the main menu is clickable and correctly navigates back to the homepage.
     */
    @Test(priority = 13)
    public void verifyWebMpMenuLogoClickable() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Mp Menu Logo Clickable");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Meeting Place Logo inside menu page");
        Assert.assertTrue(WebOperations.isElementPresent("meetingplacelogomenu"), "Meeting Place logo is not present inside menu");
        MainMenu.clickMeetingplaceLogo();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying navigating to the homepage");
        Assert.assertFalse(WebOperations.isElementPresent("closemenubutton"), Message.UNABLE_TO_NAVIGATE_TO_HOMEPAGE);
    }

    /**
     * This test method verifies that the subscribe button remains inactive when mandatory fields are invalid or empty on the web version.
     * It checks that error messages are displayed for the missing fields.
     */
    @Test(priority = 15)
    public void verifyWebSubscribeButtonInactiveWhenMandatoryFieldsInvalid() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying subscribe button inactive when mandatory fields are invalid or empty");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickNewsletterSubscriptionLink();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"));
        MainMenu.skippingAllTheFieldsEmpty();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying error messages and ensuring subscribe button remains inactive when mandatory fields are invalid or empty");
        Assert.assertTrue(WebOperations.isElementPresent("missingnameerrmsg"), Message.MISSING_NAME_ERROR_MESSAGE_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("missingemailerrmsg"), Message.MISSING_EMAIL_ERROR_MESSAGE_NOT_VISIBLE);
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_ENABLED);
    }


    /**
     * This test method ensures that the subscribe button is disabled after clicking the newsletter sign-up link in the web version.
     */
    @Test(priority = 18)
    public void verifyWebSubscribeButtonDisabled() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify subscribe button is disabled after clicking newsletter sign-up link");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.PASS, "Verifying that the subscribe button is disabled after navigating to the sign-up page.");
        Assert.assertTrue(WebOperations.isElementPresent("disabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_NOT_DISABLED);
    }

    @Test
    public void verifyEventListingPage(){
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify event listing page");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        WebOperations.clickElement("eventslink");
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        int totalEventsPresnt = WebOperations.getWebElements("subevents").size();
        System.out.println("size of the events card : " + totalEventsPresnt);
        Assert.assertTrue(totalEventsPresnt > 0, "No events found on event listing page");
        if(WebOperations.isElementPresent("showmore")){
            WebOperations.clickElement("showmore");
        }
        totalEventsPresnt = WebOperations.getWebElements("subevents").size();
        System.out.println("size of the events card : " + totalEventsPresnt);
    }
}

