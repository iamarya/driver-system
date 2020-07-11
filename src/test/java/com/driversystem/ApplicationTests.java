package com.driversystem;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class ApplicationTests {

	@Test
	public void test(){
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
	}
}
