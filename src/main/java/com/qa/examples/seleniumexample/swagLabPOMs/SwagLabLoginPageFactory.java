package com.qa.examples.seleniumexample.swagLabPOMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SwagLabLoginPageFactory extends AbstractSwagLabPageFactory {
	static String URL = "https://www.saucedemo.com/";
	static String expectTitle = "Swag Labs";

	private WebDriver driver;
	
	private By usenameInpSelector = By.id("user-name");
	private By passwordInpSelector = By.id("password");
	private By loginBtnSelector = By.id("login-button");
	private By errorMsgSelector = By.cssSelector("h3[data-test='error']");
	
	
	public SwagLabLoginPageFactory(WebDriver driver) {
		this.driver = driver;
		
		driver.get(URL);
		
		if (!driver.getTitle().equals(expectTitle)) {
			String exceptionMsg = String.format("The Swag Lab Login Page did not load (%f)", URL);
			throw new IllegalStateException(exceptionMsg);
		}
	}
	
	
	public AbstractSwagLabPageFactory login(String username, String password) {
		driver.findElement(usenameInpSelector).sendKeys(username);
		driver.findElement(passwordInpSelector).sendKeys(password);
		driver.findElement(loginBtnSelector).click();
		if (driver.getCurrentUrl().equals(SwagLabHomePageFactory.URL)) {
			return new SwagLabHomePageFactory(driver);
		} else {
			return this;
		}
		
	}
	
	public String getErrorMessage() {
		return driver.findElement(errorMsgSelector).getText();
	}

}
