package com.primerworldapps.entity;

public class SeekerUser {

	private int id;
	
	private String name;
	private String city;
	private String country;
	
	private String email;
	private String password;
	
	private int age;
	private boolean gender;

	private double rating = 0;
	private int type = 0; //fractions numbers = 0,1,2
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

	public SeekerUser setMale(boolean gender) {
		this.gender = gender;
		return instance;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public double getRating() {
		return rating;
	}

	public SeekerUser setRating(double rating) {
		this.rating = rating;
		return instance;
	}

}
