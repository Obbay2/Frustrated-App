package com.powerofpc.graphs;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.powerofpc.db.DatabaseHelper;
import com.powerofpc.frustrated.R;

public class BarGraph extends Activity {
	TextView total, green, yellow, red, cyan, pink;
	DatabaseHelper helper;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bargraph);

		LinearLayout layout = (LinearLayout) findViewById(R.id.chart);

		GraphicalView mChartView;

		XYMultipleSeriesRenderer mrenderer = new XYMultipleSeriesRenderer();
		double[] range = { 0, 5, 0, 5 };

		helper = new DatabaseHelper(getApplicationContext());
		String[] items = helper.getCount();

		int[] counts = new int[5];
		counts[0] = Integer.parseInt(items[0]);
		counts[1] = Integer.parseInt(items[1]);
		counts[2] = Integer.parseInt(items[2]);
		counts[3] = Integer.parseInt(items[3]);
		counts[4] = Integer.parseInt(items[4]);

		int yAxis = getYAxis(counts);

		if (yAxis >= 10) {
			yAxis = yAxis / 2;
		} else if (yAxis >= 30) {
			yAxis = yAxis / 4;
		}

		setupRenderers(mrenderer);
		mrenderer.setShowLegend(false);
		mrenderer.setLegendHeight(0);
		mrenderer.setLegendTextSize(0);
		mrenderer.setAxisTitleTextSize(25);
		mrenderer.setChartTitleTextSize(30);
		mrenderer.setLabelsTextSize(25);
		mrenderer.setMargins(new int[] { 50, 40, -40, 0 });
		mrenderer.setAxesColor(Color.WHITE);
		mrenderer.setShowGridX(true);
		// mrenderer.setChartTitle("Frustration");
		mrenderer.setYTitle("Times Frustrated");
		mrenderer.setInitialRange(range, 1);
		mrenderer.setXAxisMin(0);
		mrenderer.setXAxisMax(6);
		mrenderer.setYAxisMin(0);
		mrenderer.setYLabels(yAxis);
		mrenderer.setYLabelsAlign(Align.LEFT);
		mrenderer.setXLabels(0);
		mrenderer.setPanEnabled(false, false);
		mrenderer.setZoomEnabled(false, false);
		mrenderer.setBarSpacing(-0.6);

		mChartView = ChartFactory.getBarChartView(this, Series(), mrenderer, Type.STACKED);
		layout.addView(mChartView);
	}

	private void setupRenderers(XYMultipleSeriesRenderer mrenderer) {

		XYSeriesRenderer renderer1 = new XYSeriesRenderer();
		renderer1.setColor(Color.rgb(0, 255, 0));

		XYSeriesRenderer renderer2 = new XYSeriesRenderer();
		renderer2.setColor(Color.rgb(255, 255, 0));

		XYSeriesRenderer renderer3 = new XYSeriesRenderer();
		renderer3.setColor(Color.rgb(255, 0, 0));

		XYSeriesRenderer renderer4 = new XYSeriesRenderer();
		renderer4.setColor(Color.rgb(0, 255, 255));

		XYSeriesRenderer renderer5 = new XYSeriesRenderer();
		renderer5.setColor(Color.rgb(255, 0, 255));

		mrenderer.addSeriesRenderer(renderer1);
		mrenderer.addSeriesRenderer(renderer2);
		mrenderer.addSeriesRenderer(renderer3);
		mrenderer.addSeriesRenderer(renderer4);
		mrenderer.addSeriesRenderer(renderer5);
	}

	private XYMultipleSeriesDataset Series() {

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

		long total_long = Math.round(total * 100);
		long people_percent_final = Math.round(people_percent);
		long animal_percent_final = Math.round(animal_percent);
		long work_percent_final = Math.round(work_percent);
		long technology_percent_final = Math.round(technology_percent);
		long other_percent_final = Math.round(other_percent);

		XYSeries animalsCat = new XYSeries("Animals " + animal_percent_final + "%");
		XYSeries otherCat = new XYSeries("Other " + other_percent_final + "%");
		XYSeries peopleCat = new XYSeries("People " + people_percent_final + "%");
		XYSeries technologyCat = new XYSeries("Technology " + technology_percent_final + "%");
		XYSeries workCat = new XYSeries("Work " + work_percent_final + "%");

		green = (TextView) findViewById(R.id.green);
		green.setText("Animals " + animal_percent_final + "%");
		yellow = (TextView) findViewById(R.id.yellow);
		yellow.setText("Other " + other_percent_final + "%");
		red = (TextView) findViewById(R.id.red);
		red.setText("People " + people_percent_final + "%");
		cyan = (TextView) findViewById(R.id.cyan);
		cyan.setText("Technology " + technology_percent_final + "%");
		pink = (TextView) findViewById(R.id.pink);
		pink.setText("Work " + work_percent_final + "%");
		this.total = (TextView) findViewById(R.id.total);
		this.total.setText("Total Times Frustrated: " + String.valueOf(total_long));
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

		for (int i = 0; i < 5; i++) {
			switch (i) {
			case 0:
				animalsCat.add(1, animals);
				dataset.addSeries(animalsCat);
				break;
			case 1:
				otherCat.add(2, other);
				dataset.addSeries(otherCat);
				break;
			case 2:
				peopleCat.add(3, people);
				dataset.addSeries(peopleCat);
				break;
			case 3:
				technologyCat.add(4, technology);
				dataset.addSeries(technologyCat);
				break;
			case 4:
				workCat.add(5, work);
				dataset.addSeries(workCat);
				break;
			}
		}

		return dataset;
	}

	private static int getYAxis(int[] numbers) {
		int largest = numbers[0];
		for (int i = 1; i < numbers.length; i++) {
			if (numbers[i] > largest) {
				largest = numbers[i];
			}
		}
		return largest;
	}
}
