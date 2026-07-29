package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.WebConstants;
import util.WebOperations;

import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.WebOperations.mConfigProps;
import static webdriver.WebDriverSetup.driver;

public class Tenant {

    public static boolean clickAndTypeFindShopInputField(String text) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking and typing '"+text+"' into Find Shop Input Field");
            WebOperations.clickAndType("searchinputfield", text);
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click and type into Find Shop Input Field: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickAndTypeFindShopInputFieldMobile(String text) {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking and typing '"+text+"' into Find Shop Input Field");
            Tenant.clickAllFiltersMobile();
            WebOperations.clickAndType("searchinputfieldmobile", text);
            WebOperations.clickElement("showresult");
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click and type into Find Shop Input Field: " + e.getMessage());
            return false;
        }
    }

    public static boolean clearSearchFieldAndSelectCategory() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clearing Find Shop Input Field, Clicking Select Category Dropdown, and Selecting List Item");
            WebElement element = WebOperations.getWebElement("searchinputfield");
            Actions actions = new Actions(driver);

            for (int i = 0; i < 4; i++) {
                actions.sendKeys(element,Keys.BACK_SPACE).perform();
            }
            WebOperations.clickElement("selectcategorydropdown");
            WebOperations.clickElement("listitem");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to clear input field and select category: " + e.getMessage());
            return false;
        }
    }

    public static boolean clearSearchFieldAndSelectCategoryMobile() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clearing Find Shop Input Field, Clicking Select Category Dropdown, and Selecting List Item");
            Tenant.clickAllFiltersMobile();
            WebElement element = WebOperations.getWebElement("searchinputfieldmobile");
            Actions actions = new Actions(driver);
            for (int i = 0; i < 4; i++) {
                actions.sendKeys(element,Keys.BACK_SPACE).perform();
            }
            WebOperations.clickElement("filtercategories");
            WebOperations.clickElement("showresult");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to clear input field and select category: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickAllFiltersAndClearAll() {
        try {
            WebOperations.clickElement("allfilters");
            WebOperations.clickElement("clearall");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking All Filters and Clear All");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click All Filters and Clear All: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickAllFiltersMobileAndClearAll() {
        try {
            WebOperations.clickElement("mobileallfiltersicon");
            WebOperations.clickElement("clearall");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking All Filters and Clear All");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click All Filters and Clear All: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickAllFilters() {
        try {
            WebOperations.clickElement("allfilters");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking All Filters");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.FAIL, "Failed to click All Filters: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickAllFiltersMobile() {
        try {
            WebOperations.clickElement("mobileallfiltersicon");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Filters Icon");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.INFO, "Failed to click Filters Icon: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickShowMoreButton() {
        try {
            WebOperations.clickElement("showmore");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Show more button");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Show more button: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickClearAll() {
        try {
            WebOperations.clickElement("clearall");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking clear all button");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click clear all : " + e.getMessage());
            return false;
        }
    }

    public static boolean clickFilterPageCloseButton() {
        try {
            WebOperations.clickElement("filterclosebutton");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking close button button");
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click close button : " + e.getMessage());
            return false;
        }
    }

    public static String clickSubTenantAndGetTenantName() {
        try {
            List<WebElement> subTenant = WebOperations.getWebElements("subtenants");
            int randomIndex = WebOperations.getRandomIndex(subTenant.size());
            String shopName = subTenant.get(randomIndex).getText();
            subTenant.get(randomIndex).click();
            ExtentTestManager.getTest().log(Status.INFO, "Retrieving first Subtenant "+shopName+" and clicking on it");
            WebOperations.wait(WebConstants.WAIT_TIME_3_SEC);
            return shopName;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to retrieve and click the first Subtenant: " + e.getMessage());
            return null;
        }
    }

    public static String clickSubTenantAndGetTenantName(int index) {
        try {
            List<WebElement> subTenant = WebOperations.getWebElements("subtenants");
            String shopName = subTenant.get(index).getText();
            subTenant.get(index).click();
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            ExtentTestManager.getTest().log(Status.INFO, "Retrieving first Subtenant "+shopName+" and clicking on it");
            return shopName;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to retrieve and click the first Subtenant: " + e.getMessage());
            return null;
        }
    }

    public static boolean clickReadMore() {
        try {
            WebOperations.clickElement("readmore");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Read More");
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Read More: " + e.getMessage());
            return false;
        }
    }

    public static boolean clickReadLess() {
        try {
            WebOperations.clickElement("readless");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Read Less");

            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Read Less: " + e.getMessage());
            return false;
        }
    }

    public static int clickCategoryAndGetFilteredResults() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking category : " + WebOperations.getWebElements("filtercategories").get(0).getText() );
            WebOperations.getWebElements("filtercategories").get(0).click();
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
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
            return -1;
        }
    }

    public static int clickOffersAndGetFilteredResults() {
        try {
            WebOperations.clickElement("offers");
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            ExtentTestManager.getTest().log(Status.INFO, "Clicking category : " + WebOperations.getWebElements("filtercategories").get(0).getText() );
            String showResultsText = WebOperations.getText("showresult");
            String filteredNumber = showResultsText.replaceAll("[^0-9]", "");
            WebOperations.clickElement("showresult");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Show Results");
            if(WebOperations.isElementPresent("showmore")){
                WebOperations.scrollToElement("showmore");
                WebOperations.clickElement("showmore");
                ExtentTestManager.getTest().log(Status.INFO, "Clicking Show more cards");
            }
            return Integer.parseInt(filteredNumber);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Error occurred: " + e.getMessage());
            return -1;
        }
    }

    public static int clickAdditionalPerksAndGetFilteredResults() {
        try {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Additional Perk : " + WebOperations.getWebElements("additionalperkscategories").get(0).getText() );
            WebOperations.clickElement("additionalperkscategories");
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            String showResultsText = WebOperations.getText("showresult");
            String filteredNumber = showResultsText.replaceAll("[^0-9]", "");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Show Results");
            WebOperations.clickElement("showresult");
            if(WebOperations.isElementPresent("showmore")){
                WebOperations.scrollToElement("showmore");
                WebOperations.clickElement("showmore");
                ExtentTestManager.getTest().log(Status.INFO, "Clicking Show more cards");
            }
            return Integer.parseInt(filteredNumber);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Error occurred: " + e.getMessage());
            return -1;
        }
    }



    public static int getVisibleCardNumber() {
        try{
            WebOperations.scrollToElement("showmore");
            String showingNumbersForVisibleCardsText = WebOperations.getText("showingnumbersforvisiblecards");
            String firstNumber = "";

            Matcher matcher = Pattern.compile("\\d+").matcher(showingNumbersForVisibleCardsText);
            if (matcher.find()) {
                firstNumber = matcher.group(); // gets the first number, i.e., "4"
            }
            int visibleCardNumber = Integer.parseInt(firstNumber);
            return visibleCardNumber;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Read Less: " + e.getMessage());
            return 0;
        }
    }

    public static boolean clickShowMoreIfVisible() {
        try {
            boolean isShowMoreButtonPresent = false;
            if (WebOperations.isElementPresent("showmore")) {
                WebOperations.scrollToElement("showmore");
                isShowMoreButtonPresent = true;
                WebOperations.clickElement("showmore");
                ExtentTestManager.getTest().log(Status.INFO, "Checking if Show More is visible and clicking it");
            }
            return isShowMoreButtonPresent;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to click Show More if visible: " + e.getMessage());
            return false;
        }
    }

    public static boolean navigateToTheFAndBPageMobile(){
        try{
            Homepage.clickRejectCookies();
            WebOperations.wait(4000);
            if(WebOperations.isElementPresent("newsletterclosebutton")){
                WebOperations.clickElement("newsletterclosebutton");
            }
            Homepage.clickMainMenu();
            String mpNameAndLanguage = System.getProperty("mpNameAndLanguage");
            if ((Objects.nonNull(mpNameAndLanguage) && (mpNameAndLanguage.equals("livatHammersmithTestEn"))) || MeetingPlaceConfig.getMeetingplace().equals("livatHammersmithTestEn")) {
                MainMenu.clickMenuFAndBButton();
            } else {
                MainMenu.clickMenuShopsButton();
            }
            MainMenu.clickFoodAndBeverageLink();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to navigate to the offers listing page " + e.getMessage());
            return false;
        }
    }
    public static int clickSpecialffersAndGetFilteredResults() {
        try {
            WebOperations.clickElement("Special Offer");
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            ExtentTestManager.getTest().log(Status.INFO, "Clicking category : " + WebOperations.getWebElements("filtercategories").get(0).getText() );
            String showResultsText = WebOperations.getText("showresult");
            String filteredNumber = showResultsText.replaceAll("[^0-9]", "");
            WebOperations.clickElement("showresult");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking Show Results");
            if(WebOperations.isElementPresent("showmore")){
                WebOperations.scrollToElement("showmore");
                WebOperations.clickElement("showmore");
                ExtentTestManager.getTest().log(Status.INFO, "Clicking Show more cards");
            }
            return Integer.parseInt(filteredNumber);
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Error occurred: " + e.getMessage());
            return -1;
        }
    }
}
