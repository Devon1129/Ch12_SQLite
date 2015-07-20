package com.example.sqlitewithcursoradapter2;

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
	private Button btnAdd;
	private ListView lv;
	private TextView tvID, tvName, tvsubName;
	SQLController dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        dbcon = new SQLController(this);
        dbcon.open();
        
        btnAdd = (Button)findViewById(R.id.addmem_bt_id);
        lv = (ListView)findViewById(R.id.memberList_id);
        
        btnAdd.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent add_mem = new Intent(MainActivity.this, Add_member.class);
				startActivity(add_mem);
			}
        });
        
        Cursor cursor = dbcon.queryData();
        String[] from = new String[]{DBHelper.Table_ID, DBHelper.Table_MEMBER, DBHelper.Table_MEMBER2};
        int[] to = new int[]{R.id.member_id, R.id.member_name, R.id.member_subname};
        
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
        	MainActivity.this, R.layout.view_member_entery, cursor, from, to);
        
        //ListView的動態刷新紀錄。
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				tvID = (TextView)view.findViewById(R.id.member_id);
				tvName = (TextView)view.findViewById(R.id.member_name);
				tvsubName = (TextView)view.findViewById(R.id.member_subname);
				
				String memberID_val = tvID.getText().toString();
				String memberName_val = tvName.getText().toString();
				String membersbuName_val = tvsubName.getText().toString();
				
				Intent modify_intent = new Intent(MainActivity.this, Modify_member.class);
				
				//利用 Intent綁定 tvID 與 tvName傳到 Modity_member.java . 
				modify_intent.putExtra("memberID", memberID_val);
				modify_intent.putExtra("memberName", memberName_val);
				modify_intent.putExtra("membersubName", membersbuName_val);
				startActivity(modify_intent);
			}
        });
        
    }//end method onCreate.

}//end class MainActivity.
