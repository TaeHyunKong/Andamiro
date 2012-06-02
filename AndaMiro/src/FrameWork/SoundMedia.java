package FrameWork;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class SoundMedia {
	MediaPlayer mBack = null;
	MediaPlayer mEffect = null;
	
	public void soundBack(Context ctx, int name, boolean loop)
	{
		mBack= MediaPlayer.create(ctx, name);
		
		mBack.start();
		mBack.setLooping(loop);
		
	}
	
	public void soundEffect(Context ctx, int name)
	{
		mEffect = MediaPlayer.create(ctx, name);
		
		mEffect.seekTo(0);
		mEffect.start();
	}
	
	SoundPool mSoundPool;
	HashMap<Integer, Integer> mSoundPoolMap;
	AudioManager mAudioManager;
	int Volume=0;
	
	public void setEffects(Context ctx, int num)
	{
		mSoundPool = new SoundPool(num, AudioManager.STREAM_MUSIC, 0);
		mSoundPoolMap = new HashMap<Integer,Integer>();
		mAudioManager = (AudioManager)ctx.getSystemService(Context.AUDIO_SERVICE);
		Volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}
	
	public void addSound(Context ctx, int ID, int name)
	{
		mSoundPoolMap.put(ID, mSoundPool.load(ctx, name,1));
	}
	
	public int playSound(int ID, int count)
	{
		int Volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		return mSoundPool.play(mSoundPoolMap.get(ID), Volume, Volume, 1, (count-1),1f);
	}
	
	public void stopSound(int ID)
	{
		mSoundPool.stop(ID);
	}
	

}
