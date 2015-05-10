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
				msg = "���� SQL ���~!";
			}else if(rowsAffected == 0){
				//��ƪ��� �� �S���ŦX���󪺸��(����KEY(CusNo)����)���šC 
				msg = "�䤣����R���������A�L�k�R��!";
			}else{
				msg = "�쥻����" + (mIndex + 1) + "������ �w�R��!\n" + 
						"�@" + rowsAffected + "���O�� �Q�R��!";
				recSet = dbHper.getRecSet();
				//�`�NmIndex�P showRec()���P�B�C
				//��:���UButton btnPrev �� btnNext ��ܪ��e���A���ݬO�̧Ǵ`���C
				//��ܲĤ@�����
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
			String stHead = "��ܫȤ���:��" + (index + 1) + 
				"��/�@" + recSet.size() + "��";
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
