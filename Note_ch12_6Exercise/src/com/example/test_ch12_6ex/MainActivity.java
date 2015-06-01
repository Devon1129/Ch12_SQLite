package com.example.test_ch12_6ex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private DBHelper dbHelper;
	private EditText etNo;
	private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
       buildViews();
       dbHelper = new DBHelper(this);
       dbHelper.mInsert();
    }
    
    public void buildViews(){
    	 etNo = (EditText)findViewById(R.id.etIdNo);
         btnOk = (Button)findViewById(R.id.btnIdSure);
         btnOk.setOnClickListener(btnOkListener);
    }
    
    //���ʫ�_�@�ήɡA�I�s����k�C�@�άO�F�p�G DBHelper���s�b�A�h���s�Ыؤ@�ӡC
    @Override
	protected void onResume() {
		super.onResume();
		if(dbHelper == null){
			dbHelper = new DBHelper(this);
		}
	}

    //���ʼȰ��ɡA�I�s����k�C�@�άO:���� DBHelper�A�ç� dbHelper �]�� null�A����ҥe�Ϊ��귽�C
    @Override
	protected void onPause() {
		super.onPause();
		if(dbHelper != null){
			dbHelper.close();
			dbHelper = null;
		}
		
	}

	private OnClickListener btnOkListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			String result = "";
			
			//**�`�N�������:���s����쪺 editText(cusNo)�A
			//�A�N editText��J method(FinRec)�A�o�쪺�ȡA�h���P�_�C
			String cusNo = etNo.getText().toString().trim();
			String rec = dbHelper.FinRec(cusNo);
			
			if(rec.length() != 0){
				result = "�Ȥ���:" + rec;
			}else{
				result = "�L�����:" + cusNo;
			}
			
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
		}
    };
}//end class MainActivity. 
