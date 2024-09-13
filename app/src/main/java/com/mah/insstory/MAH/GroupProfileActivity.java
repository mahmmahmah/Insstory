package com.mah.insstory.MAH;

import android.animation.*;
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
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class GroupProfileActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private String copied = "";
	private String okay_string = "";
	private String no_string = "";
	private String leave_dialog_message = "";
	private String fontName = "";
	private String typeace = "";
	private String UsersString = "";
	
	private LinearLayout body;
	private CardView top;
	private LinearLayout icon;
	private TextView group_name;
	private TextView group_bio;
	private LinearLayout group_admins;
	private LinearLayout group_members;
	private LinearLayout group_privacy;
	private LinearLayout group_share;
	private LinearLayout group_about;
	private LinearLayout group_leave;
	private LinearLayout top_;
	private ImageView back;
	private TextView title;
	private ImageView more;
	private CircleImageView group_avatar;
	private CircleImageView group_edit;
	private ImageView group_admins_icon;
	private TextView group_admins_text;
	private ImageView group_members_icon;
	private TextView group_members_text;
	private ImageView group_privacy_icon;
	private TextView group_privacy_text;
	private ImageView group_share_icon;
	private TextView group_share_text;
	private ImageView group_about_icon;
	private TextView group_about_text;
	private ImageView group_leave_icon;
	private TextView group_leave_text;
	
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference gdb = _firebase.getReference("groups/group");
	private ChildEventListener _gdb_child_listener;
	private SharedPreferences save;
	private SharedPreferences lang;
	private Intent intent = new Intent();
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
	private DatabaseReference gudb = _firebase.getReference("+UsersString+");
	private ChildEventListener _gudb_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.group_profile);
		initialize(_savedInstanceState);
		FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		body = findViewById(R.id.body);
		top = findViewById(R.id.top);
		icon = findViewById(R.id.icon);
		group_name = findViewById(R.id.group_name);
		group_bio = findViewById(R.id.group_bio);
		group_admins = findViewById(R.id.group_admins);
		group_members = findViewById(R.id.group_members);
		group_privacy = findViewById(R.id.group_privacy);
		group_share = findViewById(R.id.group_share);
		group_about = findViewById(R.id.group_about);
		group_leave = findViewById(R.id.group_leave);
		top_ = findViewById(R.id.top_);
		back = findViewById(R.id.back);
		title = findViewById(R.id.title);
		more = findViewById(R.id.more);
		group_avatar = findViewById(R.id.group_avatar);
		group_edit = findViewById(R.id.group_edit);
		group_admins_icon = findViewById(R.id.group_admins_icon);
		group_admins_text = findViewById(R.id.group_admins_text);
		group_members_icon = findViewById(R.id.group_members_icon);
		group_members_text = findViewById(R.id.group_members_text);
		group_privacy_icon = findViewById(R.id.group_privacy_icon);
		group_privacy_text = findViewById(R.id.group_privacy_text);
		group_share_icon = findViewById(R.id.group_share_icon);
		group_share_text = findViewById(R.id.group_share_text);
		group_about_icon = findViewById(R.id.group_about_icon);
		group_about_text = findViewById(R.id.group_about_text);
		group_leave_icon = findViewById(R.id.group_leave_icon);
		group_leave_text = findViewById(R.id.group_leave_text);
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		
		group_admins.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), GroupAdminsActivity.class);
				intent.putExtra("group_key", getIntent().getStringExtra("group_key"));
				startActivity(intent);
			}
		});
		
		group_members.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), GroupMembersActivity.class);
				intent.putExtra("group_key", getIntent().getStringExtra("group_key"));
				startActivity(intent);
			}
		});
		
		group_privacy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), GroupPrivacyActivity.class);
				intent.putExtra("group_key", getIntent().getStringExtra("group_key"));
				startActivity(intent);
			}
		});
		
		group_share.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", "https://open.uptime.club/?type=group&id=".concat(getIntent().getStringExtra("group_key"))));
				SketchwareUtil.showMessage(getApplicationContext(), copied);
			}
		});
		
		group_leave.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dialog.setMessage(leave_dialog_message);
				dialog.setPositiveButton(okay_string, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
						group_admins.setVisibility(View.GONE);
						group_members.setVisibility(View.GONE);
						group_privacy.setVisibility(View.GONE);
						group_leave.setVisibility(View.GONE);
						group_edit.setVisibility(View.GONE);
					}
				});
				dialog.setNegativeButton(no_string, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		group_edit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), GroupEditActivity.class);
				intent.putExtra("group_key", getIntent().getStringExtra("group_key"));
				startActivity(intent);
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
					title.setText(_childValue.get("group_name").toString());
					group_name.setText(_childValue.get("group_name").toString());
					group_bio.setText(_childValue.get("group_bio").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("group_avatar").toString())).into(group_avatar);
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
		gdb.addChildEventListener(_gdb_child_listener);
		
		_gudb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("member_type").toString().equals("member")) {
						group_edit.setVisibility(View.GONE);
						group_privacy.setVisibility(View.GONE);
						group_admins.setVisibility(View.GONE);
						group_members.setVisibility(View.GONE);
						group_leave.setVisibility(View.VISIBLE);
					}
					else {
						group_edit.setVisibility(View.VISIBLE);
						group_privacy.setVisibility(View.VISIBLE);
						group_admins.setVisibility(View.VISIBLE);
						group_members.setVisibility(View.VISIBLE);
						group_leave.setVisibility(View.VISIBLE);
					}
				}
				else {
					group_edit.setVisibility(View.GONE);
					group_privacy.setVisibility(View.GONE);
					group_admins.setVisibility(View.GONE);
					group_members.setVisibility(View.GONE);
					group_leave.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					if (_childValue.get("member_type").toString().equals("member")) {
						group_edit.setVisibility(View.GONE);
						group_privacy.setVisibility(View.GONE);
						group_admins.setVisibility(View.GONE);
						group_members.setVisibility(View.GONE);
						group_leave.setVisibility(View.VISIBLE);
					}
					else {
						group_edit.setVisibility(View.VISIBLE);
						group_privacy.setVisibility(View.VISIBLE);
						group_admins.setVisibility(View.VISIBLE);
						group_members.setVisibility(View.VISIBLE);
						group_leave.setVisibility(View.VISIBLE);
					}
				}
				else {
					group_edit.setVisibility(View.GONE);
					group_privacy.setVisibility(View.GONE);
					group_admins.setVisibility(View.GONE);
					group_members.setVisibility(View.GONE);
					group_leave.setVisibility(View.GONE);
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
		gudb.addChildEventListener(_gudb_child_listener);
		
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
		group_admins.setVisibility(View.GONE);
		group_members.setVisibility(View.GONE);
		group_privacy.setVisibility(View.GONE);
		group_about.setVisibility(View.VISIBLE);
		group_leave.setVisibility(View.GONE);
		group_edit.setVisibility(View.GONE);
		group_share.setVisibility(View.VISIBLE);
		/*DbCustomStart*/
		gudb.removeEventListener(_gudb_child_listener);
		UsersString = "groups/group/".concat(getIntent().getStringExtra("group_key").concat("/".concat("users")));
		gudb =
		_firebase.getReference(UsersString);
		gudb.addChildEventListener(_gudb_child_listener);
		/*DbCustomEnd*/
		more.setVisibility(View.GONE);
		group_about.setVisibility(View.GONE);
	}
	
	@Override
	public void onStart() {
		super.onStart();
		_ThemeCustom();
		_Language();
		_changeActivityFont("youtubesansregular");
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/youtubesansdarkbold.otf"), 0);
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
	
	
	public void _changeActivityFont(final String _fontname) {
		fontName = "fonts/".concat(_fontname.concat(".otf"));
		overrideFonts(this,getWindow().getDecorView()); 
	} 
	private void overrideFonts(final android.content.Context context, final View v) {
		
		try {
			Typeface 
			typeace = Typeface.createFromAsset(getAssets(), fontName);;
			if ((v instanceof ViewGroup)) {
				ViewGroup vg = (ViewGroup) v;
				for (int i = 0;
				i < vg.getChildCount();
				i++) {
					View child = vg.getChildAt(i);
					overrideFonts(context, child);
				}
			}
			else {
				if ((v instanceof TextView)) {
					((TextView) v).setTypeface(typeace);
				}
				else {
					if ((v instanceof EditText )) {
						((EditText) v).setTypeface(typeace);
					}
					else {
						if ((v instanceof Button)) {
							((Button) v).setTypeface(typeace);
						}
					}
				}
			}
		}
		catch(Exception e)
		
		{
			SketchwareUtil.showMessage(getApplicationContext(), "Error Loading Font");
		};
	}
	
	
	public void _Language() {
		if (lang.getString("language", "").equals("")) {
			_TrLang();
		}
		if (lang.getString("language", "").equals("english")) {
			_EnLang();
		}
	}
	
	
	public void _TrLang() {
		group_admins_text.setText("Yöneticiler");
		group_members_text.setText("Üyeler");
		group_privacy_text.setText("Gizlilik");
		group_share_text.setText("Paylaş");
		group_about_text.setText("Hakkında");
		group_leave_text.setText("Gruptan Ayrıl");
		copied = "Bağlantı Kopyalandı";
		okay_string = "EVET";
		no_string = "HAYIR";
		leave_dialog_message = "Bu gruptan ayrılmak istediğini emin misin?";
	}
	
	
	public void _EnLang() {
		group_admins_text.setText("Admins");
		group_members_text.setText("Members");
		group_privacy_text.setText("Privacy");
		group_share_text.setText("Share");
		group_about_text.setText("About");
		group_leave_text.setText("Leave Group");
		copied = "Link Copied";
		okay_string = "YES";
		no_string = "NO";
		leave_dialog_message = "Are you sure you want to leave this group?";
	}
	
	
	public void _ThemeCustom() {
		_ImageColor(group_edit, "#FFFFFF");
		if (save.getString("theme", "").equals("light")) {
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
			getWindow().setStatusBarColor(0xFFFFFFFF);
			group_edit.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF2196F3));
			top_.setBackgroundColor(0xFFFFFFFF);
			body.setBackgroundColor(0xFFFFFFFF);
			group_name.setTextColor(0xFF000000);
			group_bio.setTextColor(0xFF607D8B);
			title.setTextColor(0xFF000000);
			group_admins_text.setTextColor(0xFF000000);
			group_members_text.setTextColor(0xFF000000);
			group_privacy_text.setTextColor(0xFF000000);
			group_about_text.setTextColor(0xFF000000);
			group_leave_text.setTextColor(0xFF000000);
			_ImageColor(back, "#000000");
			_ImageColor(more, "#000000");
			_ImageColor(group_admins_icon, "#000000");
			_ImageColor(group_members_icon, "#000000");
			_ImageColor(group_privacy_icon, "#000000");
			_ImageColor(group_about_icon, "#000000");
			_ImageColor(group_leave_icon, "#000000");
		}
		else {
			if (save.getString("theme", "").equals("dark")) {
				if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
					Window w =GroupProfileActivity.this.getWindow();
					w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
					w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF212121);
				}
				group_edit.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFF2196F3));
				top_.setBackgroundColor(0xFF212121);
				body.setBackgroundColor(0xFF000000);
				group_name.setTextColor(0xFFFFFFFF);
				group_bio.setTextColor(0xFF607D8B);
				title.setTextColor(0xFFFFFFFF);
				group_admins_text.setTextColor(0xFFFFFFFF);
				group_members_text.setTextColor(0xFFFFFFFF);
				group_privacy_text.setTextColor(0xFFFFFFFF);
				group_about_text.setTextColor(0xFFFFFFFF);
				group_leave_text.setTextColor(0xFFFFFFFF);
				_ImageColor(back, "#FFFFFF");
				_ImageColor(more, "#FFFFFF");
				_ImageColor(group_admins_icon, "#FFFFFF");
				_ImageColor(group_members_icon, "#FFFFFF");
				_ImageColor(group_privacy_icon, "#FFFFFF");
				_ImageColor(group_about_icon, "#FFFFFF");
				_ImageColor(group_leave_icon, "#FFFFFF");
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