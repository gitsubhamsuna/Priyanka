package com.ObjectRepo.AdminModule;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ObjectRepo.HomePage;

public class LoginPage {
	//Initialization
	@FindBy (name="username")
	private WebElement username;
	
	@FindBy (name="password")
	private WebElement password;
	
	@FindBy (name="submit")
	private WebElement submit;
	
	//Declaration
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	//Utilization
	public WebElement getUsername() {
		return username;
	}

	public WebElement getPassword() {
		return password;
	}

	public WebElement getSubmit() {
		return submit;
	}
	
	public void loginApplication(String uname, String pass) {
		username.sendKeys(uname);
		password.sendKeys(pass);
		submit.click();
	}
}
