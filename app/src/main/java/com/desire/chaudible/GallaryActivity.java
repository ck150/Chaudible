package com.desire.chaudible;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.SlidingPaneLayout.LayoutParams;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.PopupMenu;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

public class GallaryActivity extends ActionBarActivity {

	public MediaPlayer mp = new MediaPlayer();
	public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
  //  private Button startBtn;
	private String MessageIntent2;
    Button LastButton;
	public View GButtonPressed;
    
	int[] NumberOfSounds = {0,39,30,23,27,36,12,10,21,17,18};
	String[][] SoundNames = {{},
			{"Applause","Baby calling Mama","Cheering","Crying baby","Fart","Giggling man","Good afternoon Ma'am","Good morning Ma'am","Good morning Sir","Good night","Hello","Hiccups","Kiss long","Kiss","Laugh audience","Laugh crazy","Laughing baby",
		"Laughing guy","Man & woman crying","Man & woman laughing","Man and woman screaming","Man and woman sigh","Man burp-long","Man burrping","Man coughing","No","Nooo","Old man yes","Oooh","Rhythmic clapping","Sneezing girl","Sneezing man","Snoring with whistle",
		"Sobbing man","Whistle","Woman coughing","Woman crying","Woman laughing","Yes-crowd"},
		{"Angry monsters","Bee buzz","Birds chirping","Birds tweeting","Canary Songs","Cat meow long","Cat meow short","Cat purring",
		"Cattle moo","Chicken coop","Cow moo","Crickets long","Crickets & frogs","Crow","Dog bark-1","Dog bark-2","Dog musical","Dogs barking","Goat","Hawk","Horse trotting","Horse","Lion roar-1","Lion roar-2","Mosquito","Owl","Peacock","Pig","Turkey","Werewolf howling"},
		{"Big fat boing","Bing and Pop","Boing spring","Bounce","Bow and arrow","Clown laugh","Computer beep","Coochie coochie","Funny springs","Giggling man","Goofy","High fall","Mouse giggles","Over","Pacman died","Percussion beat","Popping","Recoil rubber",
			"Space","Space whirls","Taratitatutututu","Whistle police","Whistle"},
				{"Alarm clock","Alarm smoke","Beer pouring","Clock tik tok","Clock tik tok-fast","Coin","Door closing","Door knocking","Door squeaking close","Doorbell","Fan","Fire","Firecrackers","Glass breaking","Glass shattering","Old clock","Phone disconnect","Phone ringing","Sweeping broom",
					"Teeth brushing","Telephone dialing","Telephone ringing","Thunderclap","Toilet Flush","Water boiling","Whistle police","Wind"},
					{"Airplane crash","Alarm warning","Ambulance fast","Annoying machine","Auto detect timer","Bazooka","Beep beep","Beep and clicks","Blade-swipe-wav","Blender","Bomb","Cannon shot","Cannons firing","Car crash","Car hand brake","Car honking-1","Car honking-2","Car honking-3",
						"Car starting","Clock bells","Clock ticking old-1","Clock ticking old-2","Clock ticking","Dot matrix printer","Explosion","Firing squad","Fishing boat engine","Gun firing","Helicopter","Hooter","Morse code","Police siren","School bell","Ship horn","Touch tone phone","Xerox machine"},
						{"African drums","Bong beat","Bongo beats","Bongo","Bugle military","Chinese gong","Drums african","Drums military","Harp descending","Harp ascending","Harp strings","Harp strum"},
						{"Plane taking off","Busy office","Conference","Harbour","Market","Public area","Railway station chai chai","Railway station","Train moving","Zoo"},
						{"A demented laugh","Echo ghost","Eerie","Evil laugh","Ghosts","Graveyard","Grrrr","Hover","I can see you","Lonely ghost","Piano","Sad ghost","Scary laugh","Scream","Song of dead","Spooked","Spooky","Squeaking swing","Walking dead","Witch laughing","Zombie"},
						{"A few questions","Alright then","Bad network","Balls are showing","Blah blah blah","Boom headshot","Bye bye then","Can't answer","Evaa i do what i want","I am Mclovin","I kill you","No wayy","One must try","Sabji wala","Spank you","Welcome","You again!!"},
						{"A mouse","Ass hurts watching","Asshole in the room","Asshole","Bad boy","Best Bj","Bj good night","Clothes off","Cute butt","Go fuck yourself","I'm just a bitch","Let's do it","Let's fuck","Man and woman moaning","No dick","Some loving","Staring at your breasts","Wash the coochie honey"}};
	
	String filenames1[] = new String[39];
	String filenames2[] = new String[30];
	String filenames3[] = new String[23];
	String filenames4[] = new String[27];
	String filenames5[] = new String[36];
	String filenames6[] = new String[12];
	String filenames7[] = new String[10];
	String filenames8[] = new String[21];
	String filenames9[] = new String[17];
	String filenames10[] = new String[18];
	private InputStream ins;
	
	
	
	int[] ids1 = {R.raw.p10,R.raw.p11,R.raw.p12,R.raw.p13,R.raw.p14,R.raw.p15,R.raw.p16,R.raw.p17,R.raw.p18,R.raw.p19,
			R.raw.p110,R.raw.p111,R.raw.p112,R.raw.p113,R.raw.p114,R.raw.p115,R.raw.p116,R.raw.p117,R.raw.p118,R.raw.p119,
			R.raw.p120,R.raw.p121,R.raw.p122,R.raw.p123,R.raw.p124,R.raw.p125,R.raw.p126,R.raw.p127,R.raw.p128,R.raw.p129,
			R.raw.p130,R.raw.p131,R.raw.p132,R.raw.p133,R.raw.p134,R.raw.p135,R.raw.p136,R.raw.p137,R.raw.p138};
	int[] ids2 = {R.raw.p20,R.raw.p21,R.raw.p22,R.raw.p23,R.raw.p24,R.raw.p25,R.raw.p26,R.raw.p27,R.raw.p28,R.raw.p29,
			R.raw.p210,R.raw.p211,R.raw.p212,R.raw.p213,R.raw.p214,R.raw.p215,R.raw.p216,R.raw.p217,R.raw.p218,R.raw.p219,
			R.raw.p220,R.raw.p221,R.raw.p222,R.raw.p223,R.raw.p224,R.raw.p225,R.raw.p226,R.raw.p227,R.raw.p228,R.raw.p229};
	int[] ids3 = {R.raw.p30,R.raw.p31,R.raw.p32,R.raw.p33,R.raw.p34,R.raw.p35,R.raw.p36,R.raw.p37,R.raw.p38,R.raw.p39,
			R.raw.p310,R.raw.p311,R.raw.p312,R.raw.p313,R.raw.p314,R.raw.p315,R.raw.p316,R.raw.p317,R.raw.p318,R.raw.p319,
			R.raw.p320,R.raw.p321,R.raw.p322};
	int[] ids4 = {R.raw.p40,R.raw.p41,R.raw.p42,R.raw.p43,R.raw.p44,R.raw.p45,R.raw.p46,R.raw.p47,R.raw.p48,R.raw.p49,
			R.raw.p410,R.raw.p411,R.raw.p412,R.raw.p413,R.raw.p414,R.raw.p415,R.raw.p416,R.raw.p417,R.raw.p418,R.raw.p419,
			R.raw.p420,R.raw.p421,R.raw.p422,R.raw.p423,R.raw.p424,R.raw.p425,R.raw.p426};
	int[] ids5 = {R.raw.p50,R.raw.p51,R.raw.p52,R.raw.p53,R.raw.p54,R.raw.p55,R.raw.p56,R.raw.p57,R.raw.p58,R.raw.p59,
			R.raw.p510,R.raw.p511,R.raw.p512,R.raw.p513,R.raw.p514,R.raw.p515,R.raw.p516,R.raw.p517,R.raw.p518,R.raw.p519,
			R.raw.p520,R.raw.p521,R.raw.p522,R.raw.p523,R.raw.p524,R.raw.p525,R.raw.p526,R.raw.p527,R.raw.p528,R.raw.p529,
			R.raw.p530,R.raw.p531,R.raw.p532,R.raw.p533,R.raw.p534,R.raw.p535};
	int[] ids6 = {R.raw.p60,R.raw.p61,R.raw.p62,R.raw.p63,R.raw.p64,R.raw.p65,R.raw.p66,R.raw.p67,R.raw.p68,R.raw.p69,
			R.raw.p610,R.raw.p611};
	int[] ids7 = {R.raw.p70,R.raw.p71,R.raw.p72,R.raw.p73,R.raw.p74,R.raw.p75,R.raw.p76,R.raw.p77,R.raw.p78,R.raw.p79};
	
	int[] ids8 = {R.raw.p80,R.raw.p81,R.raw.p82,R.raw.p83,R.raw.p84,R.raw.p85,R.raw.p86,R.raw.p87,R.raw.p88,R.raw.p89,
			R.raw.p810,R.raw.p811,R.raw.p812,R.raw.p813,R.raw.p814,R.raw.p815,R.raw.p816,R.raw.p817,R.raw.p818,R.raw.p819,
			R.raw.p820};
	int[] ids9 = {R.raw.p90,R.raw.p91,R.raw.p92,R.raw.p93,R.raw.p94,R.raw.p95,R.raw.p96,R.raw.p97,R.raw.p98,R.raw.p99,
			R.raw.p910,R.raw.p911,R.raw.p912,R.raw.p913,R.raw.p914,R.raw.p915,R.raw.p916};
	int[] ids10 = {R.raw.p100,R.raw.p101,R.raw.p102,R.raw.p103,R.raw.p104,R.raw.p105,R.raw.p106,R.raw.p107,R.raw.p108,R.raw.p109,
			R.raw.p1010,R.raw.p1011,R.raw.p1012,R.raw.p1013,R.raw.p1014,R.raw.p1015,R.raw.p1016,R.raw.p1017};
	
	final int[] CurrentChecked = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
    
	ScrollView2 ScrollView[] = new ScrollView2[11];
	
	protected static final String TAG = "chutiyapa";
	public AlertDialog alert;
	
	private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_gallary);
		android.support.v7.app.ActionBar actionBar = getSupportActionBar();
		if(actionBar != null) {
			actionBar.setHomeButtonEnabled(false);
			actionBar.setDisplayHomeAsUpEnabled(false);
			actionBar.setDisplayShowHomeEnabled(false);
			actionBar.setDisplayShowTitleEnabled(false);
		}
		overridePendingTransition(R.animator.anim3,R.animator.anim4);
	  
		findViewById(R.id.GButton1).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
	
		findViewById(R.id.GButton2).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton3).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton4).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton5).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton6).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton7).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton8).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton9).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton10).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
		findViewById(R.id.GButton11).setOnTouchListener(new OnSwipeTouchListener(this) {
		    @Override
		    public void onSwipeLeft() {
		        // Whatever
		    //	ShowToast("Left swipe");
		    }
		    public void onSwipeRight(){
		    //	ShowToast("Right swipe");
		    SwipeActivity();
		    }
	
		});		
	
		
		gestureDetector = new GestureDetector(this, new MyGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
            	GButtonPressed = v;
                return gestureDetector.onTouchEvent(event);
            }
        };
        
        
        // findViewById(R.id.GButton1).setOnTouchListener(gestureListener);
        // findViewById(R.id.GButton2).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton3).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton4).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton5).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton6).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton7).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton8).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton9).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton10).setOnTouchListener(gestureListener);
        //findViewById(R.id.GButton11).setOnTouchListener(gestureListener);
   		
		
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
		
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer arg0) {
			//	LastButton.setBackgroundColor(0xFF999999);
				LastButton.setTextColor(Color.BLACK);
				}
		
		});
		
	}
	
	class MyGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            try {
                if (Math.abs(e1.getY() - e2.getY()) > SWIPE_MAX_OFF_PATH)
                    return false;
                // right to left swipe
                if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(GallaryActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
                }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Toast.makeText(GallaryActivity.this, "Right Swipe", Toast.LENGTH_SHORT).show();
                    onBackPressed();
    			    overridePendingTransition(R.animator.anim1,R.animator.anim2);
    			
                }
            } catch (Exception e) {
            	 Toast.makeText(GallaryActivity.this, "exception", Toast.LENGTH_SHORT).show();
            }
            return false;
        }

            @Override
        public boolean onDown(MotionEvent e) {
            	return true;
        }
           @Override
        public boolean onSingleTapUp(MotionEvent e){
        	   ImageButton ibb = (ImageButton)GButtonPressed;
        	   ibb.performClick();
        	   return true;
        }
            
    }

	
	
	private void ShowToast(String x){
        Toast.makeText(this, x, Toast.LENGTH_SHORT).show();
	}
	
	public void SwipeActivity(){    
	//	Intent i = new Intent(this,MainActivity.class);
	//	startActivity(i);
		onBackPressed();
		overridePendingTransition(R.animator.anim1,R.animator.anim2);
		  
	}

	public void confirmSetRingtone(final CharSequence buttonName,final String path){
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			int r = 0;
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				switch(arg1){
				case DialogInterface.BUTTON_POSITIVE:
 	            	  
 	            	  ShowToast("'" + buttonName + "'" + " is set as ringtone");
 	            	  
 	            	  File k = new File(path); // path is a file to /sdcard/media/ringtone
 	            	  ContentValues values = new ContentValues();
 	            	  values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
 	            	  values.put(MediaStore.MediaColumns.TITLE, "Chaudible ringtone");
 	            	  values.put(MediaStore.MediaColumns.SIZE, 215454);
 	            	  values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
 	            	  values.put(MediaStore.Audio.Media.ARTIST, "ck");
 	            	  values.put(MediaStore.Audio.Media.DURATION, 20);
 	            	  values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
 	            	  values.put(MediaStore.Audio.Media.IS_NOTIFICATION, false);
 	            	  values.put(MediaStore.Audio.Media.IS_ALARM, true);
 	            	  values.put(MediaStore.Audio.Media.IS_MUSIC, false);
 	            	  Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
 		              Uri newUri = (GallaryActivity.this).getContentResolver().insert(uri, values);

 		              RingtoneManager.setActualDefaultRingtoneUri(
 		                GallaryActivity.this,
 		                RingtoneManager.TYPE_RINGTONE,
 		                newUri
 		              );

					break;
				
				case DialogInterface.BUTTON_NEGATIVE:
					r = -1;
					break;
				}
			}
		};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure?")
			.setPositiveButton("Yes", dialogClickListener)
			.setNegativeButton("No", dialogClickListener).show();
	}
	

	public void confirmSetNotification(final CharSequence buttonName,final String path){
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				switch(arg1){
				case DialogInterface.BUTTON_POSITIVE:
 	            	  
 	            	  ShowToast("'" + buttonName + "'" + " is set as notification tone");
 	            	  
 	            	  File k = new File(path); // path is a file to /sdcard/media/ringtone
 	            	  ContentValues values = new ContentValues();
 	            	  values.put(MediaStore.MediaColumns.DATA, k.getAbsolutePath());
 	            	  values.put(MediaStore.MediaColumns.TITLE, "Chaudible ringtone");
 	            	  values.put(MediaStore.MediaColumns.SIZE, 215454);
 	            	  values.put(MediaStore.MediaColumns.MIME_TYPE, "audio/mp3");
 	            	  values.put(MediaStore.Audio.Media.ARTIST, "ck");
 	            	  values.put(MediaStore.Audio.Media.DURATION, 20);
 	            	  values.put(MediaStore.Audio.Media.IS_RINGTONE, true);
 	            	  values.put(MediaStore.Audio.Media.IS_NOTIFICATION, true);
 	            	  values.put(MediaStore.Audio.Media.IS_ALARM, true);
 	            	  values.put(MediaStore.Audio.Media.IS_MUSIC, false);
 	            	  Uri uri = MediaStore.Audio.Media.getContentUriForPath(k.getAbsolutePath());
 		              Uri newUri = (GallaryActivity.this).getContentResolver().insert(uri, values);

 		              RingtoneManager.setActualDefaultRingtoneUri(
 		                GallaryActivity.this,
 		                RingtoneManager.TYPE_NOTIFICATION,
 		                newUri
 		              );

					break;
				
				case DialogInterface.BUTTON_NEGATIVE:
					break;
				}
			}
		};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure?")
			.setPositiveButton("Yes", dialogClickListener)
			.setNegativeButton("No", dialogClickListener).show();
	
	}
	
	public void confirmDelete(final CharSequence buttonName,final String path){
		final boolean a = false;
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				switch(arg1){
				case DialogInterface.BUTTON_POSITIVE:
 	            	  
 	            	  ShowToast("'" + buttonName + "'" + " is deleted");
 	            	  
 	            	  File k = new File(path); // path is a file to /sdcard/media/ringtone
 	            	  k.delete();
 	            	  ImageButton ibbb = (ImageButton)findViewById(R.id.GButton1);
 	            	  alert.dismiss();
 	            	  ibbb.performClick();
	            	  break;
				
	            	  
				case DialogInterface.BUTTON_NEGATIVE:
					break;
				}
			}
		};
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete '" + buttonName + "'?")
			.setPositiveButton("Yes", dialogClickListener)
			.setNegativeButton("No", dialogClickListener).show();
	
			
	}


	
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox0(View v){
		

		final LinearLayout ll[] = new LinearLayout[39];
		final CheckBox cb[] = new CheckBox[39];
		final Button b[] = new Button[39];
		final ImageButton ib[] = new ImageButton[39];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.FILL_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.FILL_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		final ArrayList<String> listmp3 = new ArrayList<String>();
		String extensions = "mp3";
		File f2 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Recordings");
		if(!f2.exists()){
			ShowToast("No recordings found");
			return;
		}
		if (f2.exists()) {
            File[] files = f2.listFiles();
            if (files != null && files.length > 0) {
                for (File f : files) {
                            if (f.getAbsolutePath().endsWith(extensions)) {
                            	String name = f.getName();
                            	int pos = name.lastIndexOf(".");
                            	if (pos > 0) {
                            	    name = name.substring(0, pos);
                            	}
                            	listmp3.add(name);
                        }
                   }
                }
            else{
            	ShowToast("No recordings found");
            	return;
            }
            }
		
		final int num = listmp3.size();
		
		for(int i = 0;i<num;i++){
		
		//LinearLayout gallary2_1Layout = new LinearLayout(this);
		//gallary2_1Layout.setId(R.id.gallary2_1Layout);
		//gallary2_1Layout.setBackgroundResource(Color.BLACK);
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.MATCH_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		//CheckBox checkBox2_1 = new CheckBox(this);
		//cb[i].setId(R.id.checkBox2_1);
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[0] != -1){
		        		if(CurrentChecked[0] != k){
		        		cb[CurrentChecked[0]].setChecked(false);}
		        		CurrentChecked[0] = k;
		          	}
		        	if(CurrentChecked[0] == -1){
		            	CurrentChecked[0] = k;
		            	}
		            	
		        	}
		    	}
		    
		});
		}

		
		ll[i].addView(cb[i]);


		//Button sound2_1 = new Button(this);
		//sound2_1.setId(R.id.sound2_1);
		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(listmp3.get(i));
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		final String str = listmp3.get(i);
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                   		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Recordings/" + str + ".mp3" ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0, 186, 255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
	//	layout_179.width = LayoutParams.WRAP_CONTENT;
	//	layout_179.height = LayoutParams.MATCH_PARENT;
		//layout_179.weight = 1;
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       ib[j].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone2, popup.getMenu());  
   	            
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	            	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Recordings/" + str + ".mp3" );
   	            	
   	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Recordings/" + str + ".mp3" );
   	              }
   	              
   	              if(item.getTitle().equals("Delete")){

   	            	confirmDelete(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Recordings/" + str + ".mp3");
   	              }
   	              
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
       ll[i].addView(ib[i]);
       linearLayout_21.addView(ll[i]);
   	
       if(i==num-1 && num < 4){
			LinearLayout ll_end = new LinearLayout(this);
			ll_end.setOrientation(LinearLayout.HORIZONTAL);
			LayoutParams layout_210 = new LayoutParams();
			layout_210.width = LayoutParams.MATCH_PARENT;
			layout_210.height = LayoutParams.WRAP_CONTENT;
			ll_end.setLayoutParams(layout_210);
			
			Button b_end = new Button(this);
			b_end.setGravity(Gravity.LEFT|Gravity.CENTER);
			b_end.setText(listmp3.get(i));
			b_end.setTextSize(55);
			
			LayoutParams param_209 = new LayoutParams();
			param_209.width = LayoutParams.MATCH_PARENT;
			param_209.height = LayoutParams.MATCH_PARENT;
			
		//	layout_179.width = LayoutParams.WRAP_CONTENT;
		//	layout_179.height = LayoutParams.MATCH_PARENT;
			//layout_179.weight = 1;
			b_end.setLayoutParams(param_209);
			ll_end.addView(b_end);
			linearLayout_21.addView(ll_end);
			ll_end.setVisibility(View.INVISIBLE);
		}		
		
	}
	
		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Recordings");

		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {


			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
				
		    	for(int i = 0;i< num;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Recordings/"  + listmp3.get(i) + ".mp3")));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num1 = 0;
					for(int i = 0;i< num;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num1;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Recordings/" + listmp3.get(i) + ".mp3");
			    			num1++;
			    		}
			    	}
					if(num1 == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num1);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
				}
		
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
			  }
			});
		
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
		alert = LightBox2.create();
		alert.show();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox1(View v){
		
		
		final LinearLayout ll[] = new LinearLayout[39];
		final CheckBox cb[] = new CheckBox[39];
		final Button b[] = new Button[39];
		final ImageButton ib[] = new ImageButton[39];
		
		final ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		ScrollView.setSmoothScrollingEnabled(true);
		
		final LinearLayout linearLayout_21 = new LinearLayout(this);
		//ScrollView.setBackgroundResource(R.drawable.gallary_back);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);
		
		
	for(int i = 0;i<39;i++){
				
		//LinearLayout gallary2_1Layout = new LinearLayout(this);
		//gallary2_1Layout.setId(R.id.gallary2_1Layout);
		//gallary2_1Layout.setBackgroundResource(Color.BLACK);
		ll[i] = new LinearLayout(this);
		
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		//CheckBox checkBox2_1 = new CheckBox(this);
		//cb[i].setId(R.id.checkBox2_1);
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[1] != -1){
		        		if(CurrentChecked[1] != k){
		        		cb[CurrentChecked[1]].setChecked(false);}
		        		CurrentChecked[1] = k;
		          	}
		        	if(CurrentChecked[1] == -1){
		            	CurrentChecked[1] = k;
		            	}
		            	
		        	}
		    	}
		    
		});
		}
		
		
		ll[i].addView(cb[i]);


		//Button sound2_1 = new Button(this);
		//sound2_1.setId(R.id.sound2_1);
		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(SoundNames[1][i]);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		//b[i].setTextSize((25/getApplicationContext().getResources().getDisplayMetrics().scaledDensity));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[1][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    b[j].setTextColor(Color.rgb(0,186,255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
	//	layout_179.width = LayoutParams.WRAP_CONTENT;
	//	layout_179.height = LayoutParams.MATCH_PARENT;
		//layout_179.weight = 1;
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		
	    LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
        final PopupWindow popupWindow = new PopupWindow(
        popupView, 
        LayoutParams.WRAP_CONTENT,  
              LayoutParams.WRAP_CONTENT);  
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        
      			
        ib[i].setOnClickListener(new OnClickListener() {  
	   
        	@Override  
	           public void onClick(View v) {  
	        	
        		//Creating the instance of PopupMenu  
	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
	            //Inflating the Popup using xml file  
	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
	           
	            //registering popup with OnMenuItemClickListener  
	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
	             public boolean onMenuItemClick(MenuItem item) {  
	              if(item.getTitle().equals("Set as ringtone")){
	            	  
	            	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[1][j]);
	              }
	              	              
	            
	              if(item.getTitle().equals("Set as notification tone")){
	            
	            	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[1][j]);
	  	            
	              }
	              return true;  
	             }  
	            });  
	  
	            popup.show();//showing popup menu  
	      	
	           }  
	          });
		
		
		
        
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		
		

		filenames1[i] = "p1" + Integer.toString(i);
		ins = getResources().openRawResource(ids1[i]);
		
		FileOutputStream fos = null;
		try {
		//	fos = openFileOutput(filenames1[i], Context.MODE_WORLD_READABLE);
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[1][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

    	
	}
		
		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Expressions");

		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 39;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[1][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 39;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[1][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
				}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
				  
			  }
			});
		
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
		

		LightBox2.show();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox2(View v){
		
		final LinearLayout ll[] = new LinearLayout[30];
		final CheckBox cb[] = new CheckBox[30];
		final Button b[] = new Button[30];
		final ImageButton ib[] = new ImageButton[30];
		
		final ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

	//	ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
		
	for(int i = 0;i<30;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[2] != -1){
		        		if(CurrentChecked[2] != k){
		        		cb[CurrentChecked[2]].setChecked(false);}
		        		CurrentChecked[2] = k;
		          	}
		        	if(CurrentChecked[2] == -1){
		            	CurrentChecked[2] = k;
		            	}
		            	
		        	}
		    	}
		    
		});
		}
		
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setText(SoundNames[2][i]);
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[2][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0, 186, 255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
       	@Override  
	           public void onClick(View v) {  
	        	
       		//Creating the instance of PopupMenu  
	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
	            //Inflating the Popup using xml file  
	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
	           
	            //registering popup with OnMenuItemClickListener  
	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
	             public boolean onMenuItemClick(MenuItem item) {  
	              if(item.getTitle().equals("Set as ringtone")){
	            	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[2][j]);
	  	          }
	              	              
	            
	              if(item.getTitle().equals("Set as notification tone")){
	            	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[1][j]);
	  	            
	              }
	              return true;  
	             }  
	            });  
	  
	            popup.show();//showing popup menu  
	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
       		
	           }  
	          });

		
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames2[i] = "p2" + Integer.toString(i);
		ins = getResources().openRawResource(ids2[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[2][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Animals");
		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 30;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[2][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 30;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[2][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
				  
			  }
			});
		
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
		

		LightBox2.show();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox3(View v){
		
		final LinearLayout ll[] = new LinearLayout[23];
		final CheckBox cb[] = new CheckBox[23];
		final Button b[] = new Button[23];
		final ImageButton ib[] = new ImageButton[23];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
		
	for(int i = 0;i<23;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[3] != -1){
		        		if(CurrentChecked[3] != k){
		        		cb[CurrentChecked[3]].setChecked(false);}
		        		CurrentChecked[3] = k;
		          	}
		        	if(CurrentChecked[3] == -1){
		            	CurrentChecked[3] = k;
		            	}
		            	
		        	}
		    	}
		    
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setText(SoundNames[3][i]);
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[3][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0,186,255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
       	@Override  
	           public void onClick(View v) {  
	        	
       		//Creating the instance of PopupMenu  
	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
	            //Inflating the Popup using xml file  
	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
	           
	            //registering popup with OnMenuItemClickListener  
	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
	             public boolean onMenuItemClick(MenuItem item) {  
	              if(item.getTitle().equals("Set as ringtone")){
	            	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[3][j]);
	  	            
	              }
	              	              
	            
	              if(item.getTitle().equals("Set as notification tone")){
	            	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[3][j]);
	  	            
	              }
	              return true;  
	             }  
	            });  
	  
	            popup.show();//showing popup menu  
	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
       		
	           }  
	          });
       
       	ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames3[i] = "p3" + Integer.toString(i);
		ins = getResources().openRawResource(ids3[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[3][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Cartoony");
		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 23;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[3][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 23;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[3][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
				
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
				  
			  }
			});
		
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
				LightBox2.show();
				
	}
	
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox4(View v){
		
		final LinearLayout ll[] = new LinearLayout[27];
		final CheckBox cb[] = new CheckBox[27];
		final Button b[] = new Button[27];
		final ImageButton ib[] = new ImageButton[27];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
		
	for(int i = 0;i<27;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[4] != -1){
		        		if(CurrentChecked[4] != k){
		        		cb[CurrentChecked[4]].setChecked(false);}
		        		CurrentChecked[4] = k;
		          	}
		        	if(CurrentChecked[4] == -1){
		            	CurrentChecked[4] = k;
		            	}
		            	
		        	}
		    	}
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setText(SoundNames[4][i]);
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[4][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0,186,255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
   	           
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	         	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[4][j]);
  	            
   	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[4][j]);
  	            
   	              }
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames4[i] = "p4" + Integer.toString(i);
		ins = getResources().openRawResource(ids4[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[4][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
            try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : General");
		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 27;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[4][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 27;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[4][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
				
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
				  
			  }
			});
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
		
		LightBox2.show();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox5(View v){
		
		final LinearLayout ll[] = new LinearLayout[36];
		final CheckBox cb[] = new CheckBox[36];
		final Button b[] = new Button[36];
		final ImageButton ib[] = new ImageButton[36];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
		
	for(int i = 0;i<36;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[5] != -1){
		        		if(CurrentChecked[5] != k){
		        		cb[CurrentChecked[5]].setChecked(false);}
		        		CurrentChecked[5] = k;
		          	}
		        	if(CurrentChecked[5] == -1){
		            	CurrentChecked[5] = k;
		            	}
		        	}
		    	}
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(SoundNames[5][i]);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[5][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0, 186, 255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
   	           
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	         	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[5][j]);
  	            
   	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[5][j]);
  	            
   	              }
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames5[i] = "p5" + Integer.toString(i);
		ins = getResources().openRawResource(ids5[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[5][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Machinery");
		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 36;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[5][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 36;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[5][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
			
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
				  
			  }
			});
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
		LightBox2.show();
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox6(View v){
		
		final LinearLayout ll[] = new LinearLayout[12];
		final CheckBox cb[] = new CheckBox[12];
		final Button b[] = new Button[12];
		final ImageButton ib[] = new ImageButton[12];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
		
	for(int i = 0;i<12;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[6] != -1){
		        		if(CurrentChecked[6] != k){
		        		cb[CurrentChecked[6]].setChecked(false);}
		        		CurrentChecked[6] = k;
		          	}
		        	if(CurrentChecked[6] == -1){
		            	CurrentChecked[6] = k;
		            	}
		        	}
		    	}
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(SoundNames[6][i]);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[6][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0, 186, 255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
   	           
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	         	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[6][j]);
  	            
   	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[6][j]);
  	            
   	              }
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames6[i] = "p6" + Integer.toString(i);
		ins = getResources().openRawResource(ids6[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[6][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
            try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Musical");
		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 12;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[6][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 12;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[6][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
			
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
			  }
			});
		
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
			}
		});

		LightBox2.show();
		
	}

	
	@SuppressWarnings("deprecation")
	public void OpenLightBox7(View v){
		final LinearLayout ll[] = new LinearLayout[10];
		final CheckBox cb[] = new CheckBox[10];
		final Button b[] = new Button[10];
		final ImageButton ib[] = new ImageButton[10];
		
		
		
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
				
	for(int i = 0;i<10;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[7] != -1){
		        		if(CurrentChecked[7] != k){
		        		cb[CurrentChecked[7]].setChecked(false);}
		        		CurrentChecked[7] = k;
		          	}
		        	if(CurrentChecked[7] == -1){
		            	CurrentChecked[7] = k;
		            	}
		            	
		        	}
		    	}
		    
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(SoundNames[7][i]);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[7][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0, 186, 255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
   	           
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	         	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[7][j]);
  	            
   	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[7][j]);
  	            
   	              }
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames7[i] = "p7" + Integer.toString(i);
		ins = getResources().openRawResource(ids7[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[7][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
            try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
	}

	
		ScrollView.addView(linearLayout_21);
			
		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Places");
		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 10;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[7][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 10;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[7][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
			
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
				  
			  }
			});
		
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);

		
		LightBox2.show();
		
	}
	
	@SuppressWarnings("deprecation")
	public void OpenLightBox8(View v){
		
		final LinearLayout ll[] = new LinearLayout[21];
		final CheckBox cb[] = new CheckBox[21];
		final Button b[] = new Button[21];
		final ImageButton ib[] = new ImageButton[21];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		//ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
	for(int i = 0;i<21;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[8] != -1){
		        		if(CurrentChecked[8] != k){
		        		cb[CurrentChecked[8]].setChecked(false);}
		        		CurrentChecked[8] = k;
		          	}
		        	if(CurrentChecked[8] == -1){
		            	CurrentChecked[8] = k;
		            	}
		        	}
		    	}
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(SoundNames[8][i]);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[8][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0,186,255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
   	           
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	         	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[8][j]);
  	            
   	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[8][j]);
  	            
   	              }
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames8[i] = "p8" + Integer.toString(i);
		ins = getResources().openRawResource(ids8[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[8][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
            try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Scary");
		LightBox2.setView(ScrollView);
		
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 21;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[8][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 21;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[8][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
			
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
					  
			  }
			});

		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);

		LightBox2.show();
		
	}

	
	@SuppressWarnings("deprecation")
	public void OpenLightBox9(View v){
		
		final LinearLayout ll[] = new LinearLayout[17];
		final CheckBox cb[] = new CheckBox[17];
		final Button b[] = new Button[17];
		final ImageButton ib[] = new ImageButton[17];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		//ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
	for(int i = 0;i<17;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[9] != -1){
		        		if(CurrentChecked[9] != k){
		        		cb[CurrentChecked[9]].setChecked(false);}
		        		CurrentChecked[9] = k;
		          	}
		        	if(CurrentChecked[9] == -1){
		            	CurrentChecked[9] = k;
		            	}
		            	
		        	}
		    	}
		    
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(SoundNames[9][i]);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying()  )
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[9][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0,186,255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
   	           
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	         	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[9][j]);
  	            
   	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[9][j]);
  	            
   	              }
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
		
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
	
		filenames9[i] = "p9" + Integer.toString(i);
		ins = getResources().openRawResource(ids9[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[9][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
            try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	
	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Voices");
		LightBox2.setView(ScrollView);
		
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 17;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[9][i])));
		    		}
		    	}
		    	
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 17;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[9][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
			
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
				  
			  }
			});
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
		LightBox2.show();
		
	}

	
	@SuppressWarnings("deprecation")
	public void OpenLightBox10(View v){
		
		final LinearLayout ll[] = new LinearLayout[18];
		final CheckBox cb[] = new CheckBox[18];
		final Button b[] = new Button[18];
		final ImageButton ib[] = new ImageButton[18];
		
		ScrollView2 ScrollView = new ScrollView2(this);
		LayoutParams layout_211 = new LayoutParams();
		layout_211.width = LayoutParams.MATCH_PARENT;
		layout_211.height = LayoutParams.MATCH_PARENT;
		ScrollView.setLayoutParams(layout_211);
		
		LinearLayout linearLayout_21 = new LinearLayout(this);
		LayoutParams layout_212 = new LayoutParams();
		layout_212.width = LayoutParams.MATCH_PARENT;
		layout_212.height = LayoutParams.MATCH_PARENT;
		linearLayout_21.setOrientation(LinearLayout.VERTICAL);
		linearLayout_21.setLayoutParams(layout_212);

		//ImageButton overflow_button2_1 = new ImageButton(this);
		//overflow_button2_1.setId(R.id.overflow_button2_1);
		
	for(int i = 0;i<18;i++){
		
		ll[i] = new LinearLayout(this);
		ll[i].setGravity(Gravity.RIGHT);
		ll[i].setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layout_213 = new LayoutParams();
		layout_213.width = LayoutParams.FILL_PARENT;
		layout_213.height = LayoutParams.WRAP_CONTENT;
		ll[i].setLayoutParams(layout_213);
		
		
		cb[i] = new CheckBox(this);
		cb[i].setGravity(Gravity.CENTER);
		cb[i].setMinimumHeight((int) (50/getApplicationContext().getResources().getDisplayMetrics().density));
		LayoutParams layout_214 = new LayoutParams();
		layout_214.width = LayoutParams.WRAP_CONTENT;
		layout_214.height = LayoutParams.WRAP_CONTENT;
		cb[i].setLayoutParams(layout_214);
		final int k = i;
		if(MessageIntent2.equals("attach")){
		cb[i].setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
		    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
		    {
		        if ( isChecked )
		        {
		        	if(CurrentChecked[10] != -1){
		        		if(CurrentChecked[10] != k){
		        		cb[CurrentChecked[10]].setChecked(false);}
		        		CurrentChecked[10] = k;
		          	}
		        	if(CurrentChecked[10] == -1){
		            	CurrentChecked[10] = k;
		            	}
		            	
		        	}
		    	}
		    
		});
		}
		ll[i].addView(cb[i]);


		b[i] = new Button(this);
		b[i].setGravity(Gravity.LEFT|Gravity.CENTER);
		b[i].setText(SoundNames[10][i]);
		b[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pp1));
		b[i].setTextSize(18);
		Typeface font = Typeface.createFromAsset(getAssets(), "TitleFont.otf");
		b[i].setTypeface(font);
		final int j = i;
		b[i].setOnClickListener(new OnClickListener() {
			
        	public void onClick(View v) {
        		final Button Lastb = LastButton;
        		if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                    LastButton.setTextColor(Color.BLACK);
                    LastButton = b[j];
                    if(Lastb.equals(b[j])){
                    return;}
                }
        		LastButton = b[j];
        		
        		if(!mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
        		try {
                	
                //    AssetFileDescriptor afd;
                //    afd = getAssets().openFd("TumseNa2.mp3");
                    FileInputStream in = 
                    		new FileInputStream(new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[10][j] ));
                    //  File file1 = getDir("sound_clip", Context.MODE_WORLD_READABLE);
                   // mp.setDataSource("sound_clip");
                    mp.setDataSource(in.getFD());
                    
                   // mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength() );
                    mp.prepare();
                 //   mp.prepareAsync(); 
                    mp.start();
                    LastButton.setTextColor(Color.rgb(0, 186, 255));
                } catch (IllegalStateException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }  
            }
        } );		
		
		LinearLayout.LayoutParams param_215 = new LinearLayout.LayoutParams(
                0,
                LayoutParams.MATCH_PARENT, 1.0f);
		b[i].setLayoutParams(param_215);
		ll[i].addView(b[i]);
		
		ib[i] = new ImageButton(this);
		ib[i].setImageResource(R.drawable.gallary_overflow);
		LayoutParams layout_216 = new LayoutParams();
		layout_216.height = LayoutParams.WRAP_CONTENT;
		layout_216.width = LayoutParams.WRAP_CONTENT;
		ib[i].setLayoutParams(layout_216);
		ib[i].setBackgroundColor(Color.alpha(0));
		LayoutInflater layoutInflater 
	     = (LayoutInflater)getBaseContext()
	      .getSystemService(LAYOUT_INFLATER_SERVICE);  
		final View popupView = layoutInflater.inflate(R.layout.popup, null);  
       final PopupWindow popupWindow = new PopupWindow(
       popupView, 
       LayoutParams.WRAP_CONTENT,  
             LayoutParams.WRAP_CONTENT);  
       popupWindow.setOutsideTouchable(true);
       popupWindow.setBackgroundDrawable(new BitmapDrawable());
       
       ib[i].setOnClickListener(new OnClickListener() {  
    	   
          	@Override  
   	           public void onClick(View v) {  
   	        	
          		//Creating the instance of PopupMenu  
   	            PopupMenu popup = new PopupMenu(GallaryActivity.this,ib[j]);  
   	            //Inflating the Popup using xml file  
   	            popup.getMenuInflater().inflate(R.menu.popup_tone, popup.getMenu());  
   	           
   	            //registering popup with OnMenuItemClickListener  
   	            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {  
   	             public boolean onMenuItemClick(MenuItem item) {  
   	              if(item.getTitle().equals("Set as ringtone")){
   	         	  confirmSetRingtone(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[10][j]);
  	              }
   	              	              
   	            
   	              if(item.getTitle().equals("Set as notification tone")){
   	         	  confirmSetNotification(b[j].getText(),Environment.getExternalStorageDirectory()+ "/Chaudible/Database/" + SoundNames[10][j]);
  	            
   	              }
   	              return true;  
   	             }  
   	            });  
   	  
   	            popup.show();//showing popup menu  
   	        //    ScrollView.requestChildRectangleOnScreen(linearLayout_21, rect, true);
          		
   	           }  
   	          });
          
		ll[i].addView(ib[i]);
		linearLayout_21.addView(ll[i]);
		
		filenames10[i] = "p10" + Integer.toString(i);
		ins = getResources().openRawResource(ids10[i]);
		FileOutputStream fos = null;
		try {
			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
			if(!f1.exists()){
				f1.mkdirs();
			}
			f1 = new File(f1.getAbsoluteFile()+ "/" + SoundNames[10][i]);
			if(f1.exists()){
				continue;
			}
			fos = new FileOutputStream(f1.getAbsoluteFile());
    		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        try {
        	int bufferSize = 1024;
        	byte[] buffer = new byte[bufferSize];
        	int len = 0;
        	while ((len = ins.read(buffer)) != -1) {
        	    fos.write(buffer, 0, len);
        	}
        	if(fos!=null)
        	    fos.close();
        	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
			ins.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		
	}

		ScrollView.addView(linearLayout_21);

		AlertDialog.Builder LightBox2 = new AlertDialog.Builder(this);
		LightBox2.setTitle("Select sound clip : Ziggy ziggy");
		LightBox2.setView(ScrollView);
		LightBox2.setPositiveButton("Share", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				if(MessageIntent2.equals("share")){
				ArrayList<Uri> ShareList = new ArrayList<Uri>();
				Intent share = new Intent(Intent.ACTION_SEND_MULTIPLE);
				share.setType("audio/*");
		        
		    	for(int i = 0;i< 18;i++){
		    		if(cb[i].isChecked()){
		    			ShareList.add(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
		    					+ "/Chaudible/Database/" + SoundNames[10][i])));
		    		}
		    	}
		    	if(ShareList.size() == 0){
		    		ShowToast("No sound selected");
		    		return;
		    	}
		    	share.putParcelableArrayListExtra(Intent.EXTRA_STREAM, ShareList);
		    	startActivity(Intent.createChooser(share, "Share Sound File"));
			}
				
				if(MessageIntent2.equals("attach")){
					Intent returnIntent = new Intent();
					//ArrayList<String> ShareList = new ArrayList<String>();
					int num = 0;
					for(int i = 0;i< 18;i++){
			    		if(cb[i].isChecked()){
			    			final int j = num;
			    			returnIntent.putExtra("result" + Integer.toString(j),Environment.getExternalStorageDirectory()
			    					+ "/Chaudible/Database/" + SoundNames[10][i]);
			    			num++;
			    		}
			    	}
					if(num == 0){
						ShowToast("No sound selected");
						return;
					}
			    	returnIntent.putExtra("NumResults", num);
					setResult(Activity.RESULT_OK, returnIntent);
			    	finish();
				}
			
				
			}
			
		});
		
		LightBox2.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			  public void onClick(DialogInterface dialog, int whichButton) {
					if(mp.isPlaying())
	                {  
	                    mp.stop();
	                    mp.reset();
	                }
			  }
			});
		
		LightBox2.setOnCancelListener(new  DialogInterface.OnCancelListener(){

			@Override
			public void onCancel(DialogInterface arg0) {
				// TODO Auto-generated method stub
				if(mp.isPlaying())
                {  
                    mp.stop();
                    mp.reset();
                }
		
			}
			
		}
		);
		
		LightBox2.show();
		
		
	}

	
	@Override
	public void onBackPressed(){
		
		super.onBackPressed();
	    overridePendingTransition(R.animator.anim1,R.animator.anim2);
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.gallary, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.Rate3) {
			
			Dialog d = new Dialog(GallaryActivity.this);
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
		if (id == R.id.ShareWithFriends3) {
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "Check this new app and discover the magic of sounds\n" 
			+ Uri.parse("http://play.google.com/store/apps/details?id=com.desire.chaudible")
			);
			sendIntent.setType("text/plain");
			startActivity(Intent.createChooser(sendIntent, getResources().getText(R.string.send_to)));
			
		}
		if(id == R.id.CreatedBy3){
			Dialog d = new Dialog(GallaryActivity.this);
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
		if(id == R.id.AboutChaudible3){
			Dialog d = new Dialog(GallaryActivity.this);
			d.setTitle("About Chaudible");
			d.setContentView(R.layout.aboutc);
			d.show();
		}
		
		return super.onOptionsItemSelected(item);
	
	}

	
}
