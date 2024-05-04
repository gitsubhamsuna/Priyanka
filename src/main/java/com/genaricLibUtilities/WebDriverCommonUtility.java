package com.genaricLibUtilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class WebDriverCommonUtility{
	WebDriver driver;
	/**
	 * 
	 * @param driver
	 */
	public WebDriverCommonUtility(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * this method is used for commonUtility WebDriverWait
	 * 
	 * @param time
	 * @return
	 */
	public WebDriverWait callWebDriverWait(long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
		return wait;
	}

	/**
	 * this method is used for commonUtility Select
	 * 
	 * @param element
	 * @return
	 */
	public Select callSelect(WebElement element) {
		Select select = new Select(element);
		return select;
	}

	/**
	 * this method is used for commonUtility Actions
	 * 
	 * @return
	 */
	public Actions callActions() {
		Actions act = new Actions(driver);
		return act;
	}
	public void closeBrowser() {
		driver.close();
	}
	public void quitBrowser() {
		driver.quit();
	}
	/**
	 * this method is used to click the element 
	 * @param locator
	 */
	public void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	
	public WebElement driverFindElement(By locator) {
		return driver.findElement(locator);
	}
	
	/**
	 * this method is used only send the key for the webelement
	 * @param locator
	 * @param value
	 */
	public void sendKeysValue(By locator,String value) {
		driver.findElement(locator).sendKeys(value);
	}
	/**
	 * this method is used to maximize the browser
	 */
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * this method is used for minimize the webpage
	 */
	public void minimizeBrowser() {
		driver.manage().window().minimize();
	}

	/**
	 * this method is used for fullscreen of the webpage
	 */
	public void fullscreenBrowser() {
		driver.manage().window().fullscreen();
	}
	
	/**
	 * this method is used to access the web application
	 * @param url
	 */
	public void getBrowser(String url) {
		driver.get(url);
	}

	/**
	 * this method is used for refresh the webpage
	 */
	public void refreshWebPage() {
		driver.navigate().refresh();
	}

	/**
	 * this method is used for implicity wait
	 * 
	 * @param sec
	 */
	public void waitforImplicity(long time) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
	}

	/**
	 * this method is used for pageload wait
	 * 
	 * @param time
	 */
	public void waitforPageload(long time) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(time));
	}

	/**
	 * this method is used to wait for element to be clickable
	 * 
	 * @param time
	 * @param element
	 */
	public void waitforElementClickable(long time, WebElement element) {
		callWebDriverWait(time).until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * this method is used to wait for element to be Selected
	 * 
	 * @param time
	 * @param element
	 */
	public void waitforElementSelected(long time, WebElement element) {
		callWebDriverWait(time).until(ExpectedConditions.elementToBeSelected(element));
	}

	/**
	 * this method is used to wait for switching the frame by name and id attribute
	 * value(in a from of String)
	 * 
	 * @param time
	 * @param locator
	 */
	public void waitforFrameSwitch(long time, String locator) {
		callWebDriverWait(time).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	/**
	 * this method is used to wait for switching the frame by WebElement
	 * 
	 * @param time
	 * @param element
	 */
	public void waitforFrameSwitch(long time, WebElement element) {
		callWebDriverWait(time).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	/**
	 * this method is used for Javascript
	 * 
	 * @param time
	 * @param jsScript
	 */
	public void waitforJavascript(long time, String jsScript) {
		callWebDriverWait(time).until(ExpectedConditions.javaScriptThrowsNoExceptions(jsScript));
	}

	/**
	 * this method is used to wiat for Element is presence or not
	 * 
	 * @param time
	 * @param locator
	 */
	public void waitforPresenceofElement(long time, By locator) {
		callWebDriverWait(time).until(ExpectedConditions.presenceOfElementLocated(locator));

	}

	/**
	 * this method is used to wait fot title
	 * 
	 * @param time
	 * @param locator
	 */
	public void waitforTitle(long time, String title) {

		callWebDriverWait(time).until(ExpectedConditions.titleIs(title));
	}

	/**
	 * this method is used for visibility of element
	 * 
	 * @param time
	 * @param locator
	 */
	public void waitforVisibilityOfElement(long time, WebElement element) {
		callWebDriverWait(time).until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * this method is select by Visible Text
	 * @param element
	 * @param text
	 */
	public void selectDropdown(WebElement element, String text) {
		callSelect(element).selectByVisibleText(text);
	}
	/**
	 * this method is deselect by Visible Text
	 * @param element
	 * @param text
	 */
	public void deselectDropdown(WebElement element, String text) {
		callSelect(element).deselectByVisibleText(text);
	}
	/**
	 * this method is select by Value
	 * @param value
	 * @param element
	 */
	public void selectDropdown(String value,WebElement element) {
		callSelect(element).selectByValue(value);
	}
	/**
	 * this method is deselect by Value
	 * @param value
	 * @param element
	 */
	public void deselectDropdown(String value,WebElement element) {
		callSelect(element).deselectByValue(value);
	}
	/**
	 * this method is select by index
	 * @param element
	 * @param index
	 */
	public void selectDropdown(WebElement element, int index) {
		callSelect(element).selectByIndex(index);
	}
	/**
	 * this method is deselect by index
	 * @param element
	 * @param index
	 */
	public void deselectDropdownByindex(WebElement element, int index) {
		callSelect(element).deselectByIndex(index);
	}
	
	/**
	 * this method is used to select the multiple values
	 * @param element
	 */
	public void selectDropdown(WebElement element) {
		callSelect(element).isMultiple();
	}

	/**
	 * this method is used to return all options are there in dropdown
	 * 
	 * @param element
	 */
	public void selectDropdownByGetOptions(WebElement element) {
		callSelect(element).getOptions();
	}

	public void selectDropdownByAllSelectedoptions(WebElement element) {
		callSelect(element).getAllSelectedOptions();
	}

	/**
	 * this method is used for mouse over action
	 * 
	 * @param element
	 */
	public void mouseOver(WebElement element) {
		callActions().moveToElement(element).perform();
	}
	
	public void mouseOverAndClick(WebElement element) {
		callActions().moveToElement(element).click().perform();
	}

	/**
	 * this method is used to right click operation in a webpage anywhere
	 */
	public void rightClickActions() {
		callActions().contextClick().perform();
	}

	/**
	 * this method is used to right click operation in a paticular webelement
	 * 
	 * @param element
	 */
	public void rightClickActions(WebElement element) {
		callActions().contextClick(element).perform();
	}

	/**
	 * this method is used to click on the paticular webelement
	 * 
	 * @param element
	 */
	public void clickOperation(WebElement element) {
		callActions().click(element).perform();
	}

	/**
	 * this method is used for drag and drop action for one element(source) to
	 * another element(target)
	 */
	public void dragAndDropOperation(WebElement source, WebElement target) {
		callActions().dragAndDrop(source, target).build().perform();
	}

	/**
	 * this method is use for pass the value for the paticular webelement
	 * 
	 * @param element
	 * @param value
	 */
	public void sendValueOperation(WebElement element, String value) {
		callActions().click(element).sendKeys(value).build().perform();
	}

	/**
	 * this method is used to take the screenshot of the webpage
	 * 
	 * @param screenshotName
	 * @return 
	 * @throws IOException
	 */
	public  static String screenshotWebpage(WebDriver driver,String screenshotName) throws IOException {
		File sourcefile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File destFile=new File(IpathConstant.SCREENSHOT_FILE_PATH  + screenshotName + ".png");
//		String destpath=IpathConstant.SCREENSHOT_FILE_PATH  + screenshotName + ".png";
		Files.copy(sourcefile, destFile);
		return destFile.getAbsolutePath();
	}

	/**
	 * this method is used to take the screenshot of the webelement
	 * 
	 * @param element
	 * @param screenshotName
	 * @throws IOException
	 */
	public static void screenshotOfWebElement(WebElement element, String screenshotName) throws IOException {
		JavaUtilities ju=new JavaUtilities();
		File sourcefile = element.getScreenshotAs(OutputType.FILE);
		Files.copy(sourcefile, new File(IpathConstant.SCREENSHOT_FILE_PATH  + screenshotName +".png"));
	}
	/**
	 * this method is used to switch the frame by frame index no.
	 * @param frameIndex
	 */
	public void switchToFrame(int frameIndex) {
		driver.switchTo().frame(frameIndex);
	}
	/**
	 * this method is used to switch the frame by name or id value
	 * @param nameOrId
	 */
	public void switchToFrame(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	/**
	 * this method is used to switch the frame by WebElement
	 * @param element
	 */
	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	/**
	 * this method is used to return the parent frame
	 */
	public void parentFrame() {
		driver.switchTo().defaultContent();
	}
	
	public void popupHandle(String exptMessage) {
		Alert alt=driver.switchTo().alert();
		if(alt.getText().contains(exptMessage)) {
			alt.accept();
		}
	}	
	/**
	 * this method is return the Popup Text
	 * @return
	 */
	public String getTextPopup() {
		Alert alt=driver.switchTo().alert();
		String msg=alt.getText();
		return msg;
	}	
}
