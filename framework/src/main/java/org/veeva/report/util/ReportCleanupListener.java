package org.veeva.report.util;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

// Cleans up old reports before new test execution begins
public class ReportCleanupListener implements IInvokedMethodListener {

	// Tracks if cleanup has already been done
	private static volatile boolean initialized = false;

	// Lock object for thread safety
	private static final Object LOCK = new Object();

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		// Only run for setup/configuration methods (not test methods)
		if (method.isConfigurationMethod()) {
			synchronized (LOCK) {
				// Make sure cleanup only happens once
				if (!initialized) {
					// Remove old reports and archive them
					ReportManager.cleanAndArchiveReports();
					initialized = true; // Mark as done
				}
			}
		}
	}
}