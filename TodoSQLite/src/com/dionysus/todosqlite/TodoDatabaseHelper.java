package com.dionysus.todosqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TodoDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "applicationdata";
	private static final int DATABASE_VERSION = 1;
	//Database creation sql statement.
	private static final String DATABASE_CREATE = 
			"create table todo (_id integer primary key autoincrement, " +  
			" category text not null, summary text not null, description text not null);";
	
	public TodoDatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public TodoDatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//Method is called during creation of the database.
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);

	}

	//Method is called during an upgrade of the database, e.g. if you increase.
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TodoDatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to " +
				newVersion + ", which will destory all old data");
		onCreate(db);

	}

}

//http://puremonkey2010.blogspot.tw/2011/09/android-sqlite.html
