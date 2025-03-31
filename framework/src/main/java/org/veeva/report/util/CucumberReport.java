package org.veeva.report.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.log4j.Logger;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.veeva.config.AppConstants;

// Handles generation of Cucumber reports after test execution
public class CucumberReport implements ISuiteListener {

	// Logger for tracking report generation status
	public final Logger oLog = LoggerHelper.getLogger(CucumberReport.class);

	@Override
	public void onStart(ISuite suite) {
		// Not used in this implementation
	}

	@Override
	public void onFinish(ISuite suite) {
		try {
			// Folder where Cucumber JSON results are stored
			File jsonfile = new File(AppConstants.CUCUMBER_JSON_REPORT_PATH);

			// Folder where HTML reports will be generated
			File reportOutputDirectory = new File(AppConstants.CUCUMBER_FINAL_HTML_REPORT_PATH);

			// Find all JSON files in the results folder
			String[] fileNames = jsonfile.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					return name.endsWith(".json"); // Only accept JSON files
				}
			});

			// Create full paths for each JSON file
			for (int i = 0; i < fileNames.length; i++) {
				fileNames[i] = jsonfile.getAbsolutePath() + "/" + fileNames[i];
			}

			// Convert array to list for report builder
			List<String> jsonFiles = Arrays.asList(fileNames);

			// Configure the report settings
			Configuration configuration = new Configuration(reportOutputDirectory, suite.getName());
			configuration.setStatusFlags(true, true, true); // Show all status flags

			// Generate and save the HTML report
			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			reportBuilder.generateReports();

			// Log where the report was saved
			oLog.info("Report Generated : " + configuration.getReportDirectory());

		} catch (Exception e) {
			oLog.error("Failed to generate report", e); // Fixed: changed equals to error logging
		}
	}
}