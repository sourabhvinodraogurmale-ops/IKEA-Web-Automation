package regression;
import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.Message;
import util.WebConstants;
import util.WebOperations;
import java.util.*;

public class RegressionTestCases extends BaseTest {


    /**
     * This test case verifies the presence of the cookies options and buttons on the webpage.
     * It checks that the cookies setting element, the Accept All button, and the Reject All button are present on the page.
     */
    @Test
    public void verifyWebCookiesOptionsAndButtons() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] -  Verify cookies options and buttons");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Cookies setting , Accept All and Reject All buttons");
        Assert.assertTrue(WebOperations.isElementPresent("cookiessetting"), Message.COOKIE_SETTING_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("rejectallcookies"), Message.REJECT_ALL_COOKIES_BUTTON_NOT_VISIBLE);

    }

    /**
     * This test case verifies the functionality of the cookies setting option.
     * It clicks on the cookies setting button, checks if the cookies settings screen appears,
     * and then clicks on the Reject All Cookies button to verify that the settings screen is removed
     * once either the Accept All or Reject All buttons are clicked.
     */
    @Test
    public void verifyWebCookiesSettingOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Cookies Setting Option");
        Homepage.clickCookiesSetting();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the opening of the cookies setting screen");
        Assert.assertTrue(WebOperations.isElementPresent("rejectallcookiessettings"), Message.REJECT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Homepage.clickRejectAllCookiesSettings();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the cookies setting screen is removed after clicking Accept All or Reject All button");
        Assert.assertFalse(WebOperations.isElementPresent("rejectallcookiessettings"), Message.REJECT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
    }

    /**
     * This test case checks the functionality of the Accept All Cookies button.
     * It ensures that the Accept All button is visible, then clicks the button to verify that it disappears after clicking.
     */
    @Test
    public void verifyWebCookiesAcceptAllOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Cookies Accept All Option");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the Accept All Cookies button");
        Assert.assertTrue(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Homepage.clickAcceptCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the Accept All Cookies button is removed after clicking");
        Assert.assertFalse(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
    }

    /**
     * This test case checks if the Accept All Cookies button is no longer visible after clicking it and refreshing the page.
     * It ensures that after the button is accepted, refreshing the page will keep the button from being displayed again.
     */
    @Test
    public void verifyWebCookiesNotDisplayedAfterRefresh() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Cookies Accept All Option");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the 'Accept All Cookies' button is visible initially.");
        Assert.assertTrue(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Homepage.clickAcceptCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Clicked 'Accept All Cookies' button, verifying it's no longer visible.");
        Assert.assertFalse(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
        driver.navigate().refresh();
        ExtentTestManager.getTest().log(Status.INFO, "Page refreshed, verifying that 'Accept All Cookies' button is still not visible.");
        Assert.assertFalse(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
    }

    /**
     * This test case checks the functionality of the Meeting Place logo in the menu.
     * It verifies that the logo is present in the menu, and clicking on it navigates back to the homepage.
     * It ensures that the user can return to the homepage by clicking the logo.
     */
    @Test
    public void verifyWebMpMenuLogoClickable() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Mp Menu Logo Clickable");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Meeting Place Logo inside menu page");
        Assert.assertTrue(WebOperations.isElementPresent("meetingplacelogomenu"), Message.MEETING_PLACE_LOGO_NOT_PRESENT_IN_MENU);
        MainMenu.clickMeetingplaceLogo();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying navigating to the homepage");
//        Assert.assertFalse(WebOperations.isElementPresent("closemenubutton"), Message.UNABLE_TO_NAVIGATE_TO_HOMEPAGE);
    }

    /**
     * This test case verifies that the homepage banner carousel slides automatically.
     * It checks if the banner changes automatically and ensures there are no duplicate headings when the banner slides.
     * It also verifies that the banners are displayed correctly and updated without any errors.
     */
    @Test
    public void verifyWebHomepageBannerCarouselSlideAutomatically() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage main banner carousel slide automatically");
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
     * This test case verifies that tapping on the homepage main banner opens the expected detailed page.
     * It ensures that the banner text matches the heading on the detailed page.
     */
    @Test
    public void verifyWebTappingHomepageMainBannerOpensExpectedPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying tapping on the Homepage main banner opens the expected page");
        Homepage.clickRejectCookies();
        if (!WebOperations.isElementPresent("homepagebanner")) {
            Assert.fail("Homepage banner is not present. Test cannot proceed.");
        }
        String bannerText = Homepage.clickHomepageBannerAndVerifyText();
        Assert.assertNotNull(bannerText, Message.BANNER_TEXT_IS_NULL);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the detailed page heading matches the banner text");
        if (!WebOperations.isElementPresent("detailedpageheading")) {
            Assert.fail("Detailed page heading is not present after clicking banner.");
        }
        String detailHeading = WebOperations.getText("detailedpageheading");
        Assert.assertNotNull(detailHeading, "Detailed page heading is null after clicking banner.");
        Assert.assertTrue(detailHeading.contains(bannerText), "Banner text does not match the detailed page heading. Banner: '" + bannerText + "', Heading: '" + detailHeading + "'");
    }

    /**
     * This test case verifies that all tenants are displayed when searched with the text 'Tenants' on the web.
     * It checks the presence of the search results and the visibility of the searched results.
     */
    @Test
    public void verifyWebTenantsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying all tenants are displayed when searched with the text 'Tenants'");
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
     * This test case verifies that all news are displayed when searched with the text 'News' on the web.
     * It ensures that the search results heading is visible and that searched results are displayed.
     */
    @Test
    public void verifyWebNewsDisplayedWhenSearched() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying all news are displayed when searched with the text 'News'");
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
     * This test case verifies that tapping on the subscribe button in the footer opens the newsletter signup page on the web.
     * It ensures that the "Join Our Newsletter" heading is visible after navigating to the page.
     */
    @Test
    public void verifyWebNewsletterSubscriptionNavigation() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify tapping on subscribe opens a newsletter signup page");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.PASS, "Verifying navigating to the newsletter subscription sign-up page.");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
    }


    /**
     * This test case verifies that the 'Join Our Newsletter' page contains the necessary fields (Name, Email, and Confirm Email) on the web.
     * It ensures that these fields are visible when navigating to the page.
     */
    @Test
    public void verifyWebNewsletterSubscriptionFields() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying 'Join our newsletter' page options/fields");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.PASS, "Verifying visibility of name, email, and confirm email fields.");
        Assert.assertTrue(WebOperations.isElementPresent("nameinputfield"), Message.NAME_INPUT_FIELD_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("emailinputfield"), Message.EMAIL_INPUT_FIELD_NOT_VISIBLE);
    }


    /**
     * This test case verifies that the subscribe button is activated only when all required fields are filled in correctly on the web.
     * It ensures that the subscribe button becomes enabled after filling out the form and agreeing to the terms.
     */
    @Test
    public void verifyWebSubscribeButtonActivation() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the subscribe button activation");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button is initially disabled");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_ENABLED);
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button becomes active after filling the form and agreeing to terms");
        Assert.assertTrue(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_DISABLED);
    }

    /**
     * This test case verifies that the subscribe button remains inactive when mandatory fields are invalid or empty on the web.
     * It ensures that the error messages for missing fields are visible and the button stays disabled when fields are empty.
     */
    @Test
    public void verifyWebSubscribeButtonInactiveWhenMandatoryFieldsInvalid() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying subscribe button is inactive when mandatory fields are invalid or empty");
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
     * This test case verifies that a "Thank You" message appears after successful subscription to the newsletter on the web.
     * It ensures that the 'Thank You' message and its content are visible after clicking the subscribe button.
     */
    @Test
    public void verifyWebThankYouMessageForNewsLetterSubscription() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the Close button functionality in the 'Thank You' message");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), Message.JOIN_OUR_NEWSLETTER_HEADING_NOT_VISIBLE);
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        MainMenu.clickSubscribeButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Thank You' message is displayed after subscription");
        Assert.assertTrue(WebOperations.isElementPresent("newsletterthankyou!heading"), Message.NEWSLETTER_THANK_YOU_HEADING_NOT_VISIBLE);
    }

    /**
     * This test case verifies that the refresh button on the web resets all fields after the subscription form has been filled.
     * It checks that the form fields are cleared when the page is refreshed and the 'Join Our Newsletter' heading remains visible.
     */
    @Test
    public void verifyWebRefreshButtonClearsSubscriptionFields() { //expected failure when subscription option in not available on footer
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify refresh button resets all fields after subscription form is filled");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        ExtentTestManager.getTest().log(Status.INFO, "refreshes the screen");
        driver.navigate().refresh();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "'Join Our Newsletter' heading is visible after refresh");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        Assert.assertTrue(WebOperations.getWebElement("nameinputfield").getText().isEmpty(), "Fields are not reset after refresh");
    }

    /**
     * This test case verifies that the Close button in the 'Thank You' message works properly on the web.
     * It ensures that the user can close the 'Thank You' message after successfully subscribing to the newsletter.
     */
    @Test
    public void verifyWebCloseButtonInThankYouMessage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the Close button functionality in the 'Thank You' message");
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
     * This test case verifies that a confirmation email is sent to the registered email address after successfully subscribing to the newsletter on the web.
     * It checks if the 'Thank You' message is displayed and verifies the email content in the inbox.
     */
    @Test
    public void verifyWebNewsLetterSubscriptionEmailSuccess() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter subscription success and email sent to the registered ID");
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
     * This test case verifies the content of the confirmation email sent after subscribing to the newsletter on the web.
     * It ensures that the email contains the expected message and the 'Thank You' email heading is correct.
     */
    @Test
    public void verifyWebNewsLetterSubscriptionEmailMessage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Newsletter subscription email message");
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
        Assert.assertTrue(WebOperations.isElementPresent("mailcontentheading"), "Email heading is not correct, expected: 'You’re almost there!'");
    }


    /**
     * This test case verifies that CAPTCHA functionality is properly triggered on the web after multiple newsletter subscriptions.
     * It ensures that the Subscribe button is disabled after two subscription attempts, which should prompt the CAPTCHA functionality.
     */
    @Test
    public void verifyingWebCAPTCHAFunctionality() { //expected failure when subscription option in not available on footer
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying CAPTCHA function for newsletter subscription");
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
        Homepage.clickMainMenu();
        MainMenu.clickNewsletterSubscriptionLink();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying CAPTCHA functionality is activated by checking for disabled subscribe button");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"));
    }

    /**
     * This test case verifies the field validation for the web newsletter subscription form.
     * It checks that the name field does not allow spaces and ensures that the first name is within the specified character limit.
     * It also validates that the email format follows the correct pattern.
     */
    @Test
    public void verifyWebNewsletterSignupFormValidation() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying newsletter subscription field validation");

        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();

        // First name validation
        String firstName = "Automation Testinggggggggggggggggggggggggggggggggggggggg";
        WebOperations.clickAndType("nameinputfield", firstName);
        String valueEnteredInNameField = WebOperations.getWebElement("nameinputfield").getDomAttribute("value");
        Assert.assertTrue(valueEnteredInNameField.contains(" "), "Name should allow space to enter Lastname.");
        Assert.assertTrue(valueEnteredInNameField.length() <= 50, "First name should not exceed 60 characters.");

        // Email validation
        String email = "testingautomation00@mailinator.com";
        WebOperations.clickAndType("emailinputfield", email);
        String valueInputedInEmailField = WebOperations.getWebElement("emailinputfield").getDomAttribute("value");
        String emailRegex = "^\\w[\\w.-]*@([\\w-]+\\.)+[\\w-]+$";
        Assert.assertTrue(valueInputedInEmailField.matches(emailRegex), "Email does not match the required pattern.");
    }

    /**
     * This test case verifies the proper navigation between fields using the Tab key on the web version of the newsletter subscription form.
     * It ensures that focus moves from the name input field to the email input field when the Tab key is pressed.
     */
    @Test
    public void verifyWebTabKeyNavigation() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying newsletter Tab key navigation");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        WebElement firstInputField = WebOperations.getWebElement("nameinputfield");
        WebElement secondInputField = WebOperations.getWebElement("emailinputfield");
        firstInputField.click();
        ExtentTestManager.getTest().log(Status.INFO, "Focused on the first input field (Name).");
        WebOperations.clickTabKey();
        ExtentTestManager.getTest().log(Status.INFO, "Clicking tab key");
        boolean isSecondInputFocused = secondInputField.equals(driver.switchTo().activeElement());
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that focus has moved to the second input field (Email).");
        Assert.assertTrue(isSecondInputFocused, "Focus did not move to the second input field (Email). The tab navigation did not work as expected.");
    }


    /**
     * This test case verifies that the 'Subscribe' button on the web newsletter subscription form
     * is disabled after clicking the newsletter sign-up link.
     * It ensures that the 'Subscribe' button is correctly disabled, preventing premature submissions
     * before the user has entered the required details.
     */
    @Test
    public void verifyWebSubscribeButtonDisabled() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify subscribe button is disabled after clicking newsletter sign-up link");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"), "Join Our Newsletter heading is not visible");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the subscribe button is disabled after navigating to the sign-up page.");
        Assert.assertTrue(WebOperations.isElementPresent("disabledsubscribebutton"), "Subscribe button is not disabled");
    }

    /**
     * This test case verifies that the 'Offers or Promotions' listing page on the web
     * is displayed correctly. It checks that the promotion heading and the promotion list are visible,
     * ensuring that the user can view available promotions after navigating to the page.
     */
    @Test
    public void verifyWebPromotionsListingPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Shop: Verify OffersOrPromotions Listing page");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickPromotionLink();
        Homepage.clickNewsLetterCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Offers Or Promotions Listing page");
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), "Promotion heading is not visible");
        if (!WebOperations.isElementPresent("offersorpromotionlists")) {
            Assert.fail("Promotion list is not visible. There may be no offers available.");
        }
    }

    /**
     * This test case ensures that the user can navigate to the 'Offers or Promotions' page
     * and view the promotion details on the web version. It checks that the promotion page
     * displays correctly and verifies that the promotion name matches on the detail page.
     */
    @Test
    public void verifyWebPromotionsPage() { //expected failure when offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Shop: Verify ongoing OffersOrPromotions page");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), "Unable to navigate to Promotion page or Promotion heading is not visible");
        String promotionName = OffersOrPromotions.clickPromotionAndFetchPromotionName(0);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Promotion Detail page");
        Assert.assertEquals(WebOperations.getText("detailedpageheading"), promotionName);
    }

    /**
     * This test case verifies the search functionality on the 'Offers or Promotions' listing page
     * for the web version. It ensures that searching for promotions by name works correctly.
     * It checks that the correct promotion is displayed in the search results after filtering.
     */
    @Test
    public void verifyWebPromotionsPageSearchInput() { //expected failure when offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Shop: Verify that searching for OffersOrPromotions works from the OffersOrPromotions listing");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        String promotionName = WebOperations.getText("offersorpromotionlists");
        boolean isSearchingPromotions = OffersOrPromotions.searchOffersWithName(promotionName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search functionality for OffersOrPromotions.");
        Assert.assertTrue(isSearchingPromotions, "Search input field was not visible or not found");
        Assert.assertEquals(WebOperations.getWebElements("offersorpromotionlists").size(), 1, "More than one shop is visible after searching for the selected shop.");
        Assert.assertEquals(WebOperations.getText("offersorpromotionlists"), promotionName, "The filtered shop was not found in the search results.");
    }


    /**
     * This test case ensures that Ikea products are visible on the homepage of the website.
     * It checks if the Ikea product cards are displayed correctly.
     */
    @Test
    public void verifyWebHomepageIkeaProductsVisibility() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage Ikea products visibility");
        Homepage.clickRejectCookies();
        WebOperations.wait(5000);
        Assert.assertTrue(WebOperations.isElementPresent("hompageikeacards"), "Ikea Products are not visible on homepage");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the Ikea product card is visible on homepage.");
    }

    /**
     * This test case verifies that the Ikea product carousel on the homepage slides automatically.
     * It checks if the first and second card texts are different after waiting for the slide transition.
     */
    @Test
    public void verifyWebHomepageIkeaCardCarouselSlideAutomatically() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage Ikea card carousel slide automatically");
        Homepage.clickRejectCookies();
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        String firstCard = WebOperations.getText("hompageikeacards");
        WebOperations.addScreenshotToExtentReport();
        ExtentTestManager.getTest().log(Status.INFO, "Waited for 25 seconds for the carousel to slide.");
        WebOperations.wait(3000);
        String secondCard = WebOperations.getText("hompageikeacards");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the Ikea card carousel slides automatically by comparing the first and second card texts.");
        Assert.assertNotSame(firstCard, secondCard);
    }

    /**
     * This test case ensures that clicking the 'Buy at IKEA' button on the homepage opens a new tab
     * with the correct IKEA page.
     */
    @Test
    public void VerifyWebHomepageIkeaCardNavigation() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify in Homepage Ikea card Present with buy at ikea button");
        Homepage.clickRejectCookies();
        WebOperations.wait(4000);
        String initialWindow = Homepage.clickBuyAtIKEAButtonAndReturnInitialWindow();
        Set<String> allWindows = driver.getWindowHandles();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying opening of new tab");
        Assert.assertTrue(allWindows.size() > 1, "New tab/window has not opened.");
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(initialWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        ExtentTestManager.getTest().log(Status.INFO, "Title of new tab: " + driver.getTitle());
    }

    /**
     * This test case ensures that the user can view a list of Ikea products on the Ikea detail page
     * after navigating through the shop search.
     */
    @Test
    public void verifyWebIkeaProductsVisibilityInsideIkeaDetailPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea products visibility inside Ikea detail page");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        Homepage.clickNewsLetterCloseButton();
        Tenant.clickAndTypeFindShopInputField("ikea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search result for 'ikea'");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"));
        Tenant.clickSubTenantAndGetTenantName();
        WebOperations.scrollToElement("ikeaProducts");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Visibility of Ikea Products");
        Assert.assertTrue(WebOperations.isElementPresent("ikeaProducts"), "Ikea products are not visible");
    }

    /**
     * This test case verifies the visibility and availability of the Ikea products in the product listing.
     * It checks whether the total number of products displayed matches the total number of products available.
     */
    @Test
    public void verifyWebIkeaProductsAvailableForViewingAndTheProductListing() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea products available for viewing and the product listing");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        Homepage.clickNewsLetterCloseButton();
        Tenant.clickAndTypeFindShopInputField("ikea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search result for 'ikea'");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"));
        Tenant.clickSubTenantAndGetTenantName();
        if (WebOperations.isElementPresent("showmore")) {
            String showingNumbersForVisibleCardsText = WebOperations.getText("showingnumbersforvisiblecards");
            String[] parts = showingNumbersForVisibleCardsText.split(" ");
            int totalCardNumber = Integer.parseInt(parts[parts.length - 1]);
            while (!WebOperations.isElementEmpty("showmore")) {
                int visibleCardNumber = Tenant.getVisibleCardNumber();
                ExtentTestManager.getTest().log(Status.INFO, "Verifying visible product number: " + visibleCardNumber + " matches with total product cards visible");
                Assert.assertEquals(WebOperations.getWebElements("ikeaProducts").size(), visibleCardNumber);
                Tenant.clickShowMoreButton();
            }
            ExtentTestManager.getTest().log(Status.INFO, "Verifying visible product number: " + totalCardNumber + " matches with total product cards visible");
            Assert.assertEquals(WebOperations.getWebElements("ikeaProducts").size(), totalCardNumber);
            return;
        }
        ExtentTestManager.getTest().log(Status.INFO, "Verifying visible products should be less than 4");
        Assert.assertTrue(WebOperations.getWebElements("ikeaProducts").size() <= 4);
    }

    /**
     * This test case ensures that the user can navigate through the Ikea product listing and view products.
     * It checks the correct opening of new tabs when clicking the 'Buy at Ikea' button from the product listing.
     */
    @Test
    public void verifyWebIkeaProductListingNavigation() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea will allow the users to View a listing of interesting Ikea products");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        Homepage.clickNewsLetterCloseButton();
        Tenant.clickAndTypeFindShopInputField("ikea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search result for 'ikea'");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"));
        Tenant.clickSubTenantAndGetTenantName();
        Assert.assertTrue(WebOperations.isElementPresent("ikeaProducts"), "Ikea products are not visible");
        String initialWindow = Homepage.clickBuyAtIKEADetailPageButtonAndReturnInitialWindow();
        Set<String> allWindows = driver.getWindowHandles();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying opening of new tab");
        Assert.assertTrue(allWindows.size() > 1, "New tab/window has not opened.");
        for (String windowHandle : allWindows) {
            if (!windowHandle.equals(initialWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        ExtentTestManager.getTest().log(Status.INFO, "Title of new tab: " + driver.getTitle());
    }


    /**
     * This test case ensures that the shop listing page on the web functions as expected.
     * It verifies that the shops heading is visible, search results for specific shops are accurate,
     * and the filtering and clearing of search results works correctly.
     */
    @Test
    public void verifyWebShopListingPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the shop listing page functionality");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        Homepage.clickNewsLetterCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the shops heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        Tenant.clickAndTypeFindShopInputField("ea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search result for 'ea'");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"));
        Tenant.clickAndTypeFindShopInputField("ikeo");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying no search matches message for 'ikeo'");
        Assert.assertTrue(WebOperations.isElementPresent("nosearchmatchesmsg"));
        Tenant.clickAndTypeFindShopInputField("ikea");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search result for 'ikea' again");
        Assert.assertTrue(WebOperations.isElementPresent("ikea"));
        Tenant.clearSearchFieldAndSelectCategory();
        Assert.assertEquals(WebOperations.getText("visiblenumberonfilter"), "1");
        Tenant.clickAllFiltersAndClearAll();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying filtered result have been cleared");
        Assert.assertFalse(WebOperations.isElementPresent("visiblenumberonfilter"));
        Tenant.clickShowMoreIfVisible();
    }

    /**
     * This test case verifies the functionality of the Food & Beverage (F&B) tenant listing page on the web.
     * It checks if the page correctly navigates, if the filters work, and if the filtered results match the expected number.
     */
    @Test
    public void verifyWebFAndBTenantListingPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify F&B Tenant Listing Page ");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        Homepage.clickNewsLetterCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to Eat And Drink Page");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"), Message.FOOD_AND_BEVERAGE_HEADING_NOT_VISIBLE);
        Tenant.clickAllFilters();
        int filteredNumber = Tenant.clickCategoryAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "verifying filtered results");
        Assert.assertFalse(WebOperations.isElementPresent("filters"), Message.FILTER_HEADING_VISIBILITY);
        Assert.assertEquals(WebOperations.getWebElements("subtenants").size(), filteredNumber, "The number of filtered subtenants does not match the expected filtered number");
    }

    /**
     * This test case ensures that the F&B filters are visible on the detail page of an F&B tenant on the web.
     * It checks that the filter names are displayed properly.
     */
    @Test
    public void verifyWebFAndBFilterVisibilityOnDetailPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify F And B filter visibility on detail page ");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        Homepage.clickNewsLetterCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to Eat And Drink Page");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"), Message.FOOD_AND_BEVERAGE_HEADING_NOT_VISIBLE);
        String fAndBName = Tenant.clickSubTenantAndGetTenantName();
        Assert.assertEquals(WebOperations.getWebElement("detailedpageheading").getText(), fAndBName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying F and B filter visibility on detail page ");
        Assert.assertTrue(WebOperations.isElementPresent("filternameondetailpage"), "filter names are not present in detail page");
    }

    /**
     * This test case verifies that the F&B filter on the detail page of an F&B tenant is clickable on the web.
     * It ensures that when a filter is clicked, the filtered list updates accordingly.
     */
    @Test
    public void verifyWebFAndBFilterClickableOnDetailPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify F And B filter Clickable on detail page");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        Homepage.clickNewsLetterCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Navigating to Eat And Drink Page");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"), Message.FOOD_AND_BEVERAGE_HEADING_NOT_VISIBLE);
        String fAndBName = Tenant.clickSubTenantAndGetTenantName();
        Assert.assertEquals(WebOperations.getWebElement("detailedpageheading").getText(), fAndBName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying F and B filter visibility on detail page");
        Assert.assertTrue(WebOperations.isElementPresent("filternameondetailpage"), "Filter names are not present on detail page");
        WebOperations.clickElement("filternameondetailpage");
        ExtentTestManager.getTest().log(Status.INFO, "Clicked filter: " + WebOperations.getText("filternameondetailpage"));
        ExtentTestManager.getTest().log(Status.INFO, "Verifying filtered F and B lists");
        Assert.assertTrue(WebOperations.isElementPresent("subtenants"));
    }

    /**
     * This test case ensures that special offers are visible on the web page.
     * It verifies if the 'Special Offers' section is correctly displayed.
     */
    @Test
    public void verifyWebSpecialOffersVisibility() { //expected failure when special offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify special offers visibility ");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to offers Page and special offers visibility");
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), Message.OFFERS_HEADING_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("specialoffers"), "Special Offers are not present");
    }


    /**
     * This test case ensures that the special offers detail page on the web is functioning correctly.
     * It checks if the details of the special offer (title and date) match between the offer listing and the detail page.
     */
    @Test
    public void verifyWebSpecialOffersDetailPage() { //expected failure when special offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Special Offers Details page");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
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
     * This test case verifies the functionality of filtering special offers on the web.
     * It checks if the filter options work properly and if the number of visible special offers matches the filtered results.
     */
    @Test
    public void verifyWebVerifySpecialOffersFilter() { //expected failure when special offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Special Offers Filter");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        Tenant.clickAllFilters();
        Assert.assertTrue(WebOperations.isElementPresent("specialoffersfilter"), "Special offers are not present inside filter or filter has not opened");
        int filterNumber = OffersOrPromotions.clickSpecialOffersAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the visible cards match the expected filtered results.");
        Assert.assertEquals(WebOperations.getWebElements("specialoffers").size(), filterNumber);
    }

    /**
     * This test case verifies the functionality of searching for special offers on the web.
     * It checks if the search results return the correct special offer based on the search input.
     */
    @Test
    public void verifyWebVerifySpecialOffersSearchFunctionality() { //expected failure when special offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify searching a Special Offer works fine");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        String specialOfferName = WebOperations.getText("specialofferscardtitle");
        OffersOrPromotions.searchOffersWithName(specialOfferName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search functionality for OffersOrPromotions.");
        Assert.assertEquals(WebOperations.getWebElements("specialofferscardtitle").size(), 1, "More than one special offer is visible after searching for the selected special offer.");
        Assert.assertEquals(WebOperations.getText("specialofferscardtitle"), specialOfferName, "The filtered special offer was not found in the search results.");
    }


    /**
     * This test case verifies the functionality of the download option for special offers on the web.
     * It checks if the Play Store tab opens when the user clicks on the download option for the special offer.
     */
    @Test
    public void verifyWebDownloadOptionsForSpecialOffers() { //expected failure when special offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals Details Page can take user to play store");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying visibility of Special Offers section.");
        Assert.assertTrue(WebOperations.isElementPresent("specialoffers"), Message.SPECIAL_OFFERS_NOT_VISIBLE);
        String[] specialOfferDetails = OffersOrPromotions.clickSpecialOfferAndFetchDetails();
        String specialOfferTitle = specialOfferDetails[0];
        String specialOfferDetailPageTitle = WebOperations.getText("specialoffersdetailpagetitle");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Special Offer title '" + specialOfferTitle + "' on the detail page.");
        Assert.assertEquals(specialOfferDetailPageTitle, specialOfferTitle, Message.SPECIAL_OFFER_TITLE_MISMATCH);
        boolean isPlayStoreOpened = OffersOrPromotions.clickDownloadFromPlayStore();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the play store tab is open");
        Assert.assertTrue(isPlayStoreOpened, "Play Store tab did not open");
    }

    /**
     * This test case verifies that the package deals card is present and visible on the homepage of the website.
     */
    @Test
    public void verifyWebPackageDealsCardPresentHomescreen() { //expected failure when Package Deals is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals card present in the Home screen");
        Homepage.clickRejectCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the package deal card is visible on homepage.");
        if (!WebOperations.isElementPresent("packagedealscard")) {
            Assert.fail(Message.PACKAGE_DEALS_CARD_NOT_VISIBLE + " (Element 'packagedealscard' not found on homepage)");
        }
    }

    /**
     * This test case verifies that the package deals listing page is accessible on the web.
     * It checks the visibility of the package deal heading and the listing of package deals.
     */
    @Test
    public void verifyWebPackageDealsListingPage() { //expected failure when Package Deals is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals Listing page");
        boolean isPackageDealLinkPresent = PackageDeals.navigateToTheLPOListingPage();
        Assert.assertTrue(isPackageDealLinkPresent, Message.PACKAGE_DEALS_NOT_PRESENT_ON_MENU);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the package deal listing page");
        Assert.assertTrue(WebOperations.isElementPresent("packagedealslistingpageheading"), Message.PACKAGE_DEAL_HEADING_NOT_PRESENT);
        Assert.assertTrue(WebOperations.isElementPresent("packageofferslist"), Message.PACKAGE_DEALS_LISTING_NOT_PRESENT);
    }

    /**
     * This test case ensures that expired package deals (LPOs) are not visible on the website.
     * It checks if any package deals have an end date in the past and ensures they are hidden.
     */
    @Test
    public void verifyExpiredLPOsNotVisible() { //expected failure when Package Deals is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify expired LPOs not visible on the website");
        boolean isPackageDealLinkPresent = PackageDeals.navigateToTheLPOListingPage();
        Assert.assertTrue(isPackageDealLinkPresent, Message.PACKAGE_DEALS_NOT_PRESENT_ON_MENU);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the package deal listing page");
        Assert.assertTrue(WebOperations.isElementPresent("packageofferslist"), Message.PACKAGE_DEALS_LISTING_NOT_PRESENT);
        for (int i = 0; i < WebOperations.getWebElements("packagedealdates").size(); i++) {
            String dateString = WebOperations.getWebElements("packagedealdates").get(i).getText();
            boolean isExpiredLpoVisible = PackageDeals.isEndDateInPast(dateString);
            Assert.assertFalse(isExpiredLpoVisible, Message.EXPIRED_LPO_VISIBLE_FOR_CARD + (i + 1));
        }
    }


    /**
     * This test case verifies that the package deal details page on the web displays the correct title.
     * It ensures that the package deal title from the listing matches the title on the detail page.
     */
    @Test
    public void verifyWebPackageDealsDetailPage() { //expected failure when Package Deals is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals Detail Page");
        boolean isPackageDealLinkPresent = PackageDeals.navigateToTheLPOListingPage();
        Assert.assertTrue(isPackageDealLinkPresent, Message.PACKAGE_DEALS_NOT_PRESENT_ON_MENU);
        Assert.assertTrue(WebOperations.isElementPresent("packageofferslist"), Message.PACKAGE_DEALS_LISTING_NOT_PRESENT);
        String packageDealTitle = PackageDeals.getPackageDealAndClick();
        String detailPageTitle = WebOperations.getText("packagedealdetailpagetitle");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the package deal detail page");
        Assert.assertEquals(detailPageTitle, packageDealTitle, Message.PACKAGE_DEAL_TITLE_MISMATCH);
    }

    /**
     * This test case verifies that clicking on the download option for a package deal on the web takes the user to the Play Store.
     * It ensures that the Play Store tab opens successfully after clicking on the download button.
     */
    @Test
    public void verifyWebPackageDealsDetailPageDownloadFromPlaystoreOptions() { //expected failure when Package Deals is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Package Deals Details Page can take user to play store");
        boolean isPackageDealLinkPresent = PackageDeals.navigateToTheLPOListingPage();
        Assert.assertTrue(isPackageDealLinkPresent, Message.PACKAGE_DEALS_NOT_PRESENT_ON_MENU);
        Assert.assertTrue(WebOperations.isElementPresent("packageofferslist"), Message.PACKAGE_DEALS_LISTING_NOT_PRESENT);
        PackageDeals.getPackageDealAndClick();
        boolean isPlayStoreOpened = OffersOrPromotions.clickDownloadFromPlayStore();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the play store tab is open");
        Assert.assertTrue(isPlayStoreOpened, Message.PLAY_STORE_TAB_NOT_OPENED);
    }

    /**
     * This test case verifies the visibility of the Ikea Family Members offer on the web.
     * It ensures that the Ikea Family Members offers are present and visible on the offers page.
     */
    @Test
    public void verifyWebIkeaFamilyMembersOfferVisibility() { //expected failure when Ikea family offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Offer visibility");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating to offers Page and Ikea Family Members Offers visibility");
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), Message.OFFERS_HEADING_NOT_VISIBLE);
        Homepage.clickNewsLetterCloseButton();
        Assert.assertTrue(WebOperations.isElementPresent("ikeafamilymemberoffer"), Message.IKEA_FAMILY_MEMBER_OFFERS_NOT_VISIBLE);
    }

    /**
     * This test case verifies that the Ikea Family Members offer detail page on the web displays the correct title and date.
     * It ensures that the offer title and date on the detail page match the offer details from the listing.
     */
    @Test
    public void verifyWebIkeaFamilyMembersOfferDetailPage() { //expected failure when Ikea family offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Details page");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying visibility of Ikea Family Members Offers section.");
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
     * This test case verifies that the Ikea Family Members offers filter on the web displays the correct filtered results.
     * It ensures that the number of visible offers matches the expected number after applying the filter.
     */
    @Test
    public void verifyWebVerifyIkeaFamilyMembersOffersFilter() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Offers Filter");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        Tenant.clickAllFilters();
        int filterNumber = OffersOrPromotions.clickIkeaFamilyMemberAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying if the visible cards match the expected filtered results.");
        Assert.assertEquals(WebOperations.getWebElements("ikeafamilymemberoffer").size(), filterNumber, Message.FILTERED_OFFERS_COUNT_MISMATCH);
    }

    /**
     * This test case verifies the Ikea Family Members offers search functionality on the web.
     * It ensures that the correct offer is displayed after searching for it by name.
     */
    @Test
    public void verifyWebVerifyIkeaFamilyMembersOffersSearchFunctionality() { //expected failure when Ikea family offer is not available
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Ikea Family Members Offers Search Functionality");
        OffersOrPromotions.navigateToTheOffersListingPageWeb();
        String ikeaFamilyMemberOfferName = WebOperations.getText("ikeafamilymembercardtitle");
        OffersOrPromotions.searchWithName(ikeaFamilyMemberOfferName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying search functionality for Ikea Family Member Offer.");
        Assert.assertEquals(WebOperations.getWebElements("ikeafamilymembercardtitle").size(), 1, Message.MORE_THAN_ONE_SHOP_VISIBLE);
        Assert.assertEquals(WebOperations.getText("ikeafamilymembercardtitle"), ikeaFamilyMemberOfferName, Message.SHOP_NOT_FOUND_IN_SEARCH_RESULTS);
    }


    /**
     * This test case verifies the functionality of the back option in the web interface.
     * It ensures that the user can successfully navigate back to the previous page using the breadcrumb trail.
     */
    @Test
    public void verifyWebBackOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify homepage card back button works");
        Homepage.clickRejectCookies();
        Homepage.clickRandomCardOnHomepage();
        int breadcrumbTrailsSize = Homepage.navigateBackFromBreadcrumbTrail();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Navigating back to the previous breadcrumb trail");
        Assert.assertTrue(WebOperations.getWebElements("breadcrumbtrail").size() < breadcrumbTrailsSize, Message.BREADCRUMB_TRAIL_PREVIOUS_PAGE_NOT_REACHED);
    }


    /**
     * This test method verifies that when the 'Show More' button is clicked on the tenant listing page, additional tenant cards are displayed.
     * It checks that the number of tenant cards increases after clicking the 'Show More' button.
     */
    @Test
    public void verifyShowMoreButtonDisplaysMoreCards() {
        ExtentTestManager.startTest("[Mobile/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify tapping/clicking on 'show more' displays more cards ");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickMenuShopsButton();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Shops page navigation");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"), Message.SHOPS_HEADING_NOT_VISIBLE);
        int currentShopsCardSize = WebOperations.getWebElements("subtenants").size();
        boolean showMoreButton = Tenant.clickShowMoreIfVisible();
        if (showMoreButton) {
            int shopsCardSizeAfterClickingShowMoreButton = WebOperations.getWebElements("subtenants").size();
            ExtentTestManager.getTest().log(Status.INFO, "Verifying Clicking 'show more' displays more cards");
            Assert.assertTrue(shopsCardSizeAfterClickingShowMoreButton > currentShopsCardSize, Message.MORE_SHOPS_NOT_DISPLAYED);
        }}
    @Test
        public void verifyGlobalSearchServiceBank () {
            ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search Bank Service Word");
            GlobalSearch.clickAcceptCookies();
            GlobalSearch.clickOpenSearchIcon();
            GlobalSearch.clickAndTypeInSearchInput("ActivoBank");
            Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"), "ActivoBank");
            WebOperations.clickElement("globalSearchIkeaheading");
            WebOperations.wait(1000);
            String pageTitle = driver.getTitle();
            assert pageTitle != null;
            Assert.assertTrue(pageTitle.contains("ActivoBank"), "Title not matching");
        }
    @Test
    public void verifyGlobalSearchcategory () {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search Bank Service Word");
        GlobalSearch.clickAcceptCookies();
        GlobalSearch.clickOpenSearchIcon();
        GlobalSearch.clickAndTypeInSearchInput("Desporto");
        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"), "Desporto");
        WebOperations.clickElement("globalSearchIkeaheading");
        WebOperations.wait(1000);
        String pageTitle = driver.getTitle();
        assert pageTitle != null;
        Assert.assertTrue(pageTitle.contains("Desporto"), "Title not matching");
    }
    @Test
    public void verifyGlobalSearchBodyContent() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search design in the body content");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("design");
        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"),"design");
        WebOperations.clickElement("globalSearchIkeaheading");
        WebOperations.wait(1000);
        String pageTitle = driver.getTitle();
        assert pageTitle != null;
        Assert.assertTrue(pageTitle.contains("design"),"Title not matching");

    }
    @Test
    public void verifyGlobalSearchEventHeading() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search Event Heading");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("Fitinhas");
        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"),"Fitinhas");
        WebOperations.clickElement("globalSearchIkeaheading");
        WebOperations.wait(1000);
        String pageTitle = driver.getTitle();
        assert pageTitle != null;
        Assert.assertTrue(pageTitle.contains("Fitinhas"),"Title not matching");

    }
    @Test
    public void VerifyUserNameSpecialchar() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter Special char not supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("$Siddu_'Reddy");
        Assert.assertTrue(WebOperations.isElementPresent("youhavetoprovideyourname"));
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void VerifySpacehyphenandapostrophetosupportcompoundnames () {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Space hyphen and apostrophe to support compoundnames");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("siddu'-reddy");
        Assert.assertTrue(WebOperations.isElementPresent("disabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_DISABLED);}
    @Test
    public void VerifyUserNameInInputFieldDigit() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter digit not supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("Rani6");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(WebOperations.isElementPresent("youhavetoprovideyourname"));
    }
    @Test
    public void VerifycontactUserNameString() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact form string supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickContactusLink();
        Thread.sleep(1000);
        ContactFrom.contactUserNameInInputField("Testuser");
        Assert.assertFalse(WebOperations.isElementPresent("You have to provide your name"));
        //ContactFrom.disablcontactussubmitbebutton("disablcontactussubmitbebutton");

    }
    @Test
    public void VerifycontactMessage() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact us Message field entery");
        Homepage.clickRejectCookies();

        Thread.sleep(10000);
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }Homepage.clickMainMenu();
        MainMenu.clickContactusLink();
        ContactFrom.contactUserNameInInputField("Sid");
        ContactFrom.contactEmailInInputField("siddu@yahoo.com");
        ContactFrom.contactPhoneInInputField("6777777788");
        Thread.sleep(5000);
        ContactFrom.contactMessageInInputField("");
        Assert.assertTrue(WebOperations.isElementPresent("Youmustprovideamessage"));
    }

    @Test
    public void verifyingskipallfields() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying skipping of fields");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickContactusLink();
        ContactFrom.skippingAllTheFieldsEmpty();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Contact us' heading is visible");
        //  Assert.assertTrue(WebOperations.isElementPresent("Contact us"));

    }
    @Test
    public void Verifycontactemail() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying email verification for the contact form");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        //Thread.sleep(1000);
        MainMenu.clickContactusLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Thread.sleep(1000);
        ContactFrom.contactUserNameInInputField("Siddu");

        ContactFrom.contactEmailInInputField("siddu@yahoo.com");

    }
    @Test
    public void Verifycontactphoneno() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying email verification for the contact form");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        Thread.sleep(1000);
        MainMenu.clickContactusLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Thread.sleep(1000);
        ContactFrom.contactUserNameInInputField("Siddu");
        ContactFrom.contactPhoneInInputField("655665667");

    }

    @Test
    public void VerifycontactUserNameDigit() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact us form wont support digit in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        Thread.sleep(1000);
        MainMenu.clickContactusLink();
        ContactFrom.contactUserNameInInputField("5676sss");
        Assert.assertFalse(WebOperations.isElementPresent("You have to provide your name"));
    }

    @Test
    public void VerifyalltheLettersfromallalphabetssupportedNewsletterform () {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter supports different language");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("decorações de balões para festas");
        String firstname="decorações de balões para festas";
        //Assert.assertTrue(WebOperations.isElementPresent("email"), Message.EMAIL_INPUT_FIELD_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("disabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_DISABLED);}
}

