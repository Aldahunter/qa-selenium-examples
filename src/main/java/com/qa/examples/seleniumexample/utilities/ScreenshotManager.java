package com.qa.examples.seleniumexample.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ScreenshotManager {
	
	private File currentScreenshot;
	
	public void takeScreenshot(WebDriver driver) {
		currentScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}
	
	public void takeElementScreenshot(WebDriver driver, By selector ) {
		WebElement element = driver.findElement(selector);
		currentScreenshot = element.getScreenshotAs(OutputType.FILE);
	}
	
	public void saveScreenshot(String path) throws IOException {
		File screenshotFile = new File(path);
		screenshotFile.getParentFile().mkdirs();
		currentScreenshot.renameTo(screenshotFile);
		currentScreenshot.createNewFile();
	}
	
	public void takeAndSaveScreenshot(WebDriver driver, String path) throws IOException {
		takeScreenshot(driver);
		saveScreenshot(path);
	}
	
	public void takeAndSaveElementScreenshot(WebDriver driver, By selector, String path) throws IOException {
		takeElementScreenshot(driver, selector);
		saveScreenshot(path);
	}

}
