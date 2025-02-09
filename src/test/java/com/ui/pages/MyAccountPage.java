package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class MyAccountPage extends BrowserUtility {
	private static final By USER_NAME_LOCATOR = By.xpath("//a[@ title = \"View my customer account\"]/span");
	public static final By SEARCH_BOX_LOCATOR = By.id("search_query_top");
	public static final By ADD_NEW_ADDRESS_LINK_LOCATOR = By.xpath("//a[@title='Add my first address']");
	
	
	

	public MyAccountPage(WebDriver driver) {
		super(driver);
		
	}
	
	public String getUserName() {
		return getVisibleText(USER_NAME_LOCATOR);
	}
	
	public SearchResultsPage searchForProduct(String searchText) {
		enterText(SEARCH_BOX_LOCATOR, searchText);
		enterSpecialKey(SEARCH_BOX_LOCATOR, Keys.ENTER);
		
		SearchResultsPage searchResultPage = new SearchResultsPage(getDriver());
		return searchResultPage;	
	}
	
	public AddressPage goToAddressPage() {
		clickOn(ADD_NEW_ADDRESS_LINK_LOCATOR);
		AddressPage addressPage = new AddressPage(getDriver());
		return addressPage;
	}



}
