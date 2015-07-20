package com.example.sqlitewithcursoradapter2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Add_member extends Activity implements OnClickListener{
	private EditText et, subet;
	private Button btnAdd;
	SQLController dbcon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_member);
		
		et = (EditText)findViewById(R.id.member_et_id);
		subet = (EditText)findViewById(R.id.member_subet_id);
		btnAdd = (Button)findViewById(R.id.add_bt_id);
		
		dbcon = new SQLController(this);
		dbcon.open();
		btnAdd.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.add_bt_id:
				String name = et.getText().toString();
				String subName = subet.getText().toString();
				dbcon.insertData(name, subName);
				Intent main = new Intent(Add_member.this, MainActivity.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(main);
				break;
			default:
				break;
		}
	}

}//end class Add_member.
