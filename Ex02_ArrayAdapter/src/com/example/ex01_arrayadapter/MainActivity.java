package com.example.ex01_arrayadapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends Activity {
	ArrayAdapter<String> adapter;

//	private String[] type = new String[]{"茶類", "果汁類"};
	private static final String[] type = {"茶類", "果汁類"};
	private Spinner sp;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
		adapter = new ArrayAdapter<String>(this, R.layout.myspinner, type);
		
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		sp = (Spinner)findViewById(R.id.type);
		adapter.setDropDownViewResource(R.layout.myspinner);
		sp.setAdapter(adapter);
	}
}
//http://jim690701.blogspot.tw/2012/08/android-adapter-arrayadapter.html
//http://jim690701.blogspot.tw/2012/12/androidspinner.html
