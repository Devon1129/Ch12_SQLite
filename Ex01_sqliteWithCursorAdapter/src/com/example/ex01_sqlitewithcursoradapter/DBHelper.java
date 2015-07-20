package com.example.ex01_sqlitewithcursoradapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	//Database Information.
	public static final String DB_name = "member";
	public static final int DB_version = 1;
	
	//Table Information.
	public static final String Table_Member = "member";
	public static final String Member_id = "_id";
	public static final String Member_Name = "name";
	
	//Table Create statement.
	private static final String create_table = "create table " + 
			Table_Member +
			" ( " +
			Member_id + " integer primary key autoincrement, " +
			Member_Name + " text not null);";
	
	public DBHelper(Context context) {
		super(context, DB_name, null, DB_version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(create_table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + Table_Member);
		onCreate(db);
	}

}
