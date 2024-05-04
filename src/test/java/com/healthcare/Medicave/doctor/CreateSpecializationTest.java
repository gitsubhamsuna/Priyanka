package com.healthcare.Medicave.doctor;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.HashMap;
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

public class CreateSpecializationTest {
	WebDriver driver=null;
	public void createSpecialization() {
		FileInputStream fis1 = null;
		FileInputStream fis2 = null;
		Properties pr = new Properties();
		Workbook work = null;
		HashMap<String, String> adminmap = new HashMap<String, String>();
//		wait=new WebDriverWait(driver, Duration.ofSeconds(30));

		try {
			fis1 = new FileInputStream("./src/test/resources/addDoctor.xlsx");
			work = WorkbookFactory.create(fis1);
			Sheet sheet = work.getSheet("Sheet1");
			int rowcount = sheet.getPhysicalNumberOfRows();

			for (int i = 0; i < rowcount; i++) {
				String key = sheet.getRow(i).getCell(0).getStringCellValue();
				String value = sheet.getRow(i).getCell(1).getStringCellValue();

				// store into map
				try {
					adminmap.put(key, value);
				} catch (NullPointerException e1) {
				}
			}

		} catch (Exception e) {	}

		try {
			fis2 = new FileInputStream("./src/test/resources/adminlogindetails.properties");
			pr.load(fis2);

		} catch (Exception e) {

			e.printStackTrace();
		}
		String url = pr.getProperty("url"), username = pr.getProperty("username"),
				password = pr.getProperty("password");

		driver = new ChromeDriver();

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// access the admin module
		driver.get(pr.getProperty("url"));
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("submit")).click();

		WebElement doctorDropdown = driver.findElement(By.xpath("//span[contains(text(),'Doctors')] /parent::div"));
		Actions act = new Actions(driver);
		act.click(doctorDropdown).perform();
		driver.findElement(By.xpath("//span[contains(text(),'Doctor Specialization')]")).click();

		Random r = new Random();
		int ran = r.nextInt();
		String doctorSpecialization = ran + pr.getProperty("DoctorSpecialization");
		driver.findElement(By.name("doctorspecilization")).sendKeys(doctorSpecialization);
		driver.findElement(By.name("submit")).click();

		WebElement confDocSpeci = driver.findElement(By.xpath("//div[@class='panel-body']/p"));
		if (confDocSpeci.getText().contains("successfully")) {
			System.out.println("Doctor Specialization added successfully !!");
		} else {
			System.out.println("Doctor Specialization not added!!");
		}
		
		driver.findElement(By.xpath("//i[@class='ti-angle-down']")).click();
		driver.findElement(By.xpath("//a[@href='logout.php']")).click();
	}
	public static void main(String[] args) {
		CreateSpecializationTest cst=new CreateSpecializationTest();
		cst.createSpecialization();
	}
}
