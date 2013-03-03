package com.example.test.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseJobQueue {

	private static ExecutorService dbThread = Executors.newSingleThreadExecutor();
	
	public static void runJob(Runnable run) {
		dbThread.submit(run);
	}
}
