package com.genaricLibUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class CustomeRetryAnalyser implements IRetryAnalyzer{
	int count=0;
	int retry_Limit=4;
	
	
	
	@Override
	public boolean retry(ITestResult result) {
		if(count<retry_Limit) {
			count++;
			return true;
		}
		return false;
	}

}
