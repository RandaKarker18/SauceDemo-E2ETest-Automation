package com.automation.e2eTests.pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.e2eTests.utils.SeleniumUtils;
import com.automation.e2eTests.utils.Wait;

public class LogoutPage extends BasePage {

	private SeleniumUtils seleniumUtils;

	@FindBy(how = How.ID, using = "react-burger-menu-btn")
	private WebElement btnBurger;

	@FindBy(how = How.ID, using = "logout_sidebar_link")
	private WebElement btnLogout;

	public LogoutPage() {
		super();
		seleniumUtils = new SeleniumUtils(driver);
	}

	public void clickOnBurgerButton() {

		WebElement clickableBtn = Wait.waitUntilClickable(driver, Duration.ofSeconds(10), btnBurger);
		seleniumUtils.safeClick(clickableBtn);

	}

	public void logout() {
		WebElement clickablebtnLogout = Wait.waitUntilClickable(driver, Duration.ofSeconds(10), btnLogout);
		seleniumUtils.safeClick(clickablebtnLogout);

	}

	public WebElement getBtnBurger() {
		return btnBurger;
	}

	public WebElement getBtnLogout() {
		return btnLogout;
	}

}
