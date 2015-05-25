package com.example.ex08_baseadapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Test  extends ListActivity{
	private List<Map<String, Object>> mData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
		
		
	}

	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "food");
		map.put("info", "K.F.C");
		map.put("img", R.drawable.icon1);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "pho");
		map.put("info", "LG");
		map.put("img", R.drawable.icon2);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "girl");
		map.put("info", "Any");
		map.put("img", R.drawable.girl);
		list.add(map);
		
		return list;
	}


	public void showInfo() {
		new AlertDialog.Builder(this)
		.setTitle("Title")
		.setMessage("It is Message")
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();
		
	}
	
	public class ViewHolder{
		public ImageView img;
		public TextView title, info;
		public Button viewBtn;
		
	}
	
	public class MyAdapter extends BaseAdapter{
		private LayoutInflater mInflater;
		
		public MyAdapter(Context context){
			 this.mInflater = LayoutInflater.from(context);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null; 
			if(convertView == null){
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.item, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img);
				holder.info = (TextView)convertView.findViewById(R.id.info);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			holder.img.setBackgroundResource((Integer) mData.get(position).get("img"));
			holder.info.setText((String)mData.get(position).get("info"));
			holder.title.setText((String)mData.get(position).get("title"));
			
			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					showInfo();
				}
			});
			
			return convertView;
		}
		
	}
}
