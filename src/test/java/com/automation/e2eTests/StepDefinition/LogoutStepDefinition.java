package com.automation.e2eTests.StepDefinition;

import com.automation.e2eTests.pageObjects.LoginPage;
import com.automation.e2eTests.pageObjects.LogoutPage;
import com.automation.e2eTests.utils.Validations;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogoutStepDefinition {

	private LogoutPage logoutPage;
	private Validations validation;

	public LogoutStepDefinition() {
		validation = new Validations();
	}

	@When("I click on the burger button in the top left")
	public void iClickOnTheBurgerButtonInTheTopLeft() {
		logoutPage = new LogoutPage();
		logoutPage.clickOnBurgerButton();
	}

	@When("I click on the Logout button")
	public void iClickOnTheLogoutButton() {
		logoutPage.logout();
	}

	@Then("I am redirected to the login page {string}")
	public void iAmRedirectedToTheLoginPage(String expectedTitle) {
		LoginPage loginPage = new LoginPage();
		if (expectedTitle != null && !expectedTitle.trim().isEmpty()) {
			validation.assertEquals(loginPage.getLoginLogo(), expectedTitle);
		}
	}

}
