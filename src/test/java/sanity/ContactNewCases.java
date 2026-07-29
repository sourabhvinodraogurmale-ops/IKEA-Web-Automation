package sanity;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactFrom;
import pages.Homepage;
import pages.MainMenu;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.WebOperations;

public class ContactNewCases extends BaseTest {


    @Test
    public void VerifycontactUserNameString() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact form string supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickContactusLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Thread.sleep(1000);
        ContactFrom.contactUserNameInInputField("Testuser");
        Assert.assertFalse(WebOperations.isElementPresent("You have to provide your name"));
        //ContactFrom.disablcontactussubmitbebutton("disablcontactussubmitbebutton");

    }

    @Test
    public void VerifycontactUserNameDigit() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact us form wont support digit in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
              Thread.sleep(2000);
        MainMenu.clickContactusLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ContactFrom.contactUserNameInInputField("5676sss");
        Assert.assertFalse(WebOperations.isElementPresent("You have to provide your name"));
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

    /* @Test
 public void VerifycontactPhonenoinavlid() throws InterruptedException {
       ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact form digit not supported in the Name field");
       Homepage.clickRejectCookies();
       Homepage.clickMainMenu();
       Thread.sleep(1000);
       MainMenu.clickContactusLink();
       ContactFrom.contactUserNameInInputField("Sid");
       ContactFrom.contactEmailInInputField("siddu@yahoo.com");
       ContactFrom.contactPhoneInInputField("");
       Assert.assertTrue(WebOperations.isElementPresent("You have to provide your name"));
   }
*/
    @Test
    public void Verifycontactusform() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact us form submitted");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        Thread.sleep(1000);
        MainMenu.clickContactusLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ContactFrom.contactUserNameInInputField("Sid");
        ContactFrom.contactEmailInInputField("siddu@yahoo.com");
        ContactFrom.contactPhoneInInputField("6777777788");
        Thread.sleep(1000);
        ContactFrom.contactMessageInInputField("Hello, need more info for the MP");
        ContactFrom.Verifycontactus();
        Thread.sleep(1000);
        //Assert.assertTrue(WebOperations.isElementPresent("Youmustprovideamessage"));
    }

    @Test
    public void verifyingskipallfields() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying skipping of fields");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickContactusLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
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
    public void VerifycontactemptyMessageerror() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact us Message field entery");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        Thread.sleep(1000);
        MainMenu.clickContactusLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ContactFrom.contactUserNameInInputField("Sid");
        ContactFrom.contactEmailInInputField("siddu@yahoo.com");
        ContactFrom.contactPhoneInInputField("6777777788");
        Thread.sleep(1000);
        ContactFrom.contactMessageInInputField("");
        Assert.assertTrue(WebOperations.isElementPresent("Youmustprovideamessage"));
    }

}