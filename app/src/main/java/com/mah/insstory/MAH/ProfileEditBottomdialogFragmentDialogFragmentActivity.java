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
import android.widget.EditText;
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
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
import java.util.*;
import java.util.HashMap;
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileEditBottomdialogFragmentDialogFragmentActivity extends BottomSheetDialogFragment {
	
	public final int REQ_CD_FP = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String AvatarPath = "";
	private String AvatarName = "";
	private String AvatarUrlOld = "";
	private String AvatarUrlNew = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String PATTERN = "";
	
	private LinearLayout body;
	private LinearLayout top_layout;
	private CircleImageView profile_image;
	private EditText name;
	private EditText username;
	private EditText biography;
	private TextView save_button;
	private TextView title;
	private ImageView cancel;
	
	private Intent intent = new Intent();
	private SharedPreferences save;
	private SharedPreferences lang;
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
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
	
	private StorageReference pdb = _firebase_storage.getReference("profile/avatar");
	private OnCompleteListener<Uri> _pdb_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _pdb_download_success_listener;
	private OnSuccessListener _pdb_delete_success_listener;
	private OnProgressListener _pdb_upload_progress_listener;
	private OnProgressListener _pdb_download_progress_listener;
	private OnFailureListener _pdb_failure_listener;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
	
	@NonNull
	@Override
	public View onCreateView(@NonNull LayoutInflater _inflater, @Nullable ViewGroup _container, @Nullable Bundle _savedInstanceState) {
		View _view = _inflater.inflate(R.layout.profile_edit_bottomdialog_fragment_dialog_fragment, _container, false);
		initialize(_savedInstanceState, _view);
		FirebaseApp.initializeApp(getContext());
		initializeLogic();
		return _view;
	}
	
	private void initialize(Bundle _savedInstanceState, View _view) {
		body = _view.findViewById(R.id.body);
		top_layout = _view.findViewById(R.id.top_layout);
		profile_image = _view.findViewById(R.id.profile_image);
		name = _view.findViewById(R.id.name);
		username = _view.findViewById(R.id.username);
		biography = _view.findViewById(R.id.biography);
		save_button = _view.findViewById(R.id.save_button);
		title = _view.findViewById(R.id.title);
		cancel = _view.findViewById(R.id.cancel);
		save = getContext().getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getContext().getSharedPreferences("lang", Activity.MODE_PRIVATE);
		auth = FirebaseAuth.getInstance();
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		
		profile_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		save_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (name.getText().toString().equals("") || (username.getText().toString().equals("") || biography.getText().toString().equals(""))) {
					SketchwareUtil.showMessage(getContext().getApplicationContext(), "Fill All Items");
				}
				else {
					Pattern pattern = Pattern.compile(PATTERN);
					Matcher matcher = pattern.matcher(username.getText().toString());
					if (matcher.matches()) {
						if (AvatarPath.equals("") && AvatarName.equals("")) {
							map = new HashMap<>();
							map.put("yourname", name.getText().toString());
							map.put("username", username.getText().toString());
							map.put("bio", biography.getText().toString());
							udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
							map.clear();
							dismiss();
						}
						else {
							map = new HashMap<>();
							map.put("yourname", name.getText().toString());
							map.put("username", username.getText().toString());
							map.put("bio", biography.getText().toString());
							pdb.child(AvatarName).putFile(Uri.fromFile(new File(AvatarPath))).addOnFailureListener(_pdb_failure_listener).addOnProgressListener(_pdb_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
								@Override
								public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
									return pdb.child(AvatarName).getDownloadUrl();
								}}).addOnCompleteListener(_pdb_upload_success_listener);
							udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
							map.clear();
						}
					}
					else {
						SketchwareUtil.showMessage(getContext().getApplicationContext(), "Username contains invalid characters");
					}
				}
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dismiss();
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					name.setText(_childValue.get("yourname").toString());
					username.setText(_childValue.get("username").toString());
					biography.setText(_childValue.get("bio").toString());
					Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(profile_image);
					AvatarUrlOld = _childValue.get("avatar").toString();
				}
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					name.setText(_childValue.get("yourname").toString());
					username.setText(_childValue.get("username").toString());
					biography.setText(_childValue.get("bio").toString());
					Glide.with(getContext().getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(profile_image);
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
		
		_pdb_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onProgress(UploadTask.TaskSnapshot _param1) {
				double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
				SketchwareUtil.showMessage(getContext().getApplicationContext(), "Please wait Updating Profile to server....".concat(String.valueOf((long)(_progressValue))));
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
				map = new HashMap<>();
				map.put("avatar", _downloadUrl);
				udb.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map);
				dismiss();
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
				SketchwareUtil.showMessage(getContext().getApplicationContext(), _message);
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
		PATTERN = "^[a-z0-9_-]{5,15}$";
		
		title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),"fonts/medium.ttf"), 1);
		save_button.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange_gradient_button));
		profile_image.setElevation((float)20);
		name.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		name.setElevation((float)20);
		username.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		username.setElevation((float)20);
		biography.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)100, 0xFFFFFFFF));
		biography.setElevation((float)20);
	}
	
	@Override
	public void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			case REQ_CD_FP:
			if (_resultCode == Activity.RESULT_OK) {
				ArrayList<String> _filePath = new ArrayList<>();
				if (_data != null) {
					if (_data.getClipData() != null) {
						for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
							ClipData.Item _item = _data.getClipData().getItemAt(_index);
							_filePath.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), _item.getUri()));
						}
					}
					else {
						_filePath.add(FileUtil.convertUriToFilePath(getContext().getApplicationContext(), _data.getData()));
					}
				}
				AvatarPath = _filePath.get((int)(0));
				if (AvatarPath.endsWith(".png")) {
					AvatarName = Uri.parse(AvatarPath).getLastPathSegment();
					profile_image.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(AvatarPath, 1024, 1024));
				}
				else {
					if (AvatarPath.endsWith(".jpg")) {
						AvatarName = Uri.parse(AvatarPath).getLastPathSegment();
						profile_image.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(AvatarPath, 1024, 1024));
					}
					else {
						SketchwareUtil.showMessage(getContext().getApplicationContext(), "Invalid File Type");
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
	
	
	public void _TrLang() {
		title.setText("Profili Düzenle");
		name.setHint("İsim");
		username.setHint("Kullanıcı Adı");
		biography.setHint("Biyografi");
		save_button.setText("KAYDET");
	}
	
	
	public void _EnLang() {
		title.setText("Edit Profile");
		name.setHint("Name");
		username.setHint("Username");
		biography.setHint("Biography");
		save_button.setText("SAVE");
	}
	
	
	public void _InLang() {
		title.setText("Edit Profil");
		name.setHint("Nama");
		username.setHint("Nama Pengguna");
		biography.setHint("Biografi");
		save_button.setText("SIMPAN");
	}
	
}