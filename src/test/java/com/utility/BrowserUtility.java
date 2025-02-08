package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.constants.Browser;

public abstract class BrowserUtility { // parent classes are abstract classes and you cannot create object of abstract
										// class

	// private WebDriver driver;
	// to use webdriver in thread safety declare it as Thread local
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public BrowserUtility(WebDriver driver) {
		super();
		// BrowserUtility.driver.set(driver); //Initialize the instance variable driver
		this.driver.set(driver);
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching the browser " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver.set(new FirefoxDriver());
		} else {
			System.err.println("Invalid browser name" + browserName);
		}
	}
	

	public BrowserUtility(Browser browserName) {
		logger.info("Launching the browser " + browserName);

		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
		}
	}

	public BrowserUtility(Browser browserName, boolean isHeadless) {
		logger.info("Launching the browser " + browserName);
		
		if ( browserName == Browser.CHROME) {
			if(isHeadless) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=old");
			options.addArguments("--window-size=1920,1080");
				driver.set(new ChromeDriver(options));
			} else {
			driver.set(new ChromeDriver());
			}
		} else if ( browserName == Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--diable-gpu");
				driver.set(new EdgeDriver(options));
			} else {
				driver.set(new EdgeDriver()); 		
			}
			
		} else if (browserName == Browser.FIREFOX) {
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options)); 
			} else {
			driver.set(new FirefoxDriver()); 
			}
		}
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void goToWebsite(String url) {
		logger.info("Going to webpage " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maximize the window ");
		 driver.get().manage().window().maximize();
	}
	public void quit() {
		driver.get().quit();
	}

	public void clickOn(By locator) {
		logger.info("Find teh Element" + locator);
		WebElement webElement = driver.get().findElement(locator); // Find the element
		logger.info("Click on locator" + locator);
		webElement.click();
	}

	public void enterText(By locator, String valueToEnter) {
		logger.info("Find teh Element" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Entering the value" + valueToEnter);
		element.sendKeys(valueToEnter);
	}

	public String getVisibleText(By locator) {
		logger.info("Find teh Element" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Find the visible text on locator" + locator);
		return element.getText();

	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timestamp = format.format(date);
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "//screenshots//" + name + " - " + timestamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

}
