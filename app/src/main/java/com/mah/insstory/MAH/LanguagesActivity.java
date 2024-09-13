package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
import com.google.firebase.FirebaseApp;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class LanguagesActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout body;
	private CardView top;
	private LinearLayout english;
	private LinearLayout linear1;
	private LinearLayout turkish;
	private LinearLayout top_;
	private ImageView cancel;
	private TextView title;
	private ImageView save;
	private TextView english_title;
	private CheckBox english_check;
	private TextView textview1;
	private CheckBox Indo_check;
	private TextView turkish_title;
	private CheckBox turkish_check;
	
	private SharedPreferences lang;
	private Intent intent = new Intent();
	private SharedPreferences save1;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.languages);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top = findViewById(R.id.top);
		english = findViewById(R.id.english);
		linear1 = findViewById(R.id.linear1);
		turkish = findViewById(R.id.turkish);
		top_ = findViewById(R.id.top_);
		cancel = findViewById(R.id.cancel);
		title = findViewById(R.id.title);
		save = findViewById(R.id.save);
		english_title = findViewById(R.id.english_title);
		english_check = findViewById(R.id.english_check);
		textview1 = findViewById(R.id.textview1);
		Indo_check = findViewById(R.id.Indo_check);
		turkish_title = findViewById(R.id.turkish_title);
		turkish_check = findViewById(R.id.turkish_check);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save1 = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		english.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				english_check.performClick();
			}
		});
		
		turkish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				turkish_check.performClick();
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (turkish_check.isChecked()) {
					lang.edit().putString("language", "").commit();
				}
				else {
					if (english_check.isChecked()) {
						lang.edit().putString("language", "english").commit();
					}
					if (Indo_check.isChecked()) {
						lang.edit().putString("language", "indonesia").commit();
					}
				}
				finish();
			}
		});
		
		english_check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				english_check.setChecked(true);
				turkish_check.setChecked(false);
				Indo_check.setChecked(false);
			}
		});
		
		Indo_check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				english_check.setChecked(false);
				turkish_check.setChecked(false);
				Indo_check.setChecked(true);
			}
		});
		
		turkish_check.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				english_check.setChecked(false);
				turkish_check.setChecked(true);
				Indo_check.setChecked(false);
			}
		});
	}
	
	private void initializeLogic() {
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
		_Language();
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
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
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
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		top.setBackgroundColor(0xFFFFFFFF);
		body.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		english_title.setTextColor(0xFF000000);
		turkish_title.setTextColor(0xFF000000);
		_rippleRoundStroke(cancel, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
		_rippleRoundStroke(save, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
		_rippleRoundStroke(english, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
		_rippleRoundStroke(turkish, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
		_ImageColor(cancel, "#000000");
		_ImageColor(save, "#000000");
	}
	
	
	public void _TrLang() {
		turkish_check.setChecked(true);
		english_check.setChecked(false);
		Indo_check.setChecked(false);
		
		
		title.setText("Diller");
	}
	
	
	public void _EnLang() {
		turkish_check.setChecked(false);
		english_check.setChecked(true);
		Indo_check.setChecked(false);
		
		
		title.setText("Languages");
	}
	
	
	public void _InLang() {
		turkish_check.setChecked(false);
		english_check.setChecked(false);
		Indo_check.setChecked(true);
		
		
		title.setText("Bahasa");
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