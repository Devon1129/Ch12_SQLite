package com.example.ch12_7test;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	//**class DBHelper 裡的變數，在這 class MainActitity不能使用，
	//    但是可以使用一樣的名稱變數。它們有存活範圍，只在{}內。e.g.aryRec.
	private DBHelper dbHelper;
	private EditText etNo, etNa, etPho, etAdd;
	private Button btnNext, btnPrev, btnEdit, btnDel;
	private ArrayList<String> aryRec;
	private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //**使用的變數，要先找出來
        buildViews();
        
        dbHelper = new DBHelper(this);
        dbHelper.insertRec();
        //**呼叫的 method，如果有回傳資料，並且有需要使用到，
        //要把它存在變數裡，才能夠做使用。
        aryRec = dbHelper.getRec();
        showRec(index);
        
        //錯誤:變數出現 null。參考**line27
//        buildViews();
    }
    
    public void buildViews(){
    	etNo = (EditText)findViewById(R.id.etldCusNo);
    	etNa = (EditText)findViewById(R.id.etldCusNa);
    	etPho = (EditText)findViewById(R.id.etldCusPho);
    	etAdd = (EditText)findViewById(R.id.etldCusAdd);
    	
    	btnNext = (Button)findViewById(R.id.btldNext);
    	btnPrev = (Button)findViewById(R.id.btldPrev);
    	btnEdit = (Button)findViewById(R.id.btldEdit);
    	btnDel = (Button)findViewById(R.id.btldDel);
    	
    	btnNext.setOnClickListener(btnNextListener);
    	btnPrev.setOnClickListener(btnPrevListener);
    	btnEdit.setOnClickListener(btnEditListener);
    	btnDel.setOnClickListener(btnDelListener);
    }
    
    public OnClickListener btnNextListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    };
    
    public OnClickListener btnPrevListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    };
    
    public OnClickListener btnEditListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    };
    
    public OnClickListener btnDelListener = new OnClickListener(){
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    };
    
	
	public void showRec(int index){
		if(aryRec.size() != 0){
			String stHead = "顯示客戶資料:第 " + (index + 1) + "筆/共 " +
					aryRec.size() + "筆";
			
			//Q: split("#")??使用在哪?
			String[] fld = aryRec.get(index).split("#");
			etNo.setText(fld[0]);
			etNa.setText(fld[1]);
			etPho.setText(fld[2]);
			etAdd.setText(fld[3]);
		}else{
			etNo.setText("");
			etNa.setText("");
			etPho.setText("");
			etAdd.setText("");
		}
	}
    
    
}
