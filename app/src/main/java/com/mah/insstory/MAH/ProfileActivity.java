package com.mah.insstory.MAH;

import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
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
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.*;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.aghajari.emojiview.*;
import com.bumptech.glide.Glide;
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
import de.hdodenhof.circleimageview.*;
import java.io.*;
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

public class ProfileActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String key = "";
	private String uid = "";
	private HashMap<String, Object> m1 = new HashMap<>();
	private HashMap<String, Object> m2 = new HashMap<>();
	private String user_key = "";
	private String fontName = "";
	private String typeace = "";
	private String username_ = "";
	private String avatar_ = "";
	private String idkey_ = "";
	private String profile_dialog = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String ReportKey = "";
	private String notifying = "";
	private String NowAvatat = "";
	private boolean AddFriend = false;
	private boolean FriendRequest = false;
	private String FriendUid = "";
	private String rquid1 = "";
	private HashMap<String, Object> map1 = new HashMap<>();
	private String AddFriendDBGet = "";
	private String SentString = "";
	private String ComingRequests = "";
	private String frMy_ = "";
	private String irMy_ = "";
	private String frsMy_ = "";
	private String UserName_ = "";
	private String UserVerify_ = "";
	private String UserAvatar_ = "";
	private boolean RequestAccept = false;
	private String verify = "";
	private String online_ = "";
	private String blocked_ = "";
	private double tm_difference = 0;
	private String GenderTitle = "";
	private String OnlineStatus = "";
	private String OfflineStatus = "";
	private String ReportDialogTitle = "";
	private String ReportDialogSubtext = "";
	private String ReportDialogCancel = "";
	private String ReportDialogReport = "";
	private String ReportDialogMoreText = "";
	private String ReportDialogCheck1 = "";
	private String ReportDialogCheck2 = "";
	private String ReportDialogCheck3 = "";
	private String ReportDialogWarning1 = "";
	private String ReportDialogWarning2 = "";
	private String ToastMessageText = "";
	private String ToastRequestText = "";
	private String RequestSentText = "";
	private String AddFriendText = "";
	private String AcceptRequestText = "";
	private String UnfriendText = "";
	private String SetTimeAsecond = "";
	private String SetTimeSecond = "";
	private String SetTimeAMinute = "";
	private String SetTimeMinute = "";
	private String SetTimeHours = "";
	private String SetTimeDays = "";
	private String AddStoryTitle = "";
	private String ReportTitle = "";
	private String SettingsTitle = "";
	private String AgeTitle = "";
	private String JoinDateTitle = "";
	private String FemaleText = "";
	private String MaleText = "";
	private String fcount = "";
	private double FcountN = 0;
	private double PcountN = 0;
	private String urlcopied = "";
	private String ProfileUrlTitle = "";
	private HashMap<String, Object> PostMap = new HashMap<>();
	private double n = 0;
	private double PostLikes = 0;
	private HashMap<String, Object> lmap = new HashMap<>();
	private String YES = "";
	private String NO = "";
	private String UnfriendDialogTitle = "";
	private String UnfriendDialogMessage = "";
	private String object_clicked = "";
	private HashMap<String, Object> UserVerified = new HashMap<>();
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> UserProfile = new HashMap<>();
	private String vac = "";
	private double width1 = 0;
	private double height1 = 0;
	private String likes = "";
	private double p = 0;
	private double f = 0;
	private boolean PrivateAccount = false;
	
	private ArrayList<HashMap<String, Object>> post = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> img_post = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> PostsMap = new ArrayList<>();
	private ArrayList<String> PostKeys = new ArrayList<>();
	private ArrayList<String> pchildkeys = new ArrayList<>();
	
	private LinearLayout body;
	private CardView top;
	private NestedScrollView nestedscroll;
	private LinearLayout top_;
	private ImageView back;
	private TextView title;
	private ImageView verified;
	private LinearLayout linear51;
	private ImageView manage_user;
	private ImageView menu;
	private LinearLayout nestedbody;
	private LinearLayout more_tools;
	private LinearLayout linear48;
	private LinearLayout buttons;
	private TextView edit_profile;
	private LinearLayout posts_category_tab;
	private GridView image_post_view;
	private RecyclerView recyclerview1;
	private LinearLayout private_account;
	private LinearLayout profile_story_bg;
	private LinearLayout linear52;
	private LinearLayout avatar_lay;
	private LinearLayout status_body;
	private CircleImageView profile_image;
	private LinearLayout status_icon;
	private LinearLayout info;
	private LinearLayout posts;
	private LinearLayout space;
	private LinearLayout friends;
	private TextView posts_title;
	private TextView posts_count;
	private TextView friends_title;
	private TextView friends_count;
	private TextView yourname;
	private TextView bio_text;
	private LinearLayout linear53;
	private TextView textview3;
	private ImageView imageview2;
	private TextView Device;
	private TextView add_friend_button;
	private TextView message_button;
	private LinearLayout posts_grid;
	private LinearLayout posts_list;
	private ImageView posts_grid_icon;
	private ImageView posts_list_icon;
	private ImageView private_account_icon;
	private TextView private_account_title;
	private TextView private_account_subtext;
	
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
	
	private AlertDialog.Builder dialog;
	private Intent i = new Intent();
	private SharedPreferences lang;
	private DatabaseReference ReportUserDBAdmin = _firebase.getReference("admin/reports/users");
	private ChildEventListener _ReportUserDBAdmin_child_listener;
	private DatabaseReference friendDb = _firebase.getReference("friends");
	private ChildEventListener _friendDb_child_listener;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference sdb = _firebase.getReference("social/stories");
	private ChildEventListener _sdb_child_listener;
	private SharedPreferences save;
	private DatabaseReference fr = _firebase.getReference("frequest");
	private ChildEventListener _fr_child_listener;
	private DatabaseReference ir = _firebase.getReference("irequests");
	private ChildEventListener _ir_child_listener;
	private DatabaseReference frs = _firebase.getReference("friends");
	private ChildEventListener _frs_child_listener;
	private DatabaseReference frMy = _firebase.getReference("frequest");
	private ChildEventListener _frMy_child_listener;
	private DatabaseReference irMy = _firebase.getReference("irequests");
	private ChildEventListener _irMy_child_listener;
	private DatabaseReference frsMy = _firebase.getReference("friends");
	private ChildEventListener _frsMy_child_listener;
	private Intent intent = new Intent();
	private ObjectAnimator o = new ObjectAnimator();
	private TimerTask poPup;
	private Calendar ct = Calendar.getInstance();
	private Calendar time2 = Calendar.getInstance();
	private DatabaseReference fc = _firebase.getReference("friends");
	private ChildEventListener _fc_child_listener;
	private DatabaseReference pdb = _firebase.getReference("posts");
	private ChildEventListener _pdb_child_listener;
	private SharedPreferences last;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top = findViewById(R.id.top);
		nestedscroll = findViewById(R.id.nestedscroll);
		top_ = findViewById(R.id.top_);
		back = findViewById(R.id.back);
		title = findViewById(R.id.title);
		verified = findViewById(R.id.verified);
		linear51 = findViewById(R.id.linear51);
		manage_user = findViewById(R.id.manage_user);
		menu = findViewById(R.id.menu);
		nestedbody = findViewById(R.id.nestedbody);
		more_tools = findViewById(R.id.more_tools);
		linear48 = findViewById(R.id.linear48);
		buttons = findViewById(R.id.buttons);
		edit_profile = findViewById(R.id.edit_profile);
		posts_category_tab = findViewById(R.id.posts_category_tab);
		image_post_view = findViewById(R.id.image_post_view);
		recyclerview1 = findViewById(R.id.recyclerview1);
		private_account = findViewById(R.id.private_account);
		profile_story_bg = findViewById(R.id.profile_story_bg);
		linear52 = findViewById(R.id.linear52);
		avatar_lay = findViewById(R.id.avatar_lay);
		status_body = findViewById(R.id.status_body);
		profile_image = findViewById(R.id.profile_image);
		status_icon = findViewById(R.id.status_icon);
		info = findViewById(R.id.info);
		posts = findViewById(R.id.posts);
		space = findViewById(R.id.space);
		friends = findViewById(R.id.friends);
		posts_title = findViewById(R.id.posts_title);
		posts_count = findViewById(R.id.posts_count);
		friends_title = findViewById(R.id.friends_title);
		friends_count = findViewById(R.id.friends_count);
		yourname = findViewById(R.id.yourname);
		bio_text = findViewById(R.id.bio_text);
		linear53 = findViewById(R.id.linear53);
		textview3 = findViewById(R.id.textview3);
		imageview2 = findViewById(R.id.imageview2);
		Device = findViewById(R.id.Device);
		add_friend_button = findViewById(R.id.add_friend_button);
		message_button = findViewById(R.id.message_button);
		posts_grid = findViewById(R.id.posts_grid);
		posts_list = findViewById(R.id.posts_list);
		posts_grid_icon = findViewById(R.id.posts_grid_icon);
		posts_list_icon = findViewById(R.id.posts_list_icon);
		private_account_icon = findViewById(R.id.private_account_icon);
		private_account_title = findViewById(R.id.private_account_title);
		private_account_subtext = findViewById(R.id.private_account_subtext);
		auth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		last = getSharedPreferences("lastpos", Activity.MODE_PRIVATE);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		manage_user.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ManageUserBottomdialogFragmentDialogFragmentActivityN = new ManageUserBottomdialogFragmentDialogFragmentActivity();
				ManageUserBottomdialogFragmentDialogFragmentActivityN.show(getSupportFragmentManager(),"1");
			}
		});
		
		menu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog ProfileMenu = new com.google.android.material.bottomsheet.BottomSheetDialog(ProfileActivity.this);
				View ProfileMenuV = getLayoutInflater().inflate(R.layout.profile_menu, null);
				ProfileMenu.setContentView(ProfileMenuV);
				
				final LinearLayout linear  = (LinearLayout)ProfileMenuV.findViewById(R.id.body);
				
				ProfileMenu.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				
				ProfileMenu.show();
				android.graphics.drawable.GradientDrawable Pmdg = new android.graphics.drawable.GradientDrawable();
				int dc = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				Pmdg.setColor(0xFFFFFFFF);
				Pmdg.setCornerRadii(new float[]{
					dc*15,dc*15,dc*15 ,dc*15,dc*0,dc*0 ,dc*0,dc*0});
				linear.setElevation(dc*5);
				android.graphics.drawable.RippleDrawable ProfileMenuDG = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), Pmdg, null);
				linear.setBackground(ProfileMenuDG);
				LinearLayout slider = (LinearLayout) ProfileMenuV.findViewById(R.id.slider);
				LinearLayout report_lay = (LinearLayout) ProfileMenuV.findViewById(R.id.report_lay);
				LinearLayout settings_lay = (LinearLayout) ProfileMenuV.findViewById(R.id.settings_lay);
				LinearLayout profileurl_lay = (LinearLayout) ProfileMenuV.findViewById(R.id.profileurl_lay);
				TextView report_title = (TextView) ProfileMenuV.findViewById(R.id.report_title);
				TextView settings_title = (TextView) ProfileMenuV.findViewById(R.id.settings_title);
				TextView profileurl_title = (TextView) ProfileMenuV.findViewById(R.id.profileurl_title);
				report_title.setText(ReportTitle);
				settings_title.setText(SettingsTitle);
				profileurl_title.setText(ProfileUrlTitle);
				slider.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF424242));
				if (getIntent().getStringExtra("uid").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					settings_lay.setVisibility(View.VISIBLE);
					report_lay.setVisibility(View.GONE);
				}
				else {
					settings_lay.setVisibility(View.VISIBLE);
					report_lay.setVisibility(View.VISIBLE);
				}
				settings_lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						intent.setClass(getApplicationContext(), SettingsActivity.class);
						intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
						startActivity(intent);
						ProfileMenu.dismiss();
					}
				});
				profileurl_lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "https://ag.me/?type=user&id=".concat(getIntent().getStringExtra("uid"))));
						SketchwareUtil.showMessage(getApplicationContext(), urlcopied);
						ProfileMenu.dismiss();
					}
				});
				report_lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						save.edit().putString("LastReportUid", getIntent().getStringExtra("uid")).commit();
						ReportUserBottomdialogFragmentDialogFragmentActivityN = new ReportUserBottomdialogFragmentDialogFragmentActivity();
						ReportUserBottomdialogFragmentDialogFragmentActivityN.show(getSupportFragmentManager(),"1");
					}
				});
			}
		});
		
		edit_profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				ProfileEditBottomdialogFragmentDialogFragmentActivityN = new ProfileEditBottomdialogFragmentDialogFragmentActivity();
				ProfileEditBottomdialogFragmentDialogFragmentActivityN.show(getSupportFragmentManager(),"1");
			}
		});
		
		add_friend_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (RequestAccept) {
					map = new HashMap<>();
					map.put("sender", getIntent().getStringExtra("uid"));
					map.put("reciever", FirebaseAuth.getInstance().getCurrentUser().getUid());
					frs.child(getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).updateChildren(map);
					map.clear();
					map = new HashMap<>();
					map.put("sender", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("reciever", getIntent().getStringExtra("uid"));
					frs.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")))).updateChildren(map);
					map.clear();
					ir.child(getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
					ir.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")))).removeValue();
					fr.child(getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
					fr.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")))).removeValue();
				}
				else {
					if (AddFriend) {
						_NewCustomDialog(UnfriendDialogTitle, UnfriendDialogMessage, YES, NO, true);
					}
					else {
						if (FriendRequest) {
							ir.child(getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
							ir.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")))).removeValue();
							fr.child(getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
							fr.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")))).removeValue();
						}
						else {
							map = new HashMap<>();
							map.put("sender", getIntent().getStringExtra("uid"));
							map.put("reciever", FirebaseAuth.getInstance().getCurrentUser().getUid());
							ir.child(getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).updateChildren(map);
							map.clear();
							map = new HashMap<>();
							map.put("sender", FirebaseAuth.getInstance().getCurrentUser().getUid());
							map.put("reciever", getIntent().getStringExtra("uid"));
							fr.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")))).updateChildren(map);
							map.clear();
						}
					}
				}
			}
		});
		
		message_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), ChatActivity.class);
				i.putExtra("seconduser", getIntent().getStringExtra("uid"));
				i.putExtra("firstuser", FirebaseAuth.getInstance().getCurrentUser().getUid());
				startActivity(i);
			}
		});
		
		posts_grid.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				recyclerview1.setVisibility(View.GONE);
				image_post_view.setVisibility(View.VISIBLE);
				posts_grid_icon.setImageResource(R.drawable.grid_filled);
				posts_list_icon.setImageResource(R.drawable.posts_list_outline);
			}
		});
		
		posts_list.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				recyclerview1.setVisibility(View.VISIBLE);
				image_post_view.setVisibility(View.GONE);
				posts_grid_icon.setImageResource(R.drawable.grid_outline);
				posts_list_icon.setImageResource(R.drawable.posts_list_filled);
			}
		});
		
		_ReportUserDBAdmin_child_listener = new ChildEventListener() {
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
		ReportUserDBAdmin.addChildEventListener(_ReportUserDBAdmin_child_listener);
		
		_friendDb_child_listener = new ChildEventListener() {
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
		friendDb.addChildEventListener(_friendDb_child_listener);
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
					if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (_childValue.get("account_type").toString().equals("admin")) {
							manage_user.setVisibility(View.VISIBLE);
						}
						else {
							manage_user.setVisibility(View.GONE);
						}
						if (_childValue.containsKey("device")) {
							Device.setText(_childValue.get("device").toString());
						}
						else {
							map = new HashMap<>();
							map.put("device", Build.MANUFACTURER.concat(" ".concat(Build.MODEL)));
							udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
							map.clear();
						}
					}
				}
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
						if (_childValue.containsKey("device")) {
							Device.setText(_childValue.get("device").toString());
						}
						else {
							Device.setVisibility(View.GONE);
						}
						verify = _childValue.get("verify").toString();
						online_ = _childValue.get("online").toString();
						blocked_ = _childValue.get("blocked").toString();
						Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(profile_image);
						title.setText(_childValue.get("username").toString());
						yourname.setText(_childValue.get("yourname").toString());
						bio_text.setText(_childValue.get("bio").toString());
						UserName_ = _childValue.get("username").toString();
						UserVerify_ = _childValue.get("verify").toString();
						UserAvatar_ = _childValue.get("avatar").toString();
						if (_childValue.containsKey("uid") && _childValue.containsKey("username")) {
							UserName.put(_childValue.get("uid").toString(), _childValue.get("username").toString());
						}
						if (_childValue.containsKey("uid") && _childValue.containsKey("verify")) {
							UserVerified.put(_childValue.get("uid").toString(), _childValue.get("verify").toString());
						}
						if (_childValue.containsKey("uid") && _childValue.containsKey("avatar")) {
							UserProfile.put(_childValue.get("uid").toString(), _childValue.get("avatar").toString());
						}
						if (getIntent().getStringExtra("uid").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							friends.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									i.setClass(getApplicationContext(), FriendsActivity.class);
									startActivity(i);
								}
							});
							private_account.setVisibility(View.GONE);
							edit_profile.setVisibility(View.VISIBLE);
							menu.setVisibility(View.VISIBLE);
							buttons.setVisibility(View.GONE);
							back.setVisibility(View.GONE);
						}
						else {
							buttons.setVisibility(View.VISIBLE);
							back.setVisibility(View.VISIBLE);
							edit_profile.setVisibility(View.GONE);
							if (_childValue.get("private_account").toString().equals("true")) {
								PrivateAccount = true;
								private_account.setVisibility(View.VISIBLE);
								image_post_view.setVisibility(View.GONE);
								posts_category_tab.setVisibility(View.GONE);
								recyclerview1.setVisibility(View.GONE);
							}
							else {
								PrivateAccount = false;
								private_account.setVisibility(View.GONE);
								image_post_view.setVisibility(View.VISIBLE);
								message_button.setVisibility(View.VISIBLE);
								posts_category_tab.setVisibility(View.VISIBLE);
								recyclerview1.setVisibility(View.GONE);
							}
						}
					}
					else {
						buttons.setVisibility(View.VISIBLE);
						back.setVisibility(View.VISIBLE);
						edit_profile.setVisibility(View.GONE);
						if (_childValue.get("private_account").toString().equals("true")) {
							PrivateAccount = true;
							private_account.setVisibility(View.VISIBLE);
							image_post_view.setVisibility(View.GONE);
							posts_category_tab.setVisibility(View.GONE);
							message_button.setVisibility(View.GONE);
						}
						else {
							PrivateAccount = false;
							private_account.setVisibility(View.GONE);
							image_post_view.setVisibility(View.VISIBLE);
							message_button.setVisibility(View.VISIBLE);
							posts_category_tab.setVisibility(View.VISIBLE);
						}
					}
					_ProfileOnChildBlocks();
					if (_childValue.get("story").toString().equals("true")) {
						profile_image.setBackgroundResource(R.drawable.story_profile);
						profile_image.setOnClickListener(new View.OnClickListener() {
							@Override
							public void onClick(View _view) {
								intent.putExtra("uid", getIntent().getStringExtra("uid"));
								intent.setClass(getApplicationContext(), StoryviewActivity.class);
							}
						});
					}
					else {
						if (_childValue.get("story").toString().equals("false")) {
							profile_image.setBackgroundResource(0);
							profile_image.setOnClickListener(new View.OnClickListener() {
								@Override
								public void onClick(View _view) {
									_ProfileImageFull(_childValue.get("avatar").toString());
								}
							});
						}
					}
					if (_childValue.containsKey("title_job")) {
						textview3.setVisibility(View.VISIBLE);
						textview3.setText("⭐ ".concat(_childValue.get("title_job").toString()));
					}
					else {
						textview3.setVisibility(View.GONE);
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					if (getIntent().getStringExtra("uid").equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						if (_childValue.get("blocked").toString().equals("true")) {
							intent.setClass(getApplicationContext(), BlockActivity.class);
							startActivity(intent);
							if (_childValue.get("online").toString().equals("true")) {
								status_body.setVisibility(View.VISIBLE);
							}
							else {
								if (_childValue.get("online").toString().equals("false")) {
									status_body.setVisibility(View.GONE);
								}
								else {
									status_body.setVisibility(View.GONE);
								}
							}
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
		
		_sdb_child_listener = new ChildEventListener() {
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
		sdb.addChildEventListener(_sdb_child_listener);
		
		_fr_child_listener = new ChildEventListener() {
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
		fr.addChildEventListener(_fr_child_listener);
		
		_ir_child_listener = new ChildEventListener() {
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
		ir.addChildEventListener(_ir_child_listener);
		
		_frs_child_listener = new ChildEventListener() {
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
		frs.addChildEventListener(_frs_child_listener);
		
		_frMy_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					add_friend_button.setText(RequestSentText);
					AddFriend = false;
					FriendRequest = true;
					_rippleRoundStroke(add_friend_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
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
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					add_friend_button.setText(AddFriendText);
					AddFriend = false;
					FriendRequest = false;
					_rippleRoundStroke(add_friend_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		frMy.addChildEventListener(_frMy_child_listener);
		
		_irMy_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					RequestAccept = true;
					add_friend_button.setText(AcceptRequestText);
					_rippleRoundStroke(add_friend_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
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
		irMy.addChildEventListener(_irMy_child_listener);
		
		_frsMy_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					add_friend_button.setText(UnfriendText);
					AddFriend = true;
					FriendRequest = false;
					_rippleRoundStroke(add_friend_button, "#FF1744", "#F44336", 6, 0, "#FFFFFF");
					if (PrivateAccount) {
						image_post_view.setVisibility(View.VISIBLE);
						message_button.setVisibility(View.VISIBLE);
						private_account.setVisibility(View.GONE);
					}
					else {
						image_post_view.setVisibility(View.VISIBLE);
						message_button.setVisibility(View.VISIBLE);
						private_account.setVisibility(View.GONE);
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
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					add_friend_button.setText(AddFriendText);
					AddFriend = false;
					FriendRequest = false;
					_rippleRoundStroke(add_friend_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		frsMy.addChildEventListener(_frsMy_child_listener);
		
		_fc_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				f++;
				friends_count.setText(String.valueOf((long)(f)));
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
		fc.addChildEventListener(_fc_child_listener);
		
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
						if (_childValue.get("post_key").toString().equals(_childValue.get("post_key").toString()) && _childValue.get("uid").toString().equals(getIntent().getStringExtra("uid"))) {
							pchildkeys.add(_childKey);
							p++;
							posts_count.setText(String.valueOf((long)(p)));
							img_post.add(_childValue);
							image_post_view.setAdapter(new Image_post_viewAdapter(img_post));
							recyclerview1.setAdapter(new Recyclerview1Adapter(img_post));
						}
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
						if (_childValue.get("post_key").toString().equals(_childValue.get("post_key").toString()) && _childValue.get("uid").toString().equals(getIntent().getStringExtra("uid"))) {
							img_post.add(_childValue);
							image_post_view.setAdapter(new Image_post_viewAdapter(img_post));
							recyclerview1.setAdapter(new Recyclerview1Adapter(PostsMap));
						}
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
		save.edit().putString("LastUid", getIntent().getStringExtra("uid")).commit();
		last.edit().putString("pos", "0").commit();
		_Databases();
		recyclerview1.setLayoutManager(new LinearLayoutManager(this));
		manage_user.setVisibility(View.GONE);
		back.setVisibility(View.GONE);
		status_body.setVisibility(View.GONE);
		edit_profile.setVisibility(View.GONE);
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		yourname.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
		_ImageColor(menu, "#000000");
		_ImageColor(back, "#000000");
		body.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		yourname.setTextColor(0xFF000000);
		bio_text.setTextColor(0xFF000000);
		top_.setBackgroundColor(0xFFFFFFFF);
		posts_title.setTextColor(0xFF000000);
		friends_title.setTextColor(0xFF000000);
		posts_count.setTextColor(0xFF607D8B);
		friends_count.setTextColor(0xFF607D8B);
		status_body.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		status_icon.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF4CAF50));
		_rippleRoundStroke(edit_profile, "#FFFFFF", "#CFD8DC", 8, 2, "#CFD8DC");
		_rippleRoundStroke(add_friend_button, "#651FFF", "#673AB7", 6, 0, "#FFFFFF");
		_rippleRoundStroke(message_button, "#FFFFFF", "#CFD8DC", 6, 2, "#CFD8DC");
		SentString = FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")));
		ComingRequests = getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()));
		AddFriend = false;
		FriendRequest = false;
		RequestAccept = false;
		recyclerview1.setVisibility(View.GONE);
		image_post_view.setVisibility(View.VISIBLE);
		posts_grid_icon.setImageResource(R.drawable.grid_filled);
		posts_list_icon.setImageResource(R.drawable.posts_list_outline);
		posts_list.setVisibility(View.GONE);
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
	
	
	public void _ProfileImageFull(final String _avatar) {
		final AlertDialog profile_img_dialog = new AlertDialog.Builder(ProfileActivity.this).create();
		LayoutInflater profile_img_dialogLI = getLayoutInflater();
		View profile_img_dialogCV = (View) profile_img_dialogLI.inflate(R.layout.profile_photo_dialog_view, null);
		profile_img_dialog.setView(profile_img_dialogCV);
		profile_img_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		final ImageView avatar = (ImageView)
		profile_img_dialogCV.findViewById(R.id.avatar);
		Glide.with(getApplicationContext()).load(Uri.parse(_avatar)).into(avatar);
		if (save.getString("theme", "").equals("light")) {
			avatar.setBackgroundColor(0xFFFFFFFF);
		}
		else {
			if (save.getString("theme", "").equals("dark")) {
				avatar.setBackgroundColor(0xFF000000);
			}
		}
		profile_img_dialog.setCancelable(true);
		profile_img_dialog.show();
	}
	
	
	public void _Databases() {
		frMy.removeEventListener(_frMy_child_listener);
		frMy_ = "frequest/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		frMy = _firebase.getReference(frMy_);
		frMy.addChildEventListener(_frMy_child_listener);
		irMy.removeEventListener(_irMy_child_listener);
		irMy_ = "irequests/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		irMy = _firebase.getReference(irMy_);
		irMy.addChildEventListener(_irMy_child_listener);
		frsMy.removeEventListener(_frsMy_child_listener);
		frsMy_ = "friends/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		frsMy = _firebase.getReference(frsMy_);
		frsMy.addChildEventListener(_frsMy_child_listener);
		fc.removeEventListener(_fc_child_listener);
		fcount = "friends/".concat(getIntent().getStringExtra("uid"));
		fc = _firebase.getReference(fcount);
		fc.addChildEventListener(_fc_child_listener);
	}
	
	
	public void _ProfileOnChildBlocks() {
		if (online_.equals("true")) {
			status_body.setVisibility(View.VISIBLE);
		}
		else {
			if (online_.equals("false")) {
				status_body.setVisibility(View.GONE);
			}
			else {
				status_body.setVisibility(View.GONE);
			}
		}
		if (verify.equals("blue")) {
			verified.setImageResource(R.drawable.blue_verified);
			verified.setVisibility(View.VISIBLE);
		}
		else {
			if (verify.equals("red")) {
				verified.setImageResource(R.drawable.red_verified);
				verified.setVisibility(View.VISIBLE);
			}
			else {
				if (verify.equals("false")) {
					verified.setVisibility(View.GONE);
				}
			}
		}
	}
	
	
	public void _TrLang() {
		message_button.setText("Mesaj Yaz");
		posts_title.setText("Gönderiler");
		friends_title.setText("Arkadaşlar");
		edit_profile.setText("Profili Düzenle");
		add_friend_button.setText("Arkadaş Ekle");
		YES = "Evet";
		NO = "Hayır";
		OnlineStatus = "Şu an aktif";
		OfflineStatus = "Çevrimdışı";
		RequestSentText = "İstek Gönderildi";
		AddFriendText = "Arkadaş Ekle";
		AcceptRequestText = "İsteği Onayla";
		UnfriendText = "Arkadaşlıktan Çıkar";
		ReportTitle = "Bildir";
		SettingsTitle = "Ayarlar";
		urlcopied = "Profil Bağlantısı Kopyalandı!";
		ProfileUrlTitle = "Profili Paylaş";
		UnfriendDialogTitle = "BİLGİ";
		UnfriendDialogMessage = "Bu kullanıcıyı arkadaşlıktan çıkarmak istediğine emin misin?";
	}
	
	
	public void _EnLang() {
		message_button.setText("Messaging");
		add_friend_button.setText("Add Friend");
		posts_title.setText("Posts");
		friends_title.setText("Friends");
		edit_profile.setText("Edit Profile");
		YES = "Yes";
		NO = "No";
		OnlineStatus = "Active now";
		OfflineStatus = "Offline";
		RequestSentText = "Request Sent";
		AddFriendText = "Add Friend";
		AcceptRequestText = "Accept Request";
		UnfriendText = "Unfriend";
		ReportTitle = "Report";
		SettingsTitle = "Settings";
		urlcopied = "Profile Link Copied!";
		ProfileUrlTitle = "Share Profile";
		UnfriendDialogTitle = "INFO";
		UnfriendDialogMessage = "Are you sure you want to unfriend this user?";
		likes = "Likes";
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
	
	
	public void _lib() {
	}
	private ManageUserBottomdialogFragmentDialogFragmentActivity ManageUserBottomdialogFragmentDialogFragmentActivityN;
	private FragmentManager ManageUserBottomdialogFragmentDialogFragmentActivityFM;
	public void test_ManageUserBottomdialogFragmentDialogFragmentActivity () {
	}
	private ProfileEditBottomdialogFragmentDialogFragmentActivity ProfileEditBottomdialogFragmentDialogFragmentActivityN;
	private FragmentManager ProfileEditBottomdialogFragmentDialogFragmentActivityFM;
	public void test_ProfileEditBottomdialogFragmentDialogFragmentActivity () {
	}
	private ReportUserBottomdialogFragmentDialogFragmentActivity ReportUserBottomdialogFragmentDialogFragmentActivityN;
	private FragmentManager ReportUserBottomdialogFragmentDialogFragmentActivityFM;
	public void test_ReportUserBottomdialogFragmentDialogFragmentActivity () {
	}
	
	
	public void _NewCustomDialog(final String _Title, final String _Message, final String _YesButtonText, final String _NoButtonText, final boolean _MultiButton) {
		final AlertDialog NewCustomDialog = new AlertDialog.Builder(ProfileActivity.this).create();
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
				frs.child(getIntent().getStringExtra("uid").concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).removeValue();
				frs.child(FirebaseAuth.getInstance().getCurrentUser().getUid().concat("/".concat(getIntent().getStringExtra("uid")))).removeValue();
				NewCustomDialog.dismiss();
				
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
		message_button.setText("Kirim Pesan");
		add_friend_button.setText("Tambahkan Teman");
		posts_title.setText("Postingan");
		friends_title.setText("Teman");
		edit_profile.setText("Edit Profil");
		YES = "Iya";
		NO = "Tidak";
		OnlineStatus = "Sedang Aktif";
		OfflineStatus = "Offline";
		RequestSentText = "Permintaan Dikirim";
		AddFriendText = "Tambahkan Teman";
		AcceptRequestText = "Permintaan Diterima";
		UnfriendText = "Batalkan Teman";
		ReportTitle = "Laporkan";
		SettingsTitle = "Pengaturan";
		urlcopied = "Link Profil Disalin!";
		ProfileUrlTitle = "Bagikan Profil";
		UnfriendDialogTitle = "INFO";
		UnfriendDialogMessage = "Anda yakin ingin membatalkan pertemanan dengan pengguna ini?";
		vac = "Lihat semua komentar";
		likes = "Suka";
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
	
	public class Image_post_viewAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Image_post_viewAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = getLayoutInflater();
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.profile_image_posts, null);
			}
			
			final androidx.cardview.widget.CardView body = _view.findViewById(R.id.body);
			final ImageView image = _view.findViewById(R.id.image);
			
			Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("post_image").toString())).into(image);
			body.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					_ProfileImageFull(_data.get((int)_position).get("post_image").toString());
				}
			});
			
			return _view;
		}
	}
	
	public class Recyclerview1Adapter extends RecyclerView.Adapter<Recyclerview1Adapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Recyclerview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
			_setCount(like_count, PostLikes);
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