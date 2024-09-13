package com.mah.insstory.MAH;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.content.DialogInterface;
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
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;


public class StoryviewActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private boolean stoppedStory = false;
	private double tm_difference = 0;
	private HashMap<String, Object> map = new HashMap<>();
	private String fontName = "";
	private String typeace = "";
	private String a_second = "";
	private String second = "";
	private String a_minute = "";
	private String minute = "";
	private String hours = "";
	private String days = "";
	private String delete_dialog_title = "";
	private String delete_dialog_text = "";
	private String delete_dialog_b1 = "";
	private String delete_dialog_b2 = "";
	private String story_deleted = "";
	
	private RelativeLayout body;
	private ImageView story_image;
	private LinearLayout relative_layout_main;
	private LinearLayout top;
	private LinearLayout middle;
	private LinearLayout bottom;
	private ImageView back;
	private LinearLayout pbd;
	private ImageView more;
	private CircleImageView profile_image;
	private LinearLayout pbd_c;
	private TextView username;
	private TextView push_date;
	
	private TimerTask timer;
	private DatabaseReference sdb = _firebase.getReference("social/stories");
	private ChildEventListener _sdb_child_listener;
	private SharedPreferences lang;
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
	private Calendar ct = Calendar.getInstance();
	private Calendar time2 = Calendar.getInstance();
	private StorageReference ssdb = _firebase_storage.getReference("social/stories");
	private OnCompleteListener<Uri> _ssdb_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _ssdb_download_success_listener;
	private OnSuccessListener _ssdb_delete_success_listener;
	private OnProgressListener _ssdb_upload_progress_listener;
	private OnProgressListener _ssdb_download_progress_listener;
	private OnFailureListener _ssdb_failure_listener;
	
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.storyview);
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
		body = findViewById(R.id.body);
		story_image = findViewById(R.id.story_image);
		relative_layout_main = findViewById(R.id.relative_layout_main);
		top = findViewById(R.id.top);
		middle = findViewById(R.id.middle);
		bottom = findViewById(R.id.bottom);
		back = findViewById(R.id.back);
		pbd = findViewById(R.id.pbd);
		more = findViewById(R.id.more);
		profile_image = findViewById(R.id.profile_image);
		pbd_c = findViewById(R.id.pbd_c);
		username = findViewById(R.id.username);
		push_date = findViewById(R.id.push_date);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		dialog = new AlertDialog.Builder(this);
		
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		_sdb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					_setTime(Double.parseDouble(_childValue.get("push_date").toString()), push_date);
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("image").toString())).into(story_image);
					if (_childValue.get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
						more.setVisibility(View.VISIBLE);
					}
					else {
						more.setVisibility(View.GONE);
					}
					more.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View _view) {
							dialog.setTitle(delete_dialog_title);
							dialog.setIcon(R.drawable.delete);
							dialog.setMessage(delete_dialog_text);
							dialog.setPositiveButton(delete_dialog_b1, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									sdb.child(_childValue.get("uid_key").toString()).removeValue();
									map = new HashMap<>();
									map.put("story", "false");
									udb.child(getIntent().getStringExtra("uid")).updateChildren(map);
									map.clear();
									SketchwareUtil.showMessage(getApplicationContext(), story_deleted);
									_firebase_storage.getReferenceFromUrl(_childValue.get("image").toString()).delete().addOnSuccessListener(_ssdb_delete_success_listener).addOnFailureListener(_ssdb_failure_listener);
									finish();
								}
							});
							dialog.setNegativeButton(delete_dialog_b2, new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface _dialog, int _which) {
									
								}
							});
							dialog.create().show();
						}
					});
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
		sdb.addChildEventListener(_sdb_child_listener);
		
		_ssdb_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_ssdb_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_ssdb_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				
			}
		};
		
		_ssdb_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_ssdb_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_ssdb_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(getIntent().getStringExtra("uid"))) {
					username.setText(_childValue.get("username").toString());
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(profile_image);
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
		username.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
	}
	
	@Override
	public void onBackPressed() {
		finish();
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		_Languages();
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
			Window w =StoryviewActivity.this.getWindow();
			w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(0xFF000000);
		}
		more.setVisibility(View.GONE);
	}
	public void _setTime(final double _currentTime, final TextView _txt) {
		tm_difference = ct.getTimeInMillis() - _currentTime;
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
						time2.setTimeInMillis((long)(_currentTime));
						_txt.setText(new SimpleDateFormat("dd MMM yyyy | HH:mm").format(time2.getTime()));
					}
				}
			}
		}
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
	
	
	public void _Languages() {
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
		a_second = "Bir saniye önce";
		second = " Saniye önce";
		a_minute = "Bir dakika önce";
		minute = " Dakika önce";
		hours = " Saat önce";
		days = " Gün önce";
		delete_dialog_title = "Hikayeyi Sil?";
		delete_dialog_text = "Hikayeyi kaldırmak istediğine emin misin?";
		delete_dialog_b1 = "EVET";
		delete_dialog_b2 = "HAYIR";
		story_deleted = "Hikaye Kaldırıldı";
	}
	
	
	public void _EnLang() {
		a_second = "A second ago";
		second = " Second ago";
		a_minute = "A minute ago";
		minute = " Minute ago";
		hours = " Hours ago";
		days = " days ago";
		delete_dialog_title = "Delete Story?";
		delete_dialog_text = "Are you sure you want to remove the story?";
		delete_dialog_b1 = "YES";
		delete_dialog_b2 = "NO";
		story_deleted = "Story Deleted";
	}
	
	
	public void _InLang() {
		a_second = "Detik yang lalu";
		second = " Detik lalu";
		a_minute = "Menit yang lalu";
		minute = " Menit lalu";
		hours = " Jam lalu";
		days = " hari lalu";
		delete_dialog_title = "Hapus Cerita?";
		delete_dialog_text = "Anda yakin ingin menghapus Cerita?";
		delete_dialog_b1 = "Iya";
		delete_dialog_b2 = "Tidak";
		story_deleted = "Cerita Dihapus";
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