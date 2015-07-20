package com.example.ch12_4_3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String Table_Name = "cus33";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context){
		super(context, "company4_33.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String table = "create table " + Table_Name + 
				"(cusNo varchar(10) not null, " +
				" cusNa varchar(20) not null, " +
				" cusPho varchar(30), " +
				" cusAdd varchar(50), primary key(cusNo));";
		db.execSQL(table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = ("drop table if exists " + Table_Name);
		db.execSQL(sql);
		onCreate(db);
	}
	
	public long insertRec(String cusNo, String cusNa, String cusPho, String cusAdd){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		
		cv.put("cusNo", cusNo);
		cv.put("cusNa", cusNa);
		cv.put("cusPho", cusPho);
		cv.put("cusAdd", cusAdd);
		
		long rowID = db.insert(Table_Name, null, cv);
		db.close();
		
		return rowID;
		
	}
	
	public int count(){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "select * from " + Table_Name;
		Cursor rec = db.rawQuery(sql, null);
		
		int count = rec.getCount();
		rec.close();
		db.close();
		return count;
		
	}

}
