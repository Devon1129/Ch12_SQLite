package com.example.ch12_5browserec;

import java.util.ArrayList;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etNo, etNa, etPho, etAdd;
	private Button btnNext, btnPrev;
	private DBHelper dbHelper;
	private int mIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buildViews();
        
        dbHelper = new DBHelper(this);
        dbHelper.insertRec();
        showRec(0);
    }
    
    public void buildViews(){
    	etNo = (EditText)findViewById(R.id.etIdCusNo);
    	etNa = (EditText)findViewById(R.id.etIdCusNa);
    	etPho = (EditText)findViewById(R.id.etIdCusPho);
    	etAdd = (EditText)findViewById(R.id.etIdCusAdd);
    	
    	btnNext = (Button)findViewById(R.id.btnIdNext);
    	btnPrev = (Button)findViewById(R.id.btnIdPrev);
    	
    	btnNext.setOnClickListener(bntNextListener);
    	btnPrev.setOnClickListener(bntPrevListener);
    }
    
    private OnClickListener bntNextListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    };
    
    private OnClickListener bntPrevListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
    	
    };
    
    public void showRec(int mIndex){
    	ArrayList<String> arylist = new ArrayList<String>();
    	arylist = dbHelper.getRec();
    	
    	String[] fld = arylist.get(mIndex).split("#");

    	
    	
    }
    
    public void test() {}
}
