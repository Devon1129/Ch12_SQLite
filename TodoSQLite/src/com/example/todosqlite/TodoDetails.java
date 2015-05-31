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
				// [***] finish()��, �|call onSaveInstanceState(...).
			}
		});
	}

	private void populateFields() {
		// [***] mRowId!=null ��ܬO�s��Ҧ�.
		if(mRowId != null){
			Cursor todo = mDbHelper.fetchTodo(mRowId);
			startManagingCursor(todo);
			String category = todo.getString(todo.getColumnIndexOrThrow(TodoDbAdapter.KEY_CATEGORY));
			
			// �]�wspinner item �P todo��category �@�P.
			for(int i = 0; i < mCategory.getCount(); i++){
				//[***] getItemAtPosition(i) ���mi��item.
				String s = (String)mCategory.getItemAtPosition(i);
				Log.e(null, s + " " + category);
				//[***] equalsIgnoreCase(...) ����r��B�����j�p�g.
				if(s.equalsIgnoreCase(category)){
					mCategory.setSelection(i);
				}
			}
						
			mTitleText.setText(todo.getString(todo.getColumnIndexOrThrow(TodoDbAdapter.KEY_SUMMARY)));
			mBodyText.setText(todo.getString(todo.getColumnIndexOrThrow(TodoDbAdapter.KEY_DESCRIPTION)));
		}
	}
	
	// �bActivity �Q�������e, �t�η|call�o��method.
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
		
		// mRowId: �O��{�b��todo�s��
		// �p�G�S��mRowId, ��ܬO�@���stodo.
		// �p�G��mRowId, ��ܬO�s��todo.
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
