package com.ObjectRepo.AdminModule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.genaricLibUtilities.ExcelUtilites;
import com.genaricLibUtilities.FileUtilities;
import com.genaricLibUtilities.JavaUtilities;
import com.genaricLibUtilities.WebDriverCommonUtility;

public class AddDoctorPage extends WebDriverCommonUtility {

	@FindBy(name = "Doctorspecialization")
	private WebElement doctSpecializationDropdown;

	@FindBy(name = "docname")
	private WebElement doctorname;

	@FindBy(name = "clinicaddress")
	private WebElement doctClincAddress;

	@FindBy(name = "docfees")
	private WebElement doctFees;

	@FindBy(name = "doccontact")
	private WebElement doctContactNo;

	@FindBy(name = "docemail")
	private WebElement docEmail;

	@FindBy(name = "npass")
	private WebElement password;

	@FindBy(name = "cfpass")
	private WebElement confiPassword;

	@FindBy(id = "submit")
	private WebElement submitButton;
	
	@FindBy(xpath = "//tbody/tr/td[3]")
	private List<WebElement> allDoctorDetails;

	public AddDoctorPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	// business logic
	public void addDoctor(WebDriver driver) throws IOException {
		JavaUtilities ju = new JavaUtilities();
		FileUtilities propertiesUtility = new FileUtilities();
		ExcelUtilites excelutility = new ExcelUtilites();

		int ran = ju.getRandom();

//		String doctorSpec =propertiesUtility.readPropertiesData("DoctorSpecialization");
		String doctorSpec =excelutility.readExcelData("DoctSpecialization2", 0, 0);

		WebElement selectspli = driver.findElement(By.name("Doctorspecialization"));
		String docname = null; // empty variable to store the doctorname after doctor is created
		waitforPresenceofElement(30, By.name("Doctorspecialization"));
		selectDropdown(selectspli, doctorSpec);
		waitforElementClickable(30, doctSpecializationDropdown);
		
		ArrayList<String[]> al = new ArrayList<String[]>(); // empty ArrayList

		String doctorusername = null, doctorpassword = null;
		
		/**
		 * this logic is writing for enter the doctor detais from excel file and store (write in excel file)
		 */
		for (Entry<String, String> setmap : excelutility.getHashMapData("AddDoctor", 0).entrySet()) {
			String data = setmap.getKey();
		
			try {
				if (data.contains("docemail")) {
					doctorusername = ran + setmap.getValue();
					al.add(new String[] { data, doctorusername });// store in an ArrayList
					sendKeysValue(By.name(data), doctorusername);// fill the data from excel sheet
				} else if (data.contains("npass")) {

					doctorpassword = ran + setmap.getValue();
					al.add(new String[] { data, doctorpassword });// store in an ArrayList
					sendKeysValue(By.name(data), doctorpassword);// fill the data from excel sheet
				} else if (data.contains("docname")) {
					docname = ran + setmap.getValue();
					al.add(new String[] { data, docname });// store in an ArrayList
					sendKeysValue(By.name(data), docname);// fill the data from excel sheet
				} else {
					al.add(new String[] { data, ran + setmap.getValue() });// store in an ArrayList
					sendKeysValue(By.name(data), ran + setmap.getValue());// fill the data from excel sheet
				}
			} catch (Exception e) {
				continue;
			}
		}
		excelutility.writeExcelData(al,"Sheet2");// store the ArrayList data into Excel sheet
		submitButton.click();
		
		//popup text
		String expected="Doctor info added Successfully";
		
		Assert.assertEquals(getTextPopup(), expected, "Doctor is not Added");
		popupHandle("Successfully");// to handle the popup
		
		/**
		 * this logic is write for find the doctor name which are added by admin
		 */
		boolean flag = false;
		String text =null;
		for (WebElement e : allDoctorDetails) {
			 text = e.getText();
			if (text.contains(docname)) {
				flag = true;
				break;
			}
		}
		Assert.assertEquals(docname, text, "Doctor is not there in Doctor List");
		Reporter.log("Doctor is added successfully",true);
	}
}
