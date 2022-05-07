package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

//Quản lí action trong từng page
public class BasePage {
	/* Web browser */
	// 1. Access Modifier: public
	// 2. Kieu tra ve cua ham
	// 2.1 - void: Action (click/clear/sendkey/open..)
	// 2.2 Lay du lieu ra: string/int..
	// getXX 
	// 3 Ten ham
	// 3.1 tính năng này để làm gì -> tên
	// 3.2 Convention (Camel case)
	// 4 - Tham so truyen vao
	// Khai bao 1 bien ben trong: Kieu du lieu - ten du lieu: String addressName..
	// Kieu du lieu tra ve trong ham - tuong ung voi kieu tra ve cua ham
	// 5.1 void: ko can return
	// 5.2 khac void thi return dung capacity
	
	// Note:
	// 1 - Tham so bat buoc cua 1 ham tuong tac vs Web Brower
	
	/**
	 * Open any page Url
	 * @author Thu chan
	 * @param driver
	 * @param pageUrl
	 */
	public void openPageUrl(WebDriver driver, String pageUrl ) {
		driver.get(pageUrl);
	}
	
	/**
	 * Get page Url
	 * @param driver
	 * @return
	 */
	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();;
	}
	
	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();;
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();;
	}

	public void sendkeyToAlert(WebDriver driver, String valueToSendKey) {
		driver.switchTo().alert().sendKeys(valueToSendKey);;
	}
	
	public String getAlertText(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}
	
	/* Handle WebElement*/
	// Note 
	// 1 - Tham so dau tien la driver
	// 2.Khong phai class nao cung khoi tao driver
	// 2. Tham so tuw 2 bat buoc tuong tac vs element la locator
	// Locator: Thao tac voi element nao
	// Xpath, css, id, name
	
	public By getByXpath(String locator) {
		return By.xpath(locator);
	}
	
	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByXpath(locator));
	}
	
	public void clickToElement(WebDriver driver,String locator ) {
		getWebElement(driver, locator).click();
	}
	
	public void sendKeyToElement(WebDriver driver, String locator, String valueToSendKey ) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(valueToSendKey);
	}
	
	public String getElementText(WebDriver driver,String locator ) {
		return getWebElement(driver, locator).getText();
	}
	
	public void selectItemInDefaultDropDown(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
}
