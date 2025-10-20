package com.automation.e2eTests.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

/**
 * Utility class for initializing and managing the {@link WebDriver} instance
 * before executing Cucumber scenarios.
 *
 * This class uses the {@code @Before} annotation to automatically start a
 * browser before each scenario. The browser is dynamically selected based on
 * the system property {@code -Dbrowser}.
 *
 * Supported browsers: - chrome (default) - firefox - edge
 *
 * If no parameter is provided, Chrome will be used by default.
 *
 * A logger is also available to track scenario status and driver
 * initialization.
 */
public class Hooks {

	private static WebDriver driver;
	private static final Logger LOGGER = (Logger) LogManager.getLogger(Hooks.class.getName());

	/**
	 * Executed before each Cucumber scenario. Initializes a {@link WebDriver}
	 * instance depending on the browser specified via the system property
	 * {@code -Dbrowser}. If no parameter is specified, Chrome is used by default.
	 * 
	 * @param scenario the Cucumber scenario currently being executed
	 * @throws IllegalArgumentException if the requested browser is not supported
	 */
	@Before
	public void setWebDriver(Scenario scenario) {

		LOGGER.info("Scenario: " + scenario.getName() + " -started.");
		String browser = System.getProperty("browser");
		driver = BrowserFactory.createBrowser(browser);

	}

	/**
	 * This method is used to close browser. This method is called after the
	 * invocation of each test method in given class.
	 * 
	 * @After Methods annotated with @After execute after every scenario.
	 */
	@After
	public void quitDriver(Scenario scenario) {
		if (scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Screenshot");

		}
		driver.quit();
		LOGGER.info("Scenario: " + scenario.getName() + " - finished." + scenario.getStatus());
	}

	public static WebDriver getDriver() {
		return driver;
	}

}
