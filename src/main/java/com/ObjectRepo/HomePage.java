package com.ObjectRepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.genaricLibUtilities.WebDriverCommonUtility;

//import com.genaricLibUtilities.WebDriverCommonUtility;

public class HomePage{
	// initialization
	/**
	 * this is admin module link
	 */
	@FindBy(css = "[href='hms/admin']")
	private WebElement adminLink;
	
	/**
	 * this is doctor module link
	 */
	@FindBy(css = "[href='hms/doctor/']")
	private WebElement doctorLink;
	
	/**
	 * this is patient module link
	 */
	@FindBy(css = "[href='hms/user-login.php']")
	private WebElement patientLink;
	
	@FindBy(css = "li[class]>a[href='index.html']")
	private WebElement homeLink;
	
	@FindBy(css = "li[class]~li a[href='contact.php']")
	private WebElement contactLink;
	
	@FindBy(xpath = "//i[@class='ti-angle-down']")
	private WebElement profileDropdown;

	@FindBy(xpath = "//a[@href='logout.php']")
	private WebElement logoutLink;
	
	//Declaration
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//Utilization 
	public WebElement getAdminLink() {
		return adminLink;
	}

	public WebElement getDoctorLink() {
		return doctorLink;
	}

	public WebElement getPatientLink() {
		return patientLink;
	}
	

	public WebElement getHomeLink() {
		return homeLink;
	}

	public WebElement getContactLink() {
		return contactLink;
	}

	public WebElement getProfileDropdown() {
		return profileDropdown;
	}

	public WebElement getLogoutLink() {
		return logoutLink;
	}
	
	
	//Business Logic
	public void openDoctorLink() {
		doctorLink.click();
	}
	
	public void openAdminLink() {
		adminLink.click();
	}
	public void openPatientLink() {
		patientLink.click();
	}
	
	public void logoutPage() {
		profileDropdown.click();
		logoutLink.click();
	}
}
