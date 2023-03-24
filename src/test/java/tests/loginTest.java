package tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageobjects.AccountPage;
import pageobjects.LandingPage;
import pageobjects.LoginPage;
import resources.Base;

public class loginTest extends Base {
	WebDriver driver;

	@Test(dataProvider = "getLoginData")
	public void login(String email, String password, String expectedResult) throws IOException, InterruptedException {

		LandingPage landingPage = new LandingPage(driver);
		landingPage.myAccountDropdown().click();
		landingPage.loginOption().click();

		Thread.sleep(3000);

		LoginPage loginPage = new LoginPage(driver);
		loginPage.emailAddress().sendKeys(email);
		loginPage.password().sendKeys(password);
		loginPage.login().click();

		AccountPage accountPage = new AccountPage(driver);

		String acutualResult = null;

		try {

			if (accountPage.editAccountLink().isDisplayed()) {
				acutualResult = "Success";
			}

		} catch (Exception e) {

			acutualResult = "Failure";

		}
		Thread.sleep(2000);

		Assert.assertEquals(acutualResult, expectedResult);

	}

	@BeforeMethod
	public void openUp() throws IOException, InterruptedException {
		driver = initializeBrowser();
		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}

	@DataProvider
	public Object[][] getLoginData() {

		Object[][] data = { { "jesine@netiapps.com", "123456", "Success" },
				{ "dummy@gmail.com", "123456", "Failure" } };

		return data;
	}
}
