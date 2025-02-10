package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import com.utility.BrowserUtility;

public class PaymentPage extends BrowserUtility {
	private static final By PAY_BY_BANK_LOCATOR = By.className("bankwire");
	private static final By CONFIRM_ORDER_LOCATOR = By.xpath("//p[@id='cart_navigation']/button");
	private static final By SUCCESS_MESSAGE_LOCATOR = By.xpath("//p[contains(@class,'success')]");
	
	public PaymentPage(WebDriver driver) {
		super(driver);
	
	}
	
	public String payByBankWire() {
		clickOn(PAY_BY_BANK_LOCATOR);
		clickOn(CONFIRM_ORDER_LOCATOR);
		return getVisibleText(SUCCESS_MESSAGE_LOCATOR);
	}
	

}
