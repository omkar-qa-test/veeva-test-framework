package org.veeva.report.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.veeva.config.AppConstants;

// Manages report files - cleans old ones and archives existing reports
public class ReportManager {

	// Main method to clean and archive test reports
	public static void cleanAndArchiveReports() {
		// Define all the important folder paths
		//String cucumberReportsPath = AppConstants"target/cucumber-reports";  // Where JSON reports are stored
		//String existingReportsPath = "target/test-classes/reports/cucumberreports";  // Current HTML reports
		String archiveBasePath = "target/archived-reports";  // Where we'll save old reports

		// Create timestamp for archive folder name
		String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
		String archivePath = archiveBasePath + "/report-" + timeStamp;  // Full archive path

		try {
			// 1. Remove old JSON report files
			deleteJsonFiles(AppConstants.CUCUMBER_JSON_REPORT_PATH);

			// 2. Create folders for archiving if they don't exist
			createDirectory(archiveBasePath);
			createDirectory(archivePath);

			// 3. Move current HTML reports to archive folder
			moveFilesToArchive(AppConstants.CUCUMBER_FINAL_HTML_REPORT_PATH, AppConstants.CUCUMBER_ARCHIVE_REPORTS_PATH);

		} catch (IOException e) {
			System.err.println("Error during report cleanup and archiving: " + e.getMessage());
		}
	}

	// Deletes all JSON files from given folder
	private static void deleteJsonFiles(String directoryPath) throws IOException {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			return;  // Do nothing if folder doesn't exist
		}

		// Delete each JSON file in the folder
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().toLowerCase().endsWith(".json")) {
					if (!file.delete()) {
						System.err.println("Failed to delete file: " + file.getAbsolutePath());
					}
				}
			}
		}
	}

	// Creates a folder if it doesn't exist
	private static void createDirectory(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			if (!dir.mkdirs()) {
				System.err.println("Failed to create directory: " + path);
			}
		}
	}

	// Moves files from source to target folder
	private static void moveFilesToArchive(String sourceDir, String targetDir) throws IOException {
		File sourceDirectory = new File(sourceDir);
		if (!sourceDirectory.exists()) {
			return;  // Do nothing if source folder doesn't exist
		}

		// Move each file to archive folder
		File[] files = sourceDirectory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isFile()) {
					Path sourcePath = file.toPath();
					Path targetPath = Paths.get(targetDir, file.getName());
					Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
				}
			}
		}
	}


}