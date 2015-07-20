package com.example.todosqlite_simple;


import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

public class TodoOverview extends ListActivity{

	private TodoDatabaseHelper dbHelper;
	private Cursor cursor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_list);
		//setDividerHeight(20); �]�w ���j�u����
		this.getListView().setDividerHeight(20);
		dbHelper = new TodoDatabaseHelper(this);
		
		fillData();
//		dbHelper.createTodo("urgent", "todo4", "insert after opening readable connection.");
//		fillData();
	}
	
	private void fillData() {
		//�^�� cursor ���������.
		cursor = dbHelper.fetchAllTodos();
		startManagingCursor(cursor);
		
		//�]�w�ݭn�����.
		String[] from = new String[]{TodoDatabaseHelper.KEY_SUMMARY, TodoDatabaseHelper.KEY_CATEGORY};
//		int[] to = new int[] {R.id.label};
		//��������m.
		int[] to = new int[] {R.id.label, R.id.label2};
		
		//Now create an array adapter and set it to display using our row.
		SimpleCursorAdapter notes = new SimpleCursorAdapter(this, 
				R.layout.todo_row, cursor, from, to);
		setListAdapter(notes);
		
	}

}
