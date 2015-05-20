package com.example.ch12_6querurecexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CompDBHper extends SQLiteOpenHelper {
	private static final String TABLE_NAME = "Cus";
	private static final String CREATE_TABLE =
		"CREATE_TABLE " + TABLE_NAME +
		" ( " + 
		" cusNo VARCHAR(10) NOT NULL, " +
		" cusNa VARCHAR(20) NOT NULL, " + 
		" cusPho VARCHAR(20), " +
		" cusAdd VARCHAR(50), PRIMARY KEY(cusNo));";

	public CompDBHper(Context context, String DBname, CursorFactory factory,
			int Dbversion) {
		super(context, "Company.db", factory, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
		onCreate(db);
	}
	
	public void createTABLE(){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues[] rec = new ContentValues[3];
		for(int i = 0; i < rec.length; i++){
			rec[i] = new ContentValues();
		}
		
		rec[0].put("cusNo", "A1001");
		rec[0].put("cusNa", "Jack");
		rec[0].put("cusPho", "03-333666");
		rec[0].put("cusAdd", "Singapore");
		
		rec[1].put("cusNo", "A1002");
		rec[1].put("cusNa", "Mary");
		rec[1].put("cusPho", "04-555888");
		rec[1].put("cusAdd", "Myanmar");
		
		rec[2].put("cusNo", "A1003");
		rec[2].put("cusNa", "Jean");
		rec[2].put("cusPho", "05-111666");
		rec[2].put("cusAdd", "India");
		
		for(ContentValues row : rec){
			db.insert(TABLE_NAME, null, row);
		}
		db.close();
	}

	public String FindRec(String CusNo){
		SQLiteDatabase db = getReadableDatabase();
		String sql = " SELECT * FROM " + TABLE_NAME;
		String[] args = {"%" + "%"};
		Cursor recSet = db.rawQuery(sql, args);
		int columnCount = recSet.getColumnCount();
		String fldSet = null;
		if(recSet.getCount() != 0){
			while(recSet.moveToNext()){
				fldSet = "";
				for(int i = 0; i < columnCount; i++){
					fldSet += recSet.getString(i) + "\n";
				}
			}
		}
		recSet.close();
		db.close();
		return fldSet;
	}
}