package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
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

public class UpdateDoctorDetails {
	WebDriver driver = null;
	Random r = new Random();
	String doctorusername = null;
	String doctorpassword = null;

	public void updateDoctor() throws EncryptedDocumentException, IOException {

		JavaUtilities ju = new JavaUtilities();
		FileUtilities propertiesUtility = new FileUtilities();
		ExcelUtilites excelutility = new ExcelUtilites();
		WebDriver driver = new ChromeDriver();
		WebDriverCommonUtility driverUtility = new WebDriverCommonUtility(driver);
		int ran = ju.getRandom();
		// to store the url,username and password in a variable
		String url = propertiesUtility.readPropertiesData("admin_url"),
				username = propertiesUtility.readPropertiesData("admin_username"),
				password = propertiesUtility.readPropertiesData("admin_password");
		// maximize browser
		driverUtility.maximizeBrowser();
		// implicity wait
		driverUtility.waitforImplicity(30);
		// access the admin module
		driverUtility.getBrowser(url);
		driverUtility.sendKeysValue(By.name("username"), username);
		driverUtility.sendKeysValue(By.name("password"), password);

		driverUtility.clickElement(By.name("submit"));

		WebElement doctorDropdown = driver.findElement(By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		driverUtility.clickOperation(doctorDropdown);
		
		driverUtility.clickElement(By.xpath("//span[contains(text(),'Doctor Specialization')]"));
//		driver.findElement(By.xpath("//span[contains(text(),'Doctor Specialization')]")).click();

		String doctorSpecialization = ran + propertiesUtility.readPropertiesData("DoctorSpecialization");
		driverUtility.sendKeysValue(By.name("doctorspecilization"), doctorSpecialization);
		driverUtility.clickElement(By.name("submit"));

		WebElement confDocSpeci = driver.findElement(By.xpath("//div[@class='panel-body']/p"));
		if (confDocSpeci.getText().contains("successfully")) {
			System.out.println("Doctor Specialization added successfully !!");
		} else {
			System.out.println("Doctor Specialization not added!!");
		}

		driverUtility.waitforPresenceofElement(50, By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		WebElement dropdownDoctor = driver.findElement(By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		driverUtility.clickElement(By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		
		WebElement doctorAdd = driver.findElement(By.xpath("//span[contains(text(),'Add Doctor')]/parent::a"));
		driverUtility.waitforElementClickable(50, doctorAdd);
		driverUtility.clickElement(By.xpath("//span[contains(text(),'Add Doctor')]/parent::a"));

		WebElement selectspli = driver.findElement(By.name("Doctorspecialization"));

		String docname = null;
		driverUtility.selectDropdown(selectspli, doctorSpecialization);

		for (Entry<String, String> setmap : excelutility.getHashMapData("AddDoctor", 0).entrySet()) {
			String data = setmap.getKey();

			try {
				if (data.contains("docemail")) {
					doctorusername = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(data), doctorusername);
				} else if (data.contains("npass")) {
					doctorpassword = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(data), doctorpassword);
				} else if (data.contains("docname")) {
					docname = ran + setmap.getValue();
					driverUtility.sendKeysValue(By.name(data), docname);
				} else {
					driverUtility.sendKeysValue(By.name(data), ran + setmap.getValue());
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

		driver.findElement(By.xpath(
				"//td[text()='" + docname + "']/following-sibling::td[2]//a[contains(@href,'edit-doctor.php?id')]"))
				.click();
		for (Entry<String, String> setmap : excelutility.getHashMapData("AddDoctor", 0).entrySet()) {
			String mobile = setmap.getKey();
			if (mobile.contains("docfees")) {
				int nn = r.nextInt();
				driverUtility.sendKeysValue(By.name(mobile), r + setmap.getValue());
				break;
			}
		}
		driverUtility.waitforElementClickable(30, driver.findElement(By.name("submit")));
		driverUtility.clickElement(By.name("submit"));

		WebElement updateDoctor = driver.findElement(By.cssSelector("div[class='col-md-12']>h5"));
		if (updateDoctor.getText().contains("updated Successfully")) {
			System.out.println("Doctor Details updated Successfully ");
		} else {
			System.out.println("Doctor Details not updated Successfully ");
		}
		driverUtility.clickElement(By.xpath("//i[@class='ti-angle-down']"));
		driverUtility.clickElement(By.xpath("//a[@href='logout.php']"));

		driverUtility.quitBrowser();
	}

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		UpdateDoctorDetails adt = new UpdateDoctorDetails();
		adt.updateDoctor();
	}
}
