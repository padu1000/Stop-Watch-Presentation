package com.patrick.stopwatchpresentation;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Start extends Activity {

	private String TAG = "Start";
	StopWatch myWatch;
	boolean watchOn = false;
	Locale myLocale = new Locale("US");
	SimpleDateFormat myTime = new SimpleDateFormat("HH:mm:ss", Locale.US);
	public TextView timeLabel;
	int temp = 0;

	Vibrator v;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);

//		RelativeLayout rLayout = (RelativeLayout) findViewById(R.layout.activity_start);
//		Resources res = getResources(); // resource handle
//		Drawable drawable = res.getDrawable(R.drawable.miun);


		timeLabel = (TextView) findViewById(R.id.textView1);
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		Button btn = (Button) findViewById(R.id.Start);
		Button btn2 = (Button) findViewById(R.id.Stop);

		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startW();
			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stopW();
			}
		});
	}

	@Override
	protected void onStart() {
		myWatch = new StopWatch();
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.start, menu);
		return true;
	}

	

	private void thefunctionthatcould() {

		Thread t = new Thread() {
			@Override
			
			public void run() {
				temp =0;
				try {
					while (watchOn == true) {
						// System.out.println("Test: "+fw);
						Thread.sleep(1000);
						// final int eyeball = fw;
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								temp++;
								timeLabel.setText("" + myWatch.updatedTime());
								if (temp % 300 == 0) {
									Log.d(TAG, "Test!--------------------");
									v.vibrate(3000);
								}
								Log.d(TAG,"Counting.... " +temp);

							}
						});
					}
				} catch (InterruptedException e) {
				}
			}
		};

		t.start();

	}

	public void startW() {
		watchOn = true;
		myWatch.startWatch();
		thefunctionthatcould();
	}

	public void stopW() {
		watchOn = false;
		myWatch.stopWatch();
	}

}
