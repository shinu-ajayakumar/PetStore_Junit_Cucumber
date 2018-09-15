package cucumber.stepDefinitions;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.WebElementHelper;

import org.junit.Assert;
import org.junit.Test;

public class SearchStepDefinitions {

	WebDriver driver = null;
	Select select;
	JavascriptExecutor js;
	WebDriverWait wait;
	WebElementHelper webElementHelper;

	@Before
	public void init() {

	}

	@After
	public void tearDown() {

	}

	@Given("^Initialize Chrome driver and launch the application$")
	public void launchChromeBrowserAndNavigate() {
		if (driver == null) {
			initializeBrowser("chrome");
		}
		driver.get("https://www.petstore.com");
	}

	@When("^Provide ([^\"]*) in Search Box and Click Search button$")
	// @When("^Provide \"([^\"]*)\" in Search Box and Click Search button$")
	public void searchForItems(String arg1) throws Throwable {
		setWebelementText(driver.findElement(By.id("ctl00_Search_SearchTextMainControl")), arg1);
		clickWebelementText(driver.findElement(By.cssSelector("input.searchbutton")));
	}

	@When("^Click ([^\"]*) under Shop by Category and Select ([^\"]*) from Sort by DDL$")
	public void clickShopByCategoryAndSort(String arg1, String arg2) throws Throwable {
		clickWebelementText(driver.findElement(By.linkText(arg1)));
		selectDropdown(driver.findElement(By.id("ctl00__pageBody_ddlSortItemsSLI")), arg2);
	}

	@When("^After Clicking first product enter quantity ([^\"]*) and Click Add to Cart button$")
	public void selectFirstListedProduct(String arg1) {
		// int nthProductNo = Integer.parseInt(productNumber.replaceAll("[^0-9]", ""));
		clickWebelementText(driver.findElements(By.xpath("//a[contains(@id,'hlDescription')]")).get(0));
		setWebelementText(driver.findElement(By.id("ItemQuantity")), arg1);
		clickWebelementText(driver.findElement(By.id("ctl00__pageBody_ibAddToCart")));
	}

	@Then("^Cart Count ([^\"]*) will be displayed$")
	public void verifyCartCount(String arg1) {
		waitWhileElementHasAttributeValue("slidingTopContent", "style", "display: block;");
		Assert.assertEquals(driver.findElement(By.id("cartItemsCount")).getText(), arg1);
		driver.quit();
	}

	public void waitWhileElementHasAttributeValue(String locator, String attribute, String value) {
		while (driver.findElement(By.id(locator)).getAttribute(attribute).contains(value)) {
			int timeout = 10;
			if (timeout > 0) {
				timeout--;
				try {
					// System.out.println(attribute + "t" + value);
					Thread.sleep(1000);
				} catch (Exception e) {
				}
			}
		}
	}

	private void initializeBrowser(String browserName) {

		if (browserName.toLowerCase().contains("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\\\Selenium\\\\Spring\\\\Projects\\\\PetStore\\\\src\\\\main\\\\resources\\\\drivers\\\\geckodriver.exe");
			File ffPathBinary = new File("C:\\Users\\x162346\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary(ffPathBinary);
			DesiredCapabilities desired = DesiredCapabilities.firefox();
			FirefoxProfile ffProfile = new FirefoxProfile();
			ffProfile.setPreference("security.mixed_content.block_active_content", false);
			ffProfile.setPreference("security.mixed_content.block_display_content", false);
			ffProfile.setPreference("security.mixed_content.block_object_subrequest", false);
			ffProfile.setPreference("security.mixed_content.upgrade_display_content", true);
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.setProfile(ffProfile);
			desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, ffOptions.setBinary(firefoxBinary));
			driver = new FirefoxDriver(ffOptions);
		}
		if (browserName.toLowerCase().contains("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\\\Selenium\\\\Spring\\\\Projects\\\\PetStore\\\\src\\\\main\\\\resources\\\\drivers\\\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--allow-running-insecure-content");
			driver = new ChromeDriver(chromeOptions);
		}
		driver.manage().window().maximize();
	}

	void highlightElement(WebElement element) throws InterruptedException {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element,
				"color: yellow; border: 5px solid yellow;");
		Thread.sleep(1000);
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
	}

	private void setWebelementText(WebElement elementName, String elementText) {
		try {
			highlightElement(elementName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementName.clear();
		elementName.sendKeys(elementText);
	}

	private void clickWebelementText(WebElement elementName) {
		try {
			highlightElement(elementName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elementName.click();
	}

	private void selectDropdown(WebElement elementName, String sortByCriteria) {
		select = new Select(elementName);
		try {
			highlightElement(elementName);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		select.selectByVisibleText(sortByCriteria);
	}
}
