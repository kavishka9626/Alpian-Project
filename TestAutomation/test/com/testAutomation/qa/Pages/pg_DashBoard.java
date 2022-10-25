package com.testAutomation.qa.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.testAutomation.qa.base.TestBase;

public class pg_DashBoard extends TestBase {

	// Object Repository for Dash Board Page:
	
	// Search Box
	@FindBy(id =  "searchbox")
	public WebElement txt_Searchbox;
	
	// Search Button
	@FindBy(id = "searchsubmit")
	public WebElement btn_Search;
	
	
	//Button by value
	public WebElement ele_FirstRow(String computerName) {
		return driver.findElement(By.xpath("//table[@class='computers zebra-striped']//tbody//tr[1]//a[text()='" + computerName + "']"));
	}
	
	
	
	public pg_DashBoard() {

		PageFactory.initElements(driver, this);

	}
}
