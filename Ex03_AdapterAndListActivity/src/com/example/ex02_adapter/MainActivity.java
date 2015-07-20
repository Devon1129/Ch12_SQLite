package com.example.ex02_adapter;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//它有它自己自訂的layout
//		setContentView(R.layout.activity_main);
		
		String[] strs = {"1", "2", "3", "4", "5"};
		
/*		Note
	  		ArrayAdapter 支持一般操作，最為簡單，只能展示一行字。
			ArrayAdapter(Context context, int resource, String[] objects)
			context:this.
			resource:布局文件,列表的每一行的布局。
				android.R.layout.simple_expandable_list_item_1是系統定義好的布局文件只顯示一行文字。
			objects:數據源。
			同時用 setAdapter()完成調配的最後工作。
		
*/

		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(
						this, 
						android.R.layout.simple_expandable_list_item_1, 
						strs);
		
		setListAdapter(adapter);
	}
}
