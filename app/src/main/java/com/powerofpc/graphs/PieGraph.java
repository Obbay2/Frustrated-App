package com.powerofpc.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import com.powerofpc.db.DatabaseHelper;
import com.powerofpc.frustrated.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class PieGraph extends Activity implements OnClickListener {
	Button zoomin, zoomout, zoomreset;
	CategorySeries series = new CategorySeries("Pie Graph");
	GraphicalView mChartView;
	DatabaseHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.piegraph);

		LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
		
		Series();

		int[] colors = new int[] { Color.BLUE, Color.YELLOW, Color.GREEN,
				Color.MAGENTA, Color.RED };

		DefaultRenderer renderer = new DefaultRenderer();
		renderer.setBackgroundColor(Color.BLACK);
		renderer.setApplyBackgroundColor(true);
		renderer.setShowLegend(true);
		renderer.setLegendTextSize(30);
		renderer.setFitLegend(true);
		renderer.setShowLabels(false);
		renderer.setScale(1);
		renderer.setPanEnabled(false);
		renderer.setZoomRate((float) 1.25);
		renderer.setZoomButtonsVisible(false);
		renderer.setExternalZoomEnabled(true);
		for (int color : colors) {
			SimpleSeriesRenderer r = new SimpleSeriesRenderer();
			r.setColor(color);
			renderer.addSeriesRenderer(r);
			r.setDisplayChartValues(true);
		}
		mChartView = ChartFactory.getPieChartView(this, series, renderer);
		layout.addView(mChartView);
		initialize();
		zoomin.setOnClickListener(this);
		zoomout.setOnClickListener(this);
		zoomreset.setOnClickListener(this);
	}

	private void initialize() {
		zoomin = (Button) findViewById(R.id.zoomin);
		zoomout = (Button) findViewById(R.id.zoomout);
		zoomreset = (Button) findViewById(R.id.reset);
	}

	private void Series() {

		helper = new DatabaseHelper(getApplicationContext());
		String[] items = helper.getCount();

		double animals = Double.parseDouble(items[0]);
		double other = Double.parseDouble(items[1]);
		double people = Double.parseDouble(items[2]);
		double technology = Double.parseDouble(items[3]);
		double work = Double.parseDouble(items[4]);

		double total = (people + animals + work + technology + other) / 100;
		double people_percent = people / total;
		double animal_percent = animals / total;
		double work_percent = work / total;
		double technology_percent = technology / total;
		double other_percent = other / total;

		for (int x = 0; x < 5; x++) {
			if (animals == 0.0 && x == 0) {
				animal_percent = 0;
			} else if (other == 0.0 && x == 1) {
				other_percent = 0;
			} else if (people == 0.0 && x == 2) {
				people_percent = 0;
			} else if (technology == 0.0 && x == 3) {
				technology_percent = 0;
			} else if (work == 0.0 && x == 4) {
				work_percent = 0;
			}
		}

		for (int k = 0; k < 5; k++) {
			switch (k) {
			case 0:
				series.add("Animals " + animal_percent + "%", animals);
				break;
			case 1:
				series.add("People " + people_percent + "%", people);
				break;
			case 2:
				series.add("Work " + work_percent + "%", work);
				break;
			case 3:
				series.add("Technology " + technology_percent + "%", technology);
				break;
			case 4:
				series.add("Other " + other_percent + "%", other);
				break;
			}
		}
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.zoomin:
			mChartView.zoomIn();
			break;
		case R.id.zoomout:
			mChartView.zoomOut();
			break;
		case R.id.reset:
			mChartView.zoomReset();
			break;
		}

	}
}