package com.desire.chaudible;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;


public class SplashActivity extends FragmentActivity {

	private static int SplashTimeOut = 2500;
	private Animation fadeLogo;
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
	int[] NumberOfSounds = {0,39,30,23,27,36,12,10,21,17,18};
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView tv = (TextView) findViewById(R.id.appName1);
    	Typeface type = Typeface.createFromAsset(getAssets(),"crayon.ttf"); 
		tv.setText("CHAUDIBLE");
		tv.setTypeface(type);      
		tv.setTextColor(Color.WHITE);
		fadeLogo = AnimationUtils.loadAnimation(SplashActivity.this, R.animator.anim5);
    	tv.startAnimation(fadeLogo);
    	
    	/*
    	
    	for(int i=0;i<NumberOfSounds[1];i++){
    		filenames1[i] = "p1" + Integer.toString(i);
    		ins = getResources().openRawResource(ids1[i]);
    		
    		FileOutputStream fos = null;
    		try {
    		//	fos = openFileOutput(filenames1[i], Context.MODE_WORLD_READABLE);
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames1[i]);
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
    	
    	for(int i=0;i<NumberOfSounds[2];i++){
    		filenames2[i] = "p2" + Integer.toString(i);
    		ins = getResources().openRawResource(ids2[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames2[i]);
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
    	
    	for(int i=0;i<NumberOfSounds[3];i++){
    		filenames3[i] = "p3" + Integer.toString(i);
    		ins = getResources().openRawResource(ids3[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames3[i]);
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

    	for(int i=0;i<NumberOfSounds[4];i++){
    		filenames4[i] = "p4" + Integer.toString(i);
    		ins = getResources().openRawResource(ids4[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames4[i]);
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

    	for(int i=0;i<NumberOfSounds[5];i++){
    		filenames5[i] = "p5" + Integer.toString(i);
    		ins = getResources().openRawResource(ids5[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames5[i]);
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

    	for(int i=0;i<NumberOfSounds[6];i++){
    		filenames6[i] = "p6" + Integer.toString(i);
    		ins = getResources().openRawResource(ids6[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames6[i]);
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

    	
    	for(int i=0;i<NumberOfSounds[7];i++){
    		filenames7[i] = "p7" + Integer.toString(i);
    		ins = getResources().openRawResource(ids7[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames7[i]);
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

    	for(int i=0;i<NumberOfSounds[8];i++){
    		filenames8[i] = "p8" + Integer.toString(i);
    		ins = getResources().openRawResource(ids8[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames8[i]);
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

    	
    	for(int i=0;i<NumberOfSounds[9];i++){
    		filenames9[i] = "p9" + Integer.toString(i);
    		ins = getResources().openRawResource(ids9[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames9[i]);
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

    	for(int i=0;i<NumberOfSounds[10];i++){
    		filenames10[i] = "p10" + Integer.toString(i);
    		ins = getResources().openRawResource(ids10[i]);
    		FileOutputStream fos = null;
    		try {
    			File f1 = new File(Environment.getExternalStorageDirectory()+ "/Chaudible/Database");
    			if(!f1.exists()){
    				f1.mkdirs();
    			}
    			f1 = new File(f1.getAbsoluteFile()+ "/" + filenames10[i]);
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
			
    	
    	/*
    	
    	final String FILENAME = "sound_clip.mp3";
        InputStream ins = getResources().openRawResource(R.raw.p10);
        
        FileOutputStream fos = null;
		try {
			fos = openFileOutput(FILENAME, Context.MODE_WORLD_READABLE);
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
        
    	 */
    	
        new Handler().postDelayed(new Runnable(){

        	@Override
        	public void run(){
        		Intent in = new Intent(SplashActivity.this,MainActivity.class);
        		in.setAction(Intent.ACTION_MAIN);
        		startActivity(in);
        		finish();
        	}
        },SplashTimeOut);
        
       
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
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
}
