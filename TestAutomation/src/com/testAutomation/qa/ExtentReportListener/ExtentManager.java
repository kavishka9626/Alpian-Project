package com.testAutomation.qa.ExtentReportListener;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;

	public static ExtentReports createInstance() {
		String fileName = getReportName();
		String directory = System.getProperty("user.dir") + "\\reports\\";
		new File(directory).mkdirs();
		String path = directory + fileName;
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(path);

		// Create Extent Report Instance
		// htmlReporter = new ExtentHtmlReporter("./reports/"+fileName);
		// Set Extent Report Configuration
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.setSystemInfo("Organization", "Computer database");
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Author", "Kavishka W.");
		extent.attachReporter(htmlReporter);

		return extent;
	}

	public static String getReportName() {
		Date d = new Date();
		String fileName = "AutomationReport_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";
		return fileName;
	}
}
