package com.mah.insstory.MAH;

import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
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
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aghajari.emojiview.*;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.airbnb.lottie.LottieAnimationView;;

public class RegisterActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String fontName = "";
	private String typeace = "";
	private String FillAlItemsString = "";
	private String PATTERN = "";
	private String invalidcharacters = "";
	private String AvatarPath = "";
	private String AvatarName = "";
	private String AvatarUrl = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String p = "";
	private String code = "";
	private String codeSent = "";
	
	private LinearLayout body;
	private ScrollView scroll;
	private LinearLayout scroll_in_body;
	private LinearLayout linear3;
	private CircleImageView profile_image;
	private EditText name;
	private EditText username;
	private EditText biography;
	private LinearLayout linear5;
	private EditText edittext1;
	private EditText email;
	private EditText password;
	private CheckBox terms;
	private Button signup;
	private TextView signin_inf;
	private TextView code_phone;
	private EditText phonenumber;
	
	private Intent intent = new Intent();
	private SharedPreferences lang;
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private StorageReference pdb = _firebase_storage.getReference("profile/avatar");
	private OnCompleteListener<Uri> _pdb_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _pdb_download_success_listener;
	private OnSuccessListener _pdb_delete_success_listener;
	private OnProgressListener _pdb_upload_progress_listener;
	private OnProgressListener _pdb_download_progress_listener;
	private OnFailureListener _pdb_failure_listener;
	
	private TimerTask timer;
	private Calendar cc = Calendar.getInstance();
	private RequestNetwork countryCode;
	private RequestNetwork.RequestListener _countryCode_request_listener;
	private FirebaseAuth fa;
	private OnCompleteListener<AuthResult> _fa_create_user_listener;
	private OnCompleteListener<AuthResult> _fa_sign_in_listener;
	private OnCompleteListener<Void> _fa_reset_password_listener;
	private OnCompleteListener<Void> fa_updateEmailListener;
	private OnCompleteListener<Void> fa_updatePasswordListener;
	private OnCompleteListener<Void> fa_emailVerificationSentListener;
	private OnCompleteListener<Void> fa_deleteUserListener;
	private OnCompleteListener<Void> fa_updateProfileListener;
	private OnCompleteListener<AuthResult> fa_phoneAuthListener;
	private OnCompleteListener<AuthResult> fa_googleSignInListener;
	
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
	
	private PhoneAuthProvider.OnVerificationStateChangedCallbacks phoneauth;private PhoneAuthProvider.ForceResendingToken phoneauth_resendToken;
	private SharedPreferences save;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.register);
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
		scroll = findViewById(R.id.scroll);
		scroll_in_body = findViewById(R.id.scroll_in_body);
		linear3 = findViewById(R.id.linear3);
		profile_image = findViewById(R.id.profile_image);
		name = findViewById(R.id.name);
		username = findViewById(R.id.username);
		biography = findViewById(R.id.biography);
		linear5 = findViewById(R.id.linear5);
		edittext1 = findViewById(R.id.edittext1);
		email = findViewById(R.id.email);
		password = findViewById(R.id.password);
		terms = findViewById(R.id.terms);
		signup = findViewById(R.id.signup);
		signin_inf = findViewById(R.id.signin_inf);
		code_phone = findViewById(R.id.code_phone);
		phonenumber = findViewById(R.id.phonenumber);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		countryCode = new RequestNetwork(this);
		fa = FirebaseAuth.getInstance();
		auth = FirebaseAuth.getInstance();
		save = getSharedPreferences("save", Activity.MODE_PRIVATE);
		
		profile_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		signup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ((name.getText().toString().equals("") || (phonenumber.getText().toString().equals("") || edittext1.getText().toString().equals(""))) || (username.getText().toString().equals("") || (biography.getText().toString().equals("") || (email.getText().toString().equals("") || password.getText().toString().equals(""))))) {
					SketchwareUtil.showMessage(getApplicationContext(), FillAlItemsString);
				}
				else {
					if (terms.isChecked()) {
						pdb.child(AvatarName).putFile(Uri.fromFile(new File(AvatarPath))).addOnFailureListener(_pdb_failure_listener).addOnProgressListener(_pdb_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
							@Override
							public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
								return pdb.child(AvatarName).getDownloadUrl();
							}}).addOnCompleteListener(_pdb_upload_success_listener);
						_telegramLoaderDialog(true);
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "You Must Accept Terms And Conditions");
					}
				}
			}
		});
		
		signin_inf.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				intent.setClass(getApplicationContext(), LoginActivity.class);
				startActivity(intent);
				finish();
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
		
		_pdb_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_pdb_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				
			}
		};
		
		_pdb_upload_success_listener = new OnCompleteListener<Uri>() {
			@Override
			public void onComplete(Task<Uri> _param1) {
				final String _downloadUrl = _param1.getResult().toString();
				AvatarUrl = _downloadUrl;
				auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(RegisterActivity.this, _auth_create_user_listener);
			}
		};
		
		_pdb_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
			}
		};
		
		_pdb_delete_success_listener = new OnSuccessListener() {
			@Override
			public void onSuccess(Object _param1) {
				
			}
		};
		
		_pdb_failure_listener = new OnFailureListener() {
			@Override
			public void onFailure(Exception _param1) {
				final String _message = _param1.getMessage();
				
			}
		};
		
		_countryCode_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				code_phone.setText(_response);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
		
		fa_updateEmailListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_updatePasswordListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_emailVerificationSentListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_deleteUserListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_phoneAuthListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		fa_updateProfileListener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		fa_googleSignInListener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> task) {
				final boolean _success = task.isSuccessful();
				final String _errorMessage = task.getException() != null ? task.getException().getMessage() : "";
				
			}
		};
		
		_fa_create_user_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				
			}
		};
		
		_fa_sign_in_listener = new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete(Task<AuthResult> _param1) {
				final boolean _success = _param1.isSuccessful();
				final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
				if (_success) {
					
					linear3.setVisibility(View.VISIBLE);
				}
			}
		};
		
		_fa_reset_password_listener = new OnCompleteListener<Void>() {
			@Override
			public void onComplete(Task<Void> _param1) {
				final boolean _success = _param1.isSuccessful();
				
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
				if (_success) {
					cc = Calendar.getInstance();
					map = new HashMap<>();
					map.put("yourname", name.getText().toString());
					map.put("username", username.getText().toString());
					map.put("code_phone_number", code_phone.getText().toString());
					map.put("phone_num", phonenumber.getText().toString());
					map.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
					map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
					map.put("avatar", AvatarUrl);
					map.put("account_type", "user");
					map.put("bio", biography.getText().toString());
					map.put("verify", "false");
					map.put("blocked", "false");
					map.put("story", "false");
					map.put("online", "true");
					map.put("account_status", "active");
					map.put("account_mode", "free");
					map.put("hide_status", "false");
					map.put("club_points", (int)(1000));
					map.put("private_account", "false");
					udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
					map.clear();
					timer = new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {
								@Override
								public void run() {
									intent.setClass(getApplicationContext(), DashboardActivity.class);
									startActivity(intent);
									_telegramLoaderDialog(false);
								}
							});
						}
					};
					_timer.schedule(timer, (int)(2000));
				}
				else {
					SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
				}
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
		countryCode.startRequestNetwork(RequestNetworkController.GET, "https://ipapi.co/country_calling_code", "Calling_code", _countryCode_request_listener);
		PATTERN = "^[a-z0-9_-]{5,15}$";
		
		signup.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue_gradient_button));
		profile_image.setElevation((float)5);
		_AdvancedView(name, "#FFAB91", "#F4511E", "#009688", 20, 10, "#FFAB91", 10);
		_AdvancedView(username, "#FFAB91", "#F4511E", "#009688", 20, 10, "#FFAB91", 10);
		_AdvancedView(biography, "#FFAB91", "#F4511E", "#009688", 20, 10, "#FFAB91", 10);
		_AdvancedView(edittext1, "#FFAB91", "#F4511E", "#009688", 20, 10, "#FFAB91", 10);
		_AdvancedView(email, "#FFAB91", "#F4511E", "#009688", 20, 10, "#FFAB91", 10);
		_AdvancedView(phonenumber, "#FFAB91", "#F4511E", "#009688", 20, 10, "#FFAB91", 10);
		_AdvancedView(password, "#FFAB91", "#F4511E", "#009688", 20, 10, "#FFAB91", 10);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
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
				AvatarPath = _filePath.get((int)(0));
				if (AvatarPath.endsWith(".png")) {
					AvatarName = udb.push().getKey().concat(".png");
					profile_image.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(AvatarPath, 1024, 1024));
				}
				else {
					if (AvatarPath.endsWith(".jpg")) {
						AvatarName = udb.push().getKey().concat(".jpg");
						profile_image.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(AvatarPath, 1024, 1024));
					}
					else {
						SketchwareUtil.showMessage(getApplicationContext(), "Invalid File Type");
					}
				}
			}
			else {
				
			}
			break;
			default:
			break;
		}
	}
	
	
	@Override
	public void onStart() {
		super.onStart();
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		_Language();
	}
	public void _GradientDrawable(final View _view, final double _radius, final double _stroke, final double _shadow, final String _color, final String _borderColor, final boolean _ripple, final boolean _clickAnim, final double _animDuration) {
		if (_ripple) {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
			android.content.res.ColorStateList clrb = new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{Color.parseColor("#9E9E9E")});
			android.graphics.drawable.RippleDrawable ripdrb = new android.graphics.drawable.RippleDrawable(clrb , gd, null);
			_view.setClickable(true);
			_view.setBackground(ripdrb);
		}
		else {
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.parseColor(_color));
			gd.setCornerRadius((int)_radius);
			gd.setStroke((int)_stroke,Color.parseColor(_borderColor));
			_view.setBackground(gd);
			if (Build.VERSION.SDK_INT >= 21){
				_view.setElevation((int)_shadow);}
		}
		if (_clickAnim) {
			_view.setOnTouchListener(new View.OnTouchListener() {
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()){
						case MotionEvent.ACTION_DOWN:{
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues(0.9f);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues(0.9f);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							break;
						}
						case MotionEvent.ACTION_UP:{
							
							ObjectAnimator scaleX = new ObjectAnimator();
							scaleX.setTarget(_view);
							scaleX.setPropertyName("scaleX");
							scaleX.setFloatValues((float)1);
							scaleX.setDuration((int)_animDuration);
							scaleX.start();
							
							ObjectAnimator scaleY = new ObjectAnimator();
							scaleY.setTarget(_view);
							scaleY.setPropertyName("scaleY");
							scaleY.setFloatValues((float)1);
							scaleY.setDuration((int)_animDuration);
							scaleY.start();
							
							break;
						}
					}
					return false;
				}
			});
		}
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
		name.setHint("İsim");
		username.setHint("Kullanıcı Adı");
		biography.setHint("Biyografi");
		email.setHint("E-Posta");
		password.setHint("Şifre");
		terms.setText("Şartları ve Koşulları Kabul Ediyorum");
		signup.setText("Hesabı Oluştur");
		signin_inf.setText("Zaten Hesabın Varmı?");
		
		FillAlItemsString = "Tüm Öğeleri Doldurun";
		invalidcharacters = "Kullanıcı adı geçersiz karakterler içeriyor";
	}
	
	
	public void _EnLang() {
		name.setHint("Name");
		username.setHint("Username");
		biography.setHint("Biography");
		email.setHint("E-Mail");
		password.setHint("Password");
		terms.setText("I Accept the Terms and Conditions");
		signup.setText("Sign Up");
		signin_inf.setText("Already Have an Account?");
		
		FillAlItemsString = "Fill All Items";
		invalidcharacters = "Username contains invalid characters";
	}
	
	
	public void _InLang() {
		name.setHint("Nama");
		username.setHint("Nama Pengguna");
		biography.setHint("Biografi");
		email.setHint("E-Mail");
		password.setHint("Kata Sandi");
		terms.setText("Saya menerima Syarat dan Ketentuan yang berlaku");
		signup.setText("Daftar");
		signin_inf.setText("Sudah Mempunyai Akun?");
		
		FillAlItemsString = "Isi Semua Item";
		invalidcharacters = "Nama pengguna mengandung karakter yang tidak valid";
	}
	
	
	public void _Send() {
		p = "";
		com.google.firebase.auth.PhoneAuthProvider.getInstance().verifyPhoneNumber(p, 60, java.util.concurrent.TimeUnit.SECONDS, this, mCallbacks);
		
	}
	
	
	
	com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
		
		@Override
		
		public void onVerificationCompleted(com.google.firebase.auth.PhoneAuthCredential phoneAuthCredential) {
			
			showMessage("Verification completed");
			
		}
		
		@Override
		
		public void onVerificationFailed(com.google.firebase.FirebaseException e) {
			
			showMessage(e.toString());
			
		}
		
		@Override
		
		public void onCodeSent(String s, com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken forceResendingToken) {
			
			super.onCodeSent(s, forceResendingToken);
			
			codeSent = s;
			
		}};{
	}
	
	
	public void _Virfity() {
		code = "";
		com.google.firebase.auth.PhoneAuthCredential credential = com.google.firebase.auth.PhoneAuthProvider.getCredential(codeSent, code);
		
		signInWithPhoneAuthCredential(credential);
		
	}
	
	private void signInWithPhoneAuthCredential(com.google.firebase.auth.PhoneAuthCredential credential) {
		
		fa.signInWithCredential(credential) .addOnCompleteListener(this, _fa_sign_in_listener);
		
	}
	
	{
	}
	
	
	public void _nwnwjejjsjs(final boolean _msm) {
		final AlertDialog dialog1 = new AlertDialog.Builder(RegisterActivity.this).create();
		View inflate = getLayoutInflater().inflate(R.layout.custom,null); 
		dialog1.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		dialog1.setView(inflate);
		LottieAnimationView li1 = (LottieAnimationView) inflate.findViewById(R.id.li1);
		
		LinearLayout i1 = (LinearLayout) inflate.findViewById(R.id.i1);
		li1.setAnimationFromJson("{\"v\":\"4.8.0\",\"meta\":{\"g\":\"LottieFiles AE 1.1.0\",\"a\":\"\",\"k\":\"\",\"d\":\"\",\"tc\":\"#1a1a1a\"},\"fr\":60,\"ip\":0,\"op\":296,\"w\":88,\"h\":88,\"nm\":\"Loop\",\"ddd\":0,\"assets\":[],\"layers\":[{\"ddd\":0,\"ind\":1,\"ty\":4,\"nm\":\"Group 1522 Outlines\",\"sr\":1,\"ks\":{\"o\":{\"a\":0,\"k\":100,\"ix\":11},\"r\":{\"a\":1,\"k\":[{\"i\":{\"x\":[0.833],\"y\":[0.833]},\"o\":{\"x\":[0.167],\"y\":[0.167]},\"t\":0,\"s\":[139]},{\"t\":295,\"s\":[2299]}],\"ix\":10},\"p\":{\"a\":0,\"k\":[44,44,0],\"ix\":2},\"a\":{\"a\":0,\"k\":[44,44,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100,100],\"ix\":6}},\"ao\":0,\"shapes\":[{\"ty\":\"gr\",\"it\":[{\"ind\":0,\"ty\":\"sh\",\"ix\":1,\"ks\":{\"a\":0,\"k\":{\"i\":[[0.028,-0.026],[0.552,0.607],[-1.775,11.035],[-12.156,3.838],[-6.332,-1.757],[-1.687,-0.746],[0.828,-2.778],[3.437,0.818],[0.663,0.264],[8.067,-3.763],[2.679,-4.374],[-2.723,-7.972],[-3.281,-3.227],[-0.077,-0.078]],\"o\":[[-0.582,-0.58],[-7.475,-8.217],[2.028,-12.606],[6.286,-1.985],[1.775,0.492],[2.599,1.149],[-1.015,3.404],[-0.689,-0.164],[-8.195,-3.261],[-4.62,2.155],[-4.441,7.251],[1.494,4.376],[0.078,0.077],[-0.028,0.026]],\"v\":[[-14.317,29.751],[-16.06,28.009],[-24.791,-0.88],[-1.585,-27.622],[17.366,-27.994],[22.563,-26.074],[25.738,-18.803],[18.087,-14.327],[16.083,-15.08],[-8.34,-14.579],[-19.37,-4.796],[-21.757,18.096],[-14.467,29.439],[-14.234,29.673]],\"c\":true},\"ix\":2},\"nm\":\"Path 1\",\"mn\":\"ADBE Vector Shape - Group\",\"hd\":false},{\"ty\":\"gf\",\"o\":{\"a\":0,\"k\":100,\"ix\":10},\"r\":1,\"bm\":0,\"g\":{\"p\":2,\"k\":{\"a\":0,\"k\":[0,0.4745098039215686,0.34901960784313724,0.7372549019607844,1,0.8745098039215686,0.5647058823529412,0.18823529411764706],\"ix\":9}},\"s\":{\"a\":0,\"k\":[46.813,-21.663],\"ix\":5},\"e\":{\"a\":0,\"k\":[-40.927,-12.884],\"ix\":6},\"t\":1,\"nm\":\"Gradient Fill 1\",\"mn\":\"ADBE Vector Graphic - G-Fill\",\"hd\":false},{\"ty\":\"tr\",\"p\":{\"a\":0,\"k\":[35.462,39.957],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100],\"ix\":3},\"r\":{\"a\":0,\"k\":0,\"ix\":6},\"o\":{\"a\":0,\"k\":100,\"ix\":7},\"sk\":{\"a\":0,\"k\":0,\"ix\":4},\"sa\":{\"a\":0,\"k\":0,\"ix\":5},\"nm\":\"Transform\"}],\"nm\":\"Group 1\",\"np\":2,\"cix\":2,\"bm\":0,\"ix\":1,\"mn\":\"ADBE Vector Group\",\"hd\":false},{\"ty\":\"gr\",\"it\":[{\"ind\":0,\"ty\":\"sh\",\"ix\":1,\"ks\":{\"a\":0,\"k\":{\"i\":[[-0.028,0.026],[-0.552,-0.607],[1.775,-11.035],[12.156,-3.838],[6.332,1.757],[1.687,0.746],[-0.828,2.778],[-3.437,-0.818],[-0.663,-0.264],[-8.067,3.763],[-2.679,4.375],[2.723,7.972],[3.281,3.227],[0.078,0.078]],\"o\":[[0.582,0.58],[7.475,8.217],[-2.028,12.606],[-6.286,1.985],[-1.775,-0.492],[-2.599,-1.149],[1.015,-3.404],[0.689,0.164],[8.195,3.261],[4.62,-2.155],[4.441,-7.251],[-1.494,-4.376],[-0.078,-0.077],[0.028,-0.026]],\"v\":[[14.317,-29.751],[16.06,-28.009],[24.791,0.88],[1.585,27.622],[-17.366,27.994],[-22.563,26.074],[-25.738,18.803],[-18.087,14.327],[-16.083,15.08],[8.34,14.579],[19.37,4.796],[21.757,-18.096],[14.467,-29.439],[14.234,-29.673]],\"c\":true},\"ix\":2},\"nm\":\"Path 1\",\"mn\":\"ADBE Vector Shape - Group\",\"hd\":false},{\"ty\":\"gf\",\"o\":{\"a\":0,\"k\":100,\"ix\":10},\"r\":1,\"bm\":0,\"g\":{\"p\":2,\"k\":{\"a\":0,\"k\":[0,0.4745098039215686,0.34901960784313724,0.7372549019607844,1,0.8745098039215686,0.5647058823529412,0.18823529411764706],\"ix\":9}},\"s\":{\"a\":0,\"k\":[-13.683,31.061],\"ix\":5},\"e\":{\"a\":0,\"k\":[21.634,-16.316],\"ix\":6},\"t\":1,\"nm\":\"Gradient Fill 1\",\"mn\":\"ADBE Vector Graphic - G-Fill\",\"hd\":false},{\"ty\":\"tr\",\"p\":{\"a\":0,\"k\":[51.676,47.182],\"ix\":2},\"a\":{\"a\":0,\"k\":[0,0],\"ix\":1},\"s\":{\"a\":0,\"k\":[100,100],\"ix\":3},\"r\":{\"a\":0,\"k\":0,\"ix\":6},\"o\":{\"a\":0,\"k\":100,\"ix\":7},\"sk\":{\"a\":0,\"k\":0,\"ix\":4},\"sa\":{\"a\":0,\"k\":0,\"ix\":5},\"nm\":\"Transform\"}],\"nm\":\"Group 2\",\"np\":2,\"cix\":2,\"bm\":0,\"ix\":2,\"mn\":\"ADBE Vector Group\",\"hd\":false}],\"ip\":0,\"op\":296,\"st\":0,\"bm\":0}],\"markers\":[]}");
	}
	
	
	public void _AdvancedView(final View _v, final String _colour1, final String _colour2, final String _borderColour, final double _cornerRadius, final double _borderThickness, final String _eColour, final double _elevation) {
		int[] colors = {Color.parseColor(_colour1),Color.parseColor(_colour2)};
		android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(android.graphics.drawable.GradientDrawable.Orientation.BR_TL, colors); //Made by XenonDry
		gd.setCornerRadius((float)_cornerRadius); 
		gd.setStroke((int)_borderThickness, (Color.parseColor(_borderColour)));
		_v.setBackground(gd);
		//Made by XenonDry
		_v.setElevation((float)_elevation);
		
		_v.setOutlineAmbientShadowColor(Color.parseColor(_eColour));
		_v.setOutlineSpotShadowColor(Color.parseColor(_eColour));
	}
	
	
	public void _telegramLoaderDialog(final boolean _visibility) {
		if (_visibility) {
			if (coreprog == null){
					coreprog = new ProgressDialog(this);
					coreprog.setCancelable(false);
					coreprog.setCanceledOnTouchOutside(false);
					
					coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
					
			}
			coreprog.show();
			coreprog.setContentView(R.layout.loading);
			
			
			LinearLayout linear2 = (LinearLayout)coreprog.findViewById(R.id.linear2);
			
			LinearLayout back = (LinearLayout)coreprog.findViewById(R.id.background);
			
			LinearLayout layout_progress = (LinearLayout)coreprog.findViewById(R.id.layout_progress);
			
			if (save.getString("theme", "").equals("light")) {
					android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
					gd.setColor(Color.parseColor("#FFFFFF")); /* color */
					gd.setCornerRadius(45); /* radius */
					gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
					linear2.setBackground(gd);
			}
			else {
					if (save.getString("theme", "").equals("dark")) {
							android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable(); 
							gd.setColor(Color.parseColor("#212121")); /* color */
							gd.setCornerRadius(45); /* radius */
							gd.setStroke(0, Color.WHITE); /* stroke heigth and color */
							linear2.setBackground(gd);
					}
			}
			
			RadialProgressView progress = new RadialProgressView(this);
			layout_progress.addView(progress);
			
		}
		else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
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