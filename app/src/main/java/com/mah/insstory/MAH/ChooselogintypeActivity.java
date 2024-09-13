package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
import com.airbnb.lottie.*;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class ChooselogintypeActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout body;
	private LinearLayout bg;
	private LinearLayout main;
	private LinearLayout bottom;
	private LottieAnimationView lottie1;
	private Button login_button;
	private Button register_button;
	
	private Intent intent = new Intent();
	private SharedPreferences lang;
	private SharedPreferences save;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.chooselogintype);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		bg = findViewById(R.id.bg);
		main = findViewById(R.id.main);
		bottom = findViewById(R.id.bottom);
		lottie1 = findViewById(R.id.lottie1);
		login_button = findViewById(R.id.login_button);
		register_button = findViewById(R.id.register_button);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		login_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
			}
		});
		
		register_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), RegisterActivity.class);
				startActivity(intent);
			}
		});
	}
	
	private void initializeLogic() {
	}
	
	@Override
	public void onBackPressed() {
		finishAffinity();
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_TransperentStatusBar(true, "");
		_Language();
		body.setBackgroundColor(0xFFFFFFFF);
		login_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_gradient_button));
		register_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_gradient_button));
	}
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor(_pressed)}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _Language() {
		if (lang.getString("language", "").equals("")) {
			_TrLang();
		}
		if (lang.getString("language", "").equals("english")) {
			_EnLang();
		}
		if (lang.getString("language", "").equals("indonesia")) {
			_InLang();
		}
	}
	
	
	public void _ThemeCustom() {
		
	}
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _TrLang() {
		
		register_button.setText("HESAP OLUŞTUR");
		login_button.setText("GİRİŞ YAP");
	}
	
	
	public void _EnLang() {
		
		register_button.setText("CREATE ACCOUNT");
		login_button.setText("LOGIN");
	}
	
	
	public void _TransperentStatusBar(final boolean _show, final String _color) {
		if (_show) {
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { 
						Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
						w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
						w.setStatusBarColor(0xFF008375); w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS); }
		}
		else {
				if (_color.equals("")) {
						SketchwareUtil.showMessage(getApplicationContext(), "No Color Found For This Action. Check Your MoreBlock Details Property. I think You Not Added Any Color In MoreBlock");
				}
				else {
						Window w = this.getWindow();w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); w.setStatusBarColor(Color.parseColor(_color));
				}
		}
		/*

 if you are using this note this important thing that don't add Toolbar Otherwise its not work properly This MoreBlock Is Madded By HarshitSingh_in

How To Use This

If you are using (TRUE) Then No Need To Add Any Color But If Your Are Using (FALSE) Then Must Add Any Color Code Then Use Otherwise You Face This Error :- "No Color Found For This Action. Check Your MoreBlock Details Property. I think You Not Added Any Color In MoreBlock"

GoodDay
Happy Coding

HARSHIT SINGH

*/
	}
	
	
	public void _InLang() {
		
		register_button.setText("BUAT AKUN");
		login_button.setText("MASUK");
	}
	
	
	public void _AdvancedView(final View _v, final String _colour1, final String _colour2, final String _borderColour, final double _cornerRadius, final double _borderThickness, final String _eColour, final double _elevation) {
		int[] colors = {Color.parseColor(_colour1),Color.parseColor(_colour2)};
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.BR_TL, colors); //Made by XenonDry
		gd.setCornerRadius((float)_cornerRadius); 
		gd.setStroke((int)_borderThickness, (Color.parseColor(_borderColour)));
		_v.setBackground(gd);
		//Made by XenonDry
		_v.setElevation((float)_elevation);
		
		_v.setOutlineAmbientShadowColor(Color.parseColor(_eColour));
		_v.setOutlineSpotShadowColor(Color.parseColor(_eColour));
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}