package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.ConfigReader;
import pages.InventoryPage;
import pages.LoginPage;

public class LoginTest extends BaseTest{
	
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
	public void testSuccessfulLogin() {
		assertTrue(loginPage.verifyLoginPageLogoIsVisbile());
		assertTrue(loginPage.getPageUrl().equalsIgnoreCase(baseUrl + '/'));
		System.out.println(loginPage.getPageUrl());
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		assertTrue(inventoryPage.getPageUrl().equalsIgnoreCase(baseUrl + "/inventory.html"));
		assertTrue(inventoryPage.verifyPresenceOfProductsHeaderText());
	}
	
	@Test
	public void testLoginWithIncorrectUsername() {
		loginPage.enterCredentialsAndSignIn("incorrectUser", password);
		assertTrue(loginPage.verifyIncorrectCredentialsErrorMessageIsVisible());
	}
	
	@Test
	public void testLoginWithIncorrectPassword() {
		loginPage.enterCredentialsAndSignIn(username, "incorrectPassword");
		assertTrue(loginPage.verifyIncorrectCredentialsErrorMessageIsVisible());
	}
	
	@Test
	public void testLoginWithMissingUsername() {
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		assertTrue(loginPage.verifyusernameIsRequiredErrorMessageisVisible());
	}
	
	@Test
	public void testLoginWithMissingPassword() {
		loginPage.enterUsername(username);
		loginPage.clickLoginButton();
		assertTrue(loginPage.verifyPasswordIsRequiredErrorMessageisVisible());
	}
	
	
}
