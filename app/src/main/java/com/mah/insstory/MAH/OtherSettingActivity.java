package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.content.*;
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
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
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

public class OtherSettingActivity extends AppCompatActivity {
	
	private LinearLayout body;
	private ScrollView scroll;
	private LinearLayout scroll_in_body;
	private LinearLayout top_bar;
	private TextView stories_settings_title;
	private LinearLayout stories_settings_body;
	private TextView friendship_settings;
	private LinearLayout friendship_settings_body;
	private TextView discovery_settings_title;
	private LinearLayout discovery_settings_body;
	private TextView chat_settings_title;
	private LinearLayout chat_settings_body;
	private ImageView back;
	private TextView title;
	private TextView story_features_title;
	private LinearLayout story_library_body;
	private LinearLayout downloadable_stories_body;
	private Switch story_library_switch;
	private ImageView imageview1;
	private Switch downloadable_stories_switch;
	private ImageView imageview2;
	private LinearLayout block_new_friend_request;
	private LinearLayout friendship_gifts;
	private Switch block_new_friend_request_switch;
	private Switch friendship_gifts_switch;
	private ImageView imageview3;
	private LinearLayout swear_filter;
	private Switch swear_filter_switch;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.other_setting);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		scroll = findViewById(R.id.scroll);
		scroll_in_body = findViewById(R.id.scroll_in_body);
		top_bar = findViewById(R.id.top_bar);
		stories_settings_title = findViewById(R.id.stories_settings_title);
		stories_settings_body = findViewById(R.id.stories_settings_body);
		friendship_settings = findViewById(R.id.friendship_settings);
		friendship_settings_body = findViewById(R.id.friendship_settings_body);
		discovery_settings_title = findViewById(R.id.discovery_settings_title);
		discovery_settings_body = findViewById(R.id.discovery_settings_body);
		chat_settings_title = findViewById(R.id.chat_settings_title);
		chat_settings_body = findViewById(R.id.chat_settings_body);
		back = findViewById(R.id.back);
		title = findViewById(R.id.title);
		story_features_title = findViewById(R.id.story_features_title);
		story_library_body = findViewById(R.id.story_library_body);
		downloadable_stories_body = findViewById(R.id.downloadable_stories_body);
		story_library_switch = findViewById(R.id.story_library_switch);
		imageview1 = findViewById(R.id.imageview1);
		downloadable_stories_switch = findViewById(R.id.downloadable_stories_switch);
		imageview2 = findViewById(R.id.imageview2);
		block_new_friend_request = findViewById(R.id.block_new_friend_request);
		friendship_gifts = findViewById(R.id.friendship_gifts);
		block_new_friend_request_switch = findViewById(R.id.block_new_friend_request_switch);
		friendship_gifts_switch = findViewById(R.id.friendship_gifts_switch);
		imageview3 = findViewById(R.id.imageview3);
		swear_filter = findViewById(R.id.swear_filter);
		swear_filter_switch = findViewById(R.id.swear_filter_switch);
	}
	
	private void initializeLogic() {
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