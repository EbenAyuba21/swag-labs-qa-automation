package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	private By pageTitle = By.cssSelector("[data-test = 'title']");
	private By shoppingcartBadge = By.cssSelector("[data-test = 'shopping-cart-badge']");
	private By inventoryItemName = By.cssSelector("[data-test = 'inventory-item-name']");
	private By inventoryItemPrice = By.cssSelector("[data-test = 'inventory-item-price']");
	private By removeButton = By.xpath("//button[contains(text(), 'Remove')]");
	private By continueShoppingButton = By.name("continue-shopping");
	private By checkoutButton = By.id("checkout");
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean verifyPageTitleTestIsVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
	}
	
	public boolean verifyAbsenceofItemsinShoppingCart() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(inventoryItemName));
	}
	
	public String getShoppingCartBadgeText() {
		return driver.findElement(shoppingcartBadge).getText();
	}
	
	public List<WebElement> getInventoryItemNames(){
		return driver.findElements(inventoryItemName); 
	}
	
	public List<WebElement> getInventoryItemPrices(){
		return driver.findElements(inventoryItemPrice); 
	}
	
	public List<WebElement> getRemoveButtons() {
		return driver.findElements(removeButton);
	}
	
	public void clickContinueShoppingButton() {
		driver.findElement(continueShoppingButton).click();
	}
	
	public void clickCheckout() {
		driver.findElement(checkoutButton).click();
	}
	
	public boolean verifyAbsenceOfShoppingCartBadgeText() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingcartBadge));
	}

}
