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
	
	//table name:�R�W�ϥέ^��μƦr�Ω��u�A�Ʀr�P���u���^��r��C
	//�ϥΤ���R�W������ Exception.
	private static final String Table_Name = "ccus12_7";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context){
		//�R�W�W�h:�Ʀr�ũ��r���A�קK�[�J�ťաA�i�H���ܩ��u�]�ɶq�קK�[�J�C
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
		cv[0].put("cusNa", "�y�R");
		cv[0].put("cusPho", "03-873-1234");
		cv[0].put("cusAdd", "��鿤���w��49��");
		
		cv[1].put("cusNo", "A1002");
		cv[1].put("cusNa", "���i");
		cv[1].put("cusPho", "02-822-3129");
		cv[1].put("cusAdd", "�_�������n��20��");
		
		cv[2].put("cusNo", "A1003");
		cv[2].put("cusNa", "����");
		cv[2].put("cusPho", "02-704-1134");
		cv[2].put("cusAdd", "�_�����d��15��");
		
		for(ContentValues row:cv){
			db.insert(Table_Name, null, row);
		}
		
		db.close();
	}
	
	//Q:�ϥ�AarrayList�s��???�]���O�C��???
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
