package com.qa.examples.seleniumexample.tests.screenshotManager;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
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

	@Test
	public void testTakeAndSaveScreenshot() throws IOException {
		// Input and expected values
		String url = "https://www.bbc.co.uk/";
		String screenshotPath = targetScreenshotPath + "testTakeAndSaveScreenshot.png";
		String expectedTitle = "BBC - Home";
		
		// Go to the URL
		navigator.to(url);
		
		// Assert we have gone to the correct URL
		assertEquals(expectedTitle, driver.getTitle());
		
		// Take a screenShot
		screenshotManager.takeAndSaveScreenshot(driver, screenshotPath);
		
		// Assert we have created a file where the screenshot should be
		File screenshotFile = new File(screenshotPath);
		String assertErrMsg = String.format("The screenshot '%s' does not exists",
											screenshotFile.getCanonicalPath());
		assertTrue(assertErrMsg, screenshotFile.exists());
	}

}
