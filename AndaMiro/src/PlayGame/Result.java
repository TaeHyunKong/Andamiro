package PlayGame;

import com.AndaMiro.R;

import FrameWork.AppManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;


public class Result  {
	public boolean life=false;
	Bitmap result=null;
	int resultNum=0;
	
	public Result(){
		Init();
	}
	public void Init(){
		resultNum=0;
	}
	public void Update(){
		result= AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.result1+resultNum);
		
	}
	public void Render(Canvas cvs){
		Update();
		AppManager.getInstance().getGameMain().mdDrawImage.drawBmp(cvs, result, 0, 0);
	}
	public boolean onTouchEvent(MotionEvent event) {
		int select=event.getAction();
		int t_x=(int) event.getX();
		int t_y=(int) event.getY();
		Rect touch_rect=new Rect();
		
		switch(select){
		case MotionEvent.ACTION_DOWN:
			if(resultNum==0){
				touch_rect.set(152,635,300,720);
				if(touch_rect.contains(t_x, t_y)) resultNum=1;
			}
			if(resultNum==1){
				touch_rect.set(30,654,215,770);
				if(touch_rect.contains(t_x, t_y)) {
					Init();
					AppManager.getInstance().getGameMain().mThread.mUi.Init();
					AppManager.getInstance().getGameMain().mThread.mPlayGame.Init();
					AppManager.getInstance().getGameMain().mThread.mUi.ready=true;
					life=false;
					AppManager.getInstance().getGameMain().mThread.mUi.life=true;
					AppManager.getInstance().getGameMain().mThread.mPlayGame.life=true;
				}
				touch_rect.set(295,665,470,765);
				if(touch_rect.contains(t_x, t_y)) {
					Init();
					AppManager.getInstance().getGameMain().mThread.mUi.Init();
					AppManager.getInstance().getGameMain().mThread.mPlayGame.Init();
					AppManager.getInstance().getGameMain().mThread.mUi.ready=true;
					life=false;
					AppManager.getInstance().getGameMain().mThread.mIntro.life=true;
				}
			}
		}
		return false;
	}
}
