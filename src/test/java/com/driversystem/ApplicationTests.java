package com.driversystem;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.arya.driversystem.extra.Utils;
import com.arya.driversystem.model.Driver;

class ApplicationTests {

	@Test
	public void uuidTest(){
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();
		assertNotEquals(uuid1.toString(), uuid2.toString());
	}
	
	@Test
	public void parseDriver() {
		String line = "fdd5fb96-9ef4-45f7-af50-958f87d339b6,Arunika,Panda,1985-05-26,2020-07-11T14:58:09Z";
		Driver driver = Utils.getDriverFromString(line);
		assertNotNull(driver);
	}
}
