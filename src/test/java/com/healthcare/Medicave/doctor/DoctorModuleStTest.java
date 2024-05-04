package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DoctorModuleStTest {
	public static void main(String[] args) throws Throwable {
		FileInputStream fis1=null;
		FileInputStream fis2=null;
		Properties pr=new Properties();
		Workbook work=null;
		HashMap<String , String> map=new HashMap<String, String>();
		
		
		try {
			 fis1=new FileInputStream("./src/test/resources/AllDetails.xlsx");
			  work=WorkbookFactory.create(fis1);
			  Sheet sheet=work.getSheet("Sheet1");
			  int rowcount=sheet.getPhysicalNumberOfRows();
			  
			  for(int i=0;i<rowcount;i++) {
				  String key=sheet.getRow(i).getCell(0).getStringCellValue();
				  String value=sheet.getRow(i).getCell(1).getStringCellValue();
				  
				  //store into map
				  try{
					  map.put(key, value);
				  }catch(NullPointerException e1) {
					  
				  }
				  
			  }
			 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println(map.entrySet());
		try {
			fis2=new FileInputStream("./src/test/resources/loginDetails.properties");
			pr.load(fis2);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String url=pr.getProperty("url"),username=pr.getProperty("username"),password=pr.getProperty("password");
				
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//access the doctor module
		driver.get(pr.getProperty("url"));
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
		
		//elplicity wait 
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(30));
		//add the patient
		WebElement patientdropdown=driver.findElement(By.xpath("//span[contains(text(),'Patients')]/parent::div"));
		wait.until(ExpectedConditions.elementToBeClickable(patientdropdown));
		
		Actions act=new Actions(driver);
		act.click(patientdropdown).perform();
		WebElement patient=driver.findElement(By.xpath("//span[contains(text(),'Add Patient')]"));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'Add Patient')]")));
		act.click(patient).perform();
		String pname=null;
		String pemail=null;
		Random r=new Random();
		int ran=r.nextInt();
		for(Entry<String, String> setmap:map.entrySet()) {
			String data=setmap.getKey();
			
			try {
				if(data.contains("patname")) {
					pname=ran+setmap.getValue();
//					data=pname;
					driver.findElement(By.name(data)).sendKeys(pname);
				}
				else if(data.contains("patemail")) {
					pemail=ran+setmap.getValue();
//					data=pemail;
					driver.findElement(By.name(data)).sendKeys(pemail);
				}
				else{
					driver.findElement(By.name(data)).sendKeys(setmap.getValue());
				}
			}catch(Exception e) {
				continue;
			}
		}
		driver.findElement(By.xpath("//label[@for='rg-male']")).click();
		driver.findElement(By.id("submit")).click();
		
		act.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Patients')]/parent::div"))).click().build().perform();
		
		
		WebElement managePatient=driver.findElement(By.xpath("//span[contains(text(),'Manage Patient')]"));
		wait.until(ExpectedConditions.elementToBeClickable(managePatient));
		act.click(managePatient).perform();
		
		List<WebElement> allpatientdetails=driver.findElements(By.xpath("//td[@class='hidden-xs']"));
		boolean flag=false;
		for(WebElement e: allpatientdetails) {
			Thread.sleep(2000);
			String text=e.getText();
			if(text.contains(pname)) {
				System.out.println("Patient name is Created");
				flag=true;
				break;
			}
		}
		if(flag==false) {
			System.out.println("Patien name is not created");
		}
	}
	
	public static void adminModule() {
		FileInputStream fis1=null;
		FileInputStream fis2=null;
		Properties pr=new Properties();
		Workbook work=null;
		HashMap<String , String> adminmap=new HashMap<String, String>();
		
		
		try {
			 fis1=new FileInputStream("./src/test/resources/addDoctor.xlsx");
			  work=WorkbookFactory.create(fis1);
			  Sheet sheet=work.getSheet("Sheet1");
			  int rowcount=sheet.getPhysicalNumberOfRows();
			  
			  for(int i=0;i<rowcount;i++) {
				  String key=sheet.getRow(i).getCell(0).getStringCellValue();
				  String value=sheet.getRow(i).getCell(1).getStringCellValue();
				  
				  //store into map
				  try{
					  adminmap.put(key, value);
				  }catch(NullPointerException e1) {
					  
				  }
				  
			  }
			 
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		System.out.println(adminmap.entrySet());
		try {
			fis2=new FileInputStream("./src/test/resources/adminlogindetails.properties");
			pr.load(fis2);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		String url=pr.getProperty("url"),username=pr.getProperty("username"),password=pr.getProperty("password");
				
		
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//access the admin module
		driver.get(pr.getProperty("url"));
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
		
		
		WebElement doctorDropdown= driver.findElement(By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		Actions act=new Actions(driver);
		act.click(doctorDropdown).perform();
		driver.findElement(By.xpath("//span[contains(text(),'Doctor Specialization')]")).click();
		
		Random r=new Random();
		int ran=r.nextInt();
		String doctorSpecialization=ran+pr.getProperty("DoctorSpecialization");
		driver.findElement(By.name("doctorspecilization")).sendKeys(doctorSpecialization);
		driver.findElement(By.name("submit")).click();
		
		WebElement confDocSpeci=driver.findElement(By.xpath("//div[@class='panel-body']/p"));
		if(confDocSpeci.getText().contains("successfully")) {
			System.out.println("Doctor Specialization added successfully !!");
		}else {
			System.out.println("Doctor Specialization not added!!");
		}
		
		driver.findElement(By.xpath("//span[contains(text(),'Add Doctor')]/parent::a")).click();
		
		WebElement selectspli=driver.findElement(By.name("Doctorspecialization"));
		
		Select sel=new Select(selectspli);
		sel.selectByVisibleText(doctorSpecialization);
		for(Entry<String, String> setmap:adminmap.entrySet()) {
			String data=setmap.getKey();
			
			try {
//				if(data.contains("patname")) {
//					pname=ran+setmap.getValue();
////					data=pname;
//					driver.findElement(By.name(data)).sendKeys(pname);
//				}
//				else if(data.contains("patemail")) {
//					pemail=ran+setmap.getValue();
////					data=pemail;
//					driver.findElement(By.name(data)).sendKeys(pemail);
//				}
//				else{
					driver.findElement(By.name(data)).sendKeys(setmap.getValue());
//				}
			}catch(Exception e) {
				continue;
			}
		}
		
		driver.findElement(By.id("submit")).click();
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
