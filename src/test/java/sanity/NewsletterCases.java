package sanity;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.Message;
import util.WebOperations;

public class NewsletterCases extends BaseTest {

    @Test
    public void VerifyUserNameInInputFieldDigit() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter digit not supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("Rani6");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(WebOperations.isElementPresent("youhavetoprovideyourname"));
    }

  /*  @Test
    public void VerifycontactUserNameDigit() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact form digit not supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        Thread.sleep(1000);
        MainMenu.clickContactusLink();
        ContactFrom.contactUserNameInInputField("Rahul6");
        Assert.assertTrue(WebOperations.isElementPresent("You have to provide your name"));
    }
*/
    @Test
    public void VerifyUserNameSpecialchar() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter Special char not supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("$Siddu_'Reddy");
        Assert.assertTrue(WebOperations.isElementPresent("youhavetoprovideyourname"));

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

