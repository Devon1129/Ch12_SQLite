package com.example.sqlcontroller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	//Table Informattion
	public static final String TABLE_MEMBER = "member";
	public static final String MEMBER_ID = "_id";
	public static final String MEMBER_NAME = "name";
	
	//Database Information
	static final String DB_NAME = "MEMBER.DB";
	static final int DB_VERSION = 1; 
	
	//Table Creation statement
	private static final String CREATE_TABLE = "create table " +
		TABLE_MEMBER + 
		" ( " + MEMBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
		MEMBER_NAME + " TEXT NOT NULL);";
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
		onCreate(db);
	}

}
