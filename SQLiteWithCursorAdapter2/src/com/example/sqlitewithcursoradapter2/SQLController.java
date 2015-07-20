package com.example.sqlitewithcursoradapter2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {
	private DBHelper dbHelper;
	private Context myContext;
	//«Å§i¸ê®Æ®w db.
	private SQLiteDatabase db;
	
	public SQLController(Context context){
		myContext = context;
	}
	
	public SQLController open(){
		dbHelper = new DBHelper(myContext);
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	//Insert Data.
	public void insertData(String name,String subName){
		ContentValues cv = new ContentValues();
		
		cv.put(dbHelper.Table_MEMBER, name);
		cv.put(dbHelper.Table_MEMBER2, subName);
		
		db.insert(dbHelper.Table_Name, null, cv);
//		db.insert(dbHelper.Table_MEMBER2, null, cv);
	}
	
	//read Data.
	public Cursor queryData(){
		String[] allcolumns =
			new String[]{dbHelper.Table_ID, dbHelper.Table_MEMBER, dbHelper.Table_MEMBER2};
		Cursor cursor = 
			db.query(dbHelper.Table_Name, allcolumns, 
			null, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	//Updata Data.
	public int upData(long memberID, String memberName, String membersubName){
		ContentValues upDatacv = new ContentValues();
		upDatacv.put(dbHelper.Table_MEMBER, memberName);
		upDatacv.put(dbHelper.Table_MEMBER2, membersubName);
		int i = db.update(dbHelper.Table_Name, upDatacv, 
			dbHelper.Table_ID + "=" + memberID, null);
		return i;
	}
	
	//Delete Data.
	public void deleteData(long memberID){
		db.delete(
			dbHelper.Table_Name, dbHelper.Table_ID + "=" + memberID, 
			null);
	}

}//end class SQLiteController.
