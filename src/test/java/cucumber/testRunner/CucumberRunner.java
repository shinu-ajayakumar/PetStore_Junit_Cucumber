package cucumber.testRunner;

import org.junit.runner.RunWith;

import cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(format = { "pretty", "json:target/cucumber.json" }, features = { "src/cucumber/" })
public class CucumberRunner {

}