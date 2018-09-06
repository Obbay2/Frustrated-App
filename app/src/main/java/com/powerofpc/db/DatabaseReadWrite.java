package com.powerofpc.db;

import java.io.IOException;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseReadWrite extends SQLiteOpenHelper {
	private final Context myContext;

	/**
	 * Constructor Takes and keeps a reference of the passed context in order to
	 * access to the application assets and resources.
	 * 
	 * @param context
	 */
	public DatabaseReadWrite(Context context) {

		super(context, null, null, 1);
		this.myContext = context;
	}

	public String[] getCount() {

		NewDatabaseHelper myDbHelper = new NewDatabaseHelper(myContext);

		String[] list_items = null;
		list_items = new String[5];

		Cursor c = null;

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		c = myDbHelper.query("Count", null, null, null, null, null, null);
		c.moveToFirst();

		for (int i = 0; i < 5; i++) {
			list_items[i] = c.getString(i);
		}

		c.close();
		myDbHelper.close();

		return list_items;

	}

	public void setCount(String column) {

		NewDatabaseHelper myDbHelper = new NewDatabaseHelper(myContext);
		String[] list_items = null;
		list_items = new String[5];

		Cursor c = null;

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		c = myDbHelper.query("Count", null, null, null, null, null, null);
		c.moveToFirst();

		for (int i = 0; i < 5; i++) {
			list_items[i] = c.getString(i);
		}

		c.close();

		int count = 0;

		if (column.equals("animals")) {
			count = (int) Math.round(Double.parseDouble(list_items[0]));
		} else if (column.equals("other")) {
			count = (int) Math.round(Double.parseDouble(list_items[1]));
		} else if (column.equals("people")) {
			count = (int) Math.round(Double.parseDouble(list_items[2]));
		} else if (column.equals("technology")) {
			count = (int) Math.round(Double.parseDouble(list_items[3]));
		} else if (column.equals("work")) {
			count = (int) Math.round(Double.parseDouble(list_items[4]));
		}

		count++;

		myDbHelper.getWritableDatabase().execSQL("UPDATE Count SET " + column + " = '" + count + "'");

		myDbHelper.close();

	}

	public String ran() {
		String ran = null;
		NewDatabaseHelper myDbHelper = new NewDatabaseHelper(myContext);

		Cursor c = null;
		try {

			myDbHelper.createDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		c = myDbHelper.query("VersionRun", null, null, null, null, null, null);
		c.moveToFirst();
		ran = c.getString(0);
		c.close();
		myDbHelper.close();
		return ran;
	}

	public String[] getLevel() {
		NewDatabaseHelper myDbHelper = new NewDatabaseHelper(myContext);

		String[] list_items = null;
		list_items = new String[4];

		Cursor c = null;

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		c = myDbHelper.query("level", null, null, null, null, null, null);
		c.moveToFirst();

		for (int i = 0; i < 4; i++) {
			list_items[i] = c.getString(i);
		}

		c.close();
		myDbHelper.close();

		return list_items;

	}

	public void setLevel(String column) {

		NewDatabaseHelper myDbHelper = new NewDatabaseHelper(myContext);
		String[] list_items = null;
		list_items = new String[4];

		Cursor c = null;

		try {

			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		c = myDbHelper.query("level", null, null, null, null, null, null);
		c.moveToFirst();

		for (int i = 0; i < 4; i++) {
			list_items[i] = c.getString(i);
		}

		c.close();

		int count = 0;

		if (column.equals("low")) {
			count = (int) Math.round(Double.parseDouble(list_items[0]));
		} else if (column.equals("medium")) {
			count = (int) Math.round(Double.parseDouble(list_items[1]));
		} else if (column.equals("high")) {
			count = (int) Math.round(Double.parseDouble(list_items[2]));
		}

		count++;
		int mainCount = Integer.parseInt(list_items[3]);
		mainCount++;

		myDbHelper.getWritableDatabase().execSQL(
				"UPDATE level SET " + column + " = '" + count + "', count = '" + mainCount + "'");
		// myDbHelper.getWritableDatabase().execSQL("UPDATE level SET " + column
		// + " = '" + count + "'");

		myDbHelper.close();

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
