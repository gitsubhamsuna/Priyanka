package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class LoginDoctorModuleTest {

	WebDriver driver = null;
	FileInputStream fis1 = null;
	FileInputStream fis2 = null;
	Properties pr = new Properties();

	public void doctorModule() throws IOException {
		JavaUtilities ju = new JavaUtilities();
		FileUtilities propertiesUtility = new FileUtilities();
		ExcelUtilites excelutility = new ExcelUtilites();
		WebDriver driver = new ChromeDriver();
		WebDriverCommonUtility driverUtility = new WebDriverCommonUtility(driver);
		int ran = ju.getRandom();
		// to store the url,username and password in a variable
		String url = propertiesUtility.readPropertiesData("doctor_url"),
				username = propertiesUtility.readPropertiesData("doctor_username"),
				password = propertiesUtility.readPropertiesData("doctor_password");
		// maximize browser
		driverUtility.maximizeBrowser();
		// implicity wait
		driverUtility.waitforImplicity(20);
		// access the doctor module
		driverUtility.getBrowser(url);
		driverUtility.sendKeysValue(By.name("username"), username);
		driverUtility.sendKeysValue(By.name("password"), password);
		driverUtility.clickElement(By.name("submit"));
		
		driverUtility.clickElement(By.xpath("//i[@class='ti-angle-down']"));
		driverUtility.clickElement(By.xpath("//a[@href='logout.php']"));
		driverUtility.quitBrowser();
		
//		try {
//			fis2 = new FileInputStream("./src/test/resources/adminlogindetails.properties");
//			pr.load(fis2);
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		String url = pr.getProperty("url"), username = pr.getProperty("username"),
//				password = pr.getProperty("password");
//
//		driver = new ChromeDriver();
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//		// access the admin module
//		driver.get(pr.getProperty("url"));
//		driver.findElement(By.name("username")).sendKeys(username);
//		driver.findElement(By.name("password")).sendKeys(password);
//		driver.findElement(By.name("submit")).click();
//		
//		driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
//		driver.findElement(By.xpath("//a[@href='logout.php']")).click();
	}

	public static void main(String[] args) throws IOException {
		LoginDoctorModuleTest lg=new LoginDoctorModuleTest();
		lg.doctorModule();
	}
}
