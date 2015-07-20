package com.example.sqlcontroller;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button addmem_bt;
	ListView lv;
	SQLController dbcon;
	TextView memID_tv, memName_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbcon = new SQLController(this);
        addmem_bt = (Button)findViewById(R.id.addmem_bt_id);
        lv = (ListView)findViewById(R.id.memberList_id);
        
        //onClickListener for addmember Button.
        addmem_bt.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent add_mem = new Intent(MainActivity.this, Add_member.class);
				startActivity(add_mem);
			}
        });
        
        //Attach The Data from DataBase into ListView using Cursor Adapter.
        dbcon.open();
        Cursor cursor  = dbcon.readData();
        String[] from = new String[]{DBHelper.MEMBER_ID, DBHelper.MEMBER_NAME};
        int[] to = new int[]{R.id.member_id, R.id.member_name};
        
        SimpleCursorAdapter adapter = 
        	new SimpleCursorAdapter(MainActivity.this, R.layout.view_member_entry,cursor, from, to);
        
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        
        //OnClickListener for List Items.
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				memID_tv = (TextView)view.findViewById(R.id.member_id);
				memName_tv = (TextView)view.findViewById(R.id.member_name);
				
				String memberID_val = memID_tv.getText().toString();
				String memberName_val = memName_tv.getText().toString();
				
				Intent modify_intent = new Intent(MainActivity.this, Modify_member.class);
//				Intent modify_intent = new Intent(getApplicationContext(), Modify_member.class);
				modify_intent.putExtra("memberName", memberName_val);
				modify_intent.putExtra("memberID", memberID_val);
				startActivity(modify_intent);
			}
        });
    }//create method end. 

}//end class MainActivity.
