package com.ui.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.BrowserUtility;

public class PaymentPage extends BrowserUtility {
	private static final By PAY_BY_BANK_LOCATOR = By.className("bankwire");
	private static final By CONFIRM_ORDER_LOCATOR = By.xpath("//p[@id='cart_navigation']/button");
	private static final By SUCCESS_MESSAGE_LOCATOR = By.xpath("//p[contains(@class,'success')]");
	
	private WebDriverWait wait;

	public PaymentPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	
	}
	
	public String payByBankWire() {
		clickOn(PAY_BY_BANK_LOCATOR);
		wait.until(ExpectedConditions.visibilityOfElementLocated(CONFIRM_ORDER_LOCATOR));
		clickOn(CONFIRM_ORDER_LOCATOR);
		return getVisibleText(CONFIRM_ORDER_LOCATOR);
	}
	

}
