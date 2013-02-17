package com.example.test.util;

import android.util.Log;

public class Logger {
	
	public static final String TAG = "test";
	
	public static void e(Throwable tr, String format, Object... args) {
		Log.e(TAG, String.format(format, args), tr);
	}
	
	public static void d(String format, Object... args) {
		Log.d(TAG, String.format(format, args));
	}
}
