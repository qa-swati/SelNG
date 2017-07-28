package seltest.utils.common;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelConfigReader {
	XSSFWorkbook wb;
	XSSFSheet sheet;
	public excelConfigReader(String filepath)
	{
		try
		{
		File file = new File(filepath);
		FileInputStream fls = new FileInputStream(file);
		wb = new XSSFWorkbook(fls);
		}
		catch(Exception ex)
		{
			System.out.println("error message"+ex.getMessage());
		}
	}
	
	public String getData(int sheetno, int rownum, int colnum)
	{
		sheet = wb.getSheetAt(sheetno);
		String value =  sheet.getRow(rownum).getCell(colnum).getStringCellValue();
		return value;
	}
	
	public int getTotalRows(int sheetno)
	{
		int totalrows = wb.getSheetAt(sheetno).getLastRowNum();
		return totalrows;
	}

}
