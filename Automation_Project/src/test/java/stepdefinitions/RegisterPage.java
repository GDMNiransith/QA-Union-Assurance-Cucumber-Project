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

public class RegisterPage {

    private WebDriver driver;
    private final String BASE_URL = "http://demo.guru99.com/test/newtours/";
    private final String UNIQUE_USERNAME = "user_" + System.currentTimeMillis();
    private final String PASSWORD = "Password1";
    private final Duration TIMEOUT = Duration.ofSeconds(10);


    @Given("I navigate to the NewTours Registration page")
    public void iNavigateToTheRegistrationPage() {
        driver = DriverFactory.createDriver();
        driver.get(BASE_URL + "register.php");

        try {
            WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
            WebElement firstNameField = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.name("firstName"))
            );

            Assert.assertTrue("Registration form (First Name field) not visible after 10s.", firstNameField.isDisplayed());
            System.out.println("Navigated to Registration Page and verified form presence.");

        } catch (Exception e) {
            System.err.println("FATAL: Navigation to Registration Page failed. Closing driver.");
            DriverFactory.quitDriver(driver);
            throw e;
        }
    }


    @When("I enter personal details and contact information")
    public void iEnterPersonalDetailsAndContactInformation() {
        driver.findElement(By.name("firstName")).sendKeys("Test");
        driver.findElement(By.name("lastName")).sendKeys("User");
        driver.findElement(By.name("phone")).sendKeys("5551234567");
        driver.findElement(By.name("userName")).sendKeys("test.user@email.com");
        driver.findElement(By.name("address1")).sendKeys("123 Automation Lane");
        driver.findElement(By.name("city")).sendKeys("Selenium City");
        driver.findElement(By.name("state")).sendKeys("TX");
        driver.findElement(By.name("postalCode")).sendKeys("77001");
    }

    @When("I enter user credentials with unique data")
    public void iEnterUserCredentialsWithUniqueData() {
        driver.findElement(By.name("email")).sendKeys(UNIQUE_USERNAME);
        driver.findElement(By.name("password")).sendKeys(PASSWORD);
        driver.findElement(By.name("confirmPassword")).sendKeys(PASSWORD);
        System.out.println("Entered credentials for registration: " + UNIQUE_USERNAME);
    }

    @When("I submit the registration form")
    public void iSubmitTheRegistrationForm() {
        driver.findElement(By.name("submit")).click();
        System.out.println("Registration form submitted.");
    }

    // --- THEN Step ---
    @Then("I should be navigated to the registration confirmation page containing {string}")
    public void iShouldBeNavigatedToTheRegistrationConfirmationPage(String expectedText) {
        WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + expectedText + "')]"))
        );

        String actualText = messageElement.getText();
        Assert.assertTrue(
                String.format("Registration failed. Expected page to contain '%s', but got '%s'.", expectedText, actualText),
                actualText.contains(expectedText)
        );

        System.out.println("Registration Successful! Message found: " + actualText);
        DriverFactory.quitDriver(driver);
    }
}
