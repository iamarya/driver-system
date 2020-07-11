package com.arya.driversystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.arya.driversystem.model.Driver;

public interface DriverService {

	List<Driver> findAll() throws Exception;

	List<Driver> findAfterDate(LocalDateTime date) throws Exception;

	void save(Driver driver) throws Exception;

}
