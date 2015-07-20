package com.example.androidsmalltest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Test2 extends Activity{

	ArrayAdapter<String> mAdapter;
	List<String> mDataset;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mDataset = new ArrayList<String>();
        mDataset.add("Benjemin");
        mDataset.add("Washton");
        
        mAdapter = new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1, mDataset);
        
        ListView listview = (ListView)findViewById(R.id.listView1);
        listview.setAdapter(mAdapter);
	}

}
