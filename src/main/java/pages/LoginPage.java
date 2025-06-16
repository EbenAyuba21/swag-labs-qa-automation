package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private By loginPageLogo = By.xpath("//div[contains(text(), \"Swag Labs\")]");
	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	private By incorrectLoginCredentialsErrorMessage = By.xpath("//h3[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]");
	private By usernameIsRequiredErrorMessage = By.xpath("//h3[contains(text(), 'Epic sadface: Username is required')]");
	private By passwordIsRequiredErrorMessage = By.xpath("//h3[contains(text(), 'Epic sadface: Password is required')]");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public String getPageUrl() {
		System.out.println(driver.getCurrentUrl());
		return driver.getCurrentUrl();
	}
	
	public boolean verifyLoginPageLogoIsVisbile() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageLogo)).isDisplayed();
	}
	
	public void enterUsername(String username) {
		driver.findElement(usernameField).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void clickLoginButton() {
		driver.findElement(loginButton).click();
	}
	
	public boolean verifyIncorrectCredentialsErrorMessageIsVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(incorrectLoginCredentialsErrorMessage)).isDisplayed();
	}
	
	public boolean verifyusernameIsRequiredErrorMessageisVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameIsRequiredErrorMessage)).isDisplayed();
	}
	
	public boolean verifyPasswordIsRequiredErrorMessageisVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordIsRequiredErrorMessage)).isDisplayed();
	}
	
	public void enterCredentialsAndSignIn(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clickLoginButton();
	}
	
}
