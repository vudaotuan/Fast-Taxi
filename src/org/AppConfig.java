package org;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.LoggerFactory;

public class AppConfig extends Properties {

	private static final long serialVersionUID = 1L;

	private static AppConfig instance;

	private AppConfig() throws IOException {
//		InputStream isr = this.getClass().getResourceAsStream(
//				"/config.development.properties");
		InputStream isr = this.getClass().getResourceAsStream(
				"/config.production.properties");
		if (isr != null) {
			InputStreamReader isrProperties = new InputStreamReader(isr);
			this.load(isrProperties);
		}
		
//		DOMConfigurator config = new DOMConfigurator();
//		config.doConfigure(this.getClass().getClassLoader()
//		        .getResourceAsStream("/log4j.xml"), LogManager
//		        .getLoggerRepository());
	}

	public static AppConfig getInstance() {
		if (instance == null) {
			try {
				instance = new AppConfig();
			} catch (IOException ex) {
				LoggerFactory.getLogger(AppConfig.class).error(ex.getMessage());
			}
		}
		return instance;
	}

	public int getOldThread() {
		return Integer.parseInt((String) this.get("app.oldThread"));
	}

}
