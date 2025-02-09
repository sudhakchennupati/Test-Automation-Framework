package com.ui.tests;


import static org.testng.Assert.*;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;

@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase {

	Logger logger = LoggerUtility.getLogger(this.getClass());
	public static final  String INVALID_EMAIL_ADDRESS = "sudhakchen@gmail.com";
	public static final String INVALID_PASSWORD = "test1234";
	
	@Test(description = "verifies with the valid user is able to login into the application", groups = {"e2e", "sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
			dataProvider = "LogintestDataProvider", retryAnalyzer = com.ui.listeners.MyretryAnalyzer.class)
	public void loginTest(User user) {
		
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Sudha kiran");	
		
	}
	
	@Test(description = "verifies with the Invalid user get the error message", groups = {"e2e", "sanity", "smoke"})
	public void LoginInvalidCredentialsTest() {
		
		assertEquals(homePage.goToLoginPage().doLoginWithInvalidCredentials(INVALID_EMAIL_ADDRESS, INVALID_PASSWORD).getErrorMessage(), "Authentication failed.");	
		
	}

	@Test(description = "verifies with the valid user is able to login into the application", groups = {"e2e", "sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
			dataProvider = "loginCSVDataProvider")
	public void loginCSVTest(User user) {
		
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Sudha kiran");	
	}

}
