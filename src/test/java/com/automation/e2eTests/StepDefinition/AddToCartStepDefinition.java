package com.automation.e2eTests.StepDefinition;

import java.util.List;

import com.automation.e2eTests.pageObjects.CartPage;
import com.automation.e2eTests.pageObjects.ProductCatalogPage;
import com.automation.e2eTests.utils.Validations;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddToCartStepDefinition {

	private ProductCatalogPage productCatalogPage;
	private Validations validation;
	private CartPage cartePage;

	public AddToCartStepDefinition() {
		validation = new Validations();
	}

	@When("I add the following products to the cart:")
	public void iAddTheFollowingProductsToTheCart(DataTable dataTable) {
		productCatalogPage = new ProductCatalogPage();

		List<String> productsNames = dataTable.asList();
		for (String productName : productsNames) {
			productCatalogPage.addToCart(productName);
		}
	}

	@Then("the cart icon should show {string} items")
	public void theCartIconShouldShowItems(String expectedCount) {
		int expected = Integer.parseInt(expectedCount);
		int actualCount = productCatalogPage.getCartItemCount();
		validation.assertEquals("Le nombre d'articles dans le panier est incorrect", expected, actualCount);
	}

	@When("I click on the cart icon")
	public void iClickOnTheCartIcon() {
		productCatalogPage.clickOnCartIcon();
	}

	@Then("I should be redirected to the Cart page titled {string}")
	public void iShouldBeRedirectedToTheCartPageTitled(String expectedTitle) {
		cartePage = new CartPage();
		if (expectedTitle != null && !expectedTitle.trim().isEmpty()) {
			validation.assertEquals(cartePage.getCartTitle(), expectedTitle);
		}
	}

	@Then("I should see the following products in my cart:")
	public void iShouldSeeTheFollowingProductsInMyCart(DataTable dataTable) {
		List<String> expectedProducts = dataTable.asList();
		List<String> actualProducts = cartePage.getCartProductNames();

		for (String expected : expectedProducts) {
			validation.assertTrue(
					"Expected product '" + expected + "' was not found in the cart. Found: " + actualProducts,
					actualProducts.contains(expected));
		}
	}

}
