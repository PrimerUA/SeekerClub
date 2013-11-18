package com.primerworldapps.database.utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class GetResponseTask extends AsyncTask<HttpPost, Integer, HttpResponse> {

	private ProgressDialog progressDialog;

	public GetResponseTask(ProgressDialog progress) {
		this.progressDialog = progress;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.show();
	}

	@Override
	protected HttpResponse doInBackground(HttpPost... requests) {
		try {
			return new DefaultHttpClient().execute(requests[0]);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(HttpResponse result) {
		super.onPostExecute(result);
		progressDialog.dismiss();
	}

}
