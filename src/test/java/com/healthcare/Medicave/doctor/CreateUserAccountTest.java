package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateUserAccountTest {
	WebDriver driver;
	FileInputStream fis = null;
	FileInputStream fis1 = null;
	Workbook work = null;
	Properties pr = new Properties();
	HashMap<String, String> usermap = new HashMap<String, String>();
	Random r = new Random();
	int ran = r.nextInt();
	String username;
	String password;
	
	
	public void detailsData() {
		try {
			fis = new FileInputStream("./src/test/resources/User.properties");
			pr.load(fis);

		} catch (Exception e) {
		}

		try {
			fis1 = new FileInputStream("./src/test/resources/userDetails.xlsx");
			work = WorkbookFactory.create(fis1);
			Sheet sheet = work.getSheet("CreateAccount");
			int rowcount = sheet.getPhysicalNumberOfRows();

			for (int i = 0; i < rowcount; i++) {
				String key = sheet.getRow(i).getCell(0).getStringCellValue();
				String value = sheet.getRow(i).getCell(1).getStringCellValue();

				// store into map
				try {
					usermap.put(key, value);
				} catch (NullPointerException e1) {
				}
			}

		} catch (Exception e) {
		}
	}
	public void loginUser() {
		detailsData();
		username = pr.getProperty("username");
		password = pr.getProperty("password");
		driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

		driver.get(pr.getProperty("url"));
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
	}
	public void createAccount() {
		loginUser();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		WebElement invalidup = null;
		WebElement bookAppointment = null;
				
		try {
			invalidup = driver.findElement(By.xpath("//span[contains(text(),'Invalid')]"));
		} catch (Exception e) {
		}

		if (invalidup.getText().contains("Invalid")) {
			driver.findElement(By.cssSelector("[href='registration.php']")).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id("registration")));
			for (Entry<String, String> setmap : usermap.entrySet()) {
				String data = setmap.getKey();
				try {
					if (data.contains("email")) {
						username =ran+setmap.getValue();
						driver.findElement(By.name(data)).sendKeys(username);
					} else if (data.contains("password")) {
						password = ran+setmap.getValue();
						driver.findElement(By.name(data)).sendKeys(password);
					} else {
						driver.findElement(By.name(data)).sendKeys(ran + setmap.getValue());
					}
				} catch (Exception e) {
					continue;
				}
			}
			driver.findElement(By.cssSelector("[id='rg-male']+label")).click();
			driver.findElement(By.cssSelector("button[id='submit']")).click();
			
			Alert alt=driver.switchTo().alert();
			if(alt.getText().contains("Registered")) {
				alt.accept();
				System.out.println("User is Successfully Registered");
			}
			else {
				System.out.println("User is not Successfully Registered");
			}

		} else {
			try{
				bookAppointment = driver.findElement(By.xpath("//span[text()=' Book Appointment ']"));
				bookAppointment.click();
			}catch (NoSuchElementException e) {
			}
		}
		driver.findElement(By.xpath("//span[text()=' Book Appointment ']")).click();
		
		WebElement spl=driver.findElement(By.name("Doctorspecialization"));
		wait.until(ExpectedConditions.elementToBeClickable(spl));
		Select sel=new Select(spl);
		sel.selectByVisibleText("ENT");
		
	}
	public void bookAppointment() {
		
		driver.findElement(By.cssSelector("[href='user-login.php']")).click();
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();
		
		driver.findElement(By.xpath("//span[text()=' Book Appointment ']")).click();
	}
	public void storeUserData() {
		
	}

	public static void main(String[] args) {
		CreateUserAccountTest cua = new CreateUserAccountTest();
		cua.createAccount();
	}
}
