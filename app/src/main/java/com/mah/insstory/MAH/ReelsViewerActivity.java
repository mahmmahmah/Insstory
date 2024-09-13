package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
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

public class ReelsViewerActivity extends AppCompatActivity {
	
	private String reels_path = "";
	
	private RelativeLayout body;
	private LinearLayout mdt;
	private LinearLayout relative_in_cbody;
	private VideoView reels_preview;
	private LinearLayout top;
	private LinearLayout medium;
	private LinearLayout bottom;
	private ImageView back;
	private TextView preview_title;
	private TextView publish;
	
	private Intent intent = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.reels_viewer);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		mdt = findViewById(R.id.mdt);
		relative_in_cbody = findViewById(R.id.relative_in_cbody);
		reels_preview = findViewById(R.id.reels_preview);
		MediaController reels_preview_controller = new MediaController(this);
		reels_preview.setMediaController(reels_preview_controller);
		top = findViewById(R.id.top);
		medium = findViewById(R.id.medium);
		bottom = findViewById(R.id.bottom);
		back = findViewById(R.id.back);
		preview_title = findViewById(R.id.preview_title);
		publish = findViewById(R.id.publish);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		publish.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), CreateReelsPostActivity.class);
				intent.putExtra("path", getIntent().getStringExtra("path"));
				startActivity(intent);
				finish();
			}
		});
	}
	
	private void initializeLogic() {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w =ReelsViewerActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF000000);
		}
		getWindow().setNavigationBarColor(0xFF000000);
		View view = getWindow().getDecorView();
		view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
		
		reels_preview.setVideoURI(Uri.parse(getIntent().getStringExtra("path")));
		reels_preview.start();
		_ImageColor(back, "#FFFFFF");
		_rippleRoundStroke(publish, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(top, "#7B000000", "#7B000000", 0, 0, "#FFFFFF");
		_rippleRoundStroke(bottom, "#7B000000", "#7B000000", 0, 0, "#FFFFFF");
		medium.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (reels_preview.isPlaying()) {
					reels_preview.pause();
				}
				else {
					reels_preview.start();
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _rippleRoundStroke(final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		
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