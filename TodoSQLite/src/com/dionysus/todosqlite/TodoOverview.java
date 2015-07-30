package com.dionysus.todosqlite;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
//import android.support.v7.internal.widget.AdapterViewCompat.AdapterContextMenuInfo;

public class TodoOverview extends ListActivity{
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private TodoDbAdapter dbHelper;
	private Cursor cursor;
	private Button btnTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_list);
		//setDividerHeight(20); 設定 分隔線高度
		this.getListView().setDividerHeight(20);
		dbHelper = new TodoDbAdapter(this);
		dbHelper.open();
		
		//程式的最外層，判斷DB內是否有資料，沒有再加入自訂的資料。
		// Better solution: 檢查preference 檔. 是否是第一次開啟本app.
		// Check whether default record needed.
		Cursor cur = dbHelper.fetchAllTodos();
		if(cur.getCount() == 0)
		{
			dbHelper.createTodo("Reminder", "todo...", "This is first todo.");			
		}
		cur.close();
		
		fillData();
		// [***]註冊 ContextMenu到 ListActivity裡.
		//      讓ListActivity 的ListView與 MENU做連接，就會出現長按 Menu.
		//      所按的MENU會挾帶被選中的item's ID與position.
		registerForContextMenu(getListView());
		
		btnTitle = (Button)findViewById(R.id.btnTitle);
		btnTitle.setOnClickListener(btnListener);
	}

	private OnClickListener btnListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			createTodo();
		}
	};
	
	//create the menu based on the XML definition.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		//Menu檔:在 layout下的 menu(自己 new出來的).
		inflater.inflate(R.menu.listmenu, menu);
		return true;
	}
	
	//Reaction to the menu selection.
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		switch(item.getItemId()){
		case R.id.insert:
			createTodo();
			return true;
		case DELETE_ID:
//	     	 所按的MENU會挾帶被選中的item's ID與position.
			AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
			
			dbHelper.deleteTodo(info.id);
			fillData();
			Toast.makeText(TodoOverview.this, "delete action: " + info.id,  
					Toast.LENGTH_SHORT).show();
			break;
		default:
			Toast.makeText(TodoOverview.this, "Other action", Toast.LENGTH_SHORT).show();
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}

	private void createTodo() {
		Intent i = new Intent(this, TodoDetails.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}
	
	
	//ListView and view (row) on which was clicked, position and. 
	// [*****] 這個ListView是使用SimpleCursorAdapter, 我們假設這個adapter所回傳的id, 
	//         是table的第1個欄位資料, 而我們table第1個欄位是[_id], 所以這個參數id就是欄位[_id]的值.
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		Intent i = new Intent(this, TodoDetails.class);
		i.putExtra(TodoDbAdapter.KEY_ROWID, id);
		//Activity returns an result if called with startActivityForResult.
		
		startActivityForResult(i, ACTIVITY_EDIT);
	}
	
	//called with the result of the other activity
	//requestCode was the origin request code send to the activity
	//resultCode is the return code, 0 is everything is OK
	//intend can be use to get some data from the caller.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		fillData();
	}

	private void fillData() {
		cursor = dbHelper.fetchAllTodos();
		startManagingCursor(cursor);
		
		String[] from = new String[]{TodoDbAdapter.KEY_SUMMARY, TodoDbAdapter.KEY_CATEGORY};
		int[] to = new int[] {R.id.label, R.id.label2};
		
		//Now create an array adapter and set it to display using our row.
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, 
				R.layout.todo_row, cursor, from, to);
		setListAdapter(notes);
	}

	//使用 Menu時，就會call.
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(dbHelper != null){
			dbHelper.close();
		}
	}

}//end class TodoOverview.

//http://puremonkey2010.blogspot.tw/2011/09/android-sqlite.html