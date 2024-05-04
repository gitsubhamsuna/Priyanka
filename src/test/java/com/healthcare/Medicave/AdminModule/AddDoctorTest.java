package com.healthcare.Medicave.AdminModule;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ObjectRepo.HomePage;
import com.ObjectRepo.AdminModule.AddDoctorPage;
import com.ObjectRepo.AdminModule.DashboardPage;
import com.ObjectRepo.AdminModule.LoginPage;
import com.genaricLibUtilities.BaseClass;
import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;
@Listeners(com.genaricLibUtilities.CustomListeners.class)
public class AddDoctorTest extends BaseClass{
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
		String expectedResult="Admin | Dashboard";
		Assert.assertEquals(driver.getTitle(), expectedResult, "Login Failed");
		Reporter.log("Login Successfully",true);
	}
	
	
	@Test(groups = {"smoke","regression"})
	public void addDoctorTest(){
		DashboardPage dash=new DashboardPage(driver);
		AddDoctorPage adp=new AddDoctorPage(driver);
		dash.clickAddDoctorLink();
		try {
			adp.addDoctor(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Reporter.log("Doctor is Added",true);
	}
	
	@AfterMethod(alwaysRun = true)
	public void logoutApllication() {
		hm.logoutPage();
		String expectedResult="Hospital Management System";
		Assert.assertEquals(driver.getTitle(), expectedResult, "Logout Failed");
		Reporter.log("Logout Successfully",true);
		
	}
}
