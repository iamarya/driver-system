package com.arya.driversystem.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arya.driversystem.extra.CustomException;
import com.arya.driversystem.extra.Utils;
import com.arya.driversystem.model.Driver;

@Service
public class DriverServiceImpl implements DriverService {

	@Value("${ds.filename}")
	String fileName;
	
	private static final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);


	ReadWriteLock lock = new ReentrantReadWriteLock();

	@Override
	public List<Driver> findAll() throws Exception {
		return findAfterDate(null);
	}

	@Override
	public List<Driver> findAfterDate(LocalDateTime date) throws Exception {
		Lock readLock = lock.readLock();
		readLock.lock();
		BufferedReader br = null;
		List<Driver> drivers = new ArrayList<>();
		try {
			br=new BufferedReader(new FileReader(fileName));
			String line;
			while ((line = br.readLine()) != null) {
				addDriverToList(date, drivers, line);
			}

		} catch(Exception e) {
			throw new CustomException("Exception while reading", e);
		} finally {
			readLock.unlock();
			if (br != null) {
				br.close();
			}
		}
		return drivers;
	}

	private void addDriverToList(LocalDateTime date, List<Driver> drivers, String line) {
		try {
			Driver driver = Utils.getDriverFromString(line);
			if (date == null || date.isBefore(driver.getCreationDate())) {
				drivers.add(driver);
			}
		} catch (Exception e) {
			logger.error("Error while converting to object: "+ line , e);
		}
	}

	@Override
	public void save(Driver driver) throws Exception {
		Lock writeLock = lock.writeLock();
		writeLock.lock();
		FileWriter fw=null;
		try {
			String id = Utils.generateUUID();
			driver.setId(id);
			driver.setCreationDate(LocalDateTime.now());
			fw = new FileWriter(fileName, true);
			fw.write(System.lineSeparator());
			fw.write(Utils.getStringFromDriver(driver));
		} catch(Exception e) {
			throw new CustomException("Exception while writting", e);
		} finally {
			writeLock.unlock();
			if (fw != null) {
				fw.close();
			}
		}
	}
}
