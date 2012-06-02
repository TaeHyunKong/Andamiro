package com.AndaMiro;

import FrameWork.AddImage;
import FrameWork.AppManager;
import FrameWork.DrawImage;
import PlayGame.Intro;
import PlayGame.PlayGame;
import PlayGame.Result;
import PlayGame.Ui;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class GameMain extends SurfaceView implements Callback {
	private Context mContext;
	private SurfaceHolder mSurfaceHolder;
	private boolean mRun = false;
	public GameThread mThread;
	
	//프레임 워크 
	public AddImage mAddImage = new AddImage();
	public DrawImage mdDrawImage = new DrawImage();
	
	public GameMain(Context context) {
	super(context);
	
	mContext = context;
	mSurfaceHolder = getHolder();
	mSurfaceHolder.addCallback(this);
	mThread = new GameThread(mSurfaceHolder);
	
	AppManager.getInstance().setContext(context);
	AppManager.getInstance().setGameMain(this);

}
public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	// TODO Auto-generated method stub
	Log.i("[뷰]", "2.서페이스 교체");

}
//게임 시작할 때
public void surfaceCreated(SurfaceHolder arg0) {
	Log.i("[뷰]", "1.서페이스 생성");
	if(mThread.getState() == Thread.State.TERMINATED){
		mThread = new GameThread(arg0);
		mThread.start();
	}else{
		mThread.start();
	}
	mThread.setRun(true);
}

public void surfaceDestroyed(SurfaceHolder arg0) {
	// TODO Auto-generated method stub
	Log.i("[뷰]", "3.서페이스 파괴");
	mThread.setRun(false);
	try{
		mThread.join();
	}catch(InterruptedException e){
		e.printStackTrace();
	}

}

public class GameThread extends Thread{
	private Paint paint = new Paint();
	//게임플레
	public Intro mIntro=new Intro();
	public PlayGame mPlayGame=new PlayGame();
	public Ui mUi=new Ui();
	public Result mResult = new Result();
	
	public GameThread(SurfaceHolder surfaceholder){

	}
	
	public void run()
	{
		while(mRun)
		{
			Canvas canvas = null;
			try
			{
				canvas = mSurfaceHolder.lockCanvas(null);
				synchronized (mSurfaceHolder) {
					if(mIntro.life) mIntro.Render(canvas);
					if(mPlayGame.life)mPlayGame.Render(canvas);
					if(mUi.life)mUi.Render(canvas);
					if(mResult.life)mResult.Render(canvas);
					
					AndaMiroActivity.CheckState(canvas, paint);
				}
			}
			finally
			{
				if(canvas != null)
				{
					mSurfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
	public boolean setRun(boolean run)
	{
		mRun=run;
		return mRun;
	}
	
}
public boolean onTouchEvent(MotionEvent event){
	if(mThread.mIntro.life)mThread.mIntro.onTouchEvent(event);
	if(!mThread.mUi.pause){
		if(!mThread.mUi.ready){
			if(!AppManager.getInstance().getGameMain().mThread.mUi.check)
			if(mThread.mPlayGame.life)mThread.mPlayGame.onTouchEvent(event);
		}
	}
	if(mThread.mUi.pause){
		if(mThread.mUi.life) mThread.mUi.onTouchEvent(event);
	}
	if(mThread.mResult.life) mThread.mResult.onTouchEvent(event);
	return true;
}

}

