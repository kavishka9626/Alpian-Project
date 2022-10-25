package com.testAutomation.qa.BusinessComponents;

import com.testAutomation.qa.Pages.pg_Common;
import com.testAutomation.qa.Pages.pg_DetailPage;

public class LIB_Detail extends pg_Common {

	
	public void bc_FillAddNewForm(String computerName,String introduced,String discontinued,String company,String btnName) throws InterruptedException {
		writeToReport("Start of bc_FillAddEditForm");
		pg_Common common = new pg_Common();
		pg_DetailPage detail=new pg_DetailPage();
		
		// Click Add a new computer Button
		click(common.btn_Commontext("Add a new computer"));
		
		//Validate page header
		isCheckElementPresent(detail.lbl_SectionHeader("Add a computer"));
				
		//Fill computer name
		type(common.txt_CommonById("name"), computerName);
		
		//Fill Introduced
		type(common.txt_CommonById("introduced"), introduced);
		
		//Fill Discontinued
		type(common.txt_CommonById("discontinued"), discontinued);
		
		//Select company
		select(common.dd_CommonById("company"), company);
		
		// Click Add/Save a new computer Button
		click(detail.btn_CreateOrEdit(btnName));
		
		writeToReport("End of bc_FillAddNewForm");
	}
	
	public void bc_VerifyComputerDetails(String computerName,String introduced,String discontinued,String company,String btnName) throws InterruptedException {
		writeToReport("Start of bc_VerifyComputerDetails");
		
		pg_Common common = new pg_Common();
		pg_DetailPage detail=new pg_DetailPage();
		
		
		//Validate page header
		isCheckElementPresent(detail.lbl_SectionHeader("Edit computer"));
				
		//Validate computer name
		verifyTextByValue(common.txt_CommonById("name"), computerName);
		
		//Validate Introduced
		verifyTextByValue(common.txt_CommonById("introduced"), introduced);
		
		//Validate Discontinued
		verifyTextByValue(common.txt_CommonById("discontinued"), discontinued);
		
		//Validate company
		verifyTextByValue(common.dd_CommonById("company"), company);
		
		//Click cancel button
		click(common.btn_Commontext("Cancel"));
				
		writeToReport("End of bc_VerifyComputerDetails");
	}


}
