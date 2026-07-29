package sanity;

import Tests.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import report.ExtentTestManager;
import util.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SanityTestCases extends BaseTest {

    /**
     * This test method verifies that the cookies settings screen can be opened and that the Reject All Cookies button disappears
     * after it is clicked, indicating that the settings have been applied.
     */

    @Test
    public void verifyCookiesSettingOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Cookies Setting Option");
        Homepage.clickCookiesSetting();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the opening of the cookies setting screen");
        Assert.assertTrue(WebOperations.isElementPresent("rejectallcookiessettings"));
        Homepage.clickRejectAllCookiesSettings();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the cookies setting screen is removed after clicking Accept All or Reject All button");
        Assert.assertFalse(WebOperations.isElementPresent("rejectallcookiessettings"), Message.REJECT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
    }

    /**
     * This test method verifies that the "Accept All Cookies" option is visible, clickable, and removes the "Accept All Cookies" button
     * after being clicked.
     */
    @Test
    public void verifyCookiesAcceptAllOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Cookies Accept All Option");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the Accept All Cookies button");
        Assert.assertTrue(WebOperations.isElementPresent("acceptallcookies") , Message.ACCEPT_ALL_COOKIES_BUTTON_NOT_VISIBLE);
        Homepage.clickAcceptCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the Accept All Cookies button is removed after clicking");
        Assert.assertFalse(WebOperations.isElementPresent("acceptallcookies"), Message.ACCEPT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
    }

    /**
     * This test method verifies that the "Reject All Cookies" option works correctly and removes the button after clicking on it.
     */
    @Test
    public void verifyCookiesRejectAllOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Cookies Reject All Option");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the Reject All Cookies button");
        Assert.assertTrue(WebOperations.isElementPresent("rejectallcookies"),Message.REJECT_ALL_COOKIES_BUTTON_NOT_VISIBLE );
        Homepage.clickRejectCookies();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the Reject All Cookies button is removed after clicking");
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        Assert.assertFalse(WebOperations.isElementPresent("rejectallcookies"), Message.REJECT_ALL_BUTTON_VISIBLE_AFTER_CLICK);
    }

    /**
     * This test method ensures that searched results are displayed when searching for the term "Events" on the web version of the site.
     */
    @Test
    public void verifyWebEventsDisplayedWhenSearched() {
        String language = MeetingPlaceConfig.getMeetingPlaceLanguage();
        ExtentTestManager.startTest("[Web/" + language + "] - Verifying results are displayed when searched with the text 'Events'");

        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();

        String searchText = "Events";
        String expectedHeading = "Events";

        if (language.equalsIgnoreCase("pt")) {
            searchText = "Eventos";
            expectedHeading = "Resultados da pesquisa para \"eventos\"";
        } else if (language.equalsIgnoreCase("sv")) {
            searchText = "Evenemang";
            expectedHeading = "Sökresultat för \"evenemang\"";
        } else if (language.equalsIgnoreCase("es")) {
            searchText = "Eventos";
            expectedHeading = "Resultados de búsqueda para \"eventos\"";
        }

        Homepage.clickAndTypeInSearchInput(searchText);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"), "Search results heading is not present");
        System.out.println("Actual Heading: " + WebOperations.getText("searchresultspageheading"));
        System.out.println("Expected Heading: " + expectedHeading);

        Assert.assertEquals(WebOperations.getText("searchresultspageheading"), expectedHeading, "Search results heading does not match expected for language: " + language);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        System.out.println("Is visible searched results present: " + WebOperations.isElementPresent("visiblesearchedresults"));
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"), "No searched results are visible");
    }
//    public void verifyWebEventsDisplayedWhenSearched() {
//        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'Events'");
//        Homepage.clickRejectCookies();
//        Homepage.clickOpenSearchIcon();
//        Homepage.clickAndTypeInSearchInput("Events");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
//        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
//        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
//    }

    /**
     * This test method ensures that searched results are displayed when searching for the term "Tenant" on the web version of the site.
     */
    @Test
    public void verifyWebShopsDisplayedWhenSearched() {
        String language = MeetingPlaceConfig.getMeetingPlaceLanguage();
        ExtentTestManager.startTest("[Web/" + language + "] - Verifying results are displayed when searched with the text 'Shop'");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();

        String searchText = "Shops";
        String expectedHeading = "Shops";

        if (language.equalsIgnoreCase("pt")) {
            searchText = "Lojas";
            expectedHeading = "Resultados da pesquisa para \"lojas\"";
        } else if (language.equalsIgnoreCase("sv")) {
            searchText = "Butiker";
            expectedHeading = "Sökresultat för \"butiker\"";
        } else if (language.equalsIgnoreCase("es")) {
            searchText = "Tiendas";
            expectedHeading = "Resultados de búsqueda para \"tiendas\"";
        }

        Homepage.clickAndTypeInSearchInput(searchText);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"), "Search results heading is not present");
        Assert.assertEquals(WebOperations.getText("searchresultspageheading"), expectedHeading, "Search results heading does not match expected for language: " + language);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"), "No searched results are visible");
    }

        //        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'Shop'");
//        Homepage.clickRejectCookies();
//        Homepage.clickOpenSearchIcon();
//        Homepage.clickAndTypeInSearchInput("Shops");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
//        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
//        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
//    }

    /**
     * This test method ensures that searched results are displayed when searching for the term "Tenants" on the website.
     */
    @Test
    public void verifyWebTenantsDisplayedWhenSearched() {
        String language = MeetingPlaceConfig.getMeetingPlaceLanguage();
        ExtentTestManager.startTest("[Web/" + language + "] - Verifying results are displayed when searched with the text 'Tenants'");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();

        String searchText = "Tenants";
        String expectedHeading = "Tenants";

        if (language.equalsIgnoreCase("pt")) {
            searchText = "Inquilinos";
            expectedHeading = "Resultados da pesquisa para \"inquilinos\"";
        } else if (language.equalsIgnoreCase("sv")) {
            searchText = "Hyresgäster";
            expectedHeading = "Sökresultat för \"hyresgäster\"";
        } else if (language.equalsIgnoreCase("es")) {
            searchText = "Tenants";
            expectedHeading = "Resultados de búsqueda para \"tenants\"";
        }

        Homepage.clickAndTypeInSearchInput(searchText);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"), "Search results heading is not present");
        Assert.assertEquals(WebOperations.getText("searchresultspageheading"), expectedHeading, "Search results heading does not match expected for language: " + language);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"), "No searched results are visible");
    }
//
//        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'Tenants'");
//        Homepage.clickRejectCookies();
//        Homepage.clickOpenSearchIcon();
//        Homepage.clickAndTypeInSearchInput("Tenant");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
//        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
//        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
//    }


    /**
     * This test method verifies that searched results are displayed correctly when searched with the text 'News' on web.
     */
    @Test
//    public void verifyWebNewsDisplayedWhenSearched() {

//        String language = MeetingPlaceConfig.getMeetingPlaceLanguage();
//
//        LanguageManager.loadLanguage(language);
//
//        String searchText = LanguageManager.getValue("newspagestext,newspagesheading");
//
//        ExtentTestManager.startTest(
//                "[Web/" + language + "] - Verifying results are displayed when searched with the text '" + searchText + "'"
//        );
//
//        Homepage.clickRejectCookies();
//        Homepage.clickOpenSearchIcon();
//        Homepage.clickAndTypeInSearchInput(searchText);
//
//        ExtentTestManager.getTest().log(Status.INFO,
//                "Verifying the presence of the search results heading: "
//                        + WebOperations.getText("searchresultspageheading"));
//
//        Assert.assertTrue(
//                WebOperations.isElementPresent("searchresultspageheading"),
//                "Search results heading is not present"
//        );
//
//        ExtentTestManager.getTest().log(Status.INFO,
//                "Verifying the visibility of the searched results");
//
//        Assert.assertTrue(
//                WebOperations.isElementPresent("visiblesearchedresults"),
//                "No searched results are visible");
//    }

    public void verifyWebNewsDisplayedWhenSearched() {
        String language = MeetingPlaceConfig.getMeetingPlaceLanguage();
        ExtentTestManager.startTest("[Web/" + language + "] - Verifying results are displayed when searched with the text 'News'");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();

        String searchText = "News";
        String expectedHeading = "News";

        if (language.equalsIgnoreCase("pt")) {
            searchText = "Notícias";
            expectedHeading = "Resultados da pesquisa para \"notícias\"";
        } else if (language.equalsIgnoreCase("sv")) {
            searchText = "Nyheter";
            expectedHeading = "Sökresultat för \"nyheter\"";
        } else if (language.equalsIgnoreCase("es")) {
            searchText = "Noticias";
            expectedHeading = "Resultados de búsqueda para \"noticias\"";
        }

        Homepage.clickAndTypeInSearchInput(searchText);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"), "Search results heading is not present");
        Assert.assertEquals(WebOperations.getText("searchresultspageheading"), expectedHeading, "Search results heading does not match expected for language: " + language);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"), "No searched results are visible");
    }
//
//        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying results are displayed when searched with the text 'News'");
//        Homepage.clickRejectCookies();
//        Homepage.clickOpenSearchIcon();
//        Homepage.clickAndTypeInSearchInput("News");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the presence of the search results heading: " + WebOperations.getText("searchresultspageheading"));
//        Assert.assertTrue(WebOperations.isElementPresent("searchresultspageheading"),"Search results heading is not present");
//        ExtentTestManager.getTest().log(Status.INFO, "Verifying the visibility of the searched results");
//        Assert.assertTrue(WebOperations.isElementPresent("visiblesearchedresults"),"No searched results are visible");
//    }

    /**
     * This test method verifies that when the user clicks on the 'Shops' link from the main menu,
     * the available sub-shops are displayed correctly on the web.
     */
    @Test
    public void verifyingSubShopsAvailable() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying clicking on shop/tenants from the menu shows the available sub-shops");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the shops heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the sub-tenants are visible");
        Assert.assertTrue(WebOperations.isElementPresent("subtenants"));
    }

    /**
     * This test method ensures that when the user clicks on 'Eat and Drink' from the main menu,
     * the available sub-eat and drink options are displayed correctly.
     */

    @Test
    public void verifyingSubEatAndDrinksAvailable() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying clicking on Eat and Drink from the menu shows the available sub-eat and drink options");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the food and beverage heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the sub-tenants are visible");
        Assert.assertTrue(WebOperations.isElementPresent("subtenants"));
    }

    /**
     * This test method checks if clicking on a tenant card leads to the tenant's detailed page.
     * It verifies that the correct tenant's details are displayed after clicking the card.
     */
    @Test
    public void verifyClickingTenantCard() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying clicking on the Tenant Card leads to the Tenant Details Page");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the shops heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        for (int i = 0; i < 2; i++) {
            ExtentTestManager.getTest().log(Status.INFO, "Verifying the tenant card is correctly linked to the tenant details page for tenant " + i);
            String shopName = Tenant.clickSubTenantAndGetTenantName(i);
            Assert.assertEquals(WebOperations.getWebElement("detailedpageheading").getText(), shopName);
            driver.navigate().back();
            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
        }
    }

    /**
     * This test method verifies that the 'View on Map' option is displayed correctly on the tenant's detail page.
     * It checks that the option is visible when viewing a tenant's detailed page.
     */
    @Test
    public void verifyViewOnMapOptionDisplayed() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the 'View on Map' option is displayed");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the food and beverage heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        String shopName = Tenant.clickSubTenantAndGetTenantName();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the tenant details page heading matches the selected shop");
        Assert.assertEquals(WebOperations.getWebElement("detailedpageheading").getText(), shopName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'View on Map' option is visible");
        Assert.assertTrue(WebOperations.isElementPresent("viewonmap"), Message.VIEW_ON_MAP_BLOCK_NOT_VISIBLE);
    }

    /**
     * This test method checks the functionality of the 'Read More' and 'Read Less' options on a service details page.
     * It ensures the options work as expected to toggle between reading more or less content.
     */
    @Test
    public void verifyReadMoreAndReadLessOption() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying 'Read More' and 'Read Less' options");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickServicesLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the services heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("servicesheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        String serviceName = MainMenu.clickFirstSubServiceAndGetServiceName();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the service name matches the detailed page heading");
        Assert.assertEquals(WebOperations.getWebElement("detailedpageheading").getText(), serviceName);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Read More' button is visible");
        Assert.assertTrue(WebOperations.isElementPresent("readmore"));
        Tenant.clickReadMore();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Read Less' button is visible after clicking 'Read More'");
        Assert.assertTrue(WebOperations.isElementPresent("readless"));
        Tenant.clickReadLess();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Read More' button is visible after clicking 'Read Less'");
        Assert.assertTrue(WebOperations.isElementPresent("readmore"));
    }

    /**
     * This test method verifies the shop listing page functionality.
     * It checks the search, filter, and show more features to ensure the shop listing works correctly.
     */
    @Test
    public void verifyShopListingPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the shop listing page functionality");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the shops heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("shopsheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
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
        ExtentTestManager.getTest().log(Status.INFO, "Verifying number of visible items after clearing the search field and applying a category filter");
        Assert.assertEquals(WebOperations.getText("visiblenumberonfilter"), "1");
        Tenant.clickAllFiltersAndClearAll();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying no visible items after clearing all filters");
        Assert.assertFalse(WebOperations.isElementPresent("visiblenumberonfilter"));
        Tenant.clickShowMoreIfVisible();
    }

    /**
     * This test method verifies that tapping on the Homepage main banner opens the expected page.
     * It ensures that the text of the banner matches the heading of the detailed page.
     */
    @Test
    public void verifyTappingHomepageMainBannerOpensExpectedPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying tapping on the Homepage main banner opens the expected page");
        Homepage.clickRejectCookies();
        String bannerText = Homepage.clickHomepageBannerAndVerifyText();
        Assert.assertNotNull(bannerText,"getting null value in banner text");
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the detailed page heading matches the banner text");
        System.out.println("detail page heading : " + WebOperations.getText("detailedpageheading"));
        Assert.assertTrue(WebOperations.getText("detailedpageheading").contains(bannerText) , "Banner text does not match the detailed page heading");
    }

    /**
     * This test method verifies that the subscribe button is activated when the user fills out the subscription form.
     * It ensures that the button is initially disabled and becomes enabled once the form is completed.
     */
    @Test
    public void verifySubscribeButtonActivation() {
        String language = MeetingPlaceConfig.getMeetingPlaceLanguage();

        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the subscribe button activation");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickNewsletterSubscriptionLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join our Newsletter' heading is visible");
        // Dynamic locator based on language
//        String headingLocator = language.equalsIgnoreCase("sv")? "joinournewsletterheadingsv" : "joinournewsletterheading";
//        Assert.assertTrue(WebOperations.isElementPresent(headingLocator),"Newsletter heading is not visible");
         Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button is initially disabled");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"));
        MainMenu.fillNewsSubscriptionFormAndAgreeToTerms();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button becomes active after filling the form and agreeing to terms");
        Assert.assertTrue(WebOperations.isElementPresent("enabledsubscribebutton"));
    }

    /**
     * This test method verifies the Close button functionality in the 'Thank You' message after a newsletter subscription.
     * It checks that the 'Thank You' message closes after clicking the Close button.
     */
    @Test
    public void verifyCloseButtonInThankYouMessage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the Close button functionality in the 'Thank You' message");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickNewsletterSubscriptionLink();
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
     * This test method verifies that the subscribe button remains inactive when mandatory fields are invalid or empty
     * in the newsletter subscription form on the web.
     */
    @Test
    public void verifyWebSubscribeButtonInactiveWhenMandatoryFieldsInvalid() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying subscribe button is inactive when mandatory fields are invalid or empty");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickNewsletterSubscriptionLink();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button is initially disabled");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"));
        MainMenu.skippingAllTheFieldsEmpty();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the error messages for missing fields are visible");
        Assert.assertTrue(WebOperations.isElementPresent("missingnameerrmsg"), Message.MISSING_NAME_ERROR_MESSAGE_NOT_VISIBLE);
        Assert.assertTrue(WebOperations.isElementPresent("missingemailerrmsg"), Message.MISSING_EMAIL_ERROR_MESSAGE_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the subscribe button is still disabled after clearing all fields");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_ENABLED);
    }

    /**
     * This test method verifies that the filters sidebar is opened correctly, with all necessary elements like category heading,
     * 'Clear All' button, and 'Show Results' button visible.
     */
    @Test
    public void verifyFiltersSidebar() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the filters sidebar is opened correctly");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        WebOperations.wait(2000);
        MainMenu.clickShopsLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Tenant.clickAllFilters();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the filters sidebar is visible");
        WebOperations.wait(2000);
        System.out.println("Filters Heading Text: " + WebOperations.getText("filters"));
        Assert.assertTrue(WebOperations.isElementPresent("filters"), Message.FILTER_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the category heading is visible in the filters sidebar");
        System.out.println("Category Heading Text: " + WebOperations.getText("category"));
        Assert.assertTrue(WebOperations.isElementPresent("category"), Message.CATEGORY_HEADING_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Clear All' button is visible");
        Assert.assertTrue(WebOperations.isElementPresent("clearall"), Message.CLEAR_ALL_BUTTON_NOT_VISIBLE);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Show Results' button is visible");
        Assert.assertTrue(WebOperations.isElementPresent("showresult"), Message.SHOW_RESULTS_BUTTON_NOT_VISIBLE);
    }

    /**
     * This test method checks the functionality of the 'Clear All', 'See Results', and 'Close' buttons in the filters sidebar.
     * It verifies that after selecting a category, the sidebar is hidden, and the correct number of filtered results is displayed.
     */
    @Test
    public void verifyClearAllSeeResultsAndCloseButtons() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying the functionality of 'Clear All', 'See Results', and 'Close' buttons");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Tenant.clickAllFilters();
        int filteredNumber = Tenant.clickCategoryAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the filter sidebar is hidden after selecting a category");
        Assert.assertFalse(WebOperations.isElementPresent("filters"), Message.FILTER_HEADING_VISIBILITY);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the number of filtered results is correct");
        WebOperations.wait(3000);
        Assert.assertEquals(WebOperations.getWebElements("subtenants").size(), filteredNumber);
        Tenant.clickAllFilters();
        Tenant.clickClearAll();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the filters are cleared and the sidebar is hidden");
        Assert.assertFalse(WebOperations.isElementPresent("filters"), Message.FILTER_HEADING_VISIBILITY);
        Tenant.clickAllFilters();
        Tenant.clickFilterPageCloseButton();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the filters sidebar is closed");
        Assert.assertFalse(WebOperations.isElementPresent("filters"), Message.FILTER_HEADING_VISIBILITY);
    }

    /**
     * This test method verifies that all tenants with offers are visually indicated on the website after filtering by the 'Offers' criteria.
     * It ensures the number of visual indicators matches the filtered number of shops.
     */
    @Test
    public void verifyShopsWithOffersIndicator() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify All Tenant/Tenants that have an Offer will now have a visual indicator");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickShopsLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Tenant.clickAllFilters();
        ExtentTestManager.getTest().log(Status.INFO, "Filtering shops based on the 'Offers' criteria");
        int filteredNumber = Tenant.clickOffersAndGetFilteredResults();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the number of filtered subtenants: " + filteredNumber);
        Assert.assertFalse(WebOperations.isElementPresent("filters"), Message.FILTER_HEADING_VISIBILITY);
        Assert.assertEquals(WebOperations.getWebElements("subtenants").size(), filteredNumber, "The number of filtered subtenants does not match the expected filtered number");
        Assert.assertEquals(WebOperations.getWebElements("offersvisualindicator").size(), filteredNumber, "The number of visual indicator icons displayed does not match the expected filtered number");
    }

    /**
     * This test method verifies the functionality of the promotions page and checks that the promotion details page opens correctly.
     * It ensures that the promotion name displayed matches the detailed page heading.
     */
    @Test
    public void verifyWebPromotionsPage() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Shop: Verify ongoing OffersOrPromotions page");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickPromotionLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        Assert.assertTrue(WebOperations.isElementPresent("offersheading"), "Unable to navigate to Promotion page or Promotion heading is not visible");
        String promotionName = OffersOrPromotions.clickPromotionAndFetchPromotionName(0);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying Promotion Detail page");
        Assert.assertEquals(WebOperations.getText("detailedpageheading"), promotionName);
    }

    /**
     * This test method checks the functionality of opening the Food and Beverage explorer PDF.
     * It verifies that the 'Explorer' button works, and if clicked, the PDF is opened and displayed.
     */
    @Test
    public void verifyFoodAndBeverageExplorerPdfOpened() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Check food and beverage explorer Pdf opened");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFoodAndBeverageLink();
        Assert.assertTrue(WebOperations.isElementPresent("foodandbeverageheading"));
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        String shopName = Tenant.clickSubTenantAndGetTenantName();
        Assert.assertEquals(WebOperations.getWebElement("detailedpageheading").getText(), shopName);
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Explorer' button is available for Food & Beverage");
        if(WebOperations.isElementPresent("explorer")){
            WebOperations.clickElement("explorer");
            WebOperations.wait(20000);
            Assert.assertTrue(WebOperations.isElementPresent("google-pdf"), "Google PDF viewer is not visible");
        } else {
            ExtentTestManager.getTest().log(Status.WARNING, "Explorer button not available for this shop.");
        }
    }

    /**
     * This test method verifies the size of the service link icon when hovered over.
     * It checks that the icon size does not change unexpectedly when the user hovers over it.
     */
    @Test
    public void verifyServicesLinkIconSizeOnHover() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify services link icon size on hover");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        MainMenu.clickFloorPlanLink();
        if(WebOperations.isElementPresent("newsletterclosebutton")){
            WebOperations.clickElement("newsletterclosebutton");
        }
        MainMenu.clickFloorPlanInput();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying that the size of the service icon does not change on hover");
        boolean result = MainMenu.verifyIconSizeOnHover();
        Assert.assertTrue(result, "The icon size changed on hover!");
    }

    /**
     * This test method verifies the functionality of CAPTCHA during newsletter subscription.
     * It checks that the CAPTCHA is activated after successful subscription and the subscribe button is disabled.
     */
    @Test
    public void verifyingCAPTCHAFunctionality() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying CAPTCHA function for newsletter subscription");
        Homepage.clickRejectCookies();
        for(int i = 0; i < 2; i++){
            Homepage.clickMainMenu();
            MainMenu.clickNewsletterSubscriptionLink();
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
     * This test method verifies that the homepage banner 'Next' buttons work correctly by ensuring
     * that each banner heading is unique and clickable, and there are no duplicate banners.
     */
    @Test
    public void verifyBannerNextButtons() {

//        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage Banner next button");
//        Homepage.clickRejectCookies();
//        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
//
//        int bannerSize = WebOperations.getWebElements("bannerheading").size();
//        Set<String> uniqueHeadings = new HashSet<>();
//        List<String> bannerHeadings = new ArrayList<>();
//        String lastHeading = "";
//
//        for (int i = 0; i < bannerSize; i++) {
//            // Get only the visible/active banner heading
//            String heading = WebOperations.getVisibleText("bannerheading");
//            Assert.assertNotNull(heading, "Banner heading is null at index " + i);
//            Assert.assertFalse(heading.trim().isEmpty(), "Banner Heading is empty or next button is not working at index " + i);
//            bannerHeadings.add(heading.trim());
//            uniqueHeadings.add(heading.trim());
//
//            // Click next only if not the last banner
//            if (i < bannerSize - 1) {
//                WebOperations.clickElement("bannernextbutton");
//                if (WebOperations.isElementPresent("newsletterclosebutton")) {
//                    WebOperations.clickElement("newsletterclosebutton");
//                }
//                // Wait until the heading changes
////                WebOperations.waitUntilTextChanges("bannerheading", heading, WebConstants.WAIT_TIME_5_SEC);
//            }
//            lastHeading = heading;
//        }
//
//        Assert.assertEquals(bannerHeadings.size(), uniqueHeadings.size(), "There are duplicate banner headings.");
//        ExtentTestManager.getTest().log(Status.INFO, "Banner next buttons test passed, all banners are unique and functioning correctly.");
//    }

        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Homepage Banner next button");
        Homepage.clickRejectCookies();
        WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
        List<String> bannerHeadings = new ArrayList<>();
        Set<String> uniqueHeadings = new HashSet<>();
        int bannerSize =  WebOperations.getWebElements("bannerheading").size();
        for (int i = 1; i<bannerSize-1; i++) {
            ExtentTestManager.getTest().log(Status.INFO, "Clicking on the 'Next' button to move to the next banner.");
            Homepage.clickNextBannerButton();
            String text = WebOperations.getWebElements("bannerheading").get(i).getText();
            ExtentTestManager.getTest().log(Status.INFO, "Next banner heading: " + text);
            Assert.assertFalse(uniqueHeadings.contains(text), "Duplicate banner heading found: " + text);
            uniqueHeadings.add(text);
            bannerHeadings.add(text);
            Assert.assertFalse(text.isEmpty(), "Banner Heading is empty or next button is not working");
        }
        Assert.assertEquals(bannerHeadings.size(), uniqueHeadings.size(), "There are duplicate banner headings.");
        ExtentTestManager.getTest().log(Status.INFO, "Banner next buttons test passed, all banners are unique and functioning correctly.");
    }
    @Test
    public void verifyGlobalSearchIkea() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search Ikea Word");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("IKEA");
       // WebOperations.isElementPresent("globalSearchIkeaheading");
        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"),"IKEA");
    WebOperations.clickElement("globalSearchIkeaheading");
    WebOperations.wait(1000);
    String pageTitle = driver.getTitle();
        assert pageTitle != null;
        Assert.assertTrue(pageTitle.contains("IKEA"),"Title not matching");

    }
    @Test
    public void verifyGlobalSearchServicename() {
        String language = MeetingPlaceConfig.getMeetingPlaceLanguage();
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search Drive in Service Word");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
//        Homepage.clickAndTypeInSearchInput("Parking services");
//        // WebOperations.isElementPresent("globalSearchIkeaheading");
//        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"),"Parking services");
//        WebOperations.clickElement("globalSearchIkeaheading");
//        WebOperations.wait(1000);
//        String pageTitle = driver.getTitle();
//        assert pageTitle != null;
//        Assert.assertTrue(pageTitle.contains("Parking services"),"Title not matching");
        // Dynamic values based on language
        String searchText;
        String expectedHeading;
        String expectedTitleText;

        if (language.equalsIgnoreCase("pt")) {
            searchText = "Serviços de mobilidade";
            expectedHeading = "Serviços de mobilidade";
            expectedTitleText = "Serviços de mobilidade";
        } else if (language.equalsIgnoreCase("sv")) {
            searchText = "Service";
            expectedHeading = "Service";
        } else {
            // default (English or others)
            searchText = "Parking services";
            expectedHeading = "Service";
        }

        // Use dynamic values
        Homepage.clickAndTypeInSearchInput(searchText);
        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"),expectedHeading);
        WebOperations.clickElement("globalSearchIkeaheading");
        System.out.println();
        WebOperations.wait(1000);
        String pageTitle = driver.getTitle();
        assert pageTitle != null;
//        System.out.println("Actual Title: " + pageTitle);
//        System.out.println("Expected Text: " + expectedTitleText);
        Assert.assertTrue(pageTitle.contains(pageTitle),"Title not matching");

    }
    @Test
    public void verifyGlobalSearchShopHM() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search H & M Word");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("H & M");
        // WebOperations.isElementPresent("globalSearchIkeaheading");
        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"),"H&M");
        WebOperations.clickElement("globalSearchIkeaheading");
        WebOperations.wait(1000);
        String pageTitle = driver.getTitle();
        assert pageTitle != null;
        Assert.assertTrue(pageTitle.contains("H&M"),"Title not matching");

    }
   /* @Test
    public void verifyGlobalSearchserviceLockers() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - verify Global Search Ikea Word");
        Homepage.clickRejectCookies();
        Homepage.clickOpenSearchIcon();
        Homepage.clickAndTypeInSearchInput("Cacifos");
        // WebOperations.isElementPresent("globalSearchIkeaheading");
        Assert.assertEquals(WebOperations.getText("globalSearchIkeaheading"),"Cacifos");
        WebOperations.clickElement("globalSearchIkeaheading");
        WebOperations.wait(1000);
        String pageTitle = driver.getTitle();
        assert pageTitle != null;
        Assert.assertTrue(pageTitle.contains("Cacifos"),"Title not matching");

    }*/
    @Test
    public void VerifyUserNameSpecialchar() {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Newsletter Special char not supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("$Siddu_'Reddy");
        String value=WebOperations.getText("youhavetoprovideyourname");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
       System.out.println("rr"+value);
        Assert.assertTrue(WebOperations.isElementPresent("youhavetoprovideyourname"));

    }
    @Test
    public void VerifycontactUserNamestring() throws InterruptedException {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verifying Contact form string supported in the Name field");
        Homepage.clickRejectCookies();
        Homepage.clickMainMenu();
        Thread.sleep(10000);
        MainMenu.clickContactusLink();
        ContactFrom.contactUserNameInInputField("Rahul");
        ContactFrom.disablcontactussubmitbebutton("disablcontactussubmitbebutton");
        Assert.assertFalse(WebOperations.isElementPresent("contyouhavetoprovideyourname"));
        // Assert.assertTrue(WebOperations.isElementPresent("submit"));

    }
    @Test
    public void VerifySpacehyphenandapostrophetosupportcompoundnames () {
        ExtentTestManager.startTest("[Web/" + MeetingPlaceConfig.getMeetingPlaceLanguage() + "] - Verify Space hyphen and apostrophe to support compoundnames");
        Homepage.clickRejectCookies();
        Homepage.clickNewsLetterFromFooter();
        ExtentTestManager.getTest().log(Status.INFO, "Verifying the 'Join Our Newsletter' heading is visible");
        Assert.assertTrue(WebOperations.isElementPresent("joinournewsletterheading"));
        NewsLetterForm.addUserNameInInputField("siddu'-reddy");
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_DISABLED);
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
        Assert.assertFalse(WebOperations.isElementPresent("enabledsubscribebutton"), Message.SUBSCRIBE_BUTTON_DISABLED);}

}
