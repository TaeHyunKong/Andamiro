package FrameWork;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IState {
	public void Init(); //초기화
	public void Update(); //갱신
	public void Render(Canvas canvas); //그리기
	public boolean onTouchEvent(MotionEvent event); //터치
}
