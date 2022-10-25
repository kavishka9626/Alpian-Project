package com.testAutomation.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.testAutomation.qa.base.TestBase;

public class pg_DetailPage extends TestBase {
	// Object Repository for Computer detail Page:

	
	// Section header id
	public WebElement lbl_SectionHeader(String header) {
		return driver.findElement(By.xpath("//section[@id='main']/h1[text()='" + header + "']"));
	}
	
	//Button by value
	public WebElement btn_CreateOrEdit(String btnName) {
		return driver.findElement(By.xpath("//*[@value='" + btnName + "']"));
	}

	

	public pg_DetailPage() {

		PageFactory.initElements(driver, this);

	}


}
