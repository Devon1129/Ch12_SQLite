package com.example.ch12_4test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DBHelper dbHelper;
	private EditText etNo, etNa, etPho, etAdd;
	private Button btnAdd, btnCancel;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbHelper = new DBHelper(this);
        
        buildViews();
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
    	
    	btnAdd = (Button)findViewById(R.id.btldIns);
    	btnCancel = (Button)findViewById(R.id.btldCancel);
    	
    	btnAdd.setOnClickListener(btnAddListener);
    	btnCancel.setOnClickListener(btnCancelListener);
    }
    
    private OnClickListener btnAddListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			String CusNo = etNo.getText().toString().trim();
			String CusNa = etNa.getText().toString().trim();
			String CusPho = etPho.getText().toString().trim();
			String CusAdd = etAdd.getText().toString().trim();
			if(CusNo.equals("") || CusNa.equals("")){
				Toast.makeText(MainActivity.this, "�п�J���s�W���Ȥ���!", Toast.LENGTH_SHORT).show();
				return;
			}
			
			long rowID = dbHelper.insertRec(CusNo, CusNa, CusPho, CusAdd);
			
			//**���P�_�ɡA�n�N���v������b�e���C
			//�{������ɡA���榨�\���P�_�n���e���P�_���n�C
			String msg = null;
			if(rowID != -1){
				msg = "�s�W�O�� ���\!\n" +
						"�ثe�Ȥ��ƪ�@�� " + dbHelper.recCount() + "��";
			}else{
				msg = "�s�W���� ����!";
			}
			Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
		} 	
    };
    
    private OnClickListener btnCancelListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			etNo.setText("");
			etNa.setText("");
			etPho.setText("");
			etAdd.setText("");	
		}    	
    };
}
