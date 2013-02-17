package com.example.test;

import android.app.Application;

public class GlobalApplication extends Application {
	
	private static GlobalApplication instace;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instace = this;
	}
	
	public static GlobalApplication getInstance() {
		return instace;
	}
}
