package com.automation.e2eTests.StepDefinition;

import com.automation.e2eTests.pageObjects.ProductCatalogPage;
import com.automation.e2eTests.utils.Validations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SortProductsStepDefinition {

	private ProductCatalogPage productCatalog;
	private Validations validation;

	public SortProductsStepDefinition() {
		validation = new Validations();
	}

	@When("the user sorts products by {string}")
	public void theUserSortsProductsBy(String sortOption) {
		productCatalog = new ProductCatalogPage();
		productCatalog.selectSortOption(sortOption);

	}

	@Then("the products should be displayed in order {string}")
	public void theProductsShouldBeDisplayedInOrder(String order) {

		String currentSortOption = productCatalog.getCurrentSortOption().toLowerCase();
		boolean isSorted;

		isSorted = switch (currentSortOption) {
		case "price (low to high)" -> productCatalog.areProductsSortedByPriceAsc();
		case "price (high to low)" -> productCatalog.areProductsSortedByPriceDesc();
		case "name (a to z)" -> productCatalog.areProductsSortedByNameAsc();
		case "name (z to a)" -> productCatalog.areProductsSortedByNameDesc();
		default -> throw new IllegalArgumentException("Unknown sort option: " + currentSortOption);
		};
		validation.assertTrue("Products are not sorted correctly by " + currentSortOption + " in " + order + " order!",
				isSorted);

	}

}
