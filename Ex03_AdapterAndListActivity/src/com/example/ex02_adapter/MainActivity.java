package com.example.ex02_adapter;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

public class MainActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//�������ۤv�ۭq��layout
//		setContentView(R.layout.activity_main);
		
		String[] strs = {"1", "2", "3", "4", "5"};
		
/*		Note
	  		ArrayAdapter ����@��ާ@�A�̬�²��A�u��i�ܤ@��r�C
			ArrayAdapter(Context context, int resource, String[] objects)
			context:this.
			resource:�������,�C���C�@�檺�����C
				android.R.layout.simple_expandable_list_item_1�O�t�Ωw�q�n���������u��ܤ@���r�C
			objects:�ƾڷ��C
			�P�ɥ� setAdapter()�����հt���̫�u�@�C
		
*/

		ArrayAdapter<String> adapter = 
				new ArrayAdapter<String>(
						this, 
						android.R.layout.simple_expandable_list_item_1, 
						strs);
		
		setListAdapter(adapter);
	}
}
