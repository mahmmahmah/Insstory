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
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import java.util.regex.*;
import jp.wasabeef.picasso.transformations.*;
import org.json.*;

public class CreatePostActivity extends AppCompatActivity {
	
	public final int REQ_CD_FP = 101;
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
	
	private String ImgPath = "";
	private String ImgName = "";
	private HashMap<String, Object> map = new HashMap<>();
	private String UserName = "";
	private String Verify = "";
	private String Avatar = "";
	private String PostKey = "";
	private String SelectPicture = "";
	private String fontName = "";
	private String typeace = "";
	
	private LinearLayout body;
	private CardView top;
	private ScrollView scroll;
	private LinearLayout img_pick;
	private LinearLayout top_;
	private ImageView cancel;
	private TextView title;
	private ImageView save;
	private LinearLayout image_layout;
	private LinearLayout post_s_top;
	private LinearLayout post_settings;
	private CircleImageView avatar;
	private EditText post_desc;
	private ImageView image;
	private TextView post_settings_title;
	private LinearLayout disable_comments;
	private LinearLayout disable_save_post;
	private LinearLayout hide_like_count;
	private LinearLayout disable_comments_title_lay;
	private TextView disable_comments_subtext;
	private TextView disable_comments_title;
	private CheckBox disable_comments_check;
	private LinearLayout disable_save_post_title_lay;
	private TextView disable_save_post_subtext;
	private TextView disable_save_post_title;
	private CheckBox disable_save_post_check;
	private LinearLayout hide_like_count_title_lay;
	private TextView hide_like_count_subtext;
	private TextView hide_like_count_title;
	private CheckBox hide_like_count_check;
	private ImageView add_a_image;
	private TextView subtext;
	
	private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
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
	
	private DatabaseReference udb = _firebase.getReference("users");
	private ChildEventListener _udb_child_listener;
	private DatabaseReference pdb = _firebase.getReference("posts");
	private ChildEventListener _pdb_child_listener;
	private StorageReference psd = _firebase_storage.getReference("posts/image");
	private OnCompleteListener<Uri> _psd_upload_success_listener;
	private OnSuccessListener<FileDownloadTask.TaskSnapshot> _psd_download_success_listener;
	private OnSuccessListener _psd_delete_success_listener;
	private OnProgressListener _psd_upload_progress_listener;
	private OnProgressListener _psd_download_progress_listener;
	private OnFailureListener _psd_failure_listener;
	
	private Calendar cc = Calendar.getInstance();
	private SharedPreferences save1;
	private SharedPreferences lang;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.create_post);
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
		top = findViewById(R.id.top);
		scroll = findViewById(R.id.scroll);
		img_pick = findViewById(R.id.img_pick);
		top_ = findViewById(R.id.top_);
		cancel = findViewById(R.id.cancel);
		title = findViewById(R.id.title);
		save = findViewById(R.id.save);
		image_layout = findViewById(R.id.image_layout);
		post_s_top = findViewById(R.id.post_s_top);
		post_settings = findViewById(R.id.post_settings);
		avatar = findViewById(R.id.avatar);
		post_desc = findViewById(R.id.post_desc);
		image = findViewById(R.id.image);
		post_settings_title = findViewById(R.id.post_settings_title);
		disable_comments = findViewById(R.id.disable_comments);
		disable_save_post = findViewById(R.id.disable_save_post);
		hide_like_count = findViewById(R.id.hide_like_count);
		disable_comments_title_lay = findViewById(R.id.disable_comments_title_lay);
		disable_comments_subtext = findViewById(R.id.disable_comments_subtext);
		disable_comments_title = findViewById(R.id.disable_comments_title);
		disable_comments_check = findViewById(R.id.disable_comments_check);
		disable_save_post_title_lay = findViewById(R.id.disable_save_post_title_lay);
		disable_save_post_subtext = findViewById(R.id.disable_save_post_subtext);
		disable_save_post_title = findViewById(R.id.disable_save_post_title);
		disable_save_post_check = findViewById(R.id.disable_save_post_check);
		hide_like_count_title_lay = findViewById(R.id.hide_like_count_title_lay);
		hide_like_count_subtext = findViewById(R.id.hide_like_count_subtext);
		hide_like_count_title = findViewById(R.id.hide_like_count_title);
		hide_like_count_check = findViewById(R.id.hide_like_count_check);
		add_a_image = findViewById(R.id.add_a_image);
		subtext = findViewById(R.id.subtext);
		fp.setType("image/*");
		fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
		auth = FirebaseAuth.getInstance();
		save1 = getSharedPreferences("save", Activity.MODE_PRIVATE);
		lang = getSharedPreferences("lang", Activity.MODE_PRIVATE);
		
		cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				finish();
			}
		});
		
		save.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (ImgPath.equals("")) {
					SketchwareUtil.showMessage(getApplicationContext(), SelectPicture);
				}
				else {
					psd.child(ImgName).putFile(Uri.fromFile(new File(ImgPath))).addOnFailureListener(_psd_failure_listener).addOnProgressListener(_psd_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
						@Override
						public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
							return psd.child(ImgName).getDownloadUrl();
						}}).addOnCompleteListener(_psd_upload_success_listener);
				}
			}
		});
		
		image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		disable_comments.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disable_comments_check.performClick();
			}
		});
		
		disable_save_post.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				disable_save_post_check.performClick();
			}
		});
		
		hide_like_count.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				hide_like_count_check.performClick();
			}
		});
		
		add_a_image.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				startActivityForResult(fp, REQ_CD_FP);
			}
		});
		
		_udb_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				if (_childKey.equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
					Glide.with(getApplicationContext()).load(Uri.parse(_childValue.get("avatar").toString())).into(avatar);
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
		
		_pdb_child_listener = new ChildEventListener() {
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
				PostKey = pdb.push().getKey();
				map = new HashMap<>();
				map.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
				map.put("post_key", PostKey);
				map.put("post_image", _downloadUrl);
				if (disable_comments_check.isChecked()) {
					map.put("disable_comments", "true");
				}
				else {
					map.put("disable_comments", "false");
				}
				if (disable_save_post_check.isChecked()) {
					map.put("disable_save", "true");
				}
				else {
					map.put("disable_save", "false");
				}
				if (hide_like_count_check.isChecked()) {
					map.put("hide_like_count", "true");
				}
				else {
					map.put("hide_like_count", "false");
				}
				map.put("desc", post_desc.getText().toString());
				pdb.child(PostKey).updateChildren(map);
				map.clear();
				finish();
			}
		};
		
		_psd_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
				final long _totalByteCount = _param1.getTotalByteCount();
				
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
		scroll.setVisibility(View.GONE);
		img_pick.setVisibility(View.VISIBLE);
		title.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/medium.ttf"), 1);
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
				ImgPath = _filePath.get((int)(0));
				ImgName = pdb.push().getKey().concat(".png");
				image.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(ImgPath, 1024, 1024));
				scroll.setVisibility(View.VISIBLE);
				img_pick.setVisibility(View.GONE);
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
		_ThemeCustom();
		_languages();
	}
	public void _languages() {
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
		title.setText("Gönderi Oluştur");
		subtext.setText("Gönderi paylaşmak için yukarıdaki \"+\" butonuna tıklayın ve resim seçin. Gönderi yayınlarken lütfen topluluk kurallarımıza uyun!");
		post_desc.setHint("Açıklama");
		post_settings_title.setText("Gönderi Ayarları");
		disable_comments_title.setText("Yorumları Devre Dışı Bırak");
		disable_comments_subtext.setText("Bunu aktif ettiğinizde kimse gönderinize yorum yapamaz");
		disable_save_post_title.setText("Kaydetmeyi Devre Dışı Bırak");
		disable_save_post_subtext.setText("Bunu aktif ettiğinizde kimse gönderinizi kaydedemez");
		hide_like_count_title.setText("Beğeni Sayısını Gizle");
		hide_like_count_subtext.setText("Bunu aktif ettiğinizde favori arkadaşların ve sen dışında kimse beğeni sayısını görmez");
	}
	
	
	public void _EnLang() {
		title.setText("Create Post");
		subtext.setText("Click the \"+\" button above and select an image to create your own post. Comply with our community guidelines when posting");
		post_desc.setHint("Description");
		post_settings_title.setText("Post Settings");
		disable_comments_title.setText("Disable Comments");
		disable_comments_subtext.setText("When you enable this no one can comment on your post");
		disable_save_post_title.setText("Disable Save Post");
		disable_save_post_subtext.setText("When you enable this no one can save your post");
		hide_like_count_title.setText("Hide Like Count");
		hide_like_count_subtext.setText("When you activate this no one will see the number of likes except your favorite friends and you");
	}
	
	
	public void _ThemeCustom() {
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		getWindow().setStatusBarColor(0xFFFFFFFF);
		top_.setBackgroundColor(0xFFFFFFFF);
		body.setBackgroundColor(0xFFFFFFFF);
		title.setTextColor(0xFF000000);
		_ImageColor(cancel, "#000000");
		_ImageColor(save, "#000000");
		_ImageColor(add_a_image, "#000000");
		_rippleRoundStroke(disable_comments, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
		_rippleRoundStroke(disable_save_post, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
		_rippleRoundStroke(hide_like_count, "#F5F5F5", "#BDBDBD", 20, 0, "#FFFFFF");
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
	
	
	public void _InLang() {
		title.setText("Buat Postingan");
		subtext.setText("Klik tombol \"+\" di atas dan pilih gambar untuk membuat postingan Anda sendiri. Patuhi pedoman komunitas kami saat memposting");
		post_desc.setHint("Deskripsi");
		post_settings_title.setText("Post Settings");
		disable_comments_title.setText("Nonaktifkan Komentar");
		disable_comments_subtext.setText("Saat Anda mengaktifkan ini, tidak ada yang dapat mengomentari kiriman Anda");
		disable_save_post_title.setText("Nonaktifkan Simpan Postingan");
		disable_save_post_subtext.setText("Saat Anda mengaktifkan ini, tidak ada yang dapat menyimpan postingan Anda");
		hide_like_count_title.setText("Sembunyikan Jumlah Suka");
		hide_like_count_subtext.setText("Saat Anda mengaktifkan ini, tidak ada yang akan melihat jumlah suka kecuali teman favorit Anda dan Anda");
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