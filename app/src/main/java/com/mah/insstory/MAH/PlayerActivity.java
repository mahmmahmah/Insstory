package com.mah.insstory.MAH;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import java.text.DecimalFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class PlayerActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String str = "";
	private double pos = 0;
	private String img = "";
	private String capt = "";
	private String text = "";
	private String lastpos = "";
	private double posi = 0;
	private String likes = "";
	private HashMap<String, Object> ReelMap = new HashMap<>();
	private double n = 0;
	private double Likes = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> AvatarUrl = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listvideo = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> reels_map = new ArrayList<>();
	private ArrayList<String> ReelKeys = new ArrayList<>();
	
	private RelativeLayout linear1;
	private ViewPager viewpager2;
	private LinearLayout linear2;
	private LinearLayout linear3;
	private TextView textview1;
	private LinearLayout linear4;
	private ImageView imageview1;
	
	private RequestNetwork ra;
	private RequestNetwork.RequestListener _ra_request_listener;
	private TimerTask set;
	private TimerTask toms;
	private TimerTask liker;
	private ObjectAnimator ojp = new ObjectAnimator();
	private Intent comments = new Intent();
	private SharedPreferences last;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference rdb = _firebase.getReference("reels");
	private ChildEventListener _rdb_child_listener;
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
		setContentView(R.layout.player);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		viewpager2 = findViewById(R.id.viewpager2);
		linear2 = findViewById(R.id.linear2);
		linear3 = findViewById(R.id.linear3);
		textview1 = findViewById(R.id.textview1);
		linear4 = findViewById(R.id.linear4);
		imageview1 = findViewById(R.id.imageview1);
		ra = new RequestNetwork(this);
		last = getSharedPreferences("lastpos", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		
		viewpager2.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				pos = _position;
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		_ra_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("uid") && _childValue.containsKey("username")) {
					UserName.put(_childValue.get("uid").toString(), _childValue.get("username").toString());
					AvatarUrl.put(_childValue.get("uid").toString(), _childValue.get("avatar").toString());
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
		
		_rdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				rdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						reels_map = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								reels_map.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						Collections.shuffle(reels_map);
						viewpager2.setAdapter(new Viewpager2Adapter(reels_map));
						((PagerAdapter)viewpager2.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
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
		rdb.addChildEventListener(_rdb_child_listener);
		
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
		
		
	}
	
	@Override
	protected void onPostCreate(Bundle _savedInstanceState) {
		super.onPostCreate(_savedInstanceState);
		
	}
	public void _ActivityTransition(final View _view, final String _transitionName, final Intent _intent) {
		_view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(PlayerActivity.this, _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
	}
	
	
	public void _setCount(final TextView _txt, final double _number) {
		_txt.setText(String.valueOf((long)(_number)).concat(" ".concat(likes)));
		if (_number < 1000) {
			_txt.setText(String.valueOf((long)(_number)).concat(" ".concat(likes)));
		}
		else {
			if (_number < 10000) {
				_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(3)).concat("K ".concat(likes)));
			}
			else {
				if (_number < 100000) {
					_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(4)).concat("K ".concat(likes)));
				}
				else {
					if (_number < 1000000) {
						_txt.setText(String.valueOf(_number / 1000).substring((int)(0), (int)(3)).concat("K ".concat(likes)));
					}
					else {
						if (_number < 10000000) {
							_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(3)).concat("M ".concat(likes)));
						}
						else {
							if (_number < 100000000) {
								_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(2)).concat("M ".concat(likes)));
							}
							else {
								if (_number < 1000000000) {
									_txt.setText(String.valueOf(_number / 1000000).substring((int)(0), (int)(3)).concat("M ".concat(likes)));
								}
								else {
									if (_number < 10000000000d) {
										_txt.setText(String.valueOf(_number / 1000000000).substring((int)(0), (int)(3)).concat("B ".concat(likes)));
									}
									else {
										if (_number < 100000000000d) {
											_txt.setText(String.valueOf(_number / 1000000000).substring((int)(0), (int)(2)).concat("B ".concat(likes)));
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	public class Viewpager2Adapter extends PagerAdapter {
		
		Context _context;
		ArrayList<HashMap<String, Object>> _data;
		
		public Viewpager2Adapter(Context _ctx, ArrayList<HashMap<String, Object>> _arr) {
			_context = _ctx;
			_data = _arr;
		}
		
		public Viewpager2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_context = getApplicationContext();
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public boolean isViewFromObject(View _view, Object _object) {
			return _view == _object;
		}
		
		@Override
		public void destroyItem(ViewGroup _container, int _position, Object _object) {
			_container.removeView((View) _object);
		}
		
		@Override
		public int getItemPosition(Object _object) {
			return super.getItemPosition(_object);
		}
		
		@Override
		public CharSequence getPageTitle(int pos) {
			// Use the Activity Event (onTabLayoutNewTabAdded) in order to use this method
			return "page " + String.valueOf(pos);
		}
		
		@Override
		public Object instantiateItem(ViewGroup _container,  final int _position) {
			View _view = LayoutInflater.from(_context).inflate(R.layout.items, _container, false);
			
			final RelativeLayout back = _view.findViewById(R.id.back);
			final LinearLayout bot = _view.findViewById(R.id.bot);
			final LinearLayout top = _view.findViewById(R.id.top);
			final VideoView videoview1 = _view.findViewById(R.id.videoview1);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout linear8 = _view.findViewById(R.id.linear8);
			final LinearLayout video_ui_top = _view.findViewById(R.id.video_ui_top);
			final LinearLayout linear11 = _view.findViewById(R.id.linear11);
			final LinearLayout linear10 = _view.findViewById(R.id.linear10);
			final TextView caption = _view.findViewById(R.id.caption);
			final ImageView profileimg = _view.findViewById(R.id.profileimg);
			final TextView chnnlname = _view.findViewById(R.id.chnnlname);
			final LinearLayout like_layout = _view.findViewById(R.id.like_layout);
			final LinearLayout comment_layout = _view.findViewById(R.id.comment_layout);
			final LinearLayout share_layout = _view.findViewById(R.id.share_layout);
			final LinearLayout more_layout = _view.findViewById(R.id.more_layout);
			final ImageView unlike_button = _view.findViewById(R.id.unlike_button);
			final ImageView like_button = _view.findViewById(R.id.like_button);
			final TextView like_count = _view.findViewById(R.id.like_count);
			final ImageView comment_button = _view.findViewById(R.id.comment_button);
			final TextView comments_count = _view.findViewById(R.id.comments_count);
			final ImageView share_button = _view.findViewById(R.id.share_button);
			final TextView share_count = _view.findViewById(R.id.share_count);
			final ImageView more_button = _view.findViewById(R.id.more_button);
			
			back.setLayoutParams(new LinearLayout.LayoutParams(SketchwareUtil.getDisplayWidthPixels(getApplicationContext()), SketchwareUtil.getDisplayHeightPixels(getApplicationContext())));
			chnnlname.setText(UserName.get(_data.get((int)_position).get("uid").toString()).toString());
			caption.setText(_data.get((int)_position).get("desc").toString());
			comments_count.setText("0");
			img = AvatarUrl.get(_data.get((int)_position).get("uid").toString()).toString();
			Picasso.with(getApplicationContext())
			.load(img)
			.placeholder(R.drawable.loading)
			.fit().centerCrop()
			.transform(new CropCircleTransformation())
			.into(profileimg);
			str = _data.get((int)_position).get("post_video").toString();
			videoview1.setVideoURI(Uri.parse(str));
			toms = new TimerTask() {
				@Override
				public void run() {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							if (_position == pos) {
								videoview1.start();
								if (!last.getString("pos", "").equals("0")) {
									videoview1.resume();
								}
							}
							else {
								videoview1.pause();
							}
						}
					});
				}
			};
			_timer.scheduleAtFixedRate(toms, (int)(0), (int)(100));
			comment_layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					videoview1.pause();
					lastpos = "yes";
					comments.putExtra("url", _data.get((int)_position).get("post_video").toString());
					comments.putExtra("pos", String.valueOf((long)(videoview1.getCurrentPosition())));
					comments.setClass(getApplicationContext(), CommentsActivity.class);
					_ActivityTransition(videoview1, "vidview", comments);
				}
			});
			_ImageColor(like_button, "#FFFFFF");
			_ImageColor(comment_button, "#FFFFFF");
			_ImageColor(share_button, "#FFFFFF");
			if (_data.get((int)_position).get("disable_comments").toString().equals("true")) {
				comment_layout.setVisibility(View.GONE);
			}
			else {
				comment_layout.setVisibility(View.VISIBLE);
			}
			if (_data.get((int)_position).get("hide_post").toString().equals("true")) {
				reels_map.remove((int)(_position));
			}
			ReelMap = reels_map.get((int)_position);
			SketchwareUtil.getAllKeysFromMap(ReelMap, ReelKeys);
			n = 0;
			Likes = 0;
			for(int _repeat248 = 0; _repeat248 < (int)(ReelKeys.size()); _repeat248++) {
				if (ReelMap.get(ReelKeys.get((int)(n))).toString().equals("liked")) {
					Likes++;
				}
				n++;
			}
			_setCount(like_count, Likes);
			_ImageColor(unlike_button, "#E91E63");
			_ImageColor(like_button, "#FFFFFF");
			if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
				if (ReelMap.containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (ReelMap.get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("liked")) {
						like_button.setVisibility(View.GONE);
						unlike_button.setVisibility(View.VISIBLE);
					}
					else {
						like_button.setVisibility(View.VISIBLE);
						unlike_button.setVisibility(View.GONE);
					}
				}
				else {
					like_button.setVisibility(View.VISIBLE);
					unlike_button.setVisibility(View.GONE);
				}
			}
			else {
				like_button.setVisibility(View.VISIBLE);
				unlike_button.setVisibility(View.GONE);
			}
			like_layout.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (like_button.getVisibility() == View.VISIBLE) {
						map = new HashMap<>();
						map.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "liked");
						rdb.child(_data.get((int)_position).get("post_key").toString()).updateChildren(map);
						map.clear();
						Likes++;
						_setCount(like_count, Likes);
						like_button.setVisibility(View.GONE);
						unlike_button.setVisibility(View.VISIBLE);
					}
					else {
						map = new HashMap<>();
						map.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "unliked");
						rdb.child(_data.get((int)_position).get("post_key").toString()).updateChildren(map);
						map.clear();
						Likes--;
						_setCount(like_count, Likes);
						like_button.setVisibility(View.VISIBLE);
						unlike_button.setVisibility(View.GONE);
					}
				}
			});
			comment_layout.setVisibility(View.GONE);
			more_layout.setVisibility(View.GONE);
			share_layout.setVisibility(View.GONE);
			text = "";
			
			_container.addView(_view);
			return _view;
		}
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