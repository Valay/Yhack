package com.sketcher;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	public final static String EXTRA_MESSAGE = "com.sketcher.MESSAGE";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            	
            	cam.startPreview();
            	/*{
            		cam.takePicture (null, null, null, jpeg);
            		try{
                		Thread.sleep(2000);
            		}catch(Exception e){
            			e.printStackTrace();
            		}
            		if( (bmp =jpeg.getImage()) != null){
            			ImageView img = new ImageView(this);
            			img.setImageBitmap(bmp);
                    	setContentView(img);	
            		}
            	}*/
            	//cam.stopPreview();
            	
        		
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
