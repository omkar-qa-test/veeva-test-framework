package org.veeva.report.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.veeva.config.AppConstants;

public class BrowserParameterListener implements ITestListener {


	// Runs when the test starts
	public void onStart(ITestContext context) {
		// Get browser name from test configuration
		String browser = context.getCurrentXmlTest().getParameter("browser");
		AppConstants.PROVIDED_BROWSER =browser;
		if (browser == null){
			browser = System.getProperty("browser", AppConstants.DEFAULT_BROWSER);
			AppConstants.PROVIDED_BROWSER =browser;
		}

	}
}