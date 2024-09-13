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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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
import java.util.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class SettingsActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String lastversion = "";
	private String checkingupdates = "";
	private String signed_out = "";
	private String YES = "";
	private String NO = "";
	private String LogOutTitle = "";
	private String LogOutMessage = "";
	
	private LinearLayout body;
	private CardView top_view;
	private ScrollView slid_main;
	private LinearLayout top;
	private TextView title;
	private LinearLayout slid_body;
	private LinearLayout languages;
	private LinearLayout notification_settings;
	private LinearLayout security_body;
	private LinearLayout privacy_body;
	private LinearLayout admin_panel;
	private LinearLayout linear10;
	private LinearLayout updates_body;
	private LinearLayout help_body;
	private LinearLayout linear12;
	private LinearLayout premium;
	private LinearLayout logout_body;
	private LinearLayout delete_account;
	private ImageView languages_icon;
	private TextView lang_text;
	private ImageView notifications_icon;
	private TextView notification_settings_text;
	private ImageView security_icon;
	private TextView security_text;
	private ImageView privacy_icon;
	private TextView privacy_text;
	private ImageView admin_panel_icon;
	private TextView admin_panel_text;
	private ImageView imageview2;
	private TextView textview2;
	private ImageView updates_icon;
	private TextView updates_text;
	private ImageView about_icon;
	private TextView help_text;
	private ImageView imageview4;
	private TextView textview4;
	private ImageView buy_premium_icon;
	private TextView premium_text;
	private ImageView sign_out_icon;
	private TextView logout_text;
	private ImageView delete_account_icon;
	private TextView delete_account_text;
	
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
	
	private DatabaseReference usdb = _firebase.getReference("update");
	private ChildEventListener _usdb_child_listener;
	private TimerTask timer;
	private TimerTask timer2;
	private SharedPreferences lang;
	private SharedPreferences save;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private TimerTask t;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.settings);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top_view = findViewById(R.id.top_view);
		slid_main = findViewById(R.id.slid_main);
		top = findViewById(R.id.top);
		title = findViewById(R.id.title);
		slid_body = findViewById(R.id.slid_body);
		languages = findViewById(R.id.languages);
		notification_settings = findViewById(R.id.notification_settings);
		security_body = findViewById(R.id.security_body);
		privacy_body = findViewById(R.id.privacy_body);
		admin_panel = findViewById(R.id.admin_panel);
		linear10 = findViewById(R.id.linear10);
		updates_body = findViewById(R.id.updates_body);
		help_body = findViewById(R.id.help_body);
		linear12 = findViewById(R.id.linear12);
		premium = findViewById(R.id.premium);
		logout_body = findViewById(R.id.logout_body);
		delete_account = findViewById(R.id.delete_account);
		languages_icon = findViewById(R.id.languages_icon);
		lang_text = findViewById(R.id.lang_text);
		notifications_icon = findViewById(R.id.notifications_icon);
		notification_settings_text = findViewById(R.id.notification_settings_text);
		security_icon = findViewById(R.id.security_icon);
		security_text = findViewById(R.id.security_text);
		privacy_icon = findViewById(R.id.privacy_icon);
		privacy_text = findViewById(R.id.privacy_text);
		admin_panel_icon = findViewById(R.id.admin_panel_icon);
		admin_panel_text = findViewById(R.id.admin_panel_text);
		imageview2 = findViewById(R.id.imageview2);
		textview2 = findViewById(R.id.textview2);
		updates_icon = findViewById(R.id.updates_icon);
		updates_text = findViewById(R.id.updates_text);
		about_icon = findViewById(R.id.about_icon);
		help_text = findViewById(R.id.help_text);
		imageview4 = findViewById(R.id.imageview4);
		textview4 = findViewById(R.id.textview4);
		buy_premium_icon = findViewById(R.id.buy_premium_icon);
		premium_text = findViewById(R.id.premium_text);
		sign_out_icon = findViewById(R.id.sign_out_icon);
		logout_text = findViewById(R.id.logout_text);
		delete_account_icon = findViewById(R.id.delete_account_icon);
		delete_account_text = findViewById(R.id.delete_account_text);
		auth = FirebaseAuth.getInstance();
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		languages.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), LanguagesActivity.class);
				startActivity(intent);
			}
		});
		
		notification_settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), NotificationActivity.class);
				startActivity(intent);
			}
		});
		
		security_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), SecurityActivity.class);
				startActivity(intent);
			}
		});
		
		privacy_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), PrivacyActivity.class);
				startActivity(intent);
			}
		});
		
		admin_panel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				startActivity(intent);
			}
		});
		
		linear10.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), OtherSettingActivity.class);
				startActivity(intent);
			}
		});
		
		updates_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "You use latest version now ✔✔");
			}
		});
		
		help_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				startActivity(intent);
			}
		});
		
		premium.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
				startActivity(intent);
			}
		});
		
		logout_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_NewCustomDialog(LogOutTitle, LogOutMessage, YES, NO, true);
			}
		});
		
		delete_account.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), AccountdeleteActivity.class);
				startActivity(intent);
			}
		});
		
		_usdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				updates_body.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						SketchwareUtil.showMessage(getApplicationContext(), checkingupdates);
						timer2 = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										if (_childValue.get("new_version").toString().equals("0.1.2")) {
											SketchwareUtil.showMessage(getApplicationContext(), lastversion);
										}
										else {
											
											startActivity(intent);
										}
									}
								});
							}
						};
						_timer.schedule(timer2, (int)(2000));
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		usdb.addChildEventListener(_usdb_child_listener);
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("account_type").toString().equals("admin")) {
						admin_panel.setVisibility(View.VISIBLE);
					}
					else {
						admin_panel.setVisibility(View.GONE);
					}
					if (lang.getString("language", "").equals("")) {
						if (_childValue.get("account_mode").toString().equals("free")) {
							premium_text.setText("Premium Satın Al");
						}
						else {
							if (_childValue.get("account_mode").toString().equals("premium")) {
								premium_text.setText("Premium Üyeliği İptal Et");
							}
						}
					}
					else {
						if (lang.getString("language", "").equals("english")) {
							if (_childValue.get("account_mode").toString().equals("free")) {
								premium_text.setText("Buy Premium");
							}
							else {
								if (_childValue.get("account_mode").toString().equals("premium")) {
									premium_text.setText("Cancel Premium Membership");
								}
							}
						}
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
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
		linear12.setVisibility(View.GONE);
		linear10.setVisibility(View.GONE);
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
	public void _GradientDrawable(final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9E9E9E")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			_view.setBackground(gd);
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
		}
		if (_clickAnim) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
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
		_ImageColor(sign_out_icon, "#F44336");
		_ImageColor(delete_account_icon, "#9E9E9E");
		slid_main.setBackgroundColor(0xFFFFFFFF);
		top.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		_rippleRoundStroke(languages, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(notification_settings, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(security_body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(privacy_body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(admin_panel, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(updates_body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(help_body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(premium, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(logout_body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_rippleRoundStroke(delete_account, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		_ImageColor(languages_icon, "#000000");
		_ImageColor(notifications_icon, "#000000");
		_ImageColor(security_icon, "#000000");
		_ImageColor(privacy_icon, "#000000");
		_ImageColor(admin_panel_icon, "#000000");
		_ImageColor(updates_icon, "#000000");
		_ImageColor(about_icon, "#000000");
		lang_text.setTextColor(0xFF000000);
		notification_settings_text.setTextColor(0xFF000000);
		security_text.setTextColor(0xFF000000);
		privacy_text.setTextColor(0xFF000000);
		admin_panel_text.setTextColor(0xFF000000);
		updates_text.setTextColor(0xFF000000);
		help_text.setTextColor(0xFF000000);
		premium_text.setTextColor(0xFF673AB7);
	}
	
	
	public void _TrLang() {
		title.setText("Ayarlar");
		lang_text.setText("Diller");
		security_text.setText("Güvenlik");
		privacy_text.setText("Gizlilik");
		admin_panel_text.setText("Yönetici Paneli");
		updates_text.setText("Güncellemeler");
		help_text.setText("Hakkında");
		premium_text.setText("Premium Satın Al");
		logout_text.setText("Çıkış Yap");
		delete_account_text.setText("Hesabı Sil");
		notification_settings_text.setText("Bildirim Ayarları");
		YES = "Evet";
		NO = "Hayır";
		lastversion = "En Son Sürümü Kullanıyorsunuz!";
		checkingupdates = "Güncellemeler Kontrol Ediliyor...";
		signed_out = "Çıkış Yapıldı";
		LogOutTitle = "BİLGİ";
		LogOutMessage = "Hesabınızdan çıkmak istediğinizden emin misiniz?";
	}
	
	
	public void _EnLang() {
		title.setText("Settings");
		lang_text.setText("Languages");
		security_text.setText("Security");
		privacy_text.setText("Privacy");
		admin_panel_text.setText("Admin Panel");
		updates_text.setText("Updates");
		help_text.setText("About");
		premium_text.setText("Buy Premium");
		logout_text.setText("Log Out");
		delete_account_text.setText("Delete Account");
		notification_settings_text.setText("Notification Settings");
		YES = "Yes";
		NO = "No";
		lastversion = "You Are Using The Latest Version!";
		checkingupdates = "Checking for Updates...";
		signed_out = "Signed Out";
		LogOutTitle = "INFO";
		LogOutMessage = "Are you sure you want to log out of your account?";
	}
	
	
	public void _NewCustomDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog NewCustomDialog = new AlertDialog.Builder(SettingsActivity.this).create();
		LayoutInflater NewCustomDialogLI = getLayoutInflater();
		View NewCustomDialogCV = (View) NewCustomDialogLI.inflate(R.layout.new_custom_dialog, null);
		NewCustomDialog.setView(NewCustomDialogCV);
		NewCustomDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final TextView dialog_title = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_title);
		final TextView dialog_message = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_message);
		final TextView dialog_no_button = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_no_button);
		final TextView dialog_yes_button = (TextView)
		NewCustomDialogCV.findViewById(R.id.dialog_yes_button);
		_rippleRoundStroke(dialog_yes_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(dialog_no_button, "#FFFFFF", "#CFD8DC", 6, 2, "#CFD8DC");
		dialog_title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		dialog_title.setText(_Title);
		dialog_message.setText(_Message);
		if (_MultiButton) {
			dialog_no_button.setText(_NoButtonText);
			dialog_no_button.setVisibility(View.VISIBLE);
		}
		else {
			dialog_no_button.setVisibility(View.GONE);
		}
		dialog_yes_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				NewCustomDialog.dismiss();
				map = new HashMap<>();
				map.put("online", "false");
				udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				map.clear();
				t = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								FirebaseAuth.getInstance().signOut();
								intent.setClass(getApplicationContext(), ChooselogintypeActivity.class);
								startActivity(intent);
								SketchwareUtil.showMessage(getApplicationContext(), signed_out);
								finish();
							}
						});
					}
				};
				_timer.schedule(t, (int)(1500));
				
			}
		});
		dialog_no_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				NewCustomDialog.dismiss();
				
			}
		});
		dialog_yes_button.setText(_YesButtonText);
		NewCustomDialog.setCancelable(true);
		NewCustomDialog.show();
	}
	
	
	public void _InLang() {
		title.setText("Pengaturan");
		lang_text.setText("Bahasa");
		security_text.setText("Keamanan");
		privacy_text.setText("Privacy");
		admin_panel_text.setText("Panel Admin");
		updates_text.setText("Pembaruan");
		help_text.setText("Tentang");
		premium_text.setText("Beli Premium");
		logout_text.setText("Keluar");
		delete_account_text.setText("Hapus Akun");
		notification_settings_text.setText("Pengaturan Notifikasi");
		YES = "Iya";
		NO = "Tidak";
		lastversion = "Anda Menggunakan Versi Terbaru!";
		checkingupdates = "Memeriksa Pembaruan...";
		signed_out = "Keluar";
		LogOutTitle = "INFO";
		LogOutMessage = "Apakah Anda yakin ingin keluar dari akun Anda?";
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