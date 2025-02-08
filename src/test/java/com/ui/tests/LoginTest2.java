package com.ui.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;

public class LoginTest2 {

	public static void main(String[] args) {
	
		
		HomePage homepage = new HomePage(EDGE,true);
		LoginPage  loginPage = homepage.goToLoginPage();
		loginPage.doLoginWith("gamiyel275@maonyn.com", "password");
		

		
		
		
	}

}
