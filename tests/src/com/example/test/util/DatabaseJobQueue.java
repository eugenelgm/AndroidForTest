package com.example.test.util;

public class DatabaseJobQueue {
	
	public static void runJob(Runnable run) {
		run.run();
	}

}
