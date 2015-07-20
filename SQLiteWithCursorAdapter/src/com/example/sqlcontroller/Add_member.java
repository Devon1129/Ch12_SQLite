package com.example.sqlcontroller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Add_member extends Activity implements OnClickListener{
	@Override
	protected void onDestroy() {
		super.onDestroy();
		dbcon.close();
	}

	EditText et;
	Button add_bt, read_bt;
	SQLController dbcon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_member);
		et = (EditText)findViewById(R.id.member_et_id);
		add_bt = (Button)findViewById(R.id.add_bt_id);
		
		dbcon = new SQLController(this);
		dbcon.open();
		add_bt.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.add_bt_id:
			String name = et.getText().toString();
			dbcon.insertData(name);
			Intent main = 
				new Intent(Add_member.this, MainActivity.class)
					//�]�w�M�� activity(�Q�� stack���[��)�C
					//�M���ت��a�H�W�� activity:
					//e.g.main��B��C��D�A�^�� main�ɡAD�BC�BB�|�Q�M���C
					//���P finish();
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(main);
			break;
		default:
			break;
		}
	}

}//end class Add_member.
