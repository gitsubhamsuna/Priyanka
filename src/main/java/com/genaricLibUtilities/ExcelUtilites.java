package com.genaricLibUtilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtilites{
	
//	private Workbook fileInput() throws EncryptedDocumentException, IOException {
//		FileInputStream fis = new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
//		Workbook workbook = WorkbookFactory.create(fis);
//		return workbook;
//	}
	FileInputStream fis;
	FileOutputStream fos;
	Workbook workbook;

	/**
	 * This method is return Single sheet and single row and single cell value
	 * 
	 * @param sheetName
	 * @param rowNo
	 * @param cellNo
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String readExcelData(String sheetName, int rowNo, int cellNo)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
		workbook = WorkbookFactory.create(fis);
		Sheet sh = workbook.getSheet(sheetName);
		String data = sh.getRow(rowNo).getCell(cellNo).getStringCellValue();
		return data;
	}

	/**
	 * This method is used to count no.of row present in Excel sheet
	 * 
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
		workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getPhysicalNumberOfRows();
		return rowCount;
	}

	/**
	 * This method is used to store all the data are there in Excel sheet in the
	 * form of key and value
	 * 
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public HashMap<String, String> getHashMapData(String sheetName, int cell)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
		workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		HashMap<String, String> map = new HashMap<String, String>();

		int rowCount = sheet.getPhysicalNumberOfRows();
		for (int i = 0; i < rowCount; i++) {
			String key = sheet.getRow(i).getCell(cell).getStringCellValue();
			String value = sheet.getRow(i).getCell(cell + 1).getStringCellValue();
			map.put(key, value);
		}
		return map;
	}

	/**
	 * This method is used for write the data in Excel in the form of String
	 * 
	 * @param sheetName
	 * @param rowCreate
	 * @param cellCreate
	 * @param value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void writeExcelData(String sheetName, int rowCreate, int cellCreate, String key)
			throws EncryptedDocumentException, IOException {
		fis = new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
		workbook = WorkbookFactory.create(fis);
		Sheet sheet = workbook.getSheet(sheetName);
		sheet.createRow(cellCreate).createCell(cellCreate).setCellValue(key);
		FileOutputStream fos = new FileOutputStream(IpathConstant.EXCEL_FILE_PATH);
		workbook.write(fos);
	}
	/**
	 * this method is store the multiple data in excel file(sheet)
	 * @param key
	 * @param value
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 * ,String key ,String value
	 */
	public void writeExcelData(ArrayList<String[]> al,String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
		Workbook workbook=WorkbookFactory.create(fis);
		Sheet sheet=workbook.getSheet(sheetName);
		int row=0;
		for(String[] str:al) {
			Row r = sheet.createRow(row++);
			int ce=0;
			for(String s:str) {
				r.createCell(ce++).setCellValue(s);
			}
		}
		FileOutputStream fos=new FileOutputStream(IpathConstant.EXCEL_FILE_PATH);
		workbook.write(fos);
	}
	
	public String readData(String sheetName,String data) throws EncryptedDocumentException, IOException {
		FileInputStream fis=new FileInputStream(IpathConstant.EXCEL_FILE_PATH);
		Workbook workbook=WorkbookFactory.create(fis);
		Sheet sheet=workbook.getSheet(sheetName);
		
		int row=sheet.getPhysicalNumberOfRows();
		int cell=sheet.getRow(0).getPhysicalNumberOfCells();
		boolean flag=false;
		String result=null;
		for(int i=0;i<row;i++) {
			for(int j=0;j<cell;j++) {
				String value=sheet.getRow(i).getCell(j).toString();
				
				if(value.contentEquals(data)) {
					result=sheet.getRow(i).getCell(j+1).toString();
					flag=true;
					break;
				}
			}
		}
		if(flag==false) {
			System.out.println("Invalid data");
		}
		return result;
	}

}
