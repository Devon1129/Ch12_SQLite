package com.example.bubbletest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    @Override
    protected void onResume() {
    super.onResume();

    TextView textView = (TextView)findViewById(R.id.hello);
    androidPractice(textView);
    }

    //Android 練習冒泡排序
    private void androidPractice(TextView myText){
    	int[] intArray = {2,4,6,8,10,1,3,5,7,9};
    	int temp;
    	for(int i = intArray.length - 1; i >= 0; i--){
    		for(int j = 0; j < i; j++){
    			if(intArray[j] > intArray[j + 1]){
    				temp = intArray[j];
    				intArray[j] = intArray[j + 1];
    				intArray[j + 1] = temp;
    			}
    		}
    	}

    //排序後列印陣列元素
    String str = "冒泡：";
    for(int i = 0; i < intArray.length; i++){
//    	str = str + (intArray[i] + "") + " ";
     	str = str + intArray[i] + " " ;
     	
    }
    myText.setText(str);
    }    
}