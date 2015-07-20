package com.example.ch12_4_5;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etNo, etNa, etPho, etAdd;
	private Button btnIns, btnDel;
	private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buildViews();
        
        dbHelper = new DBHelper(this);
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		if(dbHelper == null){
			dbHelper = new DBHelper(this);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(dbHelper != null){
			dbHelper.close();
			dbHelper = null;
		}
	}

	public void buildViews(){
    	etNo = (EditText)findViewById(R.id.etldCusNo);
    	etNa = (EditText)findViewById(R.id.etldCusNa);
    	etPho = (EditText)findViewById(R.id.etldCusPho);
    	etAdd = (EditText)findViewById(R.id.etldCusAdd);
    	
    	btnIns = (Button)findViewById(R.id.btldIns);
    	btnDel = (Button)findViewById(R.id.btldCancel);
    	
    	btnIns.setOnClickListener(btnInsListener);
    	btnDel.setOnClickListener(btnDelListener);
    	
    }
    
    private OnClickListener btnInsListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			String cusNo = etNo.getText().toString().trim();
			String cusNa = etNa.getText().toString().trim();
			String cusPho = etPho.getText().toString().trim();
			String cusAdd = etAdd.getText().toString().trim();
			
			if(cusNo.equals("") || cusNa.equals("")){
				Toast.makeText(MainActivity.this, "重新輸入...", Toast.LENGTH_SHORT).show();
				return;
			}
			
			
			String msg = null;
			long rowID = dbHelper.insertRec(cusNo, cusNa, cusPho, cusAdd);
			if(rowID == -1){
				msg = "輸入失敗!!!";
			}else{
				msg = "新增記錄!!!" + "共" + dbHelper.count() + "筆資料";
			}
			Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
		}
    };
    
    private OnClickListener btnDelListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			etNo.setText("");
			etNa.setText("");
			etPho.setText("");
			etAdd.setText("");
			
		}
    	
    };
}
