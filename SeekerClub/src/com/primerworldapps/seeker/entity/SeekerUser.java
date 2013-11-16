package com.primerworldapps.seeker.entity;

public class SeekerUser {

	private String name;
	private String city;
	private String country;
	
	private String email;
	private String password;
	
	private int age;
	private boolean gender;

	private static SeekerUser instance = null;

	protected SeekerUser() {
		// Exists only to defeat instantiation.
	}

	public static SeekerUser getInstance() {
		if (instance == null) {
			instance = new SeekerUser();
		}
		return instance;
	}

	public String getName() {
		return name;
	}

	public SeekerUser setName(String name) {
		this.name = name;
		return instance;
	}

	public String getCity() {
		return city;
	}

	public SeekerUser setCity(String city) {
		this.city = city;
		return instance;
	}

	public String getCountry() {
		return country;
	}

	public SeekerUser setCountry(String country) {
		this.country = country;
		return instance;
	}

	public String getEmail() {
		return email;
	}

	public SeekerUser setEmail(String email) {
		this.email = email;
		return instance;
	}

	public String getPassword() {
		return password;
	}

	public SeekerUser setPassword(String password) {
		this.password = password;
		return instance;
	}

	public int getAge() {
		return age;
	}

	public SeekerUser setAge(int age) {
		this.age = age;
		return instance;
	}

	public boolean isMale() {
		return gender;
	}

	public SeekerUser setGender(boolean gender) {
		this.gender = gender;
		return instance;
	}

}
