package com.powerofpc.frustrated;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.powerofpc.db.DatabaseHelper;

public class Frustrated_menu extends Activity implements View.OnClickListener {
	Button animals, people, work, technology, other;
	DatabaseHelper helper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frustrated_menu);
		helper = new DatabaseHelper(getApplicationContext());
		setupVariables();
		setupButtons();
	}

	public void setupButtons() {
		String[] list;
		list = getResources().getStringArray(R.array.mainList);

		animals.setText(list[0]);
		other.setText(list[1]);
		people.setText(list[2]);
		technology.setText(list[3]);
		work.setText(list[4]);
	}

	private void setupVariables() {
		animals = (Button) findViewById(R.id.animals);
		other = (Button) findViewById(R.id.other);
		people = (Button) findViewById(R.id.people);
		technology = (Button) findViewById(R.id.technology);
		work = (Button) findViewById(R.id.work);

		animals.setOnClickListener(this);
		people.setOnClickListener(this);
		work.setOnClickListener(this);
		technology.setOnClickListener(this);
		other.setOnClickListener(this);
	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.animals:
			helper.setCount("animals");
			Intent animals = new Intent("com.powerofpc.frustrated.MAIN_MENU");
			startActivity(animals);
			break;
		case R.id.people:
			helper.setCount("people");
			Intent people = new Intent("com.powerofpc.frustrated.MAIN_MENU");
			startActivity(people);
			break;
		case R.id.work:
			helper.setCount("work");
			Intent work = new Intent("com.powerofpc.frustrated.MAIN_MENU");
			startActivity(work);
			break;
		case R.id.technology:
			helper.setCount("technology");
			Intent technology = new Intent("com.powerofpc.frustrated.MAIN_MENU");
			startActivity(technology);
			break;
		case R.id.other:
			helper.setCount("other");
			Intent other = new Intent("com.powerofpc.frustrated.MAIN_MENU");
			startActivity(other);
			break;
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
