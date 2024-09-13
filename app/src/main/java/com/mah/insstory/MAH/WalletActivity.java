package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.Intent;
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
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
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
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class WalletActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private LinearLayout body;
	private ViewPager pages;
	private LinearLayout top_spc;
	private LinearLayout bottom;
	private LinearLayout wallet;
	private LinearLayout wallet_saved_products;
	private LinearLayout wallet_history;
	private LinearLayout wallet_settings;
	private ImageView wallet_icon;
	private ImageView wallet_saved_products_icon;
	private ImageView wallet_history_icon;
	private ImageView wallet_settings_icon;
	
	private Intent intent = new Intent();
	private FragFragmentAdapter frag;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
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
	
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.wallet);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		pages = findViewById(R.id.pages);
		top_spc = findViewById(R.id.top_spc);
		bottom = findViewById(R.id.bottom);
		wallet = findViewById(R.id.wallet);
		wallet_saved_products = findViewById(R.id.wallet_saved_products);
		wallet_history = findViewById(R.id.wallet_history);
		wallet_settings = findViewById(R.id.wallet_settings);
		wallet_icon = findViewById(R.id.wallet_icon);
		wallet_saved_products_icon = findViewById(R.id.wallet_saved_products_icon);
		wallet_history_icon = findViewById(R.id.wallet_history_icon);
		wallet_settings_icon = findViewById(R.id.wallet_settings_icon);
		frag = new FragFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		auth = FirebaseAuth.getInstance();
		
		pages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				if (_position == 0) {
					_ImageColor(wallet_icon, "#651FFF");
					_ImageColor(wallet_saved_products_icon, "#CFD8DC");
					_ImageColor(wallet_history_icon, "#CFD8DC");
					_ImageColor(wallet_settings_icon, "#CFD8DC");
				}
				if (_position == 1) {
					_ImageColor(wallet_icon, "#CFD8DC");
					_ImageColor(wallet_saved_products_icon, "#651FFF");
					_ImageColor(wallet_history_icon, "#CFD8DC");
					_ImageColor(wallet_settings_icon, "#CFD8DC");
				}
				if (_position == 2) {
					_ImageColor(wallet_icon, "#CFD8DC");
					_ImageColor(wallet_saved_products_icon, "#CFD8DC");
					_ImageColor(wallet_history_icon, "#651FFF");
					_ImageColor(wallet_settings_icon, "#CFD8DC");
				}
				if (_position == 3) {
					_ImageColor(wallet_icon, "#CFD8DC");
					_ImageColor(wallet_saved_products_icon, "#CFD8DC");
					_ImageColor(wallet_history_icon, "#CFD8DC");
					_ImageColor(wallet_settings_icon, "#651FFF");
				}
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		wallet.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pages.setCurrentItem((int)0);
			}
		});
		
		wallet_saved_products.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pages.setCurrentItem((int)1);
			}
		});
		
		wallet_history.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pages.setCurrentItem((int)2);
			}
		});
		
		wallet_settings.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				pages.setCurrentItem((int)3);
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("blocked").toString().equals("true")) {
						_BannedDialog("INFO", "Dear User, Your account has been permanently blocked for not following our Community guidelines!", "Close App", "Create Request", true);
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
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		frag.setTabCount(4);
		pages.setAdapter(frag);
		_ImageColor(wallet_icon, "#651FFF");
		_ImageColor(wallet_saved_products_icon, "#CFD8DC");
		_ImageColor(wallet_history_icon, "#CFD8DC");
		_ImageColor(wallet_settings_icon, "#CFD8DC");
	}
	
	public class FragFragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public FragFragmentAdapter(Context context, FragmentManager manager) {
			super(manager);
			this.context = context;
		}
		
		public void setTabCount(int tabCount) {
			this.tabCount = tabCount;
		}
		
		@Override
		public int getCount() {
			return tabCount;
		}
		
		@Override
		public CharSequence getPageTitle(int _position) {
			
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new WalletHomeFragmentActivity();
			}
			if (_position == 1) {
				return new WalletSavedProductsFragmentActivity();
			}
			if (_position == 2) {
				return new WalletHistoryFragmentActivity();
			}
			if (_position == 3) {
				return new WalletSettingsFragmentActivity();
			}
			return null;
		}
	}
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _BannedDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog BannedDialog = new AlertDialog.Builder(this).create();
		LayoutInflater BannedDialogLI = getLayoutInflater();
		View BannedDialogCV = (View) BannedDialogLI.inflate(R.layout.new_custom_dialog, null);
		BannedDialog.setView(BannedDialogCV);
		BannedDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final TextView dialog_title = (TextView)
		BannedDialogCV.findViewById(R.id.dialog_title);
		final TextView dialog_message = (TextView)
		BannedDialogCV.findViewById(R.id.dialog_message);
		final TextView dialog_no_button = (TextView)
		BannedDialogCV.findViewById(R.id.dialog_no_button);
		final TextView dialog_yes_button = (TextView)
		BannedDialogCV.findViewById(R.id.dialog_yes_button);
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
				finishAffinity();
				
			}
		});
		dialog_no_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), CreateRequestActivity.class);
				startActivity(intent);
				
			}
		});
		dialog_yes_button.setText(_YesButtonText);
		BannedDialog.setCancelable(false);
		BannedDialog.show();
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