package com.powerofpc.frustrated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.powerofpc.db.DatabaseReadWrite;

public class Level extends Activity implements View.OnClickListener {
	Button low, medium, high, okay, cancel;
	TextView question;
	int selected;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		setupVariables();
		setupButtons();
	}

	public void setupButtons() {
		String[] list;
		list = getResources().getStringArray(R.array.level);

		question.setText(list[0]);
		low.setText(list[1]);
		medium.setText(list[2]);
		high.setText(list[3]);
		okay.setText(list[4]);
		cancel.setText(list[5]);
	}

	public void setupVariables() {
		question = (TextView) findViewById(R.id.levelQuestion);
		low = (Button) findViewById(R.id.low);
		medium = (Button) findViewById(R.id.moderately);
		high = (Button) findViewById(R.id.highly);
		okay = (Button) findViewById(R.id.okay);
		cancel = (Button) findViewById(R.id.cancel);

		low.setOnClickListener(this);
		medium.setOnClickListener(this);
		high.setOnClickListener(this);
		okay.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.okay:
			DatabaseReadWrite helper = new DatabaseReadWrite(Level.this);
			if (selected == 1) {
				helper.setLevel("low");
			} else if (selected == 2) {
				helper.setLevel("medium");
			} else if (selected == 3) {
				helper.setLevel("high");
			}
			Intent animals = new Intent("com.powerofpc.frustrated.FRUSTRATED_MENU");
			startActivity(animals);
			break;
		case R.id.cancel:
			Intent people = new Intent("com.powerofpc.frustrated.MAIN_MENU");
			startActivity(people);
			break;
		case R.id.low:
			low.setBackgroundResource(R.drawable.button_highlight2);
			medium.setBackgroundResource(R.drawable.button);
			high.setBackgroundResource(R.drawable.button);
			selected = 1;
			break;
		case R.id.moderately:
			medium.setBackgroundResource(R.drawable.button_highlight2);
			low.setBackgroundResource(R.drawable.button);
			high.setBackgroundResource(R.drawable.button);
			selected = 2;
			break;
		case R.id.highly:
			high.setBackgroundResource(R.drawable.button_highlight2);
			low.setBackgroundResource(R.drawable.button);
			medium.setBackgroundResource(R.drawable.button);
			selected = 3;
			break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
