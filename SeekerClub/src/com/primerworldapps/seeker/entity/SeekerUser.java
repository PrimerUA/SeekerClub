package com.primerworldapps.seeker.entity;

public class SeekerUser {

	private int id;
	private boolean isLoggedIn;
	
	private String googleId;
	private String name;
	
	private String email;
	private int age;
	private boolean gender;

	private double rating = 0;
	private int fractionId = 0; //fractions numbers = 0,1,2
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

	public String getEmail() {
		return email;
	}

	public SeekerUser setEmail(String email) {
		this.email = email;
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
		return fractionId;
	}

	public void setType(int type) {
		this.fractionId = type;
	}

	public SeekerUser setId(int id) {
		this.id = id;
		return instance;
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

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String getGoogleId() {
		return googleId;
	}

	public SeekerUser setGoogleId(String googleId) {
		this.googleId = googleId;
		return instance;
	}

}
