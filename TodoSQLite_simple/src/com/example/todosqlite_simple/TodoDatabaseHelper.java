package com.example.todosqlite_simple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class TodoDatabaseHelper extends SQLiteOpenHelper  {
	
	private static final String DATABASE_NAME = "applicationdata";
	private static final int DATABASE_VERSION = 4;
	private static final String DATABASE_CREATE = 
			"create table todo (_id integer primary key autoincrement, " +  
			" category text not null, summary text not null, description text not null);";
	
	public static final String DATABASE_TABLE = "todo";
	public static final String KEY_ROWID = "_id";
	public static final String KEY_CATEGORY = "category";
	public static final String KEY_SUMMARY = "summary";
	public static final String KEY_DESCRIPTION = "description";

	
	public TodoDatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);

	}
	
	public TodoDatabaseHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);	
		
		//這裡是從程式內撰寫的資料.
		createTodo(db, "remider", "todo1", "eat breakfast before 10 o'clock.");
		createTodo(db, "urgent", "todo2", "shit everyday.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(TodoDatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to " +
				newVersion + ", which will destroy all old data");
		onCreate(db);		
	}
	
	//
	// DB operations
	//
	//從程式內撰寫資料時，自己呼叫使用的.
	public long createTodo(SQLiteDatabase db, String category, String summary, String description){
		
		ContentValues initialValues = createContentValues(category, summary, description);		
		long result = db.insert(DATABASE_TABLE, null, initialValues);		
		
		return result;		
	}
	
	//自訂從外部手動輸入資料使用，所以自己建立自己的db連線.
	//Create a new todo if the todo is successfully created return the new 
	//rowId for that note, otherwise return a -1 to indicate failure.
	public long createTodo(String category, String summary, String description){		
		SQLiteDatabase db = getWritableDatabase();
		long result = createTodo(db, category, summary, description);				
		db.close();
		
		return result;		
	}
	
	//Return a Cursor over the list of all todo in the database.
	public Cursor fetchAllTodos(){
		SQLiteDatabase database = getReadableDatabase();
		
		//rawQuery:自己在 SQL內有撰寫資料; query:
		return database.query(DATABASE_TABLE, new String[]{KEY_ROWID,
				KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION},
				null, null, null, null, null);
	}
	
	private ContentValues createContentValues(String category, String summary,
			String description) {
		ContentValues values = new ContentValues();
		// [***] 變數跟字串值有區別. 
		//       KEY_CATEGORY <= 變數
		//       "KEY_CATEGORY" <= 字串值, 它的type是String, 用雙引號括起來.
		// values.put("KEY_CATEGORY", "category");
		values.put(KEY_CATEGORY, category);
		values.put(KEY_SUMMARY, summary);
		values.put(KEY_DESCRIPTION, description);
		return values;
	}
}
