package com.ui.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public class SearchResultsPage extends BrowserUtility {
	private static final By SEARCH_PAGE_HEADER = By.xpath("//span[@class='lighter']");
	private static final By ALL_PRODUCT_NAMES = By.xpath("//h5[@itemprop='name']/a");


	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

	public String getSearchPageHeader() {
		return getVisibleText(SEARCH_PAGE_HEADER);
	}

	public boolean isValidProductListDisplayed(String searchTerm) {
		List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(" "));
		List<String> allProductnames = getAllVisibleText(ALL_PRODUCT_NAMES);

		for (String productName : allProductnames) {
			String lowercaseName = productName.toLowerCase();
			for (String word : keywords) {
				if (lowercaseName.contains(word)) {
					return true;
				}
			}

		}
		return false;
	}
	public void getAllDressesNames() {
		getAllVisibleText(ALL_PRODUCT_NAMES);
	}
	
	public ProductDetailPage clickOnProduct(int index) {
		clickOn(getAllLinks(ALL_PRODUCT_NAMES).get(index));
		ProductDetailPage productDetailPage = new ProductDetailPage(getDriver());
		return productDetailPage;
		
		
	}

}
