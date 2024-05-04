package com.genaricLibUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CustomListeners implements ITestListener{
	ExtentReports report;
	ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		test=report.createTest(methodName);
		Reporter.log(methodName+" Execution Started ",true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		test.log(Status.PASS, methodName+" Passed");
		Reporter.log(methodName+" Execution Successfull ",true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		JavaUtilities ju=new JavaUtilities();
		
		String path=null;
		String methodName=result.getMethod().getMethodName();
		try {
		 path=WebDriverCommonUtility.screenshotWebpage(BaseClass.sdriver,methodName+ ju.getSystemDateinFormat());
		} catch (Exception e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(path);
		test.log(Status.FAIL, methodName+" FAILED");
		test.log(Status.FAIL, result.getThrowable());
		Reporter.log(methodName+" Execution Failed ",true);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+" SKIPPED");
		test.log(Status.SKIP, result.getThrowable());
		Reporter.log(methodName+" Execution Failed ",true);
	}

	@Override
	public void onStart(ITestContext context) {
	//Configure Report
		ExtentSparkReporter htmlreport=new ExtentSparkReporter(".\\extentReport\\report.html");
		htmlreport.config().setDocumentTitle("HMS");
		htmlreport.config().setTheme(Theme.STANDARD);
		htmlreport.config().setReportName("Hospital Management System");
		
		 report=new ExtentReports();
		report.attachReporter(htmlreport);
		report.setSystemInfo("Base Platfrom", "OS");
		report.setSystemInfo("Base Browser", "chrome");
		report.setSystemInfo("Base URL", "http://rmgtestingserver/domain/Hospital_Management_System/");
		report.setSystemInfo("Reporter Name", "Subham");
	}

	@Override
	public void onFinish(ITestContext context) {
		report.flush();
	}
	
}
