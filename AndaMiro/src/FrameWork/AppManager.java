package FrameWork;

import android.content.Context;

import com.AndaMiro.GameMain;

public class AppManager {
	private GameMain mGameMain;
	private Context mContext;
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void setGameMain(GameMain _gamemain){
		mGameMain = _gamemain;
	}
	public GameMain getGameMain(){
		return mGameMain;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void setContext(Context _context){
		mContext = _context;
	}
	public Context getContext(){
		return mContext;
	}
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	private static AppManager s_instance;
	public static AppManager getInstance(){
		if(s_instance == null){
			s_instance = new AppManager();
		}
		return s_instance;
	}
}
