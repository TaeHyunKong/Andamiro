package PlayGame;

import java.util.ArrayList;

import com.AndaMiro.R;

import FrameWork.AppManager;
import Object.Boolen;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class PlayGame {
	public boolean life=false;
	Bitmap background=null;
	long FrameTimer;
	public int timer;  //타이머
	private final int boollenSize=1;  //말풍선 갯수
	private int customerMoney;   //고객돈
	private int productMoney;    //물건값
	public int playerMoney;     //플레이어의 돈
	public int limitedTime;
	private int customerCount;   //보낸 고객수
	private int levelNum=0;
	
	Paint paint = new Paint();
	public ArrayList<Boolen> mBoolen = new ArrayList<Boolen>();
	
	public PlayGame(){
		Init();
	}
	
	public void Init(){
		FrameTimer=0;
		timer=60;
		customerMoney=0;
		productMoney=0;
		playerMoney=0;
		limitedTime=0;
		customerCount=0;
		levelNum=1000;
	}
	
	public void Update(){
		if(!AppManager.getInstance().getGameMain().mThread.mUi.check){
			long GameTime = java.lang.System.currentTimeMillis();
		
		
		if(GameTime > FrameTimer + 1000){
			FrameTimer = GameTime;
			
			if(!AppManager.getInstance().getGameMain().mThread.mUi.ready){
				if(!AppManager.getInstance().getGameMain().mThread.mUi.check){
					timer-=1;
					limitedTime++;
					if(limitedTime==10){
					AppManager.getInstance().getGameMain().mThread.mUi.check=true; //십초가되면 멈춰라
					AppManager.getInstance().getGameMain().mThread.mUi.goodNum=0;
					customerCount++; //고객수 
					limitedTime=0;//초기화
					}
				}
			}
		}
		
		}
		if(background==null) background=AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.background);
		if(mBoolen.size()<boollenSize) {
			mBoolen.add(new Boolen(AppManager.getInstance().getGameMain().mAddImage.initImage(R.drawable.boollen),levelNum));
		}
		if(AppManager.getInstance().getGameMain().mThread.mUi.check)mBoolen.remove(0);
		
		//시간이 0초이하가 되면 gameover
		if(timer<=0) {
			AppManager.getInstance().getGameMain().mThread.mUi.goodNum=3;
			AppManager.getInstance().getGameMain().mThread.mUi.check=true;
		}
		//레벨
		if(customerCount<10) levelNum=1000;
		if(customerCount<20) levelNum=5000;
		if(customerCount<30) levelNum=10000;
		
	}
	
	public void Render(Canvas canvas){
		if(!AppManager.getInstance().getGameMain().mThread.mUi.pause){
			Update();
			AppManager.getInstance().getGameMain().mdDrawImage.drawBmp(canvas, background, 0, 0);
		
			//시간초
			paint.setColor(Color.RED);
			paint.setTextSize(30);
			canvas.drawText("시간 초 : " + timer, 210, 130, paint);
			canvas.drawText("고객 수: " + customerCount, 210, 180, paint);
		}
		//계산할 돈
		if(!AppManager.getInstance().getGameMain().mThread.mUi.ready){
			if(!AppManager.getInstance().getGameMain().mThread.mUi.check)
				for(int i=0; i<mBoolen.size(); i++){
					Boolen bollen = mBoolen.get(i);
					AppManager.getInstance().getGameMain().mdDrawImage.drawBmp(canvas, bollen.bitmap, 0, 50);
					paint.setColor(Color.BLACK);
					paint.setTextSize(25);
					canvas.drawText("고객 돈 : " + bollen.customerMoney, 5, 135, paint);
					canvas.drawText("물건 값 : " + bollen.productMoney, 5, 200, paint);
					canvas.drawText("거스름돈 : " + (bollen.customerMoney-bollen.productMoney), 5, 260, paint);
					canvas.drawText("player : " + playerMoney, 5, 320, paint);
					customerMoney=bollen.customerMoney;
					productMoney=bollen.productMoney;
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
			touch_rect.set(0,600,119,699);
			if(touch_rect.contains(t_x, t_y))
			{
				playerMoney+=1;
			}
			touch_rect.set(120,600,239,699);
			if(touch_rect.contains(t_x,t_y))
			{
				playerMoney+=5;
			}
			touch_rect.set(240,600,359,699);
			if(touch_rect.contains(t_x, t_y))
			{
				playerMoney+=10;
			}
			touch_rect.set(360,600,480,699);
			if(touch_rect.contains(t_x,t_y))
			{
				playerMoney+=50;
			}
			touch_rect.set(0,700,119,800);
			if(touch_rect.contains(t_x, t_y))
			{
				playerMoney+=100;
			}
			touch_rect.set(120,700,239,800);
			if(touch_rect.contains(t_x,t_y))
			{
				playerMoney+=500;
			}
			touch_rect.set(240,700,480,800);
			if(touch_rect.contains(t_x,t_y))
			{
				playerMoney+=1000;
			}
			touch_rect.set(185,547,311,594);
			//확인버튼
			if(touch_rect.contains(t_x,t_y))
			{
				if(playerMoney==(customerMoney-productMoney)) {
					if(limitedTime<=5) AppManager.getInstance().getGameMain().mThread.mUi.goodNum=2;
					if(limitedTime>5) AppManager.getInstance().getGameMain().mThread.mUi.goodNum=1;
				}
				if(playerMoney!=(customerMoney-productMoney)) {
					AppManager.getInstance().getGameMain().mThread.mUi.goodNum=0;
				}
				AppManager.getInstance().getGameMain().mThread.mUi.check=true;
				timer=(int) Math.floor(timer);
				customerCount++; //고객수 
				limitedTime=0;//초기화
			}
			//일시정지
			touch_rect.set(420,0,480,60);
			if(touch_rect.contains(t_x,t_y)){
				AppManager.getInstance().getGameMain().mThread.mUi.pause=true;
			}
		}
		return false;
	}

}
