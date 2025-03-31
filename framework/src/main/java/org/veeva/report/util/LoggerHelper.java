package org.veeva.report.util;


import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;


@SuppressWarnings("rawtypes")
public class LoggerHelper {

	private static boolean root = false;

	public static Logger getLogger(Class clas) {
		if(root)
			return Logger.getLogger(clas);

		PropertyConfigurator.configure(
				getResourcePath("configfile/log4j.properties"));
		root = true;
		return Logger.getLogger(clas);
	}

	public static String getResourcePath(String resource) {
		String path = getBaseResourcePath() + resource;
		return path;
	}

	public static String getBaseResourcePath() {
		String path = LoggerHelper.class.getClassLoader().getResource(".").getPath();
		return path;
	}

}
