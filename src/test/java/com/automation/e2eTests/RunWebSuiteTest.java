package com.automation.e2eTests;

import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" }, plugin = { "pretty",
		"html:target/report/cucumber-report.html", "json:target/report/cucumber.json" }, tags = (""),

		glue = { "com.automation.e2eTests" },

		/*
		 * tags = ("@login_valid_credentials or @Logout"),
		 *
		 */
		monochrome = true, snippets = CAMELCASE)

public class RunWebSuiteTest {

}
