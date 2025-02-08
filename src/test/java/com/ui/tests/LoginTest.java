package com.ui.tests;

import static com.constants.Browser.*;

import static org.testng.Assert.*;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.apache.logging.log4j.Logger;
@Listeners({com.ui.listeners.TestListener.class})
public class LoginTest extends TestBase {
	
	/*
	 * 1:Test script should be small
	 * 2: you cannot have conditional statements, loops, try catch in your test method
	 * testScripts - should only follow test steps
	 * 3: No local variables - reduce the use of local variables
	 * 4: have at least one assertion
	 */
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	@Test(description = "verifies with the valid user is able to login into the application", groups = {"e2e", "sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
			dataProvider = "LogintestDataProvider", retryAnalyzer = com.ui.listeners.MyretryAnalyzer.class)
	public void loginTest(User user) {
		
		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Sudha kiran");	
		
	}
	
//	@Test(description = "verifies with the valid user is able to login into the application", groups = {"e2e", "sanity"},dataProviderClass = com.ui.dataproviders.LoginDataProvider.class,
//			dataProvider = "loginCSVDataProvider")
//	public void loginCSVTest(User user) {
//		
//		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Sudha kiran");	
//	}

}
