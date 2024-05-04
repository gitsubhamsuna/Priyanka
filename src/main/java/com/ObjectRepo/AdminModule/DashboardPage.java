package com.ObjectRepo.AdminModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class DashboardPage {
	@FindBy(xpath="//span[contains(text(),'Doctors')]/parent::div[@class='item-inner']")
	private WebElement doctorDropdown;
	
	@FindBy(xpath="//span[contains(text(),'Specialization')]/parent::a[@href='doctor-specilization.php']")
	private WebElement doctorSpecializationLink;
	
	@FindBy(xpath="//span[contains(text(),'Add')]/parent::a[@href='add-doctor.php']")
	private WebElement addDoctorLink;
	
	@FindBy(xpath="//span[contains(text(),'Manage')]/parent::a[@href='Manage-doctors.php']")
	private WebElement manageDoctorLink;
	
	
	
	public DashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}



	public void getDoctorDropdown() {
		 doctorDropdown.click();
	}



	public WebElement getDoctorSpecializationLink() {
		return doctorSpecializationLink;
	}



	public void getAddDoctorLink() {
		 addDoctorLink.click();
	}



	public WebElement getManageDoctorLink() {
		return manageDoctorLink;
	}
	
	public void clickAddDoctorLink() {
		getDoctorDropdown();
		getAddDoctorLink();
	}
	
	public void clickAddSpecializationLink() {
		getDoctorDropdown();
		doctorSpecializationLink.click();
	}
	
	
	
	
}
