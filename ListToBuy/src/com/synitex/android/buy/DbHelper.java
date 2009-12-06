package com.synitex.android.buy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper {

	public static String TABLE_ITEMS = "Items";
	public static String FLD_ITEMS_NAME = "name";
	public static String FLD_ITEMS_ID = "_id";

	private static final String DB_NAME = "ListToBuyDb";
	private static final int DB_VERSION = 1;
	private static final String CREATE_DB_SQL = "create table " + TABLE_ITEMS
			+ " (" + FLD_ITEMS_ID + " integer primary key autoincrement, "
			+ FLD_ITEMS_NAME + " text not null);";

	private SQLiteDatabase db;
	private Context context;
	private DbOpenHelper helper;

	public DbHelper(Context context) {
		this.context = context;
	}

	public void open() {
		helper = new DbOpenHelper(context);
		db = helper.getWritableDatabase();
	}

	public void close() {
		helper.close();
	}

	public Long createItem(String name) {
		ContentValues values = new ContentValues();
		values.put(FLD_ITEMS_NAME, name);
		Long id = db.insert(TABLE_ITEMS, null, values);
		return id;
	}

	// --------------------------------------------

	private static class DbOpenHelper extends SQLiteOpenHelper {

		public DbOpenHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(CREATE_DB_SQL);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		}

	}

}
