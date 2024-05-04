package TestNg;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNg2ClassTest {
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("--Before Suite--");
	}
	@AfterSuite
	public void afterSuite() {
		System.out.println("--After Suite--");
	}
	@BeforeTest
	public void beforeTest() {
		System.out.println("--Before Test--");
	}
	@AfterTest
	public void afterTest() {
		System.out.println("--Before Test--");
	}
	@BeforeClass
	public void beforeClass() {
		System.out.println("--Before Class--");
	}
	@AfterClass
	public void aferClass() {
		System.out.println("--After Class--");
	}
	
	
	@BeforeClass
	public void beforeClass1() {
		System.out.println("--Before Class2--");
	}
	@AfterClass
	public void aferClass1() {
		System.out.println("--After Class2--");
	}
	
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("--Before Method 1--");
	}
	@AfterMethod
	public void afterMethod() {
		System.out.println("--Before Method 1--");
	}
	
	
	@BeforeMethod
	public void beforeMethod2() {
		System.out.println("--Before Method 2--");
	}
	@AfterMethod
	public void afterMethod2() {
		System.out.println("--After Method 2--");
	}
	
	@Test
	public void createTest() {
		System.out.println("createTest Test 1");
	}
	@Test
	public void orgTest() {
		System.out.println("orgTest Test 2");
	}

}
