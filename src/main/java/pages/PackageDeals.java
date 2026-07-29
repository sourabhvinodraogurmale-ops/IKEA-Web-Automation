package pages;

import com.aventstack.extentreports.Status;
import report.ExtentTestManager;
import util.MeetingPlaceConfig;
import util.Message;
import util.WebConstants;
import util.WebOperations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import static util.WebOperations.mConfigProps;

public class PackageDeals {

    public static String getPackageDealAndClick() {
        try {
            String packageDealTitle = WebOperations.getText("packageofferslistcardtitle");
            WebOperations.clickElement("packageofferslist");
            ExtentTestManager.getTest().log(Status.INFO, "Clicking on the 'Package Offers: " + packageDealTitle);
            WebOperations.wait(WebConstants.WAIT_TIME_2_SEC);
            return packageDealTitle;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to fetch or click Package Offer");
            e.printStackTrace();
            return null;  // Return null if there was an error
        }
    }

    /**
     * This function checks if the end date is in the past.
     *
     * @param dateString The date range string (e.g., "2 de jan. - 31 de dez. de 2025")
     * @return true if the end date is in the past, false otherwise.
     */
    public static boolean isEndDateInPast(String dateString) {
        // Extract the end date part (after the " - " symbol)
        if (dateString.contains(" - ")) {
            dateString = dateString.split(" - ")[1].trim();
        }
        DateTimeFormatter formatter = null;
        if (MeetingPlaceConfig.getMeetingPlaceLanguage().equals("pt")) {
            Map<String, String> monthMap = new HashMap<>();
            monthMap.put("jan.", "Jan");
            monthMap.put("fev.", "Feb");
            monthMap.put("mar.", "Mar");
            monthMap.put("abr.", "Apr");
            monthMap.put("mai.", "May");
            monthMap.put("jun.", "Jun");
            monthMap.put("jul.", "Jul");
            monthMap.put("ago.", "Aug");
            monthMap.put("set.", "Sep");
            monthMap.put("out.", "Oct");
            monthMap.put("nov.", "Nov");
            monthMap.put("dez.", "Dec");

            for (Map.Entry<String, String> entry : monthMap.entrySet()) {
                dateString = dateString.replace(entry.getKey(), entry.getValue());
            }
            formatter = DateTimeFormatter.ofPattern("d 'de' MMM 'de' yyyy", Locale.ENGLISH);

        } else {
            if(dateString.contains("From ")){
                return false;
            }
            dateString = dateString.replace("Until ", "").trim();
            formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);
        }
        try {
            LocalDate endDate = LocalDate.parse(dateString, formatter);
            LocalDate currentDate = LocalDate.now();
            return endDate.isBefore(currentDate);
        } catch (Exception e) {
            System.out.println("Error parsing the date: " + e.getMessage());
            return false;
        }
    }

    public static boolean navigateToTheLPOListingPageMobile(){
        try{
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
            MainMenu.clickPackageDealsLink();
            return true;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to navigate to the offers listing page " + e.getMessage());
            return false;
        }
    }

    public static boolean navigateToTheLPOListingPage(){
        try{
            Homepage.clickRejectCookies();
            Homepage.clickMainMenu();
            boolean isPackageDealsLinkPresent = WebOperations.isElementPresent("packagedealslink");
            MainMenu.clickPackageDealsLink();
            Homepage.clickNewsLetterCloseButton();
            return isPackageDealsLinkPresent;
        } catch (Exception e) {
            ExtentTestManager.getTest().log(Status.WARNING, "Failed to navigate to the offers listing page " + e.getMessage());
            return false;
        }
    }

}
