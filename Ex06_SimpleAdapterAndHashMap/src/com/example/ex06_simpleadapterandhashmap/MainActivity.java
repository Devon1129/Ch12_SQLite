package com.example.ex06_simpleadapterandhashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SimpleAdapter adapter = new SimpleAdapter(this, getData(), R.layout.activity_main,
				new String[]{"title", "info", "img"}, new int[]{R.id.title, R.id.info, R.id.img});
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "girl");
		map.put("info", "RedAny");
		map.put("img", R.drawable.girl);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "priture");
		map.put("info", "color");
		map.put("img", R.drawable.icon1);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "TEL");
		map.put("info", "Pho");
		map.put("img", R.drawable.icon2);
		list.add(map);
		
		return list;
	}
}