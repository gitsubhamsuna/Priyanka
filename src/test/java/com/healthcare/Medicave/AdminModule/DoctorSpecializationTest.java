package com.healthcare.Medicave.AdminModule;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ObjectRepo.HomePage;
import com.ObjectRepo.AdminModule.AddDoctorPage;
import com.ObjectRepo.AdminModule.DashboardPage;
import com.ObjectRepo.AdminModule.DoctorSpecializationPage;
import com.ObjectRepo.AdminModule.LoginPage;
import com.genaricLibUtilities.BaseClass;
import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class DoctorSpecializationTest extends BaseClass {
	HomePage hm;
	LoginPage lp ;
	
	@BeforeMethod(alwaysRun = true)
	public void loginApplication() {
		hm = new HomePage(driver);
		lp = new LoginPage(driver);
		String url = null, username = null, password = null;
		try {
			url = propertiesUtility.readPropertiesData("home_url");
			username = propertiesUtility.readPropertiesData("admin_username");
			password = propertiesUtility.readPropertiesData("admin_password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		driverUtility.getBrowser(url);
		
		hm.openAdminLink();
		lp.loginApplication(username, password);
		Reporter.log("Login Successfully",true);
	}
		
	
	@Test(groups = {"regression"})
	public void createDoctorSpecializationTest() {
		JavaUtilities ju = new JavaUtilities();
		DashboardPage dash = new DashboardPage(driver);
		DoctorSpecializationPage adp = new DoctorSpecializationPage(driver);
		int ran = ju.getRandom();

		dash.clickAddSpecializationLink();
		String sp=null;
		try {
			sp = ran + excelutility.readExcelData("DoctSpecialization", 0, 1);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		try {
			excelutility.writeExcelData("DoctSpecialization2", 0, 0, sp);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		adp.createSpecialization(sp);
	}
	
	@AfterMethod(alwaysRun = true)
	public void logoutApllication() {
		hm.logoutPage();
		Reporter.log("Logout Successfully",true);
	}
}
