package seltest.tests.testscripts;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seltest.utils.common.ConfigReader;
import seltest.utils.common.DataProviderRepository;
import seltest.utils.common.TestBase;
import seltest.utils.common.excelConfigReader;
import seltest.utils.pages.LogInPage;

public class LoginTest extends TestBase{
	
	WebDriver driver;
	LogInPage loginTest;
	Logger log;
	
	@BeforeMethod
	public void setUp()
	{
		driver = getDriver();
		loginTest = new LogInPage(driver);
		log = Logger.getLogger("LoginTest");
		PropertyConfigurator.configure("./src/seltest/properties/log4j.properties");
	}
	
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="LogInData")
	public void testLogIn(String username, String password) 
	{
		try
		{
			loginTest.logIn(username, password);
			boolean isHomeTab = (driver.findElements(By.id("home_Tab")).size()!=0);
			Assert.assertTrue(isHomeTab);
			log.info("login is done thanks");
		}
		catch(Exception ex)
		{
			log.error("Test Fails");
			Assert.fail("failed test");
		}		
	}
}
