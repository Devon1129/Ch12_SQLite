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
	
	//只加一筆資料
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
 *  //插入多條資料
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
	
		//一個一個數數.
//		for(int i=0; i < rec.length; i++){
//			db.insert(Table_Name, null, rec[i]);
//		}
		
		//一次數完.
		for(ContentValues row:rec){
			db.insert(Table_Name, null, row);
		}
		
		db.close();
	}
*/
	
	public String FinRec(String CusNo){
		// 1. 建立DB連線
		SQLiteDatabase db = getReadableDatabase();
		// 2. 建立SQL語句
		//加入搜尋條件: " where CusNo like ?"
		String sql = "select * from " + Table_Name + " where CusNo like ?";
		//搜尋條件放入 array，可對應多個?參數
		String[] args ={"%" + CusNo + "%"}; 
		// 3. 執行 cursor. 作搜尋DB的動作.
		//    cursor 內含有一個table, 根據cursor位置指向不同筆資料(row).
		Cursor cursor = db.rawQuery(sql, args);
		// [***] 讀取cursor指到 table的欄位數量 => 這裡數量是4, 因為有欄位CusNo/CusNa/CusPho/CusAddr.
		int columnCount = cursor.getColumnCount();
		
		String fld = ""; 

		//cursor.getColumnCount():欄位數 
		//cursor.getCount():幾筆資料數. 
		if(cursor.getCount() != 0){
			// 4. 指向某筆資料 
			while(cursor.moveToNext()){
									
				//[***] 變數使用範圍.
				//   根據宣告的位置, 決定可使用範圍.
				//   {} 封閉範圍, 也就是說, {}外面的人看不到它的內部.
//				String fld = ""; 
						
				// 5. 讀資料	
				//   [***] cursor 讀一個String類型的資料, 根據輸入的欄位index(位置).
				// 回傳的資料格式: "CusNo:CusNa"
				fld += cursor.getString(0) + ":" + cursor.getString(1) + "\n";
				
//				for(int i = 0; i < columnCount; i++){
//					fld += cursor.getString(i)  + ":";
//				}
			}
		}
		
		// 6. 關掉cursor.
		cursor.close();
		// 7. 關掉 DB連線
		db.close();
		
		return fld;		
	}
}