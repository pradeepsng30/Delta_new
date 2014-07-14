package com.example.paraconnect;

import android.app.Activity;
import com.facebook.*;
import com.facebook.model.*;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

public class MainActivity extends Activity {
	WebView myWebView ;
	ImageButton click_button;// (WebView) findViewById(R.id.webview);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		Session.openActiveSession(this, true, new Session.StatusCallback() {

		    // callback when session changes state
		    @Override
		    public void call(Session session, SessionState state, Exception exception) {

		    	if (session.isOpened()) {
		    		// make request to the /me API
		    		Request.newMeRequest(session, new Request.GraphUserCallback() {

		    		  // callback after Graph API response with user object
		    		  @Override
		    		  public void onCompleted(final GraphUser user, Response response) {
		    			  
		    			  if (user != null) {
		    
		    				 final TextView  welcome = (TextView) findViewById(R.id.welcome);
		    				welcome.setText("Hello " + user.getName() + "!\n\nGenerating friend Suggestions Shortly ...");
		    				 /* try {
								//welcome.wait(2700);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}*/
		    				//click_button=(ImageButton)findViewById(R.id.imageButton1);
		    				//click_button.setVisibility(View.VISIBLE);
		    				
		    				myWebView=(WebView) findViewById(R.id.webview);
		    						
		    				
		    				 
		    				  WebSettings webSettings = myWebView.getSettings();
		    				  webSettings.setJavaScriptEnabled(true);
		    				  myWebView.setWebViewClient(new WebViewClient());
		    				  myWebView.loadUrl("http://m.facebook.com/friends/center/suggestions");
		    				  myWebView.setWebViewClient(new WebViewClient() {
			    					@Override
			    					public void onPageFinished(WebView view, String url) {
			    					super.onPageFinished(view, url);
			    					myWebView.setVisibility(android.view.View.VISIBLE);
			    					welcome.setText("Hello " + user.getName() + "!\n\nPeple you may know");
			    					}
			    					});
		    				  // myWebView.
		    				  
		  //  user.get
		    			  
		    			  }
		    		  }
		    		}).executeAsync();
		    		}
		    	
		    }
		  });
	}
	
	public void on_click(View view){
		
		click_button.setVisibility(View.INVISIBLE);
		myWebView.setVisibility(android.view.View.VISIBLE);
		
		//myWebView.setVisibility(visibility)
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	  super.onActivityResult(requestCode, resultCode, data);
	  Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    // Check if the key event was the Back button and if there's history
	    if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
	        myWebView.goBack();
	        return true;
	    }
	    // If it wasn't the Back key or there's no web page history, bubble up to the default
	    // system behavior (probably exit the activity)
	    return super.onKeyDown(keyCode, event);
	}
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

}
