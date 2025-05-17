package com.f1app;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class Configurator {
	private static final String HOME_ENVIRONMENT_VARIABLE = "f1app_home";
	private static final String CONFIGURATION_FILE_NAME = "f1app.properties";
	private static final String CONFIGURATION_FOLDER_NAME = "config";

	@Bean
	public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		File configurationFile = getConfigurationFile();

		if (configurationFile != null) {
			configurer.setLocation(new FileSystemResource(configurationFile));
			configurer.setIgnoreResourceNotFound(false);
		}

		return (configurer);
	}

	private File getConfigurationFile() {
		String filename = getHomeFolder() + File.separatorChar + CONFIGURATION_FOLDER_NAME + File.separatorChar
				+ CONFIGURATION_FILE_NAME;

		File file = new File(filename);

		return (file);
	}

	private String getHomeFolder() {
		String rootFolder = "" + File.separatorChar;
		String homeFolder = System.getenv(HOME_ENVIRONMENT_VARIABLE);

		homeFolder = (homeFolder != null) ? homeFolder.trim() : rootFolder;

		return (homeFolder);
	}
}
