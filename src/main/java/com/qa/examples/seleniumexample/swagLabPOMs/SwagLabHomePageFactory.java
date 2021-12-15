package com.qa.examples.seleniumexample.swagLabPOMs;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SwagLabHomePageFactory extends AbstractSwagLabPageFactory {
	static String URL = "https://www.saucedemo.com/inventory.html";
	static String expectTitle = "Swag Labs";

	private WebDriver driver;

	private By inventoryItemDivSelector = By.className("inventory_item");
	
	
	public SwagLabHomePageFactory(WebDriver driver) {
		this.driver = driver;
		
		driver.get(URL);

		if (!driver.getTitle().equals(expectTitle)) {
			String exceptionMsg = String.format("The Swag Lab Home Page did not load (%f)", URL);
			throw new IllegalStateException(exceptionMsg);
		}
	}
	
	public List<WebElement> getInventoryItems() {
		return driver.findElements(inventoryItemDivSelector);
	}
}
