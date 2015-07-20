package com.example.ch12_7test;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	//**class DBHelper �̪��ܼơA�b�o class MainActitity����ϥΡA
	//    ���O�i�H�ϥΤ@�˪��W���ܼơC���̦��s���d��A�u�b{}���Ce.g.aryRec.
	private DBHelper dbHelper;
	private EditText etNo, etNa, etPho, etAdd;
	private Button btnNext, btnPrev, btnEdit, btnDel;
	private ArrayList<String> aryRec;
	private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //**�ϥΪ��ܼơA�n����X��
        buildViews();
        
        dbHelper = new DBHelper(this);
        dbHelper.insertRec();
        //**�I�s�� method�A�p�G���^�Ǹ�ơA�åB���ݭn�ϥΨ�A
        //�n�⥦�s�b�ܼƸ̡A�~������ϥΡC
        aryRec = dbHelper.getRec();
        showRec(index);
        
        //���~:�ܼƥX�{ null�C�Ѧ�**line27
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
			String stHead = "��ܫȤ���:�� " + (index + 1) + "��/�@ " +
					aryRec.size() + "��";
			
			//Q: split("#")??�ϥΦb��?
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
