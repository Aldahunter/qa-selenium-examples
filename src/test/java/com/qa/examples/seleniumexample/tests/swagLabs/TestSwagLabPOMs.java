package com.qa.examples.seleniumexample.tests.swagLabs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.examples.seleniumexample.swagLabPOMs.SwagLabLoginPageFactory;
import com.qa.examples.seleniumexample.utilities.ScreenshotManager;

public class TestSwagLabPOMs {

	private WebDriver driver;
	String webDriverPath = "C:\\Program Files\\Selenium\\webdrivers\\geckodriver.exe";
	private ScreenshotManager screenshotManager;
	String targetScreenshotDir = ".\\target\\test-screenshots\\swagLabPOMs\\";
	
	private SwagLabLoginPageFactory swagLabLoginPageFactory;

	@Before
	public void setUp() throws Exception {
		// Set the system property for web driver's path
		System.setProperty("webdriver.gecko.driver", webDriverPath);
		
		// Instantiate web driver and go to root URL
		driver = new FirefoxDriver();
		
		// Set timeouts for loading changes
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		// Instantiate screenshot manager
		screenshotManager = new ScreenshotManager();
		
		// Instantiate the login page object model
		swagLabLoginPageFactory = new SwagLabLoginPageFactory(driver);
	}

	@After
	public void tearDown() throws Exception {
		// Close web driver after every test to prevent memory leaks
		driver.quit();
	}

	@Test
	public void swagLabLoginTest() {
		fail("Not yet implemented");
	}

}
