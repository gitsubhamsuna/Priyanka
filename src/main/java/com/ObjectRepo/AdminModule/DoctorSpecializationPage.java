package com.ObjectRepo.AdminModule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class DoctorSpecializationPage {
	@FindBy(name="doctorspecilization")
	private WebElement doctSpecializationTextField;
	
	@FindBy(name="submit")
	private WebElement submitButton;
	
	@FindBy(xpath="//div[@class='panel-body']/p")
	private WebElement confirSpecializationMessage;
	
	public DoctorSpecializationPage(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	
	
	public void getDoctSpecializationTextField(String data) {
		 doctSpecializationTextField.sendKeys(data);
	}



	public void getSubmitButton() {
		 submitButton.click();
	}

	//business logic
	public void createSpecialization(String specializationName) {
		getDoctSpecializationTextField(specializationName);
		getSubmitButton();
		
		if (confirSpecializationMessage.getText().contains("successfully")) {
			Reporter.log("Doctor Specialization added successfully !!",true);
		} else {
			Reporter.log("Doctor Specialization not added!!",true);
		}
		
	}
	
}
