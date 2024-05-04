package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.genaricLibUtilities.IpathConstant;

public class ExcelDatGenericTest {
	
	@DataProvider
	public Object[][] readDataFromdataProvider() throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
		Workbook work=WorkbookFactory.create(fis);
		Sheet sheet=work.getSheet("Sheet2");
		
		int row = sheet.getPhysicalNumberOfRows();
		int cel = sheet.getRow(0).getPhysicalNumberOfCells();
		Object [][] obj=new Object[row][cel];
		
		for(int i=0;i<row;i++) {
			for(int j=0;j<cel;j++) {
				obj[i][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		return obj;
	}
	
	@Test(dataProvider = "readDataFromdataProvider")
	public void test(String locatorValue, String data) {
		System.out.println(locatorValue+"  ------  "+data);
	}

}
