package com.example.ex_01tototest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static final int DB_Version = 1;
	private static final String DB_Name = "todo.db";
	private static final String Table_Name = "todoList";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DatabaseHelper(Context context){
		super(context, DB_Name, null, DB_Version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String table = "create table " + Table_Name + 
		" (_id integer primary key autoincrement, " +
		" category text not null, " +
		" summary text not null, " +
		" description text not null);";
		
		db.execSQL(table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = ("drop table if exists " + Table_Name);
		db.execSQL(sql);
		onCreate(db);
	}
	
//	public long insertRec(SQLiteDatabase db, String category, String summary, String discription){
//		ContentValues initialValues = createContentValues(category, summary, discription);
//		long result = db.insert(Table_Name, null, initialValues);
//		return result;
//	}
	
	public long insetRec(String category, String summary, String discription){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues values = createContentValues(category, summary, discription);
		long result = db.insert(DB_Name, null, values);
		db.close();
		
		return result;
	}

	private ContentValues createContentValues(String category, String summary,
			String discription) {
		ContentValues values = new ContentValues();
		values.put("CATEGORY", category);
		values.put("SUMMARY", summary);
		values.put("DISCRIPTION", discription);
		return values;
	}
	
//	public void insertRec(){
//		SQLiteDatabase db = getWritableDatabase();
//		ContentValues[] cv = new ContentValues[3];
//		for(int i = 0; i < cv.length; i++){
//			 cv[i] = new ContentValues();
//		}
//		
//		cv[0].put("category", "aaa");
//		cv[0].put("summary", "xxx");
//		cv[0].put("description", "test");
//		
//		cv[1].put("category", "bbb");
//		cv[1].put("summary", "yyy");
//		cv[1].put("description", "test");
//		
//		cv[2].put("category", "ccc");
//		cv[2].put("summary", "zzz");
//		cv[2].put("description", "test");
//		
//		for(int i = 0; i < cv.length; i++){
//			db.insert(Table_Name, null, cv[i]);
//		}
//			
//			db.close();
//	}

	//todo.
	public Cursor fetchAllTodos(){
//		SQLiteDatabase db = getReadableDatabase();
		
		return null;
		
	}

}
