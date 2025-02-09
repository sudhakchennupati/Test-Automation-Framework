package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class ShippingPage extends BrowserUtility {
	private static final By PROCEED_TO_CHECKOUT_LOCATOR = By.xpath("//button[@name='processCarrier']");
	private static final By AGREE_TERMS_CHECKBOX_LOCATOR = By.id("uniform-cgv");

	public ShippingPage(WebDriver driver) {
		super(driver);
	}
	
	public PaymentPage proceedToPaymentPage() {
		clickOn(AGREE_TERMS_CHECKBOX_LOCATOR);
		clickOn(PROCEED_TO_CHECKOUT_LOCATOR);
		
		return new PaymentPage(getDriver());
	}
	
	

}
