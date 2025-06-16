package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompleteCheckoutPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	private By pageTitle = By.cssSelector("[data-test = 'title']"); 
	private By checkoutCompleteMessageText = By.cssSelector("[data-test = 'complete-text']");
	private By checkoutCompleteMessageHeader = By.cssSelector("[data-test = 'complete-header']");
	private By shoppingcartBadge = By.cssSelector("[data-test = 'shopping-cart-badge']");
	private By backHomeButton = By.name("back-to-products");
	
	public CompleteCheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofNanos(10));
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean verifyPresenceOfPageTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
	}
	
	public boolean verifyCompleteMessageTextIsDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutCompleteMessageText)).isDisplayed();
	}
	
	public boolean verifyCompleteMessageHeaderIsDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutCompleteMessageHeader)).isDisplayed();
	}
	
	public boolean verifyAbsenceOfShoppingCartBadge() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingcartBadge));
	}
	public void clickBackHomeButton() {	
		driver.findElement(backHomeButton).click();
	}
}
