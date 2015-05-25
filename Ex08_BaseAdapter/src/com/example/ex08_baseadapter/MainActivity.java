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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	private List<Map<String, Object>> mData;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mData = getData();
		MyAdapter adapter = new MyAdapter(this);
		setListAdapter(adapter);
	}
	
	private List<Map<String, Object>> getData(){
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("title", "G1");
		map.put("info", "goole 1");
		map.put("img", R.drawable.girl);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "G2");
		map.put("info", "goole 2");
		map.put("img", R.drawable.icon1);
		list.add(map);
		
		map = new HashMap<String, Object>();
		map.put("title", "G3");
		map.put("info", "goole 3");
		map.put("img", R.drawable.icon2);
		list.add(map);
		
		return list;
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Log.v("MyListView-cilck!!!", (String)mData.get(position).get("title"));
	}
	
	public void showInfo(String str, String str2){
		new AlertDialog.Builder(this)
		.setTitle("我的listview")
		.setMessage(str + "介紹..." + str2)
		.setPositiveButton("確定!", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				
			}
		})
		.show();
	}
	
	public final class ViewHolder{
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
				
				//注意；要使用自訂的 convertView去連接 View(ex:ImageView、TextView、Button...等)。
				convertView = mInflater.inflate(R.layout.item, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img);
				holder.title = (TextView)convertView.findViewById(R.id.title);
				holder.info = (TextView)convertView.findViewById(R.id.info);
				holder.viewBtn = (Button)convertView.findViewById(R.id.view_btn);
				convertView.setTag(holder);
			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			//設定元件
			holder.img.setBackgroundResource((Integer)mData.get(position).get("img"));
			holder.title.setText((String) mData.get(position).get("title"));
			holder.info.setText((String)mData.get(position).get("info"));
			
			//holder.viewBtn.setText(String.valueOf(position));
			final String titleStr = holder.title.getText().toString();
			final String infoStr = holder.info.getText().toString();
			
			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
				// 這邊是一個class 定義. 這個class是匿名的.(無名的)
				// 它實作(implement) interface OnClickListener.
				// 但它存在於一個method內, method變數不能給它用. 除非, 這個變數的值不變, 也就是final.
				
				@Override
				public void onClick(View v) {	
					// 這個v就是一個button.
					showInfo(titleStr, infoStr);
//					Button btn = (Button)v;
//					if(btn != null)
//					{
//						Toast.makeText(MainActivity.this, btn.getText(), Toast.LENGTH_SHORT).show();
//					}
					
					Toast.makeText(MainActivity.this, titleStr, Toast.LENGTH_SHORT).show();
				}
			});
			
			return convertView;
		}
	}
}