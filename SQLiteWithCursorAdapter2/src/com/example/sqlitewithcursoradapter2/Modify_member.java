package com.example.sqlitewithcursoradapter2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Modify_member extends Activity implements OnClickListener{
	private EditText et, subet;
	private Button btnUpdate, btnDel;
	SQLController dbcon;
	long member_id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_member);
		
		dbcon = new SQLController(this);
		dbcon.open();
		
		et = (EditText)findViewById(R.id.edit_mem_id);
		subet = (EditText)findViewById(R.id.edit_submem_id);
		
		btnUpdate = (Button)findViewById(R.id.update_bt_id);
		btnDel = (Button)findViewById(R.id.delete_bt_id);
		
		btnUpdate.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		
		Intent i = getIntent();
		String memberID = i.getStringExtra("memberID");
		String memberName = i.getStringExtra("memberName");
		String submemberName = i.getStringExtra("membersubName");
		
		// ±N StringÂà´«¦¨ long
		member_id = Long.parseLong(memberID);	
		et.setText(memberName);
		subet.setText(submemberName);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.update_bt_id:
			String memName_upd = et.getText().toString();
			String submemberName_upd = subet.getText().toString();
			dbcon.upData(member_id, memName_upd, submemberName_upd);
			this.returnHome();
			break;
			
		case R.id.delete_bt_id:  
			dbcon.deleteData(member_id);
			this.returnHome();
			break;
		}
	}

	public void returnHome(){
		Intent home_intent = new Intent(Modify_member.this, MainActivity.class)
			.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(home_intent);
	}
}//end class Modify_member.
