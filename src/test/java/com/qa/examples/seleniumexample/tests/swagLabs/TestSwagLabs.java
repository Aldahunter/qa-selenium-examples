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

	WebDriver driver;
	String webDriverPath = "C:\\Program Files\\Selenium\\webdrivers\\geckodriver.exe";
    
	String urlRoot = "https://www.saucedemo.com/";
	By usernameSelector = By.cssSelector("#user-name");
    By passwordSelector = By.cssSelector("#password");
    By loginButtonSelector = By.cssSelector("#login-button");
    By invetoryItemsSelector = By.className("inventory_item");
    By errorSelector = By.cssSelector("h3[data-test='error']");
	
	 
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
	
	// Helper function
	private void login(String username, String password) {
		// Get page elements
		WebElement usernameInput = driver.findElement(usernameSelector);
		WebElement passwordInput = driver.findElement(passwordSelector);
		WebElement loginButton = driver.findElement(loginButtonSelector);
       
		// Login to page
		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();
    }

	@Test
	public void testSuccessfulLogin() {
		
		// Input and expected values
		String username = "standard_user";
		String password = "secret_sauce";
		
		// Login to page
		login(username, password);
		
		// Check we have logged in
		List<WebElement> inventoryItems = driver.findElements(invetoryItemsSelector);
		assertTrue(inventoryItems.size() > 0);
	}
	
	@Test
	public void testLockedOutUserLogin() {
		
		// Input and expected values
		String username = "locked_out_user";
		String password = "secret_sauce";
		String expected = "Epic sadface: Sorry, this user has been locked out.";
		
		// Login to page
		login(username, password);
		
		// Check we have been locked out
		WebElement errorBox = driver.findElement(errorSelector);
		assertEquals(expected, errorBox.getText());
	}
	
	@Test
	public void testWrongPasswordLogin() {
		
		// Input and expected values
		String username = "problem_user";
		String password = "wrong";
		String expected = "Epic sadface: Username and password do not match any user in this service";
		
		// Login to page
		login(username, password);
		
		// Check we have wrong password
		WebElement errorBox = driver.findElement(errorSelector);
		assertEquals(expected, errorBox.getText());
	}

}
