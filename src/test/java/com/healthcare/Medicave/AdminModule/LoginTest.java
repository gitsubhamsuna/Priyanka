package com.healthcare.Medicave.AdminModule;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.ObjectRepo.HomePage;
import com.ObjectRepo.AdminModule.LoginPage;
import com.genaricLibUtilities.BaseClass;
import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class LoginTest {

	@Test
	public void loginApplicationTest() {
		JavaUtilities ju = new JavaUtilities();
		FileUtilities propertiesUtility = new FileUtilities();
		ExcelUtilites excelutility = new ExcelUtilites();
		WebDriver driver = new ChromeDriver();
		WebDriverCommonUtility driverUtility = new WebDriverCommonUtility(driver);
		HomePage hm = new HomePage(driver);
		LoginPage lp = new LoginPage(driver);

		String url = null, username = null, password = null;
		try {
			url = propertiesUtility.readPropertiesData("home_url");
			username = propertiesUtility.readPropertiesData("admin_username");
			password = propertiesUtility.readPropertiesData("admin_password");
		} catch (IOException e) {
			e.printStackTrace();
		}
		driverUtility.maximizeBrowser();
		driverUtility.waitforImplicity(30);
		driverUtility.getBrowser(url);
		hm.openAdminLink();
		lp.loginApplication(username, password);
		
		System.out.println("Login Succesfull");
		hm.logoutPage();
		driverUtility.quitBrowser();
	}
}
