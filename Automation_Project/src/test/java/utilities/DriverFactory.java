package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class DriverFactory {

    private static final long TIMEOUT_SECONDS = 15;

    public static WebDriver createDriver() {
        WebDriver driver = null;
        try {

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT_SECONDS));
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
            driver = null;
        }
        return driver;
    }

    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
