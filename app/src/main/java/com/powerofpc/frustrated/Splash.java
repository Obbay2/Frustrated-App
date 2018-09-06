package com.powerofpc.frustrated;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.powerofpc.db.DatabaseHelper;

public class Splash extends Activity {
	MediaPlayer splashsound;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.splash);
		splashsound = MediaPlayer.create(Splash.this, R.raw.thunder);
		splashsound.start();
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
	}

	@Override
	protected void onPause() {
		super.onPause();
		splashsound.release();
		finish();
	}
}
