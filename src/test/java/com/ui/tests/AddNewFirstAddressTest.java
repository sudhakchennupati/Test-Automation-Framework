package com.ui.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.AddressPage;
import com.ui.pages.MyAccountPage;
import com.ui.pojo.AddressPOJO;
import com.utility.FakeAddressUtility;

@Listeners({com.ui.listeners.TestListener.class})
public class AddNewFirstAddressTest extends TestBase {
	private MyAccountPage myAccountPage;
	private AddressPOJO address; 
	
	
	@BeforeMethod(description = "Valid first time user logs in to the application")
	public void setup() {
		myAccountPage = homePage.goToLoginPage().doLoginWith("gamiyel275@maonyn.com", "password");
		address = FakeAddressUtility.getFakeAddress();
		
	}
	@Test (description = "add new address so we can checkout", groups = {"e2e","sanity"})
	public void addNewAddress() {
		String newAddress = myAccountPage.goToAddressPage().saveAddress(address);
		Assert.assertEquals(newAddress, address.getAddressAlias().toUpperCase());
		
	}

}
