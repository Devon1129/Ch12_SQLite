package com.example.sqlitewithcursoradapter2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
	//db info.
	private static final String DB_Name = "member22";
	private static final int DB_Version = 2;
	
	//table info.
	public static final String Table_Name = "member";
	public static final String Table_ID = "_id";
	public static final String Table_MEMBER = "name";
	public static final String Table_MEMBER2 = "subName";
	
	
	//table create.
	private static final String create_table = 
			"CREATE TABLE " + Table_Name + 
			" ( " +
			Table_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			Table_MEMBER + " TEXT NOT NULL," + 
			Table_MEMBER2 + " TEXT NOT NULL);";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context){
		super(context, DB_Name, null, DB_Version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(create_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = ("drop table if exists " + Table_Name);
		db.execSQL(sql);
		onCreate(db);
	}

}
