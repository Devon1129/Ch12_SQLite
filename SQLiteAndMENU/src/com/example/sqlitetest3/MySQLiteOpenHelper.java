package com.example.sqlitetest3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
	private static final String DB_Name = "todo.db";
	private static final String Table_Name = "todo_Table";
	protected static final String Field_Id = "_id";
	protected static final String Field_Text = "todo_text";
	protected static final String Field_Date = "todo_date";
	
	public MySQLiteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	public MySQLiteOpenHelper(Context context){
		super(context, DB_Name, null, 2);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "CREATE TABLE " + Table_Name + 
				" ( " + 
				Field_Id + " INTEGER primary key autoincrement, " + 
				Field_Text + " text, " + 
				Field_Date + " text);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//[fix] "EXISTS" 後面沒有接"空格"以用來與table name分隔.
		String sql = "DROP TABLE IF EXISTS " + Table_Name;
		db.execSQL(sql);
		onCreate(db);
	}
	
	public Cursor select(){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(Table_Name, null, null, null, null, null, "todo_date desc");
		return cursor;
	}
	
	public long insert(String text, String date){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		//要放在資料庫的哪一個欄位，內容.
		cv.put(Field_Text, text);
		cv.put(Field_Date, date);
		long row = db.insert(Table_Name, null, cv); 
		return row;
	}
	
	public void delete(int id){
		SQLiteDatabase db = this.getWritableDatabase();
		String where = Field_Id + "=?";
		String[] whereValue = {Integer.toString(id)};
		db.delete(Table_Name, where, whereValue);
	}
	
	public void update(int id, String text, String date){
		SQLiteDatabase db = this.getWritableDatabase();
		String where = Field_Id + "=?";
		String[] whereVale = {Integer.toString(id)};
		
		ContentValues cv = new ContentValues();
		cv.put(Field_Text, text);
		cv.put(Field_Date, date);
		db.update(Table_Name, cv, where, whereVale);
	}
}//end class MySQLitenOpenHelper. 
