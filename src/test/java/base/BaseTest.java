package base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    protected static final String TEST_RESOURCES_DIR = "src/test/resources/";
    protected static final String DOWNLOADS_DIRECTORY = TEST_RESOURCES_DIR.concat("downloads/");
    protected static final String SCREENSHOTS_DIRECTORY = TEST_RESOURCES_DIR.concat("screenshots/");
    protected static final String REPORTS_DIRECTORY = TEST_RESOURCES_DIR.concat("reports/");

    protected WebDriver driver;
    protected Logger log;

    @BeforeSuite
    protected final void setupTestSuite() throws IOException {
        cleanDirectory(REPORTS_DIRECTORY);
        cleanDirectory(SCREENSHOTS_DIRECTORY);
    }

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, Method method) {
        String tafName = "SPP TAF";
        log = LogManager.getLogger(tafName);

        log.info(" ==== TEST CASE NAME: " + method.getName() + " ====");

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
    }

    @AfterMethod(alwaysRun = true)
    public void closingDriver(ITestResult testResult) {
        log.info("Closing the WebDriver");
        driver.quit();
    }

    @AfterSuite
    public void cleanFiles() throws IOException {
        cleanDirectory(DOWNLOADS_DIRECTORY);
    }

    private void takeScreenshot(ITestResult testResult) throws IOException {
        if (ITestResult.FAILURE == testResult.getStatus()) {
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File screenshot = takesScreenshot.getScreenshotAs(OutputType.FILE);
            String testName = testResult.getName();

            FileUtils.copyFile(screenshot, new File(SCREENSHOTS_DIRECTORY.concat(testName).concat(".jpg")));
        }
    }

    private ChromeOptions configChromeOptions() {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory",
                System.getProperty("user.dir") + File.separator + DOWNLOADS_DIRECTORY);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.addArguments("disable-popup-blocking");

        return chromeOptions;
    }

    private void cleanDirectory(String directoryPath) throws IOException {
        System.out.println("____________________________________________________________");
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            FileUtils.forceMkdir(directory);
            System.out.println("Created directory: " + directoryPath);
        }

        System.out.println("Deleting not needed files from folder with path: " + directoryPath);
        FileUtils.cleanDirectory(directory);

        String[] fileList = directory.list();
        if (fileList != null && fileList.length == 0) {
            System.out.printf("All files are deleted in Directory: %s%n", directoryPath);
        } else {
            System.out.printf("Unable to delete the files in Directory: %s%n", directoryPath);
        }
        System.out.println("____________________________________________________________");
    }
}