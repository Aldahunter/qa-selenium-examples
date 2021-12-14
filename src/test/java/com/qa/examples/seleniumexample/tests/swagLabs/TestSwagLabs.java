package com.qa.examples.seleniumexample.tests.swagLabs;

import static org.junit.Assert.*;

import java.time.Duration;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSwagLabs {
	
	String urlRoot = "https://www.saucedemo.com/";
	String webDriverPath = "C:\\Program Files\\Selenium\\webdrivers\\geckodriver.exe";
	WebDriver driver;
	 

	@Before
	public void setUp() {
		// Set the system property for web driver's path
		System.setProperty("webdriver.gecko.driver", webDriverPath);
		
		// Instantiate web driver and go to root URL
		driver = new FirefoxDriver();
		driver.get(urlRoot);
		
		// Set timeouts for loading changes
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}

	@After
	public void tearDown() {
		// Close web driver after every test to prevent memory leaks
		driver.quit();
	}

	@Test
	public void testSuccessfulLogin() {
		
		// Input and expected values
		String username = "standard_user";
		String password = "secret_sauce";
		
		// Get page elements
		WebElement usernameInput = driver.findElement(By.cssSelector("#user-name"));
		WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
		WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
		
		// Login to page
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
		
		// Check we have logged in
		List<WebElement> inventoryItems = driver.findElements(By.className("inventory_item"));
		assertTrue(inventoryItems.size() > 0);
	}

}
