package com.techpanda.account;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObjects.account.HomePageObject;
import pageObjects.account.LoginPageObject;
import pageObjects.account.MyDashboardPageObject;

public class Level_03_Base_Objec_Pattern {
	WebDriver driver;
	HomePageObject homePage;
	LoginPageObject loginPage;
	MyDashboardPageObject myDashboardPage;
	
	private String requiredErrorMessage = "This is a required field.";
	private String invalidEmailErrorMessage = "Please enter a valid email address. For example johndoe@domain.com.";
	private String invalidPasswordErrorMessage = "Please enter 6 or more characters without leading or trailing spaces.";
	private String incorrectEmailOrPasswordErrorMessage =  "Invalid login or password.";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		homePage = new HomePageObject(driver);
		loginPage = new LoginPageObject(driver);
		myDashboardPage = new MyDashboardPageObject(driver);
	}

	@BeforeMethod
	public void beforeMethod() {
		homePage.clickToMyAccount();
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		loginPage.inputToEmailTextbox("");
		loginPage.inputToPasswordTextbox("");
		loginPage.clicktoLoginButton();

		assertEquals(loginPage.getEmailEmptyErrorMessage(), requiredErrorMessage);
		assertEquals(loginPage.getPasswordEmptyErrorMessage(), requiredErrorMessage);
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		loginPage.inputToEmailTextbox("1234@12345.124");
		loginPage.inputToPasswordTextbox("1234567");
		loginPage.clicktoLoginButton();

		assertEquals(loginPage.getEmailInvalidErrorMessage(), invalidEmailErrorMessage);
	}

	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		loginPage.inputToEmailTextbox("lethucnt" + randomNumber() + "@gmail.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clicktoLoginButton();

		assertEquals(loginPage.getEmailOrPasswordIncorrectErrorMessage(), incorrectEmailOrPasswordErrorMessage);
	}

	@Test
	public void TC_04_LoginWithInvalidPassword() {
		loginPage.inputToEmailTextbox("lethucnt" + randomNumber() + "@gmail.com");
		loginPage.inputToPasswordTextbox(randomNumber() + "");
		loginPage.clicktoLoginButton();

		assertEquals(loginPage.getPasswordInvalidErrorMessage(), invalidPasswordErrorMessage);
	}

	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		loginPage.inputToEmailTextbox("lethucntt1@gmail.com");
		loginPage.inputToPasswordTextbox("1234567");
		loginPage.clicktoLoginButton();
		assertEquals(loginPage.getEmailOrPasswordIncorrectErrorMessage(), incorrectEmailOrPasswordErrorMessage);
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		loginPage.inputToEmailTextbox("lethucntt1@gmail.com");
		loginPage.inputToPasswordTextbox("123456");
		loginPage.clicktoLoginButton();

		assertTrue(myDashboardPage.isContactInfoDisplayed());
	}

	@AfterClass

	private int randomNumber() {
		Random rand = new Random();

		return rand.nextInt(9999);
	}
}
