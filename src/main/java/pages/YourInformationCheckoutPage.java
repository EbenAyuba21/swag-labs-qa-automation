package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YourInformationCheckoutPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private By pageTitle = By.cssSelector("[data-test = 'title']");
	private By firstNameField = By.id("first-name");
	private By lastNameField = By.id("last-name");
	private By postalCodeField = By.id("postal-code");
	private By cancelButton = By.id("cancel");
	private By continueButton = By.id("continue");
	private By firstNameRequiredErrorMessage = By.xpath("//h3[contains(text(), 'Error: First Name is required')]");
	private By lastNameRequiredErrorMessage = By.xpath("//h3[contains(text(), 'Error: Last Name is required')]");	
	private By postalCodeRequiredErrorMessage = By.xpath("//h3[contains(text(), 'Error: Postal Code is required')]");
	
	public YourInformationCheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean verifyPresenceOfPageTitleText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
	}
	
	public void enterFirstname(String firstname) {
		driver.findElement(firstNameField).sendKeys(firstname);
	}
	
	public void enterLastName(String lastname) {
		driver.findElement(lastNameField).sendKeys(lastname);
	}
	
	public void enterPostalOrZipCode(String postalOrZipCode) {
		driver.findElement(postalCodeField).sendKeys(postalOrZipCode);
	}
	
	public void clickContinue() {
		driver.findElement(continueButton).click();
	}
	
	public void clickCancel() {
		driver.findElement(cancelButton).click();
	}
	
	public boolean verifyPresenceOfFirstNameRequiredErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameRequiredErrorMessage)).isDisplayed();
	}
	
	public boolean verifyPresenceOfLastNameRequiredErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameRequiredErrorMessage)).isDisplayed();
	}	
	
	public boolean verifyPresenceOfPostalCodeRequiredErrorMessage() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeRequiredErrorMessage)).isDisplayed();
	}	
	
	public void enterInformationAndProceed(String firstname, String lastname, String postalOrZipCode) {
		enterFirstname(firstname);
		enterLastName(lastname);
		enterPostalOrZipCode(postalOrZipCode);
		clickContinue();
	}
}
