package com.ui.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.constants.Env;
import com.utility.JSONUtility;
//import com.utility.PropertiesUtility;

public class MyretryAnalyzer implements IRetryAnalyzer {
	/*
	 * Read from Property file
	 * private static final int MAX_NUMBER_OF_ATTEMPTS = Integer.parseInt(PropertiesUtility.readProperty(Env.QA, "MAX_NUMBER_OF_ATTEMPTS"));
	 */
	//Read from Json file
	private static final int MAX_NUMBER_OF_ATTEMPTS = JSONUtility.readJSON(Env.QA).getMAX_NUMBER_OF_ATTEMPTS();
	
	private static int currentAttempt = 1;

	@Override
	public boolean retry(ITestResult result) {
		//ITestResult gives information about failed tests like what is name of test, groups test belong to, exceptions this test gives
		if(currentAttempt <= MAX_NUMBER_OF_ATTEMPTS) {
			currentAttempt++;
			return true;
		}
		
		return false;
	}

}
