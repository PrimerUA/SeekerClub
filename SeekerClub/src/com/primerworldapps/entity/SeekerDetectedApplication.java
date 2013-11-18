package com.primerworldapps.entity;

public class SeekerDetectedApplication {

	private static SeekerDetectedApplication instance = null;

	private int id;
	
	private String name;
	private int type;
	private boolean myTreat;
	private String contact;
	
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

}
