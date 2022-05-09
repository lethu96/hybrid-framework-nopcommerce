package com.techpanda.account;

import static org.testng.Assert.assertEquals;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Level_01_DRY {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/browserDrivers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://live.techpanda.org/");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.id("email")).sendKeys("123@1245");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_LoginWithIncorrectEmail() {
		driver.findElement(By.id("email")).sendKeys("lethucnt@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//ul/li/span")).getText(), "Invalid login or password.");
		
	}
	
	@Test
	public void TC_04_LoginWithInvalidPassword() {
		driver.findElement(By.id("email")).sendKeys("lethucntt1@gmail.com");
		driver.findElement(By.id("pass")).sendKeys(randomNumber()+ "");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_05_LoginWithIncorrectPassword() {
		driver.findElement(By.id("email")).sendKeys("lethucntt1@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("1234567");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//ul/li/span")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		driver.findElement(By.id("email")).sendKeys("lethucntt1@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']")).isDisplayed());
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
