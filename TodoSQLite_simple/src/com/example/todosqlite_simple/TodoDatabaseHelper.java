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
		
		//�o�̬O�q�{�������g�����.
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
	//�q�{�������g��ƮɡA�ۤv�I�s�ϥΪ�.
	public long createTodo(SQLiteDatabase db, String category, String summary, String description){
		
		ContentValues initialValues = createContentValues(category, summary, description);		
		long result = db.insert(DATABASE_TABLE, null, initialValues);		
		
		return result;		
	}
	
	//�ۭq�q�~����ʿ�J��ƨϥΡA�ҥH�ۤv�إߦۤv��db�s�u.
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
		
		//rawQuery:�ۤv�b SQL�������g���; query:
		return database.query(DATABASE_TABLE, new String[]{KEY_ROWID,
				KEY_CATEGORY, KEY_SUMMARY, KEY_DESCRIPTION},
				null, null, null, null, null);
	}
	
	private ContentValues createContentValues(String category, String summary,
			String description) {
		ContentValues values = new ContentValues();
		// [***] �ܼƸ�r��Ȧ��ϧO. 
		//       KEY_CATEGORY <= �ܼ�
		//       "KEY_CATEGORY" <= �r���, ����type�OString, �����޸��A�_��.
		// values.put("KEY_CATEGORY", "category");
		values.put(KEY_CATEGORY, category);
		values.put(KEY_SUMMARY, summary);
		values.put(KEY_DESCRIPTION, description);
		return values;
	}
}
