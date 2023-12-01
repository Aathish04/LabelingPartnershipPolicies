import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  
public class ReadExcelFile    
{  


	 public static boolean IsCountry(String nameToSearch) throws InvalidFormatException, IOException {
	        String fileLocation = "src/Resources/worldcities.xlsx";
	        XSSFWorkbook wb = new XSSFWorkbook(new File(fileLocation));
	        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
	            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
	            for (int rowIndex = 0; rowIndex < sheet.getLastRowNum(); rowIndex++) {
	                XSSFRow row = sheet.getRow(rowIndex);
	                if (row != null && row.getCell(4).getStringCellValue().equals(nameToSearch)) {
	                    return true;
	                }
	            }
	        }
	        return false;
	    }
	 public static String[] IsNamedarea(String nameToSearch) throws InvalidFormatException, IOException {
	     String [] data=new String[3];  
		 String fileLocation = "src/Resources/worldcities.xlsx";
	        XSSFWorkbook wb = new XSSFWorkbook(new File(fileLocation));
	        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
	            XSSFSheet sheet = wb.getSheetAt(sheetIndex);
	            for (int rowIndex = 0; rowIndex < sheet.getLastRowNum(); rowIndex++) {
	                XSSFRow row = sheet.getRow(rowIndex);
	                if (row != null && row.getCell(0).getStringCellValue().equals(nameToSearch)) {
	                	data[0]=  row.getCell(0).getStringCellValue();
	                	data[1]=  row.getCell(7).getStringCellValue();
	                	data[2]=  row.getCell(4).getStringCellValue();

	                }
	            }
	        }
	        return data;
	    }
}
