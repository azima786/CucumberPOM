package com.qa.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public enum Browser {
        CHROME, FIREFOX, EDGE, SAFARI
    }

    // Initialize ThreadLocal based on the given browser
    public WebDriver init_driver(String browser) {
        Browser browserType;
        try {
            browserType = Browser.valueOf(browser.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid browser type: " + browser, e);
        }

        System.out.println("Browser value is: " + browserType);

        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().driverVersion("128.0.6613.86").setup();
                tlDriver.set(new ChromeDriver());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                tlDriver.set(new FirefoxDriver());
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                tlDriver.set(new EdgeDriver());
                break;
            case SAFARI:
                // SafariDriver requires macOS
                if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    tlDriver.set(new SafariDriver());
                } else {
                    throw new RuntimeException("SafariDriver is only available on macOS");
                }
                break;
            default:
                throw new RuntimeException("Browser not supported: " + browserType);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        return getDriver();
    }

    // Get Driver with ThreadLocal
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    // Remove ThreadLocal after use to prevent memory leaks
    public static void cleanUp() {
        WebDriver driver = tlDriver.get();
        if (driver != null) {
            driver.quit();
            tlDriver.remove();
        }
    }
}
