package com.example.todosqlite;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TodoDetails extends Activity{
	private EditText mTitleText, mBodyText;
	private Long mRowId;
	private TodoDbAdapter mDbHelper;
	private Spinner mCategory;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		mDbHelper = new TodoDbAdapter(this);
		mDbHelper.open();
		setContentView(R.layout.todo_edit);
		mCategory = (Spinner)findViewById(R.id.category);
		mTitleText = (EditText)findViewById(R.id.todo_edid_summary);
		mBodyText = (EditText)findViewById(R.id.todo_edit_description);
		
		Button confirmButton = (Button)findViewById(R.id.todo_edit_button);
		mRowId = null;
		Bundle extras = getIntent().getExtras();

		if(extras != null){
			mRowId = extras.getLong(TodoDbAdapter.KEY_ROWID);
		}
		populateFields();
		confirmButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				finish();
				// [***] finish()後, 會call onSaveInstanceState(...).
			}
		});
	}

	private void populateFields() {
		// [***] mRowId!=null 表示是編輯模式.
		if(mRowId != null){
			Cursor todo = mDbHelper.fetchTodo(mRowId);
			startManagingCursor(todo);
			String category = todo.getString(todo.getColumnIndexOrThrow(TodoDbAdapter.KEY_CATEGORY));
			
			// 設定spinner item 與 todo的category 一致.
			for(int i = 0; i < mCategory.getCount(); i++){
				//[***] getItemAtPosition(i) 抓位置i的item.
				String s = (String)mCategory.getItemAtPosition(i);
				Log.e(null, s + " " + category);
				//[***] equalsIgnoreCase(...) 比較字串且忽略大小寫.
				if(s.equalsIgnoreCase(category)){
					mCategory.setSelection(i);
				}
			}
						
			mTitleText.setText(todo.getString(todo.getColumnIndexOrThrow(TodoDbAdapter.KEY_SUMMARY)));
			mBodyText.setText(todo.getString(todo.getColumnIndexOrThrow(TodoDbAdapter.KEY_DESCRIPTION)));
		}
	}
	
	// 在Activity 被殺掉之前, 系統會call這個method.
	@Override
	protected void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
		saveState();
		outState.putSerializable(TodoDbAdapter.KEY_ROWID, mRowId);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		saveState();
		
	}

	@Override
	protected void onResume() {
		super.onResume();
		populateFields();
	}

	private void saveState() {
		String category = (String)mCategory.getSelectedItem();
		String summary = mTitleText.getText().toString();
		String description = mBodyText.getText().toString();
		
		// mRowId: 記住現在的todo編號
		// 如果沒有mRowId, 表示是一筆新todo.
		// 如果有mRowId, 表示是編輯todo.
		if(mRowId == null){
			long id = mDbHelper.createTodo(category, summary, description);
			if(id > 0){
				mRowId = id;
			}
		}else{
			mDbHelper.updateTodo(mRowId, category, summary, description);
		}
	}
	
}//end class TodoDetails.
