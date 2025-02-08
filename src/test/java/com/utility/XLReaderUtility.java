package com.utility;

import java.io.File;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLReaderUtility {
	
	
	public static void main(String[] args) {
		File xlsxFile = new File(System.getProperty("user.dir") + "\\testData\\logindata.xlsx");
		
		//XLSX File
		try {
			XSSFWorkbook xssfworkbook = new XSSFWorkbook(xlsxFile);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
