package com.genaricLibUtilities;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	public ExcelUtilites excelutility=new ExcelUtilites();
	public DataBaseUtilities database=new DataBaseUtilities();
	public FileUtilities propertiesUtility = new FileUtilities();
	public WebDriver driver;
	public static WebDriver sdriver;
	
	public WebDriverCommonUtility driverUtility;
	
	/**
	 * this method is connect to the data base
	 * @throws SQLException
	 */
	@BeforeSuite(alwaysRun = true)
	public void config_BS() throws SQLException {
//		database.connectToDB();
		Reporter.log("Connect to the datbase",true);
	}
	
	/**
	 * this method is used to lunch the browser
	 */
	@BeforeClass(alwaysRun = true)
	public void config_BC() {			
		driver=new ChromeDriver();
		driverUtility=new WebDriverCommonUtility(driver);
		sdriver=driver;
		driverUtility.maximizeBrowser();
		driverUtility.waitforImplicity(20);
		Reporter.log("Browser open SuccessFully",true);
	}
	
	/**
	 * this method is used to close the browser
	 */
	@AfterClass(alwaysRun = true)
	public void browserClose() {
		driverUtility.quitBrowser();
		Reporter.log("Browser Closed",true);
	}
	
	/**
	 * this method is used to disconnect to the database
	 * @throws SQLException
	 */
	@AfterSuite(alwaysRun = true)
	public void config_AS() throws SQLException {
//		database.closeDatabase();
		Reporter.log("Dis-Connect to the datbase",true);
	}
}
