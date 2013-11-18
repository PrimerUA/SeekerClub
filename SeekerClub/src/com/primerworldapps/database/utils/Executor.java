package com.primerworldapps.database.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.concurrent.ExecutionException;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

public class Executor {

	protected InputStream getStream(String url) {
		InputStream source = null;
		try {
			byte[] res = new Streamer().execute(url).get();
			if (res != null) {
				source = new ByteArrayInputStream(res);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return source;
	}
	
	protected Reader getReader(InputStream source) {
		Reader reader = null;
		if (source != null) {
			reader = new InputStreamReader(source);
		} else {
			// TODO: make message, if source == null
			Log.e("reader", "source in reader is null");
		}
		return reader;
	}
}
