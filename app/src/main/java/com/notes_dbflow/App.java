package com.notes_dbflow;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class App extends Application {
	private static App mInstance;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mInstance=this;
		FlowManager.init(new FlowConfig.Builder(this).build());
	}
	
	public static App getInstance(){
		return mInstance;
	}
}
