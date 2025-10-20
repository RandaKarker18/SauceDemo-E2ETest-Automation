package com.automation.e2eTests.pageObjects;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.e2eTests.utils.SeleniumUtils;

public class ProductCatalogPage extends BasePage {

	private SeleniumUtils seleniumUtils;

	@FindBy(how = How.XPATH, using = "//a[@class='shopping_cart_link']")
	private WebElement cartIconBtn;

	private By productDescription = By.xpath("//div[contains(@class,'inventory_item_description')]");

	public ProductCatalogPage() {
		super();
		seleniumUtils = new SeleniumUtils(driver);
	}

	public Map<String, WebElement> getAllCatalogProductsMap() {
		List<WebElement> allProducts = seleniumUtils.findElements(productDescription);
		// Create a map: Product Name -> Product WebElement
		return allProducts.stream().collect(Collectors.toMap(
				product -> product.findElement(By.className("inventory_item_name")).getText(), product -> product));
	}

	public void addToCart(String productName) {
		Map<String, WebElement> productMap = getAllCatalogProductsMap();

		WebElement product = productMap.get(productName);
		if (product != null) {
			WebElement addToCartButton = product.findElement(By.cssSelector(".btn_inventory"));
			addToCartButton.click();
		} else {
			throw new RuntimeException("Product not found: " + productName);
		}
	}

	public int getCartItemCount() {
		try {
			WebElement badge = seleniumUtils.findElement(By.className("shopping_cart_badge"));
			return Integer.parseInt(badge.getText());
		} catch (NoSuchElementException e) {
			// Si aucun badge, le panier est vide
			return 0;
		}
	}

	public void clickOnCartIcon() {
		seleniumUtils.safeClick(cartIconBtn);
	}

}
