package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class AddPatientTest {
	public void addPatient() throws Exception {
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

		// add the patient
		WebElement patientdropdown = driver.findElement(By.xpath("//span[contains(text(),'Patients')]/parent::div"));
		driverUtility.waitforElementClickable(20, patientdropdown);

		driverUtility.clickOperation(patientdropdown);
		driverUtility.waitforElementClickable(20, patientdropdown);
		WebElement patient = driver.findElement(By.xpath("//span[contains(text(),'Add Patient')]"));
		driverUtility.waitforPresenceofElement(20, By.xpath("//span[contains(text(),'Add Patient')]"));
		driverUtility.clickOperation(patient);
		String pname = null;
		String pemail = null;
		for (Entry<String, String> setmap : excelutility.getHashMapData("AddPatient", 0).entrySet()) {
			String data = setmap.getKey();

			try {
				if (data.contains("patname")) {
					pname = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(data), pname);
				} else if (data.contains("patemail")) {
					pemail = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(data), pemail);
				} else {
					driverUtility.sendKeysValue(By.name(data), setmap.getValue());
				}
			} catch (Exception e) {
				continue;
			}
		}
		driverUtility.clickElement(By.xpath("//label[@for='rg-male']"));
		driverUtility.clickElement(By.id("submit"));

		WebElement pat = driver.findElement(By.xpath("//span[contains(text(),'Patients')]/parent::div"));
		driverUtility.mouseOverAndClick(pat);

		WebElement managePatient = driver.findElement(By.xpath("//span[contains(text(),'Manage Patient')]"));
		driverUtility.waitforElementClickable(20, managePatient);
		driverUtility.clickOperation(managePatient);

		List<WebElement> allpatientdetails = driver.findElements(By.xpath("//td[@class='hidden-xs']"));
		boolean flag = false;
		for (WebElement e : allpatientdetails) {
			Thread.sleep(2000);
			String text = e.getText();
			if (text.contains(pname)) {
				System.out.println("Patient name is Created");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("Patien name is not created");
		}
		driverUtility.clickElement(By.xpath("//i[@class='ti-angle-down']"));
		driverUtility.clickElement(By.xpath("//a[@href='logout.php']"));

		driverUtility.quitBrowser();
	}

	public static void main(String[] args) throws Exception {
		AddPatientTest apt = new AddPatientTest();
		apt.addPatient();
	}
}
