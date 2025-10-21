package com.automation.e2eTests.pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.e2eTests.utils.SelectFromListUtils;
import com.automation.e2eTests.utils.SeleniumUtils;

public class ProductCatalogPage extends BasePage {

	private SeleniumUtils seleniumUtils;

	@FindBy(how = How.CSS, using = "[data-test='title']")
	private WebElement productLabel;

	@FindBy(how = How.XPATH, using = "//a[@class='shopping_cart_link']")
	private WebElement cartIconBtn;

	@FindBy(how = How.CLASS_NAME, using = "product_sort_container")
	private WebElement sortDropdown;

	private By productDescription = By.xpath("//div[contains(@class,'inventory_item_description')]");
	private By productPrices = By.className("inventory_item_price");
	private By productNames = By.className("inventory_item_name");

	public ProductCatalogPage() {
		super();
		seleniumUtils = new SeleniumUtils(driver);
	}

	public WebElement getProductLabel() {
		return productLabel;
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

	public void selectSortOption(String sortOption) {
		SelectFromListUtils.selectDropDownListByVisibleText(sortDropdown, sortOption);
	}

	public String getCurrentSortOption() {
		return SelectFromListUtils.getSelectedOption(sortDropdown);
	}

	public boolean areProductsSortedByPriceAsc() {
		List<Double> prices = getPricesFromPage();
		List<Double> sortedPrices = new ArrayList<>(prices);
		Collections.sort(sortedPrices);
		return prices.equals(sortedPrices);
	}

	public boolean areProductsSortedByPriceDesc() {
		List<Double> prices = getPricesFromPage();
		List<Double> sortedPrices = new ArrayList<>(prices);
		sortedPrices.sort(Collections.reverseOrder());
		return prices.equals(sortedPrices);
	}

	private List<Double> getPricesFromPage() {
		List<WebElement> priceElements = driver.findElements(productPrices);
		return priceElements.stream().map(e -> Double.parseDouble(e.getText().replace("$", "")))
				.collect(Collectors.toList());
	}

	public boolean areProductsSortedByNameAsc() {
		List<String> names = getNamesFromPage();
		List<String> sorted = new ArrayList<>(names);
		Collections.sort(sorted, String.CASE_INSENSITIVE_ORDER);
		return names.equals(sorted);
	}

	public boolean areProductsSortedByNameDesc() {
		List<String> names = getNamesFromPage();
		List<String> sorted = new ArrayList<>(names);
		sorted.sort(Collections.reverseOrder(String.CASE_INSENSITIVE_ORDER));
		return names.equals(sorted);
	}

	private List<String> getNamesFromPage() {
		return driver.findElements(productNames).stream().map(WebElement::getText).collect(Collectors.toList());
	}

}
