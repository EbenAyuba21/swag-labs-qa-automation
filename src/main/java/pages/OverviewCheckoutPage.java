package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OverviewCheckoutPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	private By pageTitle = By.cssSelector("[data-test = 'title']");
	private By inventoryItemName = By.cssSelector("[data-test = 'inventory-item-name']");
	private By inventoryItemPrice = By.cssSelector("[data-test = 'inventory-item-price']");
	private By paymentInformation = By.cssSelector("[data-test = 'payment-info-value']");
	private By shippingInformation = By.cssSelector("[data-test = 'shipping-info-value']");
	private By subTotalInformation = By.cssSelector("[data-test= 'subtotal-label']");
	private By totalInformation = By.cssSelector("[data-test = 'total-label']");
	private By cancelButton = By.id("cancel");
	private By finishButton = By.id("finish");
	
	public OverviewCheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean verifyPresenceOfPageTitle() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
	}
	
	public boolean verifyPresenceOfInventoryItemName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemName)).isDisplayed();
	}
	
	public boolean verifyPresenceOfInventoryItemPrice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemPrice)).isDisplayed();
	}
	
	public boolean verifyPresenceOfPaymentInformation() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentInformation)).isDisplayed();
	}
	
	public boolean verifyPresenceOfShippingInformation() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(shippingInformation)).isDisplayed();
	}
	
	public boolean verifyPresenceOfSubTotalInformation() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(subTotalInformation)).isDisplayed();
	}
	
	public boolean verifyPresenceOfTotalPriceInformation() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(totalInformation)).isDisplayed();
	}
	
	public void clickCancelButton() {
		driver.findElement(cancelButton).click();
	}
	
	public void clickFinishButton() {
		driver.findElement(finishButton).click();
	}
}
