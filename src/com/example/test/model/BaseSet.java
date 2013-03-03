package com.example.test.model;

import com.example.test.util.DatabaseJobQueue;

public abstract class BaseSet {
	
	protected static void runOnBackground(Runnable runnable) {
		DatabaseJobQueue.runJob(runnable);
	}

}
