package com.sketcher;

import java.io.ByteArrayInputStream;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.sketcher.MESSAGE";
	
    @TargetApi(Build.VERSION_CODES.GINGERBREAD_MR1)
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        MediaMetadataRetriever md = new MediaMetadataRetriever();
        
        md.setDataSource("/storage/emulated/0/DCIM/Camera/VID_20131108_214558.mp4");
        
        //Bitmap bmp = md.getFrameAtTime(1);

        
       CameraCapture c = new CameraCapture();
//        
        if( c.checkCameraHardware(this.getApplicationContext()) ){
//        	// Okay the mobile has camera in built 
        	Camera cam = c.getCameraInstance();
        	if(cam != null){
            	TextView t = new TextView(this);
            	t.setText("Found camera");
            	setContentView(t);
            	
            	int j=0;
            	FrameProcesser jpeg = new FrameProcesser(this);
            	Bitmap bmp= null;
            	try{
                	SurfaceView sv = new SurfaceView(this);
                	setContentView(sv);
                	cam.setPreviewDisplay(sv.getHolder());
                	cam.startPreview();
 
               		cam.takePicture (null, null, null, jpeg);
            		try{
                		Thread.sleep(2000);
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            		if( (bmp =jpeg.getImage()) == null){
            			TextView t1 = new TextView(this);
                    	t1.setText("NOT Captured Image");
                    	setContentView(t1);
            		}
            	}catch(Exception e)
            	{
            		
            	}
            	cam.stopPreview();
            	
        		
        	}else{
            	TextView t = new TextView(this);
            	t.setText(" Can not Open camera");
            	setContentView(t);

        	}
        }
        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText) findViewById(R.id.editText1);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
}
