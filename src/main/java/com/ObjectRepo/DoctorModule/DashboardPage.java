package com.ObjectRepo.DoctorModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	//initilization
	@FindBy(css = "p>a[href='edit-profile.php']")
	private WebElement updateProfileLink;

	@FindBy(xpath = "//h2[text()='My Profile']")
	private WebElement myprofileText;

	@FindBy(css = "p>a[href='appointment-history.php']")
	private WebElement viewAppointmentHistoryLink;

	@FindBy(xpath = "//h2[text()='My Appointments']")
	private WebElement myAppointmentText;

	@FindBy(xpath = "//span[contains(text(),'History')]")
	private WebElement appointmentHistoryLink;

	@FindBy(xpath = "//span[contains(text(),'Patients')]/parent::div")
	private WebElement patientDropdown;

	@FindBy(xpath = "//span[contains(text(),' Search ')]/parent::div")
	private WebElement searchLink;
	
	@FindBy(xpath="//span[contains(text(),'Add')]")
	private WebElement addPatientLink;
	
	@FindBy(xpath="//span[contains(text(),'Manage')]")
	private WebElement managePatientLink;
	
	//declaration
	public DashboardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public WebElement getUpdateProfileLink() {
		return updateProfileLink;
	}

	public WebElement getMyprofileText() {
		return myprofileText;
	}

	public WebElement getViewAppointmentHistoryLink() {
		return viewAppointmentHistoryLink;
	}

	public WebElement getMyAppointmentText() {
		return myAppointmentText;
	}

	public WebElement getAppointmentHistoryLink() {
		return appointmentHistoryLink;
	}

	public WebElement getPatientDropdown() {
		return patientDropdown;
	}

	public WebElement getSearchLink() {
		return searchLink;
	}
	
	public void clickPatient() {
		patientDropdown.click();
	}
	public void clickAddPatient() {
		clickPatient();
		addPatientLink.click();
	}
	public void clickManagePatient() {
		clickPatient();
		managePatientLink.click();
	}
	
}
