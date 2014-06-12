package com.example.timer;

//import java.util.Date;

import java.util.Date;
import java.util.Timer;

import java.util.TimerTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	//Time elapsed=new Time();
	long prev,start,end,elapsed;
	Long HH,MM,SS;
	//Date date1;
	//Timer t;
	
	//prev=now=temp=elapsed=new Time();
	Boolean clicked_first=true,pause_clicked=false;
	TextView time,list;
	/*Thread thread=new Thread(new Runnable() {
        public void run() {
        	
        	//thread.setPriority(android.os.Process.THREAD_PRIORITY_URGENT_DISPLAY);
        	while(!pause_clicked && !thread.isInterrupted()){//end=System.currentTimeMillis();
      //  	time.invalidate();
           //elapsed=prev+end-start;
           // time.po
        		try{
           if(!pause_clicked){
           time.post(new Runnable() {
               public void run() {
            	   end=System.currentTimeMillis();
            	      //  	time.invalidate();
            	           elapsed=prev+end-start;
            	   elapsed/=1000;
            	   SS=elapsed%60;
            	   MM=elapsed/60;
            	   HH=MM/60;
            	   MM=MM%60;if(!pause_clicked)
            	   time.setText(Long.toString(HH)+" : "+Long.toString(MM)+" : "+Long.toString(SS));
                   //time.setText("%c%c:%c:%c%c",elapsed.toString().charAt(index));
           
                   time.invalidate();
               }
               }
           );}
           
           
   				Thread.sleep(1000);	    	
        	
        }catch(InterruptedException consumed){  consumed.printStackTrace(); try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
        	//return;
        	}}
        	});*/
	//Date date;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		//Date date = new Date();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	public void on_click_start(View view){
		if(clicked_first){
			time=(TextView)findViewById(R.id.editText1);
			time.setText("00 : 00 : 00");
			list=(TextView)findViewById(R.id.editText2);
			list.setText("");
			clicked_first=false;
			pause_clicked=true;
			//prev=new Time();
			//prev.setToNow();
			prev=0;
		}
		if(!pause_clicked)return;
		pause_clicked=false;
		Log.v("Click", "Statrt clicked");
		start=System.currentTimeMillis();
		
		new Thread(new Runnable() {
	        public void run() {
	        	Log.v("thread", "Inside new thread");
	        	//thread.setPriority(android.os.Process.THREAD_PRIORITY_URGENT_DISPLAY);
	        	while(!pause_clicked){//end=System.currentTimeMillis();
	      //  	time.invalidate();
	        		Log.v("thread", "Inside new thread..while");
	           //elapsed=prev+end-start;
	           // time.po
	        		try{
	           if(!pause_clicked){
	           time.post(new Runnable() {
	               public void run() {String hr = "",min = "",sec = "";
	            	   end=System.currentTimeMillis();
	            	      //  	time.invalidate();
	            	           elapsed=prev+end-start;
	            	   elapsed/=1000;
	            	   SS=elapsed%60;
	            	   MM=elapsed/60;
	            	   HH=MM/60;
	            	   MM=MM%60;
	            	   if(SS<10)sec="0";
	            	   if(MM<10)min="0";
	            	   if(HH<10)hr="0";
	            	   sec=sec+Long.toString(SS);
	            	   min=min+Long.toString(MM);
	            	   hr=hr+Long.toString(HH);
	            	   
	            	   
	            	   if(!pause_clicked)
	            	   time.setText(hr+" : "+min+" : "+sec);
	            	   
	            	   //time.setText("%c%c:%c:%c%c",elapsed.toString().charAt(index));
	           
	                   time.invalidate();
	               }
	               }
	           );}
	           
	           
	   				Thread.sleep(1000);	    	
	        	
	        }catch(InterruptedException consumed){  consumed.printStackTrace();}
	        	//return;
	        	}return;  }
	        	}).start();		

		
		
	}
	
	@SuppressWarnings("deprecation")
	public void on_click_pause(View view)
	{	pause_clicked=true;
	
		String temp =time.getText().toString();
		
		try{
	//		thread.interrupt();
	}catch(Exception e){e.printStackTrace();}
	prev=elapsed*1000;
	try {
		Thread.sleep(100);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	time.setText(temp);
	
	}
	
	@SuppressWarnings("deprecation")
	public void on_click_reset(View view)
	{  
		pause_clicked=true;
		try{
		//thread.interrupt();
	}catch(Exception e){e.printStackTrace();}
		list.append("\n"+time.getText().toString());
		prev=0;
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		elapsed=0;
		time.setText("00 : 00 : 00");
		//time.invalidate();
	}
	
	public void on_click_clear(View view)
	{
		list.setText("");
		
	}
}
