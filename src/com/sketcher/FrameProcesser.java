package com.sketcher;

import java.io.ByteArrayInputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.media.MediaFormat;
import android.provider.MediaStore.Images.Media;
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
		ByteArrayInputStream imgData = new ByteArrayInputStream(image);
		Bitmap bmp = BitmapFactory.decodeStream(imgData);
	}
	public Bitmap getImage(){
		return bmp;
	}

}
