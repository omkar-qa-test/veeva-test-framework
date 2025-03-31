package org.veeva.config;

import java.io.File;

public class AppConstants {
    // Timeouts
    public static final int IMPLICIT_WAIT = 10;
    public static final int EXPLICIT_WAIT = 10;
    public static final int PAGE_LOAD_TIMEOUT = 10;
    
   
    
    public static final String DEFAULT_CONFIG_PROPERTY_FILE_PATH ="src/main/resources/config/config.properties";
    
    // URLs
    public static final String URL_CP = "https://www.nba.com/warriors";
    public static final String URL_DP1 = "https://www.nba.com/sixers";
    public static final String URL_DP2 = "https://www.nba.com/bulls";
    
    
    // Browsers
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static final String EDGE = "edge";
	public static final String DEFAULT_BROWSER = "chrome";
	public static  String PROVIDED_BROWSER = "";
	
	
	//Report Path
	public static final String CUCUMBER_JSON_REPORT_PATH = "target/cucumber-reports/";
	public static final String CUCUMBER_FINAL_HTML_REPORT_PATH = "target/cucumber-reports/"; //
	//public static final String CUCUMBER_FINAL_HTML_REPORT_PATH = "target/test-classes/reports/cucumberreports/"; //cucumber-html-reports
	public static final String CUCUMBER_ARCHIVE_REPORTS_PATH = "target/archived-reports"; 
	 	
	
}