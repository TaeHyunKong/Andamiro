package PlayGame;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class CustomDialog extends Dialog {
	Context mContext = null;
	static EditText edit = null;
	static InputMethodManager ime = null;

	public CustomDialog(Context context,int theme) {
		super(context,theme);
		mContext=context;
		  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.select_dialog_item, null);
	        edit =  ((EditText) layout.findViewById(R.id.message));
	        
	        ime = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
//	        ime.showSoftInput(edit, 0);
	        ime.showSoftInputFromInputMethod(edit.getWindowToken(), InputMethodManager.SHOW_FORCED);
	                    this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

	}

}
