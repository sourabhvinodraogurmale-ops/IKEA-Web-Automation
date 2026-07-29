package sanity;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Homepage;
import pages.MainMenu;
import pages.ProfileLoginSpecialOffer;
import pages.Tenant;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.WebOperations;

public class ProfileLogin extends BaseTest {
    @Test
    public void VerifyprofilesigninusingEmail() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Profile 'Sign in' by Email and password");
        Homepage.clickRejectCookies();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ProfileLoginSpecialOffer.profilebuttonclicking();
        ProfileLoginSpecialOffer.profilesignup();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.profileInInputField("webtest04@yopmail.com");
        ProfileLoginSpecialOffer.Continuebutton();
        ProfileLoginSpecialOffer.Profilepass("Sai@1324");
        ProfileLoginSpecialOffer.Profilelogin();
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.ProfilLoginsuccess();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the profile login success");

    }

    @Test
    public void VerifyprofilenameModify() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Modify Profile");
        Homepage.clickRejectCookies();
        ProfileLoginSpecialOffer.profilebuttonclicking();
        ProfileLoginSpecialOffer.profilesignup();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.profileInInputField("webtest04@yopmail.com");
        ProfileLoginSpecialOffer.Continuebutton();
        ProfileLoginSpecialOffer.Profilepass("Sai@1324");
        ProfileLoginSpecialOffer.Profilelogin();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.ProfilLoginsuccess();
        ProfileLoginSpecialOffer.Profilemodify();
        ProfileLoginSpecialOffer.Profilemodifyclickname();
        ProfileLoginSpecialOffer.profilenamemodify("QWebtest04");
        ProfileLoginSpecialOffer.profilesave();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the profile update success");
    }

    @Test
    public void VerifyprofileLogout() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Profile Logout");
        Homepage.clickRejectCookies();
        ProfileLoginSpecialOffer.profilebuttonclicking();
        ProfileLoginSpecialOffer.profilesignup();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.profileInInputField("webtest04@yopmail.com");
        ProfileLoginSpecialOffer.Continuebutton();
        ProfileLoginSpecialOffer.Profilepass("Sai@1324");
        ProfileLoginSpecialOffer.Profilelogin();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.ProfilLoginsuccess();
        ProfileLoginSpecialOffer.profileLogout();
        ProfileLoginSpecialOffer.profilebuttonclicking();
        ProfileLoginSpecialOffer.profilesignup();
        Assert.assertTrue(WebOperations.isElementPresent("profile Login heading verification"));
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the profile logout success");

    }

    @Test
    public void VerifyprofilesigninwithEmailMagiclink() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying profile login with Email and Magic link");
        Homepage.clickRejectCookies();
        ProfileLoginSpecialOffer.profilebuttonclicking();
        ProfileLoginSpecialOffer.profilesignup();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Profile' heading is visible");
        //Assert.assertTrue(WebOperations.isElementPresent("Profile"));
        ProfileLoginSpecialOffer.profileInInputField("webtest04@yopmail.com");
        ProfileLoginSpecialOffer.Continuebutton();
        ProfileLoginSpecialOffer.Profilmagiclink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'magic link' message is displayed after continue");
        Homepage.navigateToYopmail();
        ProfileLoginSpecialOffer.Profileemailmagientery("webtest04");
        driver.switchTo().frame("ifmail");
        ProfileLoginSpecialOffer.Profileemailmagiclink();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String url = driver.getCurrentUrl();
        System.out.println(url);
        // pages.ProfileLogin.profilebuttonclicking();
        //pages.ProfileLogin.profilesignup();
        //    pages.ProfileLogin.ProfilLoginsuccess();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the profile name ");
    }
    @Test
    public void VerifyUserProfile() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying User Profile");
        Homepage.clickRejectCookies();
        ProfileLoginSpecialOffer.profilebuttonclicking();
        ProfileLoginSpecialOffer.profilesignup();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.profileInInputField("webtest04@yopmail.com");
        ProfileLoginSpecialOffer.Continuebutton();
        ProfileLoginSpecialOffer.Profilepass("Sai@1324");
        ProfileLoginSpecialOffer.Profilelogin();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.ProfilLoginsuccess();
        //ProfileLoginSpecialOffer.Profilemodify();
        Assert.assertTrue(WebOperations.isElementPresent("ProfileHeading"));
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the profile page displayed");

       // ProfileLoginSpecialOffer.Profilemodifyclickname();
        //ProfileLoginSpecialOffer.profilenamemodify("QWebtest04");
        //ProfileLoginSpecialOffer.profilesave();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the profile update success");
    }
    @Test
    public void VerifySpecialOfferPresents(){

        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Special Offer Presents");
        Homepage.clickRejectCookies();
        /*ProfileLoginSpecialOffer.profilebuttonclicking();
        ProfileLoginSpecialOffer.profilesignup();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ProfileLoginSpecialOffer.progilrInInputField("webtest04@yopmail.com");
        ProfileLoginSpecialOffer.Continuebutton();
        ProfileLoginSpecialOffer.Profilepass("Sai@1324");
        ProfileLoginSpecialOffer.Profilelogin();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }*/
        Homepage.clickMainMenu();
        MainMenu.clickOffersLink();
        Tenant.clickAllFilters();
        Tenant.clickSpecialffersAndGetFilteredResults();
        Tenant.clickShowMoreIfVisible();
        ProfileLoginSpecialOffer.Offerclaim();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Claim offer button");
    }


    }



