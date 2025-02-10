package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.constants.Browser;

public abstract class BrowserUtility { // parent classes are abstract classes and you cannot create object of abstract
										// class

	// private WebDriver driver;
	// to use webdriver in thread safety declare it as Thread local
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private WebDriverWait wait;
	
	public WebDriver getDriver() {
		return driver.get();
	}

	public BrowserUtility(WebDriver driver) {
		super();
		// BrowserUtility.driver.set(driver); //Initialize the instance variable driver
		this.driver.set(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30L));
	}

	public BrowserUtility(String browserName) {
		logger.info("Launching the browser " + browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			driver.set(new FirefoxDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else {
			System.err.println("Invalid browser name" + browserName);
		}
	}
	

	public BrowserUtility(Browser browserName) {
		logger.info("Launching the browser " + browserName);

		if (browserName == Browser.CHROME) {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.EDGE) {
			driver.set(new EdgeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
		} else if (browserName == Browser.FIREFOX) {
			driver.set(new FirefoxDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
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
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
			driver.set(new ChromeDriver());
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		} else if ( browserName == Browser.EDGE) {
			if(isHeadless) {
				EdgeOptions options = new EdgeOptions();
				options.addArguments("--headless=old");
				options.addArguments("--diable-gpu");
				driver.set(new EdgeDriver(options));
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
				driver.set(new EdgeDriver()); 	
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
			
		} else if (browserName == Browser.FIREFOX) {
			if(isHeadless) {
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless=old");
				driver.set(new FirefoxDriver(options)); 
				wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			} else {
			driver.set(new FirefoxDriver()); 
			wait = new WebDriverWait(driver.get(), Duration.ofSeconds(30L));
			}
		}
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
	
	public void enterSpecialKey(By locator, Keys keysToEnter) {
		logger.info("Find teh Element" + locator);
		WebElement webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		logger.info("entering the key" + keysToEnter);
		webElement.sendKeys(keysToEnter);
		
	}

	public void clickOn(By locator) {
		logger.info("Find teh Element" + locator);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		logger.info("Click on locator" + locator);
		element.click();
	}
	public void clickOn(WebElement element) {
		logger.info("Click on locator" + element);
		element.click();
	}

	public void enterText(By locator, String textToEnter) {
		logger.info("Find teh Element" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		//WebElement element = driver.get().findElement(locator);
		logger.info("Entering the value" + textToEnter);
		element.sendKeys(textToEnter);
	}
	
	public void clearText(By locator) {
		logger.info("Find teh Element" + locator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		//WebElement element = driver.get().findElement(locator);
		logger.info("clear the value" );
		element.clear();
	}

	public String getVisibleText(By locator) {
		logger.info("Find teh Element" + locator);
		WebElement element = driver.get().findElement(locator);
		logger.info("Find the visible text on locator" + locator);
		return element.getText();

	}
	
	public String getVisibleText(WebElement element) {
		logger.info("Find the visible text on locator" + element);
		return element.getText();

	}
	public List<String> getAllVisibleText(By locator) {
		logger.info("Find all the Elemenst" + locator);
		List<WebElement> allElements = driver.get().findElements(locator);
		List<String> textOnAllElements = new ArrayList<String>();
		for (WebElement element : allElements) {
			logger.info("Find the visible text on locator" + element);
			System.out.println(getVisibleText(element));
			 textOnAllElements.add(getVisibleText(element));	
		}
		return textOnAllElements;

	}
	public List<WebElement> getAllLinks(By locator) {
		logger.info("Find all the Elemenst" + locator);
		List<WebElement> allElements = driver.get().findElements(locator);

		return allElements;

	}
	public void selectFromDropDown(By DropDownLocator, String optionToSelect) {
		logger.info("Find the dropdown Elemenst" + DropDownLocator);
		WebElement element = driver.get().findElement(DropDownLocator);
		Select select = new Select(element);
		logger.info("Selecting the option" + optionToSelect);
		select.selectByVisibleText(optionToSelect);
		
	}
	
	public void selectFromDropDownByIndex(By dropDownLocator, int optionToSelect) {
		logger.info("Find the dropdown Elemenst" + dropDownLocator);
		WebElement element = driver.get().findElement(dropDownLocator);
		Select select = new Select(element);
		logger.info("Selecting the option by Index" + optionToSelect);
		select.selectByIndex(optionToSelect);
		
	}
	
	public void clickOnCheckbox(By checkboxLocator) {
		logger.info("Find the checkbox Element" + checkboxLocator);
		//WebElement element = driver.get().findElement(checkboxLocator);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxLocator));
		logger.info("Found the checkbox Element, now selecting" + checkboxLocator);
		element.click();
		
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenshot = (TakesScreenshot) driver.get();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timestamp = format.format(date);
		File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
		String path = "./screenshots/" + name + " - " + timestamp + ".png";
		File screenshotFile = new File(path);
		try {
			FileUtils.copyFile(screenshotData, screenshotFile);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

}
