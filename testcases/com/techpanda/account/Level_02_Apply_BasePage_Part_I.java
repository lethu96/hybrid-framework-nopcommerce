package com.techpanda.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import commons.BasePage;

public class Level_02_Apply_BasePage_Part_I {
	WebDriver driver;
	BasePage basePage;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		basePage = new BasePage();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}
	
	@BeforeMethod
	public void beforeMethod() {
		basePage.openPageUrl(driver, "https://live.techpanda.org/");
		basePage.clickToElement(driver, "//div[@class='footer']//a[text()='My Account']");
	}
	
	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		basePage.sendKeyToElement(driver, "//input[@id='email']", "");
		basePage.sendKeyToElement(driver, "//input[@id='pass']", "");
		basePage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-required-entry-email']"), "This is a required field.");
		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-required-entry-pass']"), "This is a required field.");
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		basePage.sendKeyToElement(driver, "//input[@id='email']", "1234@12345");
		basePage.sendKeyToElement(driver, "//input[@id='pass']", "1234567");
		basePage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-validate-email-email']"), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		basePage.sendKeyToElement(driver, "//input[@id='email']", "lethucnt@gmail.com");
		basePage.sendKeyToElement(driver, "//input[@id='pass']", "123456");
		basePage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//li[@class='error-msg']//ul/li/span"), "Invalid login or password.");
		
	}
	
	@Test
	public void TC_04_LoginWithInvalidPassword() {
		basePage.sendKeyToElement(driver, "//input[@id='email']", "lethucntt1@gmail.com");
		basePage.sendKeyToElement(driver, "//input[@id='pass']", randomNumber()+ "");
		basePage.clickToElement(driver, "//button[@id='send2']");
		
		Assert.assertEquals(basePage.getElementText(driver, "//div[@id='advice-validate-password-pass']"), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		basePage.sendKeyToElement(driver, "//input[@id='email']", "lethucntt1@gmail.com");
		basePage.sendKeyToElement(driver, "//input[@id='pass']", "1234567");
		basePage.clickToElement(driver, "//button[@id='send2']");

		Assert.assertEquals(basePage.getElementText(driver, "//li[@class='error-msg']//ul/li/span"), "Invalid login or password.");
	}
	
	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		basePage.sendKeyToElement(driver, "//input[@id='email']", "lethucntt1@gmail.com");
		basePage.sendKeyToElement(driver, "//input[@id='pass']", "123456");
		basePage.clickToElement(driver, "//button[@id='send2']");

		Assert.assertTrue(basePage.isElementDisplayed(driver, "//h3[text()='Contact Information']"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	private int randomNumber() {
		Random rand = new Random();
		
		return rand.nextInt(9999);
	}	
}
