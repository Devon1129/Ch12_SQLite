package com.example.ch12_7test;

import java.sql.Array;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	
	//table name:命名使用英文或數字及底線，數字與底線放於英文字後。
	//使用中文命名易產生 Exception.
	private static final String Table_Name = "ccus12_7";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context){
		//命名規則:數字勿放於字首，避免加入空白，可以的話底線也盡量避免加入。
		super(context, "12_7 conpany.db", null, 3);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String table = "create table " + Table_Name + 
				" (" +
				" cusNo varchar(10) not null, " +
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
		ContentValues[] cv = new ContentValues[3];
		for(int i = 0; i < cv.length; i++){
			cv[i] = new ContentValues();
		}
		
		cv[0].put("cusNo", "A1001");
		cv[0].put("cusNa", "宜靜");
		cv[0].put("cusPho", "03-873-1234");
		cv[0].put("cusAdd", "桃園縣平安村49號");
		
		cv[1].put("cusNo", "A1002");
		cv[1].put("cusNa", "美虹");
		cv[1].put("cusPho", "02-822-3129");
		cv[1].put("cusAdd", "北市延平南路20號");
		
		cv[2].put("cusNo", "A1003");
		cv[2].put("cusNa", "仁均");
		cv[2].put("cusPho", "02-704-1134");
		cv[2].put("cusAdd", "北市健康路15號");
		
		for(ContentValues row:cv){
			db.insert(Table_Name, null, row);
		}
		
		db.close();
	}
	
	//Q:使用AarrayList存取???因為是列表???
	public ArrayList<String> getRec(){
		SQLiteDatabase db = getReadableDatabase();
		String sql = "select * from " + Table_Name;
		Cursor rec = db.rawQuery(sql, null);
		
		ArrayList<String> aryRec = new ArrayList<String>();
		
		int columns = rec.getColumnCount();
		if(rec.getCount() != 0){
			while(rec.moveToNext()){
				String fld = "";
				for(int i = 0; i < columns; i++){
					fld += rec.getString(i) + "#";
				}
				aryRec.add(fld);
			}
		}
		rec.close();
		db.close();
		return aryRec;
		
	}
	


//	public void deleteRec(){
//		SQLiteDatabase db = getWritableDatabase();
//		String sql = "select * from " + Table_Name;
//		Cursor rec = db.rawQuery(sql, null);
//		
//		if(rec.getCount() != 0){
//			
//		}
//		int columns = rec.getColumnCount();
//		
//	}
}
