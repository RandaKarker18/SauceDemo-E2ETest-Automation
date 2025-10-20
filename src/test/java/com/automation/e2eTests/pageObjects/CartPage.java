package com.automation.e2eTests.pageObjects;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.e2eTests.utils.SeleniumUtils;

public class CartPage extends BasePage {

	private SeleniumUtils seleniumUtils;

	@FindBy(how = How.XPATH, using = "//span[@class='title']")
	private WebElement cartTitle;

	public CartPage() {
		super();
		seleniumUtils = new SeleniumUtils(driver);
	}

	public WebElement getCartTitle() {
		return cartTitle;
	}

	public List<String> getCartProductNames() {
		List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
		List<String> actualProducts = cartItems.stream().map(WebElement::getText).collect(Collectors.toList());
		return actualProducts;
	}
}
