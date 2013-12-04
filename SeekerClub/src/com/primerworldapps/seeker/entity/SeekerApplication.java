package com.primerworldapps.seeker.entity;

public class SeekerApplication {

	private int id;
	private int userId;
	
	private double latitude;
	private double longitude;

	private int type;
	private boolean myTreat;
	private boolean gender;
	private int age;
	
	private long time;

	private String contact;

	private static SeekerApplication instance = null;

	protected SeekerApplication() {
		// Exists only to defeat instantiation.
	}

	public static SeekerApplication getInstance() {
		if (instance == null) {
			instance = new SeekerApplication();
		}
		return instance;
	}

	public double getLongitude() {
		return longitude;
	}

	public SeekerApplication setLongitude(double longitude) {
		this.longitude = longitude;
		return instance;
	}

	public double getLatitude() {
		return latitude;
	}

	public SeekerApplication setLatitude(double latitude) {
		this.latitude = latitude;
		return instance;
	}

	public int getType() {
		return type;
	}

	public SeekerApplication setType(int type) {
		this.type = type;
		return instance;
	}

	public boolean isMale() {
		return gender;
	}

	public SeekerApplication setMale(boolean gender) {
		this.gender = gender;
		return instance;
	}

	public int getAge() {
		return age;
	}

	public SeekerApplication setAge(int age) {
		this.age = age;
		return instance;
	}

	public String getContact() {
		return contact;
	}

	public SeekerApplication setContact(String contact) {
		this.contact = contact;
		return instance;
	}

	public boolean isMyTreat() {
		return myTreat;
	}

	public SeekerApplication setMyTreat(boolean myTreat) {
		this.myTreat = myTreat;
		return instance;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	public long getTime() {
		return time;
	}

	public SeekerApplication setTime(long time) {
		this.time = time;
		return instance;
	}

}
