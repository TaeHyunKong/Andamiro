package FrameWork;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class SpriteAnimation{
	private Rect mRect; //�׷��� ����
	private int mFps; //�ʴ� ������
	private int mFrameNum; //������ ����
	public int mCurrentFrame; //���� ������
	private int mSpriteWidth; //����
	private int mSpriteHeight; //����
	public int ox;
	public int oy;
	Bitmap mBitmap;
	
	private long mFrameTimer;
	
	public SpriteAnimation(Bitmap bitmap) {
		//
		mBitmap = bitmap;
		mRect = new Rect(0,0,0,0);
		mFrameNum = 0;
		mCurrentFrame = 0;
		ox=0;
		oy=0;
	}
	
	public void InitSpriteData(int width,int height,int fps,int framenum){
		mSpriteWidth = width;
		mSpriteHeight = height;
		//mRect.set(0,0,mSpriteWidth,mSpriteHeight);
		mRect.top = 0;
		mRect.bottom = mSpriteHeight;
		mRect.left = 0;
		mRect.right = mSpriteWidth;
		//
		mFps = fps;
		mFrameNum = framenum;
	}
	
	public void Draw(Canvas cvs){
		Rect dest = new Rect(ox,oy,ox+mSpriteWidth,oy+mSpriteHeight);
		cvs.drawBitmap(mBitmap, mRect, dest, null);
	}
	
	public void Update(long GameTime) {
		if(GameTime > mFrameTimer + mFps){
			mFrameTimer = GameTime;
			mCurrentFrame ++;
			if( mCurrentFrame >= mFrameNum ) mCurrentFrame = 0;
		}
		mRect.left = mCurrentFrame * mSpriteWidth;
		mRect.right= mRect.left + mSpriteWidth;
	}
}
