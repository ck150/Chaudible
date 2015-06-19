package com.desire.chaudible;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RecordActivity extends ActionBarActivity {

	private static final String LOG_TAG = "chutiyapa";
    private String mFileName = null;
    //private static File f1 = null;

    private RecordButton mRecordButton = null;
    private MediaRecorder mRecorder = null;

    private ImageButton   mPlayButton = null;
    private MediaPlayer   mPlayer = null;
    //private static File newFile = null; 
    private boolean bStartRecording = true;
    private boolean bStartPlaying = true;
    private ImageButton bp = null;
    private ImageButton br = null;
    private Intent i;
    private String MessageIntent2;
    public TextView tv;
    private Animation a1;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	 	
		super.onCreate(savedInstanceState);
		File src = getFilesDir();
        //File newFile = Environment.getRootDirectory();
        //File mypath=new File(newFile,"audiorecordtest.3gp");
    	mFileName = src.getAbsolutePath()+ "/RecordedClip.3gp";
    	overridePendingTransition(R.animator.anim1,R.animator.anim2);
    	
    	
		setContentView(R.layout.activity_record);
		tv = (TextView) findViewById(R.id.tap_to);
    	Typeface type = Typeface.createFromAsset(getAssets(),"crayon.ttf"); 
		tv.setText("tap to record");
		tv.setTypeface(type);

		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		if(actionBar != null) {
			actionBar.setHomeButtonEnabled(false);
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowTitleEnabled(false);
		}

		if (savedInstanceState == null) {
		    Bundle extras = getIntent().getExtras();
		    if(extras == null) {
		        MessageIntent2= null;
		    } else {
		        MessageIntent2= extras.getString("ATTACH_MESSAGE");
		    }
		} else {
		    MessageIntent2= (String) savedInstanceState.getSerializable("ATTACH_MESSAGE");
		}
		
		//mRecordButton = (RecordButton) findViewById(R.id.record_button);
		//mPlayButton = (PlayButton) findViewById(R.id.play_button);
		//	final ImageButton button = (ImageButton) findViewById(R.id.imageButton1);
	

	/*
        LinearLayout ll = new LinearLayout(this);
        mRecordButton = new RecordButton(this);
        ll.addView(mRecordButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        mPlayButton = new PlayButton(this);
        ll.addView(mPlayButton,
            new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0));
        setContentView(ll);
	*/
		br = (ImageButton) findViewById(R.id.record_button);
		br.setImageResource(R.drawable.micblack1);
	    br.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	
	            onRecord(bStartRecording);
                if (bStartRecording) {
            //        setText("Stop recording");
                	br.setImageResource(R.drawable.micred1);
                	a1 = AnimationUtils.loadAnimation(RecordActivity.this, android.R.anim.slide_out_right);
                	tv.startAnimation(a1);
                	a1.setAnimationListener(new AnimationListener(){

						@Override
						public void onAnimationEnd(Animation arg0) {
							// TODO Auto-generated method stub
							tv.setText("tap to stop");
							a1 = AnimationUtils.loadAnimation(RecordActivity.this, android.R.anim.slide_in_left);
							tv.startAnimation(a1);
		                }

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub
							
						}
                 	});
                	tv.startAnimation(a1);
                    
                } else {
            //        setText("Start recording");
                	br.setImageResource(R.drawable.micblack1);
                   	tv.startAnimation(AnimationUtils.loadAnimation(RecordActivity.this, android.R.anim.slide_out_right));
                	tv.setText("tap to record");
                 	tv.startAnimation(AnimationUtils.loadAnimation(RecordActivity.this, android.R.anim.slide_in_left));
             
                }
                bStartRecording = !bStartRecording;
            
	        }
	        
	    });
	    
	    bp = (ImageButton) findViewById(R.id.play_button);
	    bp.setImageResource(R.drawable.play11);
	    bp.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	File f1 = new File(mFileName);
            	if(!f1.exists()){
            		showToast("No last recording found");
            		return;
            	}
            	
	            onPlay(bStartPlaying);
                if (bStartPlaying) {
            //        setText("Stop playing");
                	bp.setImageResource(R.drawable.play21);
                } else {
            //        setText("Start playing");
                	bp.setImageResource(R.drawable.play11);
                }
                bStartPlaying = !bStartPlaying;
            
	        }
	    });
		
	    final ImageButton saveButton = (ImageButton) findViewById(R.id.save_button);
		saveButton.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View v) {
	        	File f1 = new File(mFileName);
	        	if(f1.exists()){
	        	ShowDialogueBox();}
	        	else{
	        		
	        		showToast("No last recording found");
	        	}
	             
	        }
	    });
	    
	    
	    File folder = new File(Environment.getExternalStorageDirectory() + "/Chaudible/Recordings");
	    boolean success = true;
	    if (!folder.exists()) {
	        success = folder.mkdir();
	    }
	    if (success) {
	 //       Toast.makeText(this,
     //				"Folder created", Toast.LENGTH_SHORT).show();
	    } else {
	 //       Toast.makeText(this,
     //				"Failed to create folder", Toast.LENGTH_SHORT).show();
	    }
	    
	    
	    findViewById(android.R.id.content).getRootView().setOnTouchListener(new OnSwipeTouchListener2(this) {
		    @Override
		    public void onSwipeLeft() {
		    	onBackPressed();
			    overridePendingTransition(R.animator.anim3,R.animator.anim4);
			}
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");  
		    }
		    
		});		

	    
	    
	}
    
	@Override
	public void onBackPressed(){
		
		super.onBackPressed();
	    overridePendingTransition(R.animator.anim3,R.animator.anim4);
		
	}
	
	
	public void ShareButton(View v){
		//File internalFile = new File(mFileName);
    	Intent share = new Intent(Intent.ACTION_SEND);
    	File dst = new File(Environment.getExternalStorageDirectory() + "/Chaudible/Shared clips");
		 if(!dst.exists()){
			 dst.mkdir();
		 }
		 int i = 2;
		 dst = new File(Environment.getExternalStorageDirectory() + "/Chaudible/Shared clips/RecordedClip.mp3");
	//	 while(dst.exists()){
	//	 dst = new File(Environment.getExternalStorageDirectory() + "/Chaudible/Shared clips/RecordedClip" + Integer.toString(i) + ".mp3");
	//	 i++;
	//	 }
    	File src = new File(mFileName);
    	try {
			copyFile(src,dst);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Uri uri = Uri.fromFile(dst);
    	if(MessageIntent2.equals("share")){
    	share.setType("audio/*");
    	//share.setType("text/plain");
        share.putExtra(Intent.EXTRA_STREAM, uri);
        //share.putExtra(Intent.EXTRA_INTENT, "Shared through chaudible");
        startActivity(Intent.createChooser(share, "Share Sound File"));
    	}
    	
        if(MessageIntent2.equals("attach")){
      		
    	Intent returnIntent = new Intent();
    	returnIntent.putExtra("path",dst.getAbsolutePath());
    	setResult(Activity.RESULT_OK, returnIntent);
    	finish();
    	}
	}
	
	private void ShowDialogueBox(){
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Save clip as");
	//	alert.setMessage("Message");

		// Set an EditText view to get user input 
		final EditText input = new EditText(this);
		alert.setView(input);

		alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
		  String value = input.getText().toString();
		  if(value.equals("")){
			  showToast("Invalid clip name");
			  return;
		  }
		  // Do something with value!
		  File dst = new File(Environment.getExternalStorageDirectory() + "/Chaudible/Recordings/" + value + ".mp3");
		    boolean success = true;
		    int i = 2;
		    while(dst.exists()){
		    	dst = new File(Environment.getExternalStorageDirectory() + "/Chaudible/Recordings/" + value + "(" + Integer.toString(i)+ ")" +".mp3");
		    	i++;
		    }
		 //   if (!dst.exists()) {
		  //      success = dst.mkdir();
		   // }
		    if (success) {
		    //    Toast.makeText(this,
	    	//			"Folder created", Toast.LENGTH_SHORT).show();
		   // 	File src = getFilesDir();
		        //File newFile = Environment.getRootDirectory();
		        //File mypath=new File(newFile,"audiorecordtest.3gp");
		   // 	mFileName = src.getAbsolutePath();
		   // 	mFileName += "/audiorecordtest.3gp";
		        
		    	File src = new File(mFileName);
		    	
		    	try {		   
		    		showToastLong("Clip saved successfully: " + dst);
					copyFile(src,dst);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    
		    } else {
		
		    }
		    
		  
		  }
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		  public void onClick(DialogInterface dialog, int whichButton) {
		    // Canceled.
		  }
		});

		alert.show();	
	}
	
	
	
	public void showToast(String s){
		   Toast.makeText(this.getApplicationContext(),
	   		  		s, Toast.LENGTH_SHORT).show();
	   	
	}
	
	public void showToastLong(String s){
		   Toast.makeText(this.getApplicationContext(),
	   		  		s, Toast.LENGTH_LONG).show();
	   	
	}
	
	
	public void copyFile(File src, File dst) throws IOException
	{
	
	    @SuppressWarnings("resource")
		FileChannel inChannel = new FileInputStream(src).getChannel();
	    @SuppressWarnings("resource")
		FileChannel outChannel = new FileOutputStream(dst).getChannel();
	    try
	    {
	        inChannel.transferTo(0, inChannel.size(), outChannel);
	    }
	    finally
	    {
	        if (inChannel != null)
	            inChannel.close();
	        if (outChannel != null)
	            outChannel.close();
	    }
	    
		
		//FileOutputStream fos = new FileOutputStream(dst);
		//fos.write(string.getBytes());
		//fos.close();
	}
	
	
	
    private void onRecord(boolean start) {
        if (start) {
            startRecording(this.getApplicationContext());
            Toast.makeText(this,
    				"recording started", Toast.LENGTH_SHORT).show();
     
        } else {
            stopRecording();
            Toast.makeText(this,
    				"recording stopped", Toast.LENGTH_SHORT).show();
     
        }
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying(this.getApplicationContext());
        } else {
            stopPlaying();
        }
    }

    private void startPlaying(Context c) {
        mPlayer = new MediaPlayer();
        File newFile = c.getFilesDir();
        //File newFile = Environment.getRootDirectory();
        //File mypath=new File(newFile,"audiorecordtest.3gp");
    //	mFileName = newFile.getAbsolutePath();
    //	mFileName += "/audiorecordtest.3gp";
        
        try {
        	mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
            	onPlay(bStartPlaying);
                if (bStartPlaying) {
            //        setText("Stop playing");
                	bp.setImageResource(R.drawable.play21);  
                } else {
            //        setText("Start playing");
                	bp.setImageResource(R.drawable.play11);
                }
                bStartPlaying = !bStartPlaying;
            }
        });
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    private void startRecording(Context c) {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
      
        File newFile = c.getFilesDir();
        //File newFile = Environment.getRootDirectory();
        //File mypath=new File(newFile,"audiorecordtest.3gp");
    //	mFileName = newFile.getAbsolutePath();
    //	mFileName += "/audiorecordtest.3gp";
        
     	Log.e(LOG_TAG, "challl hattt !!!");
    	
		//mRecorder.setOutputFile(this.getFilesDir() + "/" + mFileName);
		mRecorder.setOutputFile(mFileName);
		
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
        
    }


    private void stopRecording() {
    	mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    class RecordButton extends ImageButton {
        boolean mStartRecording = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onRecord(mStartRecording);
                if (mStartRecording) {
            //        setText("Stop recording");
                } else {
            //        setText("Start recording");
                }
                mStartRecording = !mStartRecording;
            }
        };

        public RecordButton(Context ctx) {
            super(ctx);
     //       setText("Start recording");
            setOnClickListener(clicker);
        }
    }

    class PlayButton extends ImageButton {
        boolean mStartPlaying = true;

        OnClickListener clicker = new OnClickListener() {
            public void onClick(View v) {
                onPlay(mStartPlaying);
                if (mStartPlaying) {
         //           setText("Stop playing");
                } else {
         //           setText("Start playing");
                }
                mStartPlaying = !mStartPlaying;
            }
        };

        public PlayButton(Context ctx) {
            super(ctx);
      //      setText("Start playing");
            setOnClickListener(clicker);
        }
    }

    public RecordActivity() {
   
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecorder != null) {
            br.performClick();
         }

        if (mPlayer != null) {
        	bp.performClick();
        }
    }
    
    @Override
	protected void onStop() {
    //	br.performClick();
         if (mRecorder != null) {
         	 mRecorder.stop();
             mRecorder.release();
             mRecorder = null;
         }

         if (mPlayer != null) {
         	 mPlayer.stop();
             mPlayer.release();
             mPlayer = null;
         }
     	super.onStop();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.record, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.Rate2) {
			
			Dialog d = new Dialog(RecordActivity.this);
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
		if (id == R.id.ShareWithFriends2) {
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Check this new app and discover the magic of sounds\n" 
			+ Uri.parse("http://play.google.com/store/apps/details?id=com.desire.chaudible")
			);
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
			
		}
		if(id == R.id.CreatedBy2){
			Dialog d = new Dialog(RecordActivity.this);
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
		if(id == R.id.AboutChaudible2){
			Dialog d = new Dialog(RecordActivity.this);
			d.setTitle("About Chaudible");
			d.setContentView(R.layout.aboutc);
			d.show();
		}
		
		return super.onOptionsItemSelected(item);
	
	}
	
	
	
}
