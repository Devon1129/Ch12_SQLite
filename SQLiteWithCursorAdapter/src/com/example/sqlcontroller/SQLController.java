package com.example.sqlcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLController {
	private DBHelper dbHelper;
	private Context ourContext;
	private SQLiteDatabase database;
	
	public SQLController(Context context){
		ourContext = context;
	}
	
	public SQLController open() throws SQLException{
		dbHelper = new DBHelper(ourContext);
		database = dbHelper.getWritableDatabase();
		return this;
	}
	
	//沒有使用到，因為程式中都使用
	//.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	public void close(){
		dbHelper.close();
	}
	
	//Inserting Data into table.
	public void insertData(String name){
		ContentValues cv = new ContentValues();
		//MEMBER_NAME:欄位名稱; name:欄位值.
		cv.put(DBHelper.MEMBER_NAME, name);
		//TABLE_MEMBER:table名稱.
		database.insert(DBHelper.TABLE_MEMBER, null, cv);
	}
	
	//Getting Cursor to read data from table.
	public Cursor readData(){
		String[] allColumns = new String[]{DBHelper.MEMBER_ID, DBHelper.MEMBER_NAME};
		Cursor cursor = 
			database.query(DBHelper.TABLE_MEMBER, allColumns, 
				null, null, null, null, null);
		if(cursor != null){
			cursor.moveToFirst();
		}
		return cursor;
	}
	
	//Updating record data into table by id.
	public int updateData(long memberID, String memberName){
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put(DBHelper.MEMBER_NAME, memberName);
		int info = database.update(DBHelper.TABLE_MEMBER, cvUpdate,
			//DBHelper.MEMBER_ID + "=" + memberID:假設外面傳進來id為3， 條件式呈現:_id=3。
			DBHelper.MEMBER_ID + "=" + memberID, null); 
		return info;
	}
	
	//Deleting record data from table by id.
	public void deleteData(long memberID){
		database.delete(DBHelper.TABLE_MEMBER, DBHelper.MEMBER_ID + "=" + memberID, null);
	}
	

}//end class SQLController.
