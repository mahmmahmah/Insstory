package com.mah.insstory.MAH;

import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.*;
import android.text.Editable;
import android.text.TextWatcher;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import androidx.cardview.widget.CardView;
import com.aghajari.emojiview.view.AXEmojiTextView;
import com.aghajari.emojiview.view.AXEmojiEditText;
import com.aghajari.emojiview.view.AXEmojiPopupLayout;
import com.aghajari.emojiview.view.AXEmojiView;
import com.aghajari.emojiview.AXEmojiManager;
import com.aghajari.emojiview.AXEmojiUtils;
import com.aghajari.emojiview.emoji.iosprovider.AXIOSEmoji;
import com.aghajari.emojiview.emoji.iosprovider.AXIOSEmojiLoader;
import com.aghajari.emojiview.emoji.iosprovider.AXIOSEmojiProvider;
import com.aghajari.emojiview.view.AXEmojiPager;
import com.aghajari.emojiview.AXEmojiTheme;

public class GroupChatActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> map = new HashMap<>();
	private String key = "";
	private String chatroom = "";
	private String chatcopy = "";
	private String mkey = "";
	private String child = "";
	private String key_map = "";
	private HashMap<String, Object> m2 = new HashMap<>();
	private String mskey = "";
	private HashMap<String, Object> m3 = new HashMap<>();
	private String name = "";
	private String self_profile = "";
	private String other_profile = "";
	private String first = "";
	private String fontName = "";
	private String typeace = "";
	private double more_tools = 0;
	private String uidm = "";
	private String typingview = "";
	private String userstatus = "";
	private String blockstring = "";
	private String blockstringcopy = "";
	private String MyOnlineStatus = "";
	private String SecondOnlineStatus = "";
	private boolean ReplyedBoolean = false;
	private String MyVerified = "";
	private String YouVerified = "";
	private boolean ChildAdded = false;
	private double limit = 0;
	private HashMap<String, Object> map1 = new HashMap<>();
	private double position_ = 0;
	private double tm_difference = 0;
	private String SetTimeAsecond = "";
	private String SetTimeSecond = "";
	private String SetTimeAMinute = "";
	private String SetTimeMinute = "";
	private String SetTimeHours = "";
	private String SetTimeDays = "";
	private String TypingStatus = "";
	private String videocall = "";
	private String edit = "";
	private String copy = "";
	private String delete = "";
	private String reply = "";
	private String deletemessagedialog_title = "";
	private String deletemessagedialog_text = "";
	private String deletemessagedialog_b1 = "";
	private String deletemessagedialog_b2 = "";
	private String youcannotdeletethismessage_w = "";
	private String messagecopyed = "";
	private String block_s = "";
	private String report_s = "";
	private HashMap<String, Object> m1_map = new HashMap<>();
	private HashMap<String, Object> m2_map = new HashMap<>();
	private HashMap<String, Object> m3_map = new HashMap<>();
	private HashMap<String, Object> m4_map = new HashMap<>();
	private HashMap<String, Object> m5_map = new HashMap<>();
	private HashMap<String, Object> m6_map = new HashMap<>();
	private String emptymessage = "";
	private String groupInfo = "";
	private String GlobalGroupName = "";
	private String UsersString = "";
	private double usersCount = 0;
	private String UsersCountRightString = "";
	private String sentM = "";
	private String seenM = "";
	private String GroupMemberType = "";
	private String OnlyAdmins = "";
	private String privacy = "";
	private HashMap<String, Object> type = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();
	private ArrayList<String> str = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> user = new ArrayList<>();
	
	private LinearLayout body;
	private AXEmojiPopupLayout emojilay;
	private LinearLayout top;
	private LinearLayout medium;
	private ImageView back;
	private LinearLayout profile;
	private LinearLayout linear3;
	private ImageView more_button;
	private CircleImageView avatar;
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView users_count;
	private AXEmojiTextView groupname;
	private ImageView verified;
	private ListView messages;
	private CardView bottom_card;
	private LinearLayout bottom;
	private LinearLayout onlyadmin;
	private LinearLayout join_button;
	private LinearLayout message_body;
	private TextView onlyadmin_txt;
	private TextView join_button_text;
	private ImageView emojies_open;
	private AXEmojiEditText message;
	private ImageView send;
	
	private DatabaseReference chat1 = _firebase.getReference("+chatroom+");
	private ChildEventListener _chat1_child_listener;
	private Calendar cc = Calendar.getInstance();
	private Intent i = new Intent();
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
	
	private SharedPreferences m;
	private AlertDialog.Builder dialog;
	private SharedPreferences lang;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private SharedPreferences save;
	private Intent intent = new Intent();
	private ObjectAnimator o = new ObjectAnimator();
	private TimerTask poPup;
	private Calendar ct = Calendar.getInstance();
	private Calendar time2 = Calendar.getInstance();
	private DatabaseReference gdb = _firebase.getReference("groups/group");
	private ChildEventListener _gdb_child_listener;
	private DatabaseReference users = _firebase.getReference("+UsersString+");
	private ChildEventListener _users_child_listener;
	private Vibrator v;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_chat);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		emojilay = findViewById(R.id.emojilay);
		top = findViewById(R.id.top);
		medium = findViewById(R.id.medium);
		back = findViewById(R.id.back);
		profile = findViewById(R.id.profile);
		linear3 = findViewById(R.id.linear3);
		more_button = findViewById(R.id.more_button);
		avatar = findViewById(R.id.avatar);
		linear1 = findViewById(R.id.linear1);
		linear2 = findViewById(R.id.linear2);
		users_count = findViewById(R.id.users_count);
		groupname = findViewById(R.id.groupname);
		verified = findViewById(R.id.verified);
		messages = findViewById(R.id.messages);
		bottom_card = findViewById(R.id.bottom_card);
		bottom = findViewById(R.id.bottom);
		onlyadmin = findViewById(R.id.onlyadmin);
		join_button = findViewById(R.id.join_button);
		message_body = findViewById(R.id.message_body);
		onlyadmin_txt = findViewById(R.id.onlyadmin_txt);
		join_button_text = findViewById(R.id.join_button_text);
		emojies_open = findViewById(R.id.emojies_open);
		message = findViewById(R.id.message);
		send = findViewById(R.id.send);
		auth = FirebaseAuth.getInstance();
		m = getSharedPreferences("m", Activity.MODE_PRIVATE);
		dialog = new AlertDialog.Builder(this);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				map = new HashMap<>();
				map.put("typing", "false");
				
				map.clear();
				chat1.removeEventListener(_chat1_child_listener);
				
				finish();
			}
		});
		
		profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), GroupProfileActivity.class);
				intent.putExtra("group_key", getIntent().getStringExtra("group_key"));
				startActivity(intent);
			}
		});
		
		more_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				final com.google.android.material.bottomsheet.BottomSheetDialog ChatMenu = new com.google.android.material.bottomsheet.BottomSheetDialog(GroupChatActivity.this);
				View ChatMenuV = getLayoutInflater().inflate(R.layout.chat_more_button_custom, null);
				ChatMenu.setContentView(ChatMenuV);
				
				final LinearLayout linear  = (LinearLayout)ChatMenuV.findViewById(R.id.body);
				
				ChatMenu.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				
				ChatMenu.show();
				android.graphics.drawable.GradientDrawable CM = new android.graphics.drawable.GradientDrawable();
				int dc = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				CM.setColor(0xFFFFFFFF);
				CM.setCornerRadii(new float[]{
						dc*15,dc*15,dc*15 ,dc*15,dc*0,dc*0 ,dc*0,dc*0});
				linear.setElevation(dc*5);
				android.graphics.drawable.RippleDrawable ChatMenuDG = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), CM, null);
				linear.setBackground(ChatMenuDG);
				LinearLayout slider = (LinearLayout) ChatMenuV.findViewById(R.id.slider);
				TextView block_text = ChatMenuV.findViewById(R.id.block_text);
				TextView report_text = ChatMenuV.findViewById(R.id.report_text);
				LinearLayout block = ChatMenuV.findViewById(R.id.block);
				LinearLayout report = ChatMenuV.findViewById(R.id.report);
				LinearLayout body = ChatMenuV.findViewById(R.id.body);
				block_text.setText(block_s);
				report_text.setText(report_s);
				slider.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF424242));
				block_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"), 0);
				report_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansregular.otf"), 0);
				_rippleRoundStroke(block, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
				_rippleRoundStroke(report, "#FFFFFF", "#EEEEEE", 0, 0, "#FFFFFF");
			}
		});
		
		messages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				final com.google.android.material.bottomsheet.BottomSheetDialog MessageMenu = new com.google.android.material.bottomsheet.BottomSheetDialog(GroupChatActivity.this);
				View MessageMenuV = getLayoutInflater().inflate(R.layout.message_setting_popup, null);
				MessageMenu.setContentView(MessageMenuV);
				
				final LinearLayout linear  = (LinearLayout)MessageMenuV.findViewById(R.id.body);
				
				MessageMenu.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
				
				MessageMenu.show();
				android.graphics.drawable.GradientDrawable MM = new android.graphics.drawable.GradientDrawable();
				int dc = (int) getApplicationContext().getResources().getDisplayMetrics().density;
				MM.setColor(0xFFFFFFFF);
				MM.setCornerRadii(new float[]{
						dc*15,dc*15,dc*15 ,dc*15,dc*0,dc*0 ,dc*0,dc*0});
				linear.setElevation(dc*5);
				android.graphics.drawable.RippleDrawable MessageMenuDG = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{0xFFE0E0E0}), MM, null);
				linear.setBackground(MessageMenuDG);
				LinearLayout slider = (LinearLayout) MessageMenuV.findViewById(R.id.slider);
				TextView edit_title = (TextView) MessageMenuV.findViewById(R.id.edit_title);
				TextView copy_title = (TextView) MessageMenuV.findViewById(R.id.copy_title);
				TextView delete_title = (TextView) MessageMenuV.findViewById(R.id.delete_title);
				TextView reply_title = (TextView) MessageMenuV.findViewById(R.id.reply_title);
				LinearLayout edit_lay = (LinearLayout) MessageMenuV.findViewById(R.id.edit);
				LinearLayout copy_lay = (LinearLayout) MessageMenuV.findViewById(R.id.copy);
				LinearLayout delete_lay = (LinearLayout) MessageMenuV.findViewById(R.id.delete);
				LinearLayout reply_lay = (LinearLayout) MessageMenuV.findViewById(R.id.reply);
				edit_title.setText(edit);
				copy_title.setText(copy);
				delete_title.setText(delete);
				reply_title.setText(reply);
				slider.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF424242));
				if (listmap.get((int)_position).get("message_type").toString().equals("MSG")) {
					edit_lay.setVisibility(View.GONE);
					copy_lay.setVisibility(View.GONE);
					reply_lay.setVisibility(View.GONE);
				}
				copy_lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", listmap.get((int)_position).get("message").toString()));
						SketchwareUtil.showMessage(getApplicationContext(), messagecopyed);
						MessageMenu.dismiss();
					}
				});
				delete_lay.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View _view) {
						if (listmap.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
							dialog.setTitle(deletemessagedialog_title);
							dialog.setMessage(deletemessagedialog_text);
							dialog.setPositiveButton(deletemessagedialog_b1, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									chat1.child(listmap.get((int)_position).get("mkey").toString()).removeValue();
									
								}
							});
							dialog.setNegativeButton(deletemessagedialog_b2, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							dialog.create().show();
						}
						else {
							SketchwareUtil.showMessage(getApplicationContext(), youcannotdeletethismessage_w);
						}
						MessageMenu.dismiss();
					}
				});
			}
		});
		
		join_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("member_type", "member");
				map.put("member_avatar", save.getString("myavatar", ""));
				map.put("member_username", save.getString("myname", ""));
				map.put("member_verify", save.getString("myverify", ""));
				users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				map.clear();
			}
		});
		
		emojies_open.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (emojilay.isShowing()) {
					emojilay.hideAndOpenKeyboard();
					emojies_open.setImageResource(R.drawable.emoji_icon);
				}
				else {
					emojilay.toggle();
					emojilay.show();
					emojies_open.setImageResource(R.drawable.ic_keyboard_black);
				}
			}
		});
		
		message.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				if (message.getText().toString().equals("")) {
					send.setVisibility(View.GONE);
				}
				else {
					send.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (message.getText().toString().equals("")) {
					v.vibrate((long)(1000));
					SketchwareUtil.showMessage(getApplicationContext(), emptymessage);
				}
				else {
					mkey = chat1.push().getKey();
					cc = Calendar.getInstance();
					m2_map = new HashMap<>();
					m2_map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					m2_map.put("message", message.getText().toString());
					m2_map.put("time", String.valueOf((long)(cc.getTimeInMillis())));
					m2_map.put("message_type", "MSG");
					m2_map.put("mkey", mkey);
					m2_map.put("message_status", "forwarded");
					m2_map.put("sender_name", save.getString("myname", ""));
					m2_map.put("sender_avatar", save.getString("myavatar", ""));
					chat1.child(mkey).updateChildren(m2_map);
					m2_map.clear();
					message.setText("");
				}
			}
		});
		
		_chat1_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				messages.setStackFromBottom(true);
				ChildAdded = true;
				chat1.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						messages.setAdapter(new MessagesAdapter(listmap));
						((BaseAdapter)messages.getAdapter()).notifyDataSetChanged();
					}
					@Override
					public void onCancelled(DatabaseError _databaseError) {
					}
				});
				if (!_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					map = new HashMap<>();
					map.put("message_status", "seen");
					chat1.child(_childValue.get("mkey").toString()).updateChildren(map);
					map.clear();
				}
				child = _childKey;
				key_map = _childValue.get("mkey").toString();
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				chat1.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						messages.setAdapter(new MessagesAdapter(listmap));
						((BaseAdapter)messages.getAdapter()).notifyDataSetChanged();
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
				chat1.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						listmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								listmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						messages.setAdapter(new MessagesAdapter(listmap));
						((BaseAdapter)messages.getAdapter()).notifyDataSetChanged();
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
		chat1.addChildEventListener(_chat1_child_listener);
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("blocked").toString().equals("true")) {
						intent.setClass(getApplicationContext(), BlockActivity.class);
						startActivity(intent);
					}
					m.edit().putString("n", _childValue.get("username").toString()).commit();
					name = _childValue.get("username").toString();
					self_profile = _childValue.get("avatar").toString();
					MyOnlineStatus = _childValue.get("online").toString();
					MyVerified = _childValue.get("verify").toString();
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
		
		_gdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("group_key"))) {
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("group_avatar").toString())).into(avatar);
					groupname.setText(_childValue.get("group_name").toString());
					privacy = _childValue.get("privacy_only_admin").toString();
					user.add(_childValue);
					if (GroupMemberType.equals("notjoined")) {
						message_body.setVisibility(View.GONE);
						join_button.setVisibility(View.VISIBLE);
						onlyadmin.setVisibility(View.GONE);
					}
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("group_key"))) {
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("group_avatar").toString())).into(avatar);
					groupname.setText(_childValue.get("group_name").toString());
					if (GroupMemberType.equals("notjoined")) {
						message_body.setVisibility(View.GONE);
						join_button.setVisibility(View.VISIBLE);
						onlyadmin.setVisibility(View.GONE);
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
		gdb.addChildEventListener(_gdb_child_listener);
		
		_users_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				type.put(_childValue.get("uid").toString(), _childValue.get("member_type").toString());
				usersCount++;
				users_count.setText(String.valueOf((long)(usersCount)).concat(" ".concat(UsersCountRightString)));
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					GroupMemberType = _childValue.get("member_type").toString();
					if (GroupMemberType.equals("admin")) {
						message_body.setVisibility(View.VISIBLE);
						join_button.setVisibility(View.GONE);
						onlyadmin.setVisibility(View.GONE);
					}
					else {
						if (GroupMemberType.equals("moderator")) {
							message_body.setVisibility(View.VISIBLE);
							join_button.setVisibility(View.GONE);
							onlyadmin.setVisibility(View.GONE);
						}
						else {
							if (GroupMemberType.equals("member")) {
								if (privacy.equals("true")) {
									message_body.setVisibility(View.GONE);
									join_button.setVisibility(View.GONE);
									onlyadmin.setVisibility(View.VISIBLE);
								}
								else {
									message_body.setVisibility(View.VISIBLE);
									join_button.setVisibility(View.GONE);
									onlyadmin.setVisibility(View.GONE);
								}
							}
						}
					}
				}
				else {
					GroupMemberType = "notjoined";
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				type.put(_childValue.get("uid").toString(), _childValue.get("member_type").toString());
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					GroupMemberType = _childValue.get("member_type").toString();
					if (GroupMemberType.equals("admin")) {
						message_body.setVisibility(View.VISIBLE);
						join_button.setVisibility(View.GONE);
						onlyadmin.setVisibility(View.GONE);
					}
					else {
						if (GroupMemberType.equals("moderator")) {
							message_body.setVisibility(View.VISIBLE);
							join_button.setVisibility(View.GONE);
							onlyadmin.setVisibility(View.GONE);
						}
						else {
							if (GroupMemberType.equals("member")) {
								if (privacy.equals("true")) {
									message_body.setVisibility(View.GONE);
									join_button.setVisibility(View.GONE);
									onlyadmin.setVisibility(View.VISIBLE);
								}
								else {
									message_body.setVisibility(View.VISIBLE);
									join_button.setVisibility(View.GONE);
									onlyadmin.setVisibility(View.GONE);
								}
							}
						}
					}
				}
				else {
					GroupMemberType = "notjoined";
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
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					GroupMemberType = _childValue.get("member_type").toString();
				}
				else {
					
				}
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		users.addChildEventListener(_users_child_listener);
		
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
		GroupMemberType = "notjoined";
		verified.setVisibility(View.GONE);
		send.setVisibility(View.GONE);
		avatar.setBackgroundResource(0);
		message_body.setVisibility(View.GONE);
		join_button.setVisibility(View.GONE);
		onlyadmin.setVisibility(View.GONE);
		more_button.setVisibility(View.GONE);
		emojilay.setVisibility(View.GONE);
		AXEmojiManager.install(this,new com.aghajari.emojiview.emoji.iosprovider.AXIOSEmojiProvider(this));
		
		AXEmojiManager.getEmojiViewTheme().setFooterEnabled(true);
		
		AXEmojiPager emojiPager = new AXEmojiPager(GroupChatActivity.this);
		
		AXEmojiView emojiView = new AXEmojiView(GroupChatActivity.this);
		emojiPager.addPage(emojiView, R.drawable.emoji_icon);
		
		emojiPager.setEditText(message);
		
		emojiPager.setLeftIcon(R.drawable.emoji_search);
		
		AXEmojiPopupLayout emojilay = findViewById(R.id.emojilay);
		emojilay.setPopupAnimationEnabled(true);
		emojilay.setPopupAnimationDuration(250);
		emojilay.initPopupView(emojiPager);
		
		/*DbCustomStart*/
		chat1.removeEventListener(_chat1_child_listener);
		chatroom = "groups/group/".concat(getIntent().getStringExtra("group_key").concat("/".concat("chat")));
		chat1 =
		_firebase.getReference(chatroom);
		chat1.addChildEventListener(_chat1_child_listener);
		users.removeEventListener(_users_child_listener);
		UsersString = "groups/group/".concat(getIntent().getStringExtra("group_key").concat("/".concat("users")));
		users =
		_firebase.getReference(UsersString);
		users.addChildEventListener(_users_child_listener);
		/*DbCustomEnd*/
	}
	
	@Override
	public void onBackPressed() {
		if (ChildAdded) {
			chat1.removeEventListener(_chat1_child_listener);
			finish();
		}
		else {
			chat1.removeEventListener(_chat1_child_listener);
			finish();
		}
	}
	
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		if (ChildAdded) {
			chat1.removeEventListener(_chat1_child_listener);
		}
		else {
			chat1.removeEventListener(_chat1_child_listener);
		}
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
		_CustomView();
		_RippleRoundCustomView();
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
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _RippleRoundCustomView() {
		if (save.getString("theme", "").equals("light")) {
			_rippleRoundStroke(profile, "#FFFFFF", "#BDBDBD", 8, 0, "#FFFFFF");
			_rippleRoundStroke(more_button, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
			_rippleRoundStroke(back, "#FFFFFF", "#BDBDBD", 100, 0, "#FFFFFF");
		}
		else {
			if (save.getString("theme", "").equals("dark")) {
				_rippleRoundStroke(profile, "#212121", "#BDBDBD", 8, 0, "#FFFFFF");
				_rippleRoundStroke(more_button, "#212121", "#BDBDBD", 100, 0, "#FFFFFF");
				_rippleRoundStroke(back, "#212121", "#BDBDBD", 100, 0, "#FFFFFF");
			}
		}
	}
	
	
	public void _CustomView() {
		top.setElevation((float)8);
		_ImageColor(send, "#2196F3");
		groupname.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansdarkbold.otf"), 0);
	}
	
	
	public void _Language() {
		if (lang.getString("language", "").equals("")) {
			_TrLang();
		}
		if (lang.getString("language", "").equals("english")) {
			_EnLang();
		}
	}
	
	
	public void _ThemeCustom() {
		if (save.getString("theme", "").equals("light")) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
			_ImageColor(back, "#000000");
			_ImageColor(more_button, "#000000");
			_rippleRoundStroke(send, "#FFFFFF", "#2196F3", 50, 0, "#FFFFFF");
			top.setBackgroundColor(0xFFFFFFFF);
			body.setBackgroundColor(0xFFFFFFFF);
			groupname.setTextColor(0xFF000000);
			message.setTextColor(0xFF000000);
			bottom.setBackgroundColor(0xFFFFFFFF);
			users_count.setTextColor(0xFF000000);
			onlyadmin_txt.setTextColor(0xFF000000);
		}
		else {
			if (save.getString("theme", "").equals("dark")) {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
					Window w =GroupChatActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
				}
				_ImageColor(back, "#FFFFFF");
				_ImageColor(more_button, "#FFFFFF");
				_rippleRoundStroke(send, "#212121", "#2196F3", 50, 0, "#FFFFFF");
				top.setBackgroundColor(0xFF212121);
				body.setBackgroundColor(0xFF000000);
				groupname.setTextColor(0xFFFFFFFF);
				message.setTextColor(0xFFFFFFFF);
				bottom.setBackgroundColor(0xFF212121);
				users_count.setTextColor(0xFFFFFFFF);
				onlyadmin_txt.setTextColor(0xFFFFFFFF);
			}
		}
	}
	
	
	public void _setTime(final double _currentTime, final TextView _txt) {
		tm_difference = ct.getTimeInMillis() - _currentTime;
		if (tm_difference < 60000) {
			if ((tm_difference / 1000) < 2) {
				_txt.setText(SetTimeAsecond);
			}
			else {
				_txt.setText(String.valueOf((long)(tm_difference / 1000)).concat(SetTimeSecond));
			}
		}
		else {
			if (tm_difference < (60 * 60000)) {
				if ((tm_difference / 60000) < 2) {
					_txt.setText(SetTimeAMinute);
				}
				else {
					_txt.setText("● ".concat(String.valueOf((long)(tm_difference / 60000)).concat(SetTimeMinute)));
				}
			}
			else {
				if (tm_difference < (24 * (60 * 60000))) {
					if ((tm_difference / (60 * 60000)) < 2) {
						_txt.setText("● ".concat(String.valueOf((long)(tm_difference / (60 * 60000))).concat(SetTimeHours)));
					}
					else {
						_txt.setText("● ".concat(String.valueOf((long)(tm_difference / (60 * 60000))).concat(SetTimeHours)));
					}
				}
				else {
					if (tm_difference < (7 * (24 * (60 * 60000)))) {
						if ((tm_difference / (24 * (60 * 60000))) < 2) {
							_txt.setText("● ".concat(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(SetTimeDays)));
						}
						else {
							_txt.setText("● ".concat(String.valueOf((long)(tm_difference / (24 * (60 * 60000)))).concat(SetTimeDays)));
						}
					}
					else {
						time2.setTimeInMillis((long)(_currentTime));
						_txt.setText("● ".concat(new SimpleDateFormat("dd MMM yyyy HH:mm").format(time2.getTime())));
					}
				}
			}
		}
	}
	
	
	public void _TrLang() {
		join_button_text.setText("KATIL");
		message.setHint("Mesaj Yaz");
		onlyadmin_txt.setText("Bu gruba yalnızca yöneticiler mesaj gönderebilir!");
		SetTimeAsecond = "● Bir saniye önce";
		SetTimeSecond = " saniye önce";
		SetTimeAMinute = "● Bir dakika önce";
		SetTimeMinute = " dakika önce";
		SetTimeHours = " saat önce";
		SetTimeDays = " gün önce";
		TypingStatus = "● Yazıyor...";
		videocall = "Görüntülü Ara";
		block_s = "Engelle";
		report_s = "Bildir";
		edit = "Düzenle";
		copy = "Kopyala";
		delete = "Sil";
		reply = "Yanıtla";
		deletemessagedialog_title = "Sil?";
		deletemessagedialog_text = "Bu mesaji silmek istediğine emin misin?";
		deletemessagedialog_b1 = "EVET";
		deletemessagedialog_b2 = "HAYIR";
		youcannotdeletethismessage_w = "Bu mesajı silemezsiniz!";
		messagecopyed = "Mesaj Kopyalandı";
		emptymessage = "Boş Mesaj Gönderilemez";
		GlobalGroupName = "Küresel Sohbet";
		sentM = "İletildi";
		seenM = "Görüldü";
		UsersCountRightString = "Üye";
	}
	
	
	public void _EnLang() {
		join_button_text.setText("JOIN");
		message.setHint("Write message");
		onlyadmin_txt.setText("Only admins can send messages to this group!");
		SetTimeAsecond = "● One second ago";
		SetTimeSecond = " seconds ago";
		SetTimeAMinute = "● One minute ago";
		SetTimeMinute = " minute ago";
		SetTimeHours = " hours ago";
		SetTimeDays = " days ago";
		TypingStatus = "● Typing...";
		videocall = "Video Call";
		block_s = "Block";
		report_s = "Report";
		edit = "Edit";
		copy = "Copy";
		delete = "Delete";
		reply = "Reply";
		deletemessagedialog_title = "Delete?";
		deletemessagedialog_text = "Are you sure you want to delete this message?";
		deletemessagedialog_b1 = "YES";
		deletemessagedialog_b2 = "NO";
		youcannotdeletethismessage_w = "You cannot delete this message!";
		messagecopyed = "Message Copied";
		emptymessage = "Empty Message Cannot Be Sent";
		GlobalGroupName = "Global Chat";
		sentM = "Sent";
		seenM = "Seen";
		UsersCountRightString = "Members";
	}
	
	public class MessagesAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public MessagesAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.group_chat_message, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout message_body = _view.findViewById(R.id.message_body);
			final LinearLayout message_layout = _view.findViewById(R.id.message_layout);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView imageview2 = _view.findViewById(R.id.imageview2);
			final TextView message_text = _view.findViewById(R.id.message_text);
			final TextView date = _view.findViewById(R.id.date);
			final ImageView imageview1 = _view.findViewById(R.id.imageview1);
			
			main.setVisibility(View.GONE);
			imageview1.setVisibility(View.GONE);
			message_body.setElevation((float)2);
			imageview2.setVisibility(View.GONE);
			if (_data.get((int)_position).containsKey("message_type")) {
				if (_data.get((int)_position).get("message_type").toString().equals("MSG")) {
					main.setVisibility(View.VISIBLE);
					if (_data.get((int)_position).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						main.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
						message_body.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
						message_layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFF673AB7));
						imageview1.setVisibility(View.VISIBLE);
						message_text.setTextColor(0xFFFFFFFF);
						avatar.setVisibility(View.GONE);
						username.setVisibility(View.GONE);
						date.setTextColor(0xFFFFFFFF);
						username.setTextColor(0xFFFFFFFF);
						linear3.setVisibility(View.GONE);
					}
					else {
						message_layout.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)12, 0xFFFFFFFF));
						message_body.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
						main.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
						message_text.setTextColor(0xFF000000);
						avatar.setVisibility(View.VISIBLE);
						username.setVisibility(View.VISIBLE);
						date.setTextColor(0xFF000000);
						username.setTextColor(0xFF673AB7);
					}
					cc.setTimeInMillis((long)(Double.parseDouble(_data.get((int)_position).get("time").toString())));
					message_text.setText(_data.get((int)_position).get("message").toString());
					date.setText(new SimpleDateFormat("hh:mm").format(cc.getTime()));
					if (_data.get((int)_position).get("message_status").toString().equals("forwarded")) {
						imageview1.setImageResource(R.drawable.ic_check_white);
					}
					else {
						if (_data.get((int)_position).get("message_status").toString().equals("seen")) {
							imageview1.setImageResource(R.drawable.ic_seen);
						}
					}
					username.setText(_data.get((int)_position).get("sender_name").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("sender_avatar").toString())).into(avatar);
				}
			}
			if (type.get(_data.get((int)_position).get("uid").toString()).toString().equals("admin")) {
				imageview2.setColorFilter(0xFF673AB7, PorterDuff.Mode.MULTIPLY);
				imageview2.setVisibility(View.VISIBLE);
			}
			else {
				imageview2.setVisibility(View.GONE);
			}
			
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