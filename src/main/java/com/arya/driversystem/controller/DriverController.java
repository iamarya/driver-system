package com.arya.driversystem.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arya.driversystem.model.Driver;
import com.arya.driversystem.service.DriverService;

/**
 * 
 * This class is the main controller of the application.
 * @author arya
 *
 */
@RestController
public class DriverController {
	@Autowired
	private DriverService driverService;

	@GetMapping("/drivers")
	public List<Driver> getAllDrivers() throws Exception {
		return driverService.findAll();
	}

	@GetMapping("/drivers/byDate/{date}")
	public List<Driver> getUserAfterDate(@PathVariable(value = "date") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime date) throws Exception {
		return driverService.findAfterDate(date);
	}
	
	@PostMapping("/drivers/create")
	public String create(@RequestBody @Valid Driver driver) throws Exception {
		driverService.save(driver);
		return "success";
	}
	
}
