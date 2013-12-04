package com.primerworldapps.seeker.util;

import com.primerworldapps.seeker.NewAccountHolderScreen;
import com.primerworldapps.seeker.R;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ConnectionTask extends AsyncTask<Void, Void, Void> {
	ProgressDialog progressDialog;
	Context context;

	public ConnectionTask(Context context) {
		this.progressDialog = new ProgressDialog(context);
		this.context = context;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progressDialog.setTitle(R.string.progress_wait);
		progressDialog.setMessage(context.getString(R.string.progress_loading));
		progressDialog.setIndeterminate(true);
		progressDialog.show();
	}

	@Override
	protected Void doInBackground(Void... urls) {
		progressDialog.show();
		PlusClientAuthenticator.getInstance().getPlusClient().connect();
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		progressDialog.dismiss();
		NewAccountHolderScreen newAccountHolderScreen = (NewAccountHolderScreen) context;
		newAccountHolderScreen.finish();
	}

}
