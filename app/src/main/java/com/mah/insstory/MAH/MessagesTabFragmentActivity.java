package com.mah.insstory.MAH;

import android.animation.*;
import android.animation.ObjectAnimator;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
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
import android.view.View.*;
import android.view.animation.*;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.webkit.*;
import android.widget.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
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

public class MessagesTabFragmentActivity extends Fragment {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private double tm_difference = 0;
	private String a_second = "";
	private String second = "";
	private String a_minute = "";
	private String minute = "";
	private String hours = "";
	private String days = "";
	private HashMap<String, Object> UserName = new HashMap<>();
	private HashMap<String, Object> UserProfile = new HashMap<>();
	private HashMap<String, Object> UserVerified = new HashMap<>();
	private String inboxdb = "";
	private String YouM = "";
	private HashMap<String, Object> online = new HashMap<>();
	private String nsend = "";
	private String mess = "";
	private HashMap<String, Object> map = new HashMap<>();
	private boolean read = false;
	
	private ArrayList<HashMap<String, Object>> messagesmap = new ArrayList<>();
	
	private RecyclerView messages;
	private LinearLayout no_messages_layout;
	private TextView no_messages_title;
	private TextView no_messages_subtitle;
	
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference ms = _firebase.getReference("inbox");
	private ChildEventListener _ms_child_listener;
	private Calendar cc = Calendar.getInstance();
	private Calendar tim2 = Calendar.getInstance();
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
	private Intent i = new Intent();
	private ObjectAnimator anima = new ObjectAnimator();
	private TimerTask timer;
	private SharedPreferences save;
	private SharedPreferences lang;
	private AlertDialog.Builder delete;
	private DatabaseReference ms2 = _firebase.getReference("inbox");
	private ChildEventListener _ms2_child_listener;
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.messages_tab_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		messages = _view.findViewById(R.id.messages);
		no_messages_layout = _view.findViewById(R.id.no_messages_layout);
		no_messages_title = _view.findViewById(R.id.no_messages_title);
		no_messages_subtitle = _view.findViewById(R.id.no_messages_subtitle);
		auth = FirebaseAuth.getInstance();
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
		delete = new AlertDialog.Builder(getActivity());
		
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
					online.put(_childValue.get("uid").toString(), _childValue.get("online").toString());
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
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
					online.put(_childValue.get("uid").toString(), _childValue.get("online").toString());
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
		
		_ms_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				messages.setVisibility(View.VISIBLE);
				no_messages_layout.setVisibility(View.GONE);
				ms.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						messagesmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								messagesmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						messages.setAdapter(new MessagesAdapter(messagesmap));
						if (_childValue.containsKey("timestamp")) {
							_SortMap(messagesmap, "timestamp", false, false);
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
				ms.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						messagesmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								messagesmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						messages.setAdapter(new MessagesAdapter(messagesmap));
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
				ms.addListenerForSingleValueEvent(new ValueEventListener() {
					@Override
					public void onDataChange(DataSnapshot _dataSnapshot) {
						messagesmap = new ArrayList<>();
						try {
							GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
							for (DataSnapshot _data : _dataSnapshot.getChildren()) {
								HashMap<String, Object> _map = _data.getValue(_ind);
								messagesmap.add(_map);
							}
						}
						catch (Exception _e) {
							_e.printStackTrace();
						}
						messages.setAdapter(new MessagesAdapter(messagesmap));
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
		ms.addChildEventListener(_ms_child_listener);
		
		_ms2_child_listener = new ChildEventListener() {
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
		ms2.addChildEventListener(_ms2_child_listener);
		
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
		no_messages_title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		messages.setLayoutManager(new LinearLayoutManager(getContext()));
		messages.setVisibility(View.GONE);
		no_messages_layout.setVisibility(View.VISIBLE);
		ms.removeEventListener(_ms_child_listener);
		inboxdb = "inbox/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid());
		ms = _firebase.getReference(inboxdb);
		ms.addChildEventListener(_ms_child_listener);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
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
	
	
	public void _setTime(final double _currentTime, final TextView _txt) {
		tm_difference = cc.getTimeInMillis() - _currentTime;
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
						tim2.setTimeInMillis((long)(_currentTime));
						_txt.setText(new SimpleDateFormat("dd MMM yyyy | hh:mm").format(tim2.getTime()));
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
		no_messages_title.setText("Henüz Mesaj Yok!");
		no_messages_subtitle.setText("Topluluktan yeni insanlarla tanışın!");
		YouM = "Sen: ";
		a_second = "Bir saniye önce";
		second = " Saniye önce";
		a_minute = "Bir dakika önce";
		minute = " Dakika önce";
		hours = " Saat önce";
		days = " Gün önce";
	}
	
	
	public void _EnLang() {
		no_messages_title.setText("No Messages Yet!");
		no_messages_subtitle.setText("Meet new people from the community!");
		YouM = "You: ";
		a_second = "A second ago";
		second = " Second ago";
		a_minute = "A minute ago";
		minute = " Minute ago";
		hours = " Hours ago";
		days = " days ago";
	}
	
	
	public void _ThemeCustom() {
		no_messages_title.setTextColor(0xFF000000);
		no_messages_subtitle.setTextColor(0xFF000000);
	}
	
	
	public void _InLang() {
		no_messages_title.setText("Belum Ada Pesan!");
		no_messages_subtitle.setText("Temukan orang baru dari komunitas!");
		YouM = "Anda: ";
		a_second = "Detik yang lalu";
		second = " Detik lalu";
		a_minute = "Menit yang lalu";
		minute = " Menit lalu";
		hours = " Jam lalu";
		days = " hari lalu";
	}
	
	public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public MessagesAdapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			LayoutInflater _inflater = getActivity().getLayoutInflater();
			View _v = _inflater.inflate(R.layout.message_list, null);
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_v.setLayoutParams(_lp);
			return new ViewHolder(_v);
		}
		
		@Override
		public void onBindViewHolder(ViewHolder _holder, final int _position) {
			View _view = _holder.itemView;
			
			final androidx.cardview.widget.CardView cardview1 = _view.findViewById(R.id.cardview1);
			final LinearLayout body = _view.findViewById(R.id.body);
			final LinearLayout linear9 = _view.findViewById(R.id.linear9);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final de.hdodenhof.circleimageview.CircleImageView circleimageview1 = _view.findViewById(R.id.circleimageview1);
			final LinearLayout status_body = _view.findViewById(R.id.status_body);
			final LinearLayout status_icon = _view.findViewById(R.id.status_icon);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final TextView username = _view.findViewById(R.id.username);
			final ImageView verified = _view.findViewById(R.id.verified);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView time = _view.findViewById(R.id.time);
			final ImageView tick = _view.findViewById(R.id.tick);
			final TextView msg = _view.findViewById(R.id.msg);
			final LinearLayout linear_count = _view.findViewById(R.id.linear_count);
			final TextView count = _view.findViewById(R.id.count);
			
			linear_count.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)180, 0xFF673AB7));
			RecyclerView.LayoutParams _lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
			_view.setLayoutParams(_lp);
			verified.setVisibility(View.GONE);
			tick.setColorFilter(0xFF607D8B, PorterDuff.Mode.MULTIPLY);
			status_body.setVisibility(View.GONE);
			tick.setVisibility(View.GONE);
			_rippleRoundStroke(body, "#FFFFFF", "#ECEFF1", 20, 0, "#FFFFFF");
			username.setText(UserName.get(_data.get((int)_position).get("uid").toString()).toString());
			Glide.with(getContext().getApplicationContext()).load(Uri.parse(UserProfile.get(_data.get((int)_position).get("uid").toString()).toString())).into(circleimageview1);
			status_body.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)360, (int)1, 0xFF4CAF50, 0xFFFFFFFF));
			status_icon.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)360, (int)2, 0xFFFFFFFF, 0xFF4CAF50));
			if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("blue")) {
				verified.setImageResource(R.drawable.blue_verified);
				verified.setVisibility(View.VISIBLE);
			}
			if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("red")) {
				verified.setImageResource(R.drawable.red_verified);
				verified.setVisibility(View.VISIBLE);
			}
			if (UserVerified.get(_data.get((int)_position).get("uid").toString()).toString().equals("false")) {
				verified.setVisibility(View.GONE);
			}
			if (_data.get((int)_position).get("myuid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
				tick.setVisibility(View.VISIBLE);
				msg.setText(_data.get((int)_position).get("lastMessage").toString().concat(""));
				msg.setTextColor(0xFF000000);
				linear_count.setVisibility(View.GONE);
				body.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
								
						i.setClass(getContext().getApplicationContext(), ChatActivity.class);
								i.putExtra("seconduser", _data.get((int)_position).get("uid").toString());
								i.putExtra("firstuser", FirebaseAuth.getInstance().getCurrentUser().getUid());
								startActivity(i);
						}
				});
				body.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						delete.setTitle("Info");
						delete.setMessage("Are you sure you want delete this chat? Messages with this person will not delete!!");
						delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								ms.child(_data.get((int)_position).get("uid").toString()).removeValue();
							}
						});
						delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						delete.create().show();
						return true;
					}
				});
			}
			else {
				msg.setText(_data.get((int)_position).get("lastMessage").toString());
				if (_data.get((int)_position).get("count").toString().equals("0") || save.getString("count", "").equals("0")) {
					linear_count.setVisibility(View.GONE);
					msg.setTextColor(0xFF000000);
					map = new HashMap<>();
					map.put("count", "0");
					ms.child(_data.get((int)_position).get("uid").toString()).updateChildren(map);
					ms2.child(_data.get((int)_position).get("uid").toString().concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).updateChildren(map);
					map.clear();
					save.edit().putString("count", "1").commit();
				}
				else {
					msg.setTextColor(0xFF673AB7);
					count.setText(_data.get((int)_position).get("count").toString());
				}
				if (!read) {
					map = new HashMap<>();
					map.put("read", "false");
					ms.child(_data.get((int)_position).get("uid").toString()).updateChildren(map);
					ms2.child(_data.get((int)_position).get("uid").toString().concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).updateChildren(map);
					map.clear();
				}
				body.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
						
						read = true;
						map = new HashMap<>();
						map.put("count", "0");
						map.put("read", "true");
						ms.child(_data.get((int)_position).get("uid").toString()).updateChildren(map);	ms2.child(_data.get((int)_position).get("uid").toString().concat("/".concat(FirebaseAuth.getInstance().getCurrentUser().getUid()))).updateChildren(map);
						map.clear();
						
						i.setClass(getContext().getApplicationContext(), ChatActivity.class);
								i.putExtra("seconduser", _data.get((int)_position).get("uid").toString());
								i.putExtra("firstuser", FirebaseAuth.getInstance().getCurrentUser().getUid());
								startActivity(i);
						}
				});
				body.setOnLongClickListener(new View.OnLongClickListener() {
					@Override
					public boolean onLongClick(View _view) {
						delete.setTitle("Info");
						delete.setMessage("Are you sure you want delete this chat? Messages with this person will not delete!!");
						delete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								ms.child(_data.get((int)_position).get("uid").toString()).removeValue();
							}
						});
						delete.setNegativeButton("No", new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface _dialog, int _which) {
								
							}
						});
						delete.create().show();
						return true;
					}
				});
			}
			_setTime(Double.parseDouble(messagesmap.get((int)_position).get("timestamp").toString()), time);
			if (_data.get((int)_position).containsKey("read")) {
				if (_data.get((int)_position).get("read").toString().equals("null")) {
					_ImageColor(tick, "#607D8B");
					read = false;
				}
				else {
					if (_data.get((int)_position).get("read").toString().equals("false")) {
						_ImageColor(tick, "#607D8B");
						tick.setImageResource(R.drawable.ic_done_all_black);
						read = false;
					}
					else {
						if (_data.get((int)_position).get("read").toString().equals("true")) {
							tick.setImageResource(R.drawable.ic_done_all_black);
							_ImageColor(tick, "#651FFF");
							read = true;
						}
					}
				}
			}
			if (online.get(_data.get((int)_position).get("uid").toString()).toString().equals("true")) {
				status_body.setVisibility(View.VISIBLE);
			}
			else {
				status_body.setVisibility(View.GONE);
			}
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
}