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

public class LoginAdminModuleTest {
	WebDriver driver=null;
	public void adminModule() throws IOException {
		FileUtilities propertiesUtility = new FileUtilities();
		WebDriver driver = new ChromeDriver();
		WebDriverCommonUtility driverUtility = new WebDriverCommonUtility(driver);
		// to store the url,username and password in a variable
		String url = propertiesUtility.readPropertiesData("doctor_url"),
				username = propertiesUtility.readPropertiesData("doctor_username"),
				password = propertiesUtility.readPropertiesData("doctor_password");
		// maximize browser
		driverUtility.maximizeBrowser();
		// implicity wait
		driverUtility.waitforImplicity(20);
		// access the admin module
		driverUtility.getBrowser(url);
		driverUtility.sendKeysValue(By.name("username"), username);
		driverUtility.sendKeysValue(By.name("password"), password);
		driverUtility.clickElement(By.name("submit"));
		
		driverUtility.clickElement(By.xpath("//i[@class='ti-angle-down']"));
		driverUtility.clickElement(By.xpath("//a[@href='logout.php']"));
		driverUtility.quitBrowser();
	}
	public static void main(String[] args) throws IOException {
		LoginAdminModuleTest log=new LoginAdminModuleTest();
		log.adminModule();
	}
}
