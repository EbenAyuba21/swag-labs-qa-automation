package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryItemDetailsPage {
	private WebDriver driver;
	private WebDriverWait wait;
	
	private By inventoryItemName = By.cssSelector("[data-test = 'inventory-item-name']");
	private By inventoryItemPrice = By.cssSelector("[data-test = 'inventory-item-price']");
	private By addToCartButton = By.name("add-to-cart");
	private By removeButton = By.name("remove");
	private By shoppingcartBadge = By.cssSelector("[data-test = 'shopping-cart-badge']");
	private By shoppingCartButton = By.cssSelector("[data-test = 'shopping-cart-link']"); 
	
	public InventoryItemDetailsPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean verifyPresenceOfItemName() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemName)).isDisplayed();
	}
	
	public boolean verifyPresenceOfItemPrice() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(inventoryItemPrice)).isDisplayed();
	}
	
	public String getItemName() {
		return driver.findElement(inventoryItemName).getText();
	}
	
	public String getItemPrice() {
		return driver.findElement(inventoryItemPrice).getText();
	}
	
	public void clickAddToCart() {
		driver.findElement(addToCartButton).click();
	}
	
	public void clickRemoveButton() {
		driver.findElement(removeButton).click();
	}
	
	public String getShoppingCartBadgeText() {
		return driver.findElement(shoppingcartBadge).getText();
	}
	
	public void clickShoppingCartButton() {
		driver.findElement(shoppingCartButton).click();
	}
	
	public boolean verifyAbsenceOfShoppingCartBadgeText() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingcartBadge));
	}

}
