package FrameWork;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class DrawImage {
	public void drawBmp(Canvas cvs, Bitmap bitmap, float x, float y)
	{
		if(bitmap != null)
		{
			cvs.drawBitmap(bitmap, x, y, null);
		}
	}
	
	public void drawRectBmp(Canvas cvs,Bitmap bitmap, Rect rect1, Rect rect2)
	{
		if(bitmap !=null )
		{
			cvs.drawBitmap(bitmap, rect1, rect2, null);
		}
	}
	
	//Fade in, out
	Paint p = new Paint();
	int FadeIn = 0, FadeOut= 255, FadeSpeed = 0;
	boolean Fade = true;
	boolean Reset = false;
	
	public boolean setAlpha(boolean reset)
	{
		Reset = reset;
		return Reset;
	}
	
	public void drawFade(Canvas cvs, Bitmap bitmap,int x, int y, int index, int speed)
	{
		if(setAlpha(Reset))
		{
			FadeIn=0;
			FadeOut=255;
			Reset=false;
		}
		
		if(speed == 0) FadeSpeed =3;
		else if(speed == 1) FadeSpeed = 17;
		else if(speed == 2) FadeSpeed = 51;
		else if(speed == 3) FadeSpeed = 85;
		else FadeSpeed =3;
		
		switch(index)
		{
		case 0:
			FadeIn += FadeSpeed;
			if(FadeIn >= 255) FadeIn=255;
			p.setAlpha(FadeIn);
			cvs.drawBitmap(bitmap, x, y, p);
			break;
		case 1:
			FadeOut -= FadeSpeed;
			if(FadeIn <=0) FadeOut=0;
			p.setAlpha(FadeOut);
			cvs.drawBitmap(bitmap, x, y, p);
			break;
		case 2:
			if(Fade)FadeIn += FadeSpeed;
			else FadeIn -=FadeSpeed;
			
			if(FadeIn >= 255)Fade=false;
			else if(FadeIn <= 0)Fade=true;
			
			p.setAlpha(FadeIn);
			cvs.drawBitmap(bitmap, x, y, p);
			break;
		case 3:
			if(Fade)FadeOut -= FadeSpeed;
			else FadeOut += FadeSpeed;
			if(FadeOut <=0)Fade = false;
			else if(FadeOut >= 255)Fade = true;
			p.setAlpha(FadeOut);
			cvs.drawBitmap(bitmap, x, y, p);
			break;
			default:
				index = 0;
				break;
		}
				
	}
}
