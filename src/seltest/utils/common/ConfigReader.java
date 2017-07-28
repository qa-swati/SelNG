package seltest.utils.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	Properties prop;
	public ConfigReader(){
		try
		{
			File file = new File("./src/seltest/properties/config.property");
			FileInputStream fls = new FileInputStream(file);
			prop = new Properties();
			prop.load(fls);
		} 
		catch (Exception e) {
			System.out.println("error message"+e.getMessage());
		}
	}
	
	public String readChromePath()
	{
		String chromePath = prop.getProperty("ChromeDriver");
		return chromePath;
	}
	
	public String readAppUrl()
	{
		String url = prop.getProperty("URL");
		return url;
	}
	
	public String readExcelpath()
	{
		String excelPath = prop.getProperty("ExcelReaderPath");
		return excelPath;
	}
	
	public String readBrowserType()
	{
		String browser = prop.getProperty("BROWSER");
		return browser;
	}
}
