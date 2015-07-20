package com.example.test_ch12_6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
	private static final String DB_Name = "Cus.db";
	private static final String Table_Name = "info";
	
	public MyDBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public MyDBHelper(Context context){
		super(context, DB_Name, null, 1);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createTable =
				" create table " + Table_Name + 
				" ( cusNo VARCHAR(10) NOT NULL, " +
				" cusNa CHAR(20) NOT NULL, " +
				" cusPho CHAR(30), " + 
				" cusAdd CHAR(50), primary key(cusNo));";
				
		db.execSQL(createTable);
		
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		

	}	

	public void AddDefaultData()
	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues content = new ContentValues();
		content.put("cusNo", "A001");
		content.put("cusNa", "Jack");
		content.put("cusPho", "02xxxx");
		content.put("cusAdd", "river");
		
		db.insert(Table_Name, null, content);
	}

	public String GetCustomerInfo(String cusNo) {
		SQLiteDatabase db = getReadableDatabase();
		Cursor cursor = db.query(Table_Name, null, "cusNo like ?", 
				new String[]{"%" + cusNo + "%"}, null, null, null);
		String cusName = null;
		if(cursor.moveToNext())
		{
			cusName = cursor.getString(1);
		}
		
		return cusName;
	}
	
}
