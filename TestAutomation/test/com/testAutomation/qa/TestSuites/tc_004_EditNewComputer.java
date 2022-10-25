package com.testAutomation.qa.TestSuites;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.testAutomation.qa.BusinessComponents.LIB_Common;
import com.testAutomation.qa.BusinessComponents.LIB_Detail;
import com.testAutomation.qa.base.TestBase;

public class tc_004_EditNewComputer extends TestBase {

	LIB_Detail LIB_Detail;
	LIB_Common LIB_Common;
	public String filePath = System.getProperty("user.dir") +"/dataTable/Datatable.xlsx";
	public String sheetName = "dt_004";

	public tc_004_EditNewComputer() {
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
	public void AddNewComputer(String computerNameBefore,String computerName, String introduced,String discontinued,String company,String btnName) throws InterruptedException {
		//Verify user can add new computer to the system
		
		//Start of tc_004_EditNewComputer
		writeToReport("Start of - tc_004_EditNewComputer");
		
		//Filter data by computer name
		LIB_Common.bc_FilterValue(computerNameBefore);
		
		//Click on record
		LIB_Common.bc_ClickFirstRowValue(computerName);
		
		//Fill Add New computer
		LIB_Detail.bc_FillAddNewForm(computerName, introduced, discontinued, company,btnName);
		
		//End of tc_004_EditNewComputer
		writeToReport("End of - tc_004_EditNewComputer");
	}

	@AfterMethod
	public void end() {
		driver.close();
		driver.quit();
	}
}
