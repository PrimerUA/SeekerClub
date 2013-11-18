package com.primerworldapps.database.application;

class CaseProvider {

	// private static final String url =
	// "http://50.31.20.116:8080/VitaSolution/rest/api/cases/";
	private static final String url = "http://dev.softvit.com/vitasolution/cases/";

	public static String getCasesGeneralListUrl(int from, int length) {
		String getURL = url + "?from=" + from + "&length=" + length + "&orderBy=date";
		return getURL;
	}

	public static String getCasesActiveListUrl(int userId) {
		String getURL = url + "opened?userId=" + userId;
		return getURL;
	}

	public static String getCasesClosedListUrl(int userId) {
		String getURL = url + "opened?userId=" + userId;
		return getURL;
	}

	public static String getNewCaseUrl() {
		return url + "create/";
	}

	public static String getCasesDetailsUrl(int caseId) {
		return url + "detailed?id=" + caseId;
	}

	public static String getUpdateCaseUrl() {
		return url + "update/";
	}
}
