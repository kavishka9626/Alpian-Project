package com.testAutomation.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testAutomation.qa.base.TestBase;

public class pg_Common extends TestBase {
	// Object Repository for Common Page:
	
	@FindBy(xpath = "//a[text()=\"Computer database\"]")
	public WebElement lbl_PageHeader;
	
	
	// Text Box Common by Id
	public WebElement txt_CommonById(String txt_Name) {
		return driver.findElement(By.xpath("//input[@id='" + txt_Name + "']"));
	}

	// Dropdown Common by Id
	public WebElement dd_CommonById(String dd_Name) {
		return driver.findElement(By.xpath("//select[@id='" + dd_Name + "']"));
	}

	// Link Common
	public WebElement lnk_Common(String link_Name) {
		return driver.findElement(By.xpath("//a[text()='" + link_Name + "']"));
	}

	// Link Common Index
		public WebElement lnk_CommonIndex(String link_Name, String link_Index) {
			return driver.findElement(By.xpath("(//a[text()='" + link_Name + "'])["+link_Index+"]"));
		}
		

	// Button Common Text
	public WebElement btn_Commontext(String button_Name) {
		return driver.findElement(By.xpath("//*[text()='" + button_Name + "']"));
	}
	
	// Button Common Value
	public WebElement btn_CommonValue(String button_Name) {
		return driver.findElement(By.xpath("//*[@value='" + button_Name + "']"));
	}
	
	// Button Common Span Text
	public WebElement btn_CommonSpanText(String button_Name) {
		return driver.findElement(By.xpath("//span[text()='" + button_Name + "']"));
	}

	// Alert Message
	public WebElement lbl_AlertInfo(String alert_Meassage) {
		return driver.findElement(By.xpath("//*[text()='" + alert_Meassage + "']"));
	}

	public pg_Common() {

		PageFactory.initElements(driver, this);

	}
}
