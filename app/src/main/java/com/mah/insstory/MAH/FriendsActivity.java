package com.mah.insstory.MAH;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;


public class FriendsActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private String DbUsers = "";
	private String DbFriends = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String DbRequests = "";
	private String ChildKey = "";
	private String unfriend_dialog_text = "";
	private String unfriend_dialog_b1 = "";
	private String unfriend_dialog_b2 = "";
	private String request_title = "";
	private String friends_title = "";
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> UserProfile = new HashMap<>();
	private HashMap<String, Object> UserVerified = new HashMap<>();
	private HashMap<String, Object> UserOnline = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> friendandrequest = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> UsersMap = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> requestlist = new ArrayList<>();
	private ArrayList<String> UsersString = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> UsersMap2 = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> FriendInfo = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> RequestInfo = new ArrayList<>();
	private ArrayList<String> ChildString = new ArrayList<>();
	
	private LinearLayout body;
	private LinearLayout bottom_menu;
	private CardView top;
	private LinearLayout body2;
	private LinearLayout top_;
	private TextView title;
	private LinearLayout friends_body;
	private LinearLayout requests_body;
	private ListView friends_list;
	private LinearLayout no_friends;
	private ImageView no_friend_icon;
	private TextView no_friend_subtext;
	private ListView requests_list;
	private LinearLayout no_requests;
	private ImageView no_request_icon;
	private TextView no_requests_subtext;
	private LinearLayout friends;
	private LinearLayout requests;
	private ImageView friends_icon;
	private TextView friends_text;
	private ImageView requests_icon;
	private TextView requests_text;
	
	private SharedPreferences lang;
	private DatabaseReference friendb = _firebase.getReference("friends");
	private ChildEventListener _friendb_child_listener;
	private DatabaseReference fridb = _firebase.getReference("friends");
	private ChildEventListener _fridb_child_listener;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private SharedPreferences save1;
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
	
	private DatabaseReference ir = _firebase.getReference("irequests");
	private ChildEventListener _ir_child_listener;
	private DatabaseReference fr = _firebase.getReference("frequest");
	private ChildEventListener _fr_child_listener;
	private DatabaseReference irAll = _firebase.getReference("irequests");
	private ChildEventListener _irAll_child_listener;
	private DatabaseReference frAll = _firebase.getReference("frequest");
	private ChildEventListener _frAll_child_listener;
	private AlertDialog.Builder dialog;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.friends);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		bottom_menu = findViewById(R.id.bottom_menu);
		top = findViewById(R.id.top);
		body2 = findViewById(R.id.body2);
		top_ = findViewById(R.id.top_);
		title = findViewById(R.id.title);
		friends_body = findViewById(R.id.friends_body);
		requests_body = findViewById(R.id.requests_body);
		friends_list = findViewById(R.id.friends_list);
		no_friends = findViewById(R.id.no_friends);
		no_friend_icon = findViewById(R.id.no_friend_icon);
		no_friend_subtext = findViewById(R.id.no_friend_subtext);
		requests_list = findViewById(R.id.requests_list);
		no_requests = findViewById(R.id.no_requests);
		no_request_icon = findViewById(R.id.no_request_icon);
		no_requests_subtext = findViewById(R.id.no_requests_subtext);
		friends = findViewById(R.id.friends);
		requests = findViewById(R.id.requests);
		friends_icon = findViewById(R.id.friends_icon);
		friends_text = findViewById(R.id.friends_text);
		requests_icon = findViewById(R.id.requests_icon);
		requests_text = findViewById(R.id.requests_text);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		save1 = getSharedPreferences("save", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		
		requests_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				
				return true;
			}
		});
		
		friends.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				friends_body.setVisibility(View.VISIBLE);
				requests_body.setVisibility(View.GONE);
				friends_icon.setImageResource(R.drawable.friends_list_icons_2);
				requests_icon.setImageResource(R.drawable.friends_list_icons_4);
				_ImageColor(friends_icon, "#673AB7");
				_ImageColor(requests_icon, "#000000");
				friends_text.setTextColor(0xFF673AB7);
				requests_text.setTextColor(0xFF000000);
				title.setText(friends_title);
			}
		});
		
		requests.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				requests_body.setVisibility(View.VISIBLE);
				friends_body.setVisibility(View.GONE);
				friends_icon.setImageResource(R.drawable.friends_list_icons_3);
				requests_icon.setImageResource(R.drawable.friends_list_icons_1);
				_ImageColor(friends_icon, "#000000");
				_ImageColor(requests_icon, "#673AB7");
				friends_text.setTextColor(0xFF000000);
				requests_text.setTextColor(0xFF673AB7);
				title.setText(request_title);
			}
		});
		
		_friendb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				friendb.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						friendandrequest = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								friendandrequest.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						friends_list.setVisibility(View.VISIBLE);
						no_friends.setVisibility(View.GONE);
						friends_list.setAdapter(new Friends_listAdapter(friendandrequest));
						((BaseAdapter)friends_list.getAdapter()).notifyDataSetChanged();
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
		friendb.addChildEventListener(_friendb_child_listener);
		
		_fridb_child_listener = new ChildEventListener() {
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
		fridb.addChildEventListener(_fridb_child_listener);
		
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
				if (_childValue.containsKey("uid") && _childValue.containsKey("online")) {
					UserOnline.put(_childValue.get("uid").toString(), _childValue.get("online").toString());
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
		
		_ir_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				ir.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						requestlist = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								requestlist.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						requests_list.setVisibility(View.VISIBLE);
						no_requests.setVisibility(View.GONE);
						requests_list.setAdapter(new Requests_listAdapter(requestlist));
						((BaseAdapter)requests_list.getAdapter()).notifyDataSetChanged();
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
		ir.addChildEventListener(_ir_child_listener);
		
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
		
		_irAll_child_listener = new ChildEventListener() {
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
		irAll.addChildEventListener(_irAll_child_listener);
		
		_frAll_child_listener = new ChildEventListener() {
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
		frAll.addChildEventListener(_frAll_child_listener);
		
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
		friends_list.setVisibility(View.GONE);
		requests_list.setVisibility(View.GONE);
		friends_body.setVisibility(View.VISIBLE);
		requests_body.setVisibility(View.GONE);
		friends_icon.setImageResource(R.drawable.friends_list_icons_2);
		requests_icon.setImageResource(R.drawable.friends_list_icons_4);
		_Databases();
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
		_Language();
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
	
	
	public void _ImageColor(final ImageView _image, final String _color) {
		_image.setColorFilter(Color.parseColor(_color),PorterDuff.Mode.SRC_ATOP);
	}
	
	
	public void _Databases() {
		friendb.removeEventListener(_friendb_child_listener);
		DbFriends = "friends/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		friendb = _firebase.getReference(DbFriends);
		friendb.addChildEventListener(_friendb_child_listener);
		ir.removeEventListener(_ir_child_listener);
		DbRequests = "irequests/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		ir = _firebase.getReference(DbRequests);
		ir.addChildEventListener(_ir_child_listener);
	}
	
	
	public void _ThemeCustom() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		top.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		body.setBackgroundColor(0xFFFFFFFF);
		bottom_menu.setBackgroundColor(0xFFFFFFFF);
		_ImageColor(friends_icon, "#673AB7");
		_ImageColor(requests_icon, "#000000");
		friends_text.setTextColor(0xFF673AB7);
		requests_text.setTextColor(0xFF000000);
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
	
	
	public void _TrLang() {
		title.setText("Arkadaşlarım");
		no_friend_subtext.setText("Henüz bir arkadaşınız yok! Arkadaş edinmek için topluluk kısmından sohbet etmeye başla.");
		no_requests_subtext.setText("Henüz beklemede olan herhangi bir arkadaşlık isteği yok.");
		friends_text.setText("Arkadaşlar");
		requests_text.setText("İstekler");
		unfriend_dialog_text = "Bu kullanıcıyı arkadaşlıktan çıkarmak istediğine emin misin?";
		unfriend_dialog_b1 = "EVET";
		unfriend_dialog_b2 = "HAYIR";
		request_title = "Arkadaşlık İstekleri";
		friends_title = "Arkadaşlarım";
	}
	
	
	public void _EnLang() {
		title.setText("My Friends");
		no_friend_subtext.setText("You don't have any friends yet! Start chatting in the community section to make friends.");
		no_requests_subtext.setText("There are no pending friend requests yet.");
		friends_text.setText("Friends");
		requests_text.setText("Requests");
		unfriend_dialog_text = "Are you sure you want to unfriend this user?";
		unfriend_dialog_b1 = "YES";
		unfriend_dialog_b2 = "NO";
		request_title = "Friend Requests";
		friends_title = "My Friends";
	}
	
	
	public void _InLang() {
		title.setText("Teman Saya");
		no_friend_subtext.setText("Anda belum punya teman! Mulai mengobrol di bagian komunitas untuk berteman.");
		no_requests_subtext.setText("Belum ada permintaan pertemanan yang tertunda.");
		friends_text.setText("Teman");
		requests_text.setText("Permintaan");
		unfriend_dialog_text = "Anda yakin ingin membatalkan pertemanan dengan pengguna ini?";
		unfriend_dialog_b1 = "Iya";
		unfriend_dialog_b2 = "Tidak";
		request_title = "Permintaan Pertemanan";
		friends_title = "Teman Saya";
	}
	
	public class Friends_listAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Friends_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.friends_custom, null);
			}
			
			final LinearLayout body = _view.findViewById(R.id.body);
			final androidx.cardview.widget.CardView profile_card = _view.findViewById(R.id.profile_card);
			final LinearLayout user_info = _view.findViewById(R.id.user_info);
			final LinearLayout more = _view.findViewById(R.id.more);
			final LinearLayout delete = _view.findViewById(R.id.delete);
			final LinearLayout favorite = _view.findViewById(R.id.favorite);
			final ImageView avatar = _view.findViewById(R.id.avatar);
			final LinearLayout user_inf = _view.findViewById(R.id.user_inf);
			final TextView status = _view.findViewById(R.id.status);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			final ImageView more_icon = _view.findViewById(R.id.more_icon);
			final ImageView delete_icon = _view.findViewById(R.id.delete_icon);
			final ImageView favorite_icon = _view.findViewById(R.id.favorite_icon);
			
			username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
			delete.setVisibility(View.GONE);
			favorite.setVisibility(View.GONE);
			_ImageColor(delete_icon, "#FFFFFF");
			_ImageColor(favorite_icon, "#FFFFFF");
			username.setText(UserName.get(_data.get((int)_position).get("reciever").toString()).toString());
			Glide.with(getApplicationContext()).load(Uri.parse(UserProfile.get(_data.get((int)_position).get("reciever").toString()).toString())).into(avatar);
			if (UserOnline.get(_data.get((int)_position).get("reciever").toString()).toString().equals("true")) {
				status.setTextColor(0xFF4CAF50);
				status.setText("Online");
			}
			else {
				status.setTextColor(0xFFF44336);
				status.setText("Offline");
			}
			if (UserVerified.get(_data.get((int)_position).get("reciever").toString()).toString().equals("blue")) {
				verified.setVisibility(View.VISIBLE);
				verified.setImageResource(R.drawable.blue_verified);
			}
			else {
				if (UserVerified.get(_data.get((int)_position).get("reciever").toString()).toString().equals("red")) {
					verified.setVisibility(View.VISIBLE);
					verified.setImageResource(R.drawable.red_verified);
				}
				else {
					if (UserVerified.get(_data.get((int)_position).get("reciever").toString()).toString().equals("false")) {
						verified.setVisibility(View.GONE);
					}
				}
			}
			_rippleRoundStroke(delete, "#FF1744", "#FF8A80", 4, 0, "#FFFFFF");
			_rippleRoundStroke(favorite, "#651FFF", "#B388FF", 4, 0, "#FFFFFF");
			body.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					if (delete.getVisibility() == View.GONE && favorite.getVisibility() == View.GONE) {
						delete.setVisibility(View.VISIBLE);
						favorite.setVisibility(View.VISIBLE);
						more_icon.setImageResource(R.drawable.ic_chevron_left_black);
					}
					else {
						delete.setVisibility(View.GONE);
						favorite.setVisibility(View.GONE);
						more_icon.setImageResource(R.drawable.ic_chevron_right_black);
					}
				}
			});
			delete.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					dialog.setMessage(unfriend_dialog_text);
					dialog.setPositiveButton(unfriend_dialog_b1, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							map = new HashMap<>();
							fridb.child(_data.get((int)_position).get("reciever").toString().concat("/".concat(_data.get((int)_position).get("sender").toString()))).removeValue();
							fridb.child(_data.get((int)_position).get("sender").toString().concat("/".concat(_data.get((int)_position).get("reciever").toString()))).removeValue();
							map.clear();
							body.setVisibility(View.GONE);
						}
					});
					dialog.setNegativeButton(unfriend_dialog_b2, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface _dialog, int _which) {
							
						}
					});
					dialog.create().show();
				}
			});
			
			return _view;
		}
	}
	
	public class Requests_listAdapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Requests_listAdapter(ArrayList<HashMap<String, Object>> _arr) {
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
				_view = _inflater.inflate(R.layout.friendlist_custom, null);
			}
			
			final LinearLayout main = _view.findViewById(R.id.main);
			final LinearLayout body = _view.findViewById(R.id.body);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final de.hdodenhof.circleimageview.CircleImageView avatar = _view.findViewById(R.id.avatar);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			final LinearLayout space = _view.findViewById(R.id.space);
			final ImageView reject = _view.findViewById(R.id.reject);
			final ImageView accept = _view.findViewById(R.id.accept);
			final LinearLayout linear7 = _view.findViewById(R.id.linear7);
			
			username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
			verified.setVisibility(View.GONE);
			username.setText(UserName.get(_data.get((int)_position).get("reciever").toString()).toString());
			Glide.with(getApplicationContext()).load(Uri.parse(UserProfile.get(_data.get((int)_position).get("reciever").toString()).toString())).into(avatar);
			if (UserVerified.get(_data.get((int)_position).get("reciever").toString()).toString().equals("blue")) {
				verified.setVisibility(View.VISIBLE);
				verified.setImageResource(R.drawable.blue_verified);
			}
			else {
				if (UserVerified.get(_data.get((int)_position).get("reciever").toString()).toString().equals("red")) {
					verified.setVisibility(View.VISIBLE);
					verified.setImageResource(R.drawable.red_verified);
				}
				else {
					if (UserVerified.get(_data.get((int)_position).get("reciever").toString()).toString().equals("false")) {
						verified.setVisibility(View.GONE);
					}
				}
			}
			reject.setElevation((float)5);
			accept.setElevation((float)5);
			accept.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					map = new HashMap<>();
					map.put("sender", _data.get((int)_position).get("sender").toString());
					map.put("reciever", _data.get((int)_position).get("reciever").toString());
					fridb.child(_data.get((int)_position).get("sender").toString().concat("/".concat(_data.get((int)_position).get("reciever").toString()))).updateChildren(map);
					map.clear();
					map = new HashMap<>();
					map.put("sender", _data.get((int)_position).get("reciever").toString());
					map.put("reciever", _data.get((int)_position).get("sender").toString());
					fridb.child(_data.get((int)_position).get("reciever").toString().concat("/".concat(_data.get((int)_position).get("sender").toString()))).updateChildren(map);
					map.clear();
					irAll.child(_data.get((int)_position).get("sender").toString().concat("/".concat(_data.get((int)_position).get("reciever").toString()))).removeValue();
					frAll.child(_data.get((int)_position).get("sender").toString().concat("/".concat(_data.get((int)_position).get("reciever").toString()))).removeValue();
					main.setVisibility(View.GONE);
				}
			});
			reject.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View _view) {
					irAll.child(_data.get((int)_position).get("sender").toString().concat("/".concat(_data.get((int)_position).get("reciever").toString()))).removeValue();
					frAll.child(_data.get((int)_position).get("sender").toString().concat("/".concat(_data.get((int)_position).get("reciever").toString()))).removeValue();
					main.setVisibility(View.GONE);
				}
			});
			_rippleRoundStroke(reject, "#F44336", "#FF8A80", 100, 0, "#FFFFFF");
			_rippleRoundStroke(accept, "#4CAF50", "#69F0AE", 100, 0, "#FFFFFF");
			main.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)8, 0xFFFFFFFF));
			username.setTextColor(0xFF000000);
			
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