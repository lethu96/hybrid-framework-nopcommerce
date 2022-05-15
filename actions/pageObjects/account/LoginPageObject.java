package pageObjects.account;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.account.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToEmailTextbox(String emailValue) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_INPUT_XPATH);
		sendKeyToElement(driver, LoginPageUI.EMAIL_INPUT_XPATH, emailValue);
	}

	public void inputToPasswordTextbox(String passwordlValue) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_INPUT_XPATH);
		sendKeyToElement(driver, LoginPageUI.PASSWORD_INPUT_XPATH, passwordlValue);
	}

	public void clicktoLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BTN_XPATH);
		clickToElement(driver, LoginPageUI.LOGIN_BTN_XPATH);
	}

	private String getErrorMessage(String locator) {
//		waitForElementInVisible(driver, locator);
		return getElementText(driver, locator);
	}

	public String getEmailEmptyErrorMessage() {
		return getErrorMessage(LoginPageUI.EMAIL_EMPTY_ERROR_MESSAGE_XPATH);
	}

	public String getPasswordEmptyErrorMessage() {
		return getErrorMessage(LoginPageUI.PASSWORD_EMPTY_ERROR_MESSAGE_XPATH);
	}

	public String getEmailInvalidErrorMessage() {
		return getErrorMessage(LoginPageUI.EMAIL_INVALID_ERROR_MESSAGE_XPATH);
	}

	public String getPasswordInvalidErrorMessage() {
		return getErrorMessage(LoginPageUI.PASSWORD_INVALID_ERROR_MESSAGE_XPATH);
	}

	public String getEmailOrPasswordIncorrectErrorMessage() {
		return getErrorMessage(LoginPageUI.EMAIL_OR_PASSWORD_INCORRECT_ERROR_MESSAGE_XPATH);
	}
	

}
