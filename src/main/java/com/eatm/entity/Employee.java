package com.eatm.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;
	private String name;
	private double salary;
	private String emailId;
	private String password;
	private String role;
	@OneToOne
	private Address address;
	@OneToMany
	private List<Attendence> attendences;
	@OneToMany
	private List<Task> tasks;

}
