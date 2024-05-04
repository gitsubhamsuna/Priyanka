package com.healthcare.Medicave.DoctorModule;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ObjectRepo.HomePage;
import com.ObjectRepo.DoctorModule.DashboardPage;
import com.ObjectRepo.DoctorModule.LoginPage;
import com.ObjectRepo.DoctorModule.ManagePatientPage;
import com.genaricLibUtilities.BaseClass;
import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class ManagePatientTest extends BaseClass{
	HomePage hm;

	@BeforeMethod
	public void loginApplication() {
		hm = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);
		String url = null, username = null, password = null;
		try {
			url = propertiesUtility.readPropertiesData("home_url");
			username = excelutility.readData("Sheet2", "docemail");
			password = excelutility.readData("Sheet2", "cfpass");
		} catch (IOException e) {
			e.toString();
		}
		driverUtility.getBrowser(url);
		hm.openDoctorLink();
		lp.loginApplication(username, password);
		String expectedResult="Doctor | Dashboard";
		Assert.assertEquals(driver.getTitle(), expectedResult, "Login Failed");
		Reporter.log("Login Successfully",true);
	}
	
	/**
	 * this method is used to update the patient details 
	 */
//	@Test(priority = 2,groups = "regression" , dependsOnMethods = "addPatientTest")
	@Test(priority = 2,groups = "regression")
	public void updatePatientTest(){
		DashboardPage dash=new DashboardPage(driver);
		ManagePatientPage update=new ManagePatientPage(driver);
		dash.clickManagePatient();
//		Assert.assertEquals(false, true);
		try {
			update.updatePatient(driver, excelutility);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		Reporter.log("Update the Patient details",true);
	}
	
	/**
	 * this method is used to add the medical report which are created by doctor
	 */
//	@Test(priority = 3, groups = {"regression","smoke"}, dependsOnMethods = "updatePatientTest")
	@Test(priority = 3, groups = {"regression","smoke"})
	public void createPatientMedicalReport() {
		DashboardPage dash=new DashboardPage(driver);
		ManagePatientPage update=new ManagePatientPage(driver);
		dash.clickManagePatient();
//		Assert.assertEquals(false, true);
		try {
			update.addMedicalHistory(driver, excelutility, driverUtility);
		} catch (EncryptedDocumentException | IOException e) {
			e.printStackTrace();
		}
		Reporter.log("Medical Report is Added",true);
	}
	@AfterMethod
	public void logoutApllication() {
		hm.logoutPage();
		String expectedResult="Hospital Management System";
		Assert.assertEquals(driver.getTitle(), expectedResult, "Logout Failed");
		Reporter.log("Logout Successfully",true);
	}
}
