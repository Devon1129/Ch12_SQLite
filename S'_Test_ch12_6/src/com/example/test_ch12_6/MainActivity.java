package com.example.test_ch12_6;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MyDBHelper helper = new MyDBHelper(this);
        helper.AddDefaultData();
        
        Button btn = (Button) findViewById(R.id.btnIdSure);
        final EditText et = (EditText)findViewById(R.id.etIdNo);
        
        btn.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View v) {
					
				MyDBHelper h = new MyDBHelper(MainActivity.this);
				String cusName = h.GetCustomerInfo(et.getText().toString());
				Toast.makeText(MainActivity.this, cusName, Toast.LENGTH_SHORT).show();
		        
			}
		});
    }
}
