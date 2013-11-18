package com.primerworldapps.entity;

public class SeekerDetectedApplication {

	private static SeekerDetectedApplication instance = null;

	protected SeekerDetectedApplication() {
		// Exists only to defeat instantiation.
	}

	public static SeekerDetectedApplication getInstance() {
		if (instance == null) {
			instance = new SeekerDetectedApplication();
		}
		return instance;
	}
}
