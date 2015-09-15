package com.example.littepro_currencyconversion;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private TextView tvDollars, tvJPY, tvHK, tvSterling, tvEuro;
	private EditText etEnterNumber;
	private Button btnOk;
    
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buildViews();
    }
    
    public void buildViews(){
    	tvDollars = (TextView)findViewById(R.id.tvDollars);
    	tvJPY = (TextView)findViewById(R.id.tvJPY);
    	tvHK = (TextView)findViewById(R.id.tvHK);
    	tvSterling = (TextView)findViewById(R.id.tvSterling);
    	tvEuro = (TextView)findViewById(R.id.tvEuro);
    	
    	etEnterNumber = (EditText)findViewById(R.id.etEnterNumber);
    	
    	btnOk = (Button)findViewById(R.id.btnOk);
    	btnOk.setOnClickListener(okListener);
    	
    	
    	//第二版:使用 addTextChangedListener，偵測EditText
    	//輸入數字，直接換算
    	etEnterNumber.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(s.length() == 0)
				{
					tvDollars.setText("");
			    	tvJPY.setText("");
			    	tvHK.setText("");
			    	tvSterling.setText("");
			    	tvEuro.setText("");
					Toast.makeText(MainActivity.this, "nothing", Toast.LENGTH_SHORT).show();
				}
				else
				{
					try{
						double Numer = Double.parseDouble(s.toString());
						receiveNumber(Numer);
					}catch(NumberFormatException e){
						
						Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
						etEnterNumber.setText("");
					}
					
		            
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {								
			}
		});
    	
    }
    
    
    //第一版:點擊 Button後，換算幣值
    private OnClickListener okListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
//	        if(etEnterNumber.getText().toString() == ""){
	        	if(etEnterNumber.getText().toString().equals("")){
	        	Toast.makeText(MainActivity.this, "nothing", Toast.LENGTH_SHORT).show();
	        	
	        }else{
//	        	String msg;
//	        	if(etEnterNumber.getText().toString()){
//	        		
//	        	}
	        	/*
	        	double Numer = Double.parseDouble(etEnterNumber.getText().toString());
	            receiveNumber(Numer);
	            */
	        	
	        	double Numer;
	        	try
	        	{
	        		Numer = Double.parseDouble(etEnterNumber.getText().toString());
		        	receiveNumber(Numer);
	        	}
	        	catch(NumberFormatException e)
	        	{
	        		Toast.makeText(MainActivity.this, "輸入數字格式錯誤!!!", Toast.LENGTH_SHORT).show();
	        	}
	            
	        }
			
		}
    	
    };
    
      public void receiveNumber(double number){
//      public void receiveNumber(){
//    	double Digital = Double.parseDouble(number);
//    	double Digital = Double.parseDouble(etEnterNumber.getText().toString());
//    	double Dollars = Digital*0.029;
    	
    	  
    	  	//設定格式的輸出，為小數點後兩位
    	  	DecimalFormat mDecimalFormat = new DecimalFormat("#.##");
    	 	
    	  	double Dollars =  number*0.029;
	    	double JPY = number*33.53;
	    	double HK = number*0.22;
	    	double Sterling = number*0.018;
	    	double Euro = number*0.03;
	    	
	    	//使用String.format
	    	tvDollars.setText("answer:" + String.format("%.2f", Dollars));
	    	//使用DecimalFormat.format
//	    	tvDollars.setText(mDecimalFormat.format(Dollars));
	    	
	    	//mDecimalFormat.format(Object object)，這裡吃的是object type.
	    	tvJPY.setText(mDecimalFormat.format(JPY));
	    	tvHK.setText(mDecimalFormat.format(HK));
	    	tvSterling.setText(mDecimalFormat.format(Sterling));
	    	tvEuro.setText(mDecimalFormat.format(Euro));
	    	
	    	//轉換 double成 String，給TextView呈現
//	    	tvDollars.setText(String.valueOf(Dollars));
//	    	tvJPY.setText(String.valueOf(JPY));
//	    	tvHK.setText(String.valueOf(HK));
//	    	tvSterling.setText(String.valueOf(Sterling));
//	    	tvEuro.setText(String.valueOf(Euro));
	    
    }
}
