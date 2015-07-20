package com.example.sqlcontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Modify_member extends Activity implements OnClickListener{
	EditText et;
	Button edit_bt, delete_bt;
	long member_id;
	SQLController dbcon;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.modify_member);
		
		dbcon = new SQLController(this);
		dbcon.open();
		
		et = (EditText)findViewById(R.id.edit_mem_id);
		edit_bt = (Button)findViewById(R.id.update_bt_id);
		delete_bt = (Button)findViewById(R.id.delete_bt_id);
		
		Intent i = getIntent();
		String memberID = i.getStringExtra("memberID");
		String memberName = i.getStringExtra("memberName");
		
		member_id = Long.parseLong(memberID);
		et.setText(memberName);
		edit_bt.setOnClickListener(this);
		delete_bt.setOnClickListener(this);
 	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.update_bt_id:
			String memName_upd = et.getText().toString();
			dbcon.updateData(member_id, memName_upd);
			this.returnHome();
			break;
			
		case R.id.delete_bt_id:
			dbcon.deleteData(member_id);
			this.returnHome();
			break;
		}
	}
	
	//Q:setFlags.
	public void returnHome(){
		Intent home_intent = new Intent(this, MainActivity.class)
			.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(home_intent);
	}

}//end class Modify_member.
