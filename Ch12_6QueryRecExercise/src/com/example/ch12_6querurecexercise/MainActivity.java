package com.example.ch12_6querurecexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
//	private static final String DB_NAME = "Company";
	private static final int DBversion = 1;
	private EditText etNo;
	private Button btnSure;
	private CompDBHper dbHper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		buildViews();
	}
	
	private void buildViews(){
		etNo = (EditText)findViewById(R.id.etIdNo);
		btnSure = (Button)findViewById(R.id.btnIdSure);
		btnSure.setOnClickListener(btnSureListener);
		
		initDB();
	}
	
	private void initDB(){
		if(dbHper == null){
			dbHper = new CompDBHper(this,"Company.db", null, 1);
		}
		dbHper.createTABLE();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		initDB();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close();
			dbHper = null;
		}
	}

	private OnClickListener btnSureListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			String result = null;
			String CusNo = etNo.getText().toString().trim();
			if(CusNo.length() != 0){
				String rec = dbHper.FindRec(CusNo);
				if(rec != null){
					result = " 找到的客戶資料為:\n" + rec;
				}else{
					result = "找不到指定的客戶編號:" + CusNo;
				}
				Toast.makeText(MainActivity.this, result , Toast.LENGTH_SHORT).show();
			}
		}
	};
}