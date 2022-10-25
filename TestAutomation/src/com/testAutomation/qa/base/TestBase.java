/*
 * @autor : Kavishka W.
 * 
 */

package com.testAutomation.qa.base;

import org.testng.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import com.testAutomation.qa.utill.WebEventListener;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	// -------- ConfigFile Read-Start ----------
	public TestBase() {

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/resources/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// -------- ConfigFile Read-End ----------

	// -------- Driver Initialization-Start ----------
	public static void Initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			String currentDir = System.getProperty("user.dir");
			String chromeDriverLocation = currentDir + "/chromeDriver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

			driver = new ChromeDriver();
		}
		// EventListnerHandler initialize
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();// (Not Working For mac)
		// driver.manage().window().fullscreen();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));
	}
	// -------- Driver Initialization-End ----------

	// -------- Commands Declaration-Start --------

	// click
	public static void click(WebElement objectName) {
		try {

			objectName.click();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// Double Click
	public static void doubleClick(WebElement objectName) {
		try {
			Actions act = new Actions(driver);

			// Double click on element
			act.doubleClick(objectName).perform();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// jsClick
	public static void jsClick(WebElement objectName) {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", objectName);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// type
	public static void type(WebElement objectName, String text) {
		try {
			objectName.sendKeys(text);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// jsType
	public static void jsType(WebElement objectName, String text) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].value='" + text + "';", objectName);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// clearText
	public static void clearText(WebElement objectName) {
		try {
			objectName.clear();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// clearAllText
	public static void clearAllText(WebElement objectName) {
		try {
			objectName.sendKeys(Keys.CONTROL + "a");
			objectName.sendKeys(Keys.DELETE);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// select
	public static void select(WebElement objectName, String ddValue) {
		// Select dropdown = new Select(driver.findElement(By.id("testingDropdown")));
		// dropdown.selectByVisibleText("Database Testing");
		try {
			Select dropdown = new Select(objectName);
			dropdown.selectByVisibleText(ddValue);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// sleep
	public static void sleep(int timeout) throws InterruptedException {
		TimeUnit.MILLISECONDS.sleep(timeout);
	}

	// isCheckElementPresent
	public static void isCheckElementPresent(WebElement objectName) {
		try {
			if (objectName.isDisplayed()) {
				System.out.println(objectName + "Found");

			}

			else {
				Assert.fail(objectName + "Unable to be Found");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// isNotCheckElementPresent
	public static void isNotCheckElementPresent(WebElement objectName) {
		try {
			if (objectName.isDisplayed()) {
				Assert.fail(objectName + " Found!");
			}

			else {
				System.out.println(objectName + " not Found");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// openURL
	public static void openURL(String url) {
		driver.get(url);
	}

	// getCurrentUrl
	public static void getCurrentUrl() {
		String url;
		url = driver.getCurrentUrl();
		System.out.println(url);
	}

	// verifyCurrentUrl
	public static void verifyCurrentUrl(String expectedUrl) {

		// Assert.assertEquals(expectedUrl, driver.getCurrentUrl());

		try {
			String currentUrl;
			currentUrl = driver.getCurrentUrl();
			if (currentUrl.equals(expectedUrl)) {
				System.out.println("Current URL " + currentUrl + " & Actual URL " + expectedUrl + " Both are the Same");
			} else {
				Assert.fail("Current URL " + currentUrl + " & Actual URL " + expectedUrl + " are not Matching");
				// System.out.println("Current URL " + currentUrl + " & Actual URL " +
				// expectedUrl + " are not Matching" );
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// getText
	public static void getText(WebElement objectName) {
		String text;
		text = objectName.getText();
		System.out.println(text);
	}

	// verifyText
	public static void verifyText(WebElement objectName, String text) {
		try {
			String objtext;
			objtext = objectName.getText();
			System.out.println(objtext);
			if (objtext.equals(text)) {
				System.out.println(objtext + " and " + text + ", both values are matched");
			} else {
				Assert.fail(objtext + " and " + text + ", values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// verifyTextWithSpaces
	public static void verifyTextWithSpaces(WebElement objectName, String text) {
		try {
			String objtext;
			objtext = objectName.getText();
			String objtextremovespaces = objtext.trim();
			System.out.println(objtextremovespaces);
			if (objtextremovespaces.contains(text)) {
				System.out.println(objtextremovespaces + " and " + text + ", both values are matched");
			} else {
				Assert.fail(objtextremovespaces + " and " + text + ", values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// verifyTextWithSpacesAndIgnoreCase
	public static void verifyTextWithSpacesAndIgnoreCase(WebElement objectName, String text) {
		try {
			String objtext;
			objtext = objectName.getText();
			String objtextremovespaces = objtext.trim();
			System.out.println(objtextremovespaces);
			if (objtextremovespaces.equalsIgnoreCase(text)) {
				System.out.println(objtextremovespaces + " and " + text + ", both values are matched");
			} else {
				Assert.fail(objtextremovespaces + " and " + text + ", values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// verifyTextByValue
	public static void verifyTextByValue(WebElement objectName, String text) {
		try {
			String objtext;
			objtext = objectName.getAttribute("value");
			System.out.println(objtext);
			if (objtext.equals(text)) {
				System.out.println(objtext + " and " + text + ", both values are matched");
			} else {
				Assert.fail(objtext + " and " + text + ", values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// verifyContainsTextByValue
	public static void verifyContainsTextByValue(WebElement objectName, String text) {
		try {
			String objtext;
			objtext = objectName.getAttribute("value");
			System.out.println(objtext);
			if (objtext.contains(text)) {
				System.out.println(objtext + " and " + text + ", both values are matched");
			} else {
				Assert.fail(objtext + " and " + text + ", values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// verifyTextByAnyAttribute
	public static void verifyTextByAnyAttribute(WebElement objectName, String text, String attribute_Name) {
		try {
			String objtext;
			objtext = objectName.getAttribute(attribute_Name);
			System.out.println(objtext);
			if (objtext.equalsIgnoreCase(text)) {
				System.out.println(objtext + " and " + text + ", both values are matched");
			} else {
				Assert.fail(objtext + " and " + text + ", values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// verifySelectedValue
	public static void verifySelectedValue(WebElement objectName, String text) {
//			Select select = new Select(driver.findElement(By.xpath("//select")));
//			WebElement option = select.getFirstSelectedOption();
//			String defaultItem = option.getText();
		try {
			WebElement selectVal;
			String objtext;
			Select select = new Select(objectName);
			selectVal = select.getFirstSelectedOption();
			objtext = selectVal.getText();
			System.out.println(objtext);
			if (objtext.equals(text)) {
				System.out.println(objtext + " and " + text + ", both values are matched");
			} else {
				Assert.fail(objtext + " and " + text + ", values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}

	}

	// verifyContainsText
	public static void verifyContainsText(WebElement objectName, String text) {
		try {
			String objtext;
			String[] message;
			objtext = objectName.getText();

			if (objtext.contains("�")) {
				message = objtext.split("\n");
				System.out.println(message[0]);
				if (message[0].contains(text)) {
					System.out.println(message[0] + " and " + text + " both values are matched");
				} else {
					Assert.fail(message[0] + " and " + text + " values are not matched");
				}
			} else if (objtext.contains(text)) {
				System.out.println(objtext + " and " + text + " both values are matched");
			} else {
				Assert.fail(objtext + " and " + text + " values are not matched");
			}
		} catch (Exception e) {
			System.out.println(e);
			Assert.fail(e.toString());
		}
	}

	// pageRefresh
	public static void pageRefresh() throws InterruptedException {
		driver.navigate().refresh();
		// driver.navigate().to(driver.getCurrentUrl());
	}

	// pause
	public static void pause(int waittime) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(waittime, TimeUnit.MILLISECONDS);
	}

	// writeToReport
	public static void writeToReport(String text) {
		System.out.println(text);
	}

	// switchToWindow
	public static void switchToWindow(int tabIndex) {

		try {
			ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(newTab.get(tabIndex));
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// switchToFrameByIndex
	public static void switchToFrameByIndex(int frameIndex) {

		try {
			driver.switchTo().frame(frameIndex);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// switchToFrameByNameOrId
	public static void switchToFrameByNameOrId(String frameNameOrId) {

		try {
			driver.switchTo().frame(frameNameOrId);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// switchToDefaultFrame
	public static void switchToDefaultFrame() {

		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// closeWindow
	public static void closeWindow() {

		try {
			driver.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// isFileDownloaded
	public boolean isFileDownloaded(String downloadPath, String fileName) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();

		for (int i = 0; i < dirContents.length; i++) {
			if (dirContents[i].getName().equals(fileName)) {
				// File has been found, it can now be deleted:
				dirContents[i].delete();
				return true;
			}
		}
		return false;
	}
	// -------- Commands Declaration-End --------

	// -------- Read Data Sheet-Start --------
	public static Object[][] readExcel(String filePath, String sheetName) throws IOException {
		FileInputStream file = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(file);
		XSSFSheet sheet = wb.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		int column = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowCount][column];

		for (int i = 1; i <= rowCount; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < column; j++) {
				XSSFCell cell = row.getCell(j);
				DataFormatter formatter = new DataFormatter();
				String val = formatter.formatCellValue(cell);
				data[i - 1][j] = val;
			}

		}

		return data;
	}
	// -------- Read Data Sheet-End --------

}