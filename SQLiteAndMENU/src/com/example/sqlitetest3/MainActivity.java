package com.example.sqlitetest3;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity {
	private ListView myListView;
	private EditText myEditText;
	MySQLiteOpenHelper myHelper;
	private Cursor myCursor;
	private int _ID;
	protected static final int MENU_ADD = Menu.FIRST;
	protected static final int MENU_EDIT = Menu.FIRST + 1;
	protected static final int MENU_DELETE = Menu.FIRST + 2;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		//(�s�աA�ѧO�X�A���s�X�{�����ǡA��ܪ���r)
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
		
		//�إ߳s��
		myListView = (ListView)findViewById(R.id.listView1);
		myEditText = (EditText)findViewById(R.id.editText1);
		
		myHelper = new MySQLiteOpenHelper(this);
		
		myCursor = myHelper.select();
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(
				this, 
				R.layout.list, 
				myCursor, 
				new String[] {myHelper.Field_Text}, 
				new int[] {R.id.listTextView1});
		
		myListView.setAdapter(adapter);

		//��ťListView�O�_���Q�I��
		myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//cursor���ʨ�Q�I������m�W
				myCursor.moveToPosition(arg2);
				//cursor�����0����쵹 _ID.
				_ID = myCursor.getInt(0);
				//cursor�����1����쵹EditText.
				myEditText.setText(myCursor.getString(1));				
			}
		});
			
		//��ť ListView�O�_���Q���.
		myListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				//��o�@�� SQLiteCursor���O������A���V SQLite��Ʈw�d�ߪ����G.
				SQLiteCursor sc = (SQLiteCursor) arg0.getSelectedItem();
				//���o�ӵ���ƪ���0����줺�e�A��ƫ��A�����.
				_ID = sc.getInt(0);
				//���o�ӵ���ƪ���1����줺�e�A��ƫ��A���r��A�N��ƶ�J myEditText��.
				myEditText.setText(sc.getString(1));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void addTodo() {
		if(myEditText.getText().toString().equals("")){
			return;
		}else{
			//�I�s SQLite�� insert()��k�A�N��J��줺����ƴ��J��Ʈw��.
			myHelper.insert(myEditText.getText().toString());
			
			//���s�d�߸�Ʈw�����e.
			myCursor.requery();
			//���s��� ListView�����e +�M����J��쪺���+ _ID�k0.
			//invalidateViews:��View���ġC
			//�|�ɭPandroid���e�e��(���s��� ListView�����e)�C
			myListView.invalidateViews();
			myEditText.setText("");
			_ID = 0;
		}
	}
	

	private void editTodo() {
		if(myEditText.getText().toString().equals("")){
			return;
		}else{
			myHelper.update(_ID, myEditText.getText().toString());
			
			//���s�d�߸�Ʈw�����e
			myCursor.requery();
			//���s��� ListView�����e + �M����J��쪺���+ _ID�k0.
			myListView.invalidateViews();
			myEditText.setText("");
			_ID = 0;
		}
	}

	private void deleteTodo() {
		if(_ID == 0){
			return;
		}else{
			//�I�s SQLite��delete()��k.
			myHelper.delete(_ID);
			
			//���s�d�߸�Ʈw�����e.
			myCursor.requery();
			//���s��� ListView�����e+ �M����J��쪺���+ _ID�k0.
			myListView.invalidateViews();
			myEditText.setText("");
			_ID = 0;
		}
	}
}//end class MainActivity.
