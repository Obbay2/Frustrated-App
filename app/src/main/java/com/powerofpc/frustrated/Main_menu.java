package com.powerofpc.frustrated;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.powerofpc.db.DatabaseReadWrite;

public class Main_menu extends Activity implements View.OnClickListener {
	Button stats, frustrated, options;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		setupVariables();
		setupLevelData();

	}

	public void setupVariables() {
		stats = (Button) findViewById(R.id.stats);
		frustrated = (Button) findViewById(R.id.frustrated);

		stats.setOnClickListener(this);
		frustrated.setOnClickListener(this);

	}

	public void setupLevelData() {
		ProgressBar mProgress = (ProgressBar) findViewById(R.id.progressBar);
		DatabaseReadWrite helper = new DatabaseReadWrite(Main_menu.this);
		String[] countByLevel = helper.getLevel();
		TextView level;
		Button frustrated;

		frustrated = (Button) findViewById(R.id.frustrated);
		level = (TextView) findViewById(R.id.frustration_level);

		Double total = Double.parseDouble(countByLevel[3]) * 100;
		Double low = Double.parseDouble(countByLevel[0]) * 33;
		Double medium = Double.parseDouble(countByLevel[1]) * 66;
		Double high = Double.parseDouble(countByLevel[2]) * 100;

		Double percent = low + medium + high;
		percent = percent / total;
		percent = percent * 100;
		int intPercent = (int) Math.round(percent);

		if (intPercent <= 100 && intPercent > 66) {
			intPercent = 100;
			level.setText("High");
			level.setTextColor(Color.rgb(255, 0, 0));
			frustrated.setTextColor(Color.rgb(255, 0, 0));
			level.setGravity(Gravity.RIGHT);
		} else if (intPercent <= 66 && intPercent > 33) {
			intPercent = 66;
			level.setText("Medium");
			level.setTextColor(Color.rgb(255, 174, 0));
			frustrated.setTextColor(Color.rgb(255, 127, 68));
			level.setGravity(Gravity.CENTER_HORIZONTAL);
		} else if (intPercent <= 33 && intPercent > 0) {
			intPercent = 33;
			level.setText("Low");
			level.setTextColor(Color.rgb(0, 123, 255));
			frustrated.setTextColor(Color.rgb(0, 33, 255));
			level.setGravity(Gravity.LEFT);
		} else {
			level.setText("No Data Entered");
			level.setTextColor(Color.rgb(255, 255, 255));
			frustrated.setTextColor(Color.rgb(0, 0, 0));
			level.setGravity(Gravity.LEFT);
		}

		mProgress.setProgress(intPercent);

	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.stats:
			Intent graph = new Intent("com.powerofpc.graphs.BAR_GRAPH");
			startActivity(graph);
			break;
		case R.id.frustrated:
			Intent menu = new Intent("com.powerofpc.frustrated.LEVEL");
			startActivity(menu);
			break;
		}
	}
}
