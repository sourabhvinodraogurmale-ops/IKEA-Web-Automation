package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.WebConstants;
import util.WebOperations;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static util.WebOperations.mConfigProps;
import static webdriver.WebDriverSetup.driver;

public class OffersOrPromotions {

    public static String clickPromotionAndFetchPromotionName(int index) {
        try {
            List<WebElement> Promotions = WebOperations.getWebElements("offersorpromotionlists");
            String promotionName = Promotions.get(index).getText();
            Promotions.get(index).click();
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            ExtentTestManager.getTest().log(Status.INFO, "Retrieving first Promotion "+promotionName+" and clicking on it");
            return promotionName;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to retrieve and click the first Subtenant: " + e.getMessage());
            return null;
        }
    }

    public static boolean clickDownloadFromPlayStore() {
        try {
            WebOperations.clickElement("downloadfromplaystore");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking 'Download from Play Store' button");
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("play.google.com")) {
                return true;
            }

            if (tabs.size() == 2) {
                driver.switchTo().window(tabs.get(1)); // Switch to the new tab that opened
                currentUrl = driver.getCurrentUrl();
                if (currentUrl.contains("play.google.com")) {
                    return true;
                } else {
                    ExtentTestManager.getTest().log(Status.WARNING, "The new tab did not open the expected Play Store URL. Current URL: " + currentUrl);
                    return false;
                }
            }

            // If there is only 1 tab open, log and return false
            ExtentTestManager.getTest().log(Status.WARNING, "Expected new tab to open but no new tab was detected.");
            return false;

        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click the 'Download from Play Store' button or switch to the new tab. Error: " + e.getMessage());
            return false;
        }
    }


    public static int clickSpecialOffersAndGetFilteredResults() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking special offer filter");
            WebOperations.clickElement("specialoffersfilter");
            String showResultsText = WebOperations.getText("showresult");
            String filteredNumber = showResultsText.replaceAll("[^0-9]", "");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Show Results");
            WebOperations.clickElement("showresult");
            if(WebOperations.isElementPresent("showmore")){
                WebOperations.clickElement("showmore");
            }
            return Integer.parseInt(filteredNumber);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Error occurred: " + e.getMessage());
            return 0;
        }
    }

    public static int clickIkeaFamilyMemberAndGetFilteredResults() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking special offer filter");
            WebOperations.clickElement("ikeafamilymemberfilter");
            String showResultsText = WebOperations.getText("showresult");
            String filteredNumber = showResultsText.replaceAll("[^0-9]", "");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Show Results");
            WebOperations.clickElement("showresult");
            while(WebOperations.isElementPresent("showmore")){
                WebOperations.clickElement("showmore");
            }
            return Integer.parseInt(filteredNumber);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Error occurred: " + e.getMessage());
            return 0;
        }
    }

    public static String[] clickSpecialOfferAndFetchDetails() {
        try {
            String specialOfferTitle = WebOperations.getText("specialofferscardtitle");
            String specialOfferDate = WebOperations.getText("specialofferscarddate");
            String[] specialOfferDetail = {specialOfferTitle,specialOfferDate};
            WebOperations.scrollToElement("specialoffers");
            WebOperations.clickElement("specialoffers");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking special offer : " + specialOfferTitle);
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return specialOfferDetail;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to retrieve and click the first Subtenant: " + e.getMessage());
            return null;
        }
    }

    public static String[] clickIkeaFamilyMembersOfferAndFetchDetails() {
        try {
            String ikeaFamilyMemberOfferTitle = WebOperations.getText("ikeafamilymembercardtitle");
            String ikeaFamilyMemberOfferDate = WebOperations.getText("ikeafamilymembercarddate");
            String[] specialOfferDetail = {ikeaFamilyMemberOfferTitle,ikeaFamilyMemberOfferDate};
            WebOperations.scrollToElement("ikeafamilymemberoffer");
            WebOperations.clickElement("ikeafamilymemberoffer");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Ikea Family Member Offer : " + ikeaFamilyMemberOfferTitle);
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return specialOfferDetail;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to retrieve and click the Ikea Family Member Offer: " + e.getMessage());
            return null;
        }
    }


    public static boolean searchOffersWithName(String promotionName) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Searching for the promotion with name: " + promotionName);
            WebOperations.clickAndType("searchinputfield", promotionName);
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click and type into Input Field: " + e.getMessage());
            return false;
        }
    }

    public static boolean searchWithNameMobile(String Name) {
        try {
            System.out.println("------------------------ : " + Name);
            ExtentTestManager.getTest().log(Status.INFO, "Searching with name: " + Name);
            WebOperations.clickAndType("searchinputfieldmobile", Name);
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Show Results");
            WebOperations.clickElement("showresult");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click and type into Input Field: " + e.getMessage());
            return false;
        }
    }

    public static boolean searchWithName(String Name) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Searching with name: " + Name);
            WebOperations.clickAndType("searchinputfield", Name);
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click and type into Input Field: " + e.getMessage());
            return false;
        }
    }




    public static boolean navigateToTheOffersListingPageWeb(){
        try{
            Homepage.clickRejectCookies();
            Homepage.clickMainMenu();
            MainMenu.clickOffersLink();
            Homepage.clickNewsLetterCloseButton();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to navigate to the offers listing page " + e.getMessage());
            return false;
        }
    }

    public static boolean navigateToTheOffersListingPageMobile() {
        try {
            Homepage.clickRejectCookies();
            WebOperations.wait(4000);
            if(WebOperations.isElementPresent("newsletterclosebutton")){
                WebOperations.clickElement("newsletterclosebutton");
            }
            Homepage.clickMainMenu();
            String mpNameAndLanguage = System.getProperty("mpNameAndLanguage");

            if ((Objects.nonNull(mpNameAndLanguage) && (mpNameAndLanguage.equals("livatHammersmithTestEn"))) || MeetingPlaceConfig.getMeetingplace().equals("Livat Hammersmith") && "Testing".equals(mConfigProps.getProperty("environment"))) {
                MainMenu.clickMenuOccasionsButton();
            } else {
                MainMenu.clickMenuShopsButton();
            }

            if(WebOperations.isElementPresent("newsletterclosebutton")){
                WebOperations.clickElement("newsletterclosebutton");
            }

            MainMenu.clickOffersLink();
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to navigate to the offers listing page: " + e.getMessage());
            return false;
        }
    }



}
