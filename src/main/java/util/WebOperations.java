package util;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONTokener;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.Document;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;

import static report.ExtentTestManager.getTest;
import static webdriver.WebDriverSetup.driver;



public class WebOperations {

    private static final Logger logger = LogManager.getLogger(WebOperations.class);
    public static final Properties mConfigProps = new ReadFile().getProperties("propertyfiles/mobile.config.properties");
    static String locator;


    public static Map<String, LocatorLivat> getelementLocators() {
        Document xmlDoc = null;
        locator = "WebLocatorXML";
        xmlDoc = new ReadFile()
                .loadXML(
                        System.getProperty("user.dir") +
                                mConfigProps.getProperty(locator).replace("/", File.separator));

        Map<String, LocatorLivat> elementLocators = new ReadFile().readXMLLocatorData(xmlDoc);
        return elementLocators;
    }

    /**
     * This method loads a JSON file containing localization data (language mappings) and parses it into a nested map.
     * The outer map is keyed by language, and the inner map contains element names as keys and their localized string values as values.
     * It uses the `JSONTokener` and `org.json.JSONObject` classes to parse the JSON file.
     *
     * @return A map where the first key is the language, and the second map contains the localized element names and their respective values.
     */
    public static Map<String, Map<String, String>> LanguagesMap() {
        try {
            File jsonFile = new File(System.getProperty("user.dir") +
                    mConfigProps.getProperty("LanguageData"));

            JSONTokener tokener = new JSONTokener(new FileReader(jsonFile));
            org.json.JSONObject jsonObject = new org.json.JSONObject(tokener);


            Map<String, Map<String, String>> languageMap = new HashMap<>();

            Iterator<String> keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                org.json.JSONObject innerObject = jsonObject.getJSONObject(key);
                Map<String, String> innerMap = new HashMap<>();

                Iterator<String> innerKeys = innerObject.keys();
                while (innerKeys.hasNext()) {
                    String innerKey = innerKeys.next();
                    String value = innerObject.getString(innerKey);
                    innerMap.put(innerKey, value);
                }
                languageMap.put(key, innerMap);
            }

            return languageMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


/**
 * This method retrieves the locator value for a given element based on its name.
 * If a translated value is found in the `LanguagesMap` for the current meeting place language,
 * it replaces a placeholder `{text}` in the original locator value with the translated string.
 * If no translation is found, it returns the default locator value from the provided `elementLocators` map.
 **/

 public static String getLocatorMapValue(String elementName, Map<String, LocatorLivat> elementLocators) {
        try {
            Map<String, Map<String, String>> languageMap = LanguagesMap();
            String value = null;
            for (String key : languageMap.get(MeetingPlaceConfig.getMeetingPlaceLanguage()).keySet()) {
                if (key.contains(elementName)) {
                    value = languageMap.get(MeetingPlaceConfig.getMeetingPlaceLanguage()).get(key);
                    break;
                }
            }
            if (value == null) {
                return elementLocators.get(elementName).getValue();
            } else {
                String newval = elementLocators.get(elementName).getValue().replace("{text}", value);
                elementLocators.get(elementName).setValue(newval);
                return elementLocators.get(elementName).getValue();
            }
        } catch (Exception e) {
            // Handle any exceptions
            logger.error("Failed to get locator map value for element '{}': {}  ", elementName, e.getMessage());
            throw new RuntimeException("Failed to get locator map value for element '" + elementName + "'", e);
        }
    }


    /**
     * This method pauses the execution of the program for a specified amount of time.
     * The wait time is provided in milliseconds, and the method uses `Thread.sleep()` to achieve the delay.
     * If an error occurs during the sleep process, it throws a `RuntimeException` with the error details.
     *
     * @param wait The amount of time (in milliseconds) to pause the execution.
     * @throws RuntimeException if the thread sleep operation fails.
     */
    public static void wait(int wait) {
        try {
            Thread.sleep(wait);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to wait for " + wait / 1000 + " seconds", ex);
        }
    }


    public static int getRandomIndex(int size) {
        Random random = new Random();
        return random.nextInt(size); // Generate random index between 0 (inclusive) and size (exclusive)
    }


    /**
     * This method attempts to click on an element specified by its name.
     * It checks if the element is visible and enabled before performing the click action.
     * If the element is not clickable, an error message is logged, and a `RuntimeException` is thrown.
     * A screenshot is added to the report using `addScreenshotToExtentReport()` before the click attempt.
     *
     * @param elementName The name of the element to be clicked.
     * @throws RuntimeException if the element cannot be clicked or if an error occurs during the operation.
     */
    public static void clickElement(String elementName) {
        try {
            WebElement element = getWebElement(elementName);
            WebOperations.addScreenshotToExtentReport();
            if (element != null && element.isDisplayed()) {
                element.click();
                logger.info("Element '{}' successfully clicked.", elementName);
            } else {
                logger.error("Element '{}' is not clickable or not enabled.", elementName);
                throw new RuntimeException("Element '" + elementName + "' is not clickable or not enabled.");
            }
        } catch (Exception e) {
            logger.error("Failed to click element '{}': {}", elementName, e.getMessage());
            throw new RuntimeException("Failed to click element '" + elementName + "'", e);
        }
    }


    /**
     * This method clicks on an element specified by its name and types the provided text into it.
     * It first clears any existing text in the element, then checks if the element is clickable, enabled, and displayed.
     * If the element is not interactable, an error message is logged, and a `RuntimeException` is thrown.
     * A screenshot is added to the report using `addScreenshotToExtentReport()` after performing the action.
     *
     * @param elementName The name of the element to interact with.
     * @param text The text to type into the element.
     * @throws RuntimeException if the element cannot be clicked, typed into, or if an error occurs during the operation.
     */
    public static void clickAndType(String elementName, String text) {
        try {
            WebElement element = getWebElement(elementName);
            element.clear();
            if (element != null && element.isDisplayed() && element.isEnabled()) {
                element.click();
                logger.info("Element '{}' successfully clicked.", elementName);
                element.sendKeys(text);
                logger.info("Text '{}' successfully typed into element '{}'.", text, elementName);
            } else {
                logger.error("Element '{}' is not clickable, not enabled, or not displayed.", elementName);
                throw new RuntimeException("Element '" + elementName + "' is not clickable, not enabled, or not displayed.");
            }
            WebOperations.addScreenshotToExtentReport();
        } catch (Exception e) {
            logger.error("Failed to click and type into element '{}': {}", elementName, e.getMessage());
            throw new RuntimeException("Failed to click and type into element '" + elementName + "'", e);
        }
    }

    /**
     * This method checks whether an element specified by its name is visible on the page.
     * It retrieves the element using the provided name and checks if it is displayed.
     * The visibility status is logged, and the method returns `true` if the element is visible, otherwise `false`.
     * If an error occurs while checking the visibility, an error message is logged, and the method returns `false`.
     *
     * @param elementName The name of the element to check visibility for.
     * @return `true` if the element is visible, `false` otherwise.
     */
    public static boolean isElementPresent(String elementName) {
        try {
            WebElement element = getWebElement(elementName);
            boolean isVisible = element != null && element.isDisplayed();
            if (isVisible) {
                logger.info("Element '{}' is visible.", elementName);
            } else {
                logger.info("Element '{}' is not visible.", elementName);
            }
            return isVisible;
        } catch (Exception e) {
            logger.error("Failed to check visibility of element '{}': {}", elementName, e.getMessage());
            return false;
        }
    }

    public static boolean isElementVisible(String elementName) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement element = WebOperations.getWebElement(elementName);
        boolean isVisible = (Boolean) js.executeScript(
                "var elem = arguments[0],\n" +
                        "  box = elem.getBoundingClientRect(),\n" +
                        "  viewHeight = Math.max(document.documentElement.clientHeight, window.innerHeight),\n" +
                        "  viewWidth = Math.max(document.documentElement.clientWidth, window.innerWidth);\n" +
                        "return (box.top >= 0 && box.left >= 0 && box.bottom <= viewHeight && box.right <= viewWidth);",
                element);
        if (isVisible) {
            logger.info("Element '" + elementName + "' is visible in the viewport.");
        } else {
            logger.info("Element '" + elementName + "' is NOT visible in the viewport.");
        }
        return isVisible;
    }


    /**
     * This method retrieves the text of an element specified by its name.
     * It retrieves the element using the provided name and fetches the text content.
     * If successful, it logs the action and returns the text; if an error occurs, it logs the error and returns `null`.
     *
     * @param elementName The name of the element to get the text from.
     * @return The text of the element, or `null` if an error occurs.
     */
    public static String getText(String elementName) {
        try {
            WebElement element = getWebElement(elementName);
            String getText = element.getText();
            logger.info("Get text from element '{}'", elementName);
            return getText;
        } catch (Exception e) {
            logger.error("Failed to get text from element '{}': {}", elementName, e.getMessage());
            return null;
        }
    }

    /**
     * This method retrieves a single web element specified by its name.
     * It uses the corresponding locator type (id, xpath, or css) to find the element on the page.
     * If the element is found, it is returned; if an error occurs during the process, a `RuntimeException` is thrown.
     * A waiting mechanism is implemented using `WebDriverWait` to ensure the element is present before interacting with it.
     *
     * @param elementName The name of the element to be retrieved.
     * @return The found `WebElement` if successful, or throws a `RuntimeException` if an error occurs.
     * @throws RuntimeException if the element cannot be found or an error occurs during retrieval.
     */
    public static WebElement getWebElement(String elementName) {
        try {
            Map<String, LocatorLivat> locatorsMap = getelementLocators();
            String value = getLocatorMapValue(elementName, locatorsMap);
            System.out.println("locator value ------ " + value);
            String type = locatorsMap.get(elementName).getType();
            WebElement el = null;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

            switch (type) {
                case "id":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
                    el = driver.findElement(By.id(value));
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
                    el = driver.findElement(By.xpath(value));
                    break;
                case "css":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(value)));
                    el = driver.findElement(By.cssSelector(value));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported locator type: " + type);
            }

            WebOperations.wait(WebConstants.WAIT_TIME_1_SEC);
            logger.info("Element '{}' successfully retrieved ", elementName);
            logger.info(el+ "-----" +el.getText());
            return el;
        } catch (Exception e) {
            logger.error("Failed to find element '{}': {}  ", elementName, e.getMessage());
            throw new RuntimeException("Failed to find element '" + elementName + "'", e);
        }
    }

    /**
     * This method retrieves a list of web elements specified by their name.
     * It uses the corresponding locator type (id, xpath, or css) to find all matching elements on the page.
     * If the elements are found, they are returned; if an error occurs during the process, an empty list is returned.
     * A waiting mechanism is implemented using `WebDriverWait` to ensure the elements are present before interacting with them.
     *
     * @param elementName The name of the elements to be retrieved.
     * @return A list of `WebElement` objects if successful, or an empty list if an error occurs.
     */
    public static List<WebElement> getWebElements(String elementName) {
        try {
            Map<String, LocatorLivat> locatorsMap = getelementLocators();
            String value = getLocatorMapValue(elementName, locatorsMap);
            String type = locatorsMap.get(elementName).getType();
            List<WebElement> elements = new ArrayList<>();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            switch (type) {
                case "id":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
                    elements = driver.findElements(By.id(value));
                    break;
                case "xpath":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
                    elements = driver.findElements(By.xpath(value));
                    break;
                case "css":
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(value)));
                    elements = driver.findElements(By.cssSelector(value));
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported locator type: " + type);
            }

            logger.info("Retrieved {} elements for '{}'", elements.size(), elementName);
            return elements;
        } catch (Exception e) {
            logger.error("Failed to retrieve elements for '{}': {}", elementName, e.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     * This method checks if the specified element is empty.
     * It uses the locator type (id, xpath, or css) to find the element on the page and checks if it exists.
     * The method returns `true` if the element is empty (i.e., not present on the page) and `false` otherwise.
     * If the element is not found in the locator map, it logs an error and returns `false`.
     *
     * @param elementName The name of the element to check.
     * @return `true` if the element is empty (not found), otherwise `false`.
     */
    public static boolean isElementEmpty(String elementName) {
        try {
            Map<String, LocatorLivat> locatorsMap = getelementLocators();
            if (!locatorsMap.containsKey(elementName)) {
                logger.error("Element '{}' not found in locator map.", elementName);
                return false;
            }
            String value = getLocatorMapValue(elementName, locatorsMap);
            String type = locatorsMap.get(elementName).getType();
            boolean isEmpty = false;

            switch (type) {
                case "id":
                    isEmpty = driver.findElements(By.id(value)).isEmpty();
                    break;
                case "xpath":
                    isEmpty = driver.findElements(By.xpath(value)).isEmpty();
                    break;
                case "css":
                    isEmpty = driver.findElements(By.cssSelector(value)).isEmpty();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported locator type: " + type);
            }

            logger.info("Element '{}' is empty: {}", elementName, isEmpty);
            return isEmpty;
        } catch (Exception e) {
            logger.error("Failed to check if element '{}' is empty: {}", elementName, e.getMessage());
            return false;
        }
    }

    /**
     * This method retrieves the test data from a JSON file and returns it as a string.
     * The file is read using `Files.readAllBytes` and its contents are returned.
     * If an error occurs during file reading, a `RuntimeException` is thrown.
     *
     * @return A string containing the JSON data.
     * @throws RuntimeException if an error occurs while reading the file.
     */
    public static String getJsonTestData() {
        String jsonString = null;
        try {
            jsonString = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + mConfigProps.getProperty("TestData"))));
            return jsonString;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method takes a screenshot of the current browser window and adds it to the Extent Report.
     * The screenshot is captured as a byte array, encoded as a Base64 string, and attached to the report.
     *
     * @throws RuntimeException if the screenshot capture or report logging fails.
     */
    public static void addScreenshotToExtentReport() {
        try {
            WebOperations.wait(500);
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            String base64Image = "data:image/png;base64," + Base64.getEncoder().encodeToString(screenshot);
            getTest().log(Status.INFO, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        } catch (Exception e) {
            throw new RuntimeException("Failed to add screenshot to Extent Report", e);
        }
    }

    /**
     * This method scrolls the page to bring the specified element into view.
     * It uses JavaScript to perform a smooth scroll to the element identified by its name.
     *
     * @param elementName The name of the element to scroll to.
     */
    public static void scrollToElement(String elementName) {
        try {
            WebElement element = getWebElement(elementName);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'nearest'});", element);
            logger.info("Scrolled to the element successfully.");
        } catch (Exception e) {
            logger.error("Error while scrolling to the element: {}", e.getMessage());
        }
    }

    /**
     * This method simulates pressing the TAB key to move focus to the next field on the page.
     * It uses the `Actions` class to perform the key press action.
     *
     * @throws RuntimeException if an error occurs while pressing the TAB key.
     */
    public static void clickTabKey() {
        try {
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.TAB).perform(); // Press the TAB key to move focus to the next field
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while pressing the Tab key: ", e);
        }

    }

    public static String getVisibleText(String elementName) {
        try {
            List<WebElement> elements = getWebElements(elementName);
            for (WebElement el : elements) {
                if (el.isDisplayed()) {
                    String text = el.getText();
                    logger.info("Visible text for '{}': {}", elementName, text);
                    return text;
                }
            }
            logger.warn("No visible element found for '{}'", elementName);
            return null;
        } catch (Exception e) {
            logger.error("Failed to get visible text for '{}': {}", elementName, e.getMessage());
            return null;
        }
    }

    public static void waitUntilTextChanges(String elementName, String oldText, int timeoutMillis) {
        long endTime = System.currentTimeMillis() + timeoutMillis;
        while (System.currentTimeMillis() < endTime) {
            String currentText = getVisibleText(elementName);
            if (currentText != null && !currentText.trim().equals(oldText.trim())) {
                logger.info("Text changed for '{}': '{}' -> '{}'", elementName, oldText, currentText);
                return;
            }
            wait(200); // short poll interval
        }
        logger.warn("Timeout waiting for text to change for '{}'. Old text: '{}'", elementName, oldText);
    }

}
