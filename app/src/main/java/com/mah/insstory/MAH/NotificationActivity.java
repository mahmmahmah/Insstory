package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.Typeface;
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
import java.text.*;
import java.util.*;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class NotificationActivity extends AppCompatActivity {
	
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout body;
	private CardView top;
	private LinearLayout body_2;
	private LinearLayout top_;
	private ImageView cancel;
	private TextView title;
	private ImageView save;
	private LinearLayout background_notifications;
	private LinearLayout messaging_lay;
	private LinearLayout system_notification;
	private LinearLayout request;
	private LinearLayout more;
	private LinearLayout linear8;
	private TextView sub1;
	private TextView title1;
	private CheckBox messaging_check;
	private LinearLayout linear9;
	private TextView sub2;
	private TextView title2;
	private CheckBox system_check;
	private LinearLayout linear10;
	private TextView sub3;
	private TextView title3;
	private CheckBox request_check;
	private LinearLayout linear11;
	private TextView sub4;
	private TextView title4;
	private CheckBox more_check;
	
	private SharedPreferences save1;
	private SharedPreferences lang;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.notification);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top = findViewById(R.id.top);
		body_2 = findViewById(R.id.body_2);
		top_ = findViewById(R.id.top_);
		cancel = findViewById(R.id.cancel);
		title = findViewById(R.id.title);
		save = findViewById(R.id.save);
		background_notifications = findViewById(R.id.background_notifications);
		messaging_lay = findViewById(R.id.messaging_lay);
		system_notification = findViewById(R.id.system_notification);
		request = findViewById(R.id.request);
		more = findViewById(R.id.more);
		linear8 = findViewById(R.id.linear8);
		sub1 = findViewById(R.id.sub1);
		title1 = findViewById(R.id.title1);
		messaging_check = findViewById(R.id.messaging_check);
		linear9 = findViewById(R.id.linear9);
		sub2 = findViewById(R.id.sub2);
		title2 = findViewById(R.id.title2);
		system_check = findViewById(R.id.system_check);
		linear10 = findViewById(R.id.linear10);
		sub3 = findViewById(R.id.sub3);
		title3 = findViewById(R.id.title3);
		request_check = findViewById(R.id.request_check);
		linear11 = findViewById(R.id.linear11);
		sub4 = findViewById(R.id.sub4);
		title4 = findViewById(R.id.title4);
		more_check = findViewById(R.id.more_check);
		save1 = getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		messaging_lay.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (messaging_check.isChecked()) {
					messaging_check.setChecked(false);
				}
				else {
					messaging_check.setChecked(true);
				}
			}
		});
		
		system_notification.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (system_check.isChecked()) {
					system_check.setChecked(false);
				}
				else {
					system_check.setChecked(true);
				}
			}
		});
		
		request.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (request_check.isChecked()) {
					request_check.setChecked(false);
				}
				else {
					request_check.setChecked(true);
				}
			}
		});
		
		more.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (more_check.isChecked()) {
					more_check.setChecked(false);
				}
				else {
					more_check.setChecked(true);
				}
			}
		});
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
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		_Language();
		_ThemeCustom();
		if (save1.getString("messagenotif", "").equals("true")) {
			messaging_check.setChecked(true);
		}
		else {
			messaging_check.setChecked(false);
		}
		if (save1.getString("systemnotif", "").equals("true")) {
			system_check.setChecked(true);
		}
		else {
			system_check.setChecked(false);
		}
		if (save1.getString("requestnotif", "").equals("true")) {
			request_check.setChecked(true);
		}
		else {
			request_check.setChecked(false);
		}
		if (save1.getString("morenotif", "").equals("true")) {
			more_check.setChecked(true);
		}
		else {
			more_check.setChecked(false);
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
	
	
	public void _TrLang() {
		title.setText("Bildirim Ayarları");
		title1.setText("Mesaj Bildirimleri");
		title2.setText("Sistem Bildirimleri");
		title3.setText("İstek Bildirimleri");
		title4.setText("Diğer Bildirimler");
		sub1.setText("Herhangi bir kullanıcı sana bir mesaj gönderdiğinde gelen bildirimler.");
		sub2.setText("Uygulama geliştiricileri veya moderatörler tarafından gönderilen bildirimler.");
		sub3.setText("Yeni arkadaşlık istekleri varsa gelen bildirimler.");
		sub4.setText("Yeni beğeniler,hikayeler, çıkartmalar,yorumlar vs. bulunduğunda gelen bildirimler.");
	}
	
	
	public void _EnLang() {
		title.setText("Notification Settings");
		title1.setText("Message Notifications");
		title2.setText("System Notifications");
		title3.setText("Request Notifications");
		title4.setText("Other Notifications");
		sub1.setText("Notifications when any user sends you a message.");
		sub2.setText("Notifications by app developers or moderators.");
		sub3.setText("Incoming notifications if there are new friend requests.");
		sub4.setText("New likes, stories, stickers, comments etc. notifications when found.");
	}
	
	
	public void _ThemeCustom() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		top_.setBackgroundColor(0xFFFFFFFF);
		body.setBackgroundColor(0xFFFFFFFF);
		body_2.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		title1.setTextColor(0xFF000000);
		title2.setTextColor(0xFF000000);
		title3.setTextColor(0xFF000000);
		title4.setTextColor(0xFF000000);
		_rippleRoundStroke(cancel, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
		_rippleRoundStroke(save, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
		_rippleRoundStroke(messaging_lay, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
		_rippleRoundStroke(system_notification, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
		_rippleRoundStroke(request, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
		_rippleRoundStroke(more, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
	}
	
	
	public void _InLang() {
		title.setText("Pengaturan Notifikasi");
		title1.setText("Notifikasi Pesan");
		title2.setText("Notifikasi System");
		title3.setText("Notifikasi Permintaan");
		title4.setText("Notifikasi Lainnya");
		sub1.setText("Notifikasi ketika ada pengguna yang mengirimi Anda pesan.");
		sub2.setText("Notifikasi oleh Developer atau moderator aplikasi.");
		sub3.setText("Notifikasi masuk jika ada permintaan pertemanan baru.");
		sub4.setText("Suka baru, cerita, stiker, komentar, dll. pemberitahuan saat ditemukan.");
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