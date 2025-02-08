package com.ui.dataproviders;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.google.gson.Gson;
import com.ui.pojo.TestData;
import com.ui.pojo.User;
import com.utility.CSVReaderUtility;

public class LoginDataProvider {
	
	
	@DataProvider(name = "LogintestDataProvider")
	public Iterator<Object[]> loginDataprovider() {
		FileReader filereader = null;
		Gson gson = new Gson();
		File testDataFile = new File(System.getProperty("user.dir") + File.separator + "testData" + File.separator + "loginData.json");
		try {
			filereader = new FileReader(testDataFile);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		TestData testData = gson.fromJson(filereader, TestData.class);
		
		List<Object[]> dataToReturn = new ArrayList<Object[]>();
		for (User user:testData.getData()) {
			dataToReturn.add(new Object[] {user});
			
		}
		return dataToReturn.iterator();
		
		
	}
	
	@DataProvider(name = "loginCSVDataProvider")
	public Iterator<User> loginCSVDataprovider() {
		return CSVReaderUtility.readCsvFile("loginData.csv");
	}


}
