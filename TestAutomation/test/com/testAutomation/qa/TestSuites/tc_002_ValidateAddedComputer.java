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

public class tc_002_ValidateAddedComputer extends TestBase {

	LIB_Common LIB_Common;
	LIB_Detail LIB_Detail;
	public String filePath = System.getProperty("user.dir") +"/dataTable/Datatable.xlsx";
	public String sheetName = "dt_002";

	public tc_002_ValidateAddedComputer() {
		super();
	}
	
	@DataProvider(name = "readExcel")
	public Object[][] readExcel() throws InvalidFormatException, IOException {

		return TestBase.readExcel(filePath, sheetName);
	}

	@BeforeMethod

	public void setUp() {
		Initialization();
		LIB_Common = new LIB_Common();
	}

	@Test(dataProvider = "readExcel")
	public void ValidateAddedComputer(String computerName, String introduced,String discontinued,String company) throws InterruptedException {
		//Verify whether added record details added successfully
			
		//Start of tc_002_ValidateAddedComputer
		writeToReport("Start of - tc_002_ValidateAddedComputer");
		
		//Filter data
		LIB_Common.bc_FilterValue(computerName);
		
		//Verify computer name
		LIB_Common.bc_VerifyFirstRowValue(computerName);
		
		//Click on added record
		LIB_Common.bc_ClickFirstRowValue(computerName);
		
		//Validate added record details
		LIB_Detail.bc_VerifyComputerDetails(computerName, computerName, computerName, computerName, computerName);
				
		//End of tc_002_ValidateAddedComputer
		writeToReport("End of - tc_002_ValidateAddedComputer");
	}

	@AfterMethod
	public void end() {
		driver.close();
		driver.quit();
	}
}
