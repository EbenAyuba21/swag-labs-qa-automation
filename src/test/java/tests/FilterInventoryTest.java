package tests;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.ConfigReader;
import pages.CartPage;
import pages.InventoryItemDetailsPage;
import pages.InventoryPage;
import pages.LoginPage;

public class FilterInventoryTest extends BaseTest{
	
	
	
	LoginPage loginPage;
    InventoryPage inventoryPage;

    String username;
    String password;
    String baseUrl;

    @BeforeMethod
    public void pageSetup() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);

        username = ConfigReader.get("standard_username");
        password = ConfigReader.get("password");
        baseUrl = ConfigReader.get("baseUrl");
    }
	
	@Test
	
	public void testFilterByNameZtoA() {
		List<String> actualNames = new ArrayList<>();
		
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.selectFilter("Name (Z to A)");
		
		for (WebElement itemNames : inventoryPage.getInventoryNames()) {
			actualNames.add(itemNames.getText());
		}
		
		List<String> expectedNames = new ArrayList<>(actualNames);
		Collections.sort(expectedNames, Collections.reverseOrder());
		assertEquals(actualNames, expectedNames, "Product names should be sorted alphabetically Z-A");
	}
	
	@Test
	public void testFilterByNameAtoZ() {
		List<String> actualNames = new ArrayList<>();
		
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.selectFilter("Name (Z to A)");
		inventoryPage.selectFilter("Name (A to Z)");
		
		for (WebElement itemNames : inventoryPage.getInventoryNames()) {
			actualNames.add(itemNames.getText());
		}
		
		List<String> expectedNames = new ArrayList<>(actualNames);
		Collections.sort(expectedNames);
		assertEquals(actualNames, expectedNames, "Product names should be sorted alphabetically A-Z");
	}
	
	@Test
	public void testFilterByPriceLowToHigh() {
		List<String> actualPrices = new ArrayList<>();
		
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.selectFilter("Price (low to high)");
		
		for (WebElement itemPrices : inventoryPage.getInventoryItemPrices()) {
			actualPrices.add(itemPrices.getText());
		}
		
		List<String> copiedPrices = new ArrayList<>(actualPrices);
		
		List<Double> integerPrices = copiedPrices.stream()
			    .map(p -> Double.parseDouble(p.replace("$", "")))
			    .collect(Collectors.toList());
		
		Collections.sort(integerPrices);
		
		List<String> expectedPrices = integerPrices.stream()
			    .map(p -> String.format("$%.2f", p))
			    .collect(Collectors.toList());
		assertEquals(actualPrices, expectedPrices, "Product names should be sorted in ascending order of prices");
}
	
	@Test
	public void testFilterByPriceHighToLow() {
List<String> actualPrices = new ArrayList<>();
		
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.selectFilter("Price (high to low)");
		
		for (WebElement itemPrices : inventoryPage.getInventoryItemPrices()) {
			actualPrices.add(itemPrices.getText());
		}
		
		List<String> copiedPrices = new ArrayList<>(actualPrices);
		
		List<Double> integerPrices = copiedPrices.stream()
			    .map(p -> Double.parseDouble(p.replace("$", "")))
			    .collect(Collectors.toList());
		
		integerPrices.sort(Collections.reverseOrder());
		
		List<String> expectedPrices = integerPrices.stream()
			    .map(p -> String.format("$%.2f", p))
			    .collect(Collectors.toList());
		assertEquals(actualPrices, expectedPrices, "Product names should be sorted in descending order of prices");
	}
	
	

}
