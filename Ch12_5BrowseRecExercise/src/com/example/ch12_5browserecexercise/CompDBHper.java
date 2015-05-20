package com.example.ch12_5browserecexercise;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CompDBHper extends SQLiteOpenHelper {
//	private static final String DB_NAME = "Company";
	private static final String TABLE_NAME = "Cus";
	private static final String CREATE_TABLE =
		"CREATE TABLE " + TABLE_NAME +
		" ( " +
		" cusNo VARCHAR(10) NOT NULL, " +
		" cusNa VARCHAR(20) NOT NULL, " + 
		" cusPho VARCHAR(20), " + 
		" cusAdd VARCHAR(50), PRIMARY KEY(cusNo));";
		
	public CompDBHper(Context context, String DBname, CursorFactory factory,
			int DBversion) {
		super(context, "Company.db", null, 1);
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
		rec[0].put("cusNa", "Lin");
		rec[0].put("cusPho", "873-1234");
		rec[0].put("cusAdd", "桃園縣平安村49號");
		
		rec[1].put("cusNo", "A1002");
		rec[1].put("cusNa", "Wu");
		rec[1].put("cusPho", "822-3129");
		rec[1].put("cusAdd", "北市延平南路20號");
		
		rec[2].put("cusNo", "A1003");
		rec[2].put("cusNa", "chung");
		rec[2].put("cusPho", "704-1134");
		rec[2].put("cusAdd", "北市健康路15號");
		
		for(ContentValues row : rec){
			if(db.insert(TABLE_NAME, null, row) < 0)
			{
				Log.w("insert DB", "Fail to insert: " + row.getAsString("cusNo"));
			}
		}
		db.close();
	}
	
	public ArrayList<String> getRecSet(){
		SQLiteDatabase db = getReadableDatabase();
		String sql = " SELECT * FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<String> recAry = new ArrayList<String>();
		int columnCount = cursor.getColumnCount();
		while(cursor.moveToNext()){
			String fldSet = "";
			for(int i = 0; i < columnCount; i++){
				fldSet += cursor.getString(i) + "#";
			}
			recAry.add(fldSet);
		}
	
		cursor.close();
		db.close();
		return recAry;
	}

	public int deleteRec(String CusNo){
		SQLiteDatabase db = getWritableDatabase();
		String sql = "SELECT * FROM " + TABLE_NAME;
		Cursor cursor = db.rawQuery(sql, null);
		if(cursor.getCount() != 0){
			String whereClause = "cusNo= '" + CusNo + "'";
			int rowsAffected = db.delete(TABLE_NAME, whereClause, null);
			cursor.close();
			db.close();
			return rowsAffected;
		}else{
			cursor.close();
			db.close();
			return -1;
		}
	}
}