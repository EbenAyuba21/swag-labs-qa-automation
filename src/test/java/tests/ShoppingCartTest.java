package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.ConfigReader;
import pages.CartPage;
import pages.InventoryItemDetailsPage;
import pages.InventoryPage;
import pages.LoginPage;

public class ShoppingCartTest extends BaseTest {
	
	LoginPage loginPage;
    InventoryPage inventoryPage;
    InventoryItemDetailsPage inventoryItemDetailsPage;
    CartPage cartPage;

    String username;
    String password;
    String baseUrl;

    @BeforeMethod
    public void pageSetup() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        inventoryItemDetailsPage = new InventoryItemDetailsPage(driver);
        cartPage = new CartPage(driver);

        username = ConfigReader.get("standard_username");
        password = ConfigReader.get("password");
        baseUrl = ConfigReader.get("baseUrl");
    }
	
	
	@Test
	public void successfullyAddItemToCart() {
		loginPage.enterCredentialsAndSignIn(username, password);
		String firstInventoryName = inventoryPage.getInventoryNames().getFirst().getText();
		String firstInvetoryPrice = inventoryPage.getInventoryItemPrices().getFirst().getText();
		inventoryPage.getInventoryAddToCartButtons().getFirst().click();
		assertTrue(inventoryPage.getShoppingCartBadgeText().equalsIgnoreCase("1"));
		inventoryPage.clickCart();
		assertTrue(cartPage.getPageUrl().equalsIgnoreCase(baseUrl + "/cart.html"));
		assertTrue(cartPage.verifyPageTitleTestIsVisible());
		assertTrue(cartPage.getInventoryItemNames().getFirst().getText().equalsIgnoreCase(firstInventoryName));
		assertTrue(cartPage.getInventoryItemPrices().getFirst().getText().equalsIgnoreCase(firstInvetoryPrice));	
	}
	
	@Test
	public void successfullyAddItemToCartFromItemDetailsPage() {
		loginPage.enterCredentialsAndSignIn(username, password);
		String firstInventoryName = inventoryPage.getInventoryNames().getFirst().getText();
		String firstInvetoryPrice = inventoryPage.getInventoryItemPrices().getFirst().getText();
		
		inventoryPage.getInventoryNames().getFirst().click();
		assertTrue(inventoryItemDetailsPage.getPageUrl().contains(baseUrl + "/inventory-item.html?"));
		assertTrue(inventoryItemDetailsPage.verifyPresenceOfItemName());
		assertTrue(inventoryItemDetailsPage.getItemName().equalsIgnoreCase(firstInventoryName));
		assertTrue(inventoryItemDetailsPage.verifyPresenceOfItemPrice());
		assertTrue(inventoryItemDetailsPage.getItemPrice().equalsIgnoreCase(firstInvetoryPrice));
		inventoryItemDetailsPage.clickAddToCart();
		assertTrue(inventoryItemDetailsPage.getShoppingCartBadgeText().equalsIgnoreCase("1"));
		inventoryItemDetailsPage.clickShoppingCartButton();
		assertTrue(cartPage.verifyPageTitleTestIsVisible());
		assertTrue(cartPage.getInventoryItemNames().getFirst().getText().equalsIgnoreCase(firstInventoryName));
		assertTrue(cartPage.getInventoryItemPrices().getFirst().getText().equalsIgnoreCase(firstInvetoryPrice));		
	}
	
	@Test
	public void successfullyAddAllItemsInInventoryToCart() {
		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		loginPage.enterCredentialsAndSignIn(username, password);
		for(WebElement addToCartButton : inventoryPage.getInventoryAddToCartButtons()) {
			wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
		}
		assertTrue(inventoryPage.getShoppingCartBadgeText().equalsIgnoreCase("6"));
		inventoryPage.clickCart();
		
		for(int i = 0; i < inventoryPage.getInventoryNames().size(); i++) {
			assertTrue(inventoryPage.getInventoryNames().get(i).getText().equalsIgnoreCase(cartPage.getInventoryItemNames().get(i).getText()));
			assertTrue(inventoryPage.getInventoryItemPrices().get(i).getText().equalsIgnoreCase(cartPage.getInventoryItemPrices().get(i).getText()));
		}	
	}
	
	@Test
	public void successfullyRemoveItemFromCart() {
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.getInventoryAddToCartButtons().getFirst().click();
		assertTrue(inventoryPage.getShoppingCartBadgeText().equalsIgnoreCase("1"));
		inventoryPage.getInventoryRemoveButtons().getFirst().click();
		assertTrue(inventoryPage.verifyAbsenceOfShoppingCartBadgeText());
		inventoryPage.clickCart();
		assertTrue(cartPage.verifyAbsenceofItemsinShoppingCart());
	}
	
	@Test
	public void removeItemFromCartInItemDetailsPage() {
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.getInventoryNames().getFirst().click();
		inventoryItemDetailsPage.clickAddToCart();
		assertTrue(inventoryItemDetailsPage.getShoppingCartBadgeText().equalsIgnoreCase("1"));
		inventoryItemDetailsPage.clickRemoveButton();
		assertTrue(inventoryItemDetailsPage.verifyAbsenceOfShoppingCartBadgeText());
		inventoryItemDetailsPage.clickShoppingCartButton();
		cartPage.verifyAbsenceofItemsinShoppingCart();
	}
	
	@Test
	public void removeAllItemsFromCart() {
		int count = 0;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		loginPage.enterCredentialsAndSignIn(username, password);
		for(WebElement addToCartButton : inventoryPage.getInventoryAddToCartButtons()) {
			wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
		}
		
		inventoryPage.clickCart();
		for(WebElement removeButton : cartPage.getRemoveButtons()) {
			wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
		}
		assertTrue(cartPage.verifyAbsenceOfShoppingCartBadgeText());
		assertTrue(cartPage.verifyAbsenceofItemsinShoppingCart());
	}
}
