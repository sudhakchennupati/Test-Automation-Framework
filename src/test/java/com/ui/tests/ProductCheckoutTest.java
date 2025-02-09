package com.ui.tests;

import org.testng.annotations.BeforeMethod;

import com.ui.pages.SearchResultsPage;

import org.testng.Assert;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners({com.ui.listeners.TestListener.class})
public class ProductCheckoutTest extends TestBase{
	
	private static final String SEARCH_TERM = "Summer Printed Dress";
	private SearchResultsPage searchResultsPage;
	
	@BeforeMethod(description = "Valid first time user logs in to the application")
	public void setup() {
		searchResultsPage = homePage.goToLoginPage().doLoginWith("gamiyel275@maonyn.com", "password").searchForProduct(SEARCH_TERM);
		
		
	}
	
	@Test(description = "adds product to cart and go through teh checkout process", groups = {"e2", "sanity", "regression"})
	public void productCheckoutTest() {
		 String result = searchResultsPage.clickOnProduct(1).changeSize('M').addProductToCart().proceedToCheckout().goToConfirmAddressPage().
		 proceedToShippingPage().proceedToPaymentPage().payByBankWire();
		 Assert.assertTrue(result.contains("complete"));
		
	}

}
