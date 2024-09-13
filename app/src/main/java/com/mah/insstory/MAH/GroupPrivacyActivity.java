package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
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
import android.widget.CompoundButton;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class GroupPrivacyActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout body;
	private CardView top;
	private LinearLayout only_admin_layout;
	private LinearLayout top_;
	private ImageView back;
	private TextView title;
	private LinearLayout only_admin_body;
	private TextView only_admin_subtext;
	private TextView only_admin_title;
	private CheckBox only_admin_checkbox;
	
	private SharedPreferences lang;
	private SharedPreferences save;
	private DatabaseReference gdb = _firebase.getReference("groups/group");
	private ChildEventListener _gdb_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_privacy);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top = findViewById(R.id.top);
		only_admin_layout = findViewById(R.id.only_admin_layout);
		top_ = findViewById(R.id.top_);
		back = findViewById(R.id.back);
		title = findViewById(R.id.title);
		only_admin_body = findViewById(R.id.only_admin_body);
		only_admin_subtext = findViewById(R.id.only_admin_subtext);
		only_admin_title = findViewById(R.id.only_admin_title);
		only_admin_checkbox = findViewById(R.id.only_admin_checkbox);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		only_admin_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				only_admin_checkbox.performClick();
			}
		});
		
		only_admin_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					map = new HashMap<>();
					map.put("privacy_only_admin", "true");
					gdb.child(getIntent().getStringExtra("group_key")).updateChildren(map);
					map.clear();
				}
				else {
					map = new HashMap<>();
					map.put("privacy_only_admin", "general");
					gdb.child(getIntent().getStringExtra("group_key")).updateChildren(map);
					map.clear();
				}
			}
		});
		
		_gdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("group_key"))) {
					if (_childValue.get("privacy_only_admin").toString().equals("true")) {
						only_admin_checkbox.setChecked(true);
					}
					else {
						only_admin_checkbox.setChecked(false);
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("group_key"))) {
					if (_childValue.get("privacy_only_admin").toString().equals("true")) {
						only_admin_checkbox.setChecked(true);
					}
					else {
						only_admin_checkbox.setChecked(false);
					}
				}
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		gdb.addChildEventListener(_gdb_child_listener);
	}
	
	private void initializeLogic() {
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
		_Language();
		_changeActivityFont("youtubesansregular");
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansdarkbold.otf"), 0);
	}
	public void _ThemeCustom() {
		if (save.getString("theme", "").equals("light")) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
			top_.setBackgroundColor(0xFFFFFFFF);
			body.setBackgroundColor(0xFFEEEEEE);
			_ImageColor(back, "#000000");
			title.setTextColor(0xFF000000);
		}
		else {
			if (save.getString("theme", "").equals("dark")) {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
					Window w =GroupPrivacyActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
				}
				top_.setBackgroundColor(0xFF212121);
				body.setBackgroundColor(0xFF000000);
				_ImageColor(back, "#FFFFFF");
				title.setTextColor(0xFFFFFFFF);
			}
		}
	}
	
	
	public void _Language() {
		if (lang.getString("language", "").equals("")) {
			_TrLang();
		}
		if (lang.getString("language", "").equals("english")) {
			_EnLang();
		}
	}
	
	
	public void _TrLang() {
		title.setText("Gizlilik");
		only_admin_title.setText("Yalnızca Yöneticiler");
		only_admin_subtext.setText("Bu özellik aktifken sadece yöneticiler ve yardımcı yöneticiler gruba mesaj gönderebilir.");
	}
	
	
	public void _EnLang() {
		title.setText("Privacy");
		only_admin_title.setText("Only Admins");
		only_admin_subtext.setText("When this feature is active, only administrators and co-admins can send messages to the group.");
	}
	
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".otf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
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