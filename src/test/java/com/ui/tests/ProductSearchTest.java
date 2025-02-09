package com.ui.tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.MyAccountPage;


@Listeners({com.ui.listeners.TestListener.class})
public class ProductSearchTest extends TestBase {
	private MyAccountPage myAccountPage;
	private static final String SEARCH_TERM = "Summer Printed Dress";
	
	@BeforeMethod(description = "Valid user login to the application")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("gamiyel275@maonyn.com", "password");
		
	}
	
		
	@Test(description = "search for a product", groups = {"e2e", "regression","sanity"})
	public void searchProductPageTest() {
		String searchPageHeader = myAccountPage.searchForProduct(SEARCH_TERM).getSearchPageHeader();
		System.out.println(searchPageHeader);
		Assert.assertTrue(searchPageHeader.contains("PRINTED DRESS"));
		myAccountPage.searchForProduct(SEARCH_TERM).getAllDressesNames();
		
	}
	@Test(description = "verifies if all the product names dispalyed on search page contains search term", groups = {"e2e", "sanity"})
	public void verifySearchProductListTest() {
	
		boolean actualResult = myAccountPage.searchForProduct(SEARCH_TERM).isValidProductListDisplayed(SEARCH_TERM); 
		Assert.assertEquals(actualResult, true);
		
	}


	
	

}
