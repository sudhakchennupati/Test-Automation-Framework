package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class ConfirmAddressPage extends BrowserUtility{
	private static final By PROCEED_TO_CHECKOUT_LOCATOR = By.xpath("//button[@name='processAddress']");

	public ConfirmAddressPage(WebDriver driver) {
		super(driver);
	}
	
	public ShippingPage proceedToShippingPage() {
		clickOn(PROCEED_TO_CHECKOUT_LOCATOR);
		
		return new ShippingPage(getDriver());
		
	}

}
