package com.example.test_ch12_6ex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

	private static final String Table_Name = "Cus";

	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public DBHelper(Context context){
		super(context, "Campany.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String create_Table = "create table " + Table_Name +
				"( " + 
				" cusNo VARCHAR(10) NOT NULL, " +
				" cusNa VARCHAR(20) NOT NULL, " + 
				" cusPho VARCHAR(30), " + 
				" cusAdd VARCHAR(50), primary key(cusNo));";
		db.execSQL(create_Table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql = (" DROP TABLE IF EXISTS " + Table_Name); 
		db.execSQL(sql);
		onCreate(db);
		
	}
	
	//�u�[�@�����
	public void mInsert(){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("cusNo", "002");
		cv.put("cusNa", "joy");
		cv.put("cusPho", "22-333");
		cv.put("cusAdd", "Japen");
		
		db.insert(Table_Name, null, cv);
		db.close();
	}
/*	
 *  //���J�h�����
	public void mInsert(){
		SQLiteDatabase db = getWritableDatabase();
		ContentValues[] rec = new ContentValues[3];
		for(int i = 0; i < rec.length; i++){
			rec[i] = new ContentValues();
		}
		
		rec[0].put("cusNo", "A01");
		rec[0].put("cusNa", "jj");
		rec[0].put("cusPho", "586-45568");
		rec[0].put("cusAdd", "taiwan");
		
		rec[1].put("cusNo", "A02");
		rec[1].put("cusNa", "Qoo");
		rec[1].put("cusPho", "888-45568");
		rec[1].put("cusAdd", "taiwan");
		
		rec[2].put("cusNo", "A03");
		rec[2].put("cusNa", "wowo");
		rec[2].put("cusPho", "444-45568");
		rec[2].put("cusAdd", "taiwan");
	
		//�@�Ӥ@�ӼƼ�.
//		for(int i=0; i < rec.length; i++){
//			db.insert(Table_Name, null, rec[i]);
//		}
		
		//�@���Ƨ�.
		for(ContentValues row:rec){
			db.insert(Table_Name, null, row);
		}
		
		db.close();
	}
*/
	
	public String FinRec(String CusNo){
		// 1. �إ�DB�s�u
		SQLiteDatabase db = getReadableDatabase();
		// 2. �إ�SQL�y�y
		//�[�J�j�M����: " where CusNo like ?"
		String sql = "select * from " + Table_Name + " where CusNo like ?";
		//�j�M�����J array�A�i�����h��?�Ѽ�
		String[] args ={"%" + CusNo + "%"}; 
		// 3. ���� cursor. �@�j�MDB���ʧ@.
		//    cursor ���t���@��table, �ھ�cursor��m���V���P�����(row).
		Cursor cursor = db.rawQuery(sql, args);
		// [***] Ū��cursor���� table�����ƶq => �o�̼ƶq�O4, �]�������CusNo/CusNa/CusPho/CusAddr.
		int columnCount = cursor.getColumnCount();
		
		String fld = ""; 

		//cursor.getColumnCount():���� 
		//cursor.getCount():�X����Ƽ�. 
		if(cursor.getCount() != 0){
			// 4. ���V�Y����� 
			while(cursor.moveToNext()){
									
				//[***] �ܼƨϥνd��.
				//   �ھګŧi����m, �M�w�i�ϥνd��.
				//   {} �ʳ��d��, �]�N�O��, {}�~�����H�ݤ��쥦������.
//				String fld = ""; 
						
				// 5. Ū���	
				//   [***] cursor Ū�@��String���������, �ھڿ�J�����index(��m).
				// �^�Ǫ���Ʈ榡: "CusNo:CusNa"
				fld += cursor.getString(0) + ":" + cursor.getString(1) + "\n";
				
//				for(int i = 0; i < columnCount; i++){
//					fld += cursor.getString(i)  + ":";
//				}
			}
		}
		
		// 6. ����cursor.
		cursor.close();
		// 7. ���� DB�s�u
		db.close();
		
		return fld;		
	}
}