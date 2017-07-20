package seltest.tests.testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import seltest.utils.common.ConfigReader;
import seltest.utils.common.DataProviderRepository;
import seltest.utils.common.ListenerUtil;
import seltest.utils.common.TestBase;
import seltest.utils.common.excelConfigReader;
import seltest.utils.pages.LogInPage;

public class LoginTest extends TestBase{
	
	WebDriver driver;
	LogInPage loginTest;
	
	@BeforeMethod
	public void setUp()
	{
		driver = getDriver();
		loginTest = new LogInPage(driver);	
		
	}
	
	
	@Test(dataProviderClass=DataProviderRepository.class,dataProvider="LogInData")
	public void testLogIn(String username, String password) 
	{
			ListenerUtil.childTest.info("Testing login functionality");
			loginTest.logIn(username, password);
			boolean isHomeTab = (driver.findElements(By.id("home_Tab")).size()!=0);
			Assert.assertTrue(isHomeTab);
	}
	
	/*@AfterMethod
	public void tearDown()
	{		
		driver.quit();
	}*/
	
	
}
