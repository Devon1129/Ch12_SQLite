package com.example.ex02_arrayadapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private ListView listView;
//	private List<String> data = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
/*		Note
	 		ArrayAdapter 支持一般操作，最為簡單，只能展示一行字。
			ArrayAdapter(Context context, int textViewResourceId, List<T> objects)
			context:this.
			textViewResourceId:布局文件,列表的每一行的布局。
				android.R.layout.simple_expandable_list_item_1是系統定義好的布局文件只顯示一行文字。
			objects:數據源(一个List集合)。
			同時用 setAdapter()完成調配的最後工作。
				
*/
		listView = new ListView(this);
		listView.setAdapter(
				new ArrayAdapter<String>(
					this, android.R.layout.simple_expandable_list_item_1, getData()));
		setContentView(listView);
		
/*		
		//setContentView(View view):此為自行 new 出的 view放入。一般是放入layout，
		//ex:setContentView(R.layout.activity_main);
		TextView text = new TextView(this);
		text.setText("I lvoe you");
		setContentView(text);
		
*/
	}//end class onCreate.
	
	private List<String> getData(){
		List<String> data = new ArrayList<String>();
		data.add("test1");
		data.add("test2");
		data.add("test3");
		data.add("test4");
		
		return data;
	}
}
