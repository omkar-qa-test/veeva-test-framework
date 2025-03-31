package runners;

import org.testng.annotations.AfterSuite;
import org.veeva.drivers.DriverManager;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features/cpHomePage.feature"},
		glue = {"steps"},
		plugin = {
				"pretty",
				"html:target/cucumber-reports/cucumber.html",
				"json:target/cucumber-reports/cpLandingPage.json",
				"timeline:target/cucumber-reports/timeline"

		}

		)
public class TestRunnerCP extends AbstractTestNGCucumberTests {



}