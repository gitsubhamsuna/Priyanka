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
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class SystemTest {

	WebDriver driver;
	JavaUtilities ju;
	FileUtilities propertiesUtility;
	ExcelUtilites excelutility;
	WebDriverCommonUtility driverUtility;
	String doctorusername;
	String doctorpassword;
	int ran;

	public static void main(String[] args) throws Throwable {
		SystemTest st = new SystemTest();
		st.addDoctor();
		Thread.sleep(5000);
		st.addPatient();
	}

	public void addDoctor() throws Exception {
		ju = new JavaUtilities();
		propertiesUtility = new FileUtilities();
		excelutility = new ExcelUtilites();
		driver = new ChromeDriver();
		driverUtility = new WebDriverCommonUtility(driver);

		ran = ju.getRandom();
		// to store the url,username and password in a variable
		String url = propertiesUtility.readPropertiesData("admin_url"),
				username = propertiesUtility.readPropertiesData("admin_username"),
				password = propertiesUtility.readPropertiesData("admin_password");
		// maximize browser
		driverUtility.maximizeBrowser();
		// implicity wait
		driverUtility.waitforImplicity(20);
		// access the admin module
		driverUtility.getBrowser(url);

		// login the admin module
		driver.get(url);
		driverUtility.sendKeysValue(By.name("username"), username);
		driverUtility.sendKeysValue(By.name("password"), password);
		driverUtility.clickElement(By.name("submit"));

		// add the doctor specialization
		WebElement doctorDropdown = driver.findElement(By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		driverUtility.clickOperation(doctorDropdown);
		WebElement doctorSpecialization = driver
				.findElement(By.xpath("//span[contains(text(),'Doctor Specialization')]"));
		driverUtility.clickOperation(doctorSpecialization);

		String doctorSpec = ran + propertiesUtility.readPropertiesData("DoctorSpecialization");
		driverUtility.sendKeysValue(By.name("doctorspecilization"), doctorSpec);
		driverUtility.clickElement(By.name("submit"));

		WebElement confDocSpeci = driver.findElement(By.xpath("//div[@class='panel-body']/p"));
		if (confDocSpeci.getText().contains("successfully")) {
			System.out.println("Doctor Specialization added successfully !!");
		} else {
			System.out.println("Doctor Specialization not added!!");
		}

		driverUtility.waitforPresenceofElement(20, By.xpath("//span[contains(text(),'Doctors')] /parent::div"));

		WebElement doct = driver.findElement(By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		driverUtility.clickOperation(doct);

		// add doctor
		driverUtility.clickElement(By.xpath("//span[contains(text(),'Add Doctor')]/parent::a"));

		WebElement selectspli = driver.findElement(By.name("Doctorspecialization"));

		String docname = null; // empty variable to store the doctorname after doctor is created
		driverUtility.waitforPresenceofElement(30, By.name("Doctorspecialization"));
		driverUtility.selectDropdown(selectspli, doctorSpec);
		HashMap<String ,String > hash=new HashMap<String, String>();
		int row=0,cell=0;
		for (Entry<String, String> setmap : excelutility.getHashMapData("AddDoctor", 0).entrySet()) {
			String key = setmap.getKey();
			String value=setmap.getValue();
			hash.put(key, value);
			
			try {
				if (key.contains("docemail")) {
					doctorusername = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(key), doctorusername);
				} else if (key.contains("npass")) {
					doctorpassword = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(key), doctorpassword);
				} else if (key.contains("docname")) {
					docname = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(key), docname);
				} else {
					driverUtility.sendKeysValue(By.name(key), ran + setmap.getValue());
				}
			} catch (Exception e) {
				continue;
			}
		}
		
		driverUtility.clickElement(By.id("submit"));
		driverUtility.popupHandle("Successfully");

		boolean flag = false;
		List<WebElement> alldoc = driver.findElements(By.xpath("//tbody/tr/td[3]"));
		for (WebElement e : alldoc) {
			String text = e.getText();
			if (text.contains(docname)) {
				System.out.println("Doctor is added successfully");
				flag = true;
				break;
			}
		}
		if (flag == false) {
			System.out.println("Doctor is not added successfully");
		}

		System.out.println(doctorusername + " : " + doctorpassword);
	}

	public void addPatient() throws Exception {
		// to store the url,username and password in a variable
		String url = propertiesUtility.readPropertiesData("doctor_url");
		// maximize browser
//		driverUtility.maximizeBrowser();
		// implicity wait
		driverUtility.waitforImplicity(20);
		// access the doctor module
		driverUtility.getBrowser(url);
		driverUtility.sendKeysValue(By.name("username"), doctorusername);
		driverUtility.sendKeysValue(By.name("password"), doctorpassword);
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
}
