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
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class PrivacyActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> m1 = new HashMap<>();
	
	private LinearLayout body;
	private CardView top;
	private LinearLayout hide_status_layout;
	private LinearLayout private_account;
	private LinearLayout top_;
	private TextView title;
	private LinearLayout hide_status;
	private LinearLayout linear3;
	private TextView hide_status_subtext;
	private TextView hide_status_title;
	private CheckBox hide_status_check;
	private LinearLayout linear5;
	private TextView private_account_subtext;
	private TextView private_account_title;
	private CheckBox private_account_check;
	
	private Intent intent = new Intent();
	private FirebaseAuth auth;
	private OnCompleteListener<AuthResult> _auth_create_user_listener;
	private OnCompleteListener<AuthResult> _auth_sign_in_listener;
	private OnCompleteListener<Void> _auth_reset_password_listener;
	private OnCompleteListener<Void> auth_updateEmailListener;
	private OnCompleteListener<Void> auth_updatePasswordListener;
	private OnCompleteListener<Void> auth_emailVerificationSentListener;
	private OnCompleteListener<Void> auth_deleteUserListener;
	private OnCompleteListener<Void> auth_updateProfileListener;
	private OnCompleteListener<AuthResult> auth_phoneAuthListener;
	private OnCompleteListener<AuthResult> auth_googleSignInListener;
	
	private SharedPreferences lang;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private SharedPreferences save;
	private Calendar cc = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.privacy);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top = findViewById(R.id.top);
		hide_status_layout = findViewById(R.id.hide_status_layout);
		private_account = findViewById(R.id.private_account);
		top_ = findViewById(R.id.top_);
		title = findViewById(R.id.title);
		hide_status = findViewById(R.id.hide_status);
		linear3 = findViewById(R.id.linear3);
		hide_status_subtext = findViewById(R.id.hide_status_subtext);
		hide_status_title = findViewById(R.id.hide_status_title);
		hide_status_check = findViewById(R.id.hide_status_check);
		linear5 = findViewById(R.id.linear5);
		private_account_subtext = findViewById(R.id.private_account_subtext);
		private_account_title = findViewById(R.id.private_account_title);
		private_account_check = findViewById(R.id.private_account_check);
		auth = FirebaseAuth.getInstance();
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		private_account.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				private_account_check.performClick();
			}
		});
		
		hide_status.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				hide_status_check.performClick();
			}
		});
		
		hide_status_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					m1 = new HashMap<>();
					m1.put("hide_status", "true");
					udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m1);
					m1.clear();
					save.edit().putString("hidestatus", "true").commit();
				}
				else {
					m1 = new HashMap<>();
					m1.put("hide_status", "false");
					udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m1);
					m1.clear();
					save.edit().putString("hidestatus", "false").commit();
				}
			}
		});
		
		private_account_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
				final boolean _isChecked = _param2;
				if (_isChecked) {
					m1 = new HashMap<>();
					m1.put("private_account", "true");
					udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m1);
					m1.clear();
				}
				else {
					m1 = new HashMap<>();
					m1.put("private_account", "false");
					udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(m1);
					m1.clear();
				}
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("hide_status").toString().equals("false")) {
						hide_status_check.setChecked(false);
					}
					else {
						if (_childValue.get("hide_status").toString().equals("true")) {
							hide_status_check.setChecked(true);
						}
					}
					if (_childValue.get("private_account").toString().equals("false")) {
						private_account_check.setChecked(false);
					}
					else {
						if (_childValue.get("private_account").toString().equals("true")) {
							private_account_check.setChecked(true);
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("hide_status").toString().equals("false")) {
						hide_status_check.setChecked(false);
					}
					else {
						if (_childValue.get("hide_status").toString().equals("true")) {
							hide_status_check.setChecked(true);
						}
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
		udb.addChildEventListener(_udb_child_listener);
		
		auth_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		auth_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		auth_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_auth_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_auth_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
			}
		};
	}
	
	private void initializeLogic() {
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
	}
	
	@Override
	public void onBackPressed() {
		finish();
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
		top_.setBackgroundColor(0xFFFFFFFF);
		body.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		_rippleRoundStroke(hide_status, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
	}
	
	
	public void _TrLang() {
		title.setText("Gizlilik");
		hide_status_title.setText("Durumu Gizle");
		hide_status_subtext.setText("Bunu etkinleştirdiğinizde, son görülme tarihiniz yerine \"çevrimdışı\" ibaresi görünecektir.");
	}
	
	
	public void _EnLang() {
		title.setText("Privacy");
		hide_status_title.setText("Hide Status");
		hide_status_subtext.setText("When you enable this, \"offline\" will appear instead of your last seen date.");
	}
	
	
	public void _InLang() {
		title.setText("Privasi");
		hide_status_title.setText("Sembunyikan Status");
		hide_status_subtext.setText("Saat Anda mengaktifkan ini, \"offline\" akan muncul alih-alih tanggal terakhir Anda terlihat.");
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