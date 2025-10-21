package com.automation.e2eTests.StepDefinition;

import com.automation.e2eTests.pageObjects.LoginPage;
import com.automation.e2eTests.pageObjects.ProductCatalogPage;
import com.automation.e2eTests.utils.Validations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinition {

	private LoginPage loginPage;
	private ProductCatalogPage productCatalog;
	private Validations validation;

	public LoginStepDefinition() {
		validation = new Validations();
	}

	@Given("I navigate to the Sauce labs website")
	public void iNavigateToTheSauceLabsWebsite() {
		// Le driver est initialisé par Hooks @Before
		loginPage = new LoginPage(); // Création après que le driver soit prêt
		productCatalog = new ProductCatalogPage();
		loginPage.openLoginPage();
	}

	@When("I fill the userName field")
	public void iFillTheUserNameField() {
		loginPage.fillUserName();
	}

	@When("I fill the password field")
	public void iFillThePasswordField() {
		loginPage.fillPassword();
	}

	@When("I click on the Login button")
	public void iClickOnTheLoginButton() {
		loginPage.login();
	}

	@Then("I am redirected to the Home page {string}")
	public void iAmRedirectedToTheHomePage(String expectedTitle) {
		if (expectedTitle != null && !expectedTitle.trim().isEmpty()) {
			validation.assertEquals(productCatalog.getProductLabel(), expectedTitle);
		}
	}

}
