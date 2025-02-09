package com.ui.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.BrowserUtility;

public class ProductDetailPage extends BrowserUtility{
	private WebDriverWait wait;
	
	private static final By SIZE_DROPDOWN_LOCATOR = By.id("group_1");
	private static final By ADD_TO_CART_BUTTON_LOCATOR = By.xpath("//button[@name='Submit']");
	private static final By PROCEED_TO_CHECKOUT_LOCATOR = By.xpath("//a[@title= 'Proceed to checkout']");

	public ProductDetailPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}
	
	public ProductDetailPage changeSize(char size) {
		clickOn(SIZE_DROPDOWN_LOCATOR);
		selectFromDropDown(SIZE_DROPDOWN_LOCATOR, String.valueOf(size));
		
		return new ProductDetailPage(getDriver());
		
	}
	
	public ProductDetailPage addProductToCart() {
		wait.until(ExpectedConditions.elementToBeClickable(ADD_TO_CART_BUTTON_LOCATOR));
		clickOn(ADD_TO_CART_BUTTON_LOCATOR);
		
		return new ProductDetailPage(getDriver());
	}
	
	public ShoppingCartPage proceedToCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(PROCEED_TO_CHECKOUT_LOCATOR));
		clickOn(PROCEED_TO_CHECKOUT_LOCATOR);
		
		return new ShoppingCartPage(getDriver());
	}

	
	
	

}
