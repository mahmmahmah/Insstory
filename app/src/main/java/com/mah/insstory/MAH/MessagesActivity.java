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
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnAdapterChangeListener;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.aghajari.emojiview.*;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.InputStream;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;
import androidx.core.widget.NestedScrollView;

public class MessagesActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String key = "";
	private String mykey = "";
	private double tm_difference = 0;
	private String dbChats = "";
	private String mychats_str = "";
	private String uid = "";
	private String fontName = "";
	private String typeace = "";
	private String username_ = "";
	private String avatar_ = "";
	private String idkey_ = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String delete_chat_title = "";
	private String delete_chat_text = "";
	private String delete_chat_b1 = "";
	private String delete_chat_b2 = "";
	private String chat_deleted = "";
	private String YouM = "";
	private String Typing = "";
	private String groups_db = "";
	private String a_second = "";
	private String second = "";
	private String a_minute = "";
	private String minute = "";
	private String hours = "";
	private String days = "";
	private String MyStory = "";
	private String AddStory = "";
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> UserVerified = new HashMap<>();
	private HashMap<String, Object> UserProfile = new HashMap<>();
	private String messages_tab = "";
	private String groups_tab = "";
	private String requests_tab = "";
	private HashMap<String, Object> UserStory = new HashMap<>();
	private String friendsdb = "";
	
	private ArrayList<HashMap<String, Object>> messagesmap = new ArrayList<>();
	private ArrayList<String> delkey = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> groups = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> StoryMap = new ArrayList<>();
	
	private LinearLayout body;
	private NestedScrollView nested;
	private SwipeRefreshLayout swipe;
	private LinearLayout bottom;
	private LinearLayout nestedbody;
	private LinearLayout top;
	private CircleImageView profile_image;
	private TextView yourname;
	private LinearLayout search_body;
	private LinearLayout menu_body;
	private ImageView search_m_icon;
	private ImageView menu_m_icon;
	private LinearLayout swipe_body;
	private HorizontalScrollView stories_scrool;
	private TabLayout tabs;
	private ViewPager pages;
	private LinearLayout stories_scrool_body;
	private LinearLayout mystory_body;
	private RecyclerView stories_view;
	private CircleImageView mystory_profile;
	private TextView mystory_username;
	private LinearLayout home;
	private LinearLayout search;
	private LinearLayout shop;
	private LinearLayout messages;
	private LinearLayout profile;
	private ImageView home_icon;
	private ImageView search_icon;
	private ImageView shop_icon;
	private ImageView messages_icon;
	private CircleImageView profile_avatar;
	
	private SharedPreferences save;
	private SharedPreferences lang;
	private Intent intent = new Intent();
	private FragFragmentAdapter frag;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference fdb = _firebase.getReference("friends");
	private ChildEventListener _fdb_child_listener;
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
	
	private SharedPreferences last;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.messages);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		nested = findViewById(R.id.nested);
		swipe = findViewById(R.id.swipe);
		bottom = findViewById(R.id.bottom);
		nestedbody = findViewById(R.id.nestedbody);
		top = findViewById(R.id.top);
		profile_image = findViewById(R.id.profile_image);
		yourname = findViewById(R.id.yourname);
		search_body = findViewById(R.id.search_body);
		menu_body = findViewById(R.id.menu_body);
		search_m_icon = findViewById(R.id.search_m_icon);
		menu_m_icon = findViewById(R.id.menu_m_icon);
		swipe_body = findViewById(R.id.swipe_body);
		stories_scrool = findViewById(R.id.stories_scrool);
		tabs = findViewById(R.id.tabs);
		pages = findViewById(R.id.pages);
		stories_scrool_body = findViewById(R.id.stories_scrool_body);
		mystory_body = findViewById(R.id.mystory_body);
		stories_view = findViewById(R.id.stories_view);
		mystory_profile = findViewById(R.id.mystory_profile);
		mystory_username = findViewById(R.id.mystory_username);
		home = findViewById(R.id.home);
		search = findViewById(R.id.search);
		shop = findViewById(R.id.shop);
		messages = findViewById(R.id.messages);
		profile = findViewById(R.id.profile);
		home_icon = findViewById(R.id.home_icon);
		search_icon = findViewById(R.id.search_icon);
		shop_icon = findViewById(R.id.shop_icon);
		messages_icon = findViewById(R.id.messages_icon);
		profile_avatar = findViewById(R.id.profile_avatar);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		frag = new FragFragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		auth = FirebaseAuth.getInstance();
		last = getSharedPreferences("lastpos", Activity.MODE_PRIVATE);
		
		tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
			@Override
			public void onTabSelected(TabLayout.Tab tab) {
				final int _position = tab.getPosition();
				pages.setCurrentItem((int)_position);
			}
			
			@Override
			public void onTabUnselected(TabLayout.Tab tab) {
				final int _position = tab.getPosition();
				
			}
			
			@Override
			public void onTabReselected(TabLayout.Tab tab) {
				final int _position = tab.getPosition();
				
			}
		});
		
		pages.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int _position, float _positionOffset, int _positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageSelected(int _position) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int _scrollState) {
				
			}
		});
		
		stories_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(RecyclerView recyclerView, int _scrollState) {
				super.onScrollStateChanged(recyclerView, _scrollState);
				
			}
			
			@Override
			public void onScrolled(RecyclerView recyclerView, int _offsetX, int _offsetY) {
				super.onScrolled(recyclerView, _offsetX, _offsetY);
				
			}
		});
		
		home.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), DashboardActivity.class);
				intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
		
		search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), SearchActivity.class);
				intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
		
		shop.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), PlayerActivity.class);
				startActivity(intent);
			}
		});
		
		messages.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), ProfileActivity.class);
				intent.putExtra("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
				startActivity(intent);
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("uid") && _childValue.containsKey("username")) {
					UserName.put(_childValue.get("uid").toString(), _childValue.get("username").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("avatar")) {
					UserProfile.put(_childValue.get("uid").toString(), _childValue.get("avatar").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("verify")) {
					UserVerified.put(_childValue.get("uid").toString(), _childValue.get("verify").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("story")) {
					UserStory.put(_childValue.get("uid").toString(), _childValue.get("story").toString());
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(mystory_profile);
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(profile_image);
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(profile_avatar);
					yourname.setText(_childValue.get("yourname").toString());
					if (_childValue.get("story").toString().equals("true")) {
						mystory_profile.setBackgroundResource(R.drawable.story_profile);
						mystory_username.setText(MyStory);
						mystory_body.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								intent.setClass(getApplicationContext(), StoryviewActivity.class);
								intent.putExtra("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
								startActivity(intent);
							}
						});
					}
					else {
						mystory_profile.setBackgroundResource(0);
						mystory_username.setText(AddStory);
						mystory_body.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								intent.setClass(getApplicationContext(), AddstoryActivity.class);
								startActivity(intent);
							}
						});
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("blocked").toString().equals("true")) {
						intent.setClass(getApplicationContext(), BlockActivity.class);
						startActivity(intent);
						swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
							@Override
							public void onRefresh() {
								if (_childValue.get("story").toString().equals("true")) {
									mystory_profile.setBackgroundResource(R.drawable.story_profile);
									mystory_username.setText(MyStory);
									mystory_body.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View _view) {
											intent.setClass(getApplicationContext(), StoryviewActivity.class);
											intent.putExtra("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
											startActivity(intent);
										}
									});
								}
								else {
									mystory_profile.setBackgroundResource(0);
									mystory_username.setText(AddStory);
									mystory_body.setOnClickListener(new View.OnClickListener() {
										@Override
										public void onClick(View _view) {
											intent.setClass(getApplicationContext(), AddstoryActivity.class);
											startActivity(intent);
										}
									});
								}
								swipe.setRefreshing(false);
							}
						});
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
		
		_fdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						fdb.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								StoryMap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										StoryMap.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								stories_view.setAdapter(new Stories_viewAdapter(StoryMap));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						swipe.setRefreshing(false);
					}
				});
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						fdb.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								StoryMap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										StoryMap.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								stories_view.setAdapter(new Stories_viewAdapter(StoryMap));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						swipe.setRefreshing(false);
					}
				});
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
					@Override
					public void onRefresh() {
						fdb.addListenerForSingleValueEvent(new ValueEventListener() {
							@Override
							public void onDataChange(DataSnapshot _dataSnapshot) {
								StoryMap = new ArrayList<>();
								try {
									GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
									for (DataSnapshot _data : _dataSnapshot.getChildren()) {
										HashMap<String, Object> _map = _data.getValue(_ind);
										StoryMap.add(_map);
									}
								}
								catch (Exception _e) {
									_e.printStackTrace();
								}
								stories_view.setAdapter(new Stories_viewAdapter(StoryMap));
							}
							@Override
							public void onCancelled(DatabaseError _databaseError) {
							}
						});
						swipe.setRefreshing(false);
					}
				});
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		fdb.addChildEventListener(_fdb_child_listener);
		
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
		last.edit().putString("pos", "0").commit();
		_Language();
		stories_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
		tabs.setupWithViewPager(pages);
		frag.setTabCount(3);
		pages.setAdapter(frag);
		fdb.removeEventListener(_fdb_child_listener);
		friendsdb = "friends/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		fdb =
		_firebase.getReference(friendsdb);
		fdb.addChildEventListener(_fdb_child_listener);
		fdb.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				StoryMap = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						StoryMap.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				stories_view.setAdapter(new Stories_viewAdapter(StoryMap));
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		yourname.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		menu_body.setVisibility(View.GONE);
		search_body.setVisibility(View.GONE);
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
			if (_position == 0) {
				return messages_tab;
			}
			if (_position == 1) {
				return groups_tab;
			}
			if (_position == 2) {
				return requests_tab;
			}
			return null;
		}
		
		@Override
		public Fragment getItem(int _position) {
			if (_position == 0) {
				return new MessagesTabFragmentActivity();
			}
			if (_position == 1) {
				return new GroupsTabFragmentActivity();
			}
			if (_position == 2) {
				return new MessageRequestsTabFragmentActivity();
			}
			return null;
		}
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
		body.setBackgroundColor(0xFFFFFFFF);
		_rippleRoundStroke(mystory_body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
		android.graphics.drawable.GradientDrawable BottomNavigationBG = new android.graphics.drawable.GradientDrawable();
		int bnbg = (int) getApplicationContext().getResources().getDisplayMetrics().density;
		BottomNavigationBG.setColor(0xFFFFFFFF);
		BottomNavigationBG.setCornerRadii(new float[]{	bnbg*25,bnbg*25,bnbg*25 ,bnbg*25,bnbg*0,bnbg*0,bnbg*0,bnbg*0});
		bottom.setElevation(bnbg*15);
		bottom.setBackground(BottomNavigationBG);
		_ImageColor(home_icon, "#000000");
		_ImageColor(search_icon, "#000000");
		_ImageColor(shop_icon, "#000000");
		_ImageColor(search_m_icon, "#000000");
		_ImageColor(menu_m_icon, "#000000");
		tabs.setTabTextColors(0xFF000000, 0xFF2196F3);
		tabs.setTabRippleColor(new android.content.res.ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed}}, 
		
		new int[] {0xFF9E9E9E}));
		tabs.setSelectedTabIndicatorColor(0xFF2196F3);
		_Language();
	}
	public void _setTime(final double _currentTime, final TextView _txt) {
		tm_difference = 0 - _currentTime;
		if (tm_difference < 60000) {
			if ((tm_difference / 1000) < 2) {
				_txt.setText(a_second);
			}
			else {
				_txt.setText(String.valueOf((long)(tm_difference / 1000)).concat(second));
			}
		}
		else {
			if (tm_difference < (60 * 60000)) {
				if ((tm_difference / 60000) < 2) {
					_txt.setText(a_minute);
				}
				else {
					_txt.setText(String.valueOf((long)(tm_difference / 60000)).concat(minute));
				}
			}
			else {
				if (tm_difference < (24 * (60 * 60000))) {
					if ((tm_difference / (60 * 60000)) < 2) {
						_txt.setText(String.valueOf((long)(tm_difference / (60 * 60000))).concat(hours));
					}
					else {
						_txt.setText(String.valueOf((long)(tm_difference / (60 * 60000))).concat(hours));
					}
				}
				else {
					if (tm_difference < (7 * (24 * (60 * 60000)))) {
						if ((tm_difference / (24 * (60 * 60000))) < 2) {
							_txt.setText(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(days));
						}
						else {
							_txt.setText(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(days));
						}
					}
					else {
						
						_txt.setText("");
					}
				}
			}
		}
	}
	
	
	public void _SortMap(final ArrayList<HashMap<String, Object>> _listMap, final String _key, final boolean _isNumber, final boolean _Ascending) {
		Collections.sort(_listMap, new Comparator<HashMap<String,Object>>(){
			public int compare(HashMap<String,Object> _compareMap1, HashMap<String,Object> _compareMap2){
				if (_isNumber) {
					int _count1 = Integer.valueOf(_compareMap1.get(_key).toString());
					int _count2 = Integer.valueOf(_compareMap2.get(_key).toString());
					if (_Ascending) {
						return _count1 < _count2 ? -1 : _count1 < _count2 ? 1 : 0;
					}
					else {
						return _count1 > _count2 ? -1 : _count1 > _count2 ? 1 : 0;
					}
				}
				else {
					if (_Ascending) {
						return (_compareMap1.get(_key).toString()).compareTo(_compareMap2.get(_key).toString());
					}
					else {
						return (_compareMap2.get(_key).toString()).compareTo(_compareMap1.get(_key).toString());
					}
				}
			}});
	}
	
	
	public void _Glide(final ImageView _img, final String _url) {
		Glide.with(MessagesActivity.this)
		        .load(_url)
		        .circleCrop()
		        .into(_img);
		
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
	
	
	public void _TrLang() {
		messages_tab = "Mesajlar";
		groups_tab = "Gruplar";
		requests_tab = "İstekler";
		delete_chat_title = "Sohbeti Sil?";
		delete_chat_text = "Bu sohbeti silmek istediğine emin misin? Sohbet içerisindeki mesajlar silinmeyecek.";
		delete_chat_b1 = "HAYIR";
		delete_chat_b2 = "EVET";
		chat_deleted = "Sohbet Silindi!";
		YouM = "Sen: ";
		Typing = "● Yazıyor...";
		a_second = "Bir saniye önce";
		second = " Saniye önce";
		a_minute = "Bir dakika önce";
		minute = " Dakika önce";
		hours = " Saat önce";
		days = " Gün önce";
		MyStory = "Hikayem";
		AddStory = "Hikaye Ekle";
	}
	
	
	public void _EnLang() {
		messages_tab = "Messages";
		groups_tab = "Groups";
		requests_tab = "Requests";
		delete_chat_title = "Delete Chat?";
		delete_chat_text = "Are you sure you want to delete this chat? Messages in chat will not be deleted.";
		delete_chat_b1 = "NO";
		delete_chat_b2 = "YES";
		chat_deleted = "Chat Deleted!";
		YouM = "You: ";
		Typing = "● Typing...";
		a_second = "A second ago";
		second = " Second ago";
		a_minute = "A minute ago";
		minute = " Minute ago";
		hours = " Hours ago";
		days = " days ago";
		MyStory = "My Story";
		AddStory = "Add Story";
	}
	
	
	public void _InLang() {
		messages_tab = "Pesan";
		groups_tab = "Grup";
		requests_tab = "Permintaan";
		delete_chat_title = "Hapus Pesan?";
		delete_chat_text = "Anda yakin ingin menghapus obrolan ini? Pesan dalam obrolan tidak akan dihapus.";
		delete_chat_b1 = "Tidak";
		delete_chat_b2 = "Iya";
		chat_deleted = "Obrolan Dihapus!";
		YouM = "Anda: ";
		Typing = "● Mengetik...";
		a_second = "Detik yang lalu";
		second = " Detik lalu";
		a_minute = "A minute ago";
		minute = " Menit lalu";
		hours = " Jam lalu";
		days = " hari lalu";
		MyStory = "Cerita Saya";
		AddStory = "Buat Cerita";
	}
	
	public class Stories_viewAdapter extends RecyclerView.Adapter<Stories_viewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Stories_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.home_storyes, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout body = _view.findViewById(R.id.body);
			final de.hdodenhof.circleimageview.CircleImageView profile_image = _view.findViewById(R.id.profile_image);
			final TextView username = _view.findViewById(R.id.username);
			
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			if (save.getString("theme", "").equals("light")) {
				_rippleRoundStroke(body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
				username.setTextColor(0xFF000000);
			}
			else {
				if (save.getString("theme", "").equals("dark")) {
					_rippleRoundStroke(body, "#000000", "#EEEEEE", 5, 0, "#FFFFFF");
					username.setTextColor(0xFFFFFFFF);
				}
			}
			Glide.with(getApplicationContext()).load(Uri.parse(UserProfile.get(_data.get((int)_position).get("reciever").toString()).toString())).into(profile_image);
			username.setText(UserName.get(_data.get((int)_position).get("reciever").toString()).toString());
			if (UserStory.get(_data.get((int)_position).get("reciever").toString()).toString().equals("true")) {
				body.setVisibility(View.VISIBLE);
				profile_image.setBackgroundResource(R.drawable.story_profile);
				profile_image.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setClass(getApplicationContext(), StoryviewActivity.class);
						intent.putExtra("uid", _data.get((int)_position).get("reciever").toString());
						startActivity(intent);
					}
				});
			}
			else {
				if (UserStory.get(_data.get((int)_position).get("reciever").toString()).toString().equals("false")) {
					body.setVisibility(View.GONE);
				}
			}
			body.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), ProfileActivity.class);
					intent.putExtra("uid", _data.get((int)_position).get("reciever").toString());
					startActivity(intent);
				}
			});
		}
		
		@Override
		public int getItemCount() {
			return _data.size();
		}
		
		public class ViewHolder extends RecyclerView.ViewHolder {
			public ViewHolder(View v) {
				super(v);
			}
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