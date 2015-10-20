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


public class TodoOverview extends ListActivity{
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private TodoDbAdapter mDbAdapter;
	private Cursor cursor;
	private Button mBtnCreateToDo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_list);
		//setDividerHeight(20); 設定 分隔線高度
		this.getListView().setDividerHeight(20);
		
		//1. 產生 DB adapter. 用來讀取/寫入DB.
		mDbAdapter = new TodoDbAdapter(this);
		mDbAdapter.open();

		//2. 讀取所有todo
		//2.1 先在這裡讀一次全部todo, 以檢查是否DB無資料並加入一筆default todo.
		Cursor cur = mDbAdapter.fetchAllTodos();
		if(cur.getCount() == 0)
		{
			mDbAdapter.createTodo("Reminder", "todo...", "This is first todo.");			
		}
		cur.close();
		
		//2.2  讀取所有todo, 並填入至ListView.
		fillData();
		
		//3.當app整個結束時, 要記得關掉DB連線, 即onDestroy()內做的事.
		
		//
		// 其它View的event操作.
		//
		
		// [***]註冊 ContextMenu到 ListActivity裡.
		//      讓ListActivity 的ListView與 MENU做連接，就會出現長按 Menu.
		//      所按的MENU會挾帶被選中的item's ID與position.
		registerForContextMenu(getListView());
		
		mBtnCreateToDo = (Button)findViewById(R.id.btnTitle);
		mBtnCreateToDo.setOnClickListener(mBtnCreateTodoListener);
		
		Toast.makeText(this, "v2.1版", Toast.LENGTH_LONG).show();
	}

	private OnClickListener mBtnCreateTodoListener = new OnClickListener(){

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
	     	// 長按todo item會出現menu items, 
			//   這時選的MENU item會挾帶被選中的item's ID與position.
			
			//1. 讀取memu item的資訊(內含被選中的item's ID與position)
			AdapterContextMenuInfo info = (AdapterContextMenuInfo)item.getMenuInfo();
			//2. 使用menu item的ID做為todo ID, 並在DB內刪除此筆資料.
			mDbAdapter.deleteTodo(info.id);
			//3. 讀取所有todo, 並顯示至listview.
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
	
	//ListView and view (row) on which was clicked, position and. 
	// [*****] 這個ListView是使用SimpleCursorAdapter, 我們假設這個adapter所回傳的id, 
	//         是table的第1個欄位資料, 而我們table第1個欄位是[_id], 所以這個參數id就是欄位[_id]的值.
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		//1. NEW 一個intent
		Intent i = new Intent(this, TodoDetails.class);
		//2. 加入todo id至資料包
		i.putExtra(TodoDbAdapter.KEY_ROWID, id);
		
		//3. 啟動另一個activity, 並等待回傳的結果
		//     (當另一個activity結束, 會call onActivityResult(...), 這個method會顯示全部todo)				
		startActivityForResult(i, ACTIVITY_EDIT); //Activity returns an result if called with startActivityForResult.
	}
	
	//called with the result of the other activity
	//requestCode was the origin request code send to the activity
	//resultCode is the return code, 0 is everything is OK
	//intend can be use to get some data from the caller.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		//讀取所有todo, 並顯示至ListView.
		fillData();
	}

	private void createTodo() {
		Intent i = new Intent(this, TodoDetails.class);
		startActivityForResult(i, ACTIVITY_CREATE);
	}
	
	private void fillData() {
		cursor = mDbAdapter.fetchAllTodos();
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
		
		//產生menu view, 這裡只有一個item, [DELETE].
		menu.add(0, DELETE_ID, 0, R.string.menu_delete);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		//記得關掉DB連線(即close() method內做的事)
		if(mDbAdapter != null){
			mDbAdapter.close();
		}
	}

}//end class TodoOverview.

//http://puremonkey2010.blogspot.tw/2011/09/android-sqlite.html