package com.example.test.util;


public class Logger {
	public static final String TAG = "test";
	
	public static void e(Throwable tr, String format, Object... args) {
		System.out.println("[E]"+String.format(format, args));
		tr.printStackTrace();
	}
	
	public static void d(String format, Object... args) {
		System.out.println("[D]"+String.format(format, args));
	}
}
