package com.primerworldapps.database.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.primerworldapps.database.utils.Executor;
import com.primerworldapps.database.utils.GetResponseTask;
import com.primerworldapps.database.utils.Streamer;
import com.primerworldapps.entity.SeekerUser;

public class UserExecutor extends Executor {
	private Gson gson = new Gson();

	
}
