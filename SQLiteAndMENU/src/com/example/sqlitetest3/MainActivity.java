package com.example.sqlitetest3;
//package com.example.sqliteandmenu;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView myListView;
	private EditText myEditText;
	private TextView myDate;
	MySQLiteOpenHelper myHelper;
	private Cursor myCursor;
	private int _ID;
	protected static final int MENU_ADD = Menu.FIRST;
	protected static final int MENU_EDIT = Menu.FIRST + 1;
	protected static final int MENU_DELETE = Menu.FIRST + 2;
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		//(群組，識別碼，按鈕出現的順序，顯示的文字)
		menu.add(0, MENU_ADD, 0, R.string.AddButton);
		menu.add(0, MENU_EDIT, 1, R.string.EditButton);
		menu.add(0, MENU_DELETE, 2, R.string.DeleteButton);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case MENU_ADD :
			this.addTodo();
			break;
		case MENU_EDIT:
			this.editTodo();
			break;
		case MENU_DELETE:
			this.deleteTodo();
			break;
		}
		return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//建立連結
		myListView = (ListView)findViewById(R.id.listView1);
		myEditText = (EditText)findViewById(R.id.editText1);
		myDate = (TextView)findViewById(R.id.tvDate);
		
		myHelper = new MySQLiteOpenHelper(this);
		
		myCursor = myHelper.select();
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, 
				R.layout.list, 
				myCursor, 
				new String[] {myHelper.Field_Text, myHelper.Field_Date}, 
				new int[] {R.id.listTextView1, R.id.tvDate});
		
		myListView.setAdapter(adapter);

		//監聽ListView是否有被點擊
		myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//cursor移動到被點擊的位置上
				myCursor.moveToPosition(arg2);
				//cursor抓取第0個欄位給 _ID.
				_ID = myCursor.getInt(0);
				//cursor抓取第1個欄位給EditText.
				myEditText.setText(myCursor.getString(1));				
			}
		});
			
		//監聽 ListView是否有被選取.
		myListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//獲得一個 SQLiteCursor類別的物件，指向 SQLite資料庫查詢的結果.
				SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();
				//取得該筆資料的第0個欄位內容，資料型態為整數.
				_ID = sc.getInt(0);
				//取得該筆資料的第1個欄位內容，資料型態為字串，將資料填入 myEditText中.
				myEditText.setText(sc.getString(1));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	//myEditText.setText(now)
	private void addTodo() {
		if(myEditText.getText().toString().equals("")){
			return;
		}else{
			Date current = new Date();
			String now = sdf.format(current);
			//呼叫 SQLite的 insert()方法，將輸入欄位內的資料插入資料庫中.
			myHelper.insert(myEditText.getText().toString(), now);
//			myDate.getText().toString()
			
			//重新查詢資料庫的內容.
			myCursor.requery();
			//重新顯示 ListView的內容 +清除輸入欄位的資料+ _ID歸0.
			//invalidateViews:讓View失效。
			//會導致android重畫畫面(重新顯示 ListView的內容)。
			myListView.invalidateViews();
			myEditText.setText("");
			_ID = 0;
			
			Toast.makeText(this, "addTime: " + now, Toast.LENGTH_SHORT).show();
		}
	}
	

	private void editTodo() {
		if(myEditText.getText().toString().equals("")){
			return;
		}else{
			Date current = new Date();
			String now = sdf.format(current);
			myHelper.update(_ID, myEditText.getText().toString(), now);
			
			//重新查詢資料庫的內容
			myCursor.requery();
			//重新顯示 ListView的內容 + 清除輸入欄位的資料+ _ID歸0.
			myListView.invalidateViews();
			myEditText.setText("");
			_ID = 0;
			
			Toast.makeText(this, "editTime: " + now, Toast.LENGTH_SHORT).show();
		}
	}

	private void deleteTodo() {
		if(_ID == 0){
			return;
		}else{
			Date current = new Date();
			String now = sdf.format(current);
			//呼叫 SQLite的delete()方法.
			myHelper.delete(_ID);
			
			//重新查詢資料庫的內容.
			myCursor.requery();
			//重新顯示 ListView的內容+ 清除輸入欄位的資料+ _ID歸0.
			myListView.invalidateViews();
			myEditText.setText("");
			_ID = 0;
			
			
			Toast.makeText(this, "deleteTime: " + now, Toast.LENGTH_SHORT).show();
		}
	}
}//end class MainActivity.
