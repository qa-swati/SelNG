package seltest.tests.testscripts;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import seltest.utils.common.TestBase;

public class hrm extends TestBase{
	
	WebDriver driver;
	@BeforeMethod
	public void setUp()
	{
		driver = getDriver();
	}
	
	@Test
	public void hrntest()
	{
		driver.get("http://203.129.200.124/login.php");

		driver.findElement(By.name("txtUserName")).sendKeys("swati.goyal@metacube.com");
		driver.findElement(By.name("txtPassword")).sendKeys("swati#MHRM");
		driver.findElement(By.name("Submit")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.switchTo().frame("rightMenu");
		driver.findElement(By.xpath("//*[@id='btnPunch']//button"));
	}

}
