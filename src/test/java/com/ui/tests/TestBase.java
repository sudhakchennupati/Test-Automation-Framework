package com.ui.tests;

import static com.constants.Browser.CHROME;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LambdaTestUtility;
import com.utility.LoggerUtility;

public class TestBase {

	protected HomePage homePage;
	Logger logger = LoggerUtility.getLogger(this.getClass());

	private Boolean isLambdaTest;
	

	
	@BeforeMethod(description = "run before test to launch homepage")
	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	public void setUp(
			@Optional("chrome") String browser,
			@Optional("false") boolean isLambdaTest,
			@Optional("false") boolean isHeadless,
			ITestResult result) {
		
		WebDriver lambdaDriver;
		this.isLambdaTest = isLambdaTest;

		if (isLambdaTest) {
			lambdaDriver = LambdaTestUtility.intializeLambdaTestSesssion(browser, result.getMethod().getMethodName());
			homePage = new HomePage(lambdaDriver);

		} else {
			//launch the browser in local machine
			logger.info("Loading website Homepage");
			homePage = new HomePage(Browser.valueOf("chrome".toUpperCase()), isHeadless);
	
		}

	}

	public BrowserUtility getInstance() {
		return homePage;
	}

	@AfterMethod(description = "TearDown the browser")
	public void tearDown() {
		if(isLambdaTest) {
			LambdaTestUtility.quitSession(); // close session on Lambda server	
		} else {
			homePage.quit();  //local
		}
		
	}

}
