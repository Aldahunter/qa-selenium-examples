package com.qa.examples.seleniumexample.utilities;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotManager {
	
	private File currentScreenshot;
	
	public void takeScreenshot(WebDriver driver) {
		currentScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	}
	
	public void saveScreenshot(String path) throws IOException {
		currentScreenshot.renameTo(new File(path));
		currentScreenshot.createNewFile();
	}
	
	public void takeAndSaveScreenshot(WebDriver driver, String path) throws IOException {
		takeScreenshot(driver);
		saveScreenshot(path);
	}	

}
