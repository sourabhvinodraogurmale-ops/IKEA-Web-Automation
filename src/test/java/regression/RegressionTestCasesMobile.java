package regression;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.Message;
import util.WebConstants;
import util.WebOperations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RegressionTestCasesMobile extends BaseTest {

    /**
     * This test case is similar to the previous one but for mobile devices.
     * It verifies that the Accept All Cookies button disappears after clicking and remains absent even after the page is refreshed on a mobile device.
     */
    @Test
    public void verifyMobileCookiesNotDisplayedAfterRefresh() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Cookies Accept All Option");
        Homepage.clickHomePageCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the 'Accept All Cookies' button is visible initially.");
        Assert.assertTrue(WebOperations.isElementPresent("acceptallcookies"),Message.ACCEPT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Homepage.clickAcceptCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Clicked 'Accept All Cookies' button, verifying it's no longer visible.");
        Assert.assertFalse(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
        driver.navigate().refresh();
        ExtentTestManager.getTest().log(Status.INFO, "Page refreshed, verifying that 'Accept All Cookies' button is still not visible.");
        Assert.assertFalse(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
    }

    /**
     * This test case verifies that the homepage main banner carousel slides automatically on mobile devices.
     * It ensures that the banner headings change automatically and checks for duplicacy in the headings.
     */
    @Test
    public void verifyMobileHomepageBannerCarouselSlideAutomatically() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage main banner carousel slide automatically");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
        List<String> bannerHeadings = new ArrayList<>();  // Create a list to store banner headings
        Set<String> uniqueHeadings = new HashSet<>();  // Use a Set to check for duplicacy

        for (int i = 1; i < 3; i++) {
            String text = WebOperations.getWebElements("bannerheading").get(i).getText();
            System.out.println("banner heading: " + text);
            WebOperations.addScreenshotToExtentReport();
            ExtentTestManager.getTest().log(Status.INFO, "Verifying if banner changed Automatically");
            Assert.assertFalse(text.isEmpty(), Message.BANNER_HEADING_EMPTY_OR_NOT_CHANGING);
            uniqueHeadings.add(text);
            bannerHeadings.add(text);
            WebOperations.wait(WebConstants.WAIT_TIME_5_SEC);
        }
        Assert.assertEquals(bannerHeadings.size(), uniqueHeadings.size(), Message.DUPLICATE_BANNER_HEADINGS_FOUND);
    }

    /**
     * This test case verifies that all tenants are displayed when searched with the text 'Tenants' on mobile.
     * It checks the presence of the search results and the visibility of the searched results.
     */
    @Test
    public void verifyMobileTenantsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying all tenants are displayed when searched with the text 'Tenants'");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("Tenants");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"), Message.SEARCH_RESULTS_HEADING_NOT_PRESENT);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        if (!WebOperations.getWebElements("noresultforsearch").isEmpty()) {
            ExtentTestManager.getTest().log(Status.INFO, "No searched results are visible");
            return;
        }
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"), Message.NO_SEARCHED_RESULTS_VISIBLE);
    }

    /**
     * This test case verifies that all news are displayed when searched with the text 'News' on mobile.
     * It ensures that the search results heading is visible and that searched results are displayed.
     * If no results are found, it logs an appropriate message.
     */
    @Test
    public void verifyMobileNewsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying all news are displayed when searched with the text 'News'");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("News");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"), Message.SEARCH_RESULTS_HEADING_NOT_PRESENT);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        if (!WebOperations.getWebElements("noresultforsearch").isEmpty()) {
            ExtentTestManager.getTest().log(Status.INFO, "No searched results are visible");
            return;
        }
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"), Message.NO_SEARCHED_RESULTS_VISIBLE);
    }


    /**
     * This test case verifies that tapping on the subscribe button in the footer opens the newsletter subscription sign-up page on mobile devices.
     * It ensures that the "Join Our Newsletter" heading is visible after navigating to the page.
     */
    @Test
    public void verifyMobileNewsletterSubscriptionNavigation() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying tapping on subscribe opens a newsletter signup page");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.PASS, "Verifying navigating to the newsletter subscription sign-up page.");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
    }


    /**
     * This test case verifies that the 'Join Our Newsletter' page contains the necessary fields (Name, Email, and Confirm Email) on mobile.
     * It ensures that these fields are visible when navigating to the page.
     */
    @Test
    public void verifyMobileNewsletterSubscriptionFields() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying 'Join our newsletter' page options/fields");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.PASS, "Verifying visibility of name, email, and confirm email fields.");
        Assert.assertTrue(WebOperations.isElementPresent("nameinputfield"), Message.NAME_INPUT_FIELD_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("emailinputfield"), Message.EMAIL_INPUT_FIELD_NOT_VISIBLE);
    }

    /**
     * This test case verifies that the subscribe button remains inactive when mandatory fields are invalid or empty on mobile.
     * It ensures that the error messages for missing fields are visible and the button stays disabled when fields are empty.
     */
    @Test
    public void verifyMobileSubscribeButtonInactiveWhenMandatoryFieldsInvalid() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying subscribe button is inactive when mandatory fields are invalid or empty");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button is initially disabled");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_ENABLED);
        MainMenu.skippingAllTheFieldsEmpty();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the error messages for missing fields are visible");
        Assert.assertTrue(WebOperations.isElementPresent("missingnameerrmsg"), Message.MISSING_NAME_ERROR_MESSAGE_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("missingemailerrmsg"), Message.MISSING_EMAIL_ERROR_MESSAGE_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button is still disabled after clearing all fields");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_ENABLED);
    }

    /**
     * This test case verifies that the 'Thank You' message is displayed correctly after a successful subscription on mobile.
     * It ensures the 'Thank You' message appears after the user submits their details for the newsletter subscription.
     */
    @Test
    public void verifyMobileThankYouMessageForNewsLetterSubscription() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the Close button functionality in the 'Thank You' message");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        MainMenu.clickSubscribeButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Thank You' message is displayed after subscription");
        Assert.assertTrue(WebOperations.isElementPresent("newsletterthankyou!heading"));
    }

    /**
     * This test case verifies that the Close button in the 'Thank You' message works properly on mobile.
     * It ensures that the user can close the 'Thank You' message after subscribing to the newsletter.
     */
    @Test
    public void verifyMobileCloseButtonInThankYouMessage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the Close button functionality in the 'Thank You' message");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        MainMenu.clickSubscribeButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Thank You' message is displayed after subscription");
        Assert.assertTrue(WebOperations.isElementPresent("newsletterthankyou!heading"));
        MainMenu.clickCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Thank You' message is closed after clicking the Close button");
        Assert.assertFalse(WebOperations.isElementPresent("newsletterthankyou!heading"));
    }

    /**
     * This test case verifies that a confirmation email is sent to the registered email address after successfully subscribing to the newsletter on mobile.
     * It checks if the 'Thank You' message is displayed and verifies the email content in the inbox.
     */
    @Test
    public void verifyMobileNewsLetterSubscriptionEmailSuccess() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter subscription success and email sent to the registered ID");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        String userName = MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        MainMenu.clickSubscribeButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Thank You' message is displayed after subscription");
        Assert.assertTrue(WebOperations.isElementPresent("newsletterthankyou!heading"));
        Homepage.navigateToMailinator(userName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscription confirmation email is received");
        Assert.assertTrue(WebOperations.isElementPresent("subscriptionconfirmationmail"), "Email not received");
    }


    /**
     * This test case verifies that the correct newsletter subscription confirmation email is sent
     * after the user subscribes to the newsletter on mobile. It checks the email content and ensures
     * the correct subject line ("You’re almost there!").
     */
    @Test
    public void verifyMobileNewsLetterSubscriptionEmailMessage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Newsletter subscription email message");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        String userName = MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        MainMenu.clickSubscribeButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Thank You' message is displayed after subscription");
        Assert.assertTrue(WebOperations.isElementPresent("newsletterthankyou!heading"));
        Homepage.navigateToMailinator(userName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscription confirmation email is received");
        Assert.assertTrue(WebOperations.isElementPresent("subscriptionconfirmationmail"), "Email not received");
        Homepage.clickConfirmationMail();
        Assert.assertTrue(WebOperations.isElementVisible("mailcontentheading"), "Email heading is not correct, expected: 'You’re almost there!'");
    }

    /**
     * This test case verifies that CAPTCHA functionality is properly triggered on mobile after multiple newsletter subscriptions.
     * Similar to the web test, it checks that the Subscribe button is disabled after two subscription attempts, prompting CAPTCHA functionality.
     */
    @Test
    public void verifyingMobileCAPTCHAFunctionality() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying CAPTCHA function for newsletter subscription");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        for (int i = 0; i < 2; i++) {
            Homepage.clickNewsLetterFromFooter();
            Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
            MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
            MainMenu.clickSubscribeButton();
            Assert.assertTrue(WebOperations.isElementPresent("newsletterthankyou!heading"));
            MainMenu.clickCloseButton();
            Assert.assertFalse(WebOperations.isElementPresent("newsletterthankyou!heading"));
            ExtentTestManager.getTest().log(Status.INFO, "Newsletter subscription completed and 'Thank You' message closed.");
        }
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying CAPTCHA functionality is activated by checking for disabled subscribe button");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"));
    }

    /**
     * This test case verifies the field validation for the mobile newsletter subscription form.
     * It checks that the name field does not allow spaces and ensures that the first name is within the specified character limit.
     * It also validates that the email format follows the correct pattern.
     */
    @Test
    public void verifyMobileNewsletterSignupFormValidation() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying newsletter subscription field validation");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();

        // First name validation
        String firstName = "Automation Testinggggggggggggggggggggggggggggggggggggggg";
        WebOperations.clickAndType("nameinputfield", firstName);
        String valueEnteredInNameField = WebOperations.getWebElement("nameinputfield").getDomAttribute("value");

        Assert.assertFalse(valueEnteredInNameField.contains(" "), "Name should not contain spaces.");
        Assert.assertTrue(valueEnteredInNameField.length() <= 50, "First name should not exceed 60 characters.");

        // Email validation
        String email = "testingautomation00@mailinator.com";
        WebOperations.clickAndType("emailinputfield", email);
        String valueInputedInEmailField = WebOperations.getWebElement("emailinputfield").getDomAttribute("value");
        String emailRegex = "^\\w[\\w.-]*@([\\w-]+\\.)+[\\w-]+$";
        Assert.assertTrue(valueInputedInEmailField.matches(emailRegex), "Email does not match the required pattern.");
    }

    /**
     * This test case verifies the proper navigation between fields using the Tab key on the mobile version of the newsletter subscription form.
     * It ensures that focus moves from the name input field to the email input field when the Tab key is pressed.
     */
    @Test
    public void verifyMobileTabKeyNavigation() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying newsletter Tab key navigation");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        WebElement firstInputField = WebOperations.getWebElement("nameinputfield");
        WebElement secondInputField = WebOperations.getWebElement("emailinputfield");
        firstInputField.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.TAB).perform(); // Press the TAB key
        boolean isSecondInputFocused = secondInputField.equals(driver.switchTo().activeElement());
        Assert.assertTrue(isSecondInputFocused, "Focus did not move to the second input field");
    }


    /**
     * This test case ensures that the 'Subscribe' button on the mobile newsletter subscription form
     * is disabled after clicking the newsletter sign-up link.
     * It checks that the button is correctly disabled to prevent multiple submissions before user input.
     */
    @Test
    public void verifyMobileSubscribeButtonDisabled() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify subscribe button is disabled after clicking newsletter sign-up link");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), "Join Our Newsletter heading is not visible");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the subscribe button is disabled after navigating to the sign-up page.");
        Assert.assertTrue(WebOperations.isElementPresent("disabledsubscribebutton"), "Subscribe button is not disabled");
    }

    /**
     * This test case ensures that the mobile version of the 'Offers or Promotions' listing page
     * displays correctly. It verifies that the promotion heading and the promotion list are visible,
     * allowing users to see available promotions on their mobile devices.
     */
    @Test
    public void verifyMobilePromotionsListingPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Shop: Verify OffersOrPromotions listing page");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Offers Or Promotions Listing page");
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), "Promotion heading is not visible");
        Assert.assertTrue(WebOperations.isElementPresent("offersorpromotionlists"), Message.PROMOTION_LIST_NOT_VISIBLE);
    }

    /**
     * This test case ensures that the user can navigate to the 'Offers or Promotions' page
     * and view the promotion details on the mobile version. It checks that the promotion page
     * is displayed correctly and verifies that the promotion name matches on the detail page.
     */
    @Test
    public void verifyMobilePromotionsPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Shop: Verify ongoing OffersOrPromotions page");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), "Unable to navigate to Offers/Promotion page or Promotion heading is not visible");
        String promotionName = OffersOrPromotions.clickPromotionAndFetchPromotionName(0);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Offers Or Promotions Detail page");
        Assert.assertEquals(WebOperations.getText("detailedpageheading"), promotionName);
    }


    /**
     * This test case ensures that the search functionality on the mobile offers or promotions page
     * is working correctly. It checks if the search input is visible and if the correct promotion
     * is displayed when searched for.
     */
    @Test
    public void verifyMobilePromotionsPageSearchInput() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Shop: Verify that searching for OffersOrPromotions works from the OffersOrPromotions listing");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        String promotionName = WebOperations.getText("offersorpromotionlists");
        Tenant.clickAllFiltersMobile();
        boolean isSearchingPromotions = OffersOrPromotions.searchWithNameMobile(promotionName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search functionality for OffersOrPromotions.");
        Assert.assertTrue(isSearchingPromotions, "Search input field was not visible or not found");
        Assert.assertEquals(WebOperations.getWebElements("offersorpromotionlists").size(), 1, "More than one shop is visible after searching for the selected shop.");
        Assert.assertEquals(WebOperations.getText("offersorpromotionlists"), promotionName, "The filtered shop was not found in the search results.");
    }


    /**
     * This test case ensures that the mobile version of the shop listing page functions correctly.
     * It verifies the visibility of the shops heading, accurate search results, and proper handling of search filters.
     */
    @Test
    public void verifyMobileShopListingPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the shop listing page functionality");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        WebOperations.wait(4000);
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Homepage.clickMainMenu();
        MainMenu.clickMenuShopsButton();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the shops heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        Tenant.clickAndTypeFindShopInputFieldMobile("ea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search result for 'ea'");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"));
        Tenant.clickAndTypeFindShopInputFieldMobile("ikeo");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying no search matches message for 'ikeo'");
        Assert.assertTrue(WebOperations.isElementPresent("nosearchmatchesmsg"));
        Tenant.clickAndTypeFindShopInputFieldMobile("ikea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search result for 'ikea' again");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"));
        Tenant.clearSearchFieldAndSelectCategoryMobile();
        Tenant.clickAllFiltersMobileAndClearAll();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying filtered result have been cleared");
//        Assert.assertFalse(WebOperations.isElementVisible("visiblenumberonfilter"));
        Tenant.clickShowMoreIfVisible();
    }

    /**
     * This test case ensures that the mobile version of the Food & Beverage (F&B) tenant listing page works correctly.
     * It checks that the page navigates correctly, filters work, and that the filtered results are accurate.
     */
    @Test
    public void verifyMobileFAndBTenantListingPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify F&B Tenant Listing Page ");
        Homepage.clickHomePageCloseButton();
        Tenant.navigateToTheFAndBPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to Eat And Drink Page");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"), Message.FOOD_AND_BEVERAGE_HEADING_NOT_VISIBLE);
        Tenant.clickAllFiltersMobile();
        int filteredNumber = Tenant.clickCategoryAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "verifying filtered results");
        Assert.assertEquals(WebOperations.getWebElements("subtenants").size(), filteredNumber, "The number of filtered subtenants does not match the expected filtered number");
    }


    /**
     * This test case verifies that the mobile version of the special offers page displays correctly.
     * It checks the visibility of the special offers section and ensures the page scrolls to the special offers.
     */
    @Test
    public void verifyMobileSpecialOffersVisibility() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify special offers visibility ");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to offers Page and special offers visibility");
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), Message.OFFERS_HEADING_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("specialoffers"), "Special Offers are not present");
        WebOperations.scrollToElement("specialoffers");
    }

    /**
     * This test case verifies the functionality of the special offer detail page on the mobile version.
     * It ensures that the details (title and date) of the special offer match between the offer listing and the detail page.
     */
    @Test
    public void verifyMobileSpecialOffersDetailPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Special Offers Details page");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying visibility of Special Offers section.");
        Assert.assertTrue(WebOperations.isElementPresent("specialoffers"), Message.SPECIAL_OFFERS_NOT_VISIBLE);
        String[] specialOfferDetails = OffersOrPromotions.clickSpecialOfferAndFetchDetails();
        String specialOfferTitle = specialOfferDetails[0];
        String specialOfferDate = specialOfferDetails[1];
        String specialOfferDetailPageTitle = WebOperations.getText("specialoffersdetailpagetitle");
        String specialOfferDetailPageDate = WebOperations.getText("specialoffersdetailpagedate");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the Special Offer title and date on the detail page.");
        Assert.assertEquals(specialOfferDetailPageTitle, specialOfferTitle, Message.SPECIAL_OFFER_TITLE_MISMATCH);
        Assert.assertEquals(specialOfferDetailPageDate, specialOfferDate, Message.SPECIAL_OFFER_DATE_MISMATCH);
    }

    /**
     * This test case ensures that the mobile version of the special offers filter works correctly.
     * It verifies that the filtered special offers match the expected results.
     */
    @Test
    public void verifyMobileVerifySpecialOffersFilter() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Special Offers Filter");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        Assert.assertTrue(WebOperations.isElementPresent("specialoffers"), "Special Offers are not present");
        Tenant.clickAllFiltersMobile();
        int filterNumber = OffersOrPromotions.clickSpecialOffersAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the visible cards match the expected filtered results.");
        Assert.assertEquals(WebOperations.getWebElements("specialoffers").size(), filterNumber);
    }

    /**
     * This test case ensures that the mobile version of the special offers search functionality works as expected.
     * It verifies that searching for a specific special offer returns the correct results.
     */
    @Test
    public void verifyMobileVerifySpecialOffersSearchFunctionality() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify searching a Special Offer works fine");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        String specialOfferName = WebOperations.getText("specialofferscardtitle");
        Tenant.clickAllFiltersMobile();
        OffersOrPromotions.searchWithNameMobile(specialOfferName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search functionality for OffersOrPromotions.");
        Assert.assertEquals(WebOperations.getWebElements("specialofferscardtitle").size(), 1, "More than one special offer is visible after searching for the selected special offer.");
        Assert.assertEquals(WebOperations.getText("specialofferscardtitle"), specialOfferName, "The filtered special offer was not found in the search results.");
    }


    /**
     * This test case verifies the functionality of the download option for special offers on mobile.
     * It ensures that the Play Store tab opens when the user clicks on the download option for the special offer.
     */
    @Test
    public void verifyMobileDownloadOptionsForSpecialOffers() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Special Offers Details page");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying visibility of Special Offers section.");
        Assert.assertTrue(WebOperations.isElementPresent("specialoffers"), Message.SPECIAL_OFFERS_NOT_VISIBLE);
        String[] specialOfferDetails = OffersOrPromotions.clickSpecialOfferAndFetchDetails();
        String specialOfferTitle = specialOfferDetails[0];
        String specialOfferDetailPageTitle = WebOperations.getText("specialoffersdetailpagetitle");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Special Offer title '" + specialOfferTitle + "' on the detail page.");
        Assert.assertEquals(specialOfferDetailPageTitle, specialOfferTitle, Message.SPECIAL_OFFER_TITLE_MISMATCH);
        boolean isPlayStoreOpened = OffersOrPromotions.clickDownloadFromPlayStore();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the play store tab is open");
        Assert.assertTrue(isPlayStoreOpened,Message.PLAY_STORE_TAB_NOT_OPENED);
    }

    /**
     * This test case verifies that the package deals card is present and visible on the homepage of the mobile version.
     */
    @Test
    public void verifyMobilePackageDealsCardPresentHomescreen() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals card present in the Home screen");
        Homepage.clickHomePageCloseButton();
        Homepage.clickRejectCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the package deal card is visible on homepage.");
        Assert.assertTrue(WebOperations.isElementPresent("packagedealscard"), Message.PACKAGE_DEALS_CARD_NOT_VISIBLE);
        WebOperations.scrollToElement("packagedealscard");
    }

    /**
     * This test case verifies that the package deals listing page is accessible on mobile.
     * It checks the visibility of the package deal heading and the listing of package deals on mobile devices.
     */
    @Test
    public void verifyMobilePackageDealsListingPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals Listing page");
        Homepage.clickHomePageCloseButton();
        PackageDeals.navigateToTheLPOListingPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the package deal listing page");
        Assert.assertTrue(WebOperations.isElementPresent("packagedealslistingpageheading"), Message.PACKAGE_DEAL_HEADING_NOT_PRESENT);
        Assert.assertTrue(WebOperations.isElementPresent("packageofferslist"), Message.PACKAGE_DEALS_LISTING_NOT_PRESENT);
    }


    /**
     * This test case verifies that the package deal details page on mobile displays the correct title.
     * It ensures that the package deal title from the listing matches the title on the detail page.
     */
    @Test
    public void verifyMobilePackageDealsDetailPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals Detail Page");
        Homepage.clickHomePageCloseButton();
        PackageDeals.navigateToTheLPOListingPageMobile();
        Assert.assertTrue(WebOperations.isElementPresent("packageofferslist"), Message.PACKAGE_DEALS_LISTING_NOT_PRESENT);
        String packageDealTitle = PackageDeals.getPackageDealAndClick();
        String detailPageTitle = WebOperations.getText("packagedealdetailpagetitle");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the package deal detail page");
        Assert.assertEquals(detailPageTitle, packageDealTitle, Message.PACKAGE_DEAL_TITLE_MISMATCH);
    }

    /**
     * This test case verifies that clicking on the download option for a package deal on mobile takes the user to the Play Store.
     * It ensures that the Play Store tab opens successfully after clicking on the download button.
     */
    @Test
    public void verifyMobilePackageDealsDetailPageDownloadFromPlaystoreOptions() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals Details Page can take user to play store");
        Homepage.clickHomePageCloseButton();
        PackageDeals.navigateToTheLPOListingPageMobile();
        Assert.assertTrue(WebOperations.isElementPresent("packageofferslist"), Message.PACKAGE_DEALS_LISTING_NOT_PRESENT);
        boolean isPlayStoreOpened = OffersOrPromotions.clickDownloadFromPlayStore();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the play store tab is open");
        Assert.assertTrue(isPlayStoreOpened, Message.PLAY_STORE_TAB_NOT_OPENED);
    }

    /**
     * This test case verifies the visibility of the Ikea Family Members offer on mobile.
     * It ensures that the Ikea Family Members offers are present and visible on the offers page, and ensures that the user can scroll to it.
     */
    @Test
    public void verifyMobileIkeaFamilyMembersOfferVisibility() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Offer visibility");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to offers Page and Ikea Family Members Offers visibility");
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), Message.OFFERS_HEADING_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("ikeafamilymemberoffer"), Message.IKEA_FAMILY_MEMBER_OFFERS_NOT_VISIBLE);
        WebOperations.scrollToElement("ikeafamilymemberoffer");
    }


    /**
     * This test case verifies that the Ikea Family Members offer detail page on mobile displays the correct title and date.
     * It ensures that the offer title and date on the detail page match the offer details from the listing.
     */
    @Test
    public void verifyMobileIkeaFamilyMembersOfferDetailPage() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Offers Details page");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying visibility of Ikea Family Members Offer section.");
        Assert.assertTrue(WebOperations.isElementPresent("ikeafamilymemberoffer"), Message.IKEA_FAMILY_MEMBER_OFFERS_NOT_VISIBLE);
        String[] ikeaFamilyMemberOfferDetails = OffersOrPromotions.clickIkeaFamilyMembersOfferAndFetchDetails();
        String ikeaFamilyMemberOfferTitle = ikeaFamilyMemberOfferDetails[0];
        String ikeaFamilyMemberOfferDate = ikeaFamilyMemberOfferDetails[1];
        String ikeaFamilyMemberOfferDetailPageTitle = WebOperations.getText("ikeafamilymemberdetailpagetitle");
        String ikeaFamilyMemberOfferDetailPageDate = WebOperations.getText("ikeafamilymemberdetailpagedate");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the Ikea Family Members Offer title and date on the detail page.");
        Assert.assertEquals(ikeaFamilyMemberOfferDetailPageTitle, ikeaFamilyMemberOfferTitle, Message.IKEA_FAMILY_MEMBER_OFFER_TITLE_MISMATCH);
        Assert.assertEquals(ikeaFamilyMemberOfferDetailPageDate, ikeaFamilyMemberOfferDate, Message.IKEA_FAMILY_MEMBER_OFFER_DATE_MISMATCH);
    }

    /**
     * This test case verifies that the Ikea Family Members offers filter on mobile displays the correct filtered results.
     * It ensures that the number of visible offers matches the expected number after applying the filter.
     */
    @Test
    public void verifyMobileVerifyIkeaFamilyMembersOffersFilter() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Offers Filter");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        Tenant.clickAllFiltersMobile();
        int filterNumber = OffersOrPromotions.clickIkeaFamilyMemberAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the visible cards match the expected filtered results.");
        Assert.assertEquals(WebOperations.getWebElements("ikeafamilymemberoffer").size(), filterNumber, Message.FILTERED_OFFERS_COUNT_MISMATCH);
    }

    /**
     * This test case verifies the Ikea Family Members offers search functionality on mobile.
     * It ensures that the correct offer is displayed after searching for it by name.
     */
    @Test
    public void verifyMobileVerifyIkeaFamilyMembersOffersSearchFunctionality() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Offers Search Functionality");
        Homepage.clickHomePageCloseButton();
        OffersOrPromotions.navigateToTheOffersListingPageMobile();
        String ikeaFamilyMemberOfferName = WebOperations.getText("ikeafamilymembercardtitle");
        Tenant.clickAllFiltersMobile();
        OffersOrPromotions.searchWithNameMobile(ikeaFamilyMemberOfferName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search functionality for Ikea Family Member Offer.");
        Assert.assertEquals(WebOperations.getWebElements("ikeafamilymembercardtitle").size(), 1, Message.MORE_THAN_ONE_SHOP_VISIBLE);
        Assert.assertEquals(WebOperations.getText("ikeafamilymembercardtitle"), ikeaFamilyMemberOfferName, Message.SHOP_NOT_FOUND_IN_SEARCH_RESULTS);
    }
}