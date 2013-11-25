package com.primerworldapps.seeker.entity;

public class SeekerDetectedApplication {

	private static SeekerDetectedApplication instance = null;

	private int id;
	private int user_id;
	
	private String name;
	private int type;
	private boolean myTreat;
	private String contact;
	
	private double latitude;
	private double longitude;
	
	protected SeekerDetectedApplication() {
		// Exists only to defeat instantiation.
	}

	public static SeekerDetectedApplication getInstance() {
		if (instance == null) {
			instance = new SeekerDetectedApplication();
		}
		return instance;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getType() {
		return type;
	}

	public boolean isMyTreat() {
		return myTreat;
	}

	public String getContact() {
		return contact;
	}
	
	public SeekerDetectedApplication setId(int id) {
		this.id = id;
		return instance;
	}

	public SeekerDetectedApplication setName(String name) {
		this.name = name;
		return instance;
	}

	public SeekerDetectedApplication setType(int type) {
		this.type = type;
		return instance;
	}

	public SeekerDetectedApplication setMyTreat(boolean myTreat) {
		this.myTreat = myTreat;
		return instance;
	}

	public SeekerDetectedApplication setContact(String contact) {
		this.contact = contact;
		return instance;
	}

	public int getUser_id() {
		return user_id;
	}

	public SeekerDetectedApplication setUser_id(int user_id) {
		this.user_id = user_id;
		return instance;
	}

	public double getLatitude() {
		return latitude;
	}

	public SeekerDetectedApplication setLatitude(double latitude) {
		this.latitude = latitude;
		return instance;
	}

	public double getLongitude() {
		return longitude;
	}

	public SeekerDetectedApplication setLongitude(double longitude) {
		this.longitude = longitude;
		return instance;
	}

}
