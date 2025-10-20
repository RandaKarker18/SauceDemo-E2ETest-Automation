package com.automation.e2eTests.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.e2eTests.utils.SeleniumUtils;

public class HomePage extends BasePage {

	private SeleniumUtils seleniumUtils;

	@FindBy(how = How.CSS, using = "[data-test='title']")
	private WebElement productLabel;

	public HomePage() {
		super();
		seleniumUtils = new SeleniumUtils(driver);
	}

	public WebElement getProductLabel() {
		return productLabel;
	}

}
