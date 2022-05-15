package commons;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//Quản lí action trong từng page
public class BasePage {
	public static BasePage getBasePageInstance() {
		return new BasePage();
	}
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
	
	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, longTimeout).until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();;
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();;
	}

	public void sendkeyToAlert(WebDriver driver, String valueToSendKey) {
		waitForAlertPresence(driver).sendKeys(valueToSendKey);;
	}
	
	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}
	
	// chi dung cho duy nhat 2tab/window
	public void swithToWindowById(String expectedId) {
		
	}
	
	public void swithToWindowByTitle(String title) {
		
	}
	
	public boolean closeAllWindowWithoutParent(String parentId) {
		return true;
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
	
	public List<WebElement> getListElements(WebDriver driver, String locator) {
		return driver.findElements(getByXpath(locator));
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
	
	public String getElementAttributeValue(WebDriver driver,String locator, String attributeName ) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementCssValue(WebDriver driver,String locator, String propertyName ) {
		return getWebElement(driver, locator).getCssValue(propertyName);
	}
	
	public int getListElementSize(WebDriver driver, String locator) {
		return getListElements(driver, locator).size();
	}
	
	public void checkToCheckBoxOrRadion(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (!element.isSelected()) {
			element.click();
		 }
	}
	
	public void uncheckToCheckBox(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		if (element.isSelected()) {
			element.click();
		 }
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator) {
		 return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator) {
		 return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementSelected(WebDriver driver, String locator) {
		 return getWebElement(driver, locator).isSelected();
	}
	
	public void selectItemInDefaultDropDown(WebDriver driver, String locator, String itemText) {
		Select select = new Select(getWebElement(driver, locator));
		select.selectByVisibleText(itemText);
	}
	
	public String getFirstSelectedTextItem(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.getFirstSelectedOption().getText();
	}
	
	public boolean isDropdownMultiple(WebDriver driver, String locator) {
		Select select = new Select(getWebElement(driver, locator));
		return select.isMultiple();
	}
	
	public void selectItemInCustomDropDown(WebDriver driver, String parentXpath, String childXpath, String expectedItemText) {
		// 1. Click vào 1 thẻ đại diện cho item cha để xổ ra hết các 
		getWebElement(driver, parentXpath).click();
		sleepInSecond(2);
		
		// 2. Chờ cho các item được hiển thị thành công
		// Chờ cho các item con được  presence  DOM trong vong 30
		// 3. Lấy hết các item ra
		List<WebElement> childItems = new WebDriverWait(driver, longTimeout).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
	
		// 4. Duyệt hết tất cả các item
		for (WebElement tempElement : childItems) {
			// 5. Kiểm tra xem có đúng cái mình cần chọn hay không
			if(tempElement.getText().trim().equals(expectedItemText)) {
				// 6. Nếu đúng cái cần chọn thì click vào
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", tempElement);
				sleepInSecond(1);
				tempElement.click();
				sleepInSecond(1);
				break;
			}
		}
		
		
	}
	
	public void swithToIframe(WebDriver driver, String locator) {
		driver.switchTo().frame(getWebElement(driver, locator));
	}
	
	public void swithToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}
	// lien quan den action
	public void hoverMouseToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.contextClick(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		Actions action = new Actions(driver);
		action.doubleClick(getWebElement(driver, locator)).perform();
	}
	
	public void dragAndDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		Actions action = new Actions(driver);
		action.dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
	}
	
	public void pressKeyToElement(WebDriver driver, String locator, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locator), key).perform();
	}
	// Javascript Excutor
	public void hightlightElement(WebDriver driver, String locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]", element, "border: 2px solid red;");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1]", element, originalStyle);
	}
	
	public void clickToElementByJS(WebDriver driver, String locator) {
		JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
		jsExecutor.executeScript("arguments[0].click()", getWebElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", getWebElement(driver, locator));
	}
	
	public void scrollToElementOnDown(WebDriver driver, String locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false)", getWebElement(driver, locator));
	}
	
	public void sendKeyElementByJS(WebDriver driver, String locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value, '"+ value + "')", getWebElement(driver, locator));
	}
	
	public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('value, '"+ attributeRemove + "')", getWebElement(driver, locator));
	}
	
	public String getElementValidationMessage(WebDriver driver, String locator, String attributeRemove) {
		return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
	}
	
	public boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean)((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined'", getWebElement(driver, locator));

		return status ? true : false;
	}
	
	public void waitForElementVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitForElementInVisible(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator) {
		new WebDriverWait(driver, longTimeout).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
	}
	
	
	public void sleepInSecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	private int longTimeout = 30;
	private int shortTimeout = 5;
}
