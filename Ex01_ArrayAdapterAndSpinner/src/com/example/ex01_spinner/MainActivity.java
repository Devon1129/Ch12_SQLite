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
		
		//�]�w�\����ذ}�C�A�ϥ�createFromResource().
		ArrayAdapter adapter = ArrayAdapter.createFromResource(
				this, 
				R.array.colors, 
				android.R.layout.simple_spinner_item);				
		
		//Q:Line 20���O�]�w�L�F?
		//�]�w���.
//		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		//�]�w adapter.
		spinner.setAdapter(adapter);
		
		// ��2��spinner�]�w
		spinner = (Spinner)findViewById(R.id.spinner2);
		adapter = ArrayAdapter.createFromResource(
				this, 
				R.array.colors, 
				android.R.layout.simple_spinner_dropdown_item);	
		spinner.setAdapter(adapter);
	}	
}//end class MainActivity.

//http://style77125tech.pixnet.net/blog/post/11866447
