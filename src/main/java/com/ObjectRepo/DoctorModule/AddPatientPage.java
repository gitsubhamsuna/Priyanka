package com.ObjectRepo.DoctorModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class AddPatientPage extends WebDriverCommonUtility {
	// initialization
	@FindBy(name = "patname")
	private WebElement pName;

	@FindBy(name = "patcontact")
	private WebElement pContactNo;

	@FindBy(id = "patemail")
	private WebElement pEmail;

	@FindBy(name = "pataddress")
	private WebElement pAddress;

	@FindBy(name = "patage")
	private WebElement pAge;

	@FindBy(name = "medhis")
	private WebElement pMedicalHistory;

	@FindBy(xpath = "//input[@id='rg-male']/following-sibling::label[contains(text(),'Male')]")
	private WebElement maleRadioButton;

	@FindBy(xpath = "//input[@id='rg-female']/following-sibling::label[contains(text(),'Female')]")
	private WebElement femaleRadioButton;

	@FindBy(id = "submit")
	private WebElement addButton;

	// declaration
	public AddPatientPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// utilization
	public void getpName(String patientName) {
		pName.sendKeys(patientName);
	}

	public void getpContactNo(String patientContactNo) {
		 pContactNo.sendKeys(patientContactNo);;
	}

	public void getpEmail(String email) {
		 pEmail.sendKeys(email);
	}

	public void getpAddress(String address) {
		pAddress.sendKeys(address);;
	}

	public void getpAge(String age) {
		 pAge.sendKeys(age);;
	}

	public void getpMedicalHistory(String medicalHistory) {
		 pMedicalHistory.sendKeys(medicalHistory);;
	}

	public void getMaleRadioButton() {
		maleRadioButton.click();
		;
	}

	public void getFemaleRadioButton() {
		femaleRadioButton.click();
	}

	public void getAddButton() {
		addButton.click();
		;
	}
	
	public void addPatient(String patienName,String patientContactNo,String email,String address,String age,String medicalHistory) {
		getpName(patienName);
		getpContactNo(patientContactNo);
		getpEmail(email);
		getpAddress(address);
		getpAge(age);
		getpMedicalHistory(medicalHistory);
		getFemaleRadioButton();
		getAddButton();
	}

	public void addPatient(WebDriverCommonUtility driverUtility, ExcelUtilites excelutility, JavaUtilities ju)
			throws EncryptedDocumentException, IOException {

		String pname = null;
		String pemail = null;
		ArrayList<String []> al=new ArrayList<String[]>();
		for (Entry<String, String> setmap : excelutility.getHashMapData("AddPatient", 0).entrySet()) {
			String data = setmap.getKey();
			WebElement ele = driverUtility.driverFindElement(By.name(data));
			driverUtility.waitforElementClickable(20, ele);
			try {
				if (data.contains("patname")) {
					pname = ju.getRandom() + setmap.getValue();
					al.add(new String[] { data, pname });
					driverUtility.sendKeysValue(By.name(data), pname);
				} else if (data.contains("patemail")) {
					pemail = ju.getRandom() + setmap.getValue();
					driverUtility.sendKeysValue(By.name(data), pemail);
				} else {
					driverUtility.sendKeysValue(By.name(data), setmap.getValue());
				}
			} catch (Exception e) {
				continue;
			}
		}
		excelutility.writeExcelData(al,"Sheet3");
		getMaleRadioButton();
		getAddButton();
	}

}
