package com.example.ex_01tototest;


import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

// 2
public class MainActivity extends ListActivity {
	private DatabaseHelper Helper;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.todo_list);
	
		this.getListView().setDividerHeight(10);
		Helper = new DatabaseHelper(this);
		
	}
	
	
}