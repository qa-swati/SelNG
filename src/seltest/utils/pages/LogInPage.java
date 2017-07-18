package seltest.utils.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogInPage {
	
	WebDriver driver;
	By userId = By.id("username");
	By passId = By.id("password");
	By loginId = By.id("Login");
	
	public LogInPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public void logIn(String username, String password)
	{
		driver.findElement(userId).sendKeys(username);
	  	driver.findElement(passId).sendKeys(password);;
	  	driver.findElement(loginId).click();
	}
}
