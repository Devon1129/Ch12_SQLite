package com.example.ex01_sqlitewithcursoradapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {
	private DBHelper dbHelper;
	private Context ourContext;
	private SQLiteDatabase sqldb;
	
	public SQLController(Context context){
		ourContext = context;
	}
	
	public SQLController open() throws SQLException{
		dbHelper = new DBHelper(ourContext);
		sqldb = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void insertData(String name){
		ContentValues cv = new ContentValues();
		cv.put(dbHelper.Member_Name, name);
		sqldb.insert(DBHelper.Table_Member, null, cv);
	}
	
	//query()
	public Cursor readData(){
		String[] allColumns = new String[]{DBHelper.Member_id, DBHelper.Member_Name};
		Cursor cursor = 
			sqldb.query(DBHelper.Table_Member, allColumns, 
					null, null, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	public int updateData(long memberID, String memberName){
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(DBHelper.Member_Name, memberName);
		int info = 
			sqldb.update(DBHelper.Table_Member, cvUpdate, DBHelper.Member_id + "=" + memberID, null);
		return info;
	}
	
	

}//end class SQLController.
