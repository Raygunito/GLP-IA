package log;

import java.net.URL;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Utility class used to generate Log4j logger.
 * 
 * We can generate logs in a text or a html file.
 * 
 * @author Tianxiao.Liu@u-cergy.fr
 */
public class LoggerUtility {
	private static final String TEXT_LOG_CONFIG = "/log/log4j-text.properties";
	private static final String HTML_LOG_CONFIG = "/log/log4j-html.properties";

	public static Logger getLogger(Class<?> logClass, String logFileType) {
		if (logFileType.equals("text")) {
			URL url = logClass.getResource(TEXT_LOG_CONFIG);
			if (url != null) {
				PropertyConfigurator.configure(url);
			}else {
				PropertyConfigurator.configure(TEXT_LOG_CONFIG);
			}
		} else if (logFileType.equals("html")) {
			URL url = logClass.getResource(HTML_LOG_CONFIG);
			if (url != null) {
				PropertyConfigurator.configure(url);
			}else {
				PropertyConfigurator.configure(HTML_LOG_CONFIG);
			}
		} else {
			throw new IllegalArgumentException("Unknown log file type !");
		}
		String className = logClass.getName();
		return Logger.getLogger(className);
	}
}
