package com.example.ex01_spinner;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
	
public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		Spinner spinner = (Spinner)findViewById(R.id.spinner1);
		
		//設定功能表項目陣列，使用createFromResource().
		ArrayAdapter adapter = ArrayAdapter.createFromResource(
				this, 
				R.array.colors, 
				android.R.layout.simple_spinner_item);				
		
		//Q:Line 20不是設定過了?
		//設定選單.
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//設定 adapter.
		spinner.setAdapter(adapter);
		
		// 第2個spinner設定
		spinner = (Spinner)findViewById(R.id.spinner2);
		adapter = ArrayAdapter.createFromResource(
				this, 
				R.array.colors, 
				android.R.layout.simple_spinner_dropdown_item);	
		spinner.setAdapter(adapter);
	}	
}//end class MainActivity.

//http://style77125tech.pixnet.net/blog/post/11866447
