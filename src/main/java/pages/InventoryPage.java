package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	private By productsHeaderText = By.xpath("//span[contains(text(), \"Products\")]");
	private By productFilterDropdown = By.cssSelector("[data-test = 'product-sort-container']");
	private By cartButton = By.cssSelector("[data-test = 'shopping-cart-link']");
	private By shoppingcartBadge = By.cssSelector("[data-test = 'shopping-cart-badge']");
	private By inventoryItemNames = By.cssSelector("[data-test = 'inventory-item-name']");
	private By inventoryItemPrices = By.cssSelector("[data-test = 'inventory-item-price']");
	private By addToCartButtons = By.xpath("//button[contains(text(), 'Add to cart')]");
	private By removeButtons = By.xpath("//button[contains(text(), 'Remove')]");
	
	
	public InventoryPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public String getPageUrl() {
		return driver.getCurrentUrl();
	}
	
	public boolean verifyPresenceOfProductsHeaderText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(productsHeaderText)).isDisplayed();
	}
	
	public void selectFilter(String filterOption) {
		WebElement dropdown = driver.findElement(productFilterDropdown);
		Select select = new Select(dropdown);
		select.selectByVisibleText(filterOption);
	}
	
	public void clickCart() {
		driver.findElement(cartButton).click();
	}
	
	public List<WebElement> getInventoryNames() {
		System.out.println(driver.findElements(inventoryItemNames).getFirst().getText());
		return driver.findElements(inventoryItemNames);
	}
	
	public List<WebElement> getInventoryItemPrices() {
		return driver.findElements(inventoryItemPrices);
	}
	
	public List<WebElement> getInventoryAddToCartButtons() {
		return driver.findElements(addToCartButtons);
	}
	
	public List<WebElement> getInventoryRemoveButtons(){
		return driver.findElements(removeButtons);
	}
	
	public String getShoppingCartBadgeText() {
		return driver.findElement(shoppingcartBadge).getText();
	}
	
	public boolean verifyAbsenceOfShoppingCartBadgeText() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(shoppingcartBadge));
	}
	
}
