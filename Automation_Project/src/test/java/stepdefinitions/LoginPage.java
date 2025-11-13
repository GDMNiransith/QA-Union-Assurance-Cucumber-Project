package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverFactory;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private final String BASE_URL = "http://demo.guru99.com/test/newtours/";
    private final Duration TIMEOUT = Duration.ofSeconds(10);


    private static final String REGISTERED_USERNAME = "user_1763059308259";
    private static final String PASSWORD = "Dojitha@123";

    @Given("I am on the NewTours Login page")
    public void iAmOnTheLoginPage() {
        driver = DriverFactory.createDriver();
        driver.get(BASE_URL + "index.php");

        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
        WebElement homeLink = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.linkText("Home"))
        );

        Assert.assertTrue("Login link is not displayed, likely wrong page", homeLink.isDisplayed());
        System.out.println("Navigated to Login Page.");
    }

    @When("I log in with the registered credentials")
    public void iLogInWithTheRegisteredCredentials() {
        driver.findElement(By.name("userName")).sendKeys(REGISTERED_USERNAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        System.out.println("Entered credentials for login: " + REGISTERED_USERNAME);
    }

    @When("I click the submit button")
    public void iClickTheSubmitButton() {
        driver.findElement(By.name("submit")).click();
        System.out.println("Login form submitted.");
    }

    @Then("I should be navigated to the {string} page")
    public void iShouldBeNavigatedToThePage(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + expectedText + "')]"))
        );

        String actualText = messageElement.getText();
        Assert.assertTrue(
                String.format("Login failed. Expected page to contain '%s', but got '%s'.", expectedText, actualText),
                actualText.contains(expectedText)
        );

        System.out.println("Login Successful! Message found: " + actualText);
        DriverFactory.quitDriver(driver);
    }
}
