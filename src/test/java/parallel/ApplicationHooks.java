package parallel;

import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.FileNotFoundException;
import java.util.Properties;

public class ApplicationHooks {
    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;

    @Before(order = 0)
    public void getProperty() throws FileNotFoundException {
        configReader = new ConfigReader();
        prop = configReader.init_prop();

    }

    @Before(order = 1)
    public void launchBrowser() {
        String browser = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browser);

    }

    @After(order = 1)
    public void quitBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                // Generate a unique screenshot name with a timestamp
                String screenshotName = scenario.getName().replaceAll(" ", "_") + "_" + System.currentTimeMillis();

                // Check if driver supports screenshot capturing
                if (driver instanceof TakesScreenshot) {
                    TakesScreenshot tsDriver = (TakesScreenshot) driver;
                    byte[] screenshotBytes = tsDriver.getScreenshotAs(OutputType.BYTES);

                    // Attach the screenshot to the report
                    scenario.attach(screenshotBytes, "image/png", screenshotName);
                } else {
                    System.out.println("Driver does not support taking screenshots.");
                }
            } catch (Exception e) {
                // Handle potential exceptions during screenshot capture
                System.err.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }
}


