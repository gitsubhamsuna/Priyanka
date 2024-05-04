package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
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
import org.openqa.selenium.Keys;
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

public class AddDoctorTest {
	String doctorusername = null;
	String doctorpassword = null;

	public void addDoctor() throws IOException {

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
		ArrayList<String []> al=new ArrayList<String[]>();
		for (Entry<String, String> setmap : excelutility.getHashMapData("AddDoctor", 0).entrySet()) {
			String data = setmap.getKey();
			String v=setmap.getValue();
//			al.add(new String[] {data,v});

			try {
				if (data.contains("docemail")) {
					doctorusername = ran + setmap.getValue();
					al.add(new String[] {data,doctorusername});
					driverUtility.sendKeysValue(By.name(data), doctorusername);
				} else if (data.contains("npass")) {
					
					doctorpassword = ran + setmap.getValue();
					al.add(new String[] {data,doctorpassword});
					driverUtility.sendKeysValue(By.name(data), doctorpassword);
				} else if (data.contains("docname")) {
					docname = ran + setmap.getValue();
					al.add(new String[] {data,docname});
					driverUtility.sendKeysValue(By.name(data), docname);
				} else {
					al.add(new String[] {data,ran + setmap.getValue()});
					driverUtility.sendKeysValue(By.name(data), ran + setmap.getValue());
				}
			} catch (Exception e) {
				continue;
			}
		}
		excelutility.writeExcelData(al,"Sheet2");
		driverUtility.clickElement(By.id("submit"));
		driverUtility.popupHandle("Successfully");

		// to check the doctor is added or not
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
		driverUtility.clickElement(By.xpath("//i[@class='ti-angle-down']"));
		driverUtility.clickElement(By.xpath("//a[@href='logout.php']"));
		driverUtility.quitBrowser();
	}

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		AddDoctorTest adt = new AddDoctorTest();
		adt.addDoctor();
	}
}
