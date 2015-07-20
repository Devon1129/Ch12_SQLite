package com.example.androidsmalltest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	ArrayAdapter<String> mAdapter;
	List<String> mDataset;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mDataset = new ArrayList<String>();
        mDataset.add("jack");
        mDataset.add("mary");
        
        mAdapter = new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1, mDataset);

        
        //((ListView)findViewById(R.id.listView1)).setAdapter(adapter);
        ListView listview = (ListView)findViewById(R.id.listView1);
        listview.setAdapter(mAdapter);
        
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				for(int i=0;i<1000; i++)
				{
					mDataset.add("item " + i);
//					mAdapter.notifyDataSetChanged();
				}
				mAdapter.notifyDataSetChanged();
			}
		});
        
    }
}
