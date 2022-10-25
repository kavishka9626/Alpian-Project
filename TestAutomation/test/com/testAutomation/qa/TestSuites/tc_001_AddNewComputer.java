package com.testAutomation.qa.TestSuites;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.testAutomation.qa.BusinessComponents.LIB_Detail;
import com.testAutomation.qa.base.TestBase;

public class tc_001_AddNewComputer extends TestBase {

	LIB_Detail LIB_Detail;
	public String filePath = System.getProperty("user.dir") +"/dataTable/Datatable.xlsx";
	public String sheetName = "dt_001";

	public tc_001_AddNewComputer() {
		super();
	}
	
	@DataProvider(name = "readExcel")
	public Object[][] readExcel() throws InvalidFormatException, IOException {

		return TestBase.readExcel(filePath, sheetName);
	}

	@BeforeMethod

	public void setUp() {
		Initialization();
		LIB_Detail = new LIB_Detail();
	}

	@Test(dataProvider = "readExcel")
	public void AddNewComputer(String computerName, String introduced,String discontinued,String company,String btnName) throws InterruptedException {
		//Verify user can add new computer to the system
		
		//Start of tc_001_AddNewComputer
		writeToReport("Start of - tc_001_AddNewComputer");
		
		//Fill Add New computer
		LIB_Detail.bc_FillAddNewForm(computerName, introduced, discontinued, company,btnName);
		
		//End of tc_001_AddNewComputer
		writeToReport("End of - tc_001_AddNewComputer");
	}

	@AfterMethod
	public void end() {
		driver.close();
		driver.quit();
	}
}
