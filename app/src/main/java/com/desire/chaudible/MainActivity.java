package com.desire.chaudible;

import java.io.File;
import java.util.ArrayList;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;
//import android.text.style.TypefaceSpan;


public class MainActivity extends ActionBarActivity {
	
	private static final String LOG_TAG = "chutiyapa";
    
	public String MessageIntent = null;
	private Uri returnedUri;
	private Uri sampleUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e(LOG_TAG, "activity started");

        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		if(actionBar != null) {
            actionBar.setHomeButtonEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
        }
		Intent i = getIntent();
		
    	if(i.getAction().equals("com.whatsapp.action.WHATSAPP_RECORDING") ||
    			i.getAction().equals("android.intent.action.PICK") ||
    			i.getAction().equals("android.intent.action.GET_CONTENT")
    			){
    		MessageIntent = "attach";
    	}
    	
    	else {
    		MessageIntent = "share";
    	}
    	
    	File sample = new File(Environment.getExternalStorageDirectory() + "/Chaudible");
		returnedUri = Uri.fromFile(sample);
		sampleUri = returnedUri;
		

		
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
		if (id == R.id.Rate1) {
			
			Dialog d = new Dialog(MainActivity.this);
			d.setTitle("Rate us");
			d.setContentView(R.layout.rateus1);
			ImageButton ibcb = (ImageButton) d.findViewById(R.id.rateb);
		    ibcb.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	final String appPackageName = "com.desire.chaudible"; // getPackageName() from Context or Activity object
		        	//final String appPackageName = "com.sunny.sumo"; // getPackageName() from Context or Activity object
					try {
					    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
					} catch (android.content.ActivityNotFoundException anfe) {
					    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
					}
		        }
		    });
		//	d.requestWindowFeature(Window.FEATURE_NO_TITLE);
			d.show();
		
			//final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
			return true;
		}
		if (id == R.id.ShareWithFriends1) {
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Check this new app and discover the magic of sounds\n" 
			+ Uri.parse("http://play.google.com/store/apps/details?id=com.desire.chaudible")
			);
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
			
		}
		if(id == R.id.CreatedBy1){
			Dialog d = new Dialog(MainActivity.this);
			d.setTitle("Created by");
			d.setContentView(R.layout.createdby);
			ImageButton ibcb = (ImageButton) d.findViewById(R.id.imagecb1);
		    ibcb.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
		        	String url = "http://www.tatkartr.com";
		        	Intent i = new Intent(Intent.ACTION_VIEW);
		        	i.setData(Uri.parse(url));
		        	startActivity(i);
		        }
		    });
		//	d.requestWindowFeature(Window.FEATURE_NO_TITLE);
			d.show();
		}
		if(id == R.id.AboutChaudible1){
			Dialog d = new Dialog(MainActivity.this);
			d.setTitle("About Chaudible");
			d.setContentView(R.layout.aboutc);
			d.show();
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
	public void StartRecordActivity(View v){
		if(MessageIntent.equals("share")){
			Intent is = new Intent(this,RecordActivity.class);
			is.putExtra("ATTACH_MESSAGE", MessageIntent);
			startActivity(is);
		}
		
		if(MessageIntent.equals("attach")){
		    getUriFromRecord();
		}
	}
	
	public void StartGallary(View v){
		if(MessageIntent.equals("share")){
			Intent is = new Intent(this,GallaryActivity.class);
			is.putExtra("ATTACH_MESSAGE", MessageIntent);
			startActivity(is);
		}
		
		if(MessageIntent.equals("attach")){
		    getUriFromGallary();
		}
	}
	
	
	static final int GET_URI1 = 1;
	public void getUriFromRecord(){
		Intent getUri = new Intent(this, RecordActivity.class);
		getUri.putExtra("ATTACH_MESSAGE", MessageIntent);
	    startActivityForResult(getUri, 1);
		return;
	}
	
	static final int GET_URI2 = 2;
	public void getUriFromGallary(){
		Intent getUri = new Intent(this, GallaryActivity.class);
		getUri.putExtra("ATTACH_MESSAGE", MessageIntent);
	    startActivityForResult(getUri, 2);
		return;
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Check which request we're responding to
	    if (requestCode == GET_URI1) {
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	            // The user picked a contact.
	            // The Intent's data Uri identifies which contact was selected.
	            //returnedUri=data.getData();
	        	String pathUri = data.getStringExtra("path");
	        	File fileUri = new File(pathUri);
	        	returnedUri = Uri.fromFile(fileUri);
	        	// Do something with the contact here (bigger example below)
	            Intent ia = new Intent("com.desire.chaudible", returnedUri);
	            //	ia.setAction(getIntent().getAction());
	        	//	ia.putExtra("ATTACH_MESSAGE", MessageIntent);
	    			setResult(Activity.RESULT_OK, ia);
	    			finish();
	    		
	        }
	    }
	    
	    if(requestCode == GET_URI2){
	    	if (resultCode == RESULT_OK) {
		    int NumSounds = data.getIntExtra("NumResults", 1);
	    	ArrayList<Uri> ShareList = new ArrayList<Uri>();
	    	Log.e(LOG_TAG,Integer.toString(NumSounds));
	    	for(int i =0;i<NumSounds;i++){
	    		String temp = data.getStringExtra("result"+Integer.toString(i));
	    		Log.e(LOG_TAG,temp);
	    		ShareList.add(Uri.fromFile(new File(temp)));
	    		}
	    //    Intent ia = new Intent("com.desire.chaudible",ShareList.get(0));
	    //	ia.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
	        Intent ia = new Intent();
	        ia.setData(ShareList.get(0));
		   	
	        setResult(Activity.RESULT_OK,ia);
			finish();
	    	}
	    }
	}
}
