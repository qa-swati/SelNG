package seltest.utils.common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.rules.Timeout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class TestBase {
	
	public static WebDriver driver;
	ConfigReader config;
	
	public TestBase()
	{
		config = new ConfigReader();
	}
	
	public WebDriver getDriver()
	{
		return driver;
	}
	
	public void setDriver(String browserType)
	{
		if(browserType.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", config.readChromePath());
			driver = new ChromeDriver();
		}
		//else
		//{
			//driver = new FirefoxDriver();
		//}
		driver.manage().window().maximize();
	}
	
	public void setAppUrl(String url)
	{
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
	}
	
	public void getScreenshot(String screenshotName)
	{
		try
		{
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("./src/seltest/screenshots/"+screenshotName+".png"));
		}
		catch(Exception ex)
		{
			System.out.println("Exception occurs"+ex.getMessage());
		}
	}
	
	@BeforeMethod
	public void initializeSetUp()
	{
		setDriver(config.readBrowserType());
		setAppUrl(config.readAppUrl());
	}
	
	@AfterMethod
	public void quitSetup()
	{
		driver.quit();
	}
}
