package com.primerworldapps.database.user;

class UserProvider {

	private static final String url = "http://dev.softvit.com/vitasolution/solutions/";

	public static String getSolutionsUrl(int caseId) {
		String getURL = url + "?caseId=" + caseId;
		return getURL;
	}

	public static String getNewSolutionUrl() {
		return url + "create/";
	}

	public static String getSolutionDetailsUrl(int solutionId) {
		return url + "detailed?solutionId=" + solutionId;
	}

	public static String getSolutionAcceptUrl(int solutionId) {
		return url + "accept?solutionId=" + solutionId;
	}

}
