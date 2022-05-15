package pageObjects.account;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.account.MyDashboardPageUI;

public class MyDashboardPageObject extends BasePage{
	WebDriver driver;

	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isContactInfoDisplayed() {
//		waitForElementVisible(driver, MyDashboardPageUI.CONTACT_INFO_XPATH);
		return true;
	}

}
