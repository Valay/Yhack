package com.sketcher;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaFormat;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class FrameProcesser implements Camera.PictureCallback{
	Context c;
	Bitmap bmp=null;
	public FrameProcesser(Context c) {
		this.c = c;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onPictureTaken(byte[] image, Camera cam) {
		
		try {
			FileOutputStream fos = new FileOutputStream("test.jpg");
			fos.write(image);
			fos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayInputStream imgData = new ByteArrayInputStream(image);
		Bitmap bmp = BitmapFactory.decodeStream(imgData);
		Log.i("BMP", "BMP");
	}
	public Bitmap getImage(){
		return bmp;
	}

}
