package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

import static com.utility.PropertiesUtility.*;

public final class HomePage extends BrowserUtility{
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(), 'Sign in')]"); //class variable; not instance variable because it is static
	
	
	public HomePage(Browser browserName, boolean isheadless) {
		super(browserName,isheadless); //to call the parent class constructor from child class constructor
		//goToWebsite(readProperty(QA, "URL"));	
		goToWebsite(JSONUtility.readJSON(QA).getUrl());
		
	}
	
	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(JSONUtility.readJSON(QA).getUrl());	
		maximizeWindow();
	}




	public LoginPage goToLoginPage() { //page functions - 
		logger.info("trying to click on signin link to go login page "  );
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
		
	}

}
