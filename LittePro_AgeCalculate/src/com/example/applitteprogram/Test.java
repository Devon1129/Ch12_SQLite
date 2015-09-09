package com.example.applitteprogram;

import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {
	
	//計算年數
	public int timeShow2(int year, int month, int date) throws ParseException{
		
		//抓取當下日期
		Calendar calendar = Calendar.getInstance();		
		
		
		if(isLeapYear(year)){

	//		calendar.set(2015,Calendar.DECEMBER, 03);
			Date d1 = calendar.getTime();		
			
			calendar.set(year, month, date);
			Date d2 = calendar.getTime();
			
			
			long daterange = d1.getTime()- d2.getTime();
			//毫秒*秒*分*時，time = 一天的毫秒數。
			long time = 1000*60*60*24;
			
			int days = (int)(daterange/time);
			//天數/365 = 年數.
			int yearsAmount = (int)days/365;
			return yearsAmount;
		}else{
			return 0;
		}
		
	}
	
	//計算閏年
	public boolean isLeapYear(int year){
		return((year % 4 == 0 && year % 100 != 0) || year % 400 == 0); 
	}
		
//		//計算日期，包括時、分、秒???
//		public Long calculateTime(String date) throws ParseException{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");
//		Date dt1 = sdf.parse("2015/09/03 00:00:00");
//		Date dt2 = sdf.parse(date);
//		
//		Long ut1 = dt1.getTime();
//		Long ut2 = dt2.getTime();
//		
//		Long timeP = ut2- ut1;
//		Long sec=timeP/1000;//秒差
//		Long min=timeP/1000*60;//分差
//		Long hr=timeP/1000*60*60;//時差
//		Long day = timeP/1000*60*60*24;
//		
//		return day; 
//	}

}

//	//顯示日期:year/month/hour
//	public String timeShow(String date) throws ParseException{
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//	
//		Date dt = sdf.parse(date);	
//		Calendar calendar = Calendar.getInstance();
//	
//		calendar.setTime(dt);
//		calendar.add(Calendar.YEAR,0);
//		calendar.add(Calendar.MONTH, 0);//0-月份不變
//		calendar.add(Calendar.HOUR, 0);
//		Date tdt = calendar.getTime();
//		
//		String time = sdf.format(tdt);
//		return time;
	

////String 轉 date.
//public Date currentTime2(String date){
//	 Date date2 = new Date();
//	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//	 try{
//		 date2 = sdf.parse(date);
//		 Log.i("date", "show date!!");
//	 }catch(Exception e){
//		 e.printStackTrace();
//	 }
//	return date2;




    //Timestamp轉String(毫秒???)
//	public String currentTime(){
//		//	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////			Timestamp now = new Timestamp(System.currentTimeMillis());
//			Long now = System.currentTimeMillis()*1000;
//		//	String str = df.format(now);
//			String str = now.toString();
//			return str;
//	}


