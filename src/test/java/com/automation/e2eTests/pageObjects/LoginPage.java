package com.automation.e2eTests.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.automation.e2eTests.utils.ConfigFileReader;
import com.automation.e2eTests.utils.SeleniumUtils;

public class LoginPage extends BasePage {

	private SeleniumUtils seleniumUtils;
	private ConfigFileReader configFileReader;

	@FindBy(how = How.ID, using = "user-name")
	private WebElement userName;

	@FindBy(how = How.ID, using = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement btnLogin;

	@FindBy(xpath = "//div[@class='login_logo']")
	private WebElement loginLogo;

	public LoginPage() {
		super();
		seleniumUtils = new SeleniumUtils(driver);
		configFileReader = new ConfigFileReader();
	}

	public void openLoginPage() {
		seleniumUtils.get(configFileReader.getProperties("url"));
	}

	public void fillUserName() {
		seleniumUtils.writeText(userName, configFileReader.getProperties("username"));
	}

	public void fillPassword() {
		seleniumUtils.writeText(password, configFileReader.getProperties("password"));
	}

	public void login() {
		seleniumUtils.safeClick(btnLogin);
	}

	public WebElement getLoginLogo() {
		return loginLogo;
	}

}
