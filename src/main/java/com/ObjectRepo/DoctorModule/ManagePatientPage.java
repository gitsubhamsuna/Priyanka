package com.ObjectRepo.DoctorModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ObjectRepo.HomePage;
import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class ManagePatientPage {

	@FindBy(name = "patname")
	private WebElement pName;

	@FindBy(name = "patcontact")
	private WebElement pContactNo;

	@FindBy(xpath = "//input[@value='Male']")
	private WebElement maleRadioButton;

	@FindBy(xpath = "//input[@value='Female']")
	private WebElement femaleRadioButton;

	@FindBy(name = "pataddress")
	private WebElement pAddress;

	@FindBy(name = "patage")
	private WebElement pAge;

	@FindBy(name = "medhis")
	private WebElement medicalHistory;

	@FindBy(id = "submit")
	private WebElement updateButton;
	
	@FindBy(xpath =  "//button[text()='Add Medical History']")
	private WebElement addMedicalHistoryButton;
	
	@FindBy(xpath ="//button[text()='Submit']")
	private WebElement medicalHistorySumbitButton;
	
//	@FindBy(name="submit")
//	private WebElement medicalHistoryCancelButton;
	

	public ManagePatientPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public WebElement getpName() {
		return pName;
	}

	public WebElement getpContactNo() {
		return pContactNo;
	}

	public WebElement getMaleRadioButton() {
		return maleRadioButton;
	}

	public WebElement getFemaleRadioButton() {
		return femaleRadioButton;
	}

	public WebElement getpAddress() {
		return pAddress;
	}

	public WebElement getpAge() {
		return pAge;
	}

	public WebElement getMedicalHistory() {
		return medicalHistory;
	}

	public WebElement getUpdateButton() {
		return updateButton;
	}

	public void updatePatient(WebDriver driver, ExcelUtilites excelutility)
			throws EncryptedDocumentException, IOException {
		String pName = excelutility.readExcelData("Sheet3", 0, 1);
		driver.findElement(
				By.xpath("//td[text()='" + pName + "']/following-sibling::td/a[contains(@href,'edit-patient')]/i"))
				.click();

		femaleRadioButton.click();
		updateButton.click();
	}
	
	public void addMedicalHistory(WebDriver driver, ExcelUtilites excelutility,WebDriverCommonUtility com) throws EncryptedDocumentException, IOException {
		
		String pName = excelutility.readExcelData("Sheet3", 0, 1);
		driver.findElement(
				By.xpath("//td[text()='" + pName + "']/following-sibling::td/a[contains(@href,'view-patient')]/i"))
				.click();
		com.waitforElementClickable(20, addMedicalHistoryButton);
		addMedicalHistoryButton.click();
		
		String pname = null;
		String pemail = null;
		for (Entry<String, String> setmap : excelutility.getHashMapData("AddMedicalHistory", 0).entrySet()) {
			String data = setmap.getKey();

			try {
				com.waitforPresenceofElement(10, By.name(data));
					com.sendKeysValue(By.name(data), setmap.getValue());
			} catch (Exception e) {
				continue;
			}
		}
		com.waitforElementClickable(20, medicalHistorySumbitButton);
		medicalHistorySumbitButton.click();
		
		com.popupHandle("Medicle history");
	}

}
