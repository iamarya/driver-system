package com.arya.driversystem.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Driver {

	private String id;

	@NotEmpty(message = "First name is empty")
	private String firstName;

	@NotEmpty(message = "Last name is empty")
	private String lastName;

	@NotNull
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateofBirth;

	@JsonIgnore
	private LocalDateTime creationDate;

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public LocalDate getDateofBirth() {
		return dateofBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public void setDateofBirth(LocalDate localDate) {
		this.dateofBirth = localDate;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
