package com.patrick.stopwatchpresentation;

import java.text.SimpleDateFormat;

import android.os.Vibrator;
import android.util.Log;

public class StopWatch {
	private static final String TAG = "StopWatch";
	private long startTime;	
	private Vibrator v;
	private int delay = 10000 *50;
	
	enum state {ON, OFF, PAUSE};
	state clockState;
	
	public StopWatch() {
		
	}
	
	public void startWatch(){
		clockState = state.ON;
		startTime = System.currentTimeMillis();
	}
	
	public String updatedTime(){
		if(clockState == state.ON){
			long time = System.currentTimeMillis() - startTime;
			long seconds = (time / 1000) % 60;
			long minutes = (time/ (1000*60)) %60;
			long hours = (time/ (1000*60*60)) % 24;
			return String.format("%02d:%02d:%02d", hours, minutes, seconds);
		} else{
			return String.format("00:00:00"); 
		}
	}
	
	public void stopWatch(){
		clockState = state.OFF;
		startTime = 0;
	}
	
	public boolean shouldIVibrate(){
		
		
		
		long stall = (System.currentTimeMillis() - startTime) % delay;
		
		if(stall < 1000){
			return true;
		} else{
			return false;
		}
	}
	
}
