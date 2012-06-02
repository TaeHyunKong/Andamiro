package Object;

import java.util.Random;

import android.graphics.Bitmap;

public class Boolen {
	public Bitmap bitmap;
	Random random = new Random();
	public int customerMoney=0;
	public int productMoney=0;
	
	public Boolen(Bitmap bitmap,int num){
		this.bitmap=bitmap;
		customerMoney=num;
		//num=customerMoney-100;
		productMoney=random.nextInt(num);
	
	}

}
