package PlayGame;

import com.AndaMiro.R;

import FrameWork.AppManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Intro{
	Bitmap intro=null;
	private int introNum;
	private long FrameTimer;
	public boolean life=true;
	
	public Intro(){
		Init();
	}
	public void Init() {
		introNum=-1;
		FrameTimer=0;
	}

	public void Update() {
		long GameTime = java.lang.System.currentTimeMillis();
		if(GameTime > FrameTimer + 100){
			FrameTimer = GameTime;
			if(introNum<2) introNum++;
		}
		intro=AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.opening000+introNum);
	}

	public void Render(Canvas canvas) {
		Update();
		AppManager.getInstance().getGameMain().mdDrawImage.drawBmp(canvas, intro, 0, 0);
	}

	public boolean onTouchEvent(MotionEvent event) {
		int select=event.getAction();
		int t_x=(int) event.getX();
		int t_y=(int) event.getY();
		Rect touch_rect=new Rect();
		
		switch(select){
		case MotionEvent.ACTION_DOWN:
			touch_rect.set(118,403,414,486);
			if(touch_rect.contains(t_x, t_y))
			{
				life=false;
				AppManager.getInstance().getGameMain().mThread.mPlayGame.life=true;
				AppManager.getInstance().getGameMain().mThread.mUi.life=true;
			}
			touch_rect.set(131,675,392,752);
			if(touch_rect.contains(t_x,t_y))
			{
				android.os.Process.killProcess(android.os.Process.myPid());
			}
		}
		return false;
	}

}
