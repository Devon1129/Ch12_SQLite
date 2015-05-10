package com.example.ch12_5browserecexercise;

import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
//	private static final String DB_NAME = "Company";
	private static final int DBversion = 1;
	private EditText etCusNo, etCusNa, etCusPho, etCusAdd;
	private TextView tvTitle;
	private Button  btnNext, btnPrev, btnDel;
	private CompDBHper dbHper;
	private ArrayList<String> recSet;
	private int mIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_main);
		
		buildViews();		
	}
	
	private void buildViews(){
		etCusNo = (EditText)findViewById(R.id.etIdCusNo);
		etCusNa = (EditText)findViewById(R.id.etIdCusNa);
		etCusPho = (EditText)findViewById(R.id.etIdCusPho);
		etCusAdd = (EditText)findViewById(R.id.etIdCusAdd);
		tvTitle = (TextView)findViewById(R.id.tvTitle);
		btnNext = (Button)findViewById(R.id.btnIdNext);
		btnPrev = (Button)findViewById(R.id.btnIdPrev);
		btnDel = (Button)findViewById(R.id.btnIdDel);
		btnNext.setOnClickListener(btnNextListener);
		btnPrev.setOnClickListener(btnPrevListener);
		btnDel.setOnClickListener(btnDelListener);
		
		//loadRecordsFromDB();
		//showRec(mIndex);
	}
	
	private void loadRecordsFromDB(){
		if(dbHper == null){
			dbHper = new CompDBHper(this, "Company.db", null, DBversion);
		}
		dbHper.createTABLE();
		try
		{
			recSet = dbHper.getRecSet();
		}
		catch(Exception e)
		{
			Log.d("getRecSet", e.toString());
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		loadRecordsFromDB();
		showRec(mIndex);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if(dbHper != null){
			dbHper.close();
			dbHper = null;
		}
		recSet.clear();
	}

	private OnClickListener btnNextListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			mIndex++;
			if(mIndex >= recSet.size()){
				mIndex = 0;
			}
			showRec(mIndex);
		}
	};
	
	private OnClickListener btnPrevListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			mIndex--;
 			if(mIndex < 0){
				mIndex = recSet.size() - 1;
			}
			showRec(mIndex);
		}
	};
	
	private OnClickListener btnDelListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			String CusNo = etCusNo.getText().toString().trim();
			String msg;
			try{
			int rowsAffected = dbHper.deleteRec(CusNo);
			if(rowsAffected == -1){
				msg = "執行 SQL 錯誤!";
			}else if(rowsAffected == 0){
				//資料表為空 或 沒有符合條件的資料(此為KEY(CusNo)不符)不符。 
				msg = "找不到欲刪除的紀錄，無法刪除!";
			}else{
				msg = "原本的第" + (mIndex + 1) + "筆紀錄 已刪除!\n" + 
						"共" + rowsAffected + "筆記錄 被刪除!";
				recSet = dbHper.getRecSet();
				//注意mIndex與 showRec()的同步。
				//例:按下Button btnPrev 或 btnNext 顯示的畫面，必需是依序循環。
				//顯示第一筆資料
				mIndex = 0;
				showRec(mIndex);
				Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
			}
			}catch(Exception e){
				Log.w("Warn", e.toString());
				
			}
		}
	};
	
	private void showRec(int index){
		if(recSet.size() != 0){
			String stHead = "顯示客戶資料:第" + (index + 1) + 
				"筆/共" + recSet.size() + "筆";
			tvTitle.setTextColor(Color.RED);
			tvTitle.setText(stHead);
			Toast.makeText(MainActivity.this, 
				recSet.get(index),
				Toast.LENGTH_SHORT)
				.show();
			
			String[] fld = recSet.get(index).split("#");
			etCusNo.setText(fld[0]);
			etCusNa.setText(fld[1]);
			etCusPho.setText(fld[2]);
			etCusAdd.setText(fld[3]);
		}else{
			etCusNo.setText("");
			etCusNa.setText("");
			etCusPho.setText("");
			etCusAdd.setText("");
		}		
	}
}
