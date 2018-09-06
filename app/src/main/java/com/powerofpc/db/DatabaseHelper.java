package com.powerofpc.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	// The Android's default system path of your application database.
	private static String DB_NAME = "database";
	private static final int DB_VERSION = 1;
//	private static final String DATABASE_SCHEMA = "CREATE TABLE Animals (_id INTEGER PRIMARY KEY, count NUMERIC);\n" +
//			"INSERT INTO Animals VALUES(1,0);\n" +
//			"CREATE TABLE Count (animals NUMERIC, other NUMERIC, people NUMERIC, technology NUMERIC, work NUMERIC);\n" +
//			"INSERT INTO Count VALUES(0,0,0,0,0);\n" +
//			"CREATE TABLE Other (_id INTEGER PRIMARY KEY, count NUMERIC);\n" +
//			"INSERT INTO Other VALUES(1,0);\n" +
//			"CREATE TABLE People (_id INTEGER PRIMARY KEY, count NUMERIC);\n" +
//			"INSERT INTO People VALUES(1,0);\n" +
//			"CREATE TABLE Tables (_id INTEGER PRIMARY KEY, animals TEXT, other TEXT, people TEXT, technology TEXT, work TEXT);\n" +
//			"INSERT INTO Tables VALUES(1,'Animals','Other','People','Technology','Work');\n" +
//			"CREATE TABLE Technology (_id INTEGER PRIMARY KEY, count NUMERIC);\n" +
//			"INSERT INTO Technology VALUES(1,0);\n" +
//			"CREATE TABLE Work (_id INTEGER PRIMARY KEY, count NUMERIC);\n" +
//			"INSERT INTO Work VALUES(1,0);\n" +
//			"CREATE TABLE level (one NUMBERIC, two NUMBERIC, three NUMBERIC, four NUMERIC);\n" +
//			"INSERT INTO level VALUES(0,0,0,0);";

	private static final String COUNT_TABLE = "CREATE TABLE Count(animals NUMERIC, other NUMERIC, people NUMERIC, technology NUMERIC, work NUMERIC)";
	private static final String LEVEL_TABLE = "CREATE TABLE level (low NUMBERIC, medium NUMBERIC, high NUMBERIC, count NUMERIC)";

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(COUNT_TABLE);
		db.execSQL(LEVEL_TABLE);
		db.execSQL("INSERT INTO Count VALUES(0,0,0,0,0)");
		db.execSQL("INSERT INTO level VALUES(0,0,0,0)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public String[] getCount() {

		SQLiteDatabase db = this.getReadableDatabase();
		String[] list_items = new String[5];
		Cursor c = db.query("Count", null, null, null, null, null, null);
		c.moveToFirst();

		for (int i = 0; i < 5; i++) {
			list_items[i] = c.getString(i);
		}
		c.close();
		db.close();

		return list_items;
	}

	public void setCount(String column) {

		SQLiteDatabase db = this.getWritableDatabase();
		String[] list_items = new String[5];
		Cursor c = db.query("Count", null, null, null, null, null, null);
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

		db.execSQL("UPDATE Count SET " + column + " = '" + count + "'");
		db.close();
	}

	public String[] getLevel() {
		SQLiteDatabase db = this.getReadableDatabase();
		String[] list_items = new String[4];
		Cursor c = db.query("level", null, null, null, null, null, null);
		c.moveToFirst();

		for (int i = 0; i < 4; i++) {
			list_items[i] = c.getString(i);
		}

		c.close();
		db.close();

		return list_items;
	}

	public void setLevel(String column) {

		SQLiteDatabase db = this.getWritableDatabase();
		String[] list_items = new String[4];

		Cursor c = db.query("level", null, null, null, null, null, null);
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

		db.execSQL("UPDATE level SET " + column + " = '" + count + "', count = '" + mainCount + "'");
		db.close();
	}
}