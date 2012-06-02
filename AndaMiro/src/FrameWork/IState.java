package FrameWork;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface IState {
	public void Init(); //�ʱ�ȭ
	public void Update(); //����
	public void Render(Canvas canvas); //�׸���
	public boolean onTouchEvent(MotionEvent event); //��ġ
}
