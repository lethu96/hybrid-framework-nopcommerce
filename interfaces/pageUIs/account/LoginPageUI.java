package pageUIs.account;

public class LoginPageUI {
	public static final String EMAIL_INPUT_XPATH = "//input[@id='email']";
	public static final String PASSWORD_INPUT_XPATH = "//input[@id='pass']";
	public static final String LOGIN_BTN_XPATH = "//button[@id='send2']";
	public static final String EMAIL_EMPTY_ERROR_MESSAGE_XPATH = "//div[@id='advice-required-entry-email']";
	public static final String PASSWORD_EMPTY_ERROR_MESSAGE_XPATH = "//div[@id='advice-required-entry-pass']";
	public static final String EMAIL_INVALID_ERROR_MESSAGE_XPATH = "//div[@id='advice-validate-email-email']";
	public static final String PASSWORD_INVALID_ERROR_MESSAGE_XPATH = "//div[@id='advice-validate-password-pass']";
	public static final String EMAIL_OR_PASSWORD_INCORRECT_ERROR_MESSAGE_XPATH = "//li[@class='error-msg']//ul/li/span";
}
