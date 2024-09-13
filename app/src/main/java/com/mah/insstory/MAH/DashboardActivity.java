package com.mah.insstory.MAH;

import android.Manifest;
import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import de.hdodenhof.circleimageview.*;
import java.io.*;
import java.io.File;
import java.io.InputStream;
import java.text.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;
import androidx.core.widget.NestedScrollView;
import androidx.cardview.widget.CardView;

public class DashboardActivity extends AppCompatActivity {
	
	public final int REQ_CD_HR = 101;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String key = "";
	private String fontName = "";
	private String typeace = "";
	private String uidkey = "";
	private String uid = "";
	private String username_ = "";
	private String avatar_ = "";
	private String idkey_ = "";
	private double n = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private HashMap<String, Object> PostMap = new HashMap<>();
	private double PostLikes = 0;
	private HashMap<String, Object> lmap = new HashMap<>();
	private String add_story_title = "";
	private String my_story_title = "";
	private String object_clicked = "";
	private String welcome = "";
	private String PostKey = "";
	private String TextPostSent = "";
	private String users_db = "";
	private double width1 = 0;
	private double height1 = 0;
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> UserVerified = new HashMap<>();
	private HashMap<String, Object> UserProfile = new HashMap<>();
	private String likes = "";
	private String vac = "";
	private String ExitDialogTitle = "";
	private String ExitDialogMessage = "";
	private String YES = "";
	private String NO = "";
	private String image_path = "";
	private double no = 0;
	private double read = 0;
	private double rlist = 0;
	private String Dbfriends = "";
	private HashMap<String, Object> Friend = new HashMap<>();
	private double position_number = 0;
	private double tm_difference = 0;
	private String a_second = "";
	private String second = "";
	private String a_minute = "";
	private String minute = "";
	private String hours = "";
	private String days = "";
	private HashMap<String, Object> UserStory = new HashMap<>();
	private String AddStory = "";
	private String friendsdb = "";
	private String path_vi_d = "";
	private String name_vid = "";
	private HashMap<String, Object> map_vid = new HashMap<>();
	private String ReelsPath = "";
	
	private ArrayList<HashMap<String, Object>> posts_map = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> users_map = new ArrayList<>();
	private ArrayList<String> key_list_ = new ArrayList<>();
	private ArrayList<String> uid_list_ = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> PostsMap = new ArrayList<>();
	private ArrayList<String> PostKeys = new ArrayList<>();
	private ArrayList<String> PchildKeys = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> accs = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> frend = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> StoryMap = new ArrayList<>();
	
	private CardView top_view;
	private LinearLayout linear_background;
	private LinearLayout top;
	private LinearLayout linear1;
	private ImageView shop_icon;
	private ImageView search_icon;
	private ImageView add_post;
	private ImageView notifications;
	private ImageView imageview1;
	private TextView title;
	private LinearLayout tablayout;
	private LinearLayout linear2;
	private LinearLayout base;
	private LinearLayout trash;
	private LinearLayout layouts;
	private ImageView imageview2;
	private ImageView imageview3;
	private ImageView imageview4;
	private ImageView imageview5;
	private ImageView imageview6;
	private LinearLayout layout1;
	private LinearLayout layout2;
	private LinearLayout layout3;
	private LinearLayout layout4;
	private LinearLayout layout5;
	private LinearLayout body;
	private LinearLayout main_int;
	private SwipeRefreshLayout swipe;
	private NestedScrollView scroll;
	private LinearLayout main_body;
	private HorizontalScrollView stories_scrool;
	private RecyclerView posts_view;
	private LinearLayout stories_scrool_body;
	private LinearLayout mystory_body;
	private RecyclerView stories_view;
	private CircleImageView mystory_profile;
	private TextView mystory_username;
	private RecyclerView recyclerview2;
	private ViewPager viewpager1;
	private ViewPager viewpager2;
	private ViewPager viewpager3;
	
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
	
	private Intent intent = new Intent();
	private AlertDialog.Builder dialog;
	private SharedPreferences save;
	private SharedPreferences lang;
	private DatabaseReference udbs = _firebase.getReference("users");
	private ChildEventListener _udbs_child_listener;
	private ObjectAnimator o = new ObjectAnimator();
	private TimerTask poPup;
	private DatabaseReference pdb = _firebase.getReference("posts");
	private ChildEventListener _pdb_child_listener;
	private Calendar cc = Calendar.getInstance();
	private StorageReference psd = _firebase_storage.getReference("posts/image");
	private OnCompleteListener<Uri> _psd_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _psd_download_success_listener;
	private OnSuccessListener _psd_delete_success_listener;
	private OnProgressListener _psd_upload_progress_listener;
	private OnProgressListener _psd_download_progress_listener;
	private OnFailureListener _psd_failure_listener;
	
	private SharedPreferences last;
	private DatabaseReference friends = _firebase.getReference("friends");
	private ChildEventListener _friends_child_listener;
	private TimerTask timer;
	private Frag1FragmentAdapter frag1;
	private Frag2FragmentAdapter frag2;
	private Pagr3FragmentAdapter pagr3;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference fdb = _firebase.getReference("friends");
	private ChildEventListener _fdb_child_listener;
	private DatabaseReference sdb = _firebase.getReference("social/stories");
	private ChildEventListener _sdb_child_listener;
	private Intent hr = new Intent(Intent.ACTION_GET_CONTENT);
	private StorageReference v_dio = _firebase_storage.getReference("v_dio");
	private OnCompleteListener<Uri> _v_dio_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _v_dio_download_success_listener;
	private OnSuccessListener _v_dio_delete_success_listener;
	private OnProgressListener _v_dio_upload_progress_listener;
	private OnProgressListener _v_dio_download_progress_listener;
	private OnFailureListener _v_dio_failure_listener;
	
	private Intent in = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dashboard);
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
		top_view = findViewById(R.id.top_view);
		linear_background = findViewById(R.id.linear_background);
		top = findViewById(R.id.top);
		linear1 = findViewById(R.id.linear1);
		shop_icon = findViewById(R.id.shop_icon);
		search_icon = findViewById(R.id.search_icon);
		add_post = findViewById(R.id.add_post);
		notifications = findViewById(R.id.notifications);
		imageview1 = findViewById(R.id.imageview1);
		title = findViewById(R.id.title);
		tablayout = findViewById(R.id.tablayout);
		linear2 = findViewById(R.id.linear2);
		base = findViewById(R.id.base);
		trash = findViewById(R.id.trash);
		layouts = findViewById(R.id.layouts);
		imageview2 = findViewById(R.id.imageview2);
		imageview3 = findViewById(R.id.imageview3);
		imageview4 = findViewById(R.id.imageview4);
		imageview5 = findViewById(R.id.imageview5);
		imageview6 = findViewById(R.id.imageview6);
		layout1 = findViewById(R.id.layout1);
		layout2 = findViewById(R.id.layout2);
		layout3 = findViewById(R.id.layout3);
		layout4 = findViewById(R.id.layout4);
		layout5 = findViewById(R.id.layout5);
		body = findViewById(R.id.body);
		main_int = findViewById(R.id.main_int);
		swipe = findViewById(R.id.swipe);
		scroll = findViewById(R.id.scroll);
		main_body = findViewById(R.id.main_body);
		stories_scrool = findViewById(R.id.stories_scrool);
		posts_view = findViewById(R.id.posts_view);
		stories_scrool_body = findViewById(R.id.stories_scrool_body);
		mystory_body = findViewById(R.id.mystory_body);
		stories_view = findViewById(R.id.stories_view);
		mystory_profile = findViewById(R.id.mystory_profile);
		mystory_username = findViewById(R.id.mystory_username);
		recyclerview2 = findViewById(R.id.recyclerview2);
		viewpager1 = findViewById(R.id.viewpager1);
		viewpager2 = findViewById(R.id.viewpager2);
		viewpager3 = findViewById(R.id.viewpager3);
		auth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		last = getSharedPreferences("lastpos", Activity.MODE_PRIVATE);
		frag1 = new Frag1FragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		frag2 = new Frag2FragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		pagr3 = new Pagr3FragmentAdapter(getApplicationContext(), getSupportFragmentManager());
		hr.setType("video/*");
		hr.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		add_post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Popup();
			}
		});
		
		notifications.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				read = no;
				intent.setClass(getApplicationContext(), InboxActivity.class);
				startActivity(intent);
			}
		});
		
		main_body.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		mystory_profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		_udbs_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.containsKey("uid") && _childValue.containsKey("username")) {
					UserName.put(_childValue.get("uid").toString(), _childValue.get("username").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("verify")) {
					UserVerified.put(_childValue.get("uid").toString(), _childValue.get("verify").toString());
				}
				if (_childValue.containsKey("uid") && _childValue.containsKey("avatar")) {
					UserProfile.put(_childValue.get("uid").toString(), _childValue.get("avatar").toString());
				}
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					save.edit().putString("mykey", FirebaseAuth.getInstance().getCurrentUser().getUid()).commit();
					save.edit().putString("myname", _childValue.get("username").toString()).commit();
					save.edit().putString("myavatar", _childValue.get("avatar").toString()).commit();
					save.edit().putString("myverify", _childValue.get("verify").toString()).commit();
					save.edit().putString("hidestatus", _childValue.get("hide_status").toString()).commit();
					save.edit().putString("mypremium", _childValue.get("account_mode").toString()).commit();
					
					uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
					username_ = _childValue.get("username").toString();
					avatar_ = _childValue.get("avatar").toString();
					idkey_ = FirebaseAuth.getInstance().getCurrentUser().getUid();
					swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
						@Override
						public void onRefresh() {
							pdb.addListenerForSingleValueEvent(new ValueEventListener() {
								@Override
								public void onDataChange(DataSnapshot _dataSnapshot) {
									PostsMap = new ArrayList<>();
									try {
										GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
										for (DataSnapshot _data : _dataSnapshot.getChildren()) {
											HashMap<String, Object> _map = _data.getValue(_ind);
											PostsMap.add(_map);
										}
									}
									catch (Exception _e) {
										_e.printStackTrace();
									}
									posts_view.setAdapter(new Posts_viewAdapter(PostsMap));
								}
								@Override
								public void onCancelled(DatabaseError _databaseError) {
								}
							});
							swipe.setRefreshing(false);
						}
					});
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					save.edit().putString("mykey", FirebaseAuth.getInstance().getCurrentUser().getUid()).commit();
					save.edit().putString("myname", _childValue.get("username").toString()).commit();
					save.edit().putString("myavatar", _childValue.get("avatar").toString()).commit();
					save.edit().putString("myverify", _childValue.get("verify").toString()).commit();
					save.edit().putString("hidestatus", _childValue.get("hide_status").toString()).commit();
					save.edit().putString("mypremium", _childValue.get("account_mode").toString()).commit();
					
					if (_childValue.get("blocked").toString().equals("true")) {
						intent.setClass(getApplicationContext(), BlockActivity.class);
						startActivity(intent);
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
		udbs.addChildEventListener(_udbs_child_listener);
		
		_pdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				pdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						PostsMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								PostsMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						PchildKeys.add(_childKey);
						posts_view.setAdapter(new Posts_viewAdapter(PostsMap));
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
				pdb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						PostsMap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								PostsMap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						posts_view.setAdapter(new Posts_viewAdapter(PostsMap));
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
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
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		pdb.addChildEventListener(_pdb_child_listener);
		
		_psd_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_psd_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_psd_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_psd_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getApplicationContext(), "Images Downloaded!");
			}
		};
		
		_psd_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_psd_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_friends_child_listener = new ChildEventListener() {
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
		friends.addChildEventListener(_friends_child_listener);
		
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
					if (_childValue.get("story").toString().equals("true")) {
						mystory_profile.setBackgroundResource(R.drawable.video2_2);
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
					if (_childValue.get("story").toString().equals("true")) {
						mystory_profile.setBackgroundResource(R.drawable.video2_2);
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
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
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
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		fdb.addChildEventListener(_fdb_child_listener);
		
		_sdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				sdb.addListenerForSingleValueEvent(new ValueEventListener() {
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
		sdb.addChildEventListener(_sdb_child_listener);
		
		_v_dio_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_v_dio_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_v_dio_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				cc = Calendar.getInstance();
				map_vid = new HashMap<>();
				map_vid.put("_date", String.valueOf((long)(cc.getTimeInMillis())));
				map_vid.put("vid", _downloadUrl);
				map_vid.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				sdb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map_vid);
				map.clear();
			}
		};
		
		_v_dio_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_v_dio_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_v_dio_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
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
		imageview2.setImageResource(R.drawable.home2);
		_database();
		last.edit().putString("pos", "0").commit();
		if (getIntent().hasExtra("BlockedB")) {
			if (getIntent().getStringExtra("BlockedB").equals("true")) {
				intent.setClass(getApplicationContext(), BlockActivity.class);
				startActivity(intent);
			}
			else {
				
			}
		}
		image_path = FileUtil.getExternalStorageDir().concat("/Insstory/Images/");
		_ViewPager_TabLayout();
		if (!FileUtil.isExistFile(image_path)) {
			FileUtil.makeDir(image_path);
		}
		frag1.setTabCount(1);
		viewpager1.setAdapter(frag1);
		frag2.setTabCount(1);
		viewpager2.setAdapter(frag2);
		pagr3.setTabCount(1);
		viewpager3.setAdapter(pagr3);
		last.edit().putString("pos", "0").commit();
		stories_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
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
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_HR:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
					}
				}
				ReelsPath = _filePath.get((int)(0));
				if (ReelsPath.endsWith(".mp4")) {
					intent.setClass(getApplicationContext(), ReelsViewerActivity.class);
					intent.putExtra("path", ReelsPath);
					startActivity(intent);
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), "invalid type");
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	public class Frag1FragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public Frag1FragmentAdapter(Context context, FragmentManager manager) {
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
				return new MessagesTabFragmentActivity();
			}
			return null;
		}
	}
	
	public class Frag2FragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public Frag2FragmentAdapter(Context context, FragmentManager manager) {
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
				return new GroupsTabFragmentActivity();
			}
			return null;
		}
	}
	
	public class Pagr3FragmentAdapter extends FragmentStatePagerAdapter {
		// This class is deprecated, you should migrate to ViewPager2:
		// https://developer.android.com/reference/androidx/viewpager2/widget/ViewPager2
		Context context;
		int tabCount;
		
		public Pagr3FragmentAdapter(Context context, FragmentManager manager) {
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
				return new MessageRequestsTabFragmentActivity();
			}
			return null;
		}
	}
	
	@Override
	public void onBackPressed() {
		_NewCustomDialog(ExitDialogTitle, ExitDialogMessage, YES, NO, true);
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		top_view.setBackgroundColor(0xFFFFFFFF);
		body.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFFF4511E);
		_ImageColor(add_post, "#000000");
		_ImageColor(notifications, "#000000");
		_ImageColor(shop_icon, "#000000");
		_ImageColor(search_icon, "#000000");
		posts_view.setLayoutManager(new LinearLayoutManager(this));
		_Language();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		_rippleRoundStroke(mystory_body, "#FFFFFF", "#EEEEEE", 5, 0, "#FFFFFF");
	}
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
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
		title.setText("Jimmy");
		YES = "Evet";
		NO = "Hayır";
		add_story_title = "Hikaye Ekle";
		my_story_title = "Hikayem";
		welcome = "Hoş Geldin!";
		TextPostSent = "Yazı Gönderisi Paylaşıldı!";
		likes = "Beğeni";
		vac = "Tüm Yorumları Gör";
		ExitDialogTitle = "BİLGİ";
		ExitDialogMessage = "Uygulamadan çıkmak istediğine emin misin?";
	}
	
	
	public void _EnLang() {
		title.setText("Jummy");
		YES = "Yes";
		NO = "No";
		add_story_title = "Add Story";
		my_story_title = "My Story";
		welcome = "Welcome!";
		TextPostSent = "Text Post Shared!";
		likes = "Likes";
		vac = "See All Comments";
		ExitDialogTitle = "INFO";
		ExitDialogMessage = "Are you sure you want to exit the application?";
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
	
	
	public void _edittext_mh(final TextView _txt) {
		final TextView regex1 = new TextView(this);
		
		regex1.setText("(?<![^\\s])(([@]{1}|[#]{1})([A-Za-z0-9_-]\\.?)+)(?![^\\s,])");
		final String mentionColor = "#2196F3";
		_txt.addTextChangedListener(new TextWatcher() {
			ColorScheme keywords1 = new ColorScheme(java.util.regex.Pattern.compile(regex1.getText().toString()), Color.parseColor(mentionColor));
			final ColorScheme[] schemes = {keywords1};
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				removeSpans(s, android.text.style.ForegroundColorSpan.class);
				for(ColorScheme scheme : schemes) {
					for(java.util.regex.Matcher m = scheme.pattern.matcher(s);
					m.find();) {
						s.setSpan(new android.text.style.ForegroundColorSpan(scheme.color), m.start(), m.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
						s.setSpan(new android.text.style.StyleSpan(Typeface.BOLD), m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						
					}
				}
			}
			void removeSpans(Editable e, Class type) {
				android.text.style.CharacterStyle[] spans = e.getSpans(0, e.length(), type);
				for (android.text.style.CharacterStyle span : spans) {
					e.removeSpan(span);
				}
			}
			class ColorScheme {
				final java.util.regex.Pattern pattern;
				final int color;
				ColorScheme(java.util.regex.Pattern pattern, int color) {
					this.pattern = pattern;
					this.color = color;
				}
			}
		});
	}
	
	
	public void _textview_mh(final TextView _txt, final String _value) {
		_txt.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());
		//_txt.setTextIsSelectable(true);
		updateSpan(_value, _txt);
	}
	private void updateSpan(String str, TextView _txt){
		SpannableString ssb = new SpannableString(str);
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("(?<![^\\s])(([@]{1}|[#]{1})([A-Za-z0-9_-]\\.?)+)(?![^\\s,])");
		    java.util.regex.Matcher matcher = pattern.matcher(str);
		while(matcher.find()){
			ProfileSpan span = new ProfileSpan();
			ssb.setSpan(span, matcher.start() , matcher. end() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		} 
		_txt.setText(ssb);
		
	}
	private class ProfileSpan extends android.text.style.ClickableSpan{
		
		
		@Override
		public void onClick(View view){
			
			if(view instanceof TextView){
				    TextView tv = (TextView)view;
				
				    if(tv.getText() instanceof Spannable){
					        Spannable sp = (Spannable)tv.getText();
					    
					        int start = sp.getSpanStart(this);
					        int end = sp.getSpanEnd(this);
					        object_clicked = sp.subSequence(start,end).toString();
					SketchwareUtil.showMessage(getApplicationContext(), object_clicked);
				}
			}
			
		}
		@Override
		    public void updateDrawState(TextPaint ds) {
			        ds.setUnderlineText(false);
			       
			ds.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
			    }
	}
	
	
	public void _setViewWidthAndHeight(final double _width, final double _height, final View _view) {
		width1 = SketchwareUtil.getDisplayWidthPixels(getApplicationContext()) * (_width / 100);
		height1 = SketchwareUtil.getDisplayHeightPixels(getApplicationContext()) * (_height / 100);
		_view.setLayoutParams(new LinearLayout.LayoutParams((int) width1,(int) height1));
	}
	
	
	public void _lib() {
	}
	private NewpostBottomdialogFragmentDialogFragmentActivity NewpostBottomdialogFragmentDialogFragmentActivityN;
	private FragmentManager NewpostBottomdialogFragmentDialogFragmentActivityFM;
	public void test_NewpostBottomdialogFragmentDialogFragmentActivity () {
	}
	
	
	public void _NewCustomDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog NewCustomDialog = new AlertDialog.Builder(DashboardActivity.this).create();
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
				finishAffinity();
				
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
		title.setText("Jimmy");
		YES = "Iya";
		NO = "Tidak";
		add_story_title = "Buat Cerita";
		my_story_title = "Cerita Saya";
		welcome = "Selamat Datang!";
		TextPostSent = "Postingan Dibagikan!";
		likes = "Suka";
		vac = "Lihat Semua Komentar";
		ExitDialogTitle = "INFO";
		ExitDialogMessage = "Apakah kamu yakin ingin meninggalkan aplikasi?";
	}
	
	
	public void _database() {
		friends.removeEventListener(_friends_child_listener);
		Dbfriends = "friends/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		friends = _firebase.getReference(Dbfriends);
		friends.addChildEventListener(_friends_child_listener);
	}
	
	
	public void _ViewPager_TabLayout() {
		timer = new TimerTask() {
				@Override
				public void run() {
						runOnUiThread(new Runnable() {
								@Override
								public void run() {
										tablayout.setElevation((float)5);
										
								}
						});
				}
		};
		_timer.schedule(timer, (int)(150));
		viewPager = new androidx.viewpager.widget.ViewPager(this); viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)); MyPagerAdapter adapter = new MyPagerAdapter(); viewPager.setAdapter(adapter); viewPager.setCurrentItem(0); 
		
		base.
		
		addView(viewPager); viewPager.addOnPageChangeListener(new androidx.viewpager.widget.ViewPager.OnPageChangeListener() { public void onPageSelected(int position) { position_number = position; 
				if (position_number == 0) {
					imageview2.setImageResource(R.drawable.home2);
					imageview3.setImageResource(R.drawable.video2_1);
					imageview4.setImageResource(R.drawable.video2_4);
					imageview5.setImageResource(R.drawable.video2_8);
					imageview6.setImageResource(R.drawable.friends_list_icons_4_2);
				}
				else {
					if (position_number == 1) {
						imageview2.setImageResource(R.drawable.video2_9);
						imageview3.setImageResource(R.drawable.video2_3);
						imageview4.setImageResource(R.drawable.video2_4);
						imageview5.setImageResource(R.drawable.video2_8);
						imageview6.setImageResource(R.drawable.friends_list_icons_4_2);
					}
					else {
						if (position_number == 2) {
							imageview2.setImageResource(R.drawable.video2_9);
							imageview3.setImageResource(R.drawable.video2_1);
							imageview4.setImageResource(R.drawable.comment2);
							imageview5.setImageResource(R.drawable.video2_8);
							imageview6.setImageResource(R.drawable.friends_list_icons_4_2);
						}
						else {
							if (position_number == 3) {
								imageview2.setImageResource(R.drawable.video2_9);
								imageview3.setImageResource(R.drawable.video2_1);
								imageview4.setImageResource(R.drawable.video2_4);
								imageview5.setImageResource(R.drawable.group2);
								imageview6.setImageResource(R.drawable.friends_list_icons_4_2);
							}
							else {
								if (position_number == 4) {
									imageview2.setImageResource(R.drawable.video2_9);
									imageview3.setImageResource(R.drawable.video2_1);
									imageview4.setImageResource(R.drawable.video2_4);
									imageview5.setImageResource(R.drawable.video2_8);
									imageview6.setImageResource(R.drawable.friends_list_icons_4_1);
								}
							}
						}
					}
				}
			} @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { } @Override public void onPageScrollStateChanged(int state) { } }); tabLayout = new com.google.android.material.tabs.TabLayout(this); tabLayout.setTabGravity(tabLayout.GRAVITY_FILL); tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF")); tabLayout.setTabTextColors(Color.parseColor("#B3CEFB"), Color.parseColor("#FFFFFF")); tabLayout.setupWithViewPager(viewPager); tablayout.addView(tabLayout); } private class MyPagerAdapter extends androidx.viewpager.widget.PagerAdapter { public int getCount() { return
			
			
			
			 5
			
			
			
			; } @Override public Object instantiateItem(ViewGroup collection, int position)
		
		
		 {
			
			LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflater.inflate(R.layout.empty, null);
			
			LinearLayout container = (LinearLayout) v.findViewById(R.id.linear1);
			
			
			
			if (position == 0) {
				ViewGroup parent = (ViewGroup) layout1.getParent();
				if (parent != null) {
					parent.removeView(layout1);
				}container.addView(layout1);}
			
			
			else if (position == 1) {
				ViewGroup parent = (ViewGroup) layout2.getParent();
				if (parent != null) {
					parent.removeView(layout2);
				}container.addView(layout2);}
			
			else if (position == 2) {
				ViewGroup parent = (ViewGroup) layout3.getParent();
				if (parent != null) {
					parent.removeView(layout3);
				}container.addView(layout3);}
			
			else if (position == 3) {
				ViewGroup parent = (ViewGroup) layout4.getParent();
				if (parent != null) {
					parent.removeView(layout4);
				}container.addView(layout4);}
			
			else if (position == 4) {
				ViewGroup parent = (ViewGroup) layout5.getParent();
				if (parent != null) {
					parent.removeView(layout5);
				}container.addView(layout5);}
			
			
			collection.addView(v, 0);
			return v;
		}
		@Override public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
			trash.addView((View) view);
		}
		@Override public CharSequence getPageTitle(int position) {
			switch (position) {
				
				default:
				return null;
				
			}
		}
		
		
		
		
		
		
		
		
		@Override public boolean isViewFromObject(View arg0, Object arg1) { return arg0 == ((View) arg1);} @Override public Parcelable saveState() { return null; } } androidx.viewpager.widget.ViewPager viewPager; com.google.android.material.tabs.TabLayout tabLayout; private void foo() {
		
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
						cc.setTimeInMillis((long)(_currentTime));
						_txt.setText(new SimpleDateFormat("dd MMM YYYY | HH:mm").format(cc.getTime()));
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
		Glide.with(DashboardActivity.this)
		        .load(_url)
		        .circleCrop()
		        .into(_img);
		
	}
	
	
	public void _Popup() {
		View popupView = getLayoutInflater().inflate(R.layout.custom2, null);
		final PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		LinearLayout ay = popupView.findViewById(R.id.bg);
		
		LinearLayout re = popupView.findViewById(R.id.re);
		
		LinearLayout ab = popupView.findViewById(R.id.ab);
		
		LinearLayout st = popupView.findViewById(R.id.st);
		
		
		LinearLayout rt = popupView.findViewById(R.id.rt);
		re.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				Intent hr_video = new Intent( Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI); startActivityForResult(hr_video, REQ_CD_HR);
				popup.dismiss();
			} });
		
		st.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				in.setClass(getApplicationContext(), CreatePostActivity.class);
				startActivity(in);
				popup.dismiss();
			} });
		
		rt.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				in.setClass(getApplicationContext(), ReportActivity.class);
				startActivity(in);
				popup.dismiss();
			} });
		
		ab.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				in.setClass(getApplicationContext(), AddstoryActivity.class);
				startActivity(in);
				popup.dismiss();
			} });
		
		popup.setAnimationStyle(android.R.style.Animation_Dialog);
		
		popup.showAsDropDown(add_post, 0,0);
		
		popup.setBackgroundDrawable(new BitmapDrawable());
	}
	
	public class Posts_viewAdapter extends RecyclerView.Adapter<Posts_viewAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Posts_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.posts_view, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout in_lay = _view.findViewById(R.id.in_lay);
			final androidx.cardview.widget.CardView main = _view.findViewById(R.id.main);
			final LinearLayout body = _view.findViewById(R.id.body);
			final LinearLayout top = _view.findViewById(R.id.top);
			final androidx.cardview.widget.CardView image_card = _view.findViewById(R.id.image_card);
			final LinearLayout bottom = _view.findViewById(R.id.bottom);
			final TextView like_count = _view.findViewById(R.id.like_count);
			final TextView post_desc = _view.findViewById(R.id.post_desc);
			final TextView viewallcomments = _view.findViewById(R.id.viewallcomments);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView more = _view.findViewById(R.id.more);
			final ImageView image = _view.findViewById(R.id.image);
			final ImageView like = _view.findViewById(R.id.like);
			final ImageView comments = _view.findViewById(R.id.comments);
			final ImageView share = _view.findViewById(R.id.share);
			final LinearLayout space = _view.findViewById(R.id.space);
			final ImageView save_post = _view.findViewById(R.id.save_post);
			
			_setViewWidthAndHeight(96, 50, image_card);
			username.setTextColor(0xFF000000);
			like_count.setTextColor(0xFF000000);
			post_desc.setTextColor(0xFF000000);
			_ImageColor(comments, "#000000");
			_ImageColor(share, "#000000");
			_ImageColor(save_post, "#000000");
			main.setCardBackgroundColor(0xFFFFFFFF);
			if (_data.get((int)_position).get("disable_comments").toString().equals("true")) {
				comments.setVisibility(View.GONE);
				viewallcomments.setVisibility(View.GONE);
			}
			else {
				comments.setVisibility(View.VISIBLE);
				viewallcomments.setVisibility(View.VISIBLE);
			}
			if (_data.get((int)_position).get("disable_save").toString().equals("true")) {
				save_post.setVisibility(View.GONE);
			}
			else {
				save_post.setVisibility(View.VISIBLE);
			}
			if (_data.get((int)_position).get("hide_like_count").toString().equals("true")) {
				like_count.setVisibility(View.GONE);
			}
			else {
				like_count.setVisibility(View.VISIBLE);
			}
			viewallcomments.setText(vac);
			Glide.with(getApplicationContext()).load(Uri.parse(UserProfile.get(_data.get((int)_position).get("uid").toString()).toString())).into(avatar);
			username.setText(UserName.get(_data.get((int)_position).get("uid").toString()).toString());
			if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("blue")) {
				verified.setImageResource(R.drawable.blue_verified);
				verified.setVisibility(View.VISIBLE);
			}
			else {
				if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("red")) {
					verified.setImageResource(R.drawable.red_verified);
					verified.setVisibility(View.VISIBLE);
				}
				else {
					if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("false")) {
						verified.setVisibility(View.GONE);
					}
				}
			}
			Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("post_image").toString())).into(image);
			_textview_mh(post_desc, " @".concat(UserName.get(_data.get((int)_position).get("uid").toString()).toString()).concat(" ".concat(_data.get((int)_position).get("desc").toString())));
			PostMap = PostsMap.get((int)_position);
			SketchwareUtil.getAllKeysFromMap(PostMap, PostKeys);
			n = 0;
			PostLikes = 0;
			for(int _repeat388 = 0; _repeat388 < (int)(PostKeys.size()); _repeat388++) {
				if (PostMap.get(PostKeys.get((int)(n))).toString().equals("liked")) {
					PostLikes++;
				}
				n++;
			}
			_setCount(like_count, PostLikes);
			if (PostMap.containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				if (PostMap.get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("liked")) {
					like.setImageResource(R.drawable.liked);
				}
				else {
					like.setImageResource(R.drawable.not_liked);
				}
			}
			else {
				like.setImageResource(R.drawable.not_liked);
			}
			like.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (PostsMap.get((int)_position).containsKey(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (PostsMap.get((int)_position).get(FirebaseAuth.getInstance().getCurrentUser().getUid()).toString().equals("liked")) {
							lmap = new HashMap<>();
							lmap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "unliked");
							pdb.child(PchildKeys.get((int)(_position))).updateChildren(lmap);
						}
						else {
							lmap = new HashMap<>();
							lmap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "liked");
							pdb.child(PchildKeys.get((int)(_position))).updateChildren(lmap);
						}
					}
					else {
						lmap = new HashMap<>();
						lmap.put(FirebaseAuth.getInstance().getCurrentUser().getUid(), "liked");
						pdb.child(PchildKeys.get((int)(_position))).updateChildren(lmap);
					}
				}
			});
			comments.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), PostCommentsActivity.class);
					intent.putExtra("key", PchildKeys.get((int)(_position)));
					startActivity(intent);
				}
			});
			viewallcomments.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), PostCommentsActivity.class);
					intent.putExtra("key", PchildKeys.get((int)(_position)));
					startActivity(intent);
				}
			});
			save_post.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (_data.get((int)_position).containsKey("post_image")) {
						_firebase_storage.getReferenceFromUrl(_data.get((int)_position).get("post_image").toString()).getFile(new File(image_path.concat("Images".concat(Uri.parse(Uri.parse(_data.get((int)_position).get("post_image").toString()).getLastPathSegment()).getLastPathSegment())))).addOnSuccessListener(_psd_download_success_listener).addOnFailureListener(_psd_failure_listener).addOnProgressListener(_psd_download_progress_listener);
						SketchwareUtil.showMessage(getApplicationContext(), "image save into Jimmy Images Folder ✔✔");
						_ImageColor(save_post, "#673AB7");
					}
				}
			});
			top.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), ProfileActivity.class);
					intent.putExtra("uid", _data.get((int)_position).get("uid").toString());
					startActivity(intent);
				}
			});
			share.setVisibility(View.GONE);
			more.setVisibility(View.GONE);
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
			Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("image").toString())).into(profile_image);
			username.setText(UserName.get(_data.get((int)_position).get("uid").toString()).toString());
			if (UserStory.get(_data.get((int)_position).get("uid").toString()).toString().equals("true")) {
				profile_image.setBackgroundResource(R.drawable.story_profile);
				profile_image.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setClass(getApplicationContext(), StoryviewActivity.class);
						intent.putExtra("uid", _data.get((int)_position).get("uid").toString());
						startActivity(intent);
					}
				});
			}
			else {
				
			}
			body.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					intent.setClass(getApplicationContext(), ProfileActivity.class);
					intent.putExtra("uid", _data.get((int)_position).get("uid").toString());
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
	
	public class Recyclerview2Adapter extends RecyclerView.Adapter<Recyclerview2Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview2Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getLayoutInflater();
			View _v = _inflater.inflate(R.layout.vid_view, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final LinearLayout in_lay = _view.findViewById(R.id.in_lay);
			final androidx.cardview.widget.CardView main = _view.findViewById(R.id.main);
			final LinearLayout body = _view.findViewById(R.id.body);
			final LinearLayout top = _view.findViewById(R.id.top);
			final androidx.cardview.widget.CardView image_card = _view.findViewById(R.id.image_card);
			final LinearLayout bottom = _view.findViewById(R.id.bottom);
			final TextView like_count = _view.findViewById(R.id.like_count);
			final TextView post_desc = _view.findViewById(R.id.post_desc);
			final TextView viewallcomments = _view.findViewById(R.id.viewallcomments);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final ImageView more = _view.findViewById(R.id.more);
			final ImageView image = _view.findViewById(R.id.image);
			final ImageView like = _view.findViewById(R.id.like);
			final ImageView comments = _view.findViewById(R.id.comments);
			final ImageView share = _view.findViewById(R.id.share);
			final LinearLayout space = _view.findViewById(R.id.space);
			final ImageView save_post = _view.findViewById(R.id.save_post);
			
			username.setText(UserName.get(_data.get((int)_position).get("uid").toString()).toString());
			post_desc.setText(_data.get((int)_position).get("_data").toString());
			Glide.with(getApplicationContext()).load(Uri.parse(UserProfile.get(_data.get((int)_position).get("uid").toString()).toString())).into(avatar);
			Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("vid").toString())).into(image);
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