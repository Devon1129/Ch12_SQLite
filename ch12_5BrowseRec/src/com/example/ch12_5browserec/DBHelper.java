package com.example.ch12_5browserec;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final String Table_Name = "cus";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context){
		super(context, "company.db", null, 1);
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
	
	public void insertRec(){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv[] = new ContentValues[3];
		for(int i = 0; i < cv.length; i++){
			cv[i] = new ContentValues();
		}
		
		
		cv[0].put("cusNo", "a01");
		cv[0].put("cusNa", "bill");
		cv[0].put("cusPho", "75-545757");
		cv[0].put("cusAdd", "newyork");
		
		cv[1].put("cusNo", "b02");
		cv[1].put("cusNa", "coco");
		cv[1].put("cusPho", "45-445764");
		cv[1].put("cusAdd", "newyork");
		
		cv[2].put("cusNo", "c03");
		cv[2].put("cusNa", "boa");
		cv[2].put("cusPho", "69-5782254");
		cv[2].put("cusAdd", "newyork");
		
		for(ContentValues row:cv){
			db.insert(Table_Name, null, row);
		}
		db.close();
	}
	
	public ArrayList<String> getRec(){
		SQLiteDatabase db = getReadableDatabase();
		String sql = "select * from " + Table_Name;
		Cursor rec = db.rawQuery(sql, null);
		
		ArrayList<String> aryRec = new ArrayList<String>();
		
		int columns = rec.getColumnCount();
		String fld = null;
		if(rec.getCount() != 0){
			while(rec.moveToNext()){
				fld = "";
				for(int i = 0; i < columns; i++){
					fld += rec.getString(i) + "#";
				}
			}
			aryRec.add(fld);
		}
		rec.close();
		db.close();
		return aryRec;
		
	}

}
