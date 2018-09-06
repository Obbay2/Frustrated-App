package com.powerofpc.frustrated;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.powerofpc.db.DatabaseReadWrite;
import com.powerofpc.db.NewDatabaseHelper;

public class Splash extends Activity {
	MediaPlayer splashsound;
	String ran = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		NewDatabaseHelper helper1 = new NewDatabaseHelper(Splash.this);
		DatabaseReadWrite helper2 = new DatabaseReadWrite(Splash.this);
		ran = helper2.ran();
		if (ran.equals("0")) {
			setContentView(R.layout.splash);
			splashsound = MediaPlayer.create(Splash.this, R.raw.thunder);
			splashsound.start();
			helper1.setRun();
			Thread timer = new Thread() {
				@Override
				public void run() {
					try {
						sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {

						Intent openMainMenu = new Intent("com.powerofpc.frustrated.MAIN_MENU");
						startActivity(openMainMenu);
					}
				}
			};
			timer.start();
		} else {
			Intent openMainMenu = new Intent("com.powerofpc.frustrated.MAIN_MENU");
			startActivity(openMainMenu);
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (ran.equals("0")) {
			splashsound.release();
			finish();
		} else {
			finish();
		}
	}
}
