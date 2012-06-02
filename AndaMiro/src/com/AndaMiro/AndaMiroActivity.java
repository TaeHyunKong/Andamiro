package com.AndaMiro;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Debug;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

public class AndaMiroActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //풀스크린
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //어플 실행동안 화면 안꺼지게
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); 
        
        GameMain _gamemain = new GameMain(this);
        _gamemain.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        														ViewGroup.LayoutParams.WRAP_CONTENT,
        														0.0f));
        setContentView(_gamemain);
    }
    private static double fps_d;
    private static int fps_i=0;
    private static int fps_l=0;
    
    public static void CheckState(Canvas cvs, Paint p){
    	double max = Runtime.getRuntime().maxMemory() / (1024*1024);
    	double allocate = Debug.getNativeHeapAllocatedSize()/(1024 * 1024);
    	
    	fps_i++;
    	
    	if(System.currentTimeMillis() - fps_d > 1000)
    	{
    		fps_l = fps_i;
    		fps_d = System.currentTimeMillis();
    		fps_i = 0;
    	}
    	String t_max = "최대 메모리 : " + max;
    	String t_free = "사용 메모리 : " + allocate;
    	String t_fps = "FPS : " + fps_l;
    	
    	p.setTextSize(30);
    	p.setColor(Color.RED);
    	
    	cvs.drawText(t_max, 10, 100, p);
    	cvs.drawText(t_free, 10, 150, p);
    	cvs.drawText(t_fps, 10, 200, p);
    }
    //백버튼 조절
    Boolean is_finish = false;
    long Time; int temp = 0;
    public void onBackPressed(){
    	if(temp==1) {
    		if( Time + 2000 < java.lang.System.currentTimeMillis() ) temp=0;
    		else android.os.Process.killProcess(android.os.Process.myPid());
    	}
		if(is_finish)							
			android.os.Process.killProcess(android.os.Process.myPid());
		else if(temp==0){
			Time = java.lang.System.currentTimeMillis();
			Toast.makeText(getBaseContext(),"자네 정말 종료할껀가? 그럴꺼면 한번더 눌러달라고.", 0).show();
			temp++;
		}
    }
}