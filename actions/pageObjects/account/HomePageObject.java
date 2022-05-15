package pageObjects.account;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.account.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToMyAccount() {
		openPageUrl(driver, "https://live.techpanda.org/");
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK_XPATH);
		clickToElement(this.driver, HomePageUI.MY_ACCOUNT_LINK_XPATH);
	}
}
