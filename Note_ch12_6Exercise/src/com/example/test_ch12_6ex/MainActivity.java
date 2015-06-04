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
    
    //活動恢復作用時，呼叫此方法。作用是；如果 DBHelper不存在，則重新創建一個。
    @Override
	protected void onResume() {
		super.onResume();
		if(dbHelper == null){
			dbHelper = new DBHelper(this);
		}
	}

    //活動暫停時，呼叫此方法。作用是:關閉 DBHelper，並把 dbHelper 設為 null，釋放所占用的資源。
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
			//**注意抓取順序:editText(cusNo)→判斷 cusNo→ method FinRec →判斷 rec。
			
			String cusNo = etNo.getText().toString().trim();
		/*
			判斷 String的可能性有三種:null、""(空字串)、"..."(有值)。
			     做判斷時將可能性各別帶入條件式，導出最後結果是否與所想的一樣。
			   e.g. if(cusNo != null && cusNo.length() != 0 )
			   1. "...":
			      (true && true)-> true
			   2. "":
			      (true && false)-> false
			   3. null:
			      (false && Exception)-> false(因為第一個判斷為 false，就不再判斷了)。
		 * 
		 */
			if(cusNo.length() != 0 ){
				String rec = dbHelper.FinRec(cusNo);
				String result;
				if(rec != null && rec.length() != 0){
					result = "客戶資料:" + rec;
				}else{
					result = "無此資料:" + cusNo;
				}	
				Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
			}
			
		}
    };
}//end class MainActivity. 
