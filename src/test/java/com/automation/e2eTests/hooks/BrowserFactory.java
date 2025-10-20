package com.automation.e2eTests.hooks;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserFactory {

	public static WebDriver createBrowser(String browser) {

		WebDriver driver;
		if (browser == null) {
			browser = "chrome";
		}

		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver(buildChromeOptions());
			break;

		case "firefox":
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("--start-maximized");
			// firefoxOptions.setCapability("platform", Platform.WIN11);
			driver = new FirefoxDriver(firefoxOptions);
			break;

		case "edge":
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;

		default:
			throw new IllegalArgumentException("Browser not supported : " + browser);
		}

		return driver;
	}

	private static ChromeOptions buildChromeOptions() {
		ChromeOptions options = new ChromeOptions();

		// Performance et stabilité
		options.addArguments("--start-maximized");
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-gpu");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-logging");
		options.addArguments("--log-level=3");

		// Supprimer les popups, notifications et bulles
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-extensions");
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-save-password-bubble");

		// Bloquer les alertes du gestionnaire Google
		options.addArguments(
				"--disable-features=AutofillServerCommunication,PasswordManagerOnboarding,PasswordManagerRedesign,OptimizationHintsFetching,AccountConsistency");

		// Empêcher toute connexion automatique à un compte Google
		options.addArguments("--guest");
		options.addArguments("--no-first-run");
		options.addArguments("--disable-first-run-ui");

		// Profil Chrome isolé (aucune donnée Google ni cache)
		String tempProfile = System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.currentTimeMillis();
		options.addArguments("--user-data-dir=" + tempProfile);

		// Préférences Chrome (désactivation gestionnaire de mots de passe)
		Map<String, Object> prefs = new HashMap<>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);
		prefs.put("profile.default_content_setting_values.notifications", 2);
		options.setExperimentalOption("prefs", prefs);

		// options.addArguments("--headless=new");

		return options;
	}

}
