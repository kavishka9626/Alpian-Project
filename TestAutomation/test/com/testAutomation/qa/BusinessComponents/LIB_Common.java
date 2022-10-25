package com.testAutomation.qa.BusinessComponents;

import com.testAutomation.qa.Pages.pg_Common;
import com.testAutomation.qa.Pages.pg_DashBoard;

public class LIB_Common extends pg_DashBoard {

	
	public void bc_FilterValue(String computerName) throws InterruptedException {
		writeToReport("Start of bc_FilterValue");
		pg_Common common = new pg_Common();

		// Fill computer name
		type(common.txt_CommonById("searchbox"), computerName);
		
		//Click Filter by name button
		click(common.btn_CommonValue("Filter by name"));
		
		writeToReport("End of bc_FilterValue");
	}
	
	
	public void bc_VerifyFirstRowValue(String computerName) throws InterruptedException {
		
		writeToReport("Start of bc_VerifyFirstRowValue");
		
		pg_DashBoard dashboard= new pg_DashBoard();
		
		// Verify first row data
		isCheckElementPresent(dashboard.ele_FirstRow(computerName));
		
		
		writeToReport("End of bc_VerifyFirstRowValue");
	}
	
	public void bc_ClickFirstRowValue(String computerName) throws InterruptedException {
		
		writeToReport("Start of bc_ClickFirstRowValue");
		
		pg_DashBoard dashboard= new pg_DashBoard();
		
		// Click first row data
		click(dashboard.ele_FirstRow(computerName));
		
		writeToReport("End of bc_ClickFirstRowValue");
	}
	


}
