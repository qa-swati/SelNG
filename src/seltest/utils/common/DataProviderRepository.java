package seltest.utils.common;

import org.testng.annotations.DataProvider;

public class DataProviderRepository {
	
	ConfigReader readconfig;
	
	public DataProviderRepository()
	{
		readconfig = new ConfigReader();
	}
	
	@DataProvider(name="LogInData")
	public Object[][] dataProvider()
	{
		Object[][] data = new Object[3][2];
		excelConfigReader exc = new excelConfigReader(readconfig.readExcelpath());
		int totalrows = exc.getTotalRows(0);
		for (int row=0; row<=totalrows; row++)
		{
			data[row][0] = exc.getData(0, row, 0);
			data[row][1] = exc.getData(0, row, 1);
		}
		return data;  
	 }

}
