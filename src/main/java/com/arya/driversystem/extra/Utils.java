package com.arya.driversystem.extra;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.arya.driversystem.model.Driver;

/**
 * 
 * This is Util class which holds all util methods for the application
 * @author arya
 *
 */
public class Utils {

	static final String SEPARATOR = ",";

	/**
	 * This method convert the string to Driver object
	 * @param line
	 * @return
	 */
	public static Driver getDriverFromString(String line) {
		String[] fields = line.split(",");
		Driver driver = new Driver();
		driver.setId(fields[0]);
		driver.setFirstName(fields[1]);
		driver.setLastName(fields[2]);

		DateTimeFormatter bdformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter creatationformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

		driver.setDateofBirth(LocalDate.parse(fields[3], bdformatter));
		driver.setCreationDate(LocalDateTime.parse(fields[4], creatationformatter));
		return driver;
	}

	public static String getStringFromDriver(Driver driver) {
		StringBuilder sb = new StringBuilder();
		DateTimeFormatter creatationformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sb.append(driver.getId()).append(SEPARATOR).append(driver.getFirstName()).append(SEPARATOR)
				.append(driver.getLastName()).append(SEPARATOR).append(driver.getDateofBirth()).append(SEPARATOR)
				.append(creatationformatter.format(driver.getCreationDate()));
		return sb.toString();
	}

	public static String generateUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
}
