package com.example.applitteprogram;

import java.text.ParseException;
import java.util.Calendar;
//import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
//import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText etYYYY, etMM, etdd;
	private Button btnResult;
	private Test test;
	private String msg;
	private int result;
	private int[] MMdate = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        buildViews();
        test = new Test();
       
        //測試日期計算
//        try {
//        	long Show = test.timeShow2(1983,11,29);
//			
//			Toast.makeText(MainActivity.this, Show + "", Toast.LENGTH_LONG).show();
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
        
    }
    
    private void buildViews(){
    	etYYYY = (EditText)findViewById(R.id.etYYYY);
        etMM = (EditText)findViewById(R.id.etMM);
        etdd = (EditText)findViewById(R.id.etdd);
        
        btnResult = (Button)findViewById(R.id.btnResults);
        btnResult.setOnClickListener(ResultListener);
    }
    
    private OnClickListener ResultListener = new OnClickListener(){
    /*	 生object的順序: initializer=>constructor=> android life cycle
    	 etYYYY/etMM/etdd 是在life cycle才給值, 但在initializer就用到它們了, 這時的值是null, 
    	 因此產生 nullPointerException.
    */
//    	String year = etYYYY.getText().toString().trim();
//    	String month = etMM.getText().toString().trim();
//    	String day =  etdd.getText().toString().trim();

		@Override
		public void onClick(View v) {
			String year = etYYYY.getText().toString().trim();
	    	String month = etMM.getText().toString().trim();
	    	String day =  etdd.getText().toString().trim();
			
	    	//輸入欄位不得為空
			if(year.equals("") || month.equals("") || day.equals("")){
				msg = "請輸入完整的出生年月日!!!";
				ShowMsgAlertDialog(msg);
				return;
			}
			
			//抓取輸入的年份
			int YYYY = Integer.parseInt(etYYYY.getText().toString());
			
			if(test.isLeapYear(YYYY)){
				try {
					ageCalculate();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				msg = "你現在輸入的實際年齡為 " + result + "歲\n";
//				ShowMsgAlertDialog(msg + "\n此年是閏年!!!");
			}else{
				try {
					ageCalculate();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				msg = "你現在輸入的實際年齡為 " + result + "歲\n";
//				ShowMsgAlertDialog(msg);
			}

		}//end onClick.   	
    };//end ResultListener.
    
    //計算年齡
    private String ageCalculate() throws ParseException{
    	int YYYY = Integer.parseInt(etYYYY.getText().toString());
		int MM = Integer.parseInt(etMM.getText().toString());
		int dd = Integer.parseInt(etdd.getText().toString());

		
		int yearNow = Calendar.getInstance().get(Calendar.YEAR);
//		int monthNow = Calendar.getInstance().get(Calendar.MONTH) + 1;
//		int dayNow = Calendar.getInstance().get(Calendar.DATE);
		
		//判斷輸入的年、月、日
    	if( (YYYY > yearNow) || (YYYY < 1) || (MM > 12) || (MM < 1) ) 
		{
			msg = "你所輸入的出生[年月]不正確!!!";
			ShowMsgAlertDialog(msg);
			return msg;
		}
    	
    	//判斷2月，是否為閏年
    	if(MM == 2 && test.isLeapYear(YYYY))
    	{
    		if((dd > 29 || dd < 1))
    		{
//    			msg = "你所輸入的出生[年月]不正確!!!";
    			msg = "你所輸入的日期不正確!!!";
    			ShowMsgAlertDialog(msg);
    			return msg;
    			
    		}else{
    			int result = test.timeShow2(YYYY, MM, dd);
    			msg = "你現在輸入的實際年齡為 " + result + "歲\n";
				ShowMsgAlertDialog(msg + "此年是閏年!!!");
    			return msg;
				
    		}
    	}else{
    		if(dd > MMdate[MM] || dd < 1){
    			msg = "你所輸入的日期不正確!!!";
    			ShowMsgAlertDialog(msg);
    			return msg;
    			
    		}else{
    			int result = test.timeShow2(YYYY, MM, dd);
    			msg = "你現在輸入的實際年齡為 " + result + "歲\n";
    			ShowMsgAlertDialog(msg);
    			return msg;
    			
    		}
    	}
    	
//    	
//    	if(test.isLeapYear(YYYY) && (dd > 29 || dd < 1))
//    	{
//    		msg = "你所輸入的出生[日期]不正確!!!";
//			ShowMsgAlertDialog(msg);
//			return;
//    	}
//    	else if(dd > MMdate[MM] || dd < 1)
//    	{
//    		msg = "你所輸入的出生[日期]不正確!!!";
//			ShowMsgAlertDialog(msg);
//			return;
//    	}
    	
//    	if(dd > MMdate[MM] || dd < 1){
//			msg = "你所輸入的出生月日不正確!!!";
//			ShowMsgAlertDialog(msg);
//			
//		}else{
//			if(MM == 2 && dd > 29)
//			{
//				ageCalculate();
//				return;
//			}
			
//			if((MM==1 || MM==3 ) && dd >31)
//			{
//				//...
//				return;
//			}
//			// if 其它月份...
//			
//			if(YYYY < 1912 || MM == 2 && dd > 29){
//				msg = "你所輸入的出生月日不正確!!!";
//				ShowMsgAlertDialog(msg);
//				return;
//			}
//			
//			//1 3 5 7 8 10 12 dd到31.
//			if(YYYY < 1912 || 
//					(MM == 1 && dd > 31) || (MM == 3 && dd > 31) || (MM == 5 && dd > 31) ||
//					(MM == 7 && dd > 31) || (MM == 8 && dd > 31) || (MM == 10 && dd > 31) || 
//					(MM == 12 && dd > 31)){
//						msg = "你所輸入的出生月日不正確!!!";
//						ShowMsgAlertDialog(msg);
//						return;
//			}
//				
//			if(YYYY < 1912 || 
//					(MM == 4 && dd > 30) || (MM == 6 && dd > 30) || (MM == 9 && dd > 30) ||
//					(MM == 11 && dd > 30)){
//						msg = "你所輸入的出生月日不正確!!!";
//						ShowMsgAlertDialog(msg);
//			}else{
//				try {
//		        	long Show = test.timeShow2(YYYY, MM, dd);
//		        	String age;
//		        	age = String.valueOf(Show);
//		        	msg = "你現在輸入的實際年齡為 " + age + "歲";
//				
//					
////					Toast.makeText(MainActivity.this, Show + "", Toast.LENGTH_LONG).show();
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			
//				ShowMsgAlertDialog(msg);
//			}
//			msg = "你現在輸入的實際年齡為 " + result + "歲";
//			ShowMsgAlertDialog(msg);
//		}
    }

    //顯示訊息對話框
    private void ShowMsgAlertDialog(String msg){
    	Builder MyAlertDialog = new AlertDialog.Builder(this);
//    	MyAlertDialog.setTitle("你現在輸入的實際年齡為 " + msg + "歲");
    	MyAlertDialog.setMessage(msg);
    	DialogInterface.OnClickListener OkClick = new DialogInterface.OnClickListener() {

    		@Override
			public void onClick(DialogInterface dialog, int which) {
    			
			}//end onClick.
		};//end okClick.
		MyAlertDialog.setNeutralButton("確定!!!", OkClick);
		MyAlertDialog.show();
    	
    }//end ShowMsgAlertDialog.
 }
