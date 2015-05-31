package com.example.todosqlite;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
//import android.support.v7.internal.widget.AdapterViewCompat.AdapterContextMenuInfo;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class TodoOverview extends ListActivity{
	private static final int ACTIVITY_CREATE = 0;
	private static final int ACTIVITY_EDIT = 1;
	private static final int DELETE_ID = Menu.FIRST + 1;
	private TodoDbAdapter dbHelper;
	private Cursor cursor;
	private long mClickedItemId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_list);
		this.getListView().setDividerHeight(2);
		dbHelper = new TodoDbAdapter(this);
		dbHelper.open();
		fillData();
		// [***]���U ContextMenu�� ListActivity��.
		//      ��ListActivity ��ListView�P MENU���s���A�N�|�X�{���� Menu.
		//      �ҫ���MENU�|���a�Q�襤��item's ID�Pposition.
		registerForContextMenu(getListView());
		
		
	}

	//create the menu based on the XML definition.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		//Menu��:�b layout�U�� menu(�ۤv new�X�Ӫ�).
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
//	     	 �ҫ���MENU�|���a�Q�襤��item's ID�Pposition.
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
	// [*****] �o��ListView�O�ϥ�SimpleCursorAdapter, �ڭ̰��]�o��adapter�Ҧ^�Ǫ�id, 
	//         �Otable����1�������, �ӧڭ�table��1�����O[_id], �ҥH�o�ӰѼ�id�N�O���[_id]����.
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		mClickedItemId = id;
		
		Intent i = new Intent(this, TodoDetails.class);
		i.putExtra(TodoDbAdapter.KEY_ROWID, id);
		//Activity returns an result if called with startActivityForResult.
		
		startActivityForResult(i, ACTIVITY_EDIT);
	}
	
	//called with the result of the other activity
	//requestCode was the origin request code send to the activity
	//resultCode is the return code, 0 is everything is ok
	//intend can be use to get some data from the caller.
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		fillData();
	}

	private void fillData() {
		cursor = dbHelper.fetchAllTodos();
		startManagingCursor(cursor);
		
		String[] from = new String[]{TodoDbAdapter.KEY_SUMMARY};
		int[] to = new int[] {R.id.label};
		
		//Now create an array adapter and set it to display using our row.
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, 
				R.layout.todo_row, cursor, from, to);
		setListAdapter(notes);
	}

	//�ϥ� Menu�ɡA�N�|call.
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