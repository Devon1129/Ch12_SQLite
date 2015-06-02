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
			String result = "";
			
			//**注意抓取順序:先存取抓到的 editText(cusNo)，
			//再將 editText放入 method(FinRec)，得到的值，去做判斷。
			String cusNo = etNo.getText().toString().trim();
			String rec = dbHelper.FinRec(cusNo);
			
			if(rec.length() != 0){
				result = "客戶資料:" + rec;
			}else{
				result = "無此資料:" + cusNo;
			}
			
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
		}
    };
}//end class MainActivity. 
