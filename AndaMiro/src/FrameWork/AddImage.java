package FrameWork;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

public class AddImage {
	
	BitmapDrawable Image;
	
	public Bitmap initImage(int name)
	{
		Context ctx=AppManager.getInstance().getContext();
		BitmapDrawable Image;
		Image = (BitmapDrawable)ctx.getResources().getDrawable(name);
		return Image.getBitmap();
	}
	
}
