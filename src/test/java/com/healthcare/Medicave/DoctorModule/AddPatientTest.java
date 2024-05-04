package com.healthcare.Medicave.DoctorModule;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ObjectRepo.HomePage;
import com.ObjectRepo.DoctorModule.AddPatientPage;
import com.ObjectRepo.DoctorModule.DashboardPage;
import com.ObjectRepo.DoctorModule.LoginPage;
import com.genaricLibUtilities.BaseClass;
import com.genaricLibUtilities.CustomListeners;
import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class AddPatientTest extends BaseClass {
	JavaUtilities ju = new JavaUtilities();
	HomePage hm;
	LoginPage lp;
	/**
	 * this method wiil execute every @Test method in a class 
	 * it is used to login the application
	 */
	@BeforeMethod(alwaysRun = true)
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
	 * this method wiil be add the patient 
	 * after adding the patient it will store in Excel file 
	 */
	@Test(priority = 1,groups = {"regression","smoke"})
	public void addPatientTest() {
		DashboardPage dash=new DashboardPage(driver);
		AddPatientPage patient=new AddPatientPage(driver);
		dash.clickAddPatient();
//		Assert.assertEquals(false, true);
		try {
			patient.addPatient(driverUtility, excelutility, ju);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Reporter.log("Patient Added",true);
	}
	
	/**
	 * this method wiil execute every @Test method in a class 
	 * it is used to logout the application
	 */
	@AfterMethod(alwaysRun = true)
	public void logoutApllication() {
		hm.logoutPage();
		String expectedResult="Hospital Management System";
		Assert.assertEquals(driver.getTitle(), expectedResult, "Logout Failed");
		Reporter.log("Logout Successfully",true);
	}
}
