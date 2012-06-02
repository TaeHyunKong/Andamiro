package PlayGame;


import java.util.ArrayList;

import FrameWork.AppManager;
import Object.Check;
import Object.Count;
import Object.Pause;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

import com.AndaMiro.R;

public class Ui {
	Bitmap checkbitmap[]=new Bitmap[4];
	public boolean life=false;
	public boolean ready=true;
	public boolean check=false;
	public boolean pause;
	private boolean imagelife=true;
	public int goodNum;
	private long FrameTimer;
	private int Timer;
	private final int listNum=1;
	private final int pauseNum=1;
	
	Count mCount=null;
	ArrayList<Check> mCheck = new ArrayList<Check>();
	ArrayList<Pause> mPauses = new ArrayList<Pause>();
	
	public Ui(){
		Init();
	}
	
	public void ImageSet(){
		checkbitmap[0]=AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.good1);
		checkbitmap[1]=AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.good2);
		checkbitmap[2]=AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.good3);
		checkbitmap[3]=AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.gameover);
		imagelife=false;
	}
	public void Init(){
		goodNum=0;
		FrameTimer=0;
		Timer=-1;
		pause=false;
	}
	
	public void Update(){
		long GameTime=java.lang.System.currentTimeMillis();
		if(check){
			if(GameTime > FrameTimer + 1000){
				FrameTimer = GameTime;
				if(Timer<2) Timer++;
			}
		}
		if(ready){
			if(mCount==null){
				mCount=new Count(AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.counter));
				mCount.InitSpriteData(450, 350, 1000, 4);
				mCount.ox=15;
				mCount.oy=225;
				mCount.mCurrentFrame=-1;
			}
			if(mCount.mCurrentFrame>=3) {
				mCount.mCurrentFrame=-1;
				ready=false;
				
			}
			mCount.Update(GameTime);
		}	
			if(check){
				if(mCheck.size()<listNum)
				mCheck.add(new Check(checkbitmap[goodNum]));
			}
		if(pause){
			if(mPauses.size()<pauseNum) 
				mPauses.add(new Pause(AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.pause)));
		}
	}
	
	public void Render(Canvas canvas){
		if(imagelife) ImageSet();
		Update();
		if(ready) mCount.Draw(canvas);
		if(check){
			for (int i = 0; i <  mCheck.size(); i++) {
				   Check check = mCheck.get(i);
				   canvas.drawBitmap(check.bitmap,0, 255, null);
			}
			for(int i=0; i<mCheck.size(); i++)
			{
				if(Timer==2){
					mCheck.remove(i);
					check=false;
					Timer=-1;
					AppManager.getInstance().getGameMain().mThread.mPlayGame.playerMoney=0;
					if(goodNum==0) AppManager.getInstance().getGameMain().mThread.mPlayGame.timer-=50; //4
					if(goodNum==1) AppManager.getInstance().getGameMain().mThread.mPlayGame.timer+=6;
					if(goodNum==2) AppManager.getInstance().getGameMain().mThread.mPlayGame.timer+=16;
					if(goodNum==3) {
						AppManager.getInstance().getGameMain().mThread.mResult.life=true;
						AppManager.getInstance().getGameMain().mThread.mPlayGame.life=false;
						AppManager.getInstance().getGameMain().mThread.mUi.life=false;
					}
					AppManager.getInstance().getGameMain().mThread.mPlayGame.limitedTime=-1;
				}
			}
		}
		if(pause){
			for(int i=0; i<mPauses.size(); i++){
				Pause apause = mPauses.get(i);
				AppManager.getInstance().getGameMain().mdDrawImage.drawBmp(canvas, apause.bitmap, 0, 0);
			}
		}
		
	}
	public boolean onTouchEvent(MotionEvent event) {
		int select=event.getAction();
		int t_x=(int) event.getX();
		int t_y=(int) event.getY();
		Rect touch_rect=new Rect();
		switch(select){
		case MotionEvent.ACTION_DOWN:
		if(pause){
			//계속하기
			touch_rect.set(46,140,329,209); 
			if(touch_rect.contains(t_x, t_y)){
				pause=false;
			}
			//다시하기
			touch_rect.set(56,252,340,328); 
			if(touch_rect.contains(t_x, t_y)){
				Init();
				ready=true;
				AppManager.getInstance().getGameMain().mThread.mPlayGame.Init();
				AppManager.getInstance().getGameMain().mThread.mPlayGame.mBoolen.remove(0);
				
			}
			//메인메뉴
			touch_rect.set(60,350,323,437); 
			if(touch_rect.contains(t_x, t_y)){
				Init();
				ready=true;
				AppManager.getInstance().getGameMain().mThread.mPlayGame.Init();
				life=false;
				AppManager.getInstance().getGameMain().mThread.mPlayGame.life=false;
				AppManager.getInstance().getGameMain().mThread.mIntro.life=true;
				
				AppManager.getInstance().getGameMain().mThread.mPlayGame.mBoolen.remove(0);
				
			}
		}
		}
		return false;
	}
}
