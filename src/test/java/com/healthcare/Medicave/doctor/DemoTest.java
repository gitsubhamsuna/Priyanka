package com.healthcare.Medicave.doctor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.genaricLibUtilities.BaseClass;
import com.genaricLibUtilities.CustomListeners;


@Listeners(CustomListeners.class)
public class DemoTest extends BaseClass{
	
	@Test
	public void demoTest() throws InterruptedException {
//		 driver=new ChromeDriver();
		
		driver.get("https://www.amazon.in");
//		driver.get("http://rmgtestingserver/domain/Hospital_Management_System/");
		Assert.assertEquals(false, true);
		driver.quit();
	}

}
