package com.example.ex05_simpleadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
/*				
		SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource,
			String[] from, int[] to)
*/					
		SimpleAdapter adapter =
			new SimpleAdapter(this, getData(), R.layout.activity_main,
					new String[]{"title", "img"}, new int[]{R.id.title, R.id.img});
		setListAdapter(adapter);
	}
	
	private  List<Map<String, Object>> getData(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		//map.put(參數名稱，參數值).
		map.put("title", "momo");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		//*new 新的 HashMap放入一筆新資料.
		map = new HashMap<String, Object>();
		map.put("title", "nana");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		//*new 新的 HashMap放入一筆新資料.
		map = new HashMap<String, Object>();
		map.put("title", "lala");
		map.put("img", R.drawable.ic_launcher);
		list.add(map);
		
		return list;
	}	
}