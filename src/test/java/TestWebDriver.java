import static org.junit.Assert.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestWebDriver {
	
	public WebDriver driver;
	String urlRoot = "https://www.bing.com";
	
	@Before
	public void setup() {
		// Set the system property for web driver's path
		System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\Selenium\\webdrivers\\geckodriver.exe");
		
		// Instantiate web driver and go to root URL
		driver = new FirefoxDriver();
		driver.get(urlRoot);
		
		// Set timeouts for loading changes
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
	}
	
	@After
	public void closedown() {
		// Close web driver after every test to prevent memory leaks
		driver.quit();
	}

	@Test
	public void testSearchBar() throws InterruptedException {
		
		// Input and expected values
		String searchQuery = "puppies";
		List<String> expect = Arrays.asList("puppies - Search", "puppies - Bing");
		
		// Search for input query
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys(searchQuery);
		searchBar.submit();
		
		// Sleep so there is time to load the page and check title
		Thread.sleep(1000);
		assertTrue(expect.contains(driver.getTitle()));
		
	}

}
