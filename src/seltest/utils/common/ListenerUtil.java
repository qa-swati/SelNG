package seltest.utils.common;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.poifs.property.Child;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.configuration.Config;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ListenerUtil extends TestBase implements ITestListener
{	
	private static ExtentReports extent = createInstance(System.getProperty("user.dir")+"./src/seltest/logs/extent.html");
	private static ExtentHtmlReporter htmlReporter;
	private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();
    public static ExtentTest childTest; 
    
	@Override
	public void onTestStart(ITestResult result) {
		childTest = ((ExtentTest) parentTest.get()).createNode(result.getMethod().getMethodName());
        test.set(childTest);
        childTest.info("Test "+result.getName()+" starts");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		((ExtentTest) test.get()).pass("Test passed : "+result.getName()+" "+result.getEndMillis());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		getScreenshot(result.getName());
		try {
			((ExtentTest) test.get()).fail("Test failed : "+result.getName()+" "+result.getEndMillis()+" "+result.getThrowable());
			((ExtentTest) test.get()).fail("Test Fails at screen : ").addScreenCaptureFromPath("./src/seltest/screenshots/"+result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		ExtentTest parent = extent.createTest(getClass().getName());
        parentTest.set(parent);
    }

	@Override
	public void onFinish(ITestContext context) {
		childTest.info("Tests Finish");
		extent.flush();
	}
	public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName("Automation Report");
        
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        
        return extent;
    }

}
