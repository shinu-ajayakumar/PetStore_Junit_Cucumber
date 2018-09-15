package cucumber.testRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty", "json:target/cucumber.json", "html:target/cucumber.html" }, features = {
		"src/test/java/cucumber" }, glue = { "cucumber.stepDefinitions" }, monochrome = true, dryRun = false, strict = true)
public class CucumberRunner {

}