package com.qa.examples.seleniumexample.tests.screenshotManager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.examples.seleniumexample.utilities.ScreenshotManager;

public class TestScreenshotManager {
	
	WebDriver driver;
	Navigation navigator;
	ScreenshotManager screenshotManager;
	String webDriverPath = "C:\\Program Files\\Selenium\\webdrivers\\geckodriver.exe";
	String targetScreenshotPath = ".\\target\\test-screenshots\\";
    

	@Before
	public void setUp() throws Exception {
		// Set the system property for web driver's path
		System.setProperty("webdriver.gecko.driver", webDriverPath);
		
		// Instantiate web driver and go to root URL
		driver = new FirefoxDriver();
		navigator = driver.navigate();
		
		// Instantiate screenshot manager
		screenshotManager = new ScreenshotManager();
		
		// Set timeouts for loading changes
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}

	@After
	public void tearDown() throws Exception {
		// Close web driver after every test to prevent memory leaks
		driver.quit();
	}
	
	// Helper Class for gathering screenshot saving assertion values
	private class ScreenshotSaveAssertionValues {
		public File screenshotFile;
		public String assertErrMsg;
		
		private ScreenshotSaveAssertionValues(String screenshotPath) throws IOException {
			this.screenshotFile = new File(screenshotPath);
			this.assertErrMsg = String.format("The screenshot '%s' does not exists", this.screenshotFile.getCanonicalPath());
		}
	}
	

	@Test
	public void testTakeAndSaveScreenshot() throws IOException {
		// Input URLs and Paths
		String url = "https://www.bbc.co.uk/";
		String screenshotPath = targetScreenshotPath + "testTakeAndSaveScreenshot.png";
		
		// Input and Expected Queries
		String expectedTitle = "BBC - Home";
		
		
		// Go to URL and check we are there
		navigator.to(url);
		assertEquals(expectedTitle, driver.getTitle());
		
		// Take screenshot and check there is an image where screenshot is expected
		screenshotManager.takeAndSaveScreenshot(driver, screenshotPath);
		ScreenshotSaveAssertionValues screenshotSaveAssertValues = new ScreenshotSaveAssertionValues(screenshotPath);
		assertTrue(screenshotSaveAssertValues.assertErrMsg, screenshotSaveAssertValues.screenshotFile.exists());
	}
	
	@Test
	public void testTakeAndSaveElementScreenshot() throws IOException {
		// Input URLs and Paths
		String url = "https://www.google.co.uk/";
		String cookiesScreenshotPath = targetScreenshotPath + "testTakeAndSaveElementScreenshot-noCookiesPopUp.png";
		String inputQueryScreenshotPath = targetScreenshotPath + "testTakeAndSaveElementScreenshot-searchBarInputtedQuery.png";
		
		// Input and Expected Queries
		String searchQuery = "Pigs";
		String expectedQuery = "Pigs";
		
		// Web element selectors
		By cookiesPopUpBtnSelector = By.xpath("//button/div[text()='I agree']/..");
	    By searchBarInptSelector = By.name("q");
		
	    
		// Go to URL and get required web elements
		navigator.to(url);
		WebElement cookiesPopupButton = driver.findElement(cookiesPopUpBtnSelector);
		WebElement searchBar = driver.findElement(searchBarInptSelector);
		
		// Close and check cookies pop-up is closed
		cookiesPopupButton.click();
		assertTrue("The Cookies Pop-Up is still displayed", !cookiesPopupButton.isDisplayed());
		screenshotManager.takeAndSaveScreenshot(driver, cookiesScreenshotPath);
		
		// Assert we have created a file where the screenshot should be
		ScreenshotSaveAssertionValues screenshotSaveAssertValues = new ScreenshotSaveAssertionValues(cookiesScreenshotPath);
		assertTrue(screenshotSaveAssertValues.assertErrMsg, screenshotSaveAssertValues.screenshotFile.exists());
		
		// Type query into search bar and check it was inputed
		searchBar.sendKeys(searchQuery);
		assertEquals(expectedQuery, searchBar.getAttribute("value"));
		screenshotManager.takeAndSaveElementScreenshot(driver, searchBarInptSelector, inputQueryScreenshotPath);
		
		// Assert we have created a file where the screenshot should be
		screenshotSaveAssertValues = new ScreenshotSaveAssertionValues(inputQueryScreenshotPath);
		assertTrue(screenshotSaveAssertValues.assertErrMsg, screenshotSaveAssertValues.screenshotFile.exists());
	}

}
