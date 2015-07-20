package com.example.ex07_simplecursoradapter;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//指向通訊錄數據庫的 cursor，來獲得資料來源。
		Cursor cur = getContentResolver().query(People.CONTENT_URI, null, null, null, null);
        startManagingCursor(cur);
        
        //連接 Devices設備(手機)裡的資訊。
        ListAdapter adapter = 
    		new SimpleCursorAdapter(
    				this,android.R.layout.simple_list_item_2,cur,
    				new String[] {People.NAME, People.NOTES}, 
    				new int[] {android.R.id.text1, android.R.id.text2});

        setListAdapter(adapter);
	}
}
//http://www.cnblogs.com/devinzhang/archive/2012/01/20/2328334.html