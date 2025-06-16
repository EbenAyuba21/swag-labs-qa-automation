package tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import base.ConfigReader;
import pages.CartPage;
import pages.CompleteCheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.OverviewCheckoutPage;
import pages.YourInformationCheckoutPage;

public class CheckoutTest extends BaseTest{
	
	LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    YourInformationCheckoutPage yourInformationCheckoutPage;
    OverviewCheckoutPage overviewCheckoutPage;
    CompleteCheckoutPage completeCheckoutPage;

    String username;
    String password;
    String baseUrl;

    @BeforeMethod
    public void pageSetup() {
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        yourInformationCheckoutPage = new YourInformationCheckoutPage(driver);
        overviewCheckoutPage = new OverviewCheckoutPage(driver);
        completeCheckoutPage = new CompleteCheckoutPage(driver);

        username = ConfigReader.get("standard_username");
        password = ConfigReader.get("password");
        baseUrl = ConfigReader.get("baseUrl");
    }
	
	
	@Test
	public void SuccessfullyCheckout() {
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.getInventoryAddToCartButtons().getFirst().click();
		inventoryPage.clickCart();
		cartPage.clickCheckout();
		assertTrue(yourInformationCheckoutPage.getPageUrl().equalsIgnoreCase(baseUrl + "/checkout-step-one.html"));
		assertTrue(yourInformationCheckoutPage.verifyPresenceOfPageTitleText());
		yourInformationCheckoutPage.enterFirstname("Ebenezer");
		yourInformationCheckoutPage.enterLastName("Ayuba");
		yourInformationCheckoutPage.enterPostalOrZipCode("100232");
		yourInformationCheckoutPage.clickContinue();
		
		assertTrue(overviewCheckoutPage.getPageUrl().equals(baseUrl + "/checkout-step-two.html"));
		assertTrue(overviewCheckoutPage.verifyPresenceOfPageTitle());
		assertTrue(overviewCheckoutPage.verifyPresenceOfInventoryItemName());
		assertTrue(overviewCheckoutPage.verifyPresenceOfInventoryItemPrice());
		assertTrue(overviewCheckoutPage.verifyPresenceOfPaymentInformation());
		assertTrue(overviewCheckoutPage.verifyPresenceOfShippingInformation());
		assertTrue(overviewCheckoutPage.verifyPresenceOfSubTotalInformation());
		assertTrue(overviewCheckoutPage.verifyPresenceOfTotalPriceInformation());
		overviewCheckoutPage.clickFinishButton();
		
		assertTrue(completeCheckoutPage.getPageUrl().equalsIgnoreCase(baseUrl + "/checkout-complete.html"));
		assertTrue(completeCheckoutPage.verifyCompleteMessageHeaderIsDisplayed());
		assertTrue(completeCheckoutPage.verifyCompleteMessageTextIsDisplayed());
		assertTrue(completeCheckoutPage.verifyAbsenceOfShoppingCartBadge());
		completeCheckoutPage.clickBackHomeButton();
		assertTrue(inventoryPage.getPageUrl().equals(baseUrl + "/inventory.html"));
	}
	
	@Test
	public void checkoutWithMissingFirstnameInfo() {
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.getInventoryAddToCartButtons().getFirst().click();
		inventoryPage.clickCart();
		cartPage.clickCheckout();
		yourInformationCheckoutPage.enterLastName("Ayuba");
		yourInformationCheckoutPage.enterPostalOrZipCode("100232");
		yourInformationCheckoutPage.clickContinue();
		assertTrue(yourInformationCheckoutPage.verifyPresenceOfFirstNameRequiredErrorMessage());
	}
	
	@Test
	public void checkoutWithMissingLastnameInfo() {
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.getInventoryAddToCartButtons().getFirst().click();
		inventoryPage.clickCart();
		cartPage.clickCheckout();
		yourInformationCheckoutPage.enterFirstname("Ebenezer");
		yourInformationCheckoutPage.enterPostalOrZipCode("100232");
		yourInformationCheckoutPage.clickContinue();
		assertTrue(yourInformationCheckoutPage.verifyPresenceOfLastNameRequiredErrorMessage());
	}
	
	@Test
	public void checkoutWithMissingPostalCodeInfo() {
		loginPage.enterCredentialsAndSignIn(username, password);
		inventoryPage.getInventoryAddToCartButtons().getFirst().click();
		inventoryPage.clickCart();
		cartPage.clickCheckout();
		yourInformationCheckoutPage.enterFirstname("Ebenezer");
		yourInformationCheckoutPage.enterLastName("Ayuba");
		yourInformationCheckoutPage.clickContinue();
		assertTrue(yourInformationCheckoutPage.verifyPresenceOfPostalCodeRequiredErrorMessage());
	}
}
